<template>
    <div>
        <h3>회원 정보 수정</h3>
        <fieldset>
            <legend>회원 정보 수정</legend>
            <label for="id">아이디 : </label>
            <input type="text" id="id" v-model="id" readonly><br />
            <label for="password">비밀번호 : </label>
            <input type="password" id="password" v-model="password"><br />
            <label for="nickname">별명 : </label>
            <input type="text" id="nickname" v-model="nickname" :readonly="nicknameCheck">
            <button v-if="!nicknameCheck" @click="checkNickname">별명 중복 확인</button><br />
            <label for="email">이메일 : </label>
            <input type="email" id="email" v-model="email" :readonly="emailCheck"/>
            <button v-if="!emailCheck" @click="checkEmail">이메일 중복 확인</button><br />
            <label for="file">프로필 사진 </label>
            <input type="file" id="file" @change="image" ref="profile" /><br />
            <button @click="modify">수정하기</button>
        </fieldset>
    </div>
</template>

<script>
import { mapState } from 'vuex';
import axios from 'axios';
export default {
    name: 'ModifyInfo',
    data() {
        return {
            idx: '',
            id: '',
            password: '',
            nickname: '',
            email: '',
            image: '',
            emailCheck: false,
            nicknameCheck: false,
        };
    },
    computed: {
        ...mapState(['user']),
    },
    created() {
        this.idx = this.user.idx;
        this.id = this.user.id;
        this.password = this.user.password;
        this.nickname = this.user.nickname;
        this.email = this.user.email;
    },
    methods: {
        modify() {
            if (this.password === '' ||
                this.email === '' ||
                this.nickname === '') {
                alert("모두 입력해주세요.");
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
            const API_URL = `${REST_API}/api-user/modify`;
            let user = {
                idx: this.idx,
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
            axios({
                url: API_URL,
                method: "PUT",
                params: file,
                headers: {
                    "Content-Type": "multipart/form-data",
                    "token": sessionStorage.getItem("access-token"),
                },
                transformRequest: [
                    function () {
                        return file;
                    }
                ]
            })
                .then(() => {
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        inputImage() {
            this.image = this.$refs.profile.files;
        },
        checkNickname() {
            const REST_API = `http://localhost:9999`;
            const API_URL = `${REST_API}/api-user/check/nickname/${this.nickname}/${this.idx}`;
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
        checkEmail() {
            const REST_API = `http://localhost:9999`;
            const API_URL = `${REST_API}/api-user/check/email/${this.email}/${this.idx}`;
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
    }
}
</script>

<style></style>