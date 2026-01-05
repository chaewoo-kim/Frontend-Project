<script setup>
defineProps({
  modelValue: {
    type: String,
    default: ''
  }
})

defineEmits(['update:modelValue', 'search', 'clear'])
</script>

<template>
  <div class="search-wrapper">
    <div class="search-bar">
      <input
          type="text"
          :value="modelValue"
          @input="$emit('update:modelValue', $event.target.value)"
          @keyup.enter="$emit('search')"
          placeholder="지역, 매장명을 입력해주세요."
      />

    <button
        v-if="modelValue"
        class="clear-btn"
        type="button"
        @click="$emit('update:modelValue', '')"
    >
      ×
    </button>
  </div>
      <button class="cancel-btn" @click="$emit('clear')">
        취소
      </button>
    </div>
</template>

<style scoped>
/* 전체 가운데 정렬 */
.search-wrapper {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 24px auto 16px;
  gap: 10px;
}

/* 검색 바 */
.search-bar {
  box-sizing: border-box;
  height: 46px;
  flex: 1;
  display: flex;
  background: #fffaf3;
  border: 2px solid #e3d2bd;
  border-radius: 10px;
  padding: 0 20px;
  min-width: 0;
}

/* 입력창 */
.search-bar input {
  width: 100%;
  flex: 1;
  border: none;
  outline: none;
  background: transparent;
  font-size: 16px;
  color: #5a2d0c;
  min-width: 0;
}

/* placeholder */
.search-bar input::placeholder {
  color: #b19982;
  font-weight: 600;
}

.clear-btn {
  background: none;
  border: none;
  font-size: 18px;
  font-weight: 600;
  color: #5a2d0c;
  cursor: pointer;
  margin-left: 8px;
  padding: 0;
  line-height: 1;
}

/* 취소 버튼 */
.cancel-btn {
  background: none;
  border: none;
  font-size: 15px;
  font-weight: 600;
  color: #5a2d0c;
  cursor: pointer;
  flex-shrink: 0;
}

/* 포커스 시 테두리 강조 */
.search-bar:focus-within {
  border-color: #d62300;
}
</style>
