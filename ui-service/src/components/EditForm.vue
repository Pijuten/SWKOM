<template>
  <div v-if="file" class="edit-form">
    <h3>Edit File: {{ file.id }}</h3>
    <form @submit.prevent="submitEdit">
      <label for="fileName">File Name:</label>
      <input v-model="editedFile.title" type="text" id="fileTitle" />

      <label for="fileDescription">Description:</label>
      <input v-model="editedFile.description" type="text" id="fileDescription" />

      <label for="username">Username:</label>
      <input v-model="editedFile.username" type="text" id="username" />

      <div class="button-group">
        <button type="submit">Save Changes</button>
        <button type="button" @click="cancelEdit">Cancel</button>
      </div>


    </form>
  </div>
</template>

<script setup lang="ts">
import { defineProps, defineEmits, reactive } from 'vue';

// Props to receive the file data
const props = defineProps({
  file: {
    type: Object,
    required: true,
  },
});

// Emit to notify the parent when the file is updated or the edit is canceled
const emit = defineEmits(['save-edits', 'cancel-edit']);

// Create a reactive copy of the file to allow editing
const editedFile = reactive({ ...props.file });

// Methods
const submitEdit = () => {
  emit('save-edits', editedFile);
};

const cancelEdit = () => {
  emit('cancel-edit');
};
</script>

<style scoped>
.edit-form {
  display: flex;
  flex-direction: column;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
  max-width: 400px;
  margin: 20px auto;
  text-align: left;
}

.edit-form input {
  width: 90%;
  padding: 10px;
  margin-bottom: 15px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

.button-group {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin-top: 15px;
}

.edit-form button {
  margin-right: 10px;
  padding: 10px 15px;
  border: none;
  background-color: #3498db;
  color: white;
  cursor: pointer;
  border-radius: 4px;
}

.edit-form button:hover {
  background-color: #2980b9;
}
</style>
