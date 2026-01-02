<template>
  <div class="subWrap ad_archive_page">
    <CommonHeader />
    
    <div class="contentsWrap">
      <div class="ad_hero">
        <h1 class="ad_title">광고영상</h1>
      </div>

      <div class="sticky_filter_bar">
        <div class="web_container flex_between">
          <div class="category_label">TV CF, 디지털 광고</div>
          <div class="custom_select_wrap">
            <button type="button" class="btn_filter" @click="toggleDropdown">
              <span>{{ selectedYearLabel }}</span>
              <i class="ico_arrow" :class="{ open: isDropdownOpen }"></i>
            </button>
            <div v-if="isDropdownOpen" class="dropdown_menu">
              <ul>
                <li v-for="year in filterYears" :key="year" @click="selectYear(year)">
                  <span>{{ year === 'all' ? '모든 연도' : year }}</span>
                  <i v-if="selectedYear === year" class="ico_check"></i>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <div class="web_container list_container">
        <div v-for="year in displayedYears" :key="year" class="year_section">
          <h2 class="year_label">{{ year }}</h2>
          <div class="video_grid">
            <div v-for="(video, idx) in videosByYear[year]" :key="idx" class="video_card">
              <div class="video_thumbnail">
                <img :src="video.fileUrl || '/api/story/images/AD_VIDEO/placeholder.png'" :alt="video.title">
                <div class="play_overlay">
                  <i class="ico_play_large"></i>
                </div>
              </div>
              <p class="video_title">{{ video.title }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <CommonFooter />
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue';
import CommonHeader from '@/components/CommonHeader.vue';
import CommonFooter from '@/components/CommonFooter.vue';
import storyApi from '@/api/story';

const isDropdownOpen = ref(false);
const selectedYear = ref('all');
const filterYears = ref(['all', '2025', '2024']);

// Consolidated video data
const videoData = ref([
  // 2025
  { year: '2025', title: '할라피뇨 파퍼 - 1편', filename: 'popper1.jpg', fileUrl: '' },
  { year: '2025', title: '할라피뇨 파퍼 - 2편', filename: 'popper2.jpg', fileUrl: '' },
  { year: '2025', title: '25년 6월 오리지널스 출시', filename: 'originals.png', fileUrl: '' },
  { year: '2025', title: '25년 5월 킹퓨전 출시', filename: 'kingfusion.png', fileUrl: '' },
  { year: '2025', title: '크리스퍼 - 노윤서 편', filename: 'crisperno.jpg', fileUrl: '' },
  { year: '2025', title: '크리스퍼 - 추영우 편', filename: 'crisperchu.jpg', fileUrl: '' },
  { year: '2025', title: '[불끈버거] 먹으면 불끈 힘이 나는, 불 끈 영웅들의 버거', filename: 'bullggen.png', fileUrl: '' },
  { year: '2025', title: '콰트로치즈 풀드비프 와퍼', filename: 'pulledbeefcheeze.png', fileUrl: '' },
  
  // 2024
  { year: '2024', title: '풀드비프 와퍼, 스파이시 풀드비프 와퍼', filename: 'polledbeefspicy.png', fileUrl: '' },
  { year: '2024', title: '456 크로켓', filename: '456crocket.png', fileUrl: '' },
  { year: '2024', title: '트러플 머쉬룸 와퍼', filename: 'trufflemushroom.png', fileUrl: '' },
  { year: '2024', title: '두툼 버거', filename: 'dutum.png', fileUrl: '' },
  { year: '2024', title: '몬스터 주니어', filename: 'monsterjunior.png', fileUrl: '' },
  { year: '2024', title: '와퍼 타투', filename: 'whopperTatoo.png', fileUrl: '' },
  { year: '2024', title: '콰트로치즈 비프라구 와퍼', filename: 'quatrocheezebeefragu.png', fileUrl: '' },
  { year: '2024', title: '비프라구 와퍼', filename: 'beefragu.png', fileUrl: '' },
  { year: '2024', title: '올데이킹(1)', filename: 'alldayking1.png', fileUrl: '' },
  { year: '2024', title: '올데이킹(2)', filename: 'alldayking2.png', fileUrl: '' },
  { year: '2024', title: '오리지널스 아보카도 잭', filename: 'avocadojack.png', fileUrl: '' },
  { year: '2024', title: '불맛 더블치즈', filename: 'doublecheese.png', fileUrl: '' },
  { year: '2024', title: '리얼 어니언링', filename: 'onion.png', fileUrl: '' },
  { year: '2024', title: '뉴 와퍼', filename: 'newWhopper.png', fileUrl: '' },
  { year: '2024', title: '핫 칠리러버', filename: 'hotchilli.png', fileUrl: '' },
  { year: '2024', title: '통모짜 와퍼', filename: 'mozza.png', fileUrl: '' },
  { year: '2024', title: '오리지널스 솔티드 에그', filename: 'saltedEgg.png', fileUrl: '' },
  { year: '2024', title: '콰트로치즈 큐브스테이크 와퍼', filename: 'quatroCubeSteakWhopper.png', fileUrl: '' },
  { year: '2024', title: '큐브스테이크 와퍼', filename: 'cube.png', fileUrl: '' },
]);

const videosByYear = computed(() => {
  const groups = {};
  videoData.value.forEach(video => {
    if (!groups[video.year]) groups[video.year] = [];
    groups[video.year].push(video);
  });
  return groups;
});

const displayedYears = computed(() => {
  const years = Object.keys(videosByYear.value).sort((a, b) => parseInt(b) - parseInt(a));
  if (selectedYear.value === 'all') return years;
  return [selectedYear.value];
});

const selectedYearLabel = computed(() => {
  return selectedYear.value === 'all' ? '모든 연도' : selectedYear.value;
});

const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value;
};

const selectYear = (year) => {
  selectedYear.value = year;
  isDropdownOpen.value = false;
};

const closeDropdown = (e) => {
  if (!e.target.closest('.custom_select_wrap')) {
    isDropdownOpen.value = false;
  }
};

onMounted(async () => {
  window.addEventListener('click', closeDropdown);
  
  try {
    const response = await storyApi.getImagesByType('AD_VIDEO');
    if (response.data && response.data.data) {
      const apiImages = response.data.data;
      videoData.value.forEach(video => {
        const matchingImage = apiImages.find(img => img.originalFileName === video.filename);
        if (matchingImage) {
          video.fileUrl = matchingImage.fileUrl;
        }
      });
    }
  } catch (error) {
    console.error('Failed to fetch ad videos:', error);
  }
});

onUnmounted(() => {
  window.removeEventListener('click', closeDropdown);
});
</script>

<style scoped>
.ad_archive_page {
  background-color: #F5EBDC;
  min-height: 100vh;
}

.ad_hero {
  padding: 80px 0 60px;
  text-align: center;
}

.ad_title {
  font-family: 'Flame', sans-serif;
  font-size: 3rem;
  font-weight: 900;
  color: #502314;
}

.sticky_filter_bar {
  position: sticky;
  top: 0;
  z-index: 100;
  background-color: #F5EBDC;
  border-bottom: 1px solid rgba(80, 35, 20, 0.1);
  padding: 15px 0;
}

.flex_between {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.category_label {
  font-weight: 700;
  color: #502314;
  font-size: 1.1rem;
}

.custom_select_wrap {
  position: relative;
}

.btn_filter {
  background: none;
  border: none;
  font-weight: 700;
  color: #502314;
  font-size: 1.1rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
}

.ico_arrow {
  display: inline-block;
  width: 14px;
  height: 8px;
  background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 14 8'%3E%3Cpath d='M1 1l6 6 6-6' fill='none' stroke='%23502314' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'/%3E%3C/svg%3E") no-repeat center;
  transition: transform 0.3s;
}

.ico_arrow.open {
  transform: rotate(180deg);
}

.dropdown_menu {
  position: absolute;
  top: calc(100% + 15px);
  right: 0;
  background: #5F594D;
  border-radius: 8px;
  width: 160px;
  overflow: hidden;
  box-shadow: 0 4px 20px rgba(0,0,0,0.2);
}

.dropdown_menu ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.dropdown_menu li {
  padding: 12px 20px;
  color: #fff;
  font-weight: 700;
  cursor: pointer;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.dropdown_menu li:hover {
  background-color: #502314;
}

.ico_check {
  display: inline-block;
  width: 14px;
  height: 10px;
  background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 14 10'%3E%3Cpath d='M1 5l4 4 8-8' fill='none' stroke='white' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'/%3E%3C/svg%3E") no-repeat center;
}

.list_container {
  padding: 60px 0;
}

.year_section {
  margin-bottom: 80px;
}

.year_label {
  font-family: 'Flame', sans-serif;
  font-size: 3.5rem;
  font-weight: 900;
  color: #502314;
  margin-bottom: 40px;
}

.video_grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
}

.video_card {
  cursor: pointer;
}

.video_thumbnail {
  position: relative;
  aspect-ratio: 16 / 9;
  border-radius: 12px;
  overflow: hidden;
  background-color: #000;
  margin-bottom: 12px;
}

.video_thumbnail img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.play_overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.2);
  display: flex;
  align-items: center;
  justify-content: center;
}

.ico_play_large {
  width: 60px;
  height: 60px;
  background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 60 60'%3E%3Ccircle cx='30' cy='30' r='30' fill='rgba(0,0,0,0.5)'/%3E%3Cpath d='M25 20l15 10-15 10V20z' fill='white'/%3E%3C/svg%3E") no-repeat center;
}

.video_title {
  font-size: 1.1rem;
  font-weight: 900;
  color: #502314;
  line-height: 1.4;
}
</style>
