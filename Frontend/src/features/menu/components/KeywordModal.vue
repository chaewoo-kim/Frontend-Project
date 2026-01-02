<template>
  <div v-if="modelValue" class="modalWrap" @click.self="$emit('update:modelValue', false)">
    <article class="popWrap slide_up" @click.stop>
      <header class="pop_head">
        <h1 class="pop_tit"><span>키워드 선택</span></h1>
        <div class="pop_title_btn">
          <button type="button" class="btn_refresh text_web" @click="resetFilters">
            <span>초기화</span>
          </button>
        </div>
      </header>

      <div class="pop_cont text_left">
        <!-- 카테고리 기역 -->
        <div>
          <h2 class="tit02">카테고리</h2>
          <div class="switch_list">
            <label class="switch01">
              <input type="checkbox" value="All_01" v-model="allCategories" @change="toggleAll('categories')">
              <span class="txt_box">카테고리 전체</span>
            </label>
            <label class="switch01" v-for="cat in props.categories" :key="cat.id">
              <input type="checkbox" :value="cat.id" v-model="selectedCategories">
              <span class="txt_box">#{{ cat.name }}</span>
            </label>
          </div>
        </div>

        <!-- 맛 영역 -->
        <div class="mt-8">
          <h2 class="tit02">맛</h2>
          <div class="switch_list">
            <label class="switch01">
              <input type="checkbox" value="All_02" v-model="allTastes" @change="toggleAll('tastes')">
              <span class="txt_box">맛 전체</span>
            </label>
            <label class="switch01" v-for="taste in props.tastes" :key="taste.id">
              <input type="checkbox" :value="taste.id" v-model="selectedTastes">
              <span class="txt_box">#{{ taste.name }}</span>
            </label>
          </div>
        </div>
      </div>

      <footer class="pop_foot">
        <button type="button" class="cancel" @click="$emit('update:modelValue', false)">
          <span>취소</span>
        </button>
        <button type="button" class="apply" @click="applyFilters">
          <span>적용</span>
        </button>
      </footer>
    </article>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';

const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false
  },
  categories: {
    type: Array,
    default: () => []
  },
  tastes: {
    type: Array,
    default: () => []
  }
});

const emit = defineEmits(['update:modelValue', 'apply']);

const selectedCategories = ref([]);
const selectedTastes = ref([]);
const allCategories = ref(false);
const allTastes = ref(false);

const toggleAll = (type) => {
  if (type === 'categories') {
    selectedCategories.value = allCategories.value ? props.categories.map(o => o.id) : [];
  } else {
    selectedTastes.value = allTastes.value ? props.tastes.map(o => o.id) : [];
  }
};

const resetFilters = () => {
    selectedCategories.value = [];
    selectedTastes.value = [];
    allCategories.value = false;
    allTastes.value = false;
};

const applyFilters = () => {
    emit('apply', { categories: selectedCategories.value, tastes: selectedTastes.value });
    emit('update:modelValue', false);
};

// Sync "All" checkbox when individual items change
watch(selectedCategories, (val) => {
    allCategories.value = props.categories.length > 0 && val.length === props.categories.length;
});
watch(selectedTastes, (val) => {
    allTastes.value = props.tastes.length > 0 && val.length === props.tastes.length;
});
</script>

<style scoped>
.modalWrap {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1000;
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
    background: rgba(var(--normal-rgb), .8);
    overflow: hidden;
    padding: 20px;
}

@media screen and (min-width: 1024px) {
    .modalWrap {
        padding: 50px;
    }
}

.popWrap {
    --bg-base: #f5ebdc;
    position: relative;
    display: flex;
    flex-direction: column;
    max-width: 500px;
    min-height: 150px;
    max-height: 100%;
    margin: auto;
    background-color: var(--bg-base);
    border-radius: 20px;
    z-index: 100;
    overflow: hidden;
}

.popWrap.slide_up {
    animation: pop_up .3s ease-in forwards;
}

@keyframes pop_up {
    from { transform: translateY(20px); opacity: 0; }
    to { transform: translateY(0); opacity: 1; }
}

/* Header */
.pop_head {
    position: relative;
    padding: 20px;
    border-bottom: 1px solid var(--border);
    text-align: center;
}

.pop_tit {
    font-size: 1.25rem;
    font-weight: 900;
    color: var(--normal);
}

.pop_title_btn {
    position: absolute;
    right: 20px;
    top: 50%;
    transform: translateY(-50%);
}

.btn_refresh {
    font-size: 0.875rem;
    color: var(--sub);
    font-weight: 700;
}

/* Content */
.pop_cont {
    flex: 1;
    padding: 20px;
    overflow-y: auto;
}

.tit02 {
    font-size: 1.125rem;
    font-weight: 700;
    margin-bottom: 15px;
    color: var(--normal);
}

.switch_list {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}

.switch01 {
    cursor: pointer;
    position: relative;
}

.switch01 input {
    position: absolute;
    opacity: 0;
    cursor: pointer;
    height: 0;
    width: 0;
}

.txt_box {
    display: inline-block;
    padding: 8px 16px;
    border-radius: 30px;
    background-color: rgba(80, 35, 20, 0.05);
    color: #666;
    font-size: 0.875rem;
    font-weight: 600;
    transition: all 0.2s;
}

.switch01 input:checked + .txt_box {
    background-color: #502314;
    color: #fff;
}

/* Footer */
.pop_foot {
    display: flex;
    padding: 20px;
    gap: 10px;
    border-top: 1px solid var(--border);
}

.pop_foot button {
    flex: 1;
    height: 50px;
    border-radius: 30px;
    font-weight: 700;
    font-size: 1rem;
}

.pop_foot .cancel {
    background-color: rgba(80, 35, 20, 0.05);
    color: #666;
    border: none;
}

.pop_foot .apply {
    background-color: #502314;
    color: #fff;
}
</style>
