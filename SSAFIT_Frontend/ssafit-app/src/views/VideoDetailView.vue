<template>
  <div>
    <video-detail></video-detail>
    <review-list></review-list>
    <review-write></review-write>
  </div>
</template>

<script>
import ReviewList from "../components/review/ReviewList.vue";
import ReviewWrite from "../components/review/ReviewWrite.vue";
import VideoDetail from "../components/video/VideoDetail.vue";
export default {
  components: { VideoDetail, ReviewList, ReviewWrite },
};
</script>

<style></style>
