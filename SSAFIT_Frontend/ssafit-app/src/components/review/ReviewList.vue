<template>
  <div>
    <div class="review-count">
      <span class="count">{{ reviews.length }} 개의 리뷰</span>
    </div>
    <div v-for="review in reviews" :key="review.id" class="review-item">
      <div class="review-details">
        <div class="writer">{{ review.writer }}</div>
        <div class="content">{{ review.content }}</div>
      </div>
      <div v-if="review.replies.length" class="replies">
        <div v-for="reply in review.replies" :key="reply.id" class="reply">
          <div class="reply-writer">{{ reply.writer }}</div>
          <div class="reply-content">{{ reply.content }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      reviews: [
        {
          id: 1,
          writer: "작성자1",
          content: "굿~~~~~~~~~~~~~~~~~!",
          replies: [
            { id: 1, writer: "답글1", content: "답글입니다." },
            { id: 2, writer: "답글2", content: "답글2입니다." },
          ],
        },
        {
          id: 2,
          writer: "작성자2",
          content: "별로에요",
          replies: [],
        },
      ],
    };
  },
};
</script>

<style scoped>
.review-count {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.review-item {
  margin-bottom: 20px;
}

.review-details {
  margin-bottom: 10px;
}

.author {
  font-weight: bold;
}

.content {
  margin-top: 5px;
}

.replies {
  margin-top: 10px;
  padding-left: 20px;
  border-left: 2px solid #ccc;
}

.reply {
  margin-bottom: 10px;
}

.reply-author {
  font-weight: bold;
}

.reply-content {
  margin-top: 5px;
}
</style>
