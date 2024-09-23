import { DocumentsApi } from './api';
import { Configuration } from './configuration';

const config = new Configuration({
    basePath: 'https://your-api-base-url.com',
    accessToken: 'your-access-token-here'
  });

const documentsApi = new DocumentsApi(config);

documentsApi.documentsDocumentIdContentGet('document-id-here')
  .then(response => {
    console.log(response.data);
  })
  .catch(error => {
    console.error(error);
  });

// Get all documents
documentsApi.documentsGet()
  .then(response => {
    console.log(response.data);
  })
  .catch(error => {
    console.error(error);
  });

// Create a new document
const newDocument = {
  username: 'user123',
  description: 'My new document'
};

documentsApi.documentsPost(newDocument)
  .then(response => {
    console.log(response.data);
  })
  .catch(error => {
    console.error(error);
  });