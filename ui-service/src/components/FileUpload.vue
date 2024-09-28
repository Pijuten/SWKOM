<template>
  <div class="upload-zone">
    <h3>Upload</h3>
    <input type="file" multiple @change="onFileSelect" />
    <p>Drag & Drop your files here or click to select</p>
  </div>
</template>

<script setup lang="ts">
  import { defineEmits } from 'vue';

  // Emit files-uploaded event
  const emit = defineEmits(['files-uploaded']);

  // Handle file upload
  const onFileSelect = (event: Event) => {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      const files = Array.from(input.files);
      emit('files-uploaded', files);
    }
  };
  const onFileDrop = (event: DragEvent) => {
    const files = Array.from(event.dataTransfer?.files || []);
    emit('files-uploaded', files);
  };
  const onDragOver = () => {
    // TODO?
    return;
  };
</script>

<style scoped>
  .upload-zone {
    width: 25%;
    padding: 20px;
    border: 2px dashed #ccc;
    text-align: center;
    cursor: pointer;
  }
  .upload-zone:hover {
    background-color: #f9f9f9;
  }
</style>