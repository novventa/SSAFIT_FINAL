<template>
  <v-toolbar class="white">
    <v-app-bar-nav-icon
      ><img src="@/assets/logo.png" alt="logo" height="25px" width="25px"
    /></v-app-bar-nav-icon>
    <v-toolbar-title class="mx-0">SSAFIT</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn>로그인</v-btn>
      <v-btn class="orange darken-1">회원가입</v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
export default {
  name: "App",
  data: function () {
    return {};
  },
  computed: {
    imgHeight: function () {
      var offset = 320;
      return this.pageHeight - offset;
    },
  },
};
</script>

<style scoped></style>
