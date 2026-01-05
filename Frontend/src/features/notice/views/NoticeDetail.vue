<template>
  <div class="notice-detail-page">
    <CommonHeader />
    
    <main class="notice-detail-container">
      <div class="notice-header">
        <p class="notice-category">소식</p>
        <h1 class="notice-main-title">공지사항</h1>
      </div>

      <div class="notice-content-wrap" v-if="notice">
        <div class="notice-info">
          <span class="badge">공지사항</span>
          <h2 class="notice-title">{{ notice.title }}</h2>
          <p class="notice-date">{{ formatDate(notice.createdAt) }}</p>
        </div>

        <div class="notice-content">
          <div v-html="notice.content" class="content-text"></div>
          
          <!-- Mock content if API doesn't return full text for ID 1 as per images -->
          <div v-if="String($route.params.id) === '1'" class="mock-content">
            <p>버거킹을 이용해주시는 고객 여러분께 안내드립니다.</p>
            <p>2024년 12월 1일부터 만나서 결제 서비스가 종료됨에 따라, 딜리버리 주문 시 만나서 결제를 이용하시던 일부 고객님들께서 문의를 주고 계십니다.</p>
            <p>이에 결제수단별 이용 가능 여부 및 대체 결제 방법을 안내드립니다.</p>
            <br/>
            <p><strong>1. 법인카드 이용 안내</strong></p>
            <p>버거킹 앱에서는 카드사 종합 결제창을 통해 대부분의 법인카드 결제가 가능합니다.</p>
            <br/>
            <p>결제 방법</p>
            <p>- 결제수단에서 신용/체크카드 선택</p>
            <p>- 카드사별 결제창에서 해당 카드사 선택</p>
            <p>- 카드사 앱 또는 간편결제에 법인카드 등록 후 선택하여 결제</p>
            <br/>
            <p>예시</p>
            <p>- 삼성카드 → 삼성페이에 사전 등록 후 결제 가능</p>
            <p>- BC 기반 법인카드 → '페이북' 앱 설치 후 카드 등록 → 페이북으로 결제 가능</p>
            <br/>
            <p>BC 카드 이용고객의 경우 결제창 이용 방법은 아래 스크린샷을 참고해주세요.</p>
            <p>[페이북 앱 설치 및 법인카드 등록]</p>
            <br/>
            <p><strong>2. 아동급식카드 이용 안내</strong></p>
            <p>아동급식카드는 오프라인 매장 카드단말기에서만 사용 가능한 복지카드입니다.</p>
            <p>앱·배달앱을 통한 온라인 결제는 지원되지 않는 카드 정책으로 인해 딜리버리 주문에서 사용이 어렵습니다.</p>
            <p>아동급식카드 이용 고객께서는 매장 방문 후 결제를 부탁드립니다.</p>
            <br/>
            <p><strong>3. 온라인 결제가 지원되지 않는 카드</strong></p>
            <p>일부 카드(일부 복지카드·정책 제한 카드 등)는</p>
            <p>온라인 결제 자체가 지원되지 않아 딜리버리 결제수단으로 이용이 불가합니다.</p>
            <br/>
            <p><strong>자사 APP에서는 만나서 결제(현장 카드/ 현금) 이용이 불가해짐에 따라 해당 결제를 원하실 경우 콜센터 주문을 이용해 주세요.</strong></p>
            <br/>
            <p>만나서 결제를 애용하셨던 고객님들께 불편을 드린 점 양해 부탁드리며, 더 편리한 결제 환경을 제공할 수 있도록 지속적으로 개선해 나가겠습니다.</p>
            <p>감사합니다.</p>
          <!-- Mock content if API doesn't return full text for ID 2 as per images -->
          <div v-else-if="String($route.params.id) === '2'" class="mock-content">
            <p>안녕하세요, 버거킹입니다.</p>
            <p>12월 1일 자정부터 진행된 멤버십 개편 작업이 모두 정상적으로 완료되었습니다.</p>
            <br/>
            <p><strong>이번 개편을 통해</strong></p>
            <p>- 최고 등급인 '크라운(Crown)' 등급이 새롭게 추가되었으며, 이에 따른 전용 혜택도 함께 제공됩니다.</p>
            <p>- 또한, 출석 미션, 배지 모으기, 친구 초대, 이벤트 공유 등 새로운 플레임 적립 서비스가 적용되어 다양한 방식으로 플레임을 모을 수 있도록 개선했습니다.</p>
            <br/>
            <p>현재 모든 멤버십 정보와 개편된 기능들은 정상적으로 이용 가능합니다.</p>
            <br/>
            <p>개편 작업 기간 동안 보내주신 양해에 깊이 감사드립니다.</p>
            <p>버거킹은 앞으로도 고객 여러분께 더 나은 디지털 경험을 제공하기 위해 지속적으로 노력하겠습니다.</p>
            <br/>
            <p>감사합니다.</p>
          </div>
        </div>

        <div class="notice-actions">
          <router-link to="/notice" class="btn-list">목록</router-link>
        </div>
      </div>

      <div v-else-if="loading" class="loading">
        Loading...
      </div>
    </main>

    <CommonFooter />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import CommonHeader from '@/components/CommonHeader.vue';
import CommonFooter from '@/components/CommonFooter.vue';
import { getNoticeDetail } from '@/api/notice';

const route = useRoute();
const notice = ref(null);
const loading = ref(true);

const fetchDetail = async () => {
    loading.value = true;
    try {
        const response = await getNoticeDetail(route.params.id);
        if (response.data.success && response.data.response) {
            notice.value = response.data.response;
        }
    } catch (error) {
        console.error('Failed to fetch notice detail:', error);
    } finally {
        // Force mock data for ID 1 or ID 2 if not fetched or partially fetched
        if (String(route.params.id) === '1') {
            if (!notice.value) {
                notice.value = {
                    noticeId: 1,
                    title: '[공지사항]만나서 결제 종료 후 결제수단 이용 방법 안내',
                    createdAt: '2025-12-08',
                    content: ''
                };
            }
        } else if (String(route.params.id) === '2') {
            if (!notice.value) {
                notice.value = {
                    noticeId: 2,
                    title: '버거킹 멤버십 개편 작업 완료 안내',
                    createdAt: '2025-12-01',
                    content: ''
                };
            }
        }
        loading.value = false;
    }
};

const formatDate = (dateString) => {
  if (!dateString) return '';
  const date = new Date(dateString);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  return `${year}.${month}.${day}`;
};

onMounted(() => {
    fetchDetail();
});
</script>

<style scoped>
.notice-detail-page {
  background-color: #F5EBDC;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.notice-detail-container {
  flex: 1;
  max-width: 1140px;
  width: 100%;
  margin: 0 auto;
  padding: 60px 20px 100px;
}

.notice-header {
  text-align: center;
  margin-bottom: 60px;
}

.notice-category {
  font-size: 1.125rem;
  color: #502314;
  margin-bottom: 10px;
  font-weight: 700;
}

.notice-main-title {
  font-family: 'Flame', sans-serif;
  font-size: 3rem;
  font-weight: 900;
  color: #502314;
}

.notice-content-wrap {
  background-color: transparent;
}

.notice-info {
  margin-bottom: 40px;
}

.badge {
  display: inline-block;
  background-color: #D9CBB6;
  color: #502314;
  padding: 4px 10px;
  border-radius: 4px;
  font-size: 0.75rem;
  font-weight: 700;
  margin-bottom: 15px;
}

.notice-title {
  font-size: 1.5rem;
  font-weight: 900;
  color: #502314;
  margin-bottom: 10px;
}

.notice-date {
  font-size: 1rem;
  color: #8D7E73;
}

.notice-content {
  padding: 40px 0;
  border-top: 1px solid #D9CBB6;
  color: #502314;
  line-height: 1.6;
  font-size: 1rem;
}

.content-text {
  white-space: pre-wrap;
}

.mock-content strong {
    font-size: 1.125rem;
    font-weight: 900;
}

.notice-actions {
  display: flex;
  justify-content: center;
  margin-top: 60px;
}

.btn-list {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 240px;
  height: 60px;
  border: 1px solid #502314;
  border-radius: 30px;
  color: #502314;
  font-size: 1.125rem;
  font-weight: 900;
  text-decoration: none;
  transition: background-color 0.2s;
}

.btn-list:hover {
  background-color: rgba(80, 35, 20, 0.05);
}

.loading {
  text-align: center;
  padding: 100px 0;
  color: #8D7E73;
}

@media screen and (max-width: 1024px) {
  .notice-main-title {
    font-size: 2.25rem;
  }
  
  .notice-title {
    font-size: 1.25rem;
  }
}
</style>
