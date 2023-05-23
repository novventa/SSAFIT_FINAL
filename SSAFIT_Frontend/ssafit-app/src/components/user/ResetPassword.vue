<template>
    <div>
        <h3>비밀번호 초기화</h3>
        <fieldset>
            <legend>비밀번호 초기화</legend>
            <label for="id">아이디 : </label>
            <input type="text" id="id" v-model="id" /><br />
            <label for="email">이메일 : </label>
            <input type="email" id="email" v-model="email" /><br />
            <label for="nickname">별명 : </label>
            <input type="text" id="nickname" v-model="nickname" /><br />
            <button @click="confirm">계정 확인</button>
        </fieldset>
        <v-app>
            <v-dialog v-model="dialog" max-width="1000px">
                <v-card>
                    <v-card-title>
                        <span class="headline">비밀번호 변경</span>
                    </v-card-title>
                    <label for="password">비밀번호 : </label>
                    <input type="password" id="password" v-model="password"><br />
                    <button @click="resetPassword">변경하기</button>
                </v-card>
            </v-dialog>
        </v-app>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: 'ResetPassword',
    data() {
        return {
            idx: '',
            id: '',
            email: '',
            nickname: '',
            password: '',
            dialog: false,
        }
    },
    methods: {
        confirm() {
            let user = {
                id: this.id,
                email: this.email,
                nickname: this.nickname,
            }
            const API_URL = `http://localhost:9999/api-user/confirm`;
            axios({
                url: API_URL,
                method: "GET",
                params: user,
                headers: {
                "token": sessionStorage.getItem("access-token"),
            },
            })
                .then((res) => {
                    this.idx = res.data.idx;
                    alert("본인 인증되었습니다.");
                    this.dialog = true;
                })
                .catch((err) => {
                    console.log(err);
                })
        },
        resetPassword() {
            let user = {
                idx: this.idx,
                password: this.password,
            }
            const API_URL = `http://localhost:9999/api-user/modifyPassword`;
            axios({
                url: API_URL,
                method: "PUT",
                params: user,
                headers: {
                "token": sessionStorage.getItem("access-token"),
            },
            })
                .then(() => {
                    alert("비밀번호가 변경되었습니다.");
                    this.dialog = false;
                    this.$router.push("login");
                })
                .catch((err) => {
                    console.log(err);
                })
        }

    }
}
</script>

<style></style>