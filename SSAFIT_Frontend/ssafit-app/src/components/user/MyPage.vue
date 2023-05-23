<template>
    <div>
        <h3>마이 페이지</h3>
        <div>아이디 : {{ id }}</div>
        <div>이메일 : {{ email }}</div>
        <div>별명 : {{ nickname }}</div>
        <img :src="require(`@/assets/upload/${id}/${image}`)"><br>
        <button @click="goModify">정보 수정하기</button><br>
        <button @click="goWithdraw">탈퇴하기</button>
        <v-app>
            <v-dialog v-model="dialog" max-width="1000px">
                <v-card>
                    <v-card-title>
                        <span class="headline">탈퇴하기</span>
                    </v-card-title>
                    <span>정말 탈퇴하시겠습니까?</span>
                    <button @click="withdraw">탈퇴하기</button>
                </v-card>
            </v-dialog>
        </v-app>
    </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';
export default {
    name: 'MyPage',
    data() {
        return {
            idx: '',
            id: '',
            email: '',
            nickname: '',
            password: '',
            image: '',
            dialog: false, 
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
        this.image = this.user.image;
    },
    methods: {
        goModify() {
            this.$router.push("modifyInfo");
        },
        goWithdraw() {
            this.dialog = true;
        },
        withdraw() {
            const API_URL = `http://localhost:9999/api-user/withdraw`;
            axios({
                url: API_URL,
                method: "DELETE",
                params: this.idx,
                headers: {
                "token": sessionStorage.getItem("access-token"),
                },
            })
            .then(() => {
                alert("회원 탈퇴가 완료되었습니다.");
                sessionStorage.removeItem("access-token");
                this.$router.push("/");
            })
            .catch((err) => {
                console.log(err);
            })
        },
    }
}
</script>

<style>

</style>