<template>
  <div>
    <section>
      <v-parallax src="@/assets/img/Main.jpg" height="600">
        <v-row align="center" justify="center" class="white--text">
          <v-col cols="12">
            <h1 class="white--text mb-2 display-1 text-center font-weight-bold" style="text-shadow: 3px 2px #000000">
              모두의 운동 사이트 SSAFIT
            </h1>
            <div class="white--text subheading mb-3 text-center font-weight-bold" style="text-shadow: 2px 2px #000000">
              지금 뜨는 모든 운동 영상
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
        <v-col cols="12">
          <v-container>
            <v-row justify="center">
              <v-col cols="12" md="4">
                <v-card class="elevation-0 transparent">
                  <v-card-text class="text-center">
                    <v-icon x-large class="orange--text">mdi-video-box</v-icon>
                  </v-card-text>
                  <v-card-title primary-title class="layout justify-center">
                    <div class="headline text-center">운동 영상 찾기</div>
                  </v-card-title>
                  <v-card-text class="text-center">필요한 운동 영상을 찾아서 다른 유저들과 정보를 공유하세요.</v-card-text>
                </v-card>
              </v-col>
              <v-col cols="12" md="4">
                <v-card class="elevation-0 transparent">
                  <v-card-text class="text-center">
                    <v-icon x-large class="orange--text">mdi-plus-box</v-icon>
                  </v-card-text>
                  <v-card-title primary-title class="layout justify-center">
                    <div class="headline">운동 영상 등록</div>
                  </v-card-title>
                  <v-card-text class="text-center">원하는 운동 영상이 없다면 직접 등록할 수 있습니다.</v-card-text>
                </v-card>
              </v-col>
              <v-col cols="12" md="4">
                <v-card class="elevation-0 transparent">
                  <v-card-text class="text-center">
                    <v-icon x-large class="orange--text">mdi-account-multiple-plus</v-icon>

                  </v-card-text>
                  <v-card-title primary-title class="layout justify-center">
                    <div class="headline text-center">운동 메이트 찾기</div>
                  </v-card-title>
                  <v-card-text class="text-center">다른 유저를 팔로우하여 관심있는 영상을 공유할 수 있습니다.</v-card-text>
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
