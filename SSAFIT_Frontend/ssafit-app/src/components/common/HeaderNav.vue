<template>
  <div>
    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>SSAFIT</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-avatar v-bind="attrs" v-on="on">
            <img
              :src="require(`@/assets/upload/${id}/${image}`)"
              alt="프로필 사진"
            />
          </v-avatar>
        </template>
        <v-list>
          <v-list-item>
            <v-list-item-title>유저 닉네임</v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-title>프로필 편집</v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" fixed temporary>
      <v-list>
        <v-list-item @click="loginPage('/user/login')">
          <v-list-item-icon>
            <v-icon>mdi-login</v-icon>
          </v-list-item-icon>
          <v-list-item-title>로그인</v-list-item-title>
        </v-list-item>
        <v-list-item @click="signUpPage('/user/create')">
          <v-list-item-icon>
            <v-icon>mdi-account-plus</v-icon>
          </v-list-item-icon>
          <v-list-item-title>회원가입</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
import router from "@/router";
import { mapState } from "vuex";

export default {
  data: () => ({
    drawer: null,
    id: "",
    image: "",
    nickname: "",
  }),
  computed: {
    ...mapState(["user"]),
  },
  created() {
    this.id = this.user.id;
    this.image = this.user.image;
    this.nickname = this.user.nickname;
  },
  methods: {
    loginPage(route) {
      router.push(route);
      console.log(`Navigating to: ${route}`);
    },
    signUpPage(route) {
      router.push(route);
      console.log(`Navigating to: ${route}`);
    },
  },
};
</script>

<style></style>
