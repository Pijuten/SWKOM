<template>
  <div class="upload-container">
    <div class="upload-zone" @dragover.prevent= "onDragOver" @dragenter="onDragEnter" @dragleave= "onDragLeave" @drop.prevent="onFileDrop">
      <h3>Upload a new File</h3>
      <input type="file" multiple @change="onFileSelect" />
      <p v-if="!fileSelected">Drag & Drop your files here or click to select</p>
    </div>

    <!-- Metadata Form -->
    <div v-if="fileSelected" class="metadata-form">
      <h3>File Metadata</h3>
      <form @submit.prevent="submitForm">
        <label for="title">Title:</label>
        <input v-model="metadata.title" type="text" id="title" required />

        <label for="description">Short Description:</label>
        <input v-model="metadata.description" type="text" id="description" required />

        <label for="username">Username:</label>
        <input v-model="metadata.username" type="text" id="username" required />

        <button type="submit">Submit</button>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
  import { defineEmits } from 'vue';
  import {ref} from 'vue';
  import {Document} from "../../api";

  // Emit files-uploaded event
  const emit = defineEmits(['files-uploaded']);
  const isDragging= ref(false) //track dragging state

  // for metadata input
  const fileSelected = ref(false);
  const selectedFile = ref<File | null>(null);
  const uploadedFile = ref<Document>(); // Store uploaded files
  const metadata = ref({
    title: '',
    description: '',
    username: ''
  });

  // Handle file upload
  const onFileSelect = (event: Event) => {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      selectedFile.value = input.files[0];
      fileSelected.value = true; // Show metadata form
    }
  };
  const onFileDrop = (event: DragEvent) => {
   isDragging.value= false;
    const files = Array.from(event.dataTransfer?.files || []);
    if (files.length > 0) {
      selectedFile.value = files[0];
      fileSelected.value = true; // Show metadata form
    }
  };

  // Handle form submission (metadata + file)
  const submitForm = () => {
    if (selectedFile.value) {
      console.log("File being submitted:", selectedFile.value); // Verify the file is not null
      const  fileData: Document = {
          username: metadata.value.username,
          title: metadata.value.title,
          description: metadata.value.description,
          file: selectedFile.value
      };
      // Emit the event to parent component if needed
      emit('files-uploaded', fileData);

      resetForm(); // Reset the form and file selection only after pushing data
    } else {
      console.warn("No file selected for submission");
    }
  };


  // Handle form and selection reset
  const resetForm = () => {
    selectedFile.value = null;
    fileSelected.value = false;
    metadata.value = { title: '', description: '', username: '' };
  };

  //Handle drag over (allow dropping)
  const onDragOver = (e: DragEvent) => {
    e.preventDefault(); //allow dropping
    e.dataTransfer!.dropEffect = 'copy';
  };

  //Handle drag enter (show visual feedback)
  const onDragEnter = () => {
    isDragging.value= true;
  }

  //handle remove visual feedback
  const onDragLeave = () => {
    isDragging.value= false;
  }

</script>

<style scoped>
  .upload-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
  }
  .upload-zone {
    position: relative;
    width: 80%;
    padding: 30px;
    border: 2px dashed #ccc;
    text-align: center;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  .upload-zone:hover {
    background-color: #f9f9f9;
  }
  .metadata-form {
    width: 100%;
    border: 1px solid #ccc;
    text-align: center;
    padding: 5px;
  }

  .metadata-form label {
    display: flex;
    justify-content: flex-start;
    margin-bottom: 3px;
  }

  .metadata-form input {
    width: 90%;
    padding-top: 4px;
    padding-bottom: 4px;
    margin-bottom: 10px;
    border-radius: 4px;
    border: 1px solid #ccc;
  }

  .metadata-form button {
    padding: 10px 20px;
    background-color: #2596BE;
    color: white;
    border: none;
    cursor: pointer;
    border-radius: 5px;
  }

  .metadata-form button:hover {
    background-color: #007BFF;
  }
</style>