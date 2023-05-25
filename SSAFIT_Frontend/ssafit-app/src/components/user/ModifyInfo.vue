<template>
  <div>
    <v-card class="card">
      <v-card-title class="card-title">
        <h4>회원 정보 수정</h4>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-row>
            <v-col cols="12" sm="6">
              <v-text-field v-model="id" label="아이디" readonly></v-text-field>
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="password"
                label="비밀번호"
                type="password"
              ></v-text-field>
              <p class="error-message" v-if="password !== '' && passwordError">
                비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자를 포함하여
                입력해주세요.
              </p>
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="nickname"
                label="별명"
                :readonly="nicknameCheck"
                @keyup.enter="checkNickname"
              ></v-text-field>
              <v-btn
                v-if="!nicknameCheck"
                @click="checkNickname"
                color="orange"
                class="mt-2"
              >
                중복 확인
              </v-btn>
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="email"
                label="이메일"
                type="email"
                :readonly="emailCheck"
                @input="validateEmail"
                @keyup.enter="checkEmail"
              ></v-text-field>
              <div v-if="emailError" class="error-message">
                이메일 형식이 올바르지 않습니다.
              </div>
              <v-btn
                v-if="!emailCheck"
                @click="checkEmail"
                color="orange"
                class="mt-2"
              >
                중복 확인
              </v-btn>
            </v-col>
            <v-col cols="12">
              <v-file-input
                label="프로필 사진"
                @change="inputImage"
              ></v-file-input>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn @click="modify" color="orange">수정하기</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  name: "ModifyInfo",
  data() {
    return {
      idx: "",
      id: "",
      password: "",
      nickname: "",
      email: "",
      image: "",
      emailCheck: false,
      nicknameCheck: false,
      emailError: false,
      passwordError: false,
    };
  },
  computed: {
    ...mapState(["user"]),
  },
  created() {
    this.idx = this.user.idx;
    this.id = this.user.id;
    this.password = this.user.password;
    this.nickname = this.user.nickname;
    this.email = this.user.email;
  },
  methods: {
    modify() {
      if (this.password === "" || this.email === "" || this.nickname === "") {
        alert("모두 입력해주세요.");
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
      const API_URL = `${REST_API}/api-user/modify`;
      let user = {
        idx: this.idx,
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
        file.append("file", this.image);
      } else {
        file.append("file", null);
      }
      axios({
        url: API_URL,
        method: "PUT",
        params: file,
        headers: {
          "Content-Type": "multipart/form-data",
          token: sessionStorage.getItem("access-token"),
        },
        transformRequest: [
          function () {
            return file;
          },
        ],
      })
        .then((res) => {
          console.log(res.data);
          this.$store.commit("LOGIN", res.data);
          this.$store.commit("LOGIN", res.data);
          this.$router.push("/");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    inputImage(file) {
      this.image = file;
    },
    checkNickname() {
      if (this.nickname === "") {
        alert("별명을 입력해주세요.");
        return;
      }
      const REST_API = `http://localhost:9999`;
      const API_URL = `${REST_API}/api-user/check/nickname/${this.nickname}${this.idx}`;
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
    checkEmail() {
      const emailPattern = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/;
      const REST_API = `http://localhost:9999`;
      const API_URL = `${REST_API}/api-user/check/email/${this.email}${this.idx}`;
      if (this.email === "") {
        alert("이메일을 입력해주세요.");
        return;
      }
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

<style scoped>
.page-title {
  font-size: 24px;
  margin-bottom: 20px;
  color: orange;
}

.card-title {
  font-size: 20px;
  font-weight: bold;
}

.card {
  background-color: white;
  border-radius: 8px;
  padding: 16px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.v-btn {
  margin-left: 10px;
}

.v-btn.orange {
  background-color: orange;
  color: white;
}

.v-text-field input {
  border-bottom-color: orange;
}

.v-file-input input[type="file"] {
  color: orange;
}
.error-message {
  color: red;
}
</style>
