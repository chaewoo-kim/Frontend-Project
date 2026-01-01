<template>
  <div class="fixed inset-0 z-[100] flex items-center justify-center p-4">
    <!-- 배경 (Backdrop) -->
    <div class="absolute inset-0 bg-black/60 backdrop-blur-sm" @click="$emit('close')"></div>

    <!-- 모달 컨텐츠 -->
    <div class="relative bg-white w-full max-w-5xl rounded-2xl shadow-2xl overflow-hidden max-h-[90vh] overflow-y-auto">
      
      <!-- 닫기 버튼 -->
      <button @click="$emit('close')" class="absolute top-6 right-6 z-10 p-2 rounded-full bg-white/50 hover:bg-white transition">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 text-[#502314]" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
        </svg>
      </button>

      <!-- 메뉴 상세 래퍼 -->
      <div class="prd_detailWrap">
        <!-- 이미지 영역 -->
        <div class="prd_image">
          <span>
            <img :src="detail?.imageUrl || 'https://via.placeholder.com/500'" :alt="detail?.name" />
          </span>
        </div>

        <!-- 상세 정보 영역 -->
        <div class="cont">

          
          <!-- 메뉴명 (Kcal 포함) -->
          <p class="tit">
            {{ detail?.name }}
            <span class="kal" v-if="detail?.kcal">{{ detail?.kcal }}Kcal</span>
          </p>
          
          <!-- 메뉴 구성 및 설명 -->
          <div class="set_info">
            <div class="set">
              <span>{{ detail?.menuComposition }}</span>
            </div>
            <div class="description" v-if="detail?.description">
              <span>{{ detail?.description }}</span>
            </div>
          </div>

          <div class="h-px bg-gray-200 my-6"></div>

          <!-- 영양 정보 및 기타 -->
          <div class="mt-auto bg-gray-50 p-6 rounded-xl">
            <div class="flex items-center justify-between mb-2">
              <span class="text-gray-500 font-bold">칼로리</span>
              <span class="text-xl font-black text-[#502314]">{{ detail?.kcal || 'N/A' }} <span class="text-sm font-normal">Kcal</span></span>
            </div>
            
          <!-- 키워드 모음 (tag_list) -->
          <div class="tag_list" v-if="detail?.keywords && detail.keywords.length > 0">
            <span v-for="k in detail.keywords" :key="k.id">
              #{{ k.name }}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  detail: Object
});
defineEmits(['close']);

const getBadgeColor = (badge) => {
  switch(badge) {
    case 'NEW': return 'bg-[#E2221F]';
    case 'POPULAR': return 'bg-[#F58F00]';
    case 'LIMITED': return 'bg-black';
    case 'SPICY': return 'bg-[#ce2029]';
    case 'ALL_DAY_KING': return 'bg-[#00962c]';
    default: return 'bg-gray-600';
  }
};
</script>

<style scoped>
/* User Burger King Detail Wrapper Styles */
.prd_detailWrap {
    display: flex;
    flex-direction: column;
    background-color: var(--bg-base);
}

@media screen and (min-width: 1024px) {
    .prd_detailWrap {
        display: flex;
        flex-direction: row;
        margin: 0 -60px 64px;
        padding: 0 60px 64px;
        align-items: center;
    }
}

/* Image Section */
.prd_image {
    min-width: 140px;
    height: 105px;
    text-align: center;
}

.prd_image > span {
    display: block;
    height: 100%;
}

.prd_detailWrap .prd_image {
    position: relative;
    height: auto;
    min-height: 210px;
    margin: 0 0 5px;
}

.prd_detailWrap .prd_image img {
    display: block;
    width: 100%;
    height: auto;
    vertical-align: top;
}

@media screen and (min-width: 640px) {
    .prd_detailWrap .prd_image {
        margin: 0 0 15px;
    }
}

@media screen and (min-width: 1024px) {
    .prd_detailWrap .prd_image {
        width: 100%;
        max-width: 580px;
        margin: 0;
        padding: 0 36px;
    }
}

/* Content Section */
.prd_detailWrap .cont {
    padding: 20px;
}

@media screen and (min-width: 1024px) {
    .prd_detailWrap .cont {
        flex: 1;
        padding-left: 40px;
    }
}

/* Title */
.prd_detailWrap .tit {
    margin-top: 5px;
    font-family: var(--font-BKR);
    font-size: 1.5rem;
    color: var(--normal);
    font-weight: 700;
}

@media screen and (min-width: 640px) {
    .prd_detailWrap .tit {
        margin-top: 10px;
        font-size: 2.25rem;
    }
}

@media screen and (min-width: 1024px) {
    .prd_detailWrap .tit {
        font-size: 2.625rem;
    }
}

.prd_detailWrap .kal {
    display: inline-block;
    margin-left: 5px;
    font-family: var(--font-pre);
    font-size: .75rem;
    color: var(--light);
}

/* Set Info (Menu Composition & Description) */
.prd_detailWrap .set_info {
    display: flex;
    flex-direction: column;
    margin: 15px 0;
}

@media screen and (min-width: 1024px) {
    .prd_detailWrap .set_info {
        margin: 30px 0;
        font-size: .9375rem;
    }
}

.set_info .set {
    color: var(--normal);
    font-weight: 500;
}

.set_info .description {
    color: var(--sub);
    font-size: 0.875rem;
}

@media screen and (min-width: 1024px) {
    .prd_detailWrap .set_info .description {
        margin-top: 10px;
        font-size: .875rem;
    }
}

/* Tag List (Keyword) */
.tag_list {
    display: flex;
    flex-wrap: wrap;
    padding: 5px 0 5px 10px;
    align-items: center;
    font-size: .75rem;
    color: var(--light);
    background-color: rgba(var(--normal-rgb), .05);
    border-radius: 10px;
}

.tag_list > * {
    display: inline-block;
    margin: 5px 15px 5px 0;
}

@media screen and (min-width: 1024px) {
    .tag_list {
        padding: 15px 5px 15px 20px;
        font-size: 1rem;
    }
}
</style>
