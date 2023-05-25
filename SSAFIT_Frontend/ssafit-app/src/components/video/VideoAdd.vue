<template>
    <div>
        <input type="text" v-model="searchQuery" placeholder="검색어를 입력하세요">
        <button @click="searchVideo">검색</button><br>
        <div v-if="searchResult">
            <img :src="searchResult.snippet.thumbnails.high.url" alt="Video Thumbnail">
            <h3>{{ searchResult.snippet.title }}</h3><br>
            <v-select @change="fetchPart" label="부위" :items="items"></v-select>
            <button @click="addVideo">등록</button>
        </div><br>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data() {
        return {
            searchQuery: '',
            searchResult: '',
            part: '',
            items: [
                '전신',
                '상체',
                '하체',
                '코어',
            ],
        };
    },
    methods: {
        searchVideo() {
            const URL = "https://www.googleapis.com/youtube/v3/search";
            const API_KEY = process.env.VUE_APP_MY_API_KEY;
            axios({
                url: URL,
                method: "GET",
                params: {
                    key: API_KEY,
                    part: "snippet",
                    q: this.searchQuery,
                    type: "video",
                    maxResults: 1,
                },
            })
                .then((res) => {
                    this.searchResult = res.data.items[0];
                })
                .catch((err) =>
                    console.log(err));
        },
        fetchPart(event) {
          this.part = event;  
        },
        addVideo() {
            const URL = "http://localhost:9999/api-video/add"
            let video = {
                videoId: this.searchResult.id.videoId,
                part: this.part,
                title: this.searchResult.snippet.title,
                channelName: this.searchResult.snippet.channelTitle,
            }
            axios({
                url: URL,
                method: "POST",
                params: video,
                headers: {
                    "token": sessionStorage.getItem("access-token"),
                },
            })
                .then(() => {
                    alert("영상이 등록되었습니다.");
                    this.$router.push("/main");
                })
                .catch((err) =>
                    console.log(err));
        },
    },
}
</script>

<style></style>