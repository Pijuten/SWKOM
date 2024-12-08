import { DefaultApi } from './api';
import { Configuration } from './configuration';

const config = new Configuration({
    basePath: 'http://localhost:8081',
    accessToken: 'your-access-token-here'
  });

export const documentsApi = new DefaultApi(config);

