<template>
  <v-main class="pt-2 pt-sm-2 pt-xs-2 pt-md-0 pt-lg-0 pt-xl-0">
    <div class="header-image"></div>
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
      prevScrollPos: window.pageYOffset,
      headerImageVisible: true,
    };
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
  },
  computed: {
    checkVideos() {
      return this.videos;
    },
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.handleScroll);
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
    handleScroll() {
      const currentScrollPos = window.pageYOffset;
      if (this.prevScrollPos > currentScrollPos) {
        this.headerImageVisible = true;
      } else {
        this.headerImageVisible = false;
      }
      this.prevScrollPos = currentScrollPos;
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
.header-image {
  width: 100%;
  height: 500px;
  background-image: url("@/assets/img/image.jpg");
  background-size: cover;
  background-position: center;
  opacity: 1;
  transition: opacity 0.5s;
}

.header-image.hide {
  opacity: 0;
  visibility: hidden;
}

.header {
  text-align: left;
  margin-bottom: 20px;
  margin-top: 20px;
}

.header h2 {
  font-size: 24px;
  color: black;
}

.description {
  font-size: 16px;
  color: #666;
  margin-bottom: 10px;
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