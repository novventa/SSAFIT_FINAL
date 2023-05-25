<template>
  <v-app>
    <v-main>
      <v-container class="my-5">
        <v-card elevation="3">
          <!-- Added v-card component -->
          <v-card-title>
            <h3 class="text-center">아이디 찾기</h3>
          </v-card-title>
          <v-card-text>
            <v-form>
              <v-fieldset legend="아이디 찾기">
                <v-row>
                  <v-col cols="12">
                    <v-text-field v-model="email" label="이메일" @keyup.enter="findId"></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                      v-model="nickname"
                      label="별명" @keyup.enter="findId"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-btn color="orange" @click="findId">아이디 찾기</v-btn>
                  </v-col>
                </v-row>
              </v-fieldset>
            </v-form>
          </v-card-text>
        </v-card>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "FindId",
  data() {
    return {
      email: "",
      nickname: "",
    };
  },
  methods: {
    findId() {
      if(this.email === '' ||
      this.nickname === '') {
        alert("이메일과 별명을 모두 입력해주세요.");
        return;
      }
      let info = {
        email: this.email,
        nickname: this.nickname,
      };
      this.$store.dispatch("findId", info);
    },
  },
};
</script>

<style scoped>
.v-main {
  padding: 20px;
}

.v-card {
  max-width: 400px;
  margin: 0 auto; /* Center align the card horizontally */
}
</style>
