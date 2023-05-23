<template>
  <div>
    <h3>검색하기</h3>
    <input type="text" v-model="keyword" @keypress.enter="search" />
    <button @click="search">검색</button>
  </div>
</template>

<script>
export default {
  name: "VideoSearch",
  data() {
    return {
      keyword: "",
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
  },
};
</script>

<style></style>
