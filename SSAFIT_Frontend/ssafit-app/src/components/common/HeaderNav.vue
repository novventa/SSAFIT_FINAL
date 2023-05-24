<template>
  <div>
    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>SSAFIT</v-toolbar-title>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" fixed temporary>
      <v-list>
        <v-list-item @click="loginPage('user/login')">
          <v-list-item-icon>
            <v-icon>mdi-login</v-icon>
          </v-list-item-icon>
          <v-list-item-title>로그인</v-list-item-title>
        </v-list-item>
        <v-list-item @click="signUpPage('user/create')">
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

export default {
  data: () => ({ drawer: null }),
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
