<template>
  <div class="subWrap">
    <CommonHeader />
    
    <div class="contentsWrap">
      <div class="cont_min_area">
        <div class="web_container">
          <!-- Page Title -->
          <div class="title_page WEB">
            <h2 class="tit">Why 버거킹</h2>
          </div>

          <!-- Intro Section: 7 Reasons Index -->
          <div class="card01 info">
            <div class="detail">
              <h1 class="tit_card cen">우리가 버거의 킹인 7가지 이유</h1>
              <div class="card_cont">
                <p class="txt_detail02 cen">
                  70년 넘게 전세계 고객에게 꾸준히 사랑 받아 온<br>
                  버거킹의 특급 비법을 지금 바로 공개합니다!
                </p>
              </div>
              <ul class="scroll_index_list">
                <li v-for="(reason, index) in reasons" :key="index">
                  <div class="scroll_box" @click="scrollToSection(index)">
                    <div class="reason_text">
                      <span class="check_icon"></span>
                      <span class="txt">{{ reason.shortTitle }}</span>
                    </div>
                    <button class="btn_scroll_down">
                      <span class="arrow_icon"></span>
                    </button>
                  </div>
                </li>
              </ul>
            </div>
          </div>

          <!-- Reasons Detail Sections -->
          <div 
            v-for="(reason, index) in reasons" 
            :key="index"
            :ref="el => sectionRefs[index] = el"
            class="card01 intro_image"
          >
            <div class="cont_group">
              <div class="card_head">
                <h1 class="tit_card_sub">{{ reason.shortTitle }}</h1>
                <h2 class="tit_card">{{ reason.title }}</h2>
              </div>
              <div class="card_cont">
                <p class="txt01 txtc_light" v-html="reason.description"></p>
              </div>
            </div>
            <div class="card_image_area">
              <img 
                v-if="images[reason.imageKey]" 
                :src="images[reason.imageKey]" 
                :alt="reason.title" 
                class="MOB"
                @load="handleImageLoad(reason.imageKey)"
                @error="handleImageError(reason.imageKey)"
              >
              <img 
                v-if="images[reason.imageKey]" 
                :src="images[reason.imageKey]" 
                :alt="reason.title" 
                class="WEB"
                @load="handleImageLoad(reason.imageKey)"
                @error="handleImageError(reason.imageKey)"
              >
              <div v-if="!images[reason.imageKey] || images.loading[reason.imageKey]" class="image_placeholder">
                <p>🍔 {{ reason.shortTitle }}</p>
                <p class="txt_loading" v-if="images.loading[reason.imageKey]">이미지 로딩중...</p>
                <p class="txt_loading" v-else>이미지 준비중</p>
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

const sectionRefs = ref([]);

const reasons = [
  {
    shortTitle: '버거킹의 자랑, 와퍼!',
    title: 'Real WHOPPER',
    description: '전 세계적으로 사랑 받는 WHOPPER는 불에 직접 구운 100% 순쇠고기 패티 위에 신선한 토마토, 양상추, 양파, 마요네즈, 케첩, 상큼한 피클을 올린 버거킹의 대표 제품 입니다.<br><br>버거킹은 더 건강하고 믿을 수 있는 와퍼를 만들기 위해 끊임 없이 노력 했습니다.<br><br>와퍼의 모든 재료에 대해 향료, 색소, 보존제, 첨가제 등의 제품 성분 개선 활동을 통해 더욱 믿을 수 있고, 더욱 맛있는 리얼 와퍼가 탄생하였습니다!',
    imageKey: 'reason1'
  },
  {
    shortTitle: '불맛에 진심인 브랜드',
    title: 'FLAMED GRILLED',
    description: '버거킹은 1945년 이후로 고기를 팬에 굽는 일반 QSR과는 달리 고기를 직접 불에 구워 조리하는 직화 방식(Flame-Grilled)으로 담백하고 풍부한 맛과 향 그리고 정통 햄버거의 풍미를 선보이고 있습니다.<br><br>이 과정에서 불필요한 기름기는 제거되고 고기의 육즙은 그대로 보존되어 고기의 독특한 맛과 향을 느낄 수 있습니다.<br><br>불에 직접 구워 담백하고 풍부한 맛!<br>버거킹의 햄버거만이 드릴 수 있는 특별한 맛입니다.',
    imageKey: 'reason2'
  },
  {
    shortTitle: '신선한 원재료 사용',
    title: '패티와 야채, 소스',
    description: '100% BEEF<br>버거킹의 소고기 패티는 100% 순쇠고기로 만들어졌으며, 어떠한 방부제나 첨가물이 들어가지 않습니다.<br><br>FRESH VEGETABLES<br>버거킹의 모든 햄버거에는 엄격한 검수 과정을 거친 신선한 야채가 사용됩니다.<br>매일 매장에서 토마토를 직접 준비하여 신선함을 가치로 추구합니다.<br><br>PICKLES & MAYONNAISE<br>고소한 마요네즈와 새콤달콤한 케첩으로 정통 햄버거 특유의 풍미를 제공합니다.',
    imageKey: 'reason3'
  },
  {
    shortTitle: 'QUALITY',
    title: '엄격한 품질 관리 시스템',
    description: '엄격한 품질 관리 시스템을 운영하며 지속적인 관리를 통해 버거킹 원재료는 최상의 품질을 유지합니다.<br><br>HACCP 인증과 글로벌 식품 안전 인증인 GFSI 인증을 받은 검증된 업체만을 선정하여 원재료를 공급 받고, 원재료가 매장에 도착하면 자체적으로 유통기한 및 온도를 검사하여 기준에 미치지 못하는 제품은 반송합니다.<br><br>또한 입고 후에도 보관 제품의 신선도 유지와 변질 방지를 위해 노력하여 버거킹을 방문하는 고객들에게 최상의 맛과 품질을 제공합니다.',
    imageKey: 'reason4'
  },
  {
    shortTitle: 'SERVICE',
    title: '신속, 정확, 친절한 서비스',
    description: '정확하고 친절한 서비스를 제공하기 위해 최선을 다하고 있습니다.<br><br>신속한 주문을 위해서 고객 주문 후 2분 30초 안에 제품을 받을 수 있도록 노력하고 있으며, 또한 정확하고 친절한 서비스 유지를 위해 고객 응대 교육을 시행 중 입니다.<br><br>버거킹을 찾아 주시는 모든 고객들이 만족하실 수 있도록 버거킹이 할 수 있는 최고의 서비스, 밝은 미소와 감사하는 마음으로 고객들을 맞이하고 있습니다.',
    imageKey: 'reason5'
  },
  {
    shortTitle: 'CLEANILINESS',
    title: '철저한 위생 관리',
    description: '아무리 맛 좋은 음식이라도 위생이 먼저라는 것이 버거킹의 신념 중 하나 입니다.<br><br>버거킹의 매니저들은 미국 NRA(National Restaurant Association)에서 실시하는 SS(ServSafe) 과정을 수료하고 테스트에 합격하여 매장 내의 위생 관념이 철저 합니다.<br><br>모든 직원들은 개인 위생은 물론 제품 온도와 기계/소도구를 규정된 절차에 따라 관리함으로써 최상의 위생 상태를 유지하여 고객이 우리 제품을 안심하고 드실 수 있도록 노력하고 있습니다.',
    imageKey: 'reason6'
  },
  {
    shortTitle: '맛에 대한 신뢰',
    title: 'Trust in Taste',
    description: 'Trust in Taste란?<br>전세계 버거킹 고객들이 우리 제품의 맛에 만족감을 느낄 수 있도록 하는 활동입니다.<br>버거킹 코리아는 2024년 한국 진출 40주년을 맞이하여 와퍼 맛 개선 프로젝트를 진행하여 최상의 제품을 제공하기 위해 노력하고 있습니다.<br><br>Trust in Taste 1. 최고의 버거<br>와퍼의 맛과 품질을 지속적으로 개선하여 고객들이 최고의 맛을 느낄 수 있도록 합니다.<br>버거킹의 자산인 Flame-Grilling 방식으로 더욱 특별한 맛을 제공합니다.<br><br>Trust in Taste 2. 최고의 치킨<br>버거킹은 고객에게 가장 맛이 좋은 치킨 제품을 제공합니다.',
    imageKey: 'reason7'
  }
];

const scrollToSection = (index) => {
  const el = sectionRefs.value[index];
  if (el) {
    el.scrollIntoView({ behavior: 'smooth' });
  }
};

const images = ref({
  reason1: '',
  reason2: '',
  reason3: '',
  reason4: '',
  reason5: '',
  reason6: '',
  reason7: '',
  loading: {},
  error: {}
});

// 이미지 로드 결과 처리
const handleImageError = (key) => {
  console.error(`이미지 로드 실패 (${key}):`, images.value[key]);
  images.value.error[key] = true;
  images.value.loading[key] = false;
  
  // 폴백 이미지 설정 (예: 버거킹 로고나 메뉴 이미지)
  if (!images.value[key]) {
    images.value[key] = 'https://via.placeholder.com/800x600/502314/FFFFFF?text=Burger+King';
  }
};

const handleImageLoad = (key) => {
  console.log(`이미지 로드 성공 (${key}):`, images.value[key]);
  images.value.loading[key] = false;
  images.value.error[key] = false;
};

onMounted(async () => {
  try {
    console.log('이미지 API 호출 시작 - FileType: WHY_BURGERKING');
    const response = await storyApi.getImagesByType('WHY_BURGERKING');
    console.log('API 응답 수신:', response.data);
    
    if (response.data && response.data.data) {
      const imageList = response.data.data;
      console.log('WHY 이미지 리스트 전수 조사:', imageList.map(img => ({ name: img.originalFileName, url: img.fileUrl })));
      
      // 1. 파일명 매핑 시도
      imageList.forEach(img => {
        const fileName = img.originalFileName.toLowerCase();
        if (fileName.includes('whopper')) images.value.reason1 = img.fileUrl;
        else if (fileName.includes('grill')) images.value.reason2 = img.fileUrl;
        else if (fileName.includes('fresh')) images.value.reason3 = img.fileUrl;
        else if (fileName.includes('quality')) images.value.reason4 = img.fileUrl;
        else if (fileName.includes('service')) images.value.reason5 = img.fileUrl;
        else if (fileName.includes('cleaniliness')) images.value.reason6 = img.fileUrl;
        else if (fileName.includes('trustintaste')) images.value.reason7 = img.fileUrl;
      });

      // 2. 파일명이 매칭되지 않을 경우 인덱스 순서대로 매핑 (최대 7개)
      reasons.forEach((reason, index) => {
        const key = reason.imageKey;
        if (!images.value[key] && imageList[index]) {
          console.warn(`이미지 파일명이 규칙과 달라 인덱스 기반으로 매핑합니다: ${key} -> ${imageList[index].originalFileName}`);
          images.value[key] = imageList[index].fileUrl;
        }
      });
    } else {
      console.warn('API 응답에 데이터가 없습니다. 폴백 이미지를 준비합니다.');
      // 강제 폴백 (테스트용)
      for (let i = 1; i <= 7; i++) {
        handleImageError(`reason${i}`);
      }
    }
  } catch (error) {
    console.error('이미지 로드 통신 실패:', error);
    for (let i = 1; i <= 7; i++) {
      handleImageError(`reason${i}`);
    }
  }
});
</script>

<style scoped>
.scroll_index_list {
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  gap: 12px;
  margin-top: 40px;
  padding: 0;
  list-style: none;
}

@media screen and (min-width: 1024px) {
  .scroll_index_list {
    grid-template-columns: repeat(2, 1fr);
    gap: 15px 20px;
  }
}

.scroll_box {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 18px 25px;
  background-color: #f4eadc; /* Warm beige background */
  border-radius: 12px;
  cursor: pointer;
  transition: transform 0.2s, background-color 0.2s;
}

.scroll_box:hover {
  transform: translateY(-2px);
  background-color: #ede0cf;
}

.reason_text {
  display: flex;
  align-items: center;
  gap: 12px;
}

.check_icon {
  width: 18px;
  height: 18px;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* Red V (Check) icon using CSS */
.check_icon::before {
  content: '';
  width: 12px;
  height: 6px;
  border-left: 3px solid #E2221F;
  border-bottom: 3px solid #E2221F;
  transform: rotate(-45deg);
  margin-top: -4px;
}

.reason_text .txt {
  font-weight: 700;
  color: #502314;
  font-size: 1.125rem;
}

.btn_scroll_down {
  width: 32px;
  height: 32px;
  background-color: #502314; /* Brown circular background */
  border-radius: 50%;
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  cursor: pointer;
}

/* White Arrow Down using CSS */
.arrow_icon {
  width: 8px;
  height: 8px;
  border-right: 2px solid #fff;
  border-bottom: 2px solid #fff;
  transform: rotate(45deg);
  margin-top: -3px;
}

.tit_card_sub {
  font-size: 1.125rem;
  color: #502314; /* Brown instead of red */
  margin-bottom: 25px;
  font-weight: 800;
}

.tit_card {
  font-size: 2.25rem; /* Reduced from 3rem to fit one line */
  font-weight: 900;
  color: #502314;
  margin-bottom: 30px;
  letter-spacing: -0.02em;
}

.txtc_light {
  color: #666;
  line-height: 1.9;
  font-size: 1.0625rem;
}

.card_image_area {
  width: 100%;
  padding: 20px;
  background-color: transparent;
}

.card_image_area img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 20px; /* More rounded corners for images */
}

.image_placeholder {
  width: 100%;
  height: 300px;
  background-color: #eee;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #999;
  border-radius: 20px;
}

/* Ensure smooth scrolling works with header offset */
.card01 {
  scroll-margin-top: 120px;
  border-radius: 30px; /* Softer card corners */
  margin-bottom: 40px;
}

/* Additional layout fixes from brand.css pattern */
.web_container {
  max-width: 1200px;
  margin: 0 auto;
}

.cont_group {
  padding: 30px 20px;
}

@media screen and (min-width: 1024px) {
  .cont_group {
    padding: 40px;
  }
}

.cen {
  text-align: center;
}

.txt_loading {
  font-size: 0.8125rem;
  opacity: 0.7;
  margin-top: 5px;
}
</style>
