<script setup>
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

    stores.value = res.data.stores
    totalCount.value = res.data.totalCount
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

// 🔥 엔터 눌렀을 때만 실행
const onSearch = () => {
  loadStores()
}

// 🔥 취소 버튼
const onClear = () => {
  keyword.value = ''
  loadStores()
}
</script>

<template>
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

    <p v-if="!isLoading && stores.length === 0">
      검색 결과가 없습니다.
    </p>

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
  <StoreDetailModal
      v-if="selectedStore"
      :storeCode="selectedStore.storeCode"
      :businessTime="selectedStore.businessTime"
      @close="selectedStore = null"
  />

</template>

<style scoped>
.store-list {
  display: grid;
  grid-template-columns: repeat(2, 1fr); /* ⭐ 한 줄에 2개 */
  gap: 24px;
}
</style>
