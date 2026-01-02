<template>
  <header class="headerWrap">
    <div class="titleBar">
        <!-- 로고 (BK Korea 스타일) -->
        <router-link to="/menu" class="flex items-center">
             <span class="text-[#E2221F] font-black text-2xl md:text-3xl italic tracking-tighter transform -skew-x-12 font-['Black_Han_Sans']">BURGERKING</span>
        </router-link>
    
        <!-- 데스크탑 네비게이션: GNBWrap 구조 -->
    <nav class="GNBWrap hidden lg:flex">
        <ul>
            <li class="gnb_btnWrap" v-for="item in navItems" :key="item">
                <router-link :to="item === 'MENU' ? '/menu' : '#'">
                    <span>{{ item }}</span>
                </router-link>
            </li>
        </ul>
    </nav>

    <!-- 우측 버튼 (side_menu) -->
    <div class="side_menu">
        <a href="#" class="btn_link">채용 사이트</a>
        <a href="#" class="btn_franchise">가맹점 모집</a>
    </div>
    </div>
  </header>
</template>

<script setup>
const navItems = ['MENU', 'STORY', 'NEWS', 'STORE', 'CUSTOMER'];
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

/* Header Wrapper - Responsive Padding */
.headerWrap {
    position: relative;
    padding: 0 20px;
    z-index: 200;
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
    height: 98px;
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
}

.GNBWrap {
    flex: 1;
    height: 100%;
    justify-content: center;
    padding: 0 20px;
}

.gnb_btnWrap > a {
    display: flex;
    height: 100%;
    padding: 10px 20px;
    font-family: var(--font-BKR);
    font-weight: 700;
    font-size: 1.5rem;
    color: #502314;
    transition: color 0.3s;
}

.gnb_btnWrap > a:hover {
    color: #E2221F;
}

@media screen and (min-width: 1024px) and (max-width: 1250px) {
    .gnb_btnWrap > a {
        padding: 10px 8px;
        font-size: 1.125rem;
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
    background: url(/img/ico_tit_link_w.svg) no-repeat left 16px center;
    background-color: var(--normal);
    border-radius: 30px;
}

.btn_franchise {
    /* Manual addition to maintain red button */
    background-color: #E2221F; 
    border-radius: 30px;
}

.btn_franchise, .btn_link {
    height: 36px;
    padding: 5px 16px 5px 34px;
    font-size: .8125rem;
    color: var(--bg-base);
}
</style>
