<template>
  <div class="mainWrap">
    <CommonHeader />
    <section class="contentsWrap">
      <div class="cont_min_area">
        <div class="web_container">
          <!-- 상단 이벤트 스와이퍼 -->
          <div class="home_notice">
            <div class="top_banner">
              <swiper
                :modules="[Autoplay, Pagination]"
                :slides-per-view="1"
                :loop="true"
                :autoplay="{ delay: 5000, disableOnInteraction: false }"
                :pagination="{ clickable: true }"
                class="mainSwiper"
              >
                <swiper-slide v-for="banner in mainData.eventBanners" :key="banner.id">
                  <div class="image_area" @click="handleLink(mainData.links?.moreVideos)">
                    <img :src="banner.imageUrl" :alt="banner.title">
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
                    <img src="data:image/png;base64,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" alt="QR Code">
                  </div>
                  <button type="button" class="btn_down" @click="handleExternalLink(mainData.links?.playStore)">
                    <img src="@/assets/img/btn_down_aos.svg" alt="Google Play">
                  </button>
                  <button type="button" class="btn_down" @click="handleExternalLink(mainData.links?.appStore)">
                    <img src="@/assets/img/btn_down_ios.svg" alt="App Store">
                  </button>
                </div>
              </div>
              <!-- 매장 찾기 카드 -->
              <div class="card01 home_search">
                <h2 class="tit">내 주변 버거킹 매장 찾기</h2>
                <button type="button" class="btn_detail" @click="handleLink(mainData.links?.store)">
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
                  <button type="button" class="btn_more" @click="handleLink(mainData.links?.moreVideos)">
                    <span>더보기</span>
                  </button>
                </div>
              </div>
              <div class="media_swiper">
                <div class="video_grid">
                  <div v-for="video in mainData.adVideos" :key="video.id" class="video_item" @click="handleExternalLink(video.fileUrl)">
                    <div class="image_area">
                      <img :src="video.fileUrl" :alt="video.originalFilename">
                      <button type="button" class="btn_play"><span>video play</span></button>
                    </div>
                    <div class="tit"><span>{{ video.originalFilename }}</span></div>
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
                    <div class="QR"><img src="@/assets/img/img_QR.svg" alt="QR Code"></div>
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

const router = useRouter();
const mainData = ref({
  eventBanners: [],
  adVideos: [],
  whyBkSlides: [],
  links: {}
});

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
  max-width: 1140px;
  margin: 0 auto;
  padding: 0 20px;
}

/* Home Notice Section */
.home_notice {
  display: flex;
  gap: 20px;
  padding: 40px 0;
}

.top_banner {
  flex: 2;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 10px 30px rgba(0,0,0,0.1);
}

.top_cont {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.card01 {
  background: white;
  border-radius: 20px;
  padding: 30px;
  position: relative;
  overflow: hidden;
  box-shadow: 0 4px 15px rgba(0,0,0,0.05);
  transition: transform 0.3s;
  cursor: pointer;
}

.card01:hover {
  transform: translateY(-5px);
}

.tit {
  font-size: 1.5rem;
  font-weight: 900;
  color: var(--normal);
  line-height: 1.3;
}

.down_store {
  margin-top: 20px;
  display: flex;
  gap: 10px;
  align-items: center;
}

.QR img {
  width: 80px;
  height: 80px;
}

.btn_down img {
  height: 40px;
}

.home_search {
  background-color: #E2221F;
  color: white;
}

.home_search .tit {
  color: white;
}

.btn_detail {
  margin-top: 20px;
  border: 1px solid white;
  color: white;
  padding: 10px 20px;
  border-radius: 30px;
  font-weight: bold;
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

.video_grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}

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
