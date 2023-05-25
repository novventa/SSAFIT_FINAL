<template>
  <v-col cols="12" md="4">
    <v-card @click="clickVideo" class="video-item">
      <v-img
        :src="getThumbnailUrl(video.videoId)"
        width="561"
        height="315"
        class="video-image"
      ></v-img>
      <div class="title-overlay">
        <span class="video-title">{{ video.title }}</span>
      </div>
      <v-card-title>{{ video.part }}</v-card-title>
    </v-card>
  </v-col>
</template>

<script>
// import router from "@/router";

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
      this.$store.dispatch("clickVideo", this.video.idx);
      // .then(() => {
      //   router.push({ name: "details", params: { idx: this.video.idx } });
      // });
    },
    getThumbnailUrl(videoId) {
      return `https://img.youtube.com/vi/${videoId}/0.jpg`;
    },
  },
};
</script>

<style scoped>
.video-item {
  position: relative;
}

.video-image {
  transition: transform 0.3s;
}

.video-image:hover {
  transform: scale(1.05);
}

.title-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0;
  transition: opacity 0.3s;
}

.video-item:hover .title-overlay {
  opacity: 1;
}

.video-title {
  color: white;
  font-size: 18px;
}

.v-card {
  height: 100%;
}

.v-card-title {
  padding: 16px;
  text-align: center;
}
</style>
