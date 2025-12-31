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
            <div class="menu_list_wrap mt-12">
                <div v-for="(catData, index) in menuResponse?.menuList" :key="catData.categoryId" :id="'cat-' + catData.categoryId" class="divide_group mb-16 scroll-mt-[200px]">
                    
                    <div class="optWrap justify-between gap-2 border-b-2 border-black pb-2">
                        <h3 class="tit01 text-2xl md:text-3xl font-black text-[#502314]">{{ catData.categoryName }}</h3>
                        
                        <!-- 키워드 버튼 (첫 번째 카테고리에만 표시) -->
                        <button v-if="index === 0" class="flex items-center gap-1 text-black font-bold hover:text-[#E2221F] transition mb-1">
                           <span>키워드</span>
                           <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                             <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
                           </svg>
                        </button>
                    </div>
                    
                    <ul class="menu_list">
                        <li v-for="menu in catData.menus" :key="menu.name" class="w-1/2 md:w-1/4 px-[4px] md:px-[6px] lg:px-[9px] mb-8">
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


  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import menuApi from '@/api/menu';
import CommonHeader from '@/components/CommonHeader.vue';
import MenuCard from '@/components/MenuCard.vue';

const router = useRouter(); // 라우터 사용
const menuResponse = ref(null);
const categories = ref([]);
const activeCategoryId = ref(null);
const loading = ref(true);

// 데이터 로드
onMounted(async () => {
  try {
    const res = await menuApi.getMenus();
    // 백엔드 ApiResponse 구조: { success: true, data: { ... }, error: null } 가정
    if(res.data) {
      // res.data.data가 실제 MainMenuResponse
      const data = res.data.data || res.data; // 구조에 따라 유연하게 처리
      menuResponse.value = data;
      
      if (data.menuList) {
        categories.value = data.menuList.map(item => ({
            id: item.categoryId,
            name: item.categoryName
        }));
        if(categories.value.length > 0) {
            activeCategoryId.value = categories.value[0].id;
        }
      }
    }
  } catch (e) {
    console.error("메뉴 로드 실패", e);
    // 폴백(Fallback) 데이터: 백엔드 연결 실패 시 보여줄 가짜 데이터
    const mockData = {
        menuList: [
            {
                categoryId: 1,
                categoryName: "음료&디저트",
                menus: [
                    { name: "콜라", menuComposition: "콜라 R", imageUrl: "https://via.placeholder.com/400x400?text=Cola", badges: [] },
                    { name: "사이다", menuComposition: "사이다 R", imageUrl: "https://via.placeholder.com/400x400?text=Sprite", badges: [] },
                    { name: "아이스크림", menuComposition: "바닐라 아이스크림", imageUrl: "https://via.placeholder.com/400x400?text=Ice+Cream", badges: ["NEW"] },
                    { name: "커피", menuComposition: "아메리카노", imageUrl: "https://via.placeholder.com/400x400?text=Coffee", badges: [] }
                ]
            },
            {
                categoryId: 2,
                categoryName: "올데이스낵&올데이킹",
                menus: [
                    { name: "치즈스틱", menuComposition: "치즈스틱 4조각", imageUrl: "https://via.placeholder.com/400x400?text=Cheese+Stick", badges: [] }
                ]
            },
            {
                categoryId: 3,
                categoryName: "프리미엄",
                menus: [
                    { 
                        name: "몬스터와퍼", 
                        menuComposition: "몬스터와퍼+콜라R+프렌치프라이R", 
                        imageUrl: "https://d1wo1nmpb9h17l.cloudfront.net/bk/resource/menu/202302/1677565454645367.png", 
                        badges: ["POPULAR", "NEW"],
                        description: "4가지 치즈와 두툼한 패티의 환상적인 조합"
                    },
                    { 
                        name: "콰트로치즈와퍼", 
                        menuComposition: "콰트로치즈와퍼+콜라R+프렌치프라이R", 
                        imageUrl: "https://d1wo1nmpb9h17l.cloudfront.net/bk/resource/menu/202302/1677565492167104.png", 
                        badges: ["POPULAR"],
                        description: "4가지 치즈의 풍미가 가득한 프리미엄 버거"
                    }
                ]
            },
            {
                categoryId: 4,
                categoryName: "사이드",
                menus: [
                    { name: "프렌치프라이", menuComposition: "프렌치프라이 L", imageUrl: "https://via.placeholder.com/400x400?text=French+Fries", badges: [] }
                ]
            },
            {
                categoryId: 5,
                categoryName: "오리지널스&맥시멈",
                menus: [
                    { name: "와퍼", menuComposition: "와퍼+콜라R+프렌치프라이R", imageUrl: "https://d1wo1nmpb9h17l.cloudfront.net/bk/resource/menu/202302/1677565158285511.png", badges: [] }
                ]
            },
            {
                categoryId: 6,
                categoryName: "와퍼&주니어",
                menus: [
                    { name: "불고기와퍼", menuComposition: "불고기와퍼+콜라R+프렌치프라이R", imageUrl: "https://d1wo1nmpb9h17l.cloudfront.net/bk/resource/menu/202302/1677565261011663.png", badges: [] }
                ]
            },
            {
                categoryId: 7,
                categoryName: "치킨&슈림프버거",
                menus: [
                    { name: "비프&슈림프버거", menuComposition: "비프&슈림프버거+콜라R+프렌치프라이R", imageUrl: "https://d1wo1nmpb9h17l.cloudfront.net/bk/resource/menu/202306/1685584503714674.png", badges: ["NEW"] },
                    { name: "롱치킨버거", menuComposition: "롱치킨버거+콜라R+프렌치프라이R", imageUrl: "https://d1wo1nmpb9h17l.cloudfront.net/bk/resource/menu/202302/1677565576725838.png", badges: [] }
                ]
            }
        ]
    };
    
    menuResponse.value = mockData;
    
    if (mockData.menuList) {
        categories.value = mockData.menuList.map(item => ({
            id: item.categoryId,
            name: item.categoryName
        }));
        if(categories.value.length > 0) {
            activeCategoryId.value = categories.value[0].id;
        }
    }
    
    // alert("백엔드 연결에 실패하여 '테스트 모드'로 실행됩니다.");
  } finally {
    loading.value = false;
  }
});

// 상세 보기
const openDetail = (menu) => {
    // 1. DTO에 ID가 없는 경우를 대비해 임시 ID 생성 (데모용)
    const id = menu.id || 999; 
    router.push({ name: 'menu-detail', params: { id: id } });
};

// 스크롤 이동
const scrollToCategory = (id) => {
  activeCategoryId.value = id;
  const el = document.getElementById(`cat-${id}`);
  if(el) {
    // 헤더 높이만큼 보정
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

.divide_group > :last-child {
    margin-bottom: 0;
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
.optWrap {
    display: flex;
    align-items: center;
    margin: 10px 0;
    flex-wrap: wrap;
}

.optWrap:first-child {
    margin-top: 0 !important;
}

@media screen and (min-width: 1024px) {
    .optWrap {
        margin: 20px 0;
    }
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
        margin: 0 -60px;
        padding: 30px 60px;
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
    background-color: #f0f0f0;
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
