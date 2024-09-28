<template>
  <div class="file-list">
    <h3>Uploaded files</h3>
    <ul>
      <li v-for="(file, index) in files" :key="index">
        {{ file.name }}
        <button @click="editFile(index)">Edit</button>
        <button @click="deleteFile(index)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
  import { defineEmits, defineProps } from 'vue';
  const props = defineProps({
    files: {
      type: Array as () => Array<{ name: string }>, // Define the type for the files
      required: true
    }
  });
  const emit = defineEmits(['delete-file', 'edit-file']);
  const deleteFile = (index: number) => {
    emit('delete-file', index);
  };
  const editFile = (index: number) => {
    emit('edit-file', index);
  };
</script>

<style scoped>
  .file-list {
    width: 60%;
    border: 1px solid #2c3e50;
    margin-right: 10px;
  }
</style>