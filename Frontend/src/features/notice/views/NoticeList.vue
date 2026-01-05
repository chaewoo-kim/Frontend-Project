<template>
  <div class="notice-page">
    <CommonHeader />
    
    <main class="notice-container">
      <h1 class="notice-title">소식</h1>
      
      <div class="notice-list">
        <div v-if="loading" class="loading">Loading...</div>
        <div v-else-if="notices.length === 0" class="empty-list">등록된 소식이 없습니다.</div>
        <div 
          v-for="notice in notices" 
          :key="notice.noticeId" 
          class="notice-item"
          @click="goToDetail(notice.noticeId)"
        >
          <div class="notice-content">
            <span class="notice-subject">{{ notice.title }}</span>
            <span class="notice-date">{{ formatDate(notice.createdAt) }}</span>
          </div>
        </div>
      </div>
    </main>

    <CommonFooter />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import CommonHeader from '@/components/CommonHeader.vue';
import CommonFooter from '@/components/CommonFooter.vue';
import { getNotices } from '@/api/notice';

const router = useRouter();
const notices = ref([]);
const loading = ref(true);

const fetchNotices = async () => {
  try {
    const response = await getNotices();
    // Typical API structure: { success: true, response: { notices: [...] } }
    if (response.data.success) {
      notices.value = response.data.response.notices;
    }
  } catch (error) {
    console.error('Failed to fetch notices:', error);
  } finally {
    loading.value = false;
  }
};

const formatDate = (dateString) => {
  if (!dateString) return '';
  // Convert 2025-12-08 to 25.12.08 format
  const date = new Date(dateString);
  const year = String(date.getFullYear()).slice(-2);
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  return `${year}.${month}.${day}`;
};

const goToDetail = (noticeId) => {
  router.push(`/notice/${noticeId}`);
};

onMounted(() => {
  fetchNotices();
  
  // Dummy data for initial UI check as requested if API is not fully ready/connected
  notices.value = [
    { noticeId: 1, title: '[공지사항]만나서 결제 종료 후 결제수단 이용 방법 안내', createdAt: '2025-12-08' },
    { noticeId: 2, title: '[공지사항]버거킹 멤버십 개편 작업 완료 안내', createdAt: '2025-12-01' },
    { noticeId: 3, title: "[공지사항]딜리버리 주문 시 '만나서 결제' 서비스 종료 안내 (2025년 12월 1일 부터)", createdAt: '2025-11-24' },
    { noticeId: 4, title: '[공지사항]버거킹 개인정보 수집 및 이용 동의 개정 안내 (2025년 11월 24일 시행)', createdAt: '2025-11-18' },
    { noticeId: 5, title: '[공지사항]버거킹 개인정보 처리방침 개정 안내 (2025년 10월 20일 시행)', createdAt: '2025-10-16' },
    { noticeId: 6, title: '[공지사항]2FOR6000 + 2FOR7000 할인 행사 조기 종료 안내', createdAt: '2025-10-02' },
    { noticeId: 7, title: '[공지사항]2025년 추석연휴 딜리버리 휴무매장 안내', createdAt: '2025-10-01' },
    { noticeId: 8, title: '[공지사항]버거킹 비밀번호 설정 규칙 변경에 대한 안내 (10/1~)', createdAt: '2025-09-24' },
  ];
  loading.value = false;
});
</script>

<style scoped>
.notice-page {
  background-color: #F5EBDC; /* Burger King background beige */
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.notice-container {
  flex: 1;
  max-width: 1140px;
  width: 100%;
  margin: 0 auto;
  padding: 80px 20px;
}

.notice-title {
  font-family: 'Flame', sans-serif;
  font-size: 3rem;
  font-weight: 900;
  color: #502314; /* Dark brown */
  text-align: center;
  margin-bottom: 60px;
}

.notice-list {
  border-top: 2px solid #D9CBB6;
}

.notice-item {
  border-bottom: 1px solid #D9CBB6;
  padding: 25px 0;
  cursor: pointer;
  transition: background-color 0.2s;
}

.notice-item:hover {
  background-color: rgba(80, 35, 20, 0.05);
}

.notice-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.notice-subject {
  font-size: 1.125rem;
  font-weight: 700;
  color: #502314;
  flex: 1;
  padding-right: 20px;
}

.notice-date {
  font-size: 1rem;
  color: #8D7E73;
  white-space: nowrap;
}

.loading, .empty-list {
  text-align: center;
  padding: 50px 0;
  color: #8D7E73;
}

@media screen and (max-width: 1024px) {
  .notice-title {
    font-size: 2.25rem;
    margin-bottom: 40px;
  }
  
  .notice-container {
    padding: 40px 20px;
  }
}
</style>
