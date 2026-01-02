<script setup>
import CommonHeader from "@/components/CommonHeader.vue";

defineProps({
  modelValue: String
})
defineEmits(['update:modelValue'])
</script>

<template>
  <CommonHeader/>
  <div class="search-bar">
    <input
        :value="modelValue"
        placeholder="지역, 매장명을 입력하세요"
        @input="$emit('update:modelValue', $event.target.value)"
    />

    <button v-if="modelValue" @click="$emit('update:modelValue', '')">
      ✕
    </button>

    <button @click="$emit('update:modelValue', '')">
      취소
    </button>
  </div>
</template>
