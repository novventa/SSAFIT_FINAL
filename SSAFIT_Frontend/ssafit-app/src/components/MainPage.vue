<template>
  <v-main>
    <v-container>
      <!-- Main content -->
      <h3>조회수순</h3>
      <video-list></video-list>

      <h3>좋아요순</h3>
      <video-list></video-list>
    </v-container>
  </v-main>
</template>

<script>
import VideoList from "./video/VideoList.vue";

export default {
  components: {
    VideoList,
  },
  data() {},
};
</script>

<style>
/* Your custom styles */
</style>
