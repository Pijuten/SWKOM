<template>
  <HelloWorld msg="Paperless"/>
  <div class="container">
    <file-list :files="uploadedFiles" @delete-file="deleteFile" @edit-file="editFile" />
    <file-upload @files-uploaded="addFiles" />
  </div>
</template>

<script setup lang="ts">
import { defineComponent } from 'vue';
import HelloWorld from './components/HelloWorld.vue';
import { ref } from 'vue';
import FileList from './components/FileList.vue';
import FileUpload from './components/FileUpload.vue';

const uploadedFiles = ref<Array<{ name: string }>>([]);
const addFiles = (files: Array<{ name: string }>) => {
  uploadedFiles.value = [...uploadedFiles.value, ...files];
};
const deleteFile = (index: number) => {
  uploadedFiles.value.splice(index, 1);
};
const editFile = (index: number) => {
  // Handle the logic to edit the file, e.g., rename it
  console.log(`Edit file at index ${index}`);
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.container {
  display: flex;
  justify-content: center;
  padding: 20px;
}
</style>

