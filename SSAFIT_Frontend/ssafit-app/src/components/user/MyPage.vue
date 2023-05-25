<template>
  <v-container class="my-page">
    <h3>마이 페이지</h3>
    <v-row>
      <v-col cols="6">
        <v-card class="info-card">
          <v-card-text>
            <div class="info-item">
              <span class="info-label">아이디:</span>
              <span class="info-value">{{ id }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">이메일:</span>
              <span class="info-value">{{ email }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">별명:</span>
              <span class="info-value">{{ nickname }}</span>
            </div>
          </v-card-text>
          <v-img
            v-if="image !== 'none'"
            :src="require(`@/assets/upload/${id}/${image}`)"
            width="300"
            height="300"
            class="profile-image"
          ></v-img>
          <v-img
            v-else
            :src="require(`@/assets/upload/none.png`)"
            width="300"
            height="300"
            class="profile-image"
          ></v-img>
          <v-card-actions>
            <v-btn color="primary" @click="goModify">정보 수정하기</v-btn>
            <v-btn color="error" @click="goWithdraw">탈퇴하기</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
      <v-col cols="6">
        <v-card class="list-card">
          <v-card-text>
            <div>
              <h3>팔로우 목록: {{ followList.length }}명</h3>
            </div>
            <div
              v-for="follow in followList"
              :key="follow.yourIdx"
              class="follow-item"
              @click="getFollow(follow)"
            >
              {{ follow.yourNickname }} 님
            </div>
          </v-card-text>
        </v-card>
        <v-card class="list-card">
          <v-card-text>
            <div>
              <h3>좋아요 영상 리스트</h3>
            </div>
            <div v-for="like in likeList" :key="like.idx" class="like-item">
              <iframe
                width="550 "
                height="310"
                :src="`https://www.youtube.com/embed/${like.videoId}`"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen
              ></iframe>
            </div>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <div>
      <input type="text" v-model="search" placeholder="유저 검색" @keyup.enter="searchUser">
      <button @click="searchUser">유저 검색</button>
    </div>
    <div v-if="this.searchUsers">
      <div v-for="searchUser in searchUsers" :key="searchUser.idx">
        {{ searchUser.nickname }}
      </div>
    </div>
    <v-dialog v-model="followDialog" max-width="800">
      <v-card>
        <v-card-title>
          <span class="headline">{{ followUser }}님의 좋아요 리스트</span>
        </v-card-title>
        <v-card-text>
          <div
            v-for="video in followLikeList"
            :key="video.videoIdx"
            class="follow-like-item"
          >
            <iframe
              width="400"
              height="250"
              :src="`https://www.youtube.com/embed/${video.videoId}`"
              title="YouTube video player"
              frameborder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
              allowfullscreen
            ></iframe>
          </div>
        </v-card-text>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialog" max-width="400">
      <v-card>
        <v-card-title>
          <span class="headline">탈퇴하기</span>
        </v-card-title>
        <v-card-text>
          <span>정말 탈퇴하시겠습니까?</span>
        </v-card-text>
        <v-card-actions>
          <v-btn color="error" @click="withdraw">탈퇴하기</v-btn>
          <v-btn text @click="dialog = false">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "MyPage",
  data() {
    return {
      idx: "",
      id: "",
      email: "",
      nickname: "",
      password: "",
      image: "",
      likeList: "",
      followList: "",
      followDialog: false,
      followLikeList: "",
      followUser: "",
      dialog: false,
      search: '',
      searchUsers: '',
    };
  },
  computed: {
    ...mapState(["user"]),
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
          token: sessionStorage.getItem("access-token"),
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
        });
    },
    getFollowList() {
      const API_URL = `http://localhost:9999/api-follow/list/${this.idx}`;
      axios({
        url: API_URL,
        method: "GET",
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
      }).then((res) => {
        this.followList = res.data;
      });
    },
    getLikeList() {
      const API_URL = `http://localhost:9999/api-like/list-user/${this.idx}`;
      axios({
        url: API_URL,
        method: "GET",
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
      }).then((res) => {
        this.likeList = res.data;
      });
    },
    getFollow(f) {
      const API_URL = `http://localhost:9999/api-like/list-user/${f.yourIdx}`;
      axios({
        url: API_URL,
        method: "GET",
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
      }).then((res) => {
        this.followUser = f.yourNickname;
        this.followLikeList = res.data;
        this.followDialog = true;
      });
    },
    searchUser() {
      if(this.search === '') {
        alert("닉네임을 입력해주세요.");
        return;
      }
      const API_URL = `http://localhost:9999/api-follow/find`;
      axios({
        url: API_URL,
        method: "GET",
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
        params: {
          yourNickname : this.nickname,
        }
      })
      .then((res) => {
        console.log(res.data);
        this.searchUsers = res.data;
      })
    }
  },
};
</script>

<style scoped>
.my-page {
  margin: 20px auto;
  max-width: 1200px;
}

.info-card {
  background-color: orange;
  color: white;
  margin-bottom: 20px;
  padding: 20px;
  text-align: center;
}

.profile-image {
  margin: 20px auto;
}

.list-card {
  margin-bottom: 20px;
}

.follow-item,
.like-item {
  margin-bottom: 10px;
}

.follow-like-item {
  margin-bottom: 20px;
}

.v-dialog {
  z-index: 9999;
}
.info-item {
  margin-bottom: 10px;
}

.info-label {
  font-size: 18px;
}

.info-value {
  font-size: 20px;
}
</style>
