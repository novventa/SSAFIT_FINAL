<template>
  <v-app>
    <v-main>
      <v-container fluid>
        <v-row justify="center">
          <v-col cols="12" sm="8" md="6">
            <v-card class="signup-card">
              <v-card-title>
                <h3 class="text-center">회원 가입</h3>
              </v-card-title>
              <v-card-text>
                <v-card-actions>
                  <p>이미 회원이신가요? <a href="/user/login">로그인하기</a></p>
                </v-card-actions>
                <v-form @submit.prevent="createUser">
                  <v-fieldset legend="가입">
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="id"
                          :readonly="idCheck"
                          label="아이디"
                          required
                          @input="validateId"
                          @keyup.enter="checkId"
                        ></v-text-field>
                        <p class="error-message" v-if="id !== '' && idError">
                          아이디는 6~16자의 영문 대소문자와 숫자로만
                          입력해주세요.
                        </p>
                      </v-col>
                      <v-col cols="12">
                        <v-btn v-if="!idCheck" color="orange" @click="checkId">
                          중복 체크
                        </v-btn>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="password"
                          label="비밀번호"
                          type="password"
                          required
                          @input="validatePassword"
                        ></v-text-field>
                        <p
                          class="error-message"
                          v-if="password !== '' && passwordError"
                        >
                          비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자를
                          포함하여 입력해주세요.
                        </p>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="nickname"
                          :readonly="nicknameCheck"
                          label="별명"
                          required
                          @keyup.enter="checkNickname"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-btn
                          v-if="!nicknameCheck"
                          color="orange"
                          @click="checkNickname"
                        >
                          중복 체크
                        </v-btn>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="email"
                          :readonly="emailCheck"
                          label="이메일"
                          type="email"
                          required
                          @input="validateEmail"
                          @keyup.enter="checkEmail"
                        ></v-text-field>
                        <div v-if="emailError" class="error-message">
                          이메일 형식이 올바르지 않습니다.
                        </div>
                      </v-col>
                      <v-col cols="12">
                        <v-btn
                          v-if="!emailCheck"
                          color="orange"
                          @click="checkEmail"
                        >
                          중복 체크
                        </v-btn>
                      </v-col>
                      <v-col cols="12">
                        <v-file-input
                          v-model="image"
                          label="프로필 사진"
                          @change="inputImage"
                        ></v-file-input>
                      </v-col>
                      <v-col cols="12">
                        <v-btn color="orange" @click="createUser">
                          가입하기
                        </v-btn>
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
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "SignUp",
  data() {
    return {
      id: "",
      password: "",
      nickname: "",
      email: "",
      image: null,
      idCheck: false,
      emailCheck: false,
      nicknameCheck: false,
      emailError: false,
      passwordError: false,
      idError: false,
    };
  },
  methods: {
    createUser() {
      if (
        this.id === "" ||
        this.password === "" ||
        this.email === "" ||
        this.nickname === ""
      ) {
        alert("모두 입력해주세요.");
        return;
      }
      if (!this.idCheck) {
        alert("아이디 중복 체크를 해주세요.");
        return;
      }
      if (!this.emailCheck) {
        alert("이메일 중복 체크를 해주세요.");
        return;
      }
      if (!this.nicknameCheck) {
        alert("닉네임 중복 체크를 해주세요.");
        return;
      }
      const REST_API = `http://localhost:9999`;
      const API_URL = `${REST_API}/api-user/add`;
      let user = {
        idx: 0,
        id: this.id,
        password: this.password,
        nickname: this.nickname,
        email: this.email,
        image: "none",
      };
      let file = new FormData();
      file.append("idx", user.idx);
      file.append("id", user.id);
      file.append("password", user.password);
      file.append("nickname", user.nickname);
      file.append("email", user.email);
      file.append("image", user.image);
      if (this.image != null) {
        file.append("file", this.image[0]);
      } else {
        file.append("file", null);
      }
      console.log(file);
      axios({
        url: API_URL,
        method: "POST",
        params: file,
        headers: {
          "Content-Type": "multipart/form-data",
        },
        transformRequest: [
          function () {
            return file;
          },
        ],
      })
        .then(() => {
          alert("회원가입이 완료되었습니다.");
          this.$router.push("login");
          this.$router.go(0);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    inputImage() {
      this.image = this.$refs.profile.files;
    },
    checkId() {
      const REST_API = `http://localhost:9999`;
      const API_URL = `${REST_API}/api-user/check/id/${this.id}`;
      const idPattern = /^[a-zA-Z0-9]{6,12}$/; // 6~12자의 영문 대소문자와 숫자
      if (!idPattern.test(this.id)) {
        alert("아이디는 6~12자의 영문 대소문자와 숫자로 입력해주세요.");
        return;
      }
      axios({
        url: API_URL,
        method: "GET",
      })
        .then(() => {
          this.idCheck = true;
          alert("사용 가능합니다.");
        })
        .catch((err) => {
          alert(err.response.data.msg);
        });
    },
    validateId() {
      const idPattern = /^[a-zA-Z0-9]{6,12}$/; // 6~12자의 영문 대소문자와 숫자
      this.idError = !idPattern.test(this.id);
    },
    checkEmail() {
      const emailPattern = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/; // 이메일 형식
      const REST_API = `http://localhost:9999`;
      const API_URL = `${REST_API}/api-user/check/email/${this.email}`;
      if (!emailPattern.test(this.email)) {
        alert("이메일 형식이 올바르지 않습니다.");
        return;
      }
      axios({
        url: API_URL,
        method: "GET",
      })
        .then(() => {
          this.emailCheck = true;
          alert("사용 가능합니다.");
        })
        .catch((err) => {
          alert(err.response.data.msg);
        });
    },
    checkNickname() {
      const REST_API = `http://localhost:9999`;
      const API_URL = `${REST_API}/api-user/check/nickname/${this.nickname}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then(() => {
          this.nicknameCheck = true;
          alert("사용 가능합니다.");
        })
        .catch((err) => {
          alert(err.response.data.msg);
        });
    },
    validatePassword() {
      const passwordPattern =
        /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
      this.passwordError = !passwordPattern.test(this.password);
    },
    validateEmail() {
      const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      this.emailError = !emailPattern.test(this.email);
    },
  },
};
</script>

<style>
.signup-card {
  background-color: orange;
  color: white;
}
.error-message {
  color: red;
}
</style>
