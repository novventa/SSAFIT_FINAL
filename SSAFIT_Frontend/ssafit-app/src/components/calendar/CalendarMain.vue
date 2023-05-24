<template>
<div class="pa-5">
          <v-row>
              <v-col cols="12" md="6" class="mb-4">
                  <v-sheet height="500">
                      <v-calendar
                      v-model="selectedDate"
                              ref="calendar"
                              dark
                              :events="events"
                      ></v-calendar>
                  </v-sheet>
              </v-col>
          </v-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedDate: null,
      events:[
      {
        name: 'Event 1',
        content: 'hi',
        start: new Date(2023, 5, 24),
        end: new Date(2023, 5, 24),
      },
    ],
    };
  },
}
</script>

<style>

</style>