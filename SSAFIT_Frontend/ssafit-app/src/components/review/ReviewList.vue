<template>
  <v-container class="review-page">
    <v-card>
      <div v-if="reviews.length > 0">
        <div class="review-count">
          <span class="count">{{ reviews.length }} 개의 리뷰</span>
        </div>
        <div v-for="review in reviews" :key="review.idx" class="review-item">
          <div class="review-details">
            <div class="writer">작성자 : {{ review.writer }}</div>
            <div class="content" v-if="!review.editing">
              내용 : {{ review.content }}
            </div>
            <div class="date-wrapper">
              <div class="date">작성일자 : {{ review.regDate }}</div>
              <!-- <div class="date">수정일자 : {{ review.modDate }}</div> -->
            </div>
          </div>

          <div
            v-if="review.userIdx === user.idx && !review.editing"
            class="buttons"
          >
            <v-btn
              class="edit-button"
              color="primary"
              @click="startEdit(review)"
              icon
            >
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn
              class="delete-button"
              color="red"
              @click="deleteReview(review)"
              icon
            >
              <v-icon>mdi-trash-can</v-icon>
            </v-btn>
          </div>
        </div>
      </div>
      <div v-else>
        <span>0개의 리뷰</span>
      </div>
    </v-card>

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
          <v-btn text color="orange" @click="saveReview">저장</v-btn>
          <v-btn text @click="cancelEdit">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
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
.review-page {
  margin: 20px;
}

.review-count {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.review-item {
  margin-bottom: 20px;
}

.review-details {
  display: flex;
  flex-direction: column;
}

.date-wrapper {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.writer {
  font-weight: bold;
}

.content {
  margin-top: 5px;
}

.date {
  margin-top: 5px;
  margin-right: 10px;
  color: #888;
}

.buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}

.edit-button,
.delete-button {
  margin: 10px;
}

.v-btn .v-icon {
  margin: 4px;
}
</style>
