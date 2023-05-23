<template>
  <div>
    <h2>리뷰 작성</h2>
    <form @submit.prevent="submitReview">
      <div>
        <label for="writer">작성자:</label>
        <input type="text" id="writer" v-model="writer" required />
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

export default {
  data() {
    return {
      writer: "",
      content: "",
    };
  },
  methods: {
    ...mapActions(["addReview"]),
    submitReview() {
      const review = {
        writer: this.writer,
        content: this.content,
      };
      this.addReview(review)
        .then(() => {
          this.author = "";
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
