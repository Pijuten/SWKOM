<template>
  <HelloWorld msg="Paperless"/>
  <div class="container">
    <SearchBar :files="uploadedFiles" @search-file="fetchSearchFiles"> </SearchBar>
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
import {Document} from "../api";
import SearchBar from "@/components/SearchBar.vue";

const uploadedFiles = ref<Array<Document>>([]); // Initialize an empty array to store files

// Function to fetch files from the backend
const fetchFiles = async () => {
  try {
    // Get the data directly from the API
    const response = await documentsApi.documentGet();
    uploadedFiles.value = response.data// Update the uploadedFiles with data from the backend
    console.log(response.data)
  } catch (error) {
    console.error('Error fetching files:', error);
  }
};

const fetchSearchFiles = async (searchString:string) =>{
  const response = await documentsApi.documentSearchGet(searchString)
  uploadedFiles.value = response.data
}

// Lifecycle hook to fetch files when the component is mounted
onMounted(() => {
  fetchFiles();
});

const addFiles = async (files: Document) => {
  try {
    // Check if files is an array and has at least one item
    if (files.title!==undefined) {
      // Assuming each file in the array has the document property
        await documentsApi.documentPost(files.title,files.username,files.description,files.file);
      await fetchFiles();
    }else{
      console.log("No files")
    }
  } catch (error) {
    console.error(error);
  }
};


const deleteFile = (index: string) => {
  documentsApi.documentDelete(index);
  uploadedFiles.value.splice(index, 1);
};

const editFile = async (newDocument: Document) => {
  try {
    if(newDocument.id!==undefined){
      await documentsApi.documentPut(newDocument.title,newDocument.username,newDocument.description,newDocument.file,newDocument.id);
      // After successful edit, fetch the updated list
      await fetchFiles();
    }else{
      console.log('No file')
    }
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
