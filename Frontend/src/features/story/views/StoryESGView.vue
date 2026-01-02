<template>
  <div class="subWrap">
    <CommonHeader />
    
    <div class="contentsWrap">
      <div class="cont_min_area">
        <div class="web_container">
          <!-- Page Title -->
          <div class="title_page WEB">
            <h2 class="tit">ESG 경영</h2>
          </div>

          <!-- Intro Section: 4 Initiatives Index -->
          <div class="card01 info">
            <div class="detail">
              <h1 class="tit_card cen">지속 가능한 사회를 위한 버거킹의 노력</h1>
              <div class="card_cont">
                <p class="txt_detail02 cen">
                  버거킹은 친환경 캠페인에서부터 고용 창출, 농가 상생에 이르기까지<br>
                  지속가능한 사회를 위해 노력합니다.
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

          <!-- ESG Detail Sections -->
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
                <p>🥗 {{ reason.shortTitle }}</p>
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
    shortTitle: '플라스틱 빨대 사용 줄이기 캠페인',
    title: '저희 매장은 빨대 없는 뚜껑을 제공합니다.',
    description: '일부 음료에 한하여<br>빨대 제공 시, 음료당 1개의 종이 빨대를 제공합니다.',
    imageKey: 'straw'
  },
  {
    shortTitle: '지역 농가와의 상생',
    title: "대한민국 지역 농가와 '맛있는 상생'을 도모합니다!",
    description: '농가 상생 도모를 위하여 양파, 양상추, 토마토 등의 주 재료를<br>국내 농가에서 연간 각 1,000t 이상 수급 받아 사용하고 있습니다.',
    imageKey: 'farm'
  },
  {
    shortTitle: '동물복지단체 기부 활동',
    title: '세상 모든 댕댕이들의 행복을 버거킹이 응원해요!',
    description: '동물과 사람이 함께 행복한 세상을 만들기 위하여 비글 네트워크와 동행 등<br>동물 복지를 위해 힘쓰는 단체에 반려견들을 위한 독퍼 기부를 진행했습니다.',
    imageKey: 'donation'
  },
  {
    shortTitle: '일자리 창출과 고용안정 기여',
    title: '일자리 창출로 지역사회 경제 활성화에 기여합니다.',
    description: '경력 단절 장년층, 장애인, 고령근로자 등 취업 소외계층을<br>적극 채용한 공로로 2015년, 고용노동부 장관 표창을 수상한바 있습니다.',
    imageKey: 'job'
  }
];

const scrollToSection = (index) => {
  const el = sectionRefs.value[index];
  if (el) {
    el.scrollIntoView({ behavior: 'smooth' });
  }
};

const images = ref({
  straw: '',
  farm: '',
  donation: '',
  job: '',
  loading: {},
  error: {}
});

// 이미지 로드 결과 처리
const handleImageError = (key) => {
  console.error(`이미지 로드 실패 (${key}):`, images.value[key]);
  images.value.error[key] = true;
  images.value.loading[key] = false;
  
  if (!images.value[key]) {
    images.value[key] = 'https://via.placeholder.com/800x600/502314/FFFFFF?text=ESG+Campaign';
  }
};

const handleImageLoad = (key) => {
  console.log(`이미지 로드 성공 (${key}):`, images.value[key]);
  images.value.loading[key] = false;
  images.value.error[key] = false;
};

onMounted(async () => {
  try {
    console.log('이미지 API 호출 시작 - FileType: ESG');
    const response = await storyApi.getImagesByType('ESG');
    console.log('API 응답 수신:', response.data);
    
    if (response.data && response.data.data) {
      const imageList = response.data.data;
      console.log('ESG 이미지 리스트 전수 조사:', imageList.map(img => ({ name: img.originalFileName, url: img.fileUrl })));
      
      // 1. 파일명 매핑 시도
      imageList.forEach(img => {
        const fileName = img.originalFileName.toLowerCase();
        if (fileName.includes('noplastic')) images.value.straw = img.fileUrl;
        else if (fileName.includes('fresh')) images.value.farm = img.fileUrl;
        else if (fileName.includes('dog')) images.value.donation = img.fileUrl;
        else if (fileName.includes('recruit')) images.value.job = img.fileUrl;
      });

      // 2. 파일명이 매칭되지 않을 경우 인덱스 순서대로 매핑
      reasons.forEach((reason, index) => {
        const key = reason.imageKey;
        if (!images.value[key] && imageList[index]) {
          console.warn(`이미지 파일명이 규칙과 달라 인덱스 기반으로 매핑합니다: ${key} -> ${imageList[index].originalFileName}`);
          images.value[key] = imageList[index].fileUrl;
        }
      });
    } else {
      console.warn('API 응답에 데이터가 없습니다. 폴백 이미지를 준비합니다.');
      reasons.forEach(r => handleImageError(r.imageKey));
    }
  } catch (error) {
    console.error('이미지 로드 통신 실패:', error);
    reasons.forEach(r => handleImageError(r.imageKey));
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
  background-color: #f4eadc;
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
  background-color: #502314;
  border-radius: 50%;
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  cursor: pointer;
}

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
  color: #502314;
  margin-bottom: 25px;
  font-weight: 800;
}

.tit_card {
  font-size: 2.25rem;
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
}

.card_image_area img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 20px;
}

.image_placeholder {
  width: 100%;
  height: 300px;
  background-color: #eee;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #999;
  border-radius: 20px;
}

.txt_loading {
  font-size: 0.8125rem;
  opacity: 0.7;
  margin-top: 5px;
}

.card01 {
  scroll-margin-top: 120px;
  border-radius: 30px;
  margin-bottom: 40px;
}

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

.txt_detail02 {
  font-size: 1rem;
  color: #666;
  margin-top: 10px;
}
</style>
