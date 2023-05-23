<template>
   <div>
    <h3>아이디 찾기</h3>
    <fieldset>
      <legend>아이디 찾기</legend>
      <label for="email">이메일 : </label>
      <input type="text" id="email" v-model="email" /><br />
      <label for="nickname">별명 : </label>
      <input type="text" id="nickname" v-model="nickname" /><br />
      <button @click="findId">아이디 찾기</button>
    </fieldset>
  </div>
</template>

<script>
export default {
    name: 'FindId',
    data() {
        return {
            email: '',
            nickname: '',
        }
    },
    methods: {
        findId() {
            let info = {
                email: this.email,
                nickname: this.nickname,
            }
            this.$store.dispatch('findId', info);
        }
    }
}
</script>

<style>

</style>