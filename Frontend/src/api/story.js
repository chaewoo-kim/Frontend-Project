import axios from 'axios';

// 백엔드 API 주소 설정
const api = axios.create({
    baseURL: '/api/v1/story',
    timeout: 5000,
});

export default {
    // FileType별 이미지 조회
    getImagesByType(type) {
        return api.post('', { type });
    }
};
