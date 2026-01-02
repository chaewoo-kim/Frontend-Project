
<template>
  <div class="w-full min-h-screen bg-white">
    <CommonHeader />
    
    <div class="w-full max-w-[1144px] mx-auto px-4 pb-20 pt-10">
      <div class="max-w-[800px] mx-auto">
        <h2 class="text-[40px] font-extrabold text-[#2e2e2e] mb-10 text-center">고객지원</h2>

        <!-- Detail Content -->
        <div class="border-t-2 border-black border-b border-[#e5e5e5]">
            <!-- Question Header -->
            <div class="py-6 px-4 bg-[#f9f9f9] border-b border-[#e5e5e5]">
                <div class="flex items-start">
                    <span class="text-[#e2221f] font-extrabold text-2xl mr-3 leading-none mt-1">Q.</span>
                    <h3 class="text-2xl font-bold text-[#2e2e2e] leading-snug">
                        {{ detail?.title }}
                    </h3>
                </div>
                <div class="mt-2 text-[#b5b5b5] text-sm pl-8">
                    {{ detail?.categoryName }}
                </div>
            </div>

            <!-- Answer Body -->
            <div class="py-10 px-8 text-lg text-[#2e2e2e] leading-relaxed min-h-[300px]">
                <div class="mb-8" v-html="detail?.content"></div>

                <!-- User requested image placeholder -->
                <div class="w-full bg-gray-100 rounded-lg flex items-center justify-center p-10 border-2 border-dashed border-gray-300 mb-8">
                    <div class="text-center">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12 mx-auto text-gray-400 mb-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" />
                        </svg>
                        <p class="text-gray-500 font-bold">[이미지 영역]</p>
                        <p class="text-gray-400 text-sm">고객님이 넣어주실 이미지 위치입니다.</p>
                    </div>
                </div>

                <p>
                    추가적인 문의사항이 있으시면 고객센터로 문의바랍니다.<br/>
                    감사합니다.
                </p>
            </div>
        </div>

        <!-- Buttons -->
        <div class="flex justify-center mt-10">
            <button class="bg-[#e2221f] text-white font-bold py-4 px-12 rounded-full text-lg hover:bg-black transition-colors" @click="$router.push('/customer/qa_list')">
                목록보기
            </button>
        </div>

        <!-- Promo Banner -->
        <div class="mt-20 bg-[#f2f2f2] rounded-[20px] p-8 flex flex-col md:flex-row items-center justify-between">
            <div class="mb-6 md:mb-0">
                <h4 class="text-2xl font-extrabold text-black mb-2">문제를 해결하지 못하셨나요?</h4>
                <p class="text-[#555]">버거킹 앱에서 1:1 문의를 남겨주세요.</p>
            </div>
            <div class="flex items-center">
                <!-- QR Code Stub -->
                <div class="w-24 h-24 bg-white p-2">
                    <img src="https://www.burgerking.co.kr/img/main/app_qrcode.png" alt="QR" class="w-full h-full object-contain" />
                </div>
            </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>
import CommonHeader from '@/components/CommonHeader.vue';
import { getQADetail } from '@/api/customer';
import { useRoute } from 'vue-router';
import { ref, onMounted } from 'vue';

const route = useRoute();
const detail = ref(null);

onMounted(async () => {
    const id = route.query.id;
    if (id) {
        try {
            const res = await getQADetail(id);
            if (res.data && res.data.data) {
                detail.value = res.data.data;
            }
        } catch (e) {
            console.error(e);
        }
    }
});
</script>
