<script setup>
import { ref, onMounted } from 'vue'
import { fetchStoreDetail } from '@/api/store'

const props = defineProps({
  storeCode: String,
  businessTime: String   // ← StoreCard에서 같이 넘겨도 됨
})

const emit = defineEmits(['close'])
const detail = ref(null)

onMounted(async () => {
  const res = await fetchStoreDetail(props.storeCode)
  if (res?.success) {
    detail.value = res.data
  }
})
</script>

<template>
  <div class="overlay" @click.self="emit('close')">
    <div class="modal" v-if="detail">
      <!-- 제목 -->
      <h2 class="modal-title">매장 상세 정보</h2>

      <!-- 이미지 -->
      <img
          class="main-image"
          :src="detail.imageUrls?.[0]"
          alt="매장 이미지"
      />

      <!-- 주문 가능 시간 -->
      <section class="box">
        <h3>주문가능 시간</h3>

        <div class="row">
          <span>매장</span>
          <strong>{{ detail.todayBusinessTime }}</strong>
        </div>
        <div class="row">
          <span>딜리버리</span>
          <strong>{{ detail.deliveryTime }}</strong>
        </div>
        <div class="row">
          <span>킹오더</span>
          <strong>{{ detail.pickupTime }}</strong>
        </div>
      </section>

      <!-- 운영 시간 -->
      <section class="box">
        <h3>운영시간</h3>

        <div class="row">
          <span>운영</span>
          <strong>{{ detail.todayBusinessTime }}</strong>
        </div>

        <p class="note">
          {{ detail.salesHourNote || businessTime }}
        </p>
      </section>

      <!-- 매장 서비스 -->
      <section class="box">
        <h3>매장서비스</h3>

        <div class="services">
          <span
              v-for="service in detail.serviceNames"
              :key="service"
              class="pill"
          >
            {{ service }}
          </span>
        </div>
      </section>

      <!-- 확인 버튼 -->
      <button class="confirm" @click="emit('close')">
        확인
      </button>
    </div>
  </div>
</template>

<style scoped>
.overlay {
  position: fixed;
  inset: 0;
  background: rgba(90, 45, 12, 0.65);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  width: 720px;
  max-height: 90vh;
  overflow-y: auto;
  background: #f7efe2;
  border-radius: 24px;
  padding: 32px;
}

.modal-title {
  text-align: center;
  font-size: 26px;
  font-weight: 800;
  color: #5a2d0c;
  margin-bottom: 24px;
}

.main-image {
  width: 100%;
  border-radius: 16px;
  margin-bottom: 24px;
}

.box {
  background: #fdf7ec;
  border-radius: 16px;
  padding: 20px;
  margin-bottom: 20px;
}

.box h3 {
  font-size: 18px;
  font-weight: 700;
  color: #5a2d0c;
  margin-bottom: 12px;
}

.row {
  display: flex;
  justify-content: space-between;
  padding: 6px 0;
  font-size: 15px;
  color: #7a4a2e;
}

.row strong {
  color: #5a2d0c;
}

.note {
  margin-top: 8px;
  font-size: 13px;
  color: #8a6a4e;
}

.services {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.pill {
  padding: 6px 12px;
  border-radius: 999px;
  border: 1px solid #d2b79b;
  font-size: 13px;
  color: #5a2d0c;
  background: #fff;
}

.confirm {
  width: 100%;
  margin-top: 24px;
  padding: 16px;
  border-radius: 999px;
  background: #5a2d0c;
  color: #fff;
  font-size: 18px;
  font-weight: 800;
}
</style>
