<template>
  <HelloWorld msg="Paperless"/>
  <div class="container">
    <file-list :files="uploadedFiles" @delete-file="deleteFile" @edit-file="editFile"/>
    <file-upload @files-uploaded="addFiles"/>
  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from 'vue';
import HelloWorld from './components/HelloWorld.vue';
import FileList from './components/FileList.vue';
import FileUpload from './components/FileUpload.vue';
import {documentsApi} from '../api/example';
import {Document} from "../api"; // Import the API

const uploadedFiles = ref<Array<Document>>([]); // Initialize an empty array to store files

// Function to fetch files from the backend
const fetchFiles = async () => {
  try {
    // Get the data directly from the API
    const response = await documentsApi.documentsGet();
    uploadedFiles.value = response.data// Update the uploadedFiles with data from the backend
    console.log(response.data)
  } catch (error) {
    console.error('Error fetching files:', error);
  }
};


// Lifecycle hook to fetch files when the component is mounted
onMounted(() => {
  fetchFiles();
});

const addFiles = async (files) => {
  try {
    // Check if files is an array and has at least one item
    if (Array.isArray(files) && files.length > 0) {
      // Assuming each file in the array has the document property
      for (const fileData of files) {
        await documentsApi.documentsPost(fileData.document);
      }
      await fetchFiles();
    }
  } catch (error) {
    console.error(error);
  }
};


const deleteFile = (index: string) => {
  documentsApi.documentsDocumentIdDelete(index);
  uploadedFiles.value.splice(index, 1);
};

const editFile = async (newDocument: Document) => {
  try {
    await documentsApi.documentsDocumentIdContentPut(newDocument.id, newDocument);
    // After successful edit, fetch the updated list
    await fetchFiles();
  } catch (error) {
    console.error('Error editing file:', error);
  }
}
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
