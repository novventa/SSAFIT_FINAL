<template>
  <div class="container">
    <video-detail class="component-margin"></video-detail>
    <review-list class="component-margin"></review-list>
    <review-write class="component-margin"></review-write>
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

<style scoped>
.component-margin {
  margin-bottom: 20px;
}
</style>
