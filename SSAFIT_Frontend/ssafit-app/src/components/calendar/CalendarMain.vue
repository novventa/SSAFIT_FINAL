<template>
  <div>
    <v-calendar v-model="selectedDate" :events="events" color="primary">
      <template #day-body="{ date }">
        <div>{{ date }}</div>
        <!-- 원하는 날짜 정보를 표시하는 추가적인 내용 -->
      </template>
    </v-calendar>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedDate: null, // 선택된 날짜
      events: [
        // 이벤트 데이터
        {
          name: "Event 1",
          start: new Date(), // 이벤트 시작 날짜 및 시간
          end: new Date(), // 이벤트 종료 날짜 및 시간
        },
        // 추가적인 이벤트 데이터
      ],
    };
  },
};
</script>

<style>
/* 추가적인 스타일링 */
</style>
