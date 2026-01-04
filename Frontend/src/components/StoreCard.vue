<script setup>
const props = defineProps({
  store: Object
})
const emit = defineEmits(['click'])
</script>

<template>
  <article class="card" @click="emit('click')">
<!--    상단 영역     -->
    <div class="top">
      <div class="info">
        <h3 class="name">{{ store.name }}</h3>
        <p class="address">{{ store.address }}</p>
        <p class="time">{{ store.businessTime }}</p>

        <div class="services">
          <span
            v-for="service in store.serviceNames"
            :key="service"
            class="service-pill"
          >
            {{ service }}
          </span>
        </div>
      </div>

    <img
        class="thumb"
        :src="store.thumbnailImageUrl"
        alt="매장 썸네일" />
    </div>

    <!--    하단 영역     -->
    <div class="bottom">
      <div class="detail-link">
        매장정보 <span>&gt;</span>
      </div>
      <div
          v-if="store.membershipAvailable"
          class="membership">
        <img src="@/assets/img/crown.png" alt="멤버십"/>
        <span>멤버십 적립매장</span>
      </div>
    </div>
  </article>
</template>

<style scoped>
/* 카드 전체 */
.card {
  background: #fff;
  border-radius: 20px;
  padding: 18px 20px;
  display: flex;
  flex-direction: column;
  gap: 16px;
  cursor: pointer;
}

/* 상단 정보 */
.top {
  display: flex;
  justify-content: space-between;
  gap: 16px;
}
.info {
  flex: 1;
}

.name {
  font-size: 20px;
  font-weight: 800;
  color: #5a2d0c;
  margin-bottom: 6px;
  margin-top: 0;
}

.address {
  margin-bottom: 6px;
  font-size: 14px;
  color: #7a4a2e;
}

.time {
  margin-bottom: 10px;
  font-size: 14px;
  font-weight: 600;
  color: #5a2d0c;
}

/* 서비스 */
.services {
  display: flex;
  gap: 8px;
}

.service-pill {
  text-align: center;
  padding: 2px 5px;
  border-radius: 999px;
  font-size: 10px;
  font-weight: 600;
  background-color: #faf4ed;
  color: var(--light);  display: inline-block;
}

/* 매장정보 */
.detail-link {
  font-size: 15px;
  font-weight: 700;
  color: #5a2d0c;
}

/* 하단 */
.bottom {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
}

/* 이미지 1:1 */
.thumb {
  width: 120px;
  height: 120px;
  aspect-ratio: 1 / 1;
  border-radius: 15px;
  object-fit: cover;
}

/* 멤버십 */
.membership {
  display: inline-flex;
  align-items: center;
  gap: 3px;
  padding: 3px 10px;
  border-radius: 999px;
  background: #faf4ed;
  color: #5a2d0c;
  font-size: 13px;
  font-weight: 700;
}
.membership img {
  width: 18px;
  height: 18px;
}
</style>
