<template>
  <div class="file-list">
    <h3>Uploaded files</h3>
    <ul>
      <li v-for="(file, index) in files" :key="index">
        <span class="file-name"> {{file.title}} </span> <!--display file name-->
        <div class = file-actions>
        {{ file.id }}
        <img
            title="Edit this file"
            src="../assets/carbon_edit.png"
            alt="Edit Icon"
            class="edit-icon"
            @click="editFile(file)"
        />
        <img
            title="Delete this file"
            src="../assets/mi_delete.png"
            alt="Delete Icon"
            class="delete-icon"
            @click="deleteFile(file.id)"
        />
          <img
              title="View this file"
              src="../assets/view.png"
              alt="View Icon"
              class="view-icon"
              @click="viewFileContent(file)"
          />

        </div>
      </li>
    </ul>
    <edit-form v-if="isEditing" :file="selectedFile" @save-edits="saveFileEdits" @cancel-edit="cancelEdit" />
  </div>
</template>

<script setup lang="ts">
  import { defineEmits, defineProps, ref } from 'vue';
  import EditForm from './EditForm.vue';
  import {documentsApi} from "../../api/example";

  const props = defineProps({
    files: {
      type: Array as () => Document, // Define the type for the files
      required: true
    }
  });
  const emit = defineEmits(['delete-file', 'edit-file']);
  const isEditing = ref(false);
  const selectedFile = ref(null);
  const deleteFile = (index: string) => {
    emit('delete-file', index);
  };
  const editFile = (file: any) => {
    selectedFile.value = file;
    isEditing.value = true;
  };
  const saveFileEdits = (updatedFile: any) => {
    emit('update-file', updatedFile);
    isEditing.value = false;
  };
  const cancelEdit = () => {
    isEditing.value = false;
  };


  // Show file content in a new window or tab
  const viewFileContent = async (file) => {
    try {
      // Check if file has 'id' and 'name' properties
      if (!file || !file.id || !file.title) {
        console.error("File object is missing 'id' or 'name' properties:", file);
        return;
      }

      // Await the content of the file
      const documentContent = (await documentsApi.documentsDocumentIdContentGet(file.id)).data;

      // Open a new window
      const newWindow = window.open('', '_blank'); // '_blank' opens a new tab

      // Write the content to the new window
      if (newWindow) {
        newWindow.document.write(`
      <html lang="en">
        <head>
          <title>${file.title}</title>
          <style>
            body { font-family: Arial, sans-serif; padding: 20px; }
            pre { white-space: pre-wrap; word-wrap: break-word; }
          </style>
        </head>
        <body>
          <h2>Content of ${file.title}</h2>
          <pre>${documentContent.content}</pre>
          <button onclick="window.close();">Close</button>
        </body>
      </html>
      `);
        newWindow.document.close(); // Close the document to render
      }
    } catch (error) {
      console.error("Error fetching file content:", error);
    }
  };





</script>



<!--style -->
<style scoped>
img {
  margin-left: 15px;
  cursor: pointer;
}

.file-list {
  max-width: 99%;
  padding: 30px;
  border-radius: 12px;
  background-color: #f7f9fc;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
  margin-right: 40px;
}

.file-list h3 {
  font-size: 1.3rem;
  text-align: center;
  color: #2c3e50;
  margin-bottom: 30px;
}

.file-list ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.file-list li {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 30px;
  margin-bottom: 15px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  flex-wrap: nowrap;
}

.file-list li:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

.file-name {
  font-size: 1.2rem;
  font-weight: 500;
  color: #333;
  flex-grow: 1;
  word-break: break-all;
  margin-right: 40px;
}

.file-actions {
  display: flex;
  gap: 20px;
}

.file-actions button {
  padding: 8px 12px;
  font-size: 0.9rem;
  background-color: #3498db;
  color: #fff;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
}

.file-actions button:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
}

.file-actions button:active {
  transform: scale(0.98);
}

.view-icon {
  width: 24px;
  height: 24px;
}

</style>