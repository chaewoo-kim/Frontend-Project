import axios from 'axios';

const customerApi = axios.create({
    baseURL: '/api/v1/customer',
    timeout: 5000,
});

export const getCategories = () => customerApi.get('/main/categories');
export const getTopFaqs = () => customerApi.get('/main/faq');
export const searchQA = (keyword) => customerApi.get('/main/search', { params: { keyword } });
export const getQAListByCategory = (categoryId) => customerApi.get(`/qalist/category/${categoryId}`);
export const getAllQAList = () => customerApi.get('/qalist');
export const getQADetail = (qaId) => customerApi.get(`/detail/${qaId}`);

export default {
    getCategories,
    getTopFaqs,
    searchQA,
    getQAListByCategory,
    getAllQAList,
    getQADetail
};
