<template>
  <div class="subWrap">
    <CommonHeader />
    
    <div class="contentsWrap">
      <div class="cont_min_area">
        <div class="web_container">
          <div class="title_page WEB">
            <h2 class="tit">수상실적</h2>
          </div>

          <!-- Tabs & Filter Bar -->
          <section class="sticky_top">
            <nav class="tab01">
              <ul>
                <li class="on">
                  <button type="button"><span>연혁 및 수상내역</span></button>
                </li>
              </ul>
            </nav>
            <div class="filter_bar">
              <div class="custom_select_wrap">
                <button type="button" class="btn_filter" @click="toggleDropdown">
                  {{ selectedYearLabel }} <i class="ico_arrow" :class="{ open: isDropdownOpen }"></i>
                </button>
                <div v-if="isDropdownOpen" class="dropdown_menu">
                  <ul>
                    <li :class="{ active: selectedYear === 'all' }" @click="selectYear('all')">
                      <span class="ico_check" v-if="selectedYear === 'all'"></span>
                      모든 연도
                    </li>
                    <li v-for="year in sortedYears" :key="year" :class="{ active: selectedYear === year }" @click="selectYear(year)">
                      <span class="ico_check" v-if="selectedYear === year"></span>
                      {{ year }}
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </section>

          <!-- Awards List by Year (Sorted Descending) -->
          <div class="awards_list">
            <div v-for="year in sortedYears" :key="year" :id="'year-' + year" class="year_section">
              <h2 class="year_label">{{ year }}</h2>
              <div class="awards_grid">
                <div v-for="(award, index) in awardsByYear[year]" :key="index" class="award_card">
                  <div class="award_image">
                    <!-- Uniform placeholder image for all awards -->
                    <div class="icon_placeholder">
                      <i class="ico_trophy"></i>
                    </div>
                  </div>
                  <div class="award_info">
                    <p class="award_date">{{ award.date }}</p>
                    <h3 class="award_title">{{ award.title }}</h3>
                    <p class="award_org">{{ award.org }}</p>
                  </div>
                </div>
              </div>
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
import '@/assets/brand.css';

const isDropdownOpen = ref(false);
const selectedYear = ref('all');

const selectedYearLabel = computed(() => {
  return selectedYear.value === 'all' ? '모든 연도' : selectedYear.value;
});

const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value;
};

const selectYear = (year) => {
  selectedYear.value = year;
  isDropdownOpen.value = false;
  
  if (year === 'all') {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  } else {
    setTimeout(() => {
      const element = document.getElementById(`year-${year}`);
      if (element) {
        const offset = 120; // sticky header offset
        const bodyRect = document.body.getBoundingClientRect().top;
        const elementRect = element.getBoundingClientRect().top;
        const elementPosition = elementRect - bodyRect;
        const offsetPosition = elementPosition - offset;

        window.scrollTo({
          top: offsetPosition,
          behavior: 'smooth'
        });
      }
    }, 50);
  }
};

// Data from 2010-2024
const awardsByYear = ref({
  '2024': [
    { date: '2024년 08월', title: '부산국제광고제 Strategy Star 부문 크리스탈상 (아임낫킹)', org: '부산국제광고제' },
    { date: '2024년 12월', title: '대한민국광고대상 오디오부문 대상(두툼버거)', org: '대한민국광고대상' },
    { date: '2024년 12월', title: '대한민국광고대상 Brand Experience & Activation부문 은상(와퍼타투)', org: '대한민국광고대상' }
  ],
  '2023': [
    { date: '2023년 07월', title: '부산국제마케팅광고제 인포메이션 비디오 부분 동상', org: '다이렉트, 미디어, 전략부문 파이널 리스트' },
    { date: '2023년 07월', title: 'Youtube Works Award Best Brand Experience 부문 수상', org: '유튜브웍스 어워드' },
    { date: '2023년 07월', title: '대한민국광고대상 Social Communication 부문 은상', org: '대한민국광고대상' },
    { date: '2023년 12월', title: '대한민국광고대상 Creative Strategy 부문 동상', org: '대한민국광고대상' }
  ],
  '2022': [
    { date: '2022년 12월', title: 'DIGITAL FRANCHISEE OF THE YEAR', org: 'BURGER KING GLOBAL' },
    { date: '2022년 12월', title: 'FRANCHISEE OF THE YEAR', org: 'BURGER KING APAC' },
    { date: '2022년 12월', title: 'DIGITAL FRANCHISEE OF THE YEAR', org: 'BURGER KING APAC' },
    { date: '2022년 12월', title: '2022 프리미엄브랜드지수(KS-PBI) 패스트푸드 부문 1위', org: '한국표준협회(KSA)' },
    { date: '2022년 12월', title: '100대 프렌차이즈브랜드 선정', org: '매일경제신문' },
    { date: '2022년 12월', title: '고객신뢰도 1위 프리미엄브랜드 선정, 패스트푸드 부문 대상', org: '중앙일보S' },
    { date: '2022년 12월', title: '한국에서 가장 존경받는 기업 1위', org: '한국능률협회컨설팅(KMAC)' }
  ],
  '2021': [
    { date: '2021년 12월', title: '2021 코리아 프랜차이즈 어워즈', org: '포브스코리아' },
    { date: '2021년 12월', title: '글로벌 지속가능기업 100', org: 'UN SDGs' },
    { date: '2021년 12월', title: '프리미엄브랜드지수(KS-PBI) 패스트푸드 부문 1위', org: '한국표준협회(KSA)' },
    { date: '2021년 12월', title: '매경 100대 프랜차이즈 브랜드', org: '매일경제신문사' },
    { date: '2021년 12월', title: '한국에서 가장 존경받는 기업 1위', org: '한국능률협회컨설팅(KMAC)' },
    { date: '2021년 12월', title: 'DEVELOPER OF THE YEAR 2021', org: 'BURGER KING GLOBAL' }
  ],
  '2020': [
    { date: '2020년 12월', title: '프리미엄브랜드지수(KS-PBI) 패스트푸드 부문 1위', org: '한국표준협회(KSA)' },
    { date: '2020년 12월', title: '2020 에피 어워드(Effie Award) 코리아 식품 부문 은상', org: 'Effie' },
    { date: '2020년 12월', title: '한국에서 가장 존경받는 기업 1위', org: '한국능률협회컨설팅(KMAC)' },
    { date: '2020년 12월', title: '2019년 서울영상광고제 TV 부문 동상 수상', org: '서울영상광고제 집행위원회' }
  ],
  '2019': [
    { date: '2019년 12월', title: '서울 크리에이티브 페스티벌 동상', org: '서울영상광고제 집행위원회' },
    { date: '2019년 12월', title: 'MARKETER OF THE YEAR 2019', org: 'BURGER KING GLOBAL' }
  ],
  '2018': [
    { date: '2018년 12월', title: '제26회 국민이 선택한 좋은 광고상 OOH부문 좋은 광고상', org: '한국광고주협회' },
    { date: '2018년 12월', title: '웹어워드 코리아 프로모션 통합 대상', org: '한국인터넷전문가협회(KIPFA)' },
    { date: '2018년 12월', title: 'MARKETER OF THE YEAR 2018', org: 'BURGER KING GLOBAL' }
  ],
  '2017': [
    { date: '2017년 12월', title: '2016년 서울영상광고제 TV 부문 동상 수상', org: '서울영상광고제 집행위원회' },
    { date: '2017년 12월', title: '2017 칸 국제광고제 미디어부문 은상', org: '칸 국제광고제' },
    { date: '2017년 12월', title: 'DEVELOPER OF THE YEAR 2017', org: 'BURGER KING GLOBAL' }
  ],
  '2016': [
    { date: '2016년 12월', title: '제24회 국민이 선택한 좋은 광고상 문체부 장관상', org: '한국광고주협회' },
    { date: '2016년 12월', title: 'MARKETER OF THE YEAR 2016', org: 'BURGER KING GLOBAL' }
  ],
  '2015': [
    { date: '2015년 12월', title: '한국 산업 서비스 품질지수 고객접점 부문 1위', org: '한국능률협회컨설팅(KMAC)' },
    { date: '2015년 12월', title: 'DEVELOPER OF THE YEAR 2015', org: 'BURGER KING GLOBAL' }
  ],
  '2014': [
    { date: '2014년 12월', title: 'FRANCHISEE OF THE YEAR 2014', org: 'BURGER KING GLOBAL' }
  ],
  '2012': [
    { date: '2012년 12월', title: 'DEVELOPER OF THE YEAR 2012', org: 'BURGER KING GLOBAL' }
  ],
  '2011': [
    { date: '2011년 12월', title: '한국서비스품질지수 패스트푸드 부문 1위', org: '한국능률협회컨설팅(KMAC)' },
    { date: '2011년 12월', title: 'DEVELOPER OF THE YEAR 2011', org: 'BURGER KING GLOBAL' },
    { date: '2011년 12월', title: 'FRANCHISEE OF THE YEAR 2011', org: 'BURGER KING GLOBAL' }
  ],
  '2010': [
    { date: '2010년 12월', title: 'FRANCHISEE OF THE YEAR 2010', org: 'BURGER KING GLOBAL' }
  ]
});

const sortedYears = computed(() => {
  return Object.keys(awardsByYear.value).sort((a, b) => parseInt(b) - parseInt(a));
});
</script>

<style scoped>
.awards_list {
  padding: 40px 0;
}

.year_section {
  margin-bottom: 60px;
}

.year_label {
  font-family: 'Flame', sans-serif;
  font-size: 3rem;
  font-weight: 900;
  color: #502314;
  margin-bottom: 30px;
}

.awards_grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 30px;
}

.award_card {
  display: flex;
  gap: 20px;
  align-items: flex-start;
}

.award_image {
  flex: 0 0 160px;
  height: 160px;
  background-color: #EFE3D3;
  border-radius: 4px;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}

.award_image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.icon_placeholder {
  width: 80px;
  height: 80px;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%23C7B299'%3E%3Cpath d='M19 5h-2V3H7v2H5c-1.1 0-2 .9-2 2v1c0 2.55 1.92 4.63 4.39 4.94A5.01 5.01 0 0 0 11 15.9V19H7v2h10v-2h-4v-3.1a5.01 5.01 0 0 0 3.61-2.96C19.08 12.63 21 10.55 21 8V7c0-1.1-.9-2-2-2zM5 8V7h2v3.82C5.84 10.4 5 9.3 5 8zm14 0c0 1.3-.84 2.4-2 2.82V7h2v1z'/%3E%3C/svg%3E");
  background-size: contain;
  background-repeat: no-repeat;
  opacity: 0.6;
}

.award_info {
  flex: 1;
}

.award_date {
  font-size: 0.9rem;
  font-weight: 700;
  color: #7A695A;
  margin-bottom: 8px;
}

.award_title {
  font-size: 1.25rem;
  font-weight: 900;
  color: #502314;
  line-height: 1.3;
  margin-bottom: 12px;
  word-break: keep-all;
}

.award_org {
  font-size: 1rem;
  font-weight: 700;
  color: #502314;
}

.sticky_top {
  position: sticky;
  top: 0;
  z-index: 100;
  background-color: var(--bg-base);
  border-bottom: 1px solid #E5E5E5;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-right: 20px;
}

.tab01 { border-bottom: none; }

.filter_bar {
  display: flex;
  align-items: center;
}

.custom_select_wrap {
  position: relative;
}

.btn_filter {
  background: none;
  border: none;
  font-weight: 900;
  color: #502314;
  font-size: 1.1rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
}

.ico_arrow {
  display: inline-block;
  width: 0;
  height: 0;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-top: 5px solid #502314;
  transition: transform 0.3s;
}

.ico_arrow.open {
  transform: rotate(180deg);
}

/* Custom Dropdown Styling based on provided image */
.dropdown_menu {
  position: absolute;
  top: 100%;
  right: 0;
  margin-top: 10px;
  width: 140px;
  background-color: #5F594D; /* Dark grey similar to image */
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0,0,0,0.2);
  z-index: 101;
}

.dropdown_menu ul {
  list-style: none;
  padding: 8px 0;
  margin: 0;
}

.dropdown_menu li {
  padding: 10px 16px;
  color: #FFFFFF;
  font-size: 1.1rem;
  font-weight: 700;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 10px;
  transition: background-color 0.2s;
}

.dropdown_menu li:hover {
  background-color: #4F4A3F;
}

.dropdown_menu li.active {
  color: #FFFFFF;
}

.ico_check {
  display: inline-block;
  width: 14px;
  height: 14px;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='white'%3E%3Cpath d='M9 16.17L4.83 12l-1.42 1.41L9 19 21 7l-1.41-1.41z'/%3E%3C/svg%3E");
  background-size: contain;
  background-repeat: no-repeat;
}

@media screen and (max-width: 1024px) {
  .awards_grid {
    grid-template-columns: 1fr;
  }
  .year_label { font-size: 2rem; }
}
</style>
