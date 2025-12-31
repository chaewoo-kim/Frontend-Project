<template>
  <div class="subWrap">
    <CommonHeader />
    <div class="contentsWrap">
      <div class="cont_min_area flex flex-col items-center justify-center py-20">
        <h1 class="text-4xl font-black text-[#502314] mb-8">WELCOME TO BURGERKING</h1>
        <router-link to="/menu" class="bg-[#E2221F] text-white px-8 py-4 rounded-full font-bold text-xl hover:bg-black transition-colors">
          메뉴 보러가기
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import CommonHeader from '@/components/CommonHeader.vue';
</script>

<style scoped>
.cont_min_area {
    min-height: calc(100vh - 444px);
}
</style>
