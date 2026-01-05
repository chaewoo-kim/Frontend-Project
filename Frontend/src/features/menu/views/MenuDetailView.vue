<template>
  <div class="subWrap">
    <CommonHeader />
    
    <div v-if="loading" class="flex justify-center items-center h-[50vh]">
        <div class="animate-spin rounded-full h-12 w-12 border-4 border-[#E2221F] border-t-transparent"></div>
    </div>

    <div v-else class="contentsWrap">
      <div class="cont_min_area">

        <!-- 메뉴 상세 래퍼 -->
        <div class="prd_detailWrap">
          <!-- 이미지 영역 (뱃지 포함) -->
          <div class="prd_image">
            <div class="card_flag">
              <template v-for="badge in detail?.badges" :key="badge">
                <!-- 일반 이미지 배지 -->
                <em v-if="badge !== 'HOT' && badge !== 'SPICY'" :class="['flag_menu', getBadgeDetailClass(badge)]">{{ badge }}</em>
              </template>
            </div>
            <!-- HOT/SPICY 배지 (prd_image의 직계 자식으로 이동) -->
            <template v-for="badge in detail?.badges" :key="'hot-' + badge">
              <em v-if="badge === 'HOT' || badge === 'SPICY'" class="flag_text hot">HOT</em>
            </template>
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
  </div>
  <common-footer/>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import CommonHeader from '@/components/CommonHeader.vue';
import menuApi from '@/api/menu';
import CommonFooter from "@/components/CommonFooter.vue";

const route = useRoute();
const detail = ref(null);
const loading = ref(true);

onMounted(async () => {
    const id = route.params.id;
    try {
        const res = await menuApi.getMenuDetail(id);
        detail.value = res.data.data;
    } catch (e) {
        console.error("Detail load failed", e);
        // Fallback
        detail.value = {
            id: id,
            name: "통새우와퍼",
            menuComposition: "통새우와퍼+콜라R+프렌치프라이R",
            description: "불맛 가득한 그릴드 갈릭 슈림프가 더해진 프리미엄 버거!",
            imageUrl: "https://d1wo1nmpb9h17l.cloudfront.net/bk/resource/menu/202302/1677565538356942.png",
            kcal: 809,
            badges: ["NEW", "HOT"],
            keywords: [{id:1, name:"새우"}, {id:2, name:"매콤"}]
        }
    } finally {
        loading.value = false;
    }
});

const getBadgeDetailClass = (badge) => {
  const mapping = {
    'NEW': 'new_menu',
    'BEST': 'best_menu',
    'POPULAR': 'best_menu',
    'LIMITED': 'limited_menu',
    'ALL_DAY_KING': 'king_menu',
    'ALL_DAY_SNACK': 'snack_menu'
  };
  return mapping[badge] || '';
};
</script>

<style scoped>
/* User 가 제공한 원래의 스타일 적용 */
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

/* Image Section & Badges */
.prd_image {
    position: relative;
    min-width: 140px;
    height: auto;
    min-height: 210px;
    margin: 0 0 5px;
    text-align: center;
}

.prd_image > span {
    display: block;
    height: 100%;
}

.prd_image img {
    display: block;
    width: 100%;
    height: auto;
}

/* 사용자 제공 Badge CSS */
.prd_detailWrap .prd_image .card_flag {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
}

.card_flag {
    display: flex;
    z-index: 10;
}

@media screen and (min-width: 1024px) {
    .prd_detailWrap .card_flag {
        align-items: center;
    }
}

/* flag_menu 기본 스타일 */
.flag_menu {
    display: inline-block;
    width: 36px;
    height: 24px;
    text-indent: -999em;
    background-size: contain;
}

@media screen and (min-width: 1024px) {
    .flag_menu {
        width: 48px;
        height: 32px;
        background-size: 100% auto !important;
    }
}

/* 각 뱃지별 배경 이미지 (로컬 파일 사용) */
.flag_menu.new_menu {
    width: 48px;
    background: url(../img/menu_badge_new.svg) no-repeat 50%;
}

@media screen and (min-width: 1024px) {
    .flag_menu.new_menu {
        width: 64px;
    }
}

.prd_detailWrap .prd_image .flag_menu.new_menu {
    width: 56px;
}

@media screen and (min-width: 1024px) {
    .prd_detailWrap .prd_image .flag_menu.new_menu {
        width: 92px;
    }
}

.flag_menu.best_menu { background: url(../img/menu_badge_best.svg) no-repeat 50%; }
.flag_menu.limited_menu { background: url(../img/menu_badge_limited.svg) no-repeat 50%; }
.flag_menu.king_menu { background: url(../img/menu_badge_alldayking.svg) no-repeat 50%; }
.flag_menu.snack_menu { background: url(../img/menu_badge_alldaysnack.svg) no-repeat 50%; }

@media screen and (min-width: 1024px) {
    .prd_detailWrap .prd_image .flag_menu {
        width: 69px;
        height: 46px;
        margin-right: 10px;
    }
}

.prd_detailWrap .prd_image .flag_menu {
    width: 42px;
    height: 28px;
    background-size: contain;
}

.card_flag>:last-child {
    margin-right: 0;
}

/* 사용자 제공 HOT Badge Styles (수정본) */
.flag_text {
    position: static;
    display: inline-block;
    min-width: 34px;
    height: 18px;
    margin: 0 5px 0 0;
    padding: 1px 5px 0;
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

.prd_detailWrap .flag_text.hot {
    position: absolute !important;
    right: 0;
    top: 0;
    width: 18px;
    height: 100%;
    background-size: contain;
    z-index: 11;
}

@media screen and (min-width: 640px) {
    .prd_detailWrap .flag_text.hot {
        width: 30px;
        background-size: contain;
    }
}

@media screen and (min-width: 1024px) {
    .prd_detailWrap .flag_text.hot {
        width: 30px;
        height: 40px;
        background-size: contain;
    }
}

@media screen and (min-width: 1024px) {
    .prd_detailWrap .flag_text {
        min-width: fit-content;
        height: 22px;
        padding: 0 10px;
        font-size: .8125rem !important;
        line-height: 22px;
    }
    .flag_text.hot {
        width: 20px;
        height: 26px;
        margin-top: 11px;
        background-size: 100% auto;
    }
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
