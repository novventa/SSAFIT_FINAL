<template>
  <v-container>
    <h3 class="title">찾고 싶은 영상을 검색하세요</h3>
    <div class="toggle-buttons">
      <v-btn
        class="toggle-button"
        :class="{
          orange: !isPartSelected('전신'),
          green: isPartSelected('전신'),
        }"
        @click="togglePart('전신')"
      >
        전신
      </v-btn>
      <v-btn
        class="toggle-button"
        :class="{
          orange: !isPartSelected('상체'),
          green: isPartSelected('상체'),
        }"
        @click="togglePart('상체')"
      >
        상체
      </v-btn>
      <v-btn
        class="toggle-button"
        :class="{
          orange: !isPartSelected('하체'),
          green: isPartSelected('하체'),
        }"
        @click="togglePart('하체')"
      >
        하체
      </v-btn>
      <v-btn
        class="toggle-button"
        :class="{
          orange: !isPartSelected('코어'),
          green: isPartSelected('코어'),
        }"
        @click="togglePart('코어')"
      >
        코어
      </v-btn>
    </div>
    <div class="search-input">
      <v-text-field
        v-model="keyword"
        outlined
        dense
        solo-inverted
        label="제목으로 검색"
        @keydown.enter="search"
      ></v-text-field>
      <v-btn class="search-button" @click="search">검색</v-btn>
    </div>
  </v-container>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "VideoSearch",
  computed: {
    ...mapActions(["clearVideo"]),
  },
  created() {
    this.clearVideo;
  },
  data() {
    return {
      keyword: "",
      parts: [],
    };
  },
  methods: {
    search() {
      let keyword = {
        key: "title",
        word: this.keyword,
        orderBy: "none",
        orderByDir: "ASC",
      };

      this.$store.dispatch("searchVideo", keyword);
    },
    togglePart(part) {
      if (this.isPartSelected(part)) {
        this.parts = this.parts.filter((item) => item !== part);
      } else {
        this.parts.push(part);
      }
      // if (this.parts.length === 0) {
      //   this.$store.dispatch("searchParts", []);
      // }
      this.$store.dispatch("searchParts", this.parts);
    },
    isPartSelected(part) {
      return this.parts.includes(part);
    },
  },
};
</script>

<style scoped>
.title {
  margin-bottom: 16px;
}
.toggle-buttons {
  display: flex;
  gap: 8px;
  margin-bottom: 16px;
}

.toggle-button {
  margin-right: 8px;
  color: white !important;
}

.search-input {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 16px;
}

.search-button {
  margin-bottom: 27px;
}

.search-input .v-text-field__slot input {
  background-color: #f5f5f5 !important;
}

.search-input .v-text-field__slot input::placeholder {
  color: #9e9e9e !important;
}
</style>
