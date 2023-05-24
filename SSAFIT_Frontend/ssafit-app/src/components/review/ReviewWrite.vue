<template>
  <div>
    <h2>리뷰 작성</h2>
    <form @submit.prevent="submitReview">
      <div>
        <label for="writer">작성자 : {{ user.nickname }}</label>
      </div>
      <div>
        <label for="content">내용:</label>
        <textarea id="content" v-model="content" required></textarea>
      </div>
      <button type="submit">작성하기</button>
    </form>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { mapState } from "vuex";

export default {
  data() {
    return {
      videoIdx: "",
      content: "",
      userIdx: "",
      writer: "",
      parent: "",
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
/* Your component styles */
</style>
