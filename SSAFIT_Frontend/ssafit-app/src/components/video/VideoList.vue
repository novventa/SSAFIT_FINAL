<template>
  <div>
    <ul class="video-list">
      <video-item
        v-for="video in videos"
        :key="video.videoId"
        :video="video"
        class="each-list"
      >
      </video-item>
    </ul>
  </div>
</template>

<script>
import { mapState } from "vuex";
import VideoItem from "./VideoItem.vue";

export default {
  components: {
    VideoItem,
  },
  name: "VideoSearchList",
  computed: {
    ...mapState(["videos"]),
  },
};
</script>

<style scoped>
.video-list {
  text-align: left;
}
.each-list {
  cursor: pointer;
  margin: 40px;
}
</style>
