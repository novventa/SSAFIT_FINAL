<template>
  <v-main>
    <v-container>
      <!-- 상단에 항상 표시되는 1주일 달력 -->
      <v-calendar
        v-model="selectedDate"
        :events="events"
        :week-days="7"
        :type="type"
        :weekday-format="format"
        color="primary"
      ></v-calendar>

      <!-- Main content -->
      <h3>조회수순</h3>
      <video-list></video-list>

      <h3>좋아요순</h3>
      <video-list></video-list>
    </v-container>
  </v-main>
</template>

<script>
import VideoList from "./video/VideoList.vue";

export default {
  components: {
    VideoList,
  },
  data() {
    return {
      selectedDate: new Date(),
      events: [
        {
          name: "Event 1",
          start: "2023-05-01",
          end: "2023-05-02",
          color: "red",
        },
        {
          name: "Event 2",
          start: "2023-05-05",
          end: "2023-05-07",
          color: "blue",
        },
      ],
      type: "week",
      format: "dddd", // Change the format according to your needs
    };
  },
};
</script>

<style>
/* Your custom styles */
</style>
