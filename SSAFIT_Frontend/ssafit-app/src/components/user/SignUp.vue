<template>
    <div>
        <h3>회원 가입</h3>
        <fieldset>
            <legend>가입</legend>
            <label for="id">아이디 : </label>
            <input type="text" id="id" v-model="id" :readonly="idCheck">
            <button v-if="!idCheck" @click="checkId">아이디 중복 체크</button><br />
            <label for="password">비밀번호 : </label>
            <input type="password" id="password" v-model="password" /><br />
            <label for="nickname">별명 : </label>
            <input type="text" id="nickname" v-model="nickname" :readonly="nicknameCheck" />
            <button v-if="!nicknameCheck" @click="checkNickname">별명 중복 체크</button><br />
            <label for="email">아메일 : </label>
            <input type="email" id="email" v-model="email" :readonly="emailCheck" />
            <button v-if="!emailCheck" @click="checkEmail">이메일 중복 체크</button><br />
            <label for="file">프로필 사진 </label>
            <input type="file" id="file" @change="inputImage" ref="profile" /><br />
            <button @click="createUser">가입하기</button>
        </fieldset>
    </div>
</template>
  
<script>
import axios from 'axios';
export default {
    name: 'SignUp',
    data() {
        return {
            id: '',
            password: '',
            nickname: '',
            email: '',
            image: '',
            idCheck: false,
            emailCheck: false,
            nicknameCheck: false,
        };
    },
    methods: {
        createUser() {
            if (this.id === '' ||
                this.password === '' ||
                this.email === '' ||
                this.nickname === '') {
                alert("모두 입력해주세요.");
                return;
            }
            if (!this.idCheck) {
                alert("아이디 중복 체크를 해주세요.");
                return;
            }
            if (!this.emailCheck) {
                alert("이메일 중복 체크를 해주세요.");
                return;
            }
            if (!this.nicknameCheck) {
                alert("닉네임 중복 체크를 해주세요.");
                return;
            }
            const REST_API = `http://localhost:9999`;
            const API_URL = `${REST_API}/api-user/add`;
            let user = {
                idx: 0,
                id: this.id,
                password: this.password,
                nickname: this.nickname,
                email: this.email,
                image: "none",
            };
            let file = new FormData();
            file.append("idx", user.idx);
            file.append("id", user.id);
            file.append("password", user.password);
            file.append("nickname", user.nickname);
            file.append("email", user.email);
            file.append("image", user.image);
            if (this.image != null) {
                file.append("file", this.image[0]);
            }
            else {
                file.append("file", null);
            }
            console.log(file);
            axios({
                url: API_URL,
                method: "POST",
                params: file,
                headers: {
                    "Content-Type": "multipart/form-data",
                },
                transformRequest: [
                    function () {
                        return file;
                    }
                ]
            })
                .then(() => {
                    this.$router.push("login");
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        inputImage() {
            this.image = this.$refs.profile.files;
        },
        checkId() {
            const REST_API = `http://localhost:9999`;
            const API_URL = `${REST_API}/api-user/check/id/${this.id}`;
            axios({
                url: API_URL,
                method: "GET",
            })
                .then(() => {
                    this.idCheck = true;
                    alert("사용 가능합니다.");
                })
                .catch((err) => {
                    alert(err.response.data.msg);
                })
        },
        checkEmail() {
            const REST_API = `http://localhost:9999`;
            const API_URL = `${REST_API}/api-user/check/email/${this.email}`;
            axios({
                url: API_URL,
                method: "GET",
            })
                .then(() => {
                    this.emailCheck = true;
                    alert("사용 가능합니다.");
                })
                .catch((err) => {
                    alert(err.response.data.msg);
                })
        },
        checkNickname() {
            const REST_API = `http://localhost:9999`;
            const API_URL = `${REST_API}/api-user/check/nickname/${this.nickname}`;
            axios({
                url: API_URL,
                method: "GET",
            })
                .then(() => {
                    this.nicknameCheck = true;
                    alert("사용 가능합니다.");
                })
                .catch((err) => {
                    alert(err.response.data.msg);
                })
        },
    },
};
</script>
  
<style></style>