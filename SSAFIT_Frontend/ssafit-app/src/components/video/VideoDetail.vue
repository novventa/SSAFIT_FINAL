<template>
  <div v-if="video">
    <h2>영상 보기</h2>
    <h2>{{ video.title }}</h2>
    <iframe
      width="1120"
      height="630"
      :src="videoURL"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      allowfullscreen
    ></iframe>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "VideoDetail",
  computed: {
    ...mapState(["video"]),
    videoURL() {
      const videoId = this.video.videoId;
      console.log(this.video);
      return `https://www.youtube.com/embed/${videoId}`;
    },
  },
};
</script>

<style></style>
