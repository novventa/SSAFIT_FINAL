<template>
  <v-main>
    <v-container>
      <div class="header">
        <h2>지금 뜨는 영상</h2>
        <hr />
      </div>
      <v-btn
        class="toggle-btn"
        color="orange"
        @click="[toggleSortBy(), orderBy()]"
      >
        {{ sortBy === "views" ? "조회수 순" : "좋아요 순" }}
      </v-btn>
      <v-row>
        <v-col cols="12">
          <video-list :sortBy="sortBy"></video-list>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import VideoList from "./video/VideoList.vue";

export default {
  components: {
    VideoList,
  },
  data() {
    return {
      sortBy: "views", // Default sort option
    };
  },
  computed: {
    checkVideos() {
      return this.videos;
    },
  },
  methods: {
    toggleSortBy() {
      this.sortBy = this.sortBy === "views" ? "likes" : "views";
    },
    orderBy() {
      if (this.sortBy === "views") {
        let keyword = {
          key: "none",
          word: this.keyword,
          orderBy: "viewCnt",
          orderByDir: "DESC",
        };
        this.$store.dispatch("searchVideo", keyword);
      }
      if (this.sortBy === "likes") {
        let keyword = {
          key: "none",
          word: this.keyword,
          orderBy: "likeCnt",
          orderByDir: "DESC",
        };
        this.$store.dispatch("searchVideo", keyword);
      }
    },
  },
  watch: {
    checkVideos(value) {
      this.videos = value;
    },
  },
};
</script>

<style scoped>
.header {
  text-align: left;
  margin-bottom: 20px;
}

.header h2 {
  font-size: 24px;
  color: black;
}

.toggle-btn {
  color: white;
  padding: 10px 20px;
  margin-bottom: 20px;
  border-radius: 4px;
  font-weight: bold;
  transition: background-color 0.3s;
}

.toggle-btn:hover {
  background-color: #1565c0;
}

.v-row {
  justify-content: center;
}

.video-list {
  margin-top: 20px;
}
</style>
