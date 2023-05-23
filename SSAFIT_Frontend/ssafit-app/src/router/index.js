import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import UserView from "../views/UserView.vue";
import LandingView from "../views/LandingView.vue";
import VideoSearchView from "../views/VideoSearchView.vue";
import VideoDetailView from "../views/VideoDetailView.vue";

import SignUp from "../components/user/SignUp.vue"
import LoginUser from "../components/user/LoginUser.vue"
import FindId from "../components/user/FindId.vue"
import ResetPassword from "../components/user/ResetPassword.vue"
import ModifyInfo from "../components/user/ModifyInfo.vue"

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "land",
    component: LandingView,
  },
  {
    path: "/main",
    name: "main",
    component: HomeView,
  },
  {
    path: "/search",
    name: "search",
    component: VideoSearchView,
  },
  {
    path: "/details/:idx",
    name: "details",
    component: VideoDetailView,
  },
  {
    path: "/user",
    component: UserView,
    children: [
      {
        path: "create",
        name: "signUp",
        component: SignUp,
      },
      {
        path: "login",
        name: "loginUser",
        component: LoginUser,
      },
      {
        path: "findId",
        name: "findId",
        component: FindId,
      },
      {
        path: "resetPassword",
        name: "resetPassword",
        component: ResetPassword,
      },
      {
        path: "modifyInfo",
        name: "modifyInfo",
        component: ModifyInfo,
      }
    ]
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
