<template>
  <div class="subWrap">
    <CommonHeader />
    
    <div class="contentsWrap">
      <div class="cont_min_area">
        <div class="web_container">
            <div class="page_title">
                <h2 class="tit">고객지원</h2>
            </div>

            <!-- Tabs -->
            <div class="tab01">
                <ul>
                    <li v-for="cat in categories" :key="cat.id" :class="{ 'on': activeTab === cat.id }">
                        <button type="button" class="cat" @click="scrollToCategory(cat.id)">
                            <span>{{ cat.label }}</span>
                        </button>
                    </li>
                </ul>
            </div>

            <!-- Category Sections -->
            <div class="faq_list_wrap">
                <div v-for="cat in categories" :key="cat.id" :id="`cat_${cat.id}`" class="category_section">
                    <h3 class="category_tit">{{ cat.label }}</h3>
                    <ul class="faq_list">
                        <li v-for="qa in getQAsByCategory(cat.id)" :key="qa.qaId">
                            <router-link :to="`/customer/detail?id=${qa.qaId}`">
                                <div class="subject">
                                    <span class="txt">{{ qa.title }}</span>
                                </div>
                                <span class="arrow"></span>
                            </router-link>
                        </li>
                         <!-- Show message if no QAs in this category -->
                        <li v-if="getQAsByCategory(cat.id).length === 0" class="no_data">
                            등록된 문의사항이 없습니다.
                        </li>
                    </ul>
                </div>
            </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import CommonHeader from '@/components/CommonHeader.vue';
import { ref, onMounted, nextTick } from 'vue';
import { useRoute } from 'vue-router';
import { getCategories, getAllQAList } from '@/api/customer';

const route = useRoute();
const categories = ref([]);
const allQAs = ref([]);
const activeTab = ref(null);

const loadData = async () => {
    try {
        // Parallel fetch
        const [catRes, qaRes] = await Promise.all([
            getCategories(),
            getAllQAList()
        ]);

        if (catRes.data && catRes.data.data) {
            categories.value = catRes.data.data.map(c => ({ id: c.categoryId, label: c.categoryName }));
        }

        if (qaRes.data && qaRes.data.data) {
            // Sort by qaId ascending (oldest first)
            allQAs.value = qaRes.data.data.sort((a, b) => a.qaId - b.qaId);
        }

        // Set initial active tab
        if (categories.value.length > 0) {
            activeTab.value = categories.value[0].id;
        }

    } catch (e) {
        console.error("Failed to load data", e);
    }
};

const getQAsByCategory = (catId) => {
    return allQAs.value.filter(qa => qa.categoryId === catId);
};

const scrollToCategory = (catId) => {
    activeTab.value = catId;
    const el = document.getElementById(`cat_${catId}`);
    if (el) {
        const headerOffset = 100; // Adjust for sticky header if any
        const elementPosition = el.getBoundingClientRect().top;
        const offsetPosition = elementPosition + window.pageYOffset - headerOffset;
    
        window.scrollTo({
            top: offsetPosition,
            behavior: "smooth"
        });
    }
};

onMounted(async () => {
    await loadData();

    // Check query param for initial scroll
    if (route.query.category) {
        // Wait for DOM
        nextTick(() => {
            const catId = Number(route.query.category);
            // Verify category exists
            if (categories.value.find(c => c.id === catId)) {
               scrollToCategory(catId);
            }
        });
    }
});
</script>

<style scoped>
/* Fonts & Colors Variables re-declared or assume global if configured */
:root {
    --font: "Sandoll GothicNeoRound", "Pretendard", sans-serif;
    --bg-base: #f5ebdc;
    --normal: #502314;
    --primary: #d62300;
    --border-color: #e4d7c8;
}

.subWrap {
    width: 100%;
    min-height: 100vh;
    background-color: #f5ebdc; /* Updated to BK BG */
    font-family: var(--font, sans-serif);
    color: #502314; /* Updated to BK Normal */
}

/* Contents */
.contentsWrap {
    padding-top: 50px;
    padding-bottom: 100px;
}

.web_container {
    max-width: 1500px;
    margin: 0 auto;
    padding: 0 16px;
}

/* Page Title */
.page_title {
    margin-bottom: 50px;
    text-align: center;
}
.tit {
    font-size: 40px;
    font-weight: 800;
    color: #2e2e2e;
}

/* Tab Styles from Burger King */
.tab01 {
    width: 100%;
    height: 70px; /* Increased size */
    border-bottom: 1px solid #E4D7C8;
    background-color: transparent;
    margin-bottom: 40px;
}

.tab01.scroll {
    overflow-x: auto;
}

.tab01 ul {
    display: flex;
    padding: 0 20px;
    height: 100%; /* Match container */
    margin: 0;
    list-style: none;
    min-width: 100%;
}

.tab01 li {
    position: relative;
    display: list-item;
    height: 100%; /* Match container */
    flex: 1;
    text-align: center;
    min-width: fit-content;
}
.tab01 li button {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 100%;
    padding: 0 20px;
    font-size: 25px; /* Increased by 40% (18px -> 25px) */
    font-weight: 500;
    color: #502314;
    background: transparent;
    border: none;
    cursor: pointer;
    position: relative;
    transition: color 0.3s;
}
.tab01 li.on button {
    color: #d62300; /* BK Red */
}
.tab01 li.on button::after {
    content: '';
    position: absolute;
    bottom: 0; /* Overlap border */
    left: 0;
    width: 100%;
    height: 3px; /* Thick active line */
    background-color: #d62300;
}

/* Category Sections */
.category_section {
    position: relative;
    padding-bottom: 46px; /* Reduced by ~5% (48px -> 46px) */
    margin-bottom: 46px; /* Reduced by ~5% (48px -> 46px) */
    /* Border replaced by pseudo-element for length adjustment */
    border-bottom: none;
    scroll-margin-top: 140px;
}

.category_section::after {
    content: '';
    position: absolute;
    bottom: 0;
    left: 50%;
    transform: translateX(-50%);
    width: 100%; /* Reduced by 10% (110% -> 100%) */
    height: 4px; /* Reduced by 30% (6px -> 4.2px, rounded to 4px) */
    background-color: rgba(80, 35, 20, 0.35);
}

.category_section:last-child {
    margin-bottom: 0;
    padding-bottom: 0;
}
.category_section:last-child::after {
    display: none;
}

.category_tit {
    font-size: 28px;
    font-weight: 800;
    color: #502314;
    margin-bottom: 14px; /* Reduced by 30% (20px -> 14px) */
    padding-bottom: 0;
    border-bottom: none;
}

/* List */
.faq_list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.faq_list li {
    border-bottom: 1px solid #e5e5e5;
}
.faq_list li a {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 23px 10px; /* Reduced vertical padding by ~5% (24px -> 23px) */
    color: #2e2e2e;
    transition: background-color 0.2s;
    cursor: pointer;
}
.faq_list li a:hover {
    background-color: rgba(255,255,255, 0.4);
}

.subject {
    display: flex;
    align-items: center;
}
/* Removed .icon_q style as it is removed from template */

.txt {
    font-size: 20px;
    font-weight: 700; /* Bold */
    color: #2e2e2e;
}

.arrow {
    width: 24px;
    height: 24px;
    background: url('https://www.burgerking.co.kr/img/ico_more_arrow.svg') no-repeat center/contain;
    opacity: 0.5;
}

.no_data {
    padding: 50px 0;
    text-align: center;
    color: #999;
}
</style>
