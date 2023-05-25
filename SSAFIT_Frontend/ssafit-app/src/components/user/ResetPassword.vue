<template>
  <v-app>
    <v-main>
      <v-container class="my-5">
        <v-row justify="center">
          <v-col cols="12" sm="8" md="6">
            <h3 class="text-center">비밀번호 재설정</h3>
            <v-form>
              <v-fieldset>
                <v-row>
                  <v-col cols="12">
                    <v-text-field v-model="id" label="아이디" @keyup.enter="confirm"></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field v-model="email" label="이메일" type="email" @keyup.enter="confirm"></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field v-model="nickname" label="별명" @keyup.enter="confirm"></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-btn color="orange" @click="confirm">계정 확인</v-btn>
                  </v-col>
                </v-row>
              </v-fieldset>
            </v-form>
          </v-col>
        </v-row>
      </v-container>

      <v-dialog v-model="dialog" max-width="500px">
        <v-card>
          <v-card-title class="orange--text">
            <span class="headline">비밀번호 재설정</span>
          </v-card-title>
          <v-card-text>
            <v-text-field v-model="password" label="비밀번호" type="password" @keyup.enter="resetPassword" required
                          @input="validatePassword"></v-text-field>
            <p class="error-message" v-if="password !== '' && passwordError">
              비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자를
              포함하여 입력해주세요.
            </p>
          </v-card-text>
          <v-card-actions>
            <v-btn color="orange" @click="resetPassword">재설정하기</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-main>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: "ResetPassword",
  data() {
    return {
      idx: "",
      id: "",
      email: "",
      nickname: "",
      password: "",
      passwordError: false,
      dialog: false,
    };
  },
  methods: {
    confirm() {
      if (this.id === '' || this.email === '' || this.nickname === '') {
        alert("아이디와 이메일, 별명 모두 입력해주세요.");
        return;
      }
      let user = {
        id: this.id,
        email: this.email,
        nickname: this.nickname,
      };
      const API_URL = `http://localhost:9999/api-user/confirm`;
      axios({
        url: API_URL,
        method: "GET",
        params: user,
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          this.idx = res.data.idx;
          alert("본인 인증되었습니다.");
          this.dialog = true;
        })
        .catch((err) => {
          alert(err.response.data.msg);
        });
    },
    resetPassword() {
      if(this.passwordError) {
        alert("비밀번호 형식이 맞지 않습니다.");
        return;
      }
      let user = {
        idx: this.idx,
        password: this.password,
      };
      const API_URL = `http://localhost:9999/api-user/modifyPassword`;
      axios({
        url: API_URL,
        method: "PUT",
        params: user,
        headers: {
          token: sessionStorage.getItem("access-token"),
        },
      })
        .then(() => {
          alert("비밀번호가 재설정되었습니다.");
          this.dialog = false;
          this.$router.push("login");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    validatePassword() {
      const passwordPattern =
        /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
      this.passwordError = !passwordPattern.test(this.password);
    },
  },
};
</script>

<style>
.v-main {
  padding: 20px;
}
.error-message {
  color: red;
}
</style>
