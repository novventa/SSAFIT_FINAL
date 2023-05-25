<template>
  <div class="container">
    <video-search></video-search>
    <video-search-list></video-search-list>
  </div>
</template>

<script>
import VideoSearchList from "../components/video/VideoSearchList.vue";
import VideoSearch from "../components/video/VideoSearch.vue";
export default {
  components: { VideoSearchList, VideoSearch },
};
</script>

<style>
.container {
  margin-bottom: 25px;
}
</style>
