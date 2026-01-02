import axios from 'axios';

// 백엔드 API 주소 설정 (Spring Boot 기본 포트 8080 가정)
const api = axios.create({
  baseURL: '/api/v1/menu/main',
  timeout: 5000,
});

export default {
  // 전체 메뉴 조회
  getMenus() {
    return api.get('');
  },
  // 검색 (ids: List<Long>)
  searchMenus(ids) {
    return api.get('/search', { params: { id: ids.join(',') } });
  },
  // 상세 조회 (id: Long)
  getMenuDetail(id) {
    return api.get(`/detail/${id}`);
  },
  // 키워드 목록 조회
  getKeywords() {
    return api.get('/keywords');
  }
};
