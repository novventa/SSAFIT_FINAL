<template>
  <div>
    <video-list></video-list>
    <video-search></video-search>
  </div>
</template>

<script>
import VideoList from "../components/video/VideoList.vue";
import VideoSearch from "../components/video/VideoSearch.vue";
export default {
  components: { VideoList, VideoSearch },
};
</script>

<style></style>
