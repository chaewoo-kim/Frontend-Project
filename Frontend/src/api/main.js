import axios from 'axios';

const mainApi = axios.create({
    baseURL: '/api/v1/mainpage',
    timeout: 5000,
});

const storyApi = axios.create({
    baseURL: '/api/v1/story',
    timeout: 5000,
});

export const getMainPageData = () => mainApi.get('/total');
export const getEventImages = () => storyApi.post('', { type: 'MAINPAGE_EVENT' });

export default {
    getMainPageData,
    getEventImages
};
