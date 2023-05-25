<template>
  <div class="container">
    <h2>영상 등록하기</h2>
    <h5 style="color: red">
      운동과 관련 없는 영상을 등록할 시 관리자에 의해 삭제될 수 있습니다.
    </h5>
    <div class="search-container">
      <v-text-field
        v-model="searchQuery"
        label="Youtube 영상의 주소를 입력해주세요"
        outlined
        class="search-input"
        @keyup.enter="searchVideo"
      ></v-text-field>
      <v-btn class="search-button" color="orange" @click="searchVideo"
        >검색</v-btn
      >
    </div>

    <br />
    <v-card v-if="searchResult" class="search-result-card">
      <img
        :src="searchResult.snippet.thumbnails.high.url"
        alt="Video Thumbnail"
      />
      <h3>{{ searchResult.snippet.title }}</h3>
      <br />
      <v-select
        class="part-select"
        @change="fetchPart"
        label="부위"
        :items="items"
      ></v-select>
      <v-btn class="add-video-button" color="orange" @click="addVideo"
        >등록</v-btn
      >
    </v-card>
    <v-card v-else class="search-result-card">
      <v-row>
        <v-container fluid class="no-container">
          <p class="no-results">검색 결과가 없습니다.</p>
        </v-container>
      </v-row>
    </v-card>
    <br />
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      searchQuery: "",
      searchResult: "",
      part: "",
      items: ["전신", "상체", "하체", "코어"],
    };
  },
  methods: {
    searchVideo() {
      const URL = "https://www.googleapis.com/youtube/v3/search";
      const KEY = process.env.VUE_APP_API_KEY;
      if(this.searchQuery === '') {
        alert("영상의 주소를 입력해주세요.");
        return;
      }
      axios({
        url: URL,
        method: "GET",
        params: {
          key: KEY,
          part: "snippet",
          q: this.searchQuery.split("=")[1],
          type: "video",
          maxResults: 1,
        },
      })
        .then((res) => {
          console.log(res);
          this.searchResult = res.data.items[0];
        })
        .catch((err) => console.log(err));
    },
    fetchPart(event) {
      this.part = event;
    },
    addVideo() {
      const URL = "http://localhost:9999/api-video/add";
      let video = {
        videoId: this.searchResult.id.videoId,
        part: this.part,
        title: encodeURIComponent(this.searchResult.snippet.title),
        channelName: encodeURIComponent(this.searchResult.snippet.channelTitle),
      };
      console.log(video);
      axios({
        url: URL,
        method: "POST",
        params: video,
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
      })
        .then(() => {
          alert("영상이 등록되었습니다.");
          this.$router.push("/main");
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>

<style scoped>
.search-container {
  display: flex;
  align-items: center;
  margin-top: 10px;
}

.search-input {
  margin-right: 10px;
}

.search-button {
  margin-bottom: 36px;
}

.search-result-card {
  margin-bottom: 20px;
  padding: 20px;
  text-align: center;
  margin-left: auto;
  margin-right: auto;
}

.part-select {
  margin-bottom: 10px;
}

.add-video-button {
  margin-left: 10px;
}

.no-container {
  margin: 100px;
}

.no-results {
  margin: 20px;
  text-align: center;
  color: gray;
}
.warning {
  margin-bottom: 10px;
}
</style>
