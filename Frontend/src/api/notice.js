import axios from 'axios';

const noticeApi = axios.create({
    baseURL: '/api/v1/news/notices',
    timeout: 5000,
});

const imageApi = axios.create({
    baseURL: '/api/v1/images',
    timeout: 5000,
});

export const getNotices = (page, size) => noticeApi.get('', { params: { page, size } });
export const getNoticeDetail = (noticeId) => noticeApi.get(`/${noticeId}`);
export const getNoticeImages = (fileType) => imageApi.get('', { params: { fileType } });

export default {
    getNotices,
    getNoticeDetail,
    getNoticeImages
};
