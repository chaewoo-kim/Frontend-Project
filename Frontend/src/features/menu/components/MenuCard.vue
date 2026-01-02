<template>
  <div class="menu_card" @click="$emit('click')">
    <div class="card_flag">
        <!-- 일반 배지 (왼쪽 정렬) -->
        <template v-for="badge in menu.badges" :key="'normal-' + badge">
          <template v-if="badge !== 'HOT' && badge !== 'SPICY'">
            <!-- 이미지 기반 배지 (사용자 요청 스타일) -->
            <em v-if="getBadgeClass(badge)" :class="['flag_menu', getBadgeClass(badge)]">{{ badge }}</em>
            <!-- 텍스트 기반 배지 (경우를 대비한 폴백) -->
            <em v-else :class="getBadgeColor(badge)">{{ mapBadgeText(badge) }}</em>
          </template>
        </template>
        
        <!-- HOT/SPICY 배지 (오른쪽 정렬) -->
        <template v-for="badge in menu.badges" :key="'hot-' + badge">
          <em v-if="badge === 'HOT' || badge === 'SPICY'" class="flag_text hot">HOT</em>
        </template>
    </div>
    
    <div class="prd_image">
      <span>
        <img 
          :src="menu.imageUrl || 'https://via.placeholder.com/400x400?text=No+Image'" 
          :alt="menu.name" 
        />
      </span>
    </div>

    <div class="cont">
      <p class="tit">
        <span>{{ menu.name }}</span>
      </p>
      
      <div class="set_info">
        <span>{{ menu.menuComposition }}</span>
      </div>
    </div>

    <!-- 메뉴 상세보기 버튼 -->
    <button type="button" class="btn_detail">
      <span>메뉴 상세보기</span>
    </button>
  </div>
</template>

<script setup>
defineProps({
  menu: Object
});

const getBadgeColor = (badge) => {
  const className = badge.toLowerCase().replace(/_/g, '');
  return `flag_menu ${className}`;
};

const getBadgeClass = (badge) => {
  const mapping = {
    'NEW': 'new_menu',
    'BEST': 'best_menu',
    'POPULAR': 'best_menu',
    'LIMITED': 'limited_menu',
    'ALL_DAY_KING': 'alldayking',
    'ALL_DAY_SNACK': 'alldaysnack'
  };
  return mapping[badge] || null;
};

const mapBadgeText = (badge) => {
    if(badge === 'POPULAR') return 'BEST';
    return badge;
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Noto+Sans+KR:wght@400;500;700;900&display=swap');

* {
    box-sizing: border-box;
}

.menu_card { 
    position: relative; 
    height: 100%; 
    padding: 28px 8px 10px !important; 
    background-color: var(--bg-light); 
    box-shadow: 0 0 10px rgba(0, 0, 0, .05); 
    border-radius: 10px;
}

@media screen and (min-width: 640px) {
    .menu_card {
        padding: 28px 12px 10px !important;
    }
}

@media screen and (min-width: 1024px) {
    .menu_card {
        padding: 28px 18px 20px !important;
    }
}

/* 추가 레이아웃 속성 */
.menu_card {
    display: flex;
    flex-direction: column; 
    cursor: pointer;
}

.prd_image {
    min-width: 140px;
    height: 105px;
    text-align: center;
}

.prd_image > span {
    display: block;
    height: 100%;
}

.menu_card .prd_image { 
    position: relative; 
    margin: 0px auto; 
    min-width: 100%; 
    min-height: auto; 
    max-height: 105px;
    display: flex;
    align-items: center;
    justify-content: center;
}

@media screen and (min-width: 640px) {
    .menu_card .prd_image {
        width: 140px;
    }
}

@media screen and (min-width: 1024px) {
    .menu_card .prd_image {
        width: auto;
        height: auto;
        max-height: 178px;
    }
}

.prd_image img {
    display: block;
    width: 100%;
    height: auto;
    max-width: 100%;
    max-height: 105px; 
    object-fit: contain;
    margin: 0 auto;
}

@media screen and (min-width: 1024px) {
    .prd_image img {
        max-height: 178px; 
    }
}

.menu_card .cont {
    margin-top: 5px;
    font-size: .9375rem;
    text-align: center;
    flex: 1;
    display: flex;
    flex-direction: column;
}

.cont .tit {
    font-size: 1.25rem;
    color: #502314;
    margin-bottom: 4px;
}

.cont .set_info {
    color: #888;
    font-size: 13px;
    line-height: 1.4;
}

/* Badge Styles (사용자 제공) */
.menu_card .card_flag {
    display: flex;
    position: absolute;
    top: 10px;
    left: 8px;
    right: 8px;
    z-index: 10;
}

@media screen and (min-width: 640px) {
    .menu_card .card_flag {
        left: 12px;
        width: calc(100% - 24px);
    }
}

.menu_card .card_flag > * {
    position: static;
    margin-right: 5px;
}

.card_flag > :last-child {
    margin-right: 0;
}

.flag_menu {
    display: inline-block;
    width: 36px;
    height: 24px;
    text-indent: -999em;
    background-size: contain;
    background-repeat: no-repeat;
    background-position: 50%;
}

@media screen and (min-width: 1024px) {
    .flag_menu {
        width: 48px;
        height: 32px;
        background-size: 100% auto !important;
    }
}

/* 각 뱃지 배경 이미지 연동 (로컬 SVG 사용) */
.flag_menu.new_menu { 
    width: 48px;
    background-image: url(../img/menu_badge_new.svg); 
}
@media screen and (min-width: 1024px) {
    .flag_menu.new_menu {
        width: 64px;
    }
}
.flag_menu.best_menu { background-image: url(../img/menu_badge_best.svg); }
.flag_menu.limited_menu { background-image: url(../img/menu_badge_limited.svg); }
.flag_menu.alldayking { background-image: url(../img/menu_badge_alldayking.svg); }
.flag_menu.alldaysnack { background-image: url(../img/menu_badge_alldaysnack.svg); }

@media screen and (min-width: 1024px) {
    .flag_menu.alldayking,
    .flag_menu.alldaysnack {
        width: 54px;
        height: 54px;
    }
}

/* HOT Badge Styles */
.flag_text {
    position: static;
    display: inline-block;
    min-width: 34px;
    height: 18px;
    font-size: .625rem;
    color: var(--normal);
    line-height: 18px;
    text-align: center;
    text-indent: 0;
    background-color: var(--bg-base);
    border-radius: 5px;
}

.flag_text.hot {
    min-width: 12px;
    margin: 3px 0 0 auto;
    text-indent: -999em;
    background: url(../img/menu_badge_hot.svg) no-repeat 100%;
}

@media screen and (min-width: 1024px) {
    .flag_text.hot {
        width: 20px;
        height: 26px;
        margin-top: 11px;
        background-size: 100% auto;
    }
}

/* Detail Button */
.btn_detail {
    overflow: hidden;
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background: none;
    border: none;
    text-indent: -9999px;
    cursor: pointer;
}
</style>
