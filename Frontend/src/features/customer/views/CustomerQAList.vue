
<template>
  <div class="w-full min-h-screen bg-white">
    <CommonHeader />
    
    <div class="w-full max-w-[1144px] mx-auto px-4 pb-20 pt-10">
      <div class="max-w-[800px] mx-auto">
        <h2 class="text-[40px] font-extrabold text-[#2e2e2e] mb-10 text-center">고객지원</h2>

        <!-- Tabs -->
        <div class="flex border-b border-[#e5e5e5] mb-8 overflow-x-auto scrollbar-hide">
            <button 
                v-for="cat in categories" 
                :key="cat.id"
                @click="currentCategory = cat.id"
                class="flex-1 py-4 text-lg font-bold min-w-[100px] text-center transition-colors border-b-4"
                :class="currentCategory === cat.id ? 'text-[#e2221f] border-[#e2221f]' : 'text-[#b5b5b5] border-transparent hover:text-black'"
            >
                {{ cat.label }}
            </button>
        </div>

        <!-- Title of current category -->
        <h3 class="text-2xl font-bold text-[#2e2e2e] mb-4">
            {{ currentCategoryLabel }}
        </h3>

        <!-- List -->
        <ul class="w-full border-t border-black">
            <li v-for="(item, i) in filteredList" :key="i" class="border-b border-[#e5e5e5]">
                <router-link to="/customer/detail" class="flex items-center justify-between py-5 px-2 hover:bg-[#f9f9f9]">
                    <div class="flex items-center">
                        <span class="w-6 h-6 rounded-full bg-[#f2f2f2] text-[#e2221f] font-bold flex items-center justify-center mr-4 text-sm">Q</span>
                        <span class="text-lg text-[#2e2e2e]">{{ item.question }}</span>
                    </div>
                </router-link>
            </li>
        </ul>
        
        <!-- Pagination Stub (Visual Only) -->
        <div class="flex justify-center mt-10">
            <button class="w-8 h-8 flex items-center justify-center text-[#b5b5b5]">
                &lt;
            </button>
            <button class="w-8 h-8 flex items-center justify-center bg-[#e2221f] text-white rounded-full font-bold mx-1">
                1
            </button>
            <button class="w-8 h-8 flex items-center justify-center hover:bg-[#f2f2f2] rounded-full mx-1">
                2
            </button>
            <button class="w-8 h-8 flex items-center justify-center hover:bg-[#f2f2f2] rounded-full mx-1">
                3
            </button>
            <button class="w-8 h-8 flex items-center justify-center text-[#b5b5b5]">
                &gt;
            </button>
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>
import CommonHeader from '@/components/CommonHeader.vue';
import { ref, computed, onMounted, watch } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const currentCategory = ref('01');

const categories = [
  { id: '01', label: '딜리버리' },
  { id: '02', label: '킹오더' },
  { id: '03', label: '쿠폰' },
  { id: '04', label: '멤버십' },
  { id: '05', label: '매장/상품/서비스' },
  { id: '06', label: '기타' },
];

// Dummy data generator
const generateData = (catId) => {
    const questions = [
        "주문 취소는 어떻게 하나요?",
        "배달 소요 시간은 얼마나 걸리나요?",
        "영수증 재발급이 가능한가요?",
        "회원 탈퇴는 어떻게 하나요?",
        "알레르기 정보는 어디서 확인하나요?",
        "단체 주문은 어떻게 하나요?",
        "원산지 정보가 궁금합니다.",
        "기프티콘 사용이 안됩니다.",
        "매장 운영 시간은 언제인가요?",
        "비회원 주문도 가능한가요?"
    ];
    // Return random subset
    return questions.map((q, i) => ({
        id: i,
        question: `[${categories.find(c => c.id === catId)?.label}] ${q}`
    }));
}

const listData = ref([]);

const filteredList = computed(() => {
    return listData.value;
});

const currentCategoryLabel = computed(() => {
    return categories.find(c => c.id === currentCategory.value)?.label || '';
});

const updateData = () => {
    listData.value = generateData(currentCategory.value);
};

// Watch for route changes or local selection
watch(currentCategory, () => {
    updateData();
});

onMounted(() => {
    if (route.query.category) {
        currentCategory.value = route.query.category;
    }
    updateData();
});
</script>
