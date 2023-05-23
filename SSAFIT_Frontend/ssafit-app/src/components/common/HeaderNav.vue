<template>
  <div>
    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>SSAFIT</v-toolbar-title>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" fixed temporary>
      <v-list>
        <v-list-item @click="navigateTo('/login')">
          <v-list-item-icon>
            <v-icon>mdi-login</v-icon>
          </v-list-item-icon>
          <v-list-item-title>로그인</v-list-item-title>
        </v-list-item>
        <v-list-item @click="navigateTo('/register')">
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
export default {
  data: () => ({ drawer: null }),
  methods: {
    navigateTo(route) {
      // Perform navigation to the specified route
      // You can use Vue Router or any other navigation method of your choice
      console.log(`Navigating to: ${route}`);
    },
  },
};
</script>

<style></style>
