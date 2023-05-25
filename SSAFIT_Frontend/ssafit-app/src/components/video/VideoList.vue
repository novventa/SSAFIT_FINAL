<template>
  <v-card class="video-list">
    <v-row>
      <video-item
        v-for="video in videos.slice(0, 12)"
        :key="video.videoId"
        :video="video"
        class="video-item"
      ></video-item>
    </v-row>
  </v-card>
</template>

<script>
import { mapState } from "vuex";
import { mapActions } from "vuex";
import VideoItem from "./VideoItem.vue";

export default {
  components: {
    VideoItem,
  },
  name: "VideoList",
  computed: {
    ...mapState(["videos"]),
    ...mapActions(["getUsers", "getFollowList"]),
  },
  created() {
    this.getUsers;
    this.getFollowList;
  },
};
</script>

<style scoped>
.video-list {
  margin-top: 20px;
}

.video-item {
  cursor: pointer;
  margin-bottom: 20px;
}
</style>
