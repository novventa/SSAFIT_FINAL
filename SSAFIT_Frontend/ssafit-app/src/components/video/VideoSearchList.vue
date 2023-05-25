<template>
  <div>
    <h3>검색 결과</h3>
    <v-card class="video-list">
      <v-row v-if="videos.length > 0">
        <video-item
          v-for="video in videos"
          :key="video.videoId"
          :video="video"
          class="video-item"
        ></video-item>
      </v-row>
      <v-row v-else>
        <v-container fluid class="no-container">
          <p class="no-results">검색 결과가 없습니다.</p>
        </v-container>
      </v-row>
    </v-card>
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
  margin-top: 20px;
}

.video-item {
  cursor: pointer;
  margin-bottom: 20px;
}
.no-container {
  margin: 100px;
}
.no-results {
  margin: 20px;
  text-align: center;
  color: gray;
}
</style>
