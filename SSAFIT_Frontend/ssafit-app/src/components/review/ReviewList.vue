<template>
  <v-container class="review-page">
    <v-card>
      <div v-if="reviews.length > 0">
        <div class="review-count">
          <span class="count">{{ reviews.length }} 개의 리뷰</span>
          <hr />
        </div>
        <div v-for="review in reviews" :key="review.idx" class="review-item">
          <div class="review-details">
            <div class="writer-name">
              <v-menu class="writer" offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <img
                    :src="getUserProfilePicture(review.userIdx)"
                    alt="프로필 사진"
                    class="profile-picture"
                    v-bind="attrs"
                    v-on="on"
                  />
                </template>
                <v-list>
                  <v-list-item v-if="!isFollow(review)">
                    <v-list-item-title>
                      <v-btn text color="orange" @click="addFollow(review)"
                        >팔로우하기</v-btn
                      >
                    </v-list-item-title>
                  </v-list-item>
                  <v-list-item v-else>
                    <v-list-item-title>
                      <v-btn text color="red" @click="cancelFollow(review)"
                        >팔로우 취소</v-btn
                      >
                    </v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
              {{ review.writer }}
            </div>
            <div class="content" v-if="!review.editing">
              {{ review.content }}
            </div>
            <div class="date-wrapper">
              <div class="date">
                {{
                  review.regDate.split(" ")[0] +
                  " " +
                  review.regDate.split(" ")[1].split(":")[0] +
                  ":" +
                  review.regDate.split(" ")[1].split(":")[1]
                }}
              </div>
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
          <hr />
        </div>
      </div>
      <div v-else>
        <span style="font-size: 18pt">0개의 리뷰</span>
      </div>
    </v-card>

    <v-dialog v-model="deleteConfirmDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span>리뷰 삭제</span>
        </v-card-title>
        <v-card-text>
          <span>리뷰를 삭제하시겠습니까?</span>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text @click="cancelDelete">취소</v-btn>
          <v-btn text color="red" @click="confirmDelete">삭제</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

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
      deleteConfirmDialog: false,
      deletingReview: null,
      followNicknames: [],
    };
  },
  computed: {
    ...mapState(["video", "reviews", "user", "users", "followList"]),
    getFollowList() {
      return this.followList;
    },
    videoIdx() {
      return this.video.idx;
    },
  },
  watch: {
    getFollowList(value) {
      this.followNicknames = [];
      Array.from(value).forEach((data) => {
        this.followNicknames.push(data.yourNickname);
      });
      // !Array.from(value.some((obj) => obj.yourNickname === review.writer));
    },
  },
  created() {
    this.$store.dispatch("getFollowList");
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    ...mapActions(["getReviews", "modifyReview", "deleteReview", "addFollows"]),
    fetchData() {
      this.getReviews(this.videoIdx);
    },
    removeReview(review) {
      this.deletingReview = review;
      this.deleteConfirmDialog = true;
    },
    cancelDelete() {
      this.deletingReview = null;
      this.deleteConfirmDialog = false;
    },
    confirmDelete() {
      if (this.deletingReview) {
        this.deleteReview(this.deletingReview);
        this.deletingReview = null;
        this.deleteConfirmDialog = false;
      }
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
    getUserProfilePicture(userIdx) {
      let user = this.users.find((user) => user.idx === userIdx);
      if (user && user.image !== "none") {
        return require(`@/assets/upload/${user.id}/${user.image}`);
      }
      return require(`@/assets/upload/none.png`);
    },
    addFollow(rev) {
      // console.log(review);
      let fo = {
        myIdx: this.user.idx,
        yourIdx: rev.userIdx,
        yourNickname: rev.writer,
      };
      this.addFollows(fo);
    },
    cancelFollow(review) {
      // console.log(this.followList);
      let idx = Array.from(this.followList).find(
        (obj) =>
          obj.yourNickname === review.writer && this.user.idx === obj.myIdx
      );
      idx = idx.idx;
      this.$store.dispatch("removeFollow", idx);
    },
    isFollow(rev) {
      let flag = Array.from(this.followNicknames).find(
        (obj) => obj === rev.writer
      );
      if (flag) {
        return true;
      }
      return false;
    },
  },
};
</script>

<style scoped>
.review-page {
  max-width: 1300px;
  margin: 0 auto;
}

.review-count {
  font-size: 23px;
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
  margin: 10px;
  font-weight: bold;
}

.content {
  margin: 5px;
  font-size: 14pt;
  white-space: pre-line;
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
  margin: 2px;
}
.writer-name {
  display: flex;
  align-items: center;
  margin: 10px;
  font-weight: bold;
  font-size: 15pt;
}

.profile-picture {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
  cursor: pointer;
}

.v-menu {
  z-index: 999; /* Ensure the menu appears above other elements */
}
</style>
