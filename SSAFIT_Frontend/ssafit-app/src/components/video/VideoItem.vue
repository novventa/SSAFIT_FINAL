<template>
  <li @click="clickVideo">
    <img @click="clickVideo" :src="getThumbnailUrl(video.videoId)" />
    <h4>{{ video.title }}</h4>
  </li>
</template>

<script>
import router from "@/router";

export default {
  name: "VideoItem",
  props: {
    video: {
      type: Object,
      required: true,
    },
  },
  methods: {
    clickVideo() {
      this.$store.dispatch("clickVideo", this.video);
      router.push({ name: "details", params: { idx: this.video.idx } });
    },
    getThumbnailUrl(videoId) {
      return `https://img.youtube.com/vi/${videoId}/0.jpg`;
    },
  },
};
</script>

<style></style>
