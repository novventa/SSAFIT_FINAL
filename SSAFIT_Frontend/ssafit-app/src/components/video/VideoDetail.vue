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
      <button @click="toggleLikeVideo" :class="{ active: isLiked }">
        좋아요
      </button>
      <span>{{ video.likeCnt }}</span>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  name: "VideoDetail",
  data() {
    return {
      userIdx: "",
      videoIdx: "",
      isLiked: "", // 좋아요 버튼 상태 변수 추가
    };
  },
  created() {
    this.userLiked;
    console.log(this.isLiked);
  },
  computed: {
    ...mapState(["video", "user"]),
    videoURL() {
      const videoId = this.video.videoId;
      return `https://www.youtube.com/embed/${videoId}`;
    },
  },
  methods: {
    toggleLikeVideo() {
      let likes = {
        userIdx: this.user.idx,
        videoIdx: this.video.idx,
        videoId: this.video.videoId,
      };
      this.isLiked = !this.isLiked; // 버튼 상태 변경
      if (this.isLiked) {
        // this.video.likeCnt--;
        console.log(likes);
      }
      // else {
      //   this.video.likeCnt++;
      // }
      if (this.isLiked) {
        this.$store.dispatch("removeLike", likes);
      } else {
        this.$store.dispatch("addLike", likes);
      }
    },
    userLiked() {
      const REST_API = `http://localhost:9999`;
      const URL = `${REST_API}/api-like/details`;
      let likes = {
        userIdx: this.userIdx,
        videoIdx: this.video.idx,
      };
      axios({
        url: URL,
        method: "GET",
        params: likes,
      })
        .then((res) => {
          // console.log(res);
          if (!res.data) {
            this.isLiked = false;
          } else {
            this.isLiked = true;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
.active {
  background-color: lightblue; /* 토글 상태일 때 버튼 스타일 변경 */
}
</style>
