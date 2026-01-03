<script setup>
import { ref, onMounted } from 'vue'
import { fetchStoreDetail } from '@/api/store'

const props = defineProps({
  storeCode: String,
  // businessTime: String   // ← StoreCard에서 같이 넘겨도 됨
})

const emit = defineEmits(['close'])
const detail = ref(null)
const currentImageIndex = ref(0)
const toggleImage = () => {
  currentImageIndex.value =
      currentImageIndex.value === 0 ? 1 : 0
}

onMounted(async () => {
  try {
    detail.value = await fetchStoreDetail(props.storeCode)
  } catch (e) {
    console.error('매장 상세 조회 실패', e)
  }
})

</script>

<template>
  <div class="overlay" @click.self="emit('close')">
    <div class="modal" v-if="detail">
      <!-- 제목 -->
      <h2 class="modal-title">매장 상세 정보</h2>

      <!-- 이미지 슬라이더 -->
      <div class="image-slider" v-if="detail.imageUrls?.length">
        <img
            class="main-image"
            :src="detail.imageUrls[currentImageIndex]"
            alt="매장 이미지"
        />

        <!-- 화살표 + 인디케이터는 이미지가 2장일 때만 -->
        <template v-if="detail.imageUrls.length === 2">
          <button
              class="nav prev"
              @click.stop="toggleImage"
          >
            ‹
          </button>

          <button
              class="nav next"
              @click.stop="toggleImage"
          >
            ›
          </button>

          <div class="indicator">
            {{ currentImageIndex + 1 }} / 2
          </div>
        </template>
      </div>

      <section class="box">
        <h3>주문가능 시간</h3>

        <div class="row">
          <span>매장</span>
          <strong>{{ detail.todayBusinessTime }}</strong>
        </div>

        <div class="row">
          <span>딜리버리</span>
          <strong>{{ detail.todayDeliveryTime }}</strong>
        </div>

        <div class="row">
          <span>킹오더</span>
          <strong>{{ detail.todayKordTime }}</strong>
        </div>

      <section class="box">
          <h3>운영시간</h3>

          <div class="row">
            <span>평일</span>
            <strong>{{ detail.storTimeDays }}</strong>
          </div>
          <div class="row">
            <span>주말</span>
            <strong>{{ detail.storTimeWeekend }}</strong>
          </div>
          <div class="row">
            <span>공휴일</span>
            <strong>{{ detail.storTimeHoliday }}</strong>
          </div>

          <p class="note" v-if="detail.salesHourNote">
            {{ detail.salesHourNote }}
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
      </section>
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

.image-slider {
  position: relative;
  margin-bottom: 24px;
}

.nav {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: none;
  background: rgba(0, 0, 0, 0.45);
  color: #fff;
  font-size: 28px;
  cursor: pointer;
}

.nav.prev {
  left: 12px;
}

.nav.next {
  right: 12px;
}

.indicator {
  position: absolute;
  right: 12px;
  bottom: 12px;
  background: rgba(0, 0, 0, 0.55);
  color: #fff;
  font-size: 13px;
  padding: 4px 10px;
  border-radius: 999px;
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
