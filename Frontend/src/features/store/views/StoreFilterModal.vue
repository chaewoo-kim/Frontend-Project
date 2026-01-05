<script setup>
import { reactive } from 'vue'

const emit = defineEmits(['close', 'apply'])

const createDefaultFilters = () => ({
  services: [],
  membership: 'all',
  sort: 'name'
})

const filters = reactive(createDefaultFilters())

const SERVICES = ['킹오더','딜리버리','24시','킹모닝','주차','드라이브스루']

const toggleService = (service) => {
  const idx = filters.services.indexOf(service)

  if (idx === -1) {
    filters.services.push(service)
  } else {
    filters.services.splice(idx, 1)
  }
}

const isAllService = () => filters.services.length === 0

const resetFilter = () => {
  Object.assign(filters, createDefaultFilters())
}


const applyFilter = () => {
  emit('apply', { ...filters })
  emit('close')
}
</script>

<template>
  <div class="overlay" @click.self="emit('close')">
    <div class="modal">
      <!-- 헤더 -->
      <div class="modal-header">
        <h2>매장검색 필터</h2>
        <button class="reset" @click="resetFilter">↻</button>
      </div>

      <div class="modal-body">
        <!-- 제공 서비스 -->
        <section class="section">
          <h3>제공 서비스</h3>
          <div class="chips">
            <button
                class="chip"
                :class="{ active: isAllService() }"
                @click="filters.services = []"
            >
              전체
            </button>

            <button
                v-for="s in SERVICES"
                :key="s"
                class="chip"
                :class="{ active: filters.services.includes(s) }"
                @click="toggleService(s)"
            >
              {{ s }}
            </button>
          </div>
        </section>

        <!-- 멤버십 -->
        <section class="section">
          <h3>멤버십 적립</h3>
          <div class="radio-group">
            <button :class="{ active: filters.membership==='all' }"
                    @click="filters.membership='all'">전체</button>
            <button :class="{ active: filters.membership==='available' }"
                    @click="filters.membership='available'">가능</button>
            <button :class="{ active: filters.membership==='unavailable' }"
                    @click="filters.membership='unavailable'">불가능</button>
          </div>
        </section>

        <!-- 정렬 -->
        <section class="section">
          <h3>정렬</h3>
          <div class="radio-group">
<!--            <button :class="{ active: filters.sort==='distance' }"-->
<!--                    @click="filters.sort='distance'">가까운 순</button>-->
            <button :class="{ active: filters.sort==='name' }"
                    @click="filters.sort='name'">가나다 순</button>
          </div>
        </section>
      </div>

      <!-- 하단 -->
      <div class="modal-footer">
        <button class="cancel" @click="emit('close')">취소</button>
        <button class="apply" @click="applyFilter">적용</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.overlay {
  position: fixed;
  inset: 0;
  background: rgba(90,45,12,0.65);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 2000;
}

.modal {
  width: 560px;
  background: #f7efe2;
  border-radius: 24px;
  padding: 24px;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.modal-header h2 {
  font-size: 20px;
  font-weight: 800;
  color: #5a2d0c;
}

.reset {
  font-size: 18px;
  background: none;
  cursor: pointer;
}

.section {
  margin-bottom: 24px;
}

.section h3 {
  font-size: 16px;
  font-weight: 700;
  color: #5a2d0c;
  margin-bottom: 12px;
}

.chips {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.chip {
  padding: 8px 16px;
  border-radius: 999px;
  border: 1.5px solid #e2d3c1;
  background: #fdf7ec;
  font-weight: 700;
  color: #8a6a4e;
}

.chip.active {
  border-color: #5a2d0c;
  color: #5a2d0c;
  background: #fff;
}

.radio-group {
  display: flex;
  gap: 12px;
}

.radio-group button {
  flex: 1;
  padding: 12px;
  border-radius: 999px;
  border: 1.5px solid #e2d3c1;
  background: #fdf7ec;
  font-weight: 700;
}

.radio-group button.active {
  border-color: #5a2d0c;
  color: #5a2d0c;
  background: #fff;
}

.modal-footer {
  display: flex;
  gap: 12px;
  margin-top: 16px;
}

.cancel {
  flex: 1;
  padding: 14px;
  border-radius: 999px;
  background: #eee2d4;
  font-weight: 700;
}

.apply {
  flex: 1;
  padding: 14px;
  border-radius: 999px;
  background: #5a2d0c;
  color: #fff;
  font-weight: 800;
}
</style>
