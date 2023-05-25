<template>
  <v-footer dark padless>
    <v-card flat tile class="orange lighten-1 white--text text-center">
      <v-card-text>
        <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
          <v-icon size="24px">
            {{ icon }}
          </v-icon>
        </v-btn>
      </v-card-text>

      <v-card-text class="white--text pt-0">
        이 웹사이트에서 보여지는 모든 영상들은 영상 제작자 본인들에게 저작권이
        있으며, 영상에 대한 권한은 모두 영상 제작자에게 속합니다. 영상에 관련된
        모든 저작권과 법적 권리는 영상 제작자에게 속하며, 명시적인 허가 없이는
        어떠한 형태로든 활용, 이용할 수 없습니다.이 글을 통해 영상 제작자의
        권리를 존중하며, 관리자는 저작권과 법적 규정을 준수함을 알립니다. 만약
        영상에 대한 문의나 권리에 관련된 사항이 있을 경우, 영상 제작자에게 직접
        문의하시기 바랍니다.
      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="white--text">
        {{ new Date().getFullYear() }} —
        <strong>&copy;SSAFIT All rights reserved</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  name: "FooterBar",
  data: () => ({
    icons: ["mdi-facebook", "mdi-twitter", "mdi-instagram"],
  }),
};
</script>

<style></style>
