<script setup>
defineProps({
  store: Object
})
</script>

<template>
  <article class="card">
    <div class="info">
      <h3>{{ store.name }}</h3>
      <p>{{ store.address }}</p>
      <p class="time">{{ store.businessTime }}</p>

      <div class="tags">
        <span v-if="store.membershipAvailable">멤버십 적립매장</span>
      </div>
    </div>

    <img :src="store.thumbnailImageUrl" alt="" />
  </article>
</template>

<style scoped>
.card {
  background: #fff;
  border-radius: 16px;
  padding: 20px;
  display: flex;
  justify-content: space-between;
  cursor: pointer;
}

.info h3 {
  font-size: 18px;
  font-weight: 800;
  color: #5a2d0c;
}

.info p {
  margin-top: 6px;
  font-size: 14px;
  color: #7a4a2e;
}

.time {
  margin-top: 8px;
  font-weight: 600;
}

.tags span {
  margin-top: 10px;
  display: inline-block;
  padding: 6px 10px;
  background: #5a2d0c;
  color: #fff;
  border-radius: 999px;
  font-size: 12px;
}

img {
  width: 120px;
  height: 80px;
  border-radius: 12px;
  object-fit: cover;
}
</style>
