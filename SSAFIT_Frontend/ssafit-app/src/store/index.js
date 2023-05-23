import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import createPersistedState from 'vuex-persistedstate'
import router from '@/router'

Vue.use(Vuex);

const REST_API = `http://localhost:9999`;

export default new Vuex.Store({
  state: {
    user: {},
    videos: [],
    video: {},
    reviews: [],
  },
  getters: {
  },
  mutations: {
    LOGIN(state, payload) {
      state.user = payload;
    },
    SEARCH_VIDEO(state, videos) {
      state.videos = videos;
      console.log(videos);
    },
    CLICK_VIDEO(state, video) {
      state.video = video;
      console.log(video);
    },
  },
  actions: {
    login({ commit }, user) {
      commit("LOGIN", user);
    },
    findId({ commit }, user) {
      const API_URL = `${REST_API}/api-user/searchId`;
      axios({
        url: API_URL,
        method: "GET",
        params: user,
      })
        .then((res) => {
          alert("회원님의 아이디는 " + res.data + "입니다.");
          router.push("login");
          commit;
        })
        .catch((err) => {
          console.log(err);
        })
    },
    searchVideo({ commit }, payload) {
      console.log(payload);

      const URL = `${REST_API}/api-video/list`;
      axios({
        url: URL,
        method: "GET",
        params: payload,
        headers: {
          "token": sessionStorage.getItem("access-token"),
      },
      })
        .then((res) => {
          console.log(res.data);
          commit("SEARCH_VIDEO", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // payload : 비디오 객체가 들어온다
    clickVideo({ commit }, video) {
      const URL = `${REST_API}/api-video/details/${video.idx}`;
      axios({
        url: URL,
        method: "GET",
        headers: {
          "token": sessionStorage.getItem("access-token"),
      },
      })
        .then((res) => {
          commit("CLICK_VIDEO", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  modules: {
  },
  plugins: [
    createPersistedState(),
  ]
})
