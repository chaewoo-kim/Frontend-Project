<script setup>
const props = defineProps({
  store: Object
})
const emit = defineEmits(['click'])
</script>

<template>
  <article class="card" @click="emit('click')">
<!--    좍측 영역     -->
    <div class="left">
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

      <div class="detail-link">
        매장정보 <span>&gt;</span>
      </div>
    </div>

    <!--    우측 영역     -->
    <div class="right">
      <img
          class="thumb"
          :src="store.thumbnailImageUrl"
          alt="매장 썸네일" />

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
  justify-content: space-between;
  gap: 16px;
  cursor: pointer;
}

/* 좌측 정보 */
.left {
  flex: 1;
}

.name {
  font-size: 20px;
  font-weight: 800;
  color: #5a2d0c;
  margin-bottom: 6px;
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
  margin-bottom: 16px;
}

.service-pill {
  text-align: center;
  padding: 2px 5px;
  border-radius: 999px;
  font-size: 13px;
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

/* 우측 */
.right {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 10px;
}

/* 이미지 1:1 */
.thumb {
  width: 150px;
  aspect-ratio: 1 / 1;
  border-radius: 18px;
  object-fit: cover;
}

/* 멤버십 */
.membership {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  margin-top: 14px;
  padding: 8px 14px;
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
