<template>
  <v-main>
    <section>
      <v-parallax src="@/assets/img/Main.jpg" height="600">
        <v-layout column align-center justify-center class="white--text">
          <h1
            class="white--text mb-2 display-1 text-xs-center"
            style="font-weight: 900; text-shadow: 3px 2px #000000"
          >
            모두의 운동 사이트 SSAFIT
          </h1>
          <div
            class="white--text subheading mb-3 text-xs-center"
            style="font-weight: 900; text-shadow: 2px 2px #000000"
          >
            당신이 필요한 모든 운동 정보
          </div>
          <v-btn class="blue lighten-2 mt-5" dark large href="/pre-made-themes">
            서비스 사용하기
          </v-btn>
        </v-layout>
      </v-parallax>
    </section>

    <section>
      <v-layout column wrap class="my-5" align-center>
        <v-flex xs12 sm4 class="my-3">
          <div class="text-xs-center">
            <h2 class="headline">설명1</h2>
            <span class="subheading"> 내용1 </span>
          </div>
        </v-flex>
        <v-flex xs12>
          <v-container grid-list-xl>
            <v-layout row wrap align-center>
              <v-flex xs12 md4>
                <v-card class="elevation-0 transparent">
                  <v-card-text class="text-xs-center">
                    <!-- <v-icon x-large class="blue--text text--lighten-2"
                      >public</v-icon
                    > -->
                  </v-card-text>
                  <v-card-title primary-title class="layout justify-center">
                    <div class="headline text-xs-center">운동 영상 찾기</div>
                  </v-card-title>
                  <v-card-text> 서비스 설명1 </v-card-text>
                </v-card>
              </v-flex>
              <v-flex xs12 md4>
                <v-card class="elevation-0 transparent">
                  <v-card-text class="text-xs-center">
                    <!-- <v-icon x-large class="blue--text text--lighten-2"
                      >flash_on</v-icon
                    > -->
                  </v-card-text>
                  <v-card-title primary-title class="layout justify-center">
                    <div class="headline">운동 메이트 찾기</div>
                  </v-card-title>
                  <v-card-text> 서비스 설명2 </v-card-text>
                </v-card>
              </v-flex>
              <v-flex xs12 md4>
                <v-card class="elevation-0 transparent">
                  <v-card-text class="text-xs-center">
                    <!-- <v-icon x-large class="blue--text text--lighten-2"
                      >share</v-icon
                    > -->
                  </v-card-text>
                  <v-card-title primary-title class="layout justify-center">
                    <div class="headline text-xs-center">운동 루틴 관리</div>
                  </v-card-title>
                  <v-card-text> 서비스 설명3 </v-card-text>
                </v-card>
              </v-flex>
            </v-layout>
          </v-container>
        </v-flex>
      </v-layout>
    </section>
  </v-main>
</template>

<script>
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
};
</script>

<style></style>
