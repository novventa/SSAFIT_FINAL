<template>
  <div>
    <div v-if="reviews.length > 0">
      <div class="review-count">
        <span class="count">{{ reviews.length }} 개의 리뷰</span>
      </div>
      <div v-for="review in reviews" :key="review.idx" class="review-item">
        <div class="review-details">
          <div class="writer">작성자: {{ review.writer }}</div>
          <div v-if="!review.editing" class="content">
            내용: {{ review.content }}
          </div>
          <span>작성일자 : {{ review.regDate }}</span>
          <span>수정일자 : {{ review.modDate }}</span>
        </div>
        <div
          v-if="review.userIdx === user.idx && !review.editing"
          class="buttons"
        >
          <button class="edit-button" @click="startEdit(review)">수정</button>
          <button class="delete-button" @click="deleteReview(review)">
            삭제
          </button>
        </div>
      </div>
    </div>
    <div v-else>
      <span>0개의 리뷰</span>
    </div>

    <v-dialog v-model="editDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span>리뷰 수정</span>
        </v-card-title>
        <v-card-text>
          <v-textarea
            v-model="editedContent"
            auto-grow
            :counter="500"
            label="리뷰 내용"
          ></v-textarea>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="saveReview">저장</v-btn>
          <v-btn text @click="cancelEdit">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  data() {
    return {
      editDialog: false,
      editedContent: "",
      editingReview: null,
    };
  },
  computed: {
    ...mapState(["video", "reviews", "user"]),
    videoIdx() {
      return this.video.idx;
    },
  },
  methods: {
    ...mapActions(["getReviews", "modifyReview", "deleteReview"]),
    fetchData() {
      this.getReviews(this.videoIdx);
    },
    deleteReview(review) {
      this.$store.dispatch("deleteReview", review.idx);
    },
    startEdit(review) {
      this.editingReview = review;
      this.editedContent = review.content;
      this.editDialog = true;
    },
    saveReview() {
      if (this.editingReview) {
        this.editingReview.content = this.editedContent;
        this.editDialog = false;
        this.modifyReview(this.editingReview);
      }
    },
    cancelEdit() {
      this.editDialog = false;
      this.editedContent = "";
      this.editingReview = null;
    },
  },
  mounted() {
    this.fetchData();
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
