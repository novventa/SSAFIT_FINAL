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
    <div>
      <span>조회수 : {{ video.viewCnt }}</span>
      <button v-if="!isLiked" @click="toggleLikeVideo(1)">
        좋아요
      </button>
      <button v-else @click="toggleLikeVideo(0)">
        좋아요 취소
      </button>
      <span>좋아요 수 : {{ video.likeCnt }}</span>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "VideoDetail",
  data() {
    return {
      userIdx: "",
      videoIdx: "",// 좋아요 버튼 상태 변수 추가
      isLiked: false,
    };
  },
  computed: {
    ...mapState(["video", "user", "likeList"]),
    videoURL() {
      const videoId = this.video.videoId;
      return `https://www.youtube.com/embed/${videoId}`;
    },
  },

 created() {
   this.isLiked = Array.from(this.$store.state.likeList).some((obj) => obj.videoIdx === this.$store.state.video.idx);
  },
  methods: {
  toggleLikeVideo(val) {
      let likes = {
        userIdx: this.user.idx,
        videoIdx: this.video.idx,
        videoId: this.video.videoId,
      };
      if (val === 0) {
        this.$store.dispatch("removeLike", likes);
        this.video.likeCnt--;
      } else {
        this.$store.dispatch("addLike", likes);
        this.video.likeCnt++;
      }
      this.isLiked = !this.isLiked;
    },
  },
};
</script>

<style>
.active {
  background-color: lightblue; /* 토글 상태일 때 버튼 스타일 변경 */
}
</style>
