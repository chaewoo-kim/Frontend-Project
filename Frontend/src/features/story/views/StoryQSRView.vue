<template>
  <div class="subWrap">
    <CommonHeader />
    
    <div class="contentsWrap">
      <div class="cont_min_area">
        <div class="web_container">
          <!-- Page Title -->
          <div class="title_page WEB">
            <h2 class="tit">SMART QSR</h2>
          </div>

          <!-- Intro Section: 3 Digital Pillars Index (Matching Image) -->
          <div class="card01 info qsr_intro_card">
            <div class="detail">
              <h1 class="tit_card cen">온, 오프라인에서<br class="MOB"> 최상의 고객 경험을 제공하는 똑똑한 QSR</h1>
              <div class="card_cont">
                <p class="txt_detail02 cen">
                  프리미엄 QSR 브랜드 버거킹은 '가장 스마트한 QSR 브랜드'를 목표로<br>
                  주문과 결제, 멤버십 프로그램 등 다양한 고객 혜택 제공을 위한<br>
                  디지털 채널 경쟁력을 바탕으로 한국 진출 후 꾸준히 성장하고 있습니다.
                </p>
              </div>
              <ul class="scroll_index_list">
                <li v-for="(pillar, index) in pillars" :key="index">
                  <div class="scroll_box" @click="scrollToSection(index)">
                    <div class="pillar_text">
                      <span class="check_icon"></span>
                      <span class="txt">{{ pillar.shortTitle }}</span>
                    </div>
                    <button class="btn_scroll_down">
                      <span class="arrow_icon"></span>
                    </button>
                  </div>
                </li>
              </ul>
            </div>
          </div>

          <!-- Pillars Detail Sections -->
          <div 
            v-for="(pillar, index) in pillars" 
            :key="index"
            :ref="el => sectionRefs[index] = el"
            class="card01 intro_image"
          >
            <div class="cont_group">
              <div class="card_head">
                <h1 class="tit_card_sub">{{ pillar.subTitle }}</h1>
                <h2 class="tit_card">{{ pillar.title }}</h2>
              </div>
              <div class="card_cont">
                <p class="txt01 txtc_light" v-html="pillar.description"></p>
              </div>
            </div>
            <div class="card_image_area">
              <img 
                v-if="images[pillar.imageKey]" 
                :src="images[pillar.imageKey]" 
                :alt="pillar.title" 
                @error="handleImageError(pillar.imageKey)"
              >
              <div v-else class="image_placeholder">
                <p>🍔 {{ pillar.shortTitle }}</p>
                <p class="txt_loading">이미지 로딩 중...</p>
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

const pillars = [
  {
    shortTitle: '모바일 오더 UX 강화',
    subTitle: '빠르고 편리한 주문 경험',
    title: '더욱 스마트해진 킹오더 & 딜리버리',
    description: '버거킹 앱 리뉴얼을 통해 더 빠르고 편리한 모바일 주문 서비스를 제공합니다. 주문부터 결제, 픽업까지 물 흐르듯 이어지는 최적의 동선과 취향에 맞는 재료 커스터마이징 기능으로 나만의 와퍼를 더 간편하게 만나보세요.',
    imageKey: 'mobile'
  },
  {
    shortTitle: '맞춤화된 멤버십 혜택 제공',
    subTitle: '풍성한 멤버십 프로그램',
    title: '고객님의 취향을 저격하는 개인화 혜택',
    description: "새롭게 개편된 5단계 멤버십 등급 체계와 '마이픽' 쿠폰 시스템으로 나에게 꼭 필요한 혜택을 직접 선택할 수 있습니다. 앱 출석과 미션으로 얻는 '플레임'을 모아 버거킹만이 드릴 수 있는 특별한 즐거움을 누려보세요.",
    imageKey: 'membership'
  },
  {
    shortTitle: '전세계 최상위 수준의 매장 키오스크 보유 수',
    subTitle: '앞서가는 매장 시스템',
    title: '기술로 실현하는 대기 없는 매장',
    description: '버거킹은 전국 매장에 최첨단 키오스크를 대규모로 도입하여 주문 효율성을 극대화했습니다. 사용자 친화적인 인터페이스로 누구나 쉽고 빠르게 주문할 수 있으며, 디지털 기술을 통해 고객님의 소중한 시간을 절약해 드립니다.',
    imageKey: 'kiosk'
  }
];

const images = ref({
  mobile: '',
  membership: '',
  kiosk: ''
});

const scrollToSection = (index) => {
  const el = sectionRefs.value[index];
  if (el) {
    el.scrollIntoView({ behavior: 'smooth' });
  }
};

const handleImageError = (key) => {
  images.value[key] = 'https://via.placeholder.com/800x600/502314/FFFFFF?text=Smart+QSR';
};

onMounted(async () => {
  try {
    const response = await storyApi.getImagesByType('SMART_QSR');
    if (response.data && response.data.data) {
      const imageList = response.data.data;
      
      // Mapping logic: prioritized filenames as requested by user
      imageList.forEach(img => {
        const fileName = img.originalFileName.toLowerCase();
        if (fileName.includes('mobile')) images.value.mobile = img.fileUrl;
        else if (fileName.includes('membership')) images.value.membership = img.fileUrl;
        else if (fileName.includes('kiosk')) images.value.kiosk = img.fileUrl;
        // Fallback for previous naming conventions if necessary
        else if (fileName.includes('service') && !images.value.mobile) images.value.mobile = img.fileUrl;
        else if (fileName.includes('value') && !images.value.membership) images.value.membership = img.fileUrl;
        else if (fileName.includes('quality') && !images.value.kiosk) images.value.kiosk = img.fileUrl;
      });

      // Index fallback
      if (!images.value.mobile && imageList[0]) images.value.mobile = imageList[0].fileUrl;
      if (!images.value.membership && imageList[1]) images.value.membership = imageList[1].fileUrl;
      if (!images.value.kiosk && imageList[2]) images.value.kiosk = imageList[2].fileUrl;
    }
    
    // Final fallback
    pillars.forEach(p => {
      if (!images.value[p.imageKey]) handleImageError(p.imageKey);
    });
  } catch (error) {
    console.error('QSR 이미지 로드 실패:', error);
    pillars.forEach(p => handleImageError(p.imageKey));
  }
});
</script>

<style scoped>
.qsr_intro_card {
  padding-bottom: 60px !important;
}

.pillar_text {
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

.pillar_text .txt {
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
  height: 100%;
}

.tit_card_sub {
  font-size: 1.125rem;
  color: #000; /* Changed from red to black as requested */
  margin-bottom: 15px;
  font-weight: 800;
}

.tit_card {
  font-size: 2rem;
  font-weight: 900;
  color: #502314;
  line-height: 1.3;
}

.txtc_light {
  color: #666;
  line-height: 1.8;
  font-size: 1.0625rem;
}

.card_image_area {
  width: 100%;
  border-radius: 0 0 30px 30px;
  overflow: hidden;
}

.card_image_area img {
  width: 100%;
  display: block;
}

.image_placeholder {
  height: 300px;
  background: #eee;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #999;
}

.cen { text-align: center; }

@media screen and (max-width: 640px) {
  .tit_card { font-size: 1.5rem; }
  .scroll_box { padding: 15px; }
}
</style>
