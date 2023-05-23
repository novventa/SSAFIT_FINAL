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
  },
  getters: {
  },
  mutations: {
    LOGIN(state, payload) {
      state.user = payload;
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
  },
  modules: {
  },
  plugins: [
    createPersistedState(),
  ]
})
