<template>
  <div class="file-list">
    <h3>Uploaded files</h3>
    <ul>
      <li v-for="(file, index) in files" :key="index">
        {{ file.id }}
        <button @click="editFile(file.id)">Edit</button>
        <button @click="deleteFile(file.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
  import { defineEmits, defineProps } from 'vue';
  const props = defineProps({
    files: {
      type: Array as () => Document, // Define the type for the files
      required: true
    }
  });
  const emit = defineEmits(['delete-file', 'edit-file']);
  const deleteFile = (index: string) => {
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