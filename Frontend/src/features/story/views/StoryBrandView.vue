<template>
  <div class="subWrap">
    <CommonHeader />
    
    <div class="contentsWrap">
      <div class="cont_min_area">
        <div class="web_container">
          <!-- Page Title -->
          <div class="title_page WEB">
            <h2 class="tit">브랜드</h2>
          </div>

          <!-- Burger King Global Section -->
          <div class="card01 intro_image break_web">
            <div class="cont_group">
              <div class="card_head">
                <h2 class="tit_card cen">버거킹 GLOBAL</h2>
              </div>
              <div class="card_cont">
                <p class="txt01">
                  세계 최대 QSR(Quick Service Restaurant) 브랜드 중 하나인 버거킹은 
                  <span class="txt_underline">1954년 미국 마이애미</span>에서 James W.McLamore와 David Edgerton에 의해 설립되었습니다.<br><br>
                  버거킹은 현재 100개 이상의 국가에서 19,500개 이상의 매장을 운영하고 있습니다.<br><br>
                  고기를 팬에 굽는 일반 QSR과는 달리, 
                  <span class="txt_underline">버거킹은 고기를 직접 불에 구워 조리하는 직화 방식 (Flame-grilling)으로 담백하고 풍부한 맛과 향,</span> 
                  그리고 정통 햄버거의 풍미를 선보이고 있습니다.<br><br>
                  버거킹의 대표 제품인 와퍼(Whopper)는 100% 순 쇠고기 패티와 함께 양상추, 토마토, 양파, 피클 등 4가지 신선한 야채가 어우러진 풍부한 맛으로 전세계 고객에게 사랑 받고 있습니다.
                </p>
              </div>
            </div>
            <div class="card_image_area">
              <img 
                v-if="images.pastStore" 
                :src="images.pastStore" 
                alt="Burger King Global" 
                class="MOB"
                @load="handleImageLoad('pastStore')"
                @error="handleImageError('pastStore')"
              >
              <img 
                v-if="images.pastStore" 
                :src="images.pastStore" 
                alt="Burger King Global" 
                class="WEB"
                @load="handleImageLoad('pastStore')"
                @error="handleImageError('pastStore')"
              >
            </div>
          </div>

          <!-- Burger King Korea Section -->
          <div class="card01 intro_image break_web">
            <div class="cont_group">
              <div class="card_head">
                <h2 class="tit_card cen">버거킹 KOREA</h2>
              </div>
              <div class="card_cont">
                <p class="txt01">
                  <span class="txt_underline">버거킹 코리아는 지난 1984년 종로점을 오픈</span>하며 한국에 진출한 이래, 
                  차별화된 제품과 개성 있는 매장 인테리어로 지난 39년간 한국 소비자의 사랑을 받아오고 있습니다.<br><br>
                  "가장 맛있는 햄버거를 제공하는 프리미엄 QSR 브랜드"로 자리매김한 버거킹은 현재 
                  <span class="txt_underline">전국 490개 </span>(2024년 3월 기준) 매장을 운영하고 있습니다.<br><br>
                  <span class="txt_underline">좋은 품질의 제품(Quality)과 친절한 서비스 (Service) 그리고 청결한 매장(Cleanliness)</span>은 
                  고객 여러분께 더 큰 만족을 드리고자 노력하는 버거킹의 약속이자 가치입니다.<br><br>
                  더욱 풍성하고 맛있는 제품과 정성을 다하는 고객 서비스로 여러분께 더욱 가까이 다가가도록 하겠습니다.
                </p>
              </div>
            </div>
            <div class="card_image_area">
              <img 
                v-if="images.currentStore" 
                :src="images.currentStore" 
                alt="Burger King Korea" 
                class="MOB"
                @load="handleImageLoad('currentStore')"
                @error="handleImageError('currentStore')"
              >
              <img 
                v-if="images.currentStore" 
                :src="images.currentStore" 
                alt="Burger King Korea" 
                class="WEB"
                @load="handleImageLoad('currentStore')"
                @error="handleImageError('currentStore')"
              >
            </div>
          </div>

          <!-- BKR Company Info Section -->
          <div class="info_section">
            <div class="card01 intro_image break_web">
              <div class="cont_group">
                <div class="card_head">
                  <h1 class="tit_card cen">BKR 회사 정보</h1>
                </div>
                <div class="card_cont">
                  <div class="dlist01 company_info">
                    <dl>
                      <dt>사명</dt>
                      <dd class="text_right"><span>주식회사 비케이알</span></dd>
                    </dl>
                    <dl>
                      <dt>대표자</dt>
                      <dd class="text_right"><span>이동형</span></dd>
                    </dl>
                    <dl>
                      <dt>설립</dt>
                      <dd class="text_right"><span>2012.11.21</span></dd>
                    </dl>
                    <dl>
                      <dt>사업내용</dt>
                      <dd class="text_right"><span>피자, 햄버거, 샌드위치 및 유사 음식점업</span></dd>
                    </dl>
                    <dl>
                      <dt>전화번호</dt>
                      <dd class="text_right">
                        <span>080-022-8163</span>
                        <p class="txt_info">
                          상담 가능 시간 : 평일 오전 9시 ~ 오후 6시<br>
                          (토,일,공휴일 휴무)
                        </p>
                      </dd>
                    </dl>
                    <dl>
                      <dt>소재지</dt>
                      <dd class="text_right"><span>서울시 종로구 삼봉로 71 G타워 4, 5층</span></dd>
                    </dl>
                  </div>
                </div>
              </div>
              <div class="card_image_area map_wrap">
                <div class="map_w">
                  <img 
                    v-if="images.map" 
                    :src="images.map" 
                    alt="BKR 회사 위치" 
                    class="map_image"
                    @load="handleImageLoad('map')"
                    @error="handleImageError('map')"
                  >
                  <div v-else class="map_placeholder">
                    <p>📍 서울시 종로구 삼봉로 71 G타워</p>
                    <p class="map_note">지도 이미지 로딩중...</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <CommonFooter />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import CommonHeader from '@/components/CommonHeader.vue';
import CommonFooter from '@/components/CommonFooter.vue';
import storyApi from '@/api/story';
import '@/assets/brand.css';

// 이미지 URL 및 로딩 상태
const images = ref({
  pastStore: '',
  currentStore: '',
  map: '',
  loading: {
    pastStore: true,
    currentStore: true,
    map: true
  },
  error: {
    pastStore: false,
    currentStore: false,
    map: false
  }
});

// 이미지 로드 결과 처리
const handleImageError = (key) => {
  console.error(`이미지 로드 실패 (${key}):`, images.value[key]);
  images.value.error[key] = true;
  images.value.loading[key] = false;
  
  // 폴백 이미지 설정
  if (key === 'pastStore') {
    images.value.pastStore = 'https://via.placeholder.com/1200x500/502314/FFFFFF?text=Burger+King+Global+Fallback';
  } else if (key === 'currentStore') {
    images.value.currentStore = 'https://via.placeholder.com/1200x500/E2221F/FFFFFF?text=Burger+King+Korea+Fallback';
  }
};

const handleImageLoad = (key) => {
  console.log(`이미지 로드 성공 (${key}):`, images.value[key]);
  images.value.loading[key] = false;
  images.value.error[key] = false;
};

// 이미지 로드
onMounted(async () => {
  try {
    console.log('이미지 API 호출 시작 - FileType: BRAND');
    const response = await storyApi.getImagesByType('BRAND');
    console.log('API 응답 수신:', response.data);
    
    if (response.data && response.data.data) {
      const imageList = response.data.data;
      console.log('브랜드 이미지 리스트:', imageList.map(img => img.originalFileName));
      
      // 파일명으로 이미지 매핑
      imageList.forEach(img => {
        if (img.originalFileName === 'pastStore.png') {
          images.value.pastStore = img.fileUrl;
          console.log('pastStore URL 설정됨');
        } else if (img.originalFileName === 'currentStore.png') {
          images.value.currentStore = img.fileUrl;
          console.log('currentStore URL 설정됨');
        } else if (img.originalFileName === 'map.png') {
          images.value.map = img.fileUrl;
          console.log('map URL 설정됨');
        }
      });
    } else {
      console.warn('API 응답에 이미지 데이터가 없습니다.');
    }
  } catch (error) {
    console.error('브랜드 이미지 API 호출 실패:', error);
    // 폴백 이미지 설정
    handleImageError('pastStore');
    handleImageError('currentStore');
    handleImageError('map');
  }
});
</script>

<style scoped>
/* Global Box Sizing */
* { box-sizing: border-box; }

/* CSS Reset */
dd, dl, dt, em, figure, h1, h2, h3, h4, h5, h6, li, ol, p, ul {
  margin: 0;
  padding: 0;
}

/* SubWrap */
.subWrap {
  min-height: 100%;
  display: flex;
  flex-direction: column;
  background-color: var(--bg-base);
  overflow: auto;
}

/* Contents Wrapper */
.contentsWrap {
  position: relative;
  flex: 1;
  overflow: auto;
  padding: 20px 20px 0;
}

.cont_min_area {
  min-height: calc(100vh - 166px);
}

@media screen and (min-width: 640px) {
  .contentsWrap {
    padding: 30px 30px 0;
  }
}

@media screen and (min-width: 1024px) {
  .contentsWrap {
    padding: 40px 60px 0;
    overflow: initial;
  }
  
  .cont_min_area {
    min-height: calc(100vh - 444px);
  }
}

/* Web Container */
.web_container {
  max-width: 1200px;
  margin: 0 auto;
}

/* Page Title */
.title_page {
  margin-bottom: 30px;
  font-size: 1.1875rem;
  text-align: center;
}

.title_page .tit {
  color: #502314;
  font-weight: 900;
}

@media screen and (min-width: 1024px) {
  .title_page {
    margin-top: 40px;
    margin-bottom: 50px;
    font-size: 2.125rem;
  }
}

/* Card Styles */
.card01 {
  background: #fff;
  border-radius: 12px;
  overflow: hidden;
  margin-bottom: 30px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.intro_image {
  display: flex;
  flex-direction: column;
}

@media screen and (min-width: 1024px) {
  .intro_image.break_web {
    flex-direction: row;
  }
  
  .intro_image.break_web .cont_group {
    flex: 1;
    padding: 40px;
  }
  
  .intro_image.break_web .card_image_area {
    flex: 1;
  }
}

.cont_group {
  padding: 30px 20px;
}

.card_head {
  margin-bottom: 20px;
}

.tit_card {
  font-size: 1.5rem;
  font-weight: 900;
  color: #502314;
}

.tit_card.cen {
  text-align: center;
}

@media screen and (min-width: 1024px) {
  .tit_card {
    font-size: 2rem;
  }
}

.card_cont {
  margin-top: 20px;
}

.txt01 {
  font-size: 0.9375rem;
  line-height: 1.8;
  color: #333;
}

@media screen and (min-width: 1024px) {
  .txt01 {
    font-size: 1.0625rem;
  }
}

.txt_underline {
  text-decoration: underline;
  text-decoration-color: #E2221F;
  text-decoration-thickness: 2px;
  text-underline-offset: 3px;
  font-weight: 600;
}

/* Card Image Area */
.card_image_area {
  width: 100%;
  min-height: 250px;
}

.card_image_area img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.MOB {
  display: block !important;
}

.WEB {
  display: none !important;
}

@media screen and (min-width: 1024px) {
  .MOB {
    display: none !important;
  }
  
  .WEB {
    display: block !important;
  }
}

/* Company Info Definition List */
.dlist01 {
  margin-top: 20px;
}

.dlist01 dl {
  display: flex;
  padding: 15px 0;
  border-bottom: 1px solid #e0e0e0;
}

.dlist01 dl:first-child {
  border-top: 1px solid #e0e0e0;
}

.dlist01 dt {
  flex: 0 0 120px;
  font-weight: 600;
  color: #502314;
}

.dlist01 dd {
  flex: 1;
  color: #333;
}

.dlist01 .text_right {
  text-align: right;
}

@media screen and (min-width: 1024px) {
  .dlist01 dt {
    flex: 0 0 150px;
  }
}

.txt_info {
  margin-top: 8px;
  font-size: 0.875rem;
  color: #666;
  line-height: 1.6;
}

/* Map Wrapper */
.map_wrap {
  background: #f8f9fa;
}

.map_w {
  width: 100%;
  height: 100%;
  min-height: 300px;
}

.map_placeholder {
  width: 100%;
  height: 100%;
  min-height: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  font-size: 1.125rem;
  font-weight: 600;
  text-align: center;
  padding: 20px;
}

.map_placeholder p {
  margin: 10px 0;
}

.map_note {
  font-size: 0.875rem;
  opacity: 0.8;
  font-weight: 400;
}

.map_image {
  width: 100%;
  height: 100%;
  min-height: 300px;
  object-fit: cover;
  display: block;
}

@media screen and (min-width: 1024px) {
  .map_w {
    min-height: 400px;
  }
  
  .map_placeholder {
    min-height: 400px;
  }
  
  .map_image {
    min-height: 400px;
  }
}
</style>
