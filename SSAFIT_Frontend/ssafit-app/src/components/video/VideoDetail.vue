<template>
  <v-container class="video-detail">
    <v-card v-if="video" class="video-card">
      <h2>{{ video.title }}</h2>
      <v-responsive class="video-responsive">
        <iframe
          :src="videoURL"
          width="1120"
          height="630"
          title="YouTube video player"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          allowfullscreen
        ></iframe>
      </v-responsive>
      <v-row class="video-info">
        <v-col cols="6" class="view-count">
          <span
            >조회수: <span class="count">{{ video.viewCnt }}</span></span
          >
        </v-col>
        <v-col cols="6" class="like-button">
          <v-btn
            :color="isLiked ? 'blue' : 'white'"
            @click="toggleLikeVideo"
            right
          >
            <v-icon left>{{
              isLiked ? "mdi-thumb-up" : "mdi-thumb-up-outline"
            }}</v-icon>
            {{ isLiked ? "좋아요" : "좋아요" }} {{ video.likeCnt }}
          </v-btn>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "VideoDetail",
  computed: {
    ...mapState(["video", "user", "likeList"]),
    videoURL() {
      const videoId = this.video.videoId;
      return `https://www.youtube.com/embed/${videoId}`;
    },
    isLiked() {
      return Array.from(this.likeList).some((obj) => obj.videoIdx === this.video.idx);
    },
  },
  methods: {
    toggleLikeVideo() {
      const likes = {
        userIdx: this.user.idx,
        videoIdx: this.video.idx,
        videoId: this.video.videoId,
      };
      if (this.isLiked) {
        this.$store.dispatch("removeLike", likes);
        this.video.likeCnt--;
      } else {
        this.$store.dispatch("addLike", likes);
        this.video.likeCnt++;
      }
    },
  },
};
</script>

<style scoped>
.video-detail {
  margin: 20px;
}

.video-card {
  padding: 16px;
}

.video-responsive {
  margin-bottom: 16px;
}

.video-info {
  margin-top: 16px;
}

.view-count {
  font-size: 20px;
  display: flex;
  align-items: center;
}

.count {
  margin-left: 4px;
}

.like-button {
  text-align: right;
}

span {
  margin-right: 8px;
}

.v-btn {
  margin-left: 8px;
}

.v-btn .v-icon {
  margin-right: 4px;
}
</style>
