<template>
  <div class="subWrap">
    <CommonHeader />

    <!-- 메인 컨테이너 (contentsWrap) -->
    <div class="contentsWrap">
        <div class="cont_min_area">
            <!-- 메뉴 소개 타이틀 (User Custom) -->
            <div class="title_page WEB">
                <h2 class="tit">메뉴 소개</h2>
            </div>
    
            <!-- 카테고리 필터 (Sticky) -->
            <div class="sticky_top">
                <div class="filter_option category">
                    <ul class="switch_list">
                        <li class="switch01" 
                            v-for="cat in categories" 
                            :key="cat.id"
                            @click="scrollToCategory(cat.id)"
                            :class="{ 'active': activeCategoryId === cat.id }"
                        >
                            <a href="javascript:void(0)">
                                <span>{{ cat.name }}</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
    
            <!-- 메뉴 리스트 영역 -->
            <div class="menu_list_wrap mt-4">
                <div v-for="(catData, index) in menuResponse?.categoryMenuResponseList" :key="catData.categoryId" :id="'cat-' + catData.categoryId" class="divide_group scroll-mt-[200px]">
                    
                    <div class="optWrap">
                        <h3 class="tit01 text-[#502314]">{{ catData.categoryName }}</h3>
                        
                        <!-- 키워드 버튼 (사용자 제공 스타일) -->
                        <button v-if="index === 0" 
                                type="button"
                                @click="showKeywordModal = true"
                                class="btn_filter text">
                           <span>키워드</span>
                        </button>
                    </div>
                    
                    <ul class="menu_list">
                        <li v-for="menu in catData.menuList" :key="menu.name" class="w-1/2 md:w-1/4 px-[4px] md:px-[6px] lg:px-[9px] mb-8">
                           <MenuCard 
                                :menu="menu"
                                @click="router.push(`/menu/detail/${menu.id || 999}`)" 
                           />
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!-- 키워드 선택 모달 -->
    <KeywordModal 
        v-model="showKeywordModal" 
        :categories="keywordCategories"
        :tastes="keywordTastes"
        @apply="handleKeywordApply" 
    />
  </div>
  <common-footer/>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import menuApi from '@/api/menu';
import CommonHeader from '@/components/CommonHeader.vue';
import MenuCard from '../components/MenuCard.vue';
import KeywordModal from '../components/KeywordModal.vue';
import CommonFooter from "@/components/CommonFooter.vue";

const router = useRouter(); // 라우터 사용
const menuResponse = ref(null);
const categories = ref([]);
const activeCategoryId = ref(null);
const loading = ref(true);
const showKeywordModal = ref(false);
const keywordCategories = ref([]);
const keywordTastes = ref([]);

const handleKeywordApply = async (filters) => {
    loading.value = true;
    try {
        const allIds = [...filters.categories, ...filters.tastes];
        if (allIds.length === 0) {
            const res = await menuApi.getMenus();
            if(res.data && res.data.data) {
                menuResponse.value = res.data.data;
            }
        } else {
            const res = await menuApi.searchMenus(allIds);
            if(res.data && res.data.data) {
                menuResponse.value = res.data.data;
            }
        }
        
        // Update categories list based on results
        if (menuResponse.value?.categoryMenuResponseList) {
            categories.value = menuResponse.value.categoryMenuResponseList.map(item => ({
                id: item.categoryId,
                name: item.categoryName
            }));
            if (categories.value.length > 0) {
                activeCategoryId.value = categories.value[0].id;
                // Scroll to top of list after filtering
                window.scrollTo({ top: 0, behavior: 'smooth' });
                
                // Re-setup observer for new elements
                setTimeout(() => {
                    setupScrollObserver();
                }, 500);
            }
        }
    } catch (e) {
        console.error("필터링 실패", e);
    } finally {
        loading.value = false;
    }
};

// 데이터 로드
onMounted(async () => {
  try {
    const res = await menuApi.getMenus();
    if(res.data && res.data.data) {
      const data = res.data.data;
      menuResponse.value = data;
      
      if (data.categoryMenuResponseList) {
        categories.value = data.categoryMenuResponseList.map(item => ({
            id: item.categoryId,
            name: item.categoryName
        }));
        if(categories.value.length > 0) {
            activeCategoryId.value = categories.value[0].id;
        }
      }
    }

    // 키워드 데이터 로드
    const kwRes = await menuApi.getKeywords();
    if(kwRes.data && kwRes.data.data) {
        keywordCategories.value = kwRes.data.data.keyWordCategoryList;
        keywordTastes.value = kwRes.data.data.keyWordTasteList;
    }

    // 스크롤 감지 설정
    setTimeout(() => {
        setupScrollObserver();
    }, 500);
  } catch (e) {
    console.error("데이터 로드 실패", e);
  } finally {
    loading.value = false;
  }
});

let observer = null;
const setupScrollObserver = () => {
    if (observer) observer.disconnect();
    
    observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            // 화면 상단 근처에 올 때 활성화 (threshold 조절 가능)
            if (entry.isIntersecting) {
                const idAttr = entry.target.id;
                if (idAttr) {
                    const id = parseInt(idAttr.replace('cat-', ''));
                    activeCategoryId.value = id;
                }
            }
        });
    }, {
        // 상단에서 어느 정도 위치에 올 때 감지할지 설정
        rootMargin: '-150px 0px -70% 0px',
        threshold: 0
    });

    document.querySelectorAll('.divide_group').forEach(el => {
        observer.observe(el);
    });
};

// 상세 보기
const openDetail = (menu) => {
    const id = menu.id || 999; 
    router.push({ name: 'menu-detail', params: { id: id } });
};

// 스크롤 이동
const scrollToCategory = (id) => {
  activeCategoryId.value = id;
  const el = document.getElementById(`cat-${id}`);
  if(el) {
    const headerOffset = 180; 
    const elementPosition = el.getBoundingClientRect().top;
    const offsetPosition = elementPosition + window.pageYOffset - headerOffset;
  
    window.scrollTo({
      top: offsetPosition,
      behavior: "smooth"
    });
  }
};
</script>

<style>
/* Global Box Sizing */
* { box-sizing: border-box; }

/* CSS Reset */
dd, dl, dt, em, figure, h1, h2, h3, h4, h5, h6, li, ol, p, ul {
    margin: 0;
    padding: 0;
}

.fade-enter-active, .fade-leave-active { transition: opacity 0.2s ease; }
.fade-enter-from, .fade-leave-to { opacity: 0; }

/* 스크롤바 숨기기 유틸리티 */
.scrollbar-hide::-webkit-scrollbar {
    display: none;
}
.scrollbar-hide {
    -ms-overflow-style: none;
    scrollbar-width: none;
}

/* User Custom Title Styles */
.title_page {
    margin-bottom: 30px;
    font-size: 1.1875rem;
    text-align: center;
}

.title_page .tit {
    color: #502314;
    font-weight: 900;
}

@media screen and (min-width: 1024px) {
    .title_page {
        margin-top: 40px;
        margin-bottom: 50px;
        font-size: 2.125rem;
        text-align: center;
    }
}

/* User Custom Menu List Styles */
.menu_list {
    display: flex;
    flex-wrap: wrap;
    margin: -5px -4px;
}

@media screen and (min-width: 640px) {
    .menu_list {
        display: flex;
        flex-wrap: wrap;
        margin: 10px -6px;
    }
}
@media screen and (min-width: 1024px) {
    .menu_list {
        margin: 7px -9px 21px;
    }
}

.divide_group {
    border-bottom: 5px solid rgba(var(--normal-rgb), .1);
}

.divide_group:last-child {
    border-bottom: none;
}

.divide_group > :last-child {
    margin-bottom: 0px;
}

@media screen and (min-width: 1024px) {
    .divide_group {
        margin: 0 -60px;
        padding: 30px 60px;
    }
}

/* User Contents Wrapper Styles */
.contentsWrap {
    position: relative;
    flex: 1;
    overflow: auto;
    padding: 20px 20px 0;
}

.cont_min_area {
    min-height: calc(100vh - 166px);
}

@media screen and (min-width: 640px) {
    .contentsWrap {
        padding: 30px 30px 0;
    }
}

@media screen and (min-width: 1024px) {
    .contentsWrap {
        padding: 40px 60px 0;
        overflow: initial;
    }
    
    .cont_min_area {
        min-height: calc(100vh - 444px);
    }
}

/* User Custom OptWrap Styles */
.optWrap:first-child {
    margin-top: 0 !important;
}

@media screen and (min-width: 1024px) {
    .optWrap {
        margin: 20px 0;
    }
}

.optWrap {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
}

.optWrap > :first-child:not(.option) {
    flex: 1;
    min-width: 0;
    margin: 0;
}

h1, h2, h3, h4, h5, h6 {
    font-weight: 700;
}

.tit01 {
    font-size: 1.125rem; /* Fallback for mobile if needed */
}

@media screen and (min-width: 1024px) {
    .tit01 {
        font-size: 1.1875rem;
    }
}

h3 {
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    unicode-bidi: isolate;
}

/* 사용자 제공 키워드(필터) 버튼 스타일 */
.btn_filter {
    position: relative;
    height: 30px;
    padding: 6px 10px 5px 28px;
    font-size: .8125rem;
    vertical-align: middle;
    /* 로컬 폴더 이미지 사용 */
    background: url(../img/menu_filter.svg) no-repeat 10px;
    border: 1px solid var(--form-border);
    border-radius: 40px;
    color: var(--normal);
    cursor: pointer;
}

.btn_filter.text {
    width: auto;
    text-indent: 0;
    padding: 5px 0 5px 25px;
    background-position: 0;
    background-size: 20px auto;
    border: none;
}

@media screen and (min-width: 640px) {
    .btn_filter {
        padding: 6px 16px 5px 34px;
        font-size: .8125rem;
    }
}

@media screen and (min-width: 1024px) {
    .btn_filter {
        height: 36px;
        padding: 6px 16px 5px 38px;
        font-size: 1.0625rem;
    }
    .btn_filter.text {
        padding-left: 29px;
        background-size: 24px auto;
    }
}

.optWrap .option>:first-child {
    margin-left: 0;
}

.optWrap .option button {
    margin-left: 15px;
}

/* User SubWrap (Background & Layout) */
.subWrap {
    min-height: 100%;
    display: flex;
    flex-direction: column;
    background-color: var(--bg-base);
    overflow: auto;
}

/* User Sticky Category Filter Styles */
.sticky_top {
    position: sticky;
    top: 98px;
    z-index: 30;
    background-color: var(--bg-base);
}

@media screen and (min-width: 1024px) {
    .sticky_top {
        top: 98px;
        padding: 30px 0px; /* Aligned with contentsWrap padding */
    }
}

@media screen and (min-width: 1024px) {
    .title_page + .sticky_top {
        margin-top: -30px;
    }
}

.filter_option {
    display: flex;
    white-space: nowrap;
    overflow-x: auto;
}

@media screen and (min-width: 1024px) {
    .sticky_top > .filter_option {
        margin: 0;
    }
}

@media screen and (min-width: 1024px) {
    .filter_option.category {
        padding: 0;
    }
}

.switch_list {
    display: flex;
    list-style: none;
    padding: 0;
    margin: 0;
}

@media screen and (min-width: 1024px) {
    .filter_option.category .switch_list {
        padding: 0;
    }
}

@media screen and (min-width: 1024px) {
    .sticky_top .switch_list:last-child {
        margin: 0;
    }
}

.switch01 {
    cursor: pointer;
}

.filter_option.category .switch01 {
    min-width: auto;
    margin: 0 10px 0 0;
}

@media screen and (min-width: 1024px) {
    .switch01 {
        position: relative;
        cursor: pointer;
    }
}

.switch01 a {
    display: inline-flex;
    align-items: center;
    padding: 10px 20px;
    border-radius: 30px;
    background-color: rgba(var(--normal-rgb), .05);
    color: #666;
    font-weight: 600;
    transition: all 0.3s;
}

.switch01.active a {
    background-color: #502314;
    color: #fff;
}

.switch01:hover a {
    background-color: #e0e0e0;
}

.switch01.active:hover a {
    background-color: #402010;
}
</style>
