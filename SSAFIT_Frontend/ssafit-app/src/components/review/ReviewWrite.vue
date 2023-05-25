<template>
  <v-container class="review-form">
    <h2>리뷰 작성</h2>
    <v-form @submit.prevent="submitReview">
      <v-row>
        <v-col cols="12">
          <v-text-field
            label="작성자"
            :value="user.nickname"
            readonly
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-textarea v-model="content" label="내용" required></v-textarea>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-btn color="orange" type="submit">작성하기</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  data() {
    return {
      content: '',
    };
  },
  computed: {
    ...mapState(["video", "user"]),
  },
  methods: {
    ...mapActions(["addReview"]),
    submitReview() {
      if (!this.content) {
        console.error("리뷰 내용을 입력해주세요.");
        return;
      }

      const review = {
        videoIdx: this.video.idx,
        content: this.content,
        writer: this.user.nickname,
        userIdx: this.user.idx,
      };

      this.addReview(review)
        .then(() => {
          this.content = "";
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style scoped>
.review-form {
  margin: 20px;
}

.v-row:not(:last-child) {
  margin-bottom: 16px;
}

.v-btn {
  color: white;
}
</style>
