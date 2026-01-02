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
  <div class="search-bar">
    <input
        type="text"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        @keyup.enter="$emit('search')"
        placeholder="지역, 매장명을 입력하세요"
    />

    <button
        v-if="modelValue"
        @click="$emit('clear')"
    >
      취소
    </button>
  </div>
</template>
