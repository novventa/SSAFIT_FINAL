<template>
  <v-app>
    <v-main>
      <v-container fluid>
        <v-row justify="center">
          <v-col cols="12" sm="8" md="6">
            <v-card class="login-card">
              <v-card-title>
                <h3 class="text-center">로그인</h3>
              </v-card-title>
              <v-card-text>
                <v-form @submit.prevent="login">
                  <v-fieldset legend="로그인">
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="id"
                          label="아이디"
                          required
                          @keyup.enter="login"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="password"
                          label="비밀번호"
                          type="password"
                          required
                          @keyup.enter="login"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-btn color="orange" @click="login">로그인</v-btn>
                      </v-col>
                      <v-col cols="12">
                        <v-btn text small color="orange" @click="forgotPassword"
                          >아이디 또는 비밀번호를 잊어버리셨나요?</v-btn
                        >
                      </v-col>
                    </v-row>
                  </v-fieldset>
                </v-form>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>

    <v-dialog v-model="forgotPasswordDialog" max-width="500px">
      <v-card>
        <v-card-title>계정 복구</v-card-title>
        <v-card-text>
          <v-btn text small color="orange" @click="findID">아이디 찾기</v-btn>
          <v-btn text small color="orange" @click="resetPassword"
            >비밀번호 재설정</v-btn
          >
        </v-card-text>
        <v-card-actions>
          <v-btn text color="orange" @click="forgotPasswordDialog = false"
            >닫기</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-app>
</template>

<script>
import router from "@/router";
import axios from "axios";

export default {
  name: "LoginUser",
  data() {
    return {
      id: "",
      password: "",
      forgotPasswordDialog: false,
    };
  },
  created() {
    this.loadingVideo;
  },
  methods: {
    login() {
      if (this.id === "" || this.password === "") {
        alert("아이디와 비밀번호를 모두 입력해주세요.");
        return;
      }
      let user = {
        id: this.id,
        password: this.password,
      };
      const API_URL = `http://localhost:9999/api-user/login`;
      axios({
        url: API_URL,
        method: "POST",
        params: user,
      })
        .then((res) => {
          sessionStorage.setItem("access-token", res.data["access-token"]);
          this.$store.dispatch("login", res.data["user"]);
        })
        .catch((err) => {
          alert(err.response.data.msg);
        });
    },
    forgotPassword() {
      this.forgotPasswordDialog = true;
    },
    findID() {
      router.push("/user/findId");
    },
    resetPassword() {
      router.push("/user/resetPassword");
    },
    loadingVideo() {
      let keyword = {
        key: "none",
        word: "",
        orderBy: "viewCnt",
        orderByDir: "DESC",
      };
      console.log("Aasdasd");
      this.$store.dispatch("searchVideo", keyword);
    },
  },
};
</script>

<style>
.login-card {
  background-color: orange;
  color: white;
}
</style>
