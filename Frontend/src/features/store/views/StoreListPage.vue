<script setup>
import CommonHeader from "@/components/CommonHeader.vue";

import { ref, onMounted } from 'vue'
import { fetchStoreList } from '@/api/store'
import StoreCard from '@/components/StoreCard.vue'
import StoreSearchBar from './StoreSearchBar.vue'
import StoreDetailModal from '@/components/StoreDetailModal.vue'

const stores = ref([])
const totalCount = ref(0)
const keyword = ref('')
const selectedStoreCode = ref(null)
const isLoading = ref(false)

const loadStores = async () => {
  try {
    isLoading.value = true
    const res = await fetchStoreList(keyword.value)

    stores.value = res.stores
    totalCount.value = res.totalCount
  } catch (e) {
    console.error('매장 목록 조회 실패', e)
    stores.value = []
    totalCount.value = 0
  } finally {
    isLoading.value = false
  }
}

// 최초 전체 매장 조회
onMounted(loadStores)

//엔터 눌렀을 때만 실행
const onSearch = () => {
  loadStores()
}

//취소 버튼
const onClear = () => {
  keyword.value = ''
  loadStores()
}
</script>

<template>
  <CommonHeader/>
  <section class="store-page">
    <h1>매장 찾기</h1>

    <StoreSearchBar
        v-model="keyword"
        @search="onSearch"
        @clear="onClear"
    />

    <p v-if="isLoading">매장을 불러오는 중입니다...</p>

    <p v-else>
      {{ totalCount }}개의 매장이 있습니다.
    </p>

    <div
        v-if="!isLoading && stores.length === 0"
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

      <button class="all-store-btn">
        전체 매장 보기
      </button>
    </div>


    <div class="store-list">
      <StoreCard
          v-for="store in stores"
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
  </section>
</template>

<style scoped>
.empty-result {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  padding: 80px 0;
  text-align: center;
}

.count {
  font-size: 14px;
  color: #6b4e3d;
  margin-bottom: 40px;
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
</style>
