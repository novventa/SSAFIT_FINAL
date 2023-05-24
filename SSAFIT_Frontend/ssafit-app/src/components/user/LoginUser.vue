<template>
    <div>
        <h3>로그인</h3>
        <fieldset>
            <legend>로그인</legend>
            <label for="id">아이디 : </label>
            <input type="text" id="id" v-model="id" @keyup.enter="login"/><br />
            <label for="password">비밀번호 : </label>
            <input type="password" id="password" v-model="password" @keyup.enter="login"/><br />
            <button @click="login">로그인</button>
        </fieldset>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'LoginUser',
    data() {
        return {
            id: '',
            password: '',
        }
    },
    methods: {
        login() {
            if(this.id === '' ||
            this.password === '') {
                alert("아이디와 비밀번호를 모두 입력해주세요.");
                return;
            }
            let user = {
                id: this.id,
                password: this.password,
            }
            const API_URL = `http://localhost:9999/api-user/login`;
            axios({
                url: API_URL,
                method: "POST",
                params: user,
            })
            .then((res) => {
                sessionStorage.setItem("access-token", res.data["access-token"]);
                this.$store.dispatch("login", res.data["user"]);
                this.$store.dispatch("getLikeList");
                this.$router.push("/");
            })
            .catch((err) => {
                console.log(err);
            })
        },
    }
}
</script>

<style></style>