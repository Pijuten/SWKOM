<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>This website allows you to manage your files.</p>
  </div>
</template>

<script lang="ts">
  import { defineComponent } from 'vue';
  export default defineComponent({
    name: 'HelloWorld',
    props: {
      msg: String,
    },
  });
</script>

<style scoped>
  h3 {
    margin: 40px 0 0;
  }
</style>