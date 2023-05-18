<template>
  <v-app>
    <v-main>
      <header-nav></header-nav>
      <router-view />
      <footer-bar></footer-bar>
    </v-main>
  </v-app>
</template>

<script>
import FooterBar from "./components/common/FooterBar.vue";
import HeaderNav from "./components/common/HeaderNav.vue";

export default {
  name: "App",
  components: { HeaderNav, FooterBar },
};
</script>
