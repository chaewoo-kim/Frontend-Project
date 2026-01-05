
<template>
  <div class="subWrap">
    <CommonHeader />

    <div class="titleBar_M">
      <div class="title_btn">
        <button type="button" class="btn_head_prev" @click="$router.go(-1)"><span>Previous page</span></button>
      </div>
      <div class="title">
        <h1 class="tit"><span>고객지원</span></h1>
      </div>
      <div class="title_btn right">
        <button type="button" class="btn_gnb"><span>GNB</span></button>
      </div>
    </div>

    <section class="contentsWrap">
      <div class="cont_min_area">
        <div class="web_container">
          <div class="title_page WEB">
            <h2 class="tit">고객지원</h2>
          </div>
          <div class="tit_faq">
            <span><b>버거킹에 궁금한 점이 있나요?</b></span>
          </div>
          <div class="search_row">
            <div class="inp_box active">
                <input type="text" v-model="searchKeyword" @keyup.enter="handleSearch" placeholder="궁금한 내용을 검색해주세요.">
                <button type="button" class="btn_search type02" @click="handleSearch">search</button>
            </div>
          </div>
          <ul class="faq_category">
             <li v-for="(cat, index) in categories" :key="index">
                <button type="button" @click="goToDocs(cat.id)">
                    <span :class="cat.iconClass">{{ cat.label }}</span>
                </button>
             </li>
          </ul>
          <div class="faq_list">
            <h5 class="faq_header">많이 물어본 질문</h5>
            <ul>
              <li v-for="(faq, i) in topFaqs" :key="i">
                <router-link :to="`/customer/detail?id=${faq.id}`">
                    <p>{{ faq.title }}</p>
                    <button type="button" class="btn_arrow">arrow</button>
                </router-link>
              </li>
            </ul>
          </div>
          
          <ul class="guide_contact">
            <li>
                <div>
                    <p class="position">대표 소비자 상담</p>
                    <p class="phone"><strong>080-022-8163</strong></p>
                    <p class="working_hour"><span>상담 가능 시간 : 평일 오전 9시 ~ 오후 6시</span></p>
                    <p class="breaktime"><span>(토,일,공휴일 휴무)</span></p>
                </div>
            </li>
            <li>
                <div>
                    <p class="position">매장 / SITE 개설상담</p>
                    <p class="phone"><strong>02-6331-8344</strong></p>
                    <p class="working_hour"><span>상담 가능 시간 : 평일 오전 10시~오후5시</span></p>
                    <p class="breaktime"><span>(점심시간 12:00~13:00, 주말 공휴일 제외)</span></p>
                </div>
                <button class="btn01">가맹 모집안내 바로가기</button>
            </li>
          </ul>
        </div>
      </div>
    </section>
  </div>

  <common-footer/>
</template>

<script setup>
import CommonHeader from '@/components/CommonHeader.vue';
import { useRouter } from 'vue-router';
import { ref, onMounted } from 'vue';
import { getCategories, getTopFaqs } from '@/api/customer';
import CommonFooter from "@/components/CommonFooter.vue";

const router = useRouter();

const categories = ref([]);
const topFaqs = ref([]);
const searchKeyword = ref('');

const getIconClass = (name) => {
    if (name.includes('딜리버리')) return 'ico_faq_delivery';
    if (name.includes('킹오더')) return 'ico_faq_order';
    if (name.includes('쿠폰')) return 'ico_faq_coupon';
    if (name.includes('멤버십')) return 'ico_faq_membership';
    if (name.includes('매장') || name.includes('상품') || name.includes('서비스')) return 'ico_faq_product';
    return 'ico_faq_other';
};

const goToDocs = (id) => {
    router.push({ path: '/customer/qa_list', query: { category: id } });
};

const handleSearch = () => {
    if (searchKeyword.value.trim()) {
        router.push({ path: '/customer/qa_list', query: { keyword: searchKeyword.value } });
    }
};

onMounted(async () => {
    try {
        const catRes = await getCategories();
        if (catRes.data && catRes.data.data) {
            categories.value = catRes.data.data.map(c => ({
                id: c.categoryId,
                label: c.categoryName,
                iconClass: getIconClass(c.categoryName)
            }));
        }

        const faqRes = await getTopFaqs();
        if (faqRes.data && faqRes.data.data) {
            topFaqs.value = faqRes.data.data.map(f => ({
                id: f.qaId, // Ensure API returns qaId as expected in FaqResponse
                title: f.title
            }));
        }
    } catch (error) {
        console.error('Failed to fetch customer data', error);
    }
});
</script>

<style scoped>
@import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.8/dist/web/static/pretendard.css");

@font-face {
    font-family: 'Sandoll GothicNeoRound';
    font-weight: 400; /* Normal */
    font-style: normal;
    src: url('https://www.burgerking.co.kr/fonts/SDGothicNeoRound-gBd.woff2') format('woff2');
}

@font-face {
    font-family: 'Sandoll GothicNeoRound';
    font-weight: 700; /* Bold */
    font-style: normal;
    src: url('https://www.burgerking.co.kr/fonts/SDGothicNeoRound-hEb.woff2') format('woff2');
}

:root {
    --ion-default-dynamic-font: -apple-system-body;
    --ion-font-family: var(--ion-default-font);
    --ion-safe-area-top: env(safe-area-inset-top);
    --ion-safe-area-bottom: env(safe-area-inset-bottom);
    --ion-safe-area-left: env(safe-area-inset-left);
    --ion-safe-area-right: env(safe-area-inset-right);
    --ion-default-font: "Roboto", "Helvetica Neue", sans-serif;
    
    --font: "Sandoll GothicNeoRound", "Pretendard", sans-serif;
    --font-pre: "Pretendard", "Sandoll GothicNeoRound", sans-serif;
    --font-flame: "Flame", "Sandoll GothicNeoRound", sans-serif;
    --font-BKR: "BKR", "Sandoll GothicNeoRound", "Pretendard", sans-serif;
    
    --bg-base: #f5ebdc;
    --bg-light: #fffcf8;
    --normal: #502314;
    --normal-rgb: 80, 35, 20;
    --primary: #d62300;
    --sub: #6f6f6f;
    --light: #89665b;
    --border: rgba(80, 35, 20, 0.1);
    --form-border: #d4c3b4;
    --disabled: #fffcf8;
    --disabled-bg: rgba(80, 35, 20, 0.15);
    --placeholder: rgba(80, 35, 20, 0.3);
    --red: #d62300;
    --orange: #ff8732;
    --yellow: #fa0;
    --green: #198737;
    --king: #c9281d;
    --swiper-theme-color: #007aff;
    --swiper-navigation-size: 44px;
}

/* Base Styles */
.subWrap {
    width: 100%;
    min-height: 100vh;
    background-color: var(--bg-base); /* Changed from #fff to var(--bg-base) */
    font-family: var(--font);
    color: var(--normal);
}

/* Ensure contentsWrap is transparent or matches */
.contentsWrap {
    background-color: transparent; /* Changed from #fff */
}

/* If web_container needs background, usually it's transparent on the main bg, or white card? 
   Looking at BK site, the main background is creamy (#f5ebdc). 
   Let's check if there are white containers. 
   The User's CSS had --bg-base: #f5ebdc. 
*/
.web_container {
    max-width: 1500px; /* Adjusted to 1500px */
    margin: 0 auto;
    padding: 0 16px;
    padding-bottom: 100px;
}

/* Title Page */
.title_page.WEB {
    /* Layout inherited from user request context mostly implies the text inside */
    text-align: center;
}
.title_page.WEB .tit {
    display: block!important;
    margin-top: 40px;
    margin-bottom: 50px;
    font-size: 2.125rem;
    text-align: center;
    
    font-family: var(--font);
    font-weight: 800; /* Keeping weight as it matches 'Bold' usually implied, though user CSS didn't explicitly strict it, it had variables */
    color: var(--normal);
    
    /* Variables from user snippet applied via root, but here are specific property overrides if any */
    user-select: none!important;
    -webkit-text-size-adjust: none;
    word-break: break-all;
    box-sizing: border-box;
}

/* FAQ Title */
.tit_faq {
    text-align: center;
    margin-bottom: 20px;
    font-size: 20px;
}

/* Search Row */
.search_row {
    width: 100%;
    max-width: 1500px; /* Resize to 1500px */
    margin: 0 auto 30px;
    height: 40px; /* Explicit height */
}
.inp_box {
    position: relative;
    width: 100%;
    height: 100%;
    /* Border is on the input usually, but user HTML has class 'active' on inp_box which implies wrapper styling */
}
.inp_box input {
    width: 100%;
    height: 46px;
    background-color: #fffcf8;
    border: 1px solid #d4c3b4; /* Normal state */
    border-radius: 10px; /* As seen in subagent */
    padding: 5px 50px 3px 15px; /* Right padding for button */
    font-size: 15px;
    font-weight: 400;
    color: #000000;
    box-sizing: border-box;
    outline: none;
    font-family: var(--font);
}
.inp_box.active input {
    border-color: #dcdcdc;
    border-width: 2px;
}
.btn_search {
    position: absolute;
    right: 0;
    top: 0;
    width: 50px;
    height: 46px;
    background: transparent;
    border: none;
    cursor: pointer;
    font-size: 0;
}
.btn_search.type02 {
    background: url('https://www.burgerking.co.kr/img/btn_form_search.svg') no-repeat center/contain;
    background-size: 24px; /* Adjust size if needed */
}

/* FAQ Category */
/* FAQ Category - Precise User Update */
.faq_category {
    display: flex;
    gap: 10px;
    flex-wrap: wrap;
    margin-top: 0;
    padding-left: 0;
    list-style: none;
}

@media screen and (min-width: 1024px) {
    .faq_category {
        max-width: 964px;
        margin: 20px auto;
    }
}

@media screen and (min-width: 640px) {
    .faq_category {
        flex-wrap: nowrap;
    }
}

.faq_category li {
    flex: 1;
    min-width: 0;
}

/* Category Button Style */
.faq_category li button {
    display: block;
    font-size: .8125rem;
    font-weight: 400;
    width: 100%;
    height: 135px;
    border-radius: 20px;
    border: 1px solid #e4d7c8;
    border-top-width: 1px;
    border-right-width: 1px;
    border-bottom-width: 1px;
    border-left-width: 1px;
    border-top-style: solid;
    border-right-style: solid;
    border-bottom-style: solid;
    border-left-style: solid;
    border-top-color: rgb(228, 215, 200);
    border-right-color: rgb(228, 215, 200);
    border-bottom-color: rgb(228, 215, 200);
    border-left-color: rgb(228, 215, 200);
    background: #fffcf8;
    box-shadow: 0 0 10px 0 rgba(0, 0, 0, .05);
    overflow: hidden;
    cursor: pointer;
    margin: 0;
    color: var(--normal);
    font-family: var(--font);
    position: relative;
    vertical-align: middle;
}

.faq_category span[class^="ico_faq"] {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    background-color: transparent;
    border-radius: 0;
    margin: 0;
    background: none;
    padding: 0;
    font-size: 19px; /* Reduced by 5% from 20px */
    font-weight: 700;
    color: #502314;
    box-sizing: border-box;
    text-align: center;
}

.faq_category span[class^="ico_faq"]::before {
    content: '';
    display: block;
    position: static;
    transform: none;
    margin: 0 auto 8px auto;
    width: 65px;
    height: 65px;
    background-color: transparent;
    border-radius: 0;
    background-repeat: no-repeat;
    background-position: center;
    background-size: 49px;
    transition: none;
    box-shadow: none;
}


.ico_faq_delivery::before { background-image: url('https://www.burgerking.co.kr/img/ico_faq_delivery.svg'); }
.ico_faq_order::before { background-image: url('https://www.burgerking.co.kr/img/ico_faq_order.svg'); }
.ico_faq_coupon::before { background-image: url('https://www.burgerking.co.kr/img/ico_faq_coupon.svg'); }
.ico_faq_membership::before { background-image: url('https://www.burgerking.co.kr/img/ico_faq_membership.svg'); }
.ico_faq_product::before { background-image: url('https://www.burgerking.co.kr/img/ico_faq_product.svg'); }
.ico_faq_other::before { background-image: url('https://www.burgerking.co.kr/img/ico_faq_other.svg'); }

.faq_list {
    border-top: 5px solid rgba(80, 35, 20, .1);
    margin: 21px -20px 0;
    padding: 15px 20px 10px;
    width: 100%;
    margin-left: auto;
    margin-right: auto;
}

@media screen and (min-width: 640px) {
    .faq_list {
        margin: 30px -30px 0;
        padding: 15px 30px;
    }
}

@media screen and (min-width: 1024px) {
    .faq_list {
        max-width: 1500px;
        padding: 30px 30px 0;
        margin: 30px auto 0;
    }
}

.faq_header {
    font-size: 24px;
    font-weight: 800;
    color: #2e2e2e;
    margin-bottom: 20px;
    padding-bottom: 10px;
}

.faq_list ul li {
    border-bottom: 1px solid #e5e5e5;
}
.faq_list ul li a {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px 10px;
    width: 100%;
    color: #2e2e2e;
}
.faq_list ul li p {
    font-size: 18px;
    font-weight: bold; /* Bold text for top5Faq */
}
.btn_arrow {
    width: 24px;
    height: 24px;
    background: url(https://www.burgerking.co.kr/img/ico_more_arrow.svg) no-repeat center/contain;
    font-size: 0;
    transform: rotate(0deg);
    opacity: 0.5;
}

.guide_contact {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-top: 60px;
    border-top: 1px solid #e5e5e5;
    padding-top: 40px;
}
.guide_contact li {
    text-align: left;
}
.guide_contact .position { font-size: 14px; color: #000; font-weight: bold; margin-bottom: 5px; }
.guide_contact .phone strong { font-size: 24px; color: #000; font-weight: 800; }
.guide_contact .working_hour, .guide_contact .breaktime { font-size: 14px; color: #666; }
.guide_contact .btn01 {
    display: inline-block;
    margin-top: 10px;
    padding: 5px 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 12px;
}

.terms_menu {
    border-bottom: 1px solid #444;
    padding-bottom: 20px;
    margin-bottom: 20px;
}
.terms_menu a {
    color: #fff;
    margin-right: 20px;
    font-size: 14px;
}
.footer .info p {
    font-size: 12px;
    line-height: 1.6;
}

/* Mobile Title Bar (Hide on Desktop) */
.titleBar_M {
    display: none;
}
@media screen and (max-width: 768px) {
    .web_container { padding: 0; }
    .title_page.WEB { display: none; }
    .titleBar_M {
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 50px;
        padding: 0 16px;
        background: #fff;
        border-bottom: 1px solid #eee;
    }
    .titleBar_M .tit { font-size: 18px; font-weight: bold; }
    .faq_category { gap: 10px; }
    .faq_category li button { width: auto; }
    .faq_category span[class^="ico_faq"]::before { width: 60px; height: 60px; background-size: 30px; }
}
</style>
