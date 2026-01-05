<template>
  <div class="mainWrap">
    <CommonHeader />
    <section class="contentsWrap">
      <div class="cont_min_area">
        <div class="web_container">
          <!-- 상단 이벤트 스와이퍼 -->
          <!-- 상단 이벤트 스와이퍼 -->
          <div class="home_notice">
            <div class="top_banner">
              <swiper
                v-if="eventImages.length > 0"
                :modules="[Autoplay, Pagination]"
                :slides-per-view="1"
                :loop="true"
                :autoplay="{ delay: 5000, disableOnInteraction: false }"
                :pagination="{ clickable: true }"
                class="mainSwiper"
              >
                <swiper-slide
                  v-for="banner in eventImages"
                  :key="banner.fileUrl"
                  style="cursor: pointer"
                  @click="handleLink('/notice/event/ongoing')"
                >
                  <div class="image_area">
                    <img :src="banner.fileUrl" :alt="banner.originalFileName">
                  </div>
                </swiper-slide>
              </swiper>
            </div>

            <div class="top_cont">
              <!-- 앱 다운로드 카드 -->
              <div class="card01 home_app_down_card">
                <h2 class="tit">앱 다운로드 받고 <br>할인쿠폰 활용하기!</h2>
                <div class="down_store">
                  <div class="QR WEB">
                    <img src="@/assets/img/img_QR.svg" alt="App download">
                  </div>
                  <button type="button" class="btn_down" @click="handleExternalLink('https://play.google.com/store/apps/details?id=kr.co.burgerkinghybrid')">
                    <img src="@/assets/img/btn_google_down.svg" alt="Google Play에서 다운로드">
                  </button>
                  <button type="button" class="btn_down" @click="handleExternalLink('https://apps.apple.com/kr/app/1017567032')">
                    <img src="@/assets/img/btn_apple_down.svg" alt="App Store에서 다운로드">
                  </button>
                </div>
              </div>
              <!-- 매장 찾기 카드 -->
              <div class="card01 home_search">
                <h2 class="tit">내 주변 버거킹 매장 찾기</h2>
                <div class="store_icon">
                  <img src="@/assets/img/img_store_finding.png" alt="Store Icon">
                </div>

                <div class="search_bar_mock">
                   <span>지역, 매장명을 입력해 주세요.</span>
                   <svg class="ico_search" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                     <path d="M11 19C15.4183 19 19 15.4183 19 11C19 6.58172 15.4183 3 11 3C6.58172 3 3 6.58172 3 11C3 15.4183 6.58172 19 11 19Z" stroke="#3d2117" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                     <path d="M21 21L16.65 16.65" stroke="#3d2117" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                   </svg>
                </div>
                <!-- Overlay Button -->
                <button type="button" class="btn_detail" @click="handleLink('/store')">
                  <span>매장 찾기 화면으로 이동</span>
                </button>
              </div>
            </div>
          </div>

          <!-- 광고 영상 섹션 -->
          <div class="home_cont">
            <div class="web_container">
              <div class="title optWrap">
                <h2 class="tit_home">광고영상</h2>
                <div class="option">
                  <button type="button" class="btn_more" @click="handleLink('/story/ad')">
                    <span>더보기</span>
                  </button>
                </div>
              </div>
              <div class="media_swiper">
                <div class="video_list">
                  <div
                    v-for="video in storyAdVideos"
                    :key="video.id"
                    class="video_item"
                    @click="handleLink('/story/ad')"
                  >
                    <div class="image_area image_size01">
                      <img :src="video.fileUrl" :alt="video.title">
                      <button type="button" class="btn_play"><span>video play</span></button>
                    </div>
                    <div class="tit"><span>{{ video.title }}</span></div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- 메뉴, 매장, Why 섹션 -->
          <div class="home_cont">
            <div class="title">
              <h2 class="tit_home">메뉴와 매장소개, Why 버거킹</h2>
            </div>
            <div class="home_intro">
              <ul>
                <li>
                  <div class="card01">
                    <div class="card_info">
                      <div class="txt_chip01"><span>메뉴소개</span></div>
                    </div>
                    <h3 class="tit_card"><span>버거킹만이 드릴 수 있는 특별한 맛을 소개합니다</span></h3>
                    <div class="card_cont">
                      <div class="r_btn">
                        <button type="button" class="btn_move" @click="handleLink('/menu')">
                          <span>메뉴 둘러보기</span>
                          <img src="@/assets/img/ico_bt_link.svg" alt="" class="ico_link">
                        </button>
                      </div>
                    </div>
                    <div class="image_area image_size01">
                      <img src="https://mob-prd.burgerking.co.kr/images/story/main/2025/01/20/fb8997c7-170d-484c-a48b-fc5e1abe97ba.png" alt="Menu Intro">
                    </div>
                  </div>
                </li>
                <li>
                  <div class="card01">
                    <div class="card_info"><span class="txt_chip01">매장찾기</span></div>
                    <h3 class="tit_card"><span>가장 맛있는 버거를 제공하는 프리미엄 QSR 브랜드 매장</span></h3>
                    <div class="card_cont">
                      <div class="r_btn">
                        <button type="button" class="btn_move" @click="handleLink('/store')">
                          <span>가까운 매장 찾아보기</span>
                          <img src="@/assets/img/ico_bt_link.svg" alt="" class="ico_link">
                        </button>
                      </div>
                    </div>
                    <div class="image_area"><img src="https://mob-prd.burgerking.co.kr/images/story/main/2025/01/20/ec0239ed-d960-4609-b232-1de08b62a08d.png" alt="Store"></div>
                  </div>
                </li>
                <li>
                  <div class="card01">
                    <h3 class="tit_card" style="text-align: center; margin-bottom: 20px;">맛에 대한 신뢰<br>Trust in Taste</h3>
                    <div class="card_cont" style="display: flex; justify-content: flex-end; padding-bottom: 0;">
                      <div class="r_btn">
                        <button type="button" class="btn_move" @click="handleLink('/story/why')" style="gap: 5px;">
                          <span>Why 버거킹</span>
                          <img src="@/assets/img/ico_bt_link.svg" alt="" class="ico_link">
                        </button>
                      </div>
                    </div>
                    <div class="image_area image_size01" style="margin-top: auto;">
                      <img src="https://mob-prd.burgerking.co.kr/images/story/web/2025/01/11/c2a57504-636d-49b9-a7c4-5bb1dda82ac8.png" alt="Trust in Taste">
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>

          <!-- 주문 배너 -->
          <div class="home_cont">
            <div class="web_container">
              <div class="delivery_banner" @click="handleExternalLink(mainData.links?.order)">
                <div class="title_area">
                  <h2 class="tit_home">집에서 만나는 버거킹</h2>
                  <p>딜리버리 전화주문 안내 09:00 - 19:00 / 연중무휴<br><span class="txt_sub">(매장별로 상이할 수 있습니다.)</span></p>
                </div>
                <div class="tel_area">
                  <div class="tel">
                    <img src="@/assets/img/img_delivery_tel.png" alt="1599-0505">
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- 브랜드 스토리 섹션 -->
          <div class="home_cont">
            <div class="web_container">
              <div class="title"><h2 class="tit_home">고객과 함께 성장하는 버거킹</h2></div>
              <div class="brand_cont">
                <div class="brand01">
                  <div class="card01" @click="handleLink('/story/brand')">
                    <div class="card_head"><span class="txt_chip01">브랜드</span></div>
                    <div class="card_cont"><h3 class="tit_card">Since 1954, 전 세계 고객에게 사랑받는 글로벌 브랜드 버거킹!</h3></div>
                  </div>
                  <div class="card01" @click="handleLink('/story/esg')">
                    <div class="card_head"><span class="txt_chip01">ESG 경영</span></div>
                    <div class="card_cont"><h3 class="tit_card">지속 가능한 사회를 위한 버거킹의 노력</h3></div>
                  </div>
                </div>
                <div class="card01" @click="handleLink('/story/qsr')">
                  <div class="card_head"><span class="txt_chip01">SMART QSR</span></div>
                  <div class="card_cont"><h3 class="tit_card">온, 오프라인에서 최상의 고객 경험을 제공하는 똑똑한 QSR</h3></div>
                  <div class="image_area"><img src="https://mob-prd.burgerking.co.kr/images/story/main/2025/01/20/a16a3f0c-612a-4e80-a687-1b9a37a03df8.png" alt="Smart QSR"></div>
                </div>
                <div class="card01" @click="handleLink('/story/awards')">
                  <div class="card_head"><span class="txt_chip01">수상실적</span></div>
                  <div class="card_cont"><h3 class="tit_card">1984년 한국 진출 이후 열심히 성장해온 버거킹 코리아!</h3></div>
                  <div class="image_area"><img src="https://mob-prd.burgerking.co.kr/images/story/main/2025/01/20/a754388d-be92-42c9-b8f5-429e1a222021.png" alt="Awards"></div>
                </div>
              </div>
            </div>
          </div>

          <!-- 하단 대형 앱 다운로드 배너 -->
          <div class="home_cont app_down_card WEB">
            <div class="web_container">
              <div class="app_downWrap">
                <div class="phone_ui">
                  <div class="wrap">
                    <ul class="image_list">
                      <li class="an01"><img src="@/assets/img/img_home_phone_cont01.png" alt="Phone UI"></li>
                      <li class="an02"><img src="@/assets/img/img_home_phone_cont02.png" alt="Phone UI"></li>
                      <li class="an03"><img src="@/assets/img/img_home_phone_cont03.png" alt="Phone UI"></li>
                    </ul>
                  </div>
                </div>
                <div class="cont">
                  <h2 class="tit">
                    <span class="txt_red">오직 버거킹 앱에서만 가능한 혜택</span>
                    <span class="txt_brown">지금 앱 다운로드 받으시고 혜택을 누리세요!</span>
                  </h2>
                  <div class="benefit_list">
                    <strong>픽업오더</strong>
                    <strong>딜리버리 오더</strong>
                    <strong>멤버십 적립</strong>
                    <strong>할인쿠폰</strong>
                  </div>
                  <div class="app_down">

                    <div class="btn_area">
                      <button type="button" class="btn_app image_btn" @click="handleExternalLink('https://play.google.com/store/apps/details?id=kr.co.burgerkinghybrid')">
                        <img src="@/assets/img/btn_down_aos.svg" alt="Google Play에서 다운로드">
                      </button>
                      <button type="button" class="btn_app image_btn" @click="handleExternalLink('https://apps.apple.com/kr/app/1017567032')">
                        <img src="@/assets/img/btn_down_ios.svg" alt="App Store에서 다운로드">
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <CommonFooter />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import CommonHeader from '@/components/CommonHeader.vue';
import CommonFooter from '@/components/CommonFooter.vue';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { Autoplay, Pagination } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/pagination';
import mainApi from '@/api/main';
import storyApi from '@/api/story';

const router = useRouter();
const mainData = ref({
  eventBanners: [],
  adVideos: [],
  whyBkSlides: [],
  links: {}
});
const eventImages = ref([]);
const storyAdVideos = ref([
  { title: '할라피뇨 파퍼 - 1편', filename: 'popper1.jpg', fileUrl: '' },
  { title: '할라피뇨 파퍼 - 2편', filename: 'popper2.jpg', fileUrl: '' },
  { title: '25년 6월 오리지널스 출시', filename: 'originals.png', fileUrl: '' }
]);

const fetchMainData = async () => {
  try {
    const res = await mainApi.getMainPageData();
    if (res.data.success) {
      mainData.value = res.data.data;
    }
  } catch (error) {
    console.error('Failed to fetch main page data:', error);
  }
};

const fetchEventImages = async () => {
  try {
    const res = await mainApi.getEventImages();
    if (res.data.success) {
      eventImages.value = res.data.data;
    }
  } catch (error) {
    console.error('Failed to fetch event images:', error);
  }
};

const fetchStoryAdVideos = async () => {
  try {
    const res = await storyApi.getImagesByType('AD_VIDEO');
    if (res.data.success) {
      const apiImages = res.data.data;
      storyAdVideos.value.forEach(video => {
        const matchingImage = apiImages.find(img => img.originalFileName === video.filename);
        if (matchingImage) {
          video.fileUrl = matchingImage.fileUrl;
        }
      });
    }
  } catch (error) {
    console.error('Failed to fetch story ad videos:', error);
  }
};

const handleLink = (path) => {
  if (path) router.push(path);
};

const handleExternalLink = (url) => {
  if (url) {
      if (url.startsWith('http')) {
          window.open(url, '_blank');
      } else {
          window.location.href = url;
      }
  }
};

onMounted(() => {
  fetchMainData();
  fetchEventImages();
  fetchStoryAdVideos();
});
</script>

<style scoped>
/* Provided CSS integration */
:root {
  --bg-base: #f5ebdc;
  --bg-light: #fffcf8;
  --normal: #502314;
  --primary: #d62300;
  --font-BKR: "BKR", "Sandoll GothicNeoRound", "Pretendard", sans-serif;
}

.mainWrap {
  background-color: var(--bg-base);
  min-height: 100vh;
}

.web_container {
  max-width: 1500px;
  margin: 0 auto;
  padding: 0 20px;
}

/* Home Notice Section */
.home_notice {
  margin-bottom: 30px;
}

@media screen and (min-width: 640px) {
    .home_notice {
        margin-bottom: 40px;
    }
}

@media screen and (min-width: 1024px) {
    .home_notice {
        display: flex;
        margin-bottom: 50px;
        gap: 20px;
    }
    .top_cont {
        margin-top: 13px;
    }
}

.top_banner {
  flex: 2;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 10px 30px rgba(0,0,0,0.1);
  margin-top: 25px;
}

/* Swiper Slide Styles */
.mainSwiper :deep(.swiper-slide) {
  height: auto;
  overflow: hidden;
  flex-shrink: 0;
  width: 100%;
  position: relative;
  transition-property: transform;
  display: block;
  box-sizing: border-box;
}

.mainSwiper .image_area {
  width: 100%;
  height: 100%;
}

.mainSwiper .image_area img {
  width: 100%;
  height: 100%;
  display: block;
  object-fit: cover;
}

/* Swiper Pagination Customization */
.mainSwiper :deep(.swiper-pagination-bullet) {
  background: #ccc;
  opacity: 1;
}

.mainSwiper :deep(.swiper-pagination-bullet-active) {
  background: white;
}

.top_cont {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.card01 {
  margin: 10px 0;
  padding: 20px;
  background: var(--bg-light);
  border-radius: 10px;
  position: relative;
  overflow: hidden;
  box-shadow: 0 4px 15px rgba(0,0,0,0.05); /* Keeping shadow as it looks nice, though not explicitly in snippet it was there before */
  transition: transform 0.3s;
  cursor: default;
}

@media screen and (min-width: 640px) {
  .card01 {
    padding: 30px;
    border-radius: 20px;
  }
}

@media screen and (min-width: 1024px) {
  .card01 {
    padding: 40px;
    border-radius: 20px;
  }
}

.tit {
  font-size: 1.5rem;
  font-weight: 900;
  color: var(--normal);
  line-height: 1.3;
}

@media screen and (min-width: 640px) {
  .top_cont .card01 .tit {
    display: flex;
    align-items: center;
    min-height: 2.5em;
    justify-content: center;
  }
}

@media screen and (min-width: 1500px) {
  .top_cont .card01 .tit {
    min-height: auto;
  }
}

.down_store {
  display: flex;
  margin-top: auto;
}

.home_app_down_card {
  padding: 20px;
  background-color: var(--bg-light);
  border-radius: 20px;
  /* background-image url missing in local assets, keeping color */
}

.btn_down {
  flex: 1;
  height: 38px;
  padding: 2px 5px 1px;
  background: #f1e7db;
  border-radius: 50px;
  border: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}

.menu_tit {
    margin-bottom: 20px;
}

@media screen and (min-width: 640px) {
  .home_app_down_card {
    padding: 30px;
  }

  .down_store {
    display: block;
  }

  .btn_down {
    width: 150px;
    height: 42px;
    margin: 17px 5px 0;
    display: inline-flex;
  }
}

@media screen and (min-width: 1024px) {
  .home_app_down_card {
    background-position: right 18px bottom 51px;
  }

  .down_store {
    position: relative;
    width: 260px;
    display: block;
    margin: 20px auto 0;
    padding-left: 110px;
  }

  .QR {
    position: absolute;
    left: 0;
    top: 0;
    width: 100px;
    height: 100px;
    border-radius: 5px;
    overflow: hidden;
  }

  .QR img {
      width: 100%;
      height: 100%;
  }

  .down_store .btn_down:first-of-type {
    margin-top: 0;
  }
}

.top_cont .card01 {
  position: relative;
  display: flex;
  flex-direction: column;
  min-height: 180px;
  font-family: var(--font-BKR);
  font-size: 1.1875rem;
  text-align: center;
  line-height: 1.23;
}

@media screen and (min-width: 640px) {
  .top_cont .card01 {
    flex: 1;
    display: flex;
    flex-direction: column;
    height: 100%;
    min-height: 220px;
    margin-top: 0;
    padding: 20px;
    font-size: 1.1875rem;
  }
}

@media screen and (min-width: 1024px) {
  .top_cont .card01 {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
    min-height: 220px;
    margin: 12px 0 0;
    padding: 20px;
    font-size: 1.375rem;
    overflow: hidden;
  }
}

@media screen and (min-width: 1500px) {
  .top_cont .card01:not(:first-child) {
    margin: 20px 0 0;
  }

  .top_cont .card01 {
    font-size: 1.875rem;
    padding: 30px;
  }
}

.btn_detail {
  margin-top: 20px;
  border: 1px solid white;
  color: white;
  padding: 10px 20px;
  border-radius: 30px;
  font-weight: bold;
}

.home_search .btn_detail {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  margin: 0;
  text-indent: -999em;
  font-weight: 500;
  font-size: 1.0625rem;
  color: #2e2e2e; /* var(--sub) replacement */
  text-align: left;
  border: none;
  background: transparent;
  cursor: pointer;
}

.home_search {
  background-color: #502314; /* Image Background Match */
  color: white;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.home_search .tit {
  color: white;
  margin-bottom: 0 !important; /* Reset margin */
  text-shadow: 2px 2px 0px #000; /* Simple shadow for contrast */
}

.store_icon {
  margin: 15px 0;
  width: 140px; /* Adjust size based on visual */
  height: auto;
}

.store_icon img {
  width: 100%;
  height: auto;
}

.search_bar_mock {
  background: white;
  border-radius: 50px;
  padding: 12px 20px;
  width: 90%;
  max-width: 400px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #999;
  font-size: 1rem;
  font-weight: 500;
}

.ico_search {
  width: 24px;
  height: 24px;
}

@media screen and (min-width: 1500px) {
  .home_search {
    /* Resetting background image logic if simpler style preferred, or keeping it if compatible */
    background-image: none;
  }
}

/* Ads Section */
.home_cont {
  padding: 60px 0;
}

.tit_home {
  font-size: 2rem;
  font-weight: 900;
  color: var(--normal);
  margin-bottom: 30px;
}

.optWrap {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.btn_more {
  background: #502314;
  color: white;
  padding: 8px 25px;
  border-radius: 20px;
  font-weight: bold;
}

.media_swiper {
  padding: 0;
}

.video_list {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
}

@media screen and (max-width: 1024px) {
  .video_list {
    gap: 12px;
  }
}

.video_item {
  cursor: pointer;
}

.image_area.image_size01 {
  position: relative;
  width: 100%;
  aspect-ratio: 16 / 9;
  border-radius: 12px;
  overflow: hidden;
  background-color: #000;
  margin-bottom: 12px;
}

.image_area.image_size01 img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* Remove old grid styles */
/* .video_grid { ... } removed/replaced */

.video_item .image_area {
  position: relative;
  border-radius: 15px;
  overflow: hidden;
}

.btn_play {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.2);
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  cursor: pointer;
}

.btn_play span {
  text-indent: -999em;
  display: block;
}

.btn_play::after {
  content: '';
  width: 60px;
  height: 60px;
  background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 60 60'%3E%3Ccircle cx='30' cy='30' r='30' fill='rgba(0,0,0,0.5)'/%3E%3Cpath d='M25 20l15 10-15 10V20z' fill='white'/%3E%3C/svg%3E") no-repeat center;
  display: block;
}

.video_item .tit {
  font-size: 1.1rem;
  font-weight: 900;
  color: #502314;
  line-height: 1.4;
  margin-top: 0;
}

/* Intro Section */
.home_intro ul {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  list-style: none;
  padding: 0;
}

.home_intro ul li {
  display: flex;
  flex-direction: column;
  min-width: 0;
}

.home_intro ul li .card01 {
  flex: 1;
  display: flex;
  flex-direction: column;
  /* width: 100%; explicit width not strictly needed with flex column but okay */
}

.home_intro .card01 .image_area {
  margin: 20px -20px -20px;
  width: calc(100% + 40px);
  border-radius: 0 0 10px 10px;
}

@media screen and (min-width: 640px) {
  .home_intro .card01 .image_area {
    margin: 30px -30px -30px;
    width: calc(100% + 60px);
    border-radius: 0 0 20px 20px;
  }
}

@media screen and (min-width: 1024px) {
  .home_intro .card01 .image_area {
    margin: 40px -40px -40px;
    width: calc(100% + 80px);
  }
}

.txt_chip01 {
  display: inline-block;
  text-align: center;
  white-space: nowrap;
  min-width: 42px;
  padding: 5px 7px 4px;
  font-size: .625rem;
  line-height: 1.2;
  background: #faf4ed;
  border-radius: 8px;
  color: #502314;
  font-weight: 800;
}

@media screen and (min-width: 640px) {
  .txt_chip01 {
    min-width: 80px;
    height: 36px;
    padding: 10px 16px;
    font-size: .8125rem;
    line-height: 16px;
  }
}

@media screen and (min-width: 1024px) {
  .txt_chip01 {
    min-width: 84px;
    height: 36px;
    padding: 10px 16px;
    font-size: .875rem;
    line-height: 16px;
  }
}

.tit_card {
  font-family: var(--font-BKR);
  color: var(--normal);
  font-size: 1.0625rem;
  line-height: 150%;
  margin-top: 10px;
}

.home_intro .tit_card {
  font-size: 0.7rem; /* ~40% reduction from 1.0625rem */
  font-weight: 900;
}

@media screen and (min-width: 640px) {
  .tit_card {
    font-size: 1.375rem;
    line-height: 1.1;
    margin-top: 18px;
  }
  .home_intro .tit_card {
    font-size: 0.825rem; /* ~40% reduction from 1.375rem */
  }
}

@media screen and (min-width: 1024px) {
  .tit_card {
    font-size: 2.25rem;
    line-height: 1.2;
    margin-top: 18px;
    flex: 1;
  }
  .home_intro .tit_card {
    font-size: 1.35rem; /* 40% reduction from 2.25rem */
  }
}

.btn_move {
  position: relative;
  display: flex;
  align-items: center;
  gap: 10px;
  min-height: 18px;
  padding: 0 10px;
  font-size: .9375rem;
  color: var(--normal);
  font-family: var(--font-BKR);
  font-weight: 800;
  border: none;
  cursor: pointer;
}

.btn_move:hover {
  text-decoration: underline;
}

.ico_link {
  width: 18px;
  height: auto;
}

.btn_move:before {
  content: "";
  position: absolute;
  left: -5px;
  top: -5px;
  width: calc(100% + 10px);
  height: calc(100% + 10px);
}

@media screen and (min-width: 640px) {
  .btn_move {
    min-height: 24px;
    font-size: 1.0625rem;
    gap: 15px;
  }
  .ico_link {
    width: 24px;
  }
}

@media screen and (min-width: 1024px) {
  .btn_move {
    max-width: fit-content;
    height: 34px;
    padding: 0 20px;
    font-size: 1.0625rem;
    gap: 20px;
  }
  .ico_link {
    width: 24px;
  }
}

.r_btn {
  display: flex;
  justify-content: flex-end;
}

.card_cont {
  flex: 1;
  margin-top: 18px;
  position: relative;
}

/* Delivery Banner */
.delivery_banner {
  background: #502314;
  color: white;
  border-radius: 20px;
  padding: 40px 60px;
  display: flex;
  justify-content: center;
  gap: 200px;
  align-items: center;
  cursor: pointer;
}

.delivery_banner .tit_home {
  color: white;
  margin-bottom: 10px;
  font-family: var(--font-BKR); /* Or specific bold font */
  font-size: 2.5rem;
}

.delivery_banner p {
  font-size: 1rem;
  opacity: 0.8;
}

.tel_area {
  display: flex;
  align-items: center;
}

.tel strong {
  font-size: 3.5rem;
  font-family: var(--font-flame);
  color: #f2a900; /* Matching BK yellow */
}

.tel img {
  height: 56px;
  width: auto;
}

/* Brand Section */
/* Brand Section */
.brand_cont {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.brand01 {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

@media screen and (min-width: 640px) {
  .brand_cont {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
  }
}

@media screen and (min-width: 1024px) {
  .brand_cont {
    grid-template-columns: repeat(3, 1fr);
  }
}

.brand_cont .tit_card {
  font-family: "Sandoll GothicNeoRound", sans-serif;
  font-size: 1.47rem;
  font-weight: 700;
  margin-top: 10px;
  line-height: 1.2;
}

.brand_cont .card01 {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.brand01 .card01:last-child {
  margin-top: 30px;
}

.brand_cont .card01 .card_cont {
  flex: 1; /* Pushes content/image apart if needed, or just let it flow */
}

/* Full bleed image for brand section */
.brand_cont .card01 .image_area {
  margin: 20px -20px -20px;
  width: calc(100% + 40px);
  border-radius: 0 0 10px 10px;
  margin-top: auto; /* Push to bottom */
}

@media screen and (min-width: 640px) {
  .brand_cont .card01 .image_area {
    margin: 30px -30px -30px;
    width: calc(100% + 60px);
    border-radius: 0 0 20px 20px;
  }
}

@media screen and (min-width: 1024px) {
  .brand_cont .card01 .image_area {
    margin: 40px -40px -40px;
    width: calc(100% + 80px);
  }
}

/* App Down Big Card */
.app_down_card {
  background: #efe2d4; /* Much lighter skin color */
  color: white;
  border-radius: 40px;
  padding: 60px 60px 0; /* Remove bottom padding for flush phone */
  margin-bottom: 100px;
  overflow: hidden;
}

.app_downWrap {
  display: flex;
  gap: 60px;
  align-items: flex-end; /* Align bottom */
}

/* Enhancing image list to look like phone UI */
.phone_ui {
  flex: 1;
  position: relative;
  height: 400px; /* Adjust based on image aspect */
}

.phone_ui .wrap {
  width: 100%;
  height: 100%;
  position: relative;
}

.image_list {
  position: relative;
  width: 100%;
  height: 100%;
  list-style: none;
  padding: 0;
  margin: 0;
}

.image_list li {
  position: absolute;
  bottom: 0;
  transition: all 0.5s ease;
}

.image_list li img {
  display: block;
  width: 100%;
  max-width: 300px; /* Adjust size */
  border-radius: 30px; /* Phone rounded corners */
  box-shadow: 0 10px 30px rgba(0,0,0,0.2);
}

/* Stacking logic - adjusting positions to create a fanned out or layered look */
.image_list li.an01 {
  z-index: 3;
  left: 50%;
  transform: translateX(-50%);
  bottom: 0px; /* Main phone */
}

.image_list li.an02 {
  z-index: 2;
  left: 50%;
  transform: translateX(-80%) scale(0.9); /* Left behind */
  bottom: 20px;
  opacity: 0.8;
}

.image_list li.an03 {
  z-index: 1;
  left: 50%;
  transform: translateX(-20%) scale(0.9); /* Right behind */
  bottom: 20px;
  opacity: 0.8;
}

.app_downWrap .cont {
  flex: 1.5;
  padding-bottom: 60px;
}

.app_downWrap .tit {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-bottom: 30px;
  font-family: var(--font-BKR); /* Using BKR font */
  line-height: 1.3;
}

.app_downWrap .tit .txt_red {
  color: #E2221F;
  font-size: 2.5rem; /* ~40px */
  font-weight: 900;
  text-align: left;
}

.app_downWrap .tit .txt_brown {
  color: #502314;
  font-size: 1.8rem; /* ~28px */
  font-weight: 800;
  text-align: left;
}

.benefit_list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-bottom: 40px;
}

.benefit_list strong {
  background: #502314;
  color: white;
  padding: 10px 20px;
  border-radius: 10px;
  font-size: 1.1rem;
  font-weight: 700;
  border: none; /* Remove previous border if any */
}

.btn_app.image_btn {
  background: none;
  border: none;
  padding: 0;
  width: 160px;
  height: auto;
  cursor: pointer;
}

.btn_app.image_btn img {
  width: 100%;
  height: auto;
}

.app_down {
  display: flex;
  align-items: flex-end;
  gap: 20px;
}

.QR {
  width: 100px;
  height: 100px;
  background: white;
  border-radius: 10px;
  padding: 5px;
}

.QR img {
  width: 100%;
  height: 100%;
}

.btn_area {
    display: flex;
    gap: 10px;
}

@media screen and (max-width: 1024px) {
  .home_notice, .video_grid, .home_intro ul, .brand_cont, .app_downWrap {
    grid-template-columns: 1fr;
    flex-direction: column;
  }
}
</style>
