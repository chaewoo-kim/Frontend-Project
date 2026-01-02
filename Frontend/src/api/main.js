import axios from 'axios';

const mainApi = axios.create({
    baseURL: '/api/v1/mainpage',
    timeout: 5000,
});

export const getMainPageData = () => mainApi.get('/total');

export default {
    getMainPageData
};
