<script setup>
import CommonHeader from "@/components/CommonHeader.vue";

import { ref, onMounted, onUnmounted } from 'vue'
import { fetchStoreList } from '@/api/store'
import StoreCard from '@/components/StoreCard.vue'
import StoreSearchBar from './StoreSearchBar.vue'
import StoreDetailModal from '@/components/StoreDetailModal.vue'
import StoreFilterModal from "@/features/store/views/StoreFilterModal.vue";
import CommonFooter from "@/components/CommonFooter.vue";

const stores = ref([])
const filteredStores = ref([])  // 필터 적용된 결과
const activeFilters = ref(null) // 현재 적용된 필터
const showFilter = ref(false)   // 필터 모달 열기/닫기
const totalCount = ref(0)
const keyword = ref('')
const selectedStoreCode = ref(null)
const isLoading = ref(false)
const PAGE_SIZE = 40
const page = ref(1)
const visibleStores = ref([])


const loadStores = async () => {
  try {
    isLoading.value = true
    const res = await fetchStoreList(keyword.value)

    stores.value = res.stores
    applyFilters()
  } catch (e) {
    console.error('매장 목록 조회 실패', e)
    stores.value = []
    filteredStores.value = []
    totalCount.value = 0
  } finally {
    isLoading.value = false
  }
}

// 최초 전체 매장 조회
onMounted(loadStores)

onMounted(() => {
  window.addEventListener('scroll', onScroll)
})

onUnmounted(() => {
  window.removeEventListener('scroll', onScroll)
})

//엔터 눌렀을 때만 실행
const onSearch = () => {
  loadStores()
}

//취소 버튼
const onClear = () => {
  keyword.value = ''
  activeFilters.value = null
  loadStores()
}

const onApplyFilter = (filters) => {
  activeFilters.value = filters
  applyFilters()
  showFilter.value = false
}

const updateVisibleStores = () => {
  visibleStores.value = filteredStores.value.slice(
      0,
      page.value * PAGE_SIZE
  )
}

const applyFilters = () => {
  let result = [...stores.value]

  if (activeFilters.value) {
    const { services, membership, sort } = activeFilters.value

    if (services.length > 0 && !services.includes('전체')) {
      result = result.filter(store =>
          services.every(s => store.serviceNames?.includes(s))
      )
    }

    if (membership === 'available') {
      result = result.filter(store => store.membershipAvailable)
    }
    if (membership === 'unavailable') {
      result = result.filter(store => !store.membershipAvailable)
    }

    if (sort === 'name') {
      result.sort((a, b) => a.name.localeCompare(b.name))
    }
  }

  filteredStores.value = result
  totalCount.value = result.length

  page.value = 1 // 필터 바뀌면 페이지 초기화
  updateVisibleStores()
}

const loadMore = () => {
  page.value++
  updateVisibleStores()
}

const onShowAllStores = () => {
  keyword.value = ''
  activeFilters.value = null
  page.value = 1
  loadStores()
}

// 스크롤 탑 FAB 상태
const showScrollTop = ref(false)

// 스크롤 감지
const onScroll = () => {
  showScrollTop.value = window.scrollY > 300
}

// 맨 위로 이동
const scrollToTop = () => {
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  })
}

</script>

<template>
  <CommonHeader/>
  <section class="store-page">
    <h1 class="page-title">매장 찾기</h1>

    <StoreSearchBar
        v-model="keyword"
        @search="onSearch"
        @clear="onClear"
    />

    <p v-if="isLoading">매장을 불러오는 중입니다...</p>

    <div class="count-row">
      <p class="count">
        <strong>{{ totalCount }}개</strong>의 매장이 있습니다.
      </p>

      <button class="filter-btn" @click="showFilter = true">
        필터
      </button>
    </div>

    <StoreFilterModal
        v-if="showFilter"
        @close="showFilter = false"
        @apply="onApplyFilter"
    />
    <div
        v-if="!isLoading && filteredStores.length === 0"
        class="empty-result"
    >
      <img
          src="@/assets/img/empty.png"
          alt="검색 결과 없음"
          class="empty-icon"
      />

      <p class="message">
        검색 결과가 없습니다.<br />
        다른 검색어를 입력하거나 필터를 조정해보세요.
      </p>

      <button class="all-store-btn" @click="onShowAllStores">
        전체 매장 보기
      </button>
    </div>

    <div class="store-list">
      <StoreCard
          v-for="store in visibleStores"
          :key="store.storeCode"
          :store="store"
          @click="selectedStoreCode = store.storeCode"
      />
    </div>

    <StoreDetailModal
        v-if="selectedStoreCode"
        :storeCode="selectedStoreCode"
        @close="selectedStoreCode = null"
    />

    <div v-if="visibleStores.length < totalCount" class="load-more">
      <button class="load-more-btn" @click="loadMore">
        더보기
      </button>
    </div>
    <!-- 맨 위로 가는 FAB 버튼 -->
    <button
        v-if="showScrollTop"
        class="scroll-top-btn"
        @click="scrollToTop"
    >
      ↑
    </button>
    <article class="fabWrap"></article>

  </section>
  <common-footer/>

</template>

<style scoped>
.store-page {
  margin-top: 64px;
  padding: 0 50px;
}
.page-title {
  text-align: center;
  font-size: 34px;
  font-weight: 900;
  color: #5a2d0c;
  letter-spacing: -0.02em;
  margin-bottom: 32px;
}
.empty-result {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  padding: 80px 0;
  text-align: center;
}
.count-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 16px;
}

.filter-btn {
  padding: 2px 8px;
  border-radius: 5px;
  background: #5a2d0c;
  color:  #faf4ed;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
}

.count {
  font-size: 16px;
  color: #6b4e3d;
  padding-bottom: 10px;
}

.empty-icon {
  width: 100px;
  height: auto;
  margin-bottom: 24px;
}

.message {
  font-size: 16px;
  color: #4a2c1a;
  line-height: 1.6;
  margin-bottom: 32px;
}

.all-store-btn {
  padding: 12px 24px;
  border-radius: 24px;
  border: 1px solid #d6c1b0;
  background-color: transparent;
  color: #4a2c1a;
  cursor: pointer;
}

.store-list {
  display: grid;
  grid-template-columns: repeat(2, 1fr); /* 한 줄에 2개 */
  gap: 24px;
}

.load-more {
  display: flex;
  justify-content: center;
  margin: 40px 0;
}

.load-more-btn {
  padding: 12px 32px;
  border-radius: 24px;
  background: #faf4ed;
  border: 1px solid #d6c1b0;
  color: #5a2d0c;
  font-size: 15px;
  font-weight: 600;
  cursor: pointer;
}
.fabWrap {
  margin-top: 90px;
}
.scroll-top-btn {
  position: fixed;
  right: 24px;
  bottom: 24px;

  width: 54px;
  height: 54px;
  border-radius: 50%;

  background: #faf4ed;
  color: #5a2d0c;
  font-size: 30px;
  font-weight: 800;

  border: none;
  cursor: pointer;

  display: flex;
  align-items: center;
  justify-content: center;

  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.25);
  z-index: 1000;
}

</style>
