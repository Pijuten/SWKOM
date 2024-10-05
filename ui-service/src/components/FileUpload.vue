<template>
  <div class="upload-zone">
    <h3>Upload a new File</h3>
    <input type="file" multiple @change="onFileSelect" />
    <p>Drag & Drop your files here or click to select</p>

    <div class=" upload-zone"
  @dragover.prevent= "onDragOver"
         @dragenter="onDragEnter"
  @dragleave= "onDragLeave"
  @drop.prevent="onFileDrop">
  </div>

  </div>
</template>

<script setup lang="ts">
  import { defineEmits } from 'vue';
  import {valueOf} from "axios";
  import {ref} from 'vue';

  // Emit files-uploaded event
  const emit = defineEmits(['files-uploaded']);
  const isDragging= ref(false) //track dragging state

  // Handle file upload
  const onFileSelect = (event: Event) => {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      const files = Array.from(input.files);
      emit('files-uploaded', files);
    }
  };
  const onFileDrop = (event: DragEvent) => {
   isDragging.value= false;
    const files = Array.from(event.dataTransfer?.files || []);
    emit('files-uploaded', files);
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
  .upload-zone {
    position: relative;
    width: 30%;
    padding: 30px;
    border: 2px dashed #ccc;
    text-align: center;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  .upload-zone:hover {
    background-color: #f9f9f9;
  }



</style>