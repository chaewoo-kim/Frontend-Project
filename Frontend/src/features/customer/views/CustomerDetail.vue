
<template>
  <div class="subWrap">
    <CommonHeader />

    <div class="contentsWrap">
      <div class="cont_min_area">
        <div class="web_container">
          <!-- Page Title -->
          <div class="title_page">
            <h2 class="tit">
              <span class="block txtc_light">고객지원</span>
              <strong class="block">{{ detail?.categoryName || '로딩중...' }}</strong>
            </h2>
          </div>

          <!-- Detail Content -->
          <div class="detail_content">
              <!-- Category Tag and Question -->
              <div class="question_section">
                  <div class="flag_status">
                      <span class="notice">{{ detail?.categoryName }}</span>
                  </div>
                  <p class="title_question">
                      <strong>Q</strong>{{ detail?.title }}
                  </p>
              </div>

              <!-- Answer Body -->
              <div class="answer_body">
                  <div class="content" v-html="detail?.content"></div>
              </div>
          </div>

          <!-- Buttons -->
          <div class="btn_wrap">
              <button type="button" class="btn02" @click="$router.push('/customer/qa_list')">
                  목록
              </button>
          </div>

          <!-- App Download Banner -->
          <div class="app_downWrap">
            <div class="phone_ui">
              <div class="image_qr">
                <div class="ico_logo_mini"></div>
                <img :src="qrImage" alt="QR code">
              </div>
            </div>
            <div class="cont">
              <h2 class="tit"><strong>문제를 해결하지 못하셨나요?</strong></h2>
              <h3>&gt;버거킹 앱으로 이동하신 후 고객의 소리/의견을 등록해주세요.</h3>
              <div class="btn_area">
                <a href="https://play.google.com/store/apps/details?id=kr.co.burgerkinghybrid" target="_blank" class="btn_app_link">
                  <img :src="btnAos" alt="Google Play">
                </a>
                <a href="https://apps.apple.com/kr/app/%EB%B2%84%EA%B1%B0%ED%82%B9-%ED%96%84%EB%B2%84%EA%B1%B0-%ED%82%B9%EC%98%A4%EB%8D%94-%EB%94%9C%EB%A6%AC%EB%B2%84%EB%A6%AC/id1017567032" target="_blank" class="btn_app_link">
                  <img :src="btnIos" alt="App Store">
                </a>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
  <common-footer/>
</template>
<script setup>
import CommonHeader from '@/components/CommonHeader.vue';
import { getQADetail } from '@/api/customer';
import { useRoute } from 'vue-router';
import { ref, onMounted } from 'vue';

// 이미지
import qrImage from '@/assets/img/img_QR.svg';
import btnAos from '@/assets/img/btn_down_aos.svg';
import btnIos from '@/assets/img/btn_down_ios.svg';
import CommonFooter from "@/components/CommonFooter.vue";

const route = useRoute();
const detail = ref(null);

onMounted(async () => {
    const id = route.query.id;
    if (id) {
        try {
            const res = await getQADetail(id);
            if (res.data && res.data.data) {
                detail.value = res.data.data;
            }
        } catch (e) {
            console.error(e);
        }
    }
});
</script>

<style scoped>
@import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.8/dist/web/static/pretendard.css");

@font-face {
    font-family: 'Sandoll GothicNeoRound';
    font-weight: 400;
    font-style: normal;
    src: url('https://www.burgerking.co.kr/fonts/SDGothicNeoRound-gBd.woff2') format('woff2');
}

@font-face {
    font-family: 'Sandoll GothicNeoRound';
    font-weight: 700;
    font-style: normal;
    src: url('https://www.burgerking.co.kr/fonts/SDGothicNeoRound-hEb.woff2') format('woff2');
}

:root {
    --font: "Sandoll GothicNeoRound", "Pretendard", sans-serif;
    --bg-base: #f5ebdc;
    --normal: #502314;
    --primary: #d62300;
    --border-color: #e4d7c8;
}

/* Base Styles */
.subWrap {
    width: 100%;
    min-height: 100vh;
    background-color: #f5ebdc;
    font-family: var(--font);
    color: #502314;
}

.contentsWrap {
    padding-top: 50px;
    padding-bottom: 100px;
    background-color: transparent;
}

.web_container {
    max-width: 1500px;
    margin: 0 auto;
    padding: 0 16px;
}

/* Title Page */
.title_page {
    margin-bottom: 50px;
    text-align: center;
}

.title_page .tit {
    display: block;
    margin: 0;
    font-size: 2.125rem;
    font-weight: 800;
    color: #502314;
}

.title_page .block {
    display: block;
}

.title_page .txtc_light {
    font-weight: 400;
    color: #89665b;
    font-size: 1.5rem;
    margin-bottom: 8px;
}

.title_page strong {
    font-weight: 800;
    color: #502314;
    font-size: 2.5rem;
}

/* Question Section */
.question_section {
    margin-bottom: 30px;
}

.flag_status {
    display: flex;
    font-size: 0.625rem;
    font-weight: 400;
    margin-bottom: 10px;
}

.flag_status .notice {
    display: inline-block;
    padding: 4px 12px;
    background-color: #e4d7c8;
    color: #502314;
    border-radius: 4px;
    font-size: 12px;
    font-weight: 600;
    letter-spacing: 0.5px;
}

.title_question {
    font-size: 1.03125rem;
    font-weight: 700;
    line-height: 21px;
    color: #502314;
    margin: 0;
    padding: 0;
}

@media screen and (min-width: 1024px) {
    .title_question {
        font-size: 1.16875rem;
    }
}

.title_question strong {
    font-weight: 700;
    margin-right: 5px;
    color: #d62300;
    font-size: 1.13rem;
}

@media screen and (min-width: 1024px) {
    .title_question strong {
        font-size: 1.28rem;
    }
}

.answer_body {
    padding: 20px 0 0 0;
    background-color: transparent;
    min-height: 50px;
    margin-top: 10px;
}

@media screen and (min-width: 1024px) {
    .answer_body {
        margin-top: 30px;
    }
}

.content {
    font-size: 1.03125rem;
    font-weight: 700;
    line-height: 22px;
    color: #502314;
}

@media screen and (min-width: 1024px) {
    .content {
        font-size: 1.16875rem;
    }
}

.btn_wrap {
    display: flex;
    justify-content: center;
    margin-top: 30px;
}

.btn02 {
    min-width: 84px;
    height: 36px;
    padding: 2px 10px 1px;
    font-weight: 700;
    font-size: 1.21875rem;
    color: #000000;
    border: 2px solid #000000;
    border-radius: 50px;
    background: transparent;
    cursor: pointer;
    transition: all 0.3s;
}

@media screen and (min-width: 1024px) {
    .btn02 {
        min-width: 324px;
        height: 54px;
        padding: 5px 16px 3px;
        font-size: 1.40625rem;
    }
}

.btn02:hover {
    background-color: #502314;
    color: #fff;
    border-color: #502314;
}

.app_downWrap {
    position: relative;
    display: flex;
    flex-direction: column;
    background-color: #e4d7c8;
    border-radius: 24px;
    margin: 40px 0 30px;
    padding: 30px 20px;
}

@media screen and (min-width: 640px) {
    .app_downWrap {
        max-width: 640px;
        margin: 40px auto 30px;
        flex-direction: row;
        align-items: center;
    }
}

@media screen and (min-width: 1024px) {
    .app_downWrap {
        max-width: 1198px;
        height: 377px;
        padding: 0 60px;
        margin: 40px auto;
    }
}

.phone_ui {
    order: 2;
    position: relative;
    height: 233px;
    background: url('@/assets/img/bg_appdown_phone.png') no-repeat bottom;
    background-size: 320px auto;
    overflow: hidden;
}

@media screen and (min-width: 640px) {
    .phone_ui {
        order: 0;
        width: 250px;
        margin-top: auto;
        background: url('@/assets/img/bg_appdown_phone.png') no-repeat 100% 100%;
        background-size: contain;
    }
}

@media screen and (min-width: 1024px) {
    .phone_ui {
        position: relative;
        width: 405px;
        height: 100%;
        background: url('@/assets/img/bg_appdown_phone.png') no-repeat 0 0;
        overflow: hidden;
    }
}

.image_qr {
    position: relative;
    left: 131px;
    top: 30px;
    width: 231px;
    padding-top: 73px;
    text-align: center;
    border-radius: 24px 24px 0 0;
    overflow: hidden;
}

.ico_logo_mini {
    position: absolute;
    top: 32px;
    left: 45%;
    width: 32px;
    height: 32px;
    background: url('@/assets/img/ico_logo_mini.svg') no-repeat center center;
    background-size: auto 32px;
}

@media screen and (max-width: 1023px) {
    .image_qr {
        left: 80px;
        top: 20px;
        width: 180px;
        padding-top: 50px;
    }
    .ico_logo_mini {
        top: 20px;
        height: 24px;
        background-size: auto 24px;
    }
}

.image_qr img {
    width: 100%;
    height: 100%;
    object-fit: contain;
}

.cont {
    flex-grow: 1;
}

.cont .tit {
    margin: 0 0 10px;
    font-size: 1.5rem;
    color: #502314;
    text-align: left;
}

@media screen and (min-width: 1024px) {
    .cont .tit {
        font-size: 2rem;
    }
}

.cont h3 {
    margin: 0 0 25px;
    font-size: 1rem;
    font-weight: 400;
    color: #502314;
}

.btn_area {
    display: flex;
    gap: 20px;
    flex-wrap: wrap;
    align-items: center;
}

.btn_app_link {
    display: inline-block;
    cursor: pointer;
    transition: transform 0.2s;
}

.btn_app_link:hover {
    transform: scale(1.05);
}

.btn_app_link img {
    height: 50px;
    width: auto;
    display: block;
}
</style>
