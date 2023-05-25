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
    users: [],
    videos: [],
    video: {},
    reviews: [],
    results: [],
    likeList: [],
    like: '',
    followList: [],
  },
  getters: {
  },
  mutations: {
    LOGIN(state, payload) {
      state.user = payload;
      router.push({name: "main"});
    },
    GET_USERS(state, list) {
      state.users = list;
    },
    SEARCH_VIDEO(state, videos) {
      state.videos = videos;
    },
    CLICK_VIDEO(state, video) {
      state.video = video;
      router.push({ name: "details", params: { idx: video.idx }});
    },
    CLEAR_USER(state) {
      state.user = {};
    },
    CLEAR_VIDEO(state) {
      state.videos = [];
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
    login({ commit, dispatch }, user) {
      let keyword = {
        key: "none",
        word: "",
        orderBy: "viewCnt",
        orderByDir: "DESC",
      };
      dispatch("searchVideo", keyword);
      commit("LOGIN", user);
      commit("LOGIN", user);
    },
    getUsers({ commit }) {
      const URL = `${REST_API}/api-user/list`;
      axios({
        url: URL,
        method: "GET",
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
      })
      .then((res) => {
        commit("GET_USERS", res.data);
      })
        .catch((err) => {
          console.log(err);
      });
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
          alert(err.response.data.msg);
        })
    },
    searchParts({ commit }, parts) {
      const URL = `${REST_API}/api-video/list-part`;
      axios({
        url: URL,
        method: "GET",
        params: {
          parts: parts.join(","),
        },
        headers: {
          token: sessionStorage.getItem("access-token"),
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
    searchVideo({ commit }, payload) {
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
    clearVideo({ commit }) {
      commit("CLEAR_VIDEO");
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
     addFollows({ commit, dispatch }, follow) {
      const URL = `${REST_API}/api-follow/add`;
      console.log(follow);
      commit;
      axios({
        url: URL,
        method: "POST",
        params: follow,
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
      })
        .then(() => {
          dispatch("getFollowList");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    removeFollow({ commit, dispatch }, idx) {
      const URL = `${REST_API}/api-follow/remove/${idx}`;
      commit;
      axios({
        url: URL,
        method: "DELETE",
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
      })
        .then(() => {
          dispatch("getFollowList");
          console.log("here");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getFollowList(context) {
      const API_URL = `http://localhost:9999/api-follow/list/${context.state.user.idx}`;
      console.log(context.state.user.idx);
      axios({
        url: API_URL,
        method: "GET",
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
      }).then((res) => {
        context.state.followList = res.data;
      });
    },
  },
  modules: {
  },
  plugins: [
    createPersistedState(),
  ]
})
