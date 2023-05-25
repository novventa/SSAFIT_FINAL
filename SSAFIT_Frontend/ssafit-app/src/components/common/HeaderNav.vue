<template>
  <div>
    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title @click="goMain"
        >
        <img :src="require('@/assets/logo.png')" width="150" style="margin-top: 5px" />
      </v-toolbar-title>
      <v-spacer></v-spacer>

      <div v-show="Object.keys(user).length !== 0" class="profile-info">
        <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">
            <div v-if="user.image === 'none'">
              <v-avatar v-bind="attrs" v-on="on" size="36">
                <img
                  :src="require(`@/assets/upload/none.png`)"
                  alt="프로필 사진"
                />
              </v-avatar>
            </div>
            <div v-else>
              <v-avatar v-bind="attrs" v-on="on" size="36">
                <img
                  :src="require(`@/assets/upload/${id}/${image}`)"
                  alt="프로필 사진"
                />
              </v-avatar>
            </div>
          </template>
          <v-list>
            <v-list-item @click="myPage('/user/myPage')">
              <v-list-item-title>마이 페이지</v-list-item-title>
            </v-list-item>
            <v-list-item @click="logout">
              <v-list-item-title>로그아웃</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>

        <v-divider class="mx-3" vertical inset></v-divider>

        <div class="profile-name">
          <h4>{{ user.nickname }}님 환영합니다</h4>
        </div>
      </div>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" fixed temporary>
      <v-list>
        <v-list-item v-if="!user.id" @click="loginPage('/user/login')">
          <v-list-item-icon>
            <v-icon>mdi-login</v-icon>
          </v-list-item-icon>
          <v-list-item-title>로그인</v-list-item-title>
        </v-list-item>
        <v-list-item v-if="!user.id" @click="signUpPage('/user/create')">
          <v-list-item-icon>
            <v-icon>mdi-account-plus</v-icon>
          </v-list-item-icon>
          <v-list-item-title>회원가입</v-list-item-title>
        </v-list-item>
        <v-list-item v-if="user.id" @click="mainPage('/main')">
          <v-list-item-icon>
            <v-icon>mdi-home</v-icon>
          </v-list-item-icon>
          <v-list-item-title>홈</v-list-item-title>
        </v-list-item>
        <v-list-item v-if="user.id" @click="searchPage('/search')">
          <v-list-item-icon>
            <v-icon>mdi-magnify</v-icon>
          </v-list-item-icon>
          <v-list-item-title>영상 검색하기</v-list-item-title>
        </v-list-item>
        <v-list-item v-if="user.id" @click="addPage('/add')">
          <v-list-item-icon>
            <v-icon>mdi-plus</v-icon>
          </v-list-item-icon>
          <v-list-item-title>영상 등록하기</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
import router from "@/router";
import { mapState } from "vuex";
import { mapActions } from "vuex";

export default {
  data: () => ({
    drawer: null,
    id: "",
    image: "",
    nickname: "",
  }),
  computed: {
    ...mapState(["user"]),
    ...mapActions(["clearUser"]),
  },
  created() {
    this.id = this.user.id;
    this.image = this.user.image;
    this.nickname = this.user.nickname;
  },
  methods: {
    mainPage(route) {
      if (router.currentRoute.path !== "/main") {
        router.push(route);
      }
      console.log(`Navigating to: ${route}`);
    },
    loginPage(route) {
      if (router.currentRoute.path !== "/login") {
        router.push(route);
      }
      console.log(`Navigating to: ${route}`);
    },
    signUpPage(route) {
      if (router.currentRoute.path !== "/signUp") {
        router.push(route);
      }
      console.log(`Navigating to: ${route}`);
    },
    searchPage(route) {
      if (router.currentRoute.path !== "/search") {
        router.push(route);
      }
      console.log(`Navigating to: ${route}`);
    },
    addPage(route) {
      if (router.currentRoute.path !== "/add") {
        router.push(route);
      }
      console.log(`Navigating to: ${route}`);
    },
    myPage(route) {
      if (router.currentRoute.path !== "/myPage") {
        router.push(route);
      }
      console.log(`Navigating to: ${route}`);
    },
    logout() {
      sessionStorage.removeItem("access-token");
      this.clearUser;
      router.push("/");
      router.go(0);
    },
    goMain() {
      if (router.currentRoute.path !== "/main" && this.user.id) {
        router.push("/main");
      }
      if (router.currentRoute.path !== "/" && !this.user.id) {
        router.push("/");
      }
    },
  },
};
</script>

<style scoped>
.profile-info {
  display: flex;
  align-items: center;
}

.profile-name {
  margin-left: 1px;
}

.v-avatar {
  cursor: pointer;
}

.v-menu__content {
  width: 200px;
}
</style>
