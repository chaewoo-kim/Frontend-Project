<script>
import CommonHeader from '@/components/CommonHeader.vue'
import CommonFooter from '@/components/CommonFooter.vue'
import { getEventDetail } from '@/api/news'

export default {
  components: { CommonHeader, CommonFooter },
  data() {
    return {
      event: null
    }
  },
  async created() {
    const id = this.$route.params.id;
    await this.fetchEvent(id);
  },
  watch: {
    async '$route.params.id'(newId) {
      await this.fetchEvent(newId);
    }
  },
  methods: {
    async fetchEvent(id) {
      try {
        const response = await getEventDetail(id);
        this.event = response.data;
      } catch (error) {
        console.error("Failed to fetch event detail:", error);
        this.event = null;
      }
    },
    goList() {
      if (this.event && this.event.isOngoing) {
        this.$router.push('/news/event/ongoing')
      } else {
        this.$router.push('/news/event/end')
      }
    }
  }
}
</script>

<template>
  <div class="ion-page">
    <div class="subWrap">
      <CommonHeader />

      <section class="contentsWrap">
        <div class="cont_min_area">
          <div class="web_container">
            <div class="event_detail" v-if="event">
              <div class="page_tit">
                <span class="sub_tit">이벤트</span>
                <h2>{{ event.title }}</h2> <!-- Display Title here instead of Category for h2? No BK uses title as main, category small -->
              </div>

              <div class="event_info_wrap">
                <div class="badge_area">
                  <span class="badge ongoing" v-if="event.isOngoing">진행중</span>
                  <span class="badge days_left" v-if="event.daysLeft">{{ event.daysLeft }}일 남음</span>
                </div>
                <!-- <div class="event_title">{{ event.title }}</div> --> <!-- Title already in header? Or separate? BK usually has title in header area or just below -->
                
                <div class="event_period">
                  <span class="label">기간</span>
                  <span>{{ event.startDate.replace(/-/g, '.') }} ~ {{ event.endDate.replace(/-/g, '.') }}</span>
                </div>
              </div>

              <div class="detail_img">
                <img :src="event.image" :alt="event.title">
              </div>

              <div class="btn_area">
                <button type="button" class="btn01" @click="goList">목록</button>
              </div>
            </div>
            <div v-else class="nodata">
              <p>이벤트를 찾을 수 없습니다.</p>
              <div class="btn_area">
                <button type="button" class="btn01" @click="goList">목록으로 돌아가기</button>
              </div>
            </div>
          </div>
        </div>
        <CommonFooter />
      </section>
    </div>
  </div>
</template>

<style scoped>
.ion-page {
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 100vh;
  background-color: #fff; /* Detail page usually white content, beige bg maybe? */
}

.subWrap {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.contentsWrap {
  flex: 1;
}

.web_container {
  max-width: 1144px;
  margin: 0 auto;
  padding: 50px 20px 100px;
}

/* Header Section */
.page_tit {
  margin-bottom: 40px;
  border-bottom: 2px solid #000;
  padding-bottom: 20px;
}

.page_tit .sub_tit {
  display: block;
  font-size: 16px;
  color: #787878;
  margin-bottom: 10px;
  font-weight: 700;
}

.page_tit h2 {
  font-size: 40px;
  font-weight: 800;
  color: #000;
  margin: 0;
  font-family: var(--font-BKR);
}

/* Event Info Section */
.event_info_wrap {
  margin-bottom: 30px;
}

.badge_area {
  margin-bottom: 15px;
  display: flex;
  gap: 10px;
}

.badge {
  display: inline-block;
  padding: 4px 12px;
  border-radius: 4px;
  font-size: 14px;
  font-weight: 700;
  line-height: 1.5;
}

.badge.ongoing {
  background: #502314;
  color: #fff;
}

.badge.days_left {
  background: #fecdad;
  color: #e22219;
}

.event_period {
  font-size: 18px;
  color: #000;
  font-weight: 500;
  margin-bottom: 30px;
  display: flex;
  align-items: center;
  gap: 10px;
}
.event_period .label {
  font-weight: 700;
  margin-right: 10px;
}

.detail_img {
  text-align: center;
  margin-bottom: 60px;
}
.detail_img img {
  max-width: 100%;
}

.btn_area {
  margin-top: 60px;
  text-align: center;
}

.btn01 {
  display: inline-block;
  min-width: 200px;
  height: 56px;
  line-height: 56px;
  background: #e22219;
  color: #fff;
  font-size: 18px;
  font-weight: 700;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}

.nodata {
  text-align: center;
  padding: 100px 0;
  font-size: 18px;
}
</style>
