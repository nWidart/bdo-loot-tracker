import axios from 'axios';
import Cookies from 'js-cookie';

axios.interceptors.request.use((config) => {
  if (config.url && config.url.includes('authenticate')) {
    return config;
  }
  const token = Cookies.get('jwtToken');
  config.headers.Authorization = `Bearer ${token}`;
  return config;
});

axios.interceptors.response.use((response) => {
  return response;
}, (error) => {
  if (error && error.response && error.response.status) {
    window.location.assign('/login');
  }
  return Promise.reject(error);
});
