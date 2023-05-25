<template>
  <div>
    <section>
      <v-parallax src="@/assets/img/Main.jpg" height="600">
        <v-row align="center" justify="center" class="white--text">
          <v-col cols="12">
            <h1
              class="white--text mb-2 display-1 text-center font-weight-bold"
              style="text-shadow: 3px 2px #000000"
            >
              모두의 운동 사이트 SSAFIT
            </h1>
            <div
              class="white--text subheading mb-3 text-center font-weight-bold"
              style="text-shadow: 2px 2px #000000"
            >
              당신이 필요한 모든 운동 정보
            </div>
          </v-col>
          <v-btn class="orange darken-1 mt-5" dark large @click="register">
            서비스 사용하기
          </v-btn>
        </v-row>
      </v-parallax>
    </section>

    <section>
      <v-row class="my-5" justify="center">
        <v-col cols="12" sm="4" class="my-3">
          <div class="text-center">
            <h2 class="headline">설명1</h2>
            <span class="subheading">내용1</span>
          </div>
        </v-col>
        <v-col cols="12">
          <v-container>
            <v-row justify="center">
              <v-col cols="12" md="4">
                <v-card class="elevation-0 transparent">
                  <v-card-text class="text-center">
                    <!-- <v-icon x-large class="blue--text text--lighten-2"
                      >public</v-icon
                    > -->
                  </v-card-text>
                  <v-card-title primary-title class="layout justify-center">
                    <div class="headline text-center">운동 영상 찾기</div>
                  </v-card-title>
                  <v-card-text>서비스 설명1</v-card-text>
                </v-card>
              </v-col>
              <v-col cols="12" md="4">
                <v-card class="elevation-0 transparent">
                  <v-card-text class="text-center">
                    <!-- <v-icon x-large class="blue--text text--lighten-2"
                      >flash_on</v-icon
                    > -->
                  </v-card-text>
                  <v-card-title primary-title class="layout justify-center">
                    <div class="headline">운동 메이트 찾기</div>
                  </v-card-title>
                  <v-card-text>서비스 설명2</v-card-text>
                </v-card>
              </v-col>
              <v-col cols="12" md="4">
                <v-card class="elevation-0 transparent">
                  <v-card-text class="text-center">
                    <!-- <v-icon x-large class="blue--text text--lighten-2"
                      >share</v-icon
                    > -->
                  </v-card-text>
                  <v-card-title primary-title class="layout justify-center">
                    <div class="headline text-center">운동 루틴 관리</div>
                  </v-card-title>
                  <v-card-text>서비스 설명3</v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-col>
      </v-row>
    </section>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "App",
  data: function () {
    return {};
  },
  computed: {
    imgHeight: function () {
      var offset = 320;
      return this.pageHeight - offset;
    },
  },
  methods: {
    register() {
      router.push("/user/create");
    },
  },
};
</script>

<style></style>
