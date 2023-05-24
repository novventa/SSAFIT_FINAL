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
    likeList: [],
    like: '',
  },
  getters: {
  },
  mutations: {
    LOGIN(state, payload) {
      state.user = payload;
    },
    SEARCH_VIDEO(state, videos) {
      state.videos = videos;
    },
    CLICK_VIDEO(state, video) {
      state.video = video;
    },
    CLEAR_USER(state) {
      state.user = {};
    },
    GET_REVIEWS(state, reviews) {
      state.reviews = reviews;
    },
    ADD_REVIEW(state, review) {
      state.review = review;
    },
    MODIFY_REVIEW(state, review) {
      state.review = review;
    },
    GET_LIKE_LIST(state, list) {
      state.likeList = list;
    }
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
    clickVideo({ commit}, idx) {
      const URL = `${REST_API}/api-video/details/${idx}`;
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
    clearUser({commit}) {
      commit("CLEAR_USER");
    },
     getReviews({ commit }, videoIdx) {
      const URL = `${REST_API}/api-review/list/${videoIdx}`;
      axios({
        url: URL,
        method: "GET",
        headers: {
          "token": sessionStorage.getItem("access-token"),
      },
      })
        .then((res) => {
          commit("GET_REVIEWS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addReview({ commit, dispatch }, review) {
      const URL = `${REST_API}/api-review/add`;
      axios({
        url: URL,
        method: "POST",
        params: review,
        headers: {
          "token": sessionStorage.getItem("access-token"),
      },
      })
        .then(() => {
          commit;
          dispatch("getReviews", review.videoIdx);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    modifyReview({ commit }, review) {
      const URL = `${REST_API}/api-review/modify`;
      axios({
        url: URL,
        method: "PUT",
        params: review,
        headers: {
          "token": sessionStorage.getItem("access-token"),
      },
      })
        .then((res) => {
          console.log(res);
          commit("MODIFY_REVIEW", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteReview({ commit, dispatch}, review) {
      const URL = `${REST_API}/api-review/remove/${review.idx}`;
      axios({
        url: URL,
        method: "DELETE",
        headers: {
          "token": sessionStorage.getItem("access-token"),
      },
      })
        .then(() => {
          commit;
          dispatch("getReviews", review.videoIdx);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addLike({ commit, dispatch}, likes) {
      const URL = `${REST_API}/api-like/add`;
      axios({
        url: URL,
        method: "POST",
        params: likes,
        headers: {
          "token": sessionStorage.getItem("access-token"),
      },
      })
        .then(() => {
          dispatch("getLikeList");
          commit;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    removeLike({ commit, dispatch}, likes) {
      const URL = `${REST_API}/api-like/remove`;
      axios({
        url: URL,
        method: "DELETE",
        params: likes,
        headers: {
          "token": sessionStorage.getItem("access-token"),
      },
      })
        .then(() => {
          dispatch("getLikeList");
          commit;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // checkLike({commit}, likes) {
    //   const URL = `${REST_API}/api-like/list-video/details`;
    //   axios({
    //     url: URL,
    //     method: "GET",
    //     params: likes,
    //     headers: {
    //       "token": sessionStorage.getItem("access-token"),
    //   },
    //   })
    //     .then((res) => {
    //       if(res.data.idx) {
    //         commit("CHECK_LIKE", true);
    //       }
    //       else {
    //         commit("CHECK_LIKE", false);
    //       }
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // },
    getLikeList(context) {
      const URL = `${REST_API}/api-like/list-user/${context.state.user.idx}`;
      axios({
        url: URL,
        method: "GET",
        headers: {
          "token": sessionStorage.getItem("access-token"),
      },
      })
        .then((res) => {
          console.log("here");
          console.log(res.data);
          context.commit("GET_LIKE_LIST", res.data);
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
