<template>
    <div>
        <h3>마이 페이지</h3>
        <div>아이디 : {{ id }}</div>
        <div>이메일 : {{ email }}</div>
        <div>별명 : {{ nickname }}</div>
        <img v-if="image != 'none'" :src="require(`@/assets/upload/${id}/${image}`)"><br>
        <div v-if="followList">
            <div>팔로우 목록 : {{ followList.length }}명</div>
            <div v-for="follow in followList" :key="follow.yourIdx" @click="getFollow(follow)">
                {{ follow.yourNickname }} 님
            </div>
        </div>
        <div v-if="likeList">
            <div>좋아요 영상 리스트</div>
            <div v-for="like in likeList" :key="like.idx">
                <iframe width="400" height="250" :src="`https://www.youtube.com/embed/` + like.videoId"
                    title="YouTube video player" frameborder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                    allowfullscreen></iframe>
            </div>
        </div>
        <button @click="goModify">정보 수정하기</button><br>
        <button @click="goWithdraw">탈퇴하기</button>
        <v-app>
            <v-dialog v-model="followDialog" width="800px" height="600px">
                <v-card>
                    <div>{{ followUser }}님의 좋아요 리스트</div>
                    <div v-for="video in followLikeList" :key="video.videoIdx">
                        <iframe width="400" height="250" :src="`https://www.youtube.com/embed/` + video.videoId"
                            title="YouTube video player" frameborder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                            allowfullscreen></iframe>
                    </div>
                </v-card>
            </v-dialog>
        </v-app>
        <v-app>
            <v-dialog v-model="dialog" max-width="1000px">
                <v-card>
                    <v-card-title>
                        <span class="headline">탈퇴하기</span>
                    </v-card-title>
                    <span>정말 탈퇴하시겠습니까?</span><br>
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
            likeList: '',
            followList: '',
            followDialog: false,
            followLikeList: '',
            followUser: '',
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
        this.getFollowList();
        this.getLikeList();
    },
    methods: {
        goModify() {
            this.$router.push("modifyInfo");
        },
        goWithdraw() {
            this.dialog = true;
        },
        withdraw() {
            const API_URL = `http://localhost:9999/api-user/remove/${this.idx}`;
            axios({
                url: API_URL,
                method: "DELETE",
                headers: {
                    "token": sessionStorage.getItem("access-token"),
                },
            })
                .then(() => {
                    alert("회원 탈퇴가 완료되었습니다.");
                    this.$store.dispatch("clearUser");
                    sessionStorage.removeItem("access-token");
                    this.$router.push("/");
                })
                .catch((err) => {
                    console.log(err);
                })
        },
        getFollowList() {
            const API_URL = `http://localhost:9999/api-follow/list/${this.idx}`;
            axios({
                url: API_URL,
                method: "GET",
                headers: {
                    "token": sessionStorage.getItem("access-token"),
                },
            })
                .then((res) => {
                    this.followList = res.data;
                })
        },
        getLikeList() {
            const API_URL = `http://localhost:9999/api-like/list-user/${this.idx}`;
            axios({
                url: API_URL,
                method: "GET",
                headers: {
                    "token": sessionStorage.getItem("access-token"),
                },
            })
                .then((res) => {
                    this.likeList = res.data;
                })
        },
        getFollow(f) {
            const API_URL = `http://localhost:9999/api-like/list-user/${f.yourIdx}`;
            axios({
                url: API_URL,
                method: "GET",
                headers: {
                    "token": sessionStorage.getItem("access-token"),
                },
            })
                .then((res) => {
                    this.followUser = f.yourNickname;
                    this.followLikeList = res.data;
                    this.followDialog = true;
                })
        },
    }
}
</script>

<style></style>