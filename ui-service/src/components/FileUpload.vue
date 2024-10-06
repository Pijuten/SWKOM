<template>
  <div class="upload-container">
    <div class="upload-zone">
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
  import { ref } from 'vue';
  import { defineEmits } from 'vue';

  // Emit files-submit event
  const emit = defineEmits(['file-submitted']);

  // Reactive variables to store the selected file and metadata
  const fileSelected = ref(false);
  const selectedFile = ref<File | null>(null);
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
      fileSelected.value = true;
    }
  };

  // Handle form submission
  const submitForm = () => {
    if (selectedFile.value) {
      emit('file-submitted', {
        file: selectedFile.value,
        metadata: metadata.value
      });
      resetForm();
    }
  };

  const resetForm = () => {
    // Reset the form after submission
    selectedFile.value = null;
    fileSelected.value = false;
    metadata.value = { title: '', description: '', username: '' };
  };

  const onFileDrop = (event: DragEvent) => {
    const files = Array.from(event.dataTransfer?.files || []);
    emit('file-uploaded', files);
  };
  const onDragOver = () => {
    // TODO?
    return;
  };
</script>

<style scoped>
  .upload-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 20px;
    padding: 20px;
  }
  .upload-zone {
    width: 70%;
    padding: 20px;
    border: 2px dashed #ccc;
    text-align: center;
    cursor: pointer;
  }
  .upload-zone:hover {
    background-color: #f9f9f9;
  }
  .metadata-form {
    width: 80%;
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