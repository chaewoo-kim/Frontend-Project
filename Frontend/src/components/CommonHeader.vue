<template>
  <header class="headerWrap">
    <div class="titleBar">
        <!-- 로고 (BK Korea 스타일) -->
        <h1 class="title_logo">
            <router-link to="/main" class="logo">
                <img src="@/assets/img/bg_logo.svg" alt="Burger King">
            </router-link>
        </h1>

        <!-- 데스크탑 네비게이션: GNBWrap 구조 -->
    <nav class="GNBWrap hidden lg:flex">
        <ul>
          <li
              class="gnb_btnWrap"
              v-for="item in navItems"
              :key="item.name"
              @mouseenter="handleMouseEnter(item.name)"
              @mouseleave="handleMouseLeave(item.name)"
          >
            <router-link :to="item.path">
              <span>{{ item.name }}</span>
            </router-link>

            <!-- STORY 서브 네비게이션 (브라운 필 디자인) -->
            <div v-if="item.name === 'STORY' && showStorySub" class="sub_gnb">
              <div class="sub_gnb_inner">
                <router-link to="/story/brand">브랜드</router-link>
                <router-link to="/story/why">Why 버거킹</router-link>
                <router-link to="/story/esg">ESG 경영</router-link>
                <router-link to="/story/qsr">Smart QSR</router-link>
                <router-link to="/story/awards">수상실적</router-link>
                <router-link to="/story/ad">광고영상</router-link>
              </div>
            </div>

            <!-- NEWS 서브 네비게이션 -->
            <div v-if="item.name === 'NEWS' && showNewsSub" class="sub_gnb">
              <div class="sub_gnb_inner">
                <router-link to="/notice/event/ongoing">이벤트</router-link>
                <router-link to="/notice/notice">공지사항</router-link>
              </div>
            </div>
          </li>
        </ul>
    </nav>

    <!-- 우측 버튼 (side_menu) -->
    <div class="side_menu">
        <a href="https://bkr.recruiter.co.kr/career/home" class="btn_link" target="_blank" rel="noopener noreferrer">채용사이트</a>
        <a href="https://www.burgerking.co.kr/" class="btn_franchise" target="_blank" rel="noopener noreferrer">가맹점모집</a>
    </div>
    </div>
  </header>
</template>

<script setup>
import { ref } from 'vue';

const showStorySub = ref(false);
const showNewsSub = ref(false);

const navItems = [
    { name: 'MENU', path: '/menu' },
    { name: 'STORY', path: '/story/brand' },
    { name: 'NEWS', path: '/notice' },
    { name: 'STORE', path: '/store' },
    { name: 'CUSTOMER', path: '/customer/main' }
];

const handleMouseEnter = (name) => {
  if (name === 'STORY') showStorySub.value = true;
  if (name === 'NEWS') showNewsSub.value = true;
};

const handleMouseLeave = (name) => {
  if (name === 'STORY') showStorySub.value = false;
  if (name === 'NEWS') showNewsSub.value = false;
};
</script>

<style scoped>
/* Reset */
* { box-sizing: border-box; }
article, aside, figure, footer, header, hgroup, nav, section {
    display: block;
    padding: 0;
    margin: 0;
}
ul {
    list-style: none;
    padding: 0;
    margin: 0;
}

.headerWrap {
    position: relative;
    padding: 0 20px;
    z-index: 500;
    background-color: var(--bg-base);
}

@media screen and (min-width: 640px) {
  .headerWrap {
    padding: 0 30px;
  }
}

@media screen and (min-width: 1024px) {
  .headerWrap {
    position: sticky;
    top: 0;
    padding: 0 60px;
    background-color: var(--bg-base);
  }
}

/* User TitleBar Layout */
.titleBar {
    display: flex;
    width: 100%;
    max-width: 1400px;
    height: 80px; /* Reduced from 98px */
    margin: 0 auto;
    align-items: center;
    position: relative; /* For absolute nav positioning */
}

a {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  margin: 0;
  color: #502314; /* var(--normal) replacement */
  text-decoration: none;
  outline: none;
  cursor: pointer;
}

/* User GNB Styles */
.GNBWrap, .GNBWrap>ul {
  display: flex;
  height: 100%;
}

.GNBWrap {
  flex: 1;
  height: 100%;
  justify-content: center;
  padding: 0 20px;
}

.gnb_btnWrap {
  position: relative;
  display: flex;
  align-items: center;
}

.gnb_btnWrap > a {
    display: flex;
    height: 100%;
    padding: 10px 20px;
    font-family: var(--font-BKR);
    font-weight: 800; /* Extra Bold/Black chunky feel */
    font-size: 1.5625rem; /* ~25px */
    color: #502314;
    transition: color 0.3s;
    letter-spacing: -0.02em;
}

.gnb_btnWrap > a:hover {
  color: #E2221F;
}

/* STORY Sub GNB - Brown Pill Design */
.sub_gnb {
  position: absolute;
  top: 80%; /* Position below the header link */
  left: 50%;
  transform: translateX(-50%);
  z-index: 600;
  white-space: nowrap;
}

.sub_gnb_inner {
  display: flex;
  background-color: #502314; /* 브라운 배경 */
  border-radius: 50px; /* 필 디자인 */
  padding: 10px 30px;
  gap: 30px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
}

.sub_gnb_inner a {
  color: #f5ebdc; /* 라이트 컬러 */
  font-weight: 700;
  font-size: 1.125rem;
  transition: color 0.2s;
  padding: 5px 0;
}

.sub_gnb_inner a:hover {
  color: #E2221F; /* 호버 시 빨간색 */
}

@media screen and (min-width: 1024px) and (max-width: 1250px) {
  .gnb_btnWrap > a {
    padding: 10px 8px;
    font-size: 1.125rem;
  }
  .sub_gnb_inner {
    padding: 8px 15px;
    gap: 15px;
  }
  .sub_gnb_inner a {
    font-size: 0.9rem;
  }
}

/* User Side Menu Styles */
.side_menu {
  display: flex;
  position: relative;
  margin-left: auto;
  gap: 10px;
}

.btn_link {
    background: url(@/assets/img/ico_tit_link_w.svg) no-repeat left 16px center;
    background-color: #502314; /* Match footer recruit button brown */
    border-radius: 30px;
    background-size: 14px;
}

.btn_franchise {
  background-color: #502314;
  border-radius: 30px;
  position: relative;
}

.btn_franchise::before {
  content: '';
  position: absolute;
  left: 16px;
  top: 50%;
  transform: translateY(-50%);
  width: 14px;
  height: 14px;
  background-color: #f5ebdc; /* var(--bg-base) usually, using hex for certainty or sticking to requested 'skin color' */
  -webkit-mask: url(@/assets/img/store.png) no-repeat center;
  mask: url(@/assets/img/store.png) no-repeat center;
  -webkit-mask-size: contain;
  mask-size: contain;
}

.btn_franchise, .btn_link {
    height: 36px;
    padding: 5px 16px 5px 38px;
    font-size: .8125rem;
    color: #fff;
    font-weight: 700;
}

/* Precise Logo Styling based on User Request */
.title_logo {
    display: block;
    margin: 0;
    padding: 0;
}

.title_logo .logo {
    display: flex;
    align-items: center;
    height: 100%;
}

.title_logo .logo img {
    height: 24px; /* Burger King Korea official height */
    width: auto;
    object-fit: contain;
}

h1, h2, h3, h4, h5, h6 {
    font-weight: 700;
    font-size: 1em;
}
</style>