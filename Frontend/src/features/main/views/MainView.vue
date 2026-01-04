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
                  @click="handleLink('/news-event')"
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
                  <button type="button" class="btn_down" @click="handleExternalLink('https://play.google.com/store/apps/details?id=kr.co.burgerking')">
                    <img src="@/assets/img/btn_google_down.svg" alt="Google Play에서 다운로드">
                  </button>
                  <button type="button" class="btn_down" @click="handleExternalLink('https://apps.apple.com/kr/app/id1095254627')">
                    <img src="@/assets/img/btn_apple_down.svg" alt="App Store에서 다운로드">
                  </button>
                </div>
              </div>
              <!-- 매장 찾기 카드 -->
              <div class="card01 home_search">
                <h2 class="tit">내 주변 버거킹 매장 찾기</h2>
                <div class="store_icon">
                  <img src="@/assets/img/ico_tit_store_w.svg" alt="Store Icon">
                </div>
                <div class="search_bar_mock">
                   <span>지역, 매장명을 입력해 주세요.</span>
                   <svg class="ico_search" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                     <path d="M11 19C15.4183 19 19 15.4183 19 11C19 6.58172 15.4183 3 11 3C6.58172 3 3 6.58172 3 11C3 15.4183 6.58172 19 11 19Z" stroke="#3d2117" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                     <path d="M21 21L16.65 16.65" stroke="#3d2117" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                   </svg>
                </div>
                <!-- Overlay Button -->
                <button type="button" class="btn_detail" @click="handleLink('/store/search')">
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
                    @click="handleExternalLink(video.fileUrl)"
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
                    <div class="card_info"><div class="txt_chip01"><span>메뉴소개</span></div></div>
                    <h3 class="tit_card"><span>버거킹만이 드릴 수 있는 특별한 맛을 소개합니다</span></h3>
                    <div class="card_cont">
                      <div class="r_btn">
                        <button type="button" class="btn_move" @click="handleLink(mainData.links?.menu)">
                          <span>메뉴 둘러보기</span>
                        </button>
                      </div>
                    </div>
                    <div class="image_area"><img src="https://mob-prd.burgerking.co.kr/images/story/main/2025/01/20/fb8997c7-170d-484c-a48b-fc5e1abe97ba.png" alt="Menu"></div>
                  </div>
                </li>
                <li>
                  <div class="card01">
                    <div class="card_info"><span class="txt_chip01">매장찾기</span></div>
                    <h3 class="tit_card"><span>가장 맛있는 버거를 제공하는 프리미엄 QSR 브랜드 매장</span></h3>
                    <div class="card_cont">
                      <div class="r_btn">
                        <button type="button" class="btn_move" @click="handleLink(mainData.links?.store)">
                          <span>가까운 매장 찾아보기</span>
                        </button>
                      </div>
                    </div>
                    <div class="image_area"><img src="https://mob-prd.burgerking.co.kr/images/story/main/2025/01/20/ec0239ed-d960-4609-b232-1de08b62a08d.png" alt="Store"></div>
                  </div>
                </li>
                <li>
                  <div class="card01 pd0">
                    <swiper
                      :modules="[Autoplay, Pagination]"
                      :slides-per-view="1"
                      :loop="true"
                      :autoplay="{ delay: 4000 }"
                      :pagination="{ clickable: true }"
                    >
                      <swiper-slide v-for="slide in mainData.whyBkSlides" :key="slide.id">
                        <div class="card_cont">
                          <h3 class="tit_card" v-html="slide.title + '<br>' + slide.description"></h3>
                          <div class="r_btn">
                            <button type="button" class="btn_move" @click="handleLink(mainData.links?.whyBk)">
                              <span>Why 버거킹</span>
                            </button>
                          </div>
                        </div>
                        <div class="image_area"><img :src="slide.imageUrl" :alt="slide.title"></div>
                      </swiper-slide>
                    </swiper>
                  </div>
                </li>
              </ul>
            </div>
          </div>

          <!-- 주문 배너 -->
          <div class="home_cont">
            <div class="web_container">
              <div class="delivery_banner" @click="handleExternalLink(mainData.links?.order)">
                <div class="cont">
                  <div class="title">
                    <h2 class="tit_home">집에서 만나는 버거킹</h2>
                    <p>딜리버리 전화주문 안내 09:00 - 19:00 / 연중무휴<br><span class="txt_sub">(매장별로 상이할 수 있습니다.)</span></p>
                  </div>
                  <div class="tel"><strong>1599 - 0505</strong></div>
                </div>
                <button type="button" class="btn_detail"><span>detail</span></button>
              </div>
            </div>
          </div>

          <!-- 브랜드 스토리 섹션 -->
          <div class="home_cont">
            <div class="web_container">
              <div class="title"><h2 class="tit_home">고객과 함께 성장하는 버거킹</h2></div>
              <div class="brand_cont">
                <div class="brand01">
                  <div class="card01" @click="handleLink(mainData.links?.brand)">
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
                  <h2 class="tit"><strong><em>오직 버거킹 앱에서</em>만 가능한 <em>혜택</em> <br>지금 앱 다운로드 받으시고 혜택을 누리세요!</strong></h2>
                  <div class="benefit_list"><strong>픽업오더</strong><strong>딜리버리 오더</strong><strong>멤버십 적립</strong><strong>할인쿠폰</strong></div>
                  <div class="app_down">
                    <div class="QR"></div>
                    <div class="btn_area">
                      <button type="button" class="btn_app aos" @click="handleExternalLink(mainData.links?.playStore)"><span>Google Play에서 다운로드</span></button>
                      <button type="button" class="btn_app ios" @click="handleExternalLink(mainData.links?.appStore)"><span>App Store에서 다운로드</span></button>
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
const storyAdVideos = ref([]);

// Mapping titles for Story Ad Videos (as they might only return filename/fileUrl from API)
const adVideoTitles = {
  'c446fd95-c6c0-4b92-a82f-c6aa8df5a161.jpg': '할라피뇨 파퍼 - 1편',
  'fd342a2c-6723-4bb9-88ac-f177b1e5d413.jpg': '할라피뇨 파퍼 - 2편',
  'adb22742-6314-49a8-b250-ebabb2bf6864.png': '25년 6월 오리지널스 출시'
};

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
      // Slicing to recent 3 and mapping titles if possible
      storyAdVideos.value = res.data.data.slice(0, 3).map(video => {
        // Extract filename from URL or object to match title
        const filename = video.fileUrl.split('/').pop();
        return {
          ...video,
          title: adVideoTitles[filename] || video.originalFileName || '광고 영상'
        };
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
}

.top_banner {
  flex: 2;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 10px 30px rgba(0,0,0,0.1);
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
  cursor: pointer;
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
  background-color: #3d2117; /* Dark Brown */
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
  width: 80px; /* Adjust size based on visual */
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
  padding: 0 20px;
}

.video_list {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12px;
}

.video_item {
  cursor: pointer;
}

.image_area.image_size01 {
  position: relative;
  width: 100%;
  aspect-ratio: 16 / 9;
  border-radius: 15px;
  overflow: hidden;
  background: #000;
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
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 60px;
  height: 60px;
  background: rgba(226,34,31, 0.9);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn_play::after {
  content: '';
  border-style: solid;
  border-width: 10px 0 10px 15px;
  border-color: transparent transparent transparent white;
  margin-left: 5px;
}

.video_item .tit {
  margin-top: 15px;
  font-weight: bold;
  font-size: 1.1rem;
}

/* Intro Section */
.home_intro ul {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  list-style: none;
  padding: 0;
}

.txt_chip01 {
  background: #F5EBDC;
  color: #502314;
  padding: 5px 15px;
  border-radius: 20px;
  font-weight: bold;
  font-size: 0.8rem;
  display: inline-block;
  margin-bottom: 15px;
}

.tit_card {
  font-size: 1.25rem;
  font-weight: 900;
  line-height: 1.4;
  margin-bottom: 20px;
}

.btn_move {
  background: #E2221F;
  color: white;
  padding: 10px 25px;
  border-radius: 25px;
  font-weight: bold;
}

/* Delivery Banner */
.delivery_banner {
  background: #502314;
  color: white;
  border-radius: 20px;
  padding: 40px 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
}

.delivery_banner .tit_home {
  color: white;
  margin-bottom: 10px;
}

.tel strong {
  font-size: 3rem;
  font-family: var(--font-flame);
}

/* Brand Section */
.brand_cont {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.brand01 {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* App Down Big Card */
.app_down_card {
  background: #E2221F;
  color: white;
  border-radius: 40px;
  padding: 60px;
  margin-bottom: 100px;
}

.app_downWrap {
  display: flex;
  gap: 60px;
  align-items: center;
}

.phone_ui {
  flex: 1;
}

.image_list {
  display: flex;
  gap: 20px;
  list-style: none;
}

.image_list img {
  width: 100%;
  border-radius: 20px;
}

.app_downWrap .cont {
  flex: 1.5;
}

.benefit_list {
  display: flex;
  gap: 20px;
  margin: 30px 0;
}

.benefit_list strong {
  border: 1px solid rgba(255,255,255,0.4);
  padding: 8px 20px;
  border-radius: 20px;
}

.btn_area {
  display: flex;
  gap: 15px;
  margin-top: 20px;
}

.btn_app {
  background: white;
  color: #E2221F;
  padding: 15px 30px;
  border-radius: 30px;
  font-weight: 900;
}

@media screen and (max-width: 1024px) {
  .home_notice, .video_grid, .home_intro ul, .brand_cont, .app_downWrap {
    grid-template-columns: 1fr;
    flex-direction: column;
  }
}
</style>
