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

      <div class="modal-body">
      <!-- 이미지 슬라이더 -->
        <div class="image-slider" v-if="detail.imageUrls?.length">
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


        <img
            class="main-image"
            :src="detail.imageUrls[currentImageIndex]"
            alt="매장 이미지"
        />
      </div>

      <!-- 매장 기본 정보 -->
      <section class="store-basic">
        <div class="store-header">
          <h3 class="store-name">{{ detail.name }}</h3>
        </div>
        <p class="store-address">{{ detail.address }}</p>
        <strong><p class="store-phone">{{ detail.phone }}</p></strong>
      </section>

      <section class="box">
        <h3>주문가능 시간</h3>

        <div class="row1">
          <span>매장</span>
          {{ detail.todayBusinessTime }}
        </div>

        <div class="row1">
          <span>딜리버리</span>
          {{ detail.todayDeliveryTime }}
        </div>

        <div class="row1">
          <span>킹오더</span>
          {{ detail.todayKordTime }}
        </div>

        <div class="row1">
          <span>아침메뉴</span>
          {{ detail.todayKmomTime }}
        </div>
      </section>

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
      </div>

        <!-- 확인 버튼 -->
    <div class="modal-footer">
        <button class="confirm" @click="emit('close')">
          확인
        </button>
    </div>
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
  background: #f7efe2;
  border-radius: 24px;
  padding: 24px;
  display: flex;
  flex-direction: column;
}
.modal-body {
  flex: 1;
  overflow-y: auto;
  padding-right: 4px;
}

.modal-title {
  text-align: center;
  font-size: 26px;
  font-weight: 800;
  color: #5a2d0c;
  margin-bottom: 24px;
}
.store-header{
  margin: 0;
  padding: 0 ;
}
.store-name {
  margin: 0;
  font-size: 20px;
  font-weight: 800;
}

.store-address {
  margin-top: 6px;        /* 원하는 만큼만 */
  margin-bottom: 0;
  font-size: 15px;
  font-weight: 600;
  color: #7a4a2e;
}
.store-phone {
  margin-top: 4px;
  margin-bottom: 14px;
}

.image-slider {
  position: relative;
  margin-bottom: 24px;
  width: 100%;
  height: 320px;
  overflow: hidden;
  background: #ddd;
}

.main-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
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
  padding: 24px;
  margin-bottom: 24px;
}

.box h3 {
  font-size: 16px;
  font-weight: 700;
  color: #5a2d0c;
  border-radius: 16px;
  margin-bottom: 5px;
  padding: 0;
}

.row {
  display: grid;
  grid-template-columns: 80px auto;
  align-items: center;
  font-size: 15px;
  font-weight: 700;
  color: #7a4a2e;
  margin-bottom: 0;
}
.row1 {
  display: grid;
  grid-template-columns: 80px auto;
  align-items: center;
  font-size: 14px;
  color: #7a4a2e;
  margin-bottom: 0;
  margin-left: 10px;
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
  padding: 2px 8px;
  border-radius: 999px;
  border: 1px solid #d2b79b;
  font-size: 13px;
  font-weight: 600;
  color: #5a2d0c;
  background: #fdf7ec;
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

.modal-footer {
  padding-top: 16px;
  background: #f7efe2;
}
</style>
