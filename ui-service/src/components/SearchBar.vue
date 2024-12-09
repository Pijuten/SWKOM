<script setup lang="ts">
import {defineProps, PropType, ref} from "vue";
import {Document} from "../../api";

const props = defineProps({
  files: {
    type: Array as PropType<Document[]>,
    required: true, // or false if it's optional
  },
});
const inputValue = ref("");
</script>

<template>
  <input v-model="inputValue" />
  <button @click="$emit('search-file', inputValue)">Search</button>
</template>

<style scoped>

</style>