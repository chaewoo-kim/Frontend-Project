<script>
import EventTab from '../components/EventTab.vue'
import EventCard from '../components/EventCard.vue'
import CommonHeader from '@/components/CommonHeader.vue'
import CommonFooter from '@/components/CommonFooter.vue'
import { getOngoingEvents, getEndedEvents } from '@/api/news'

export default {
  components: { EventTab, EventCard, CommonHeader, CommonFooter },

  data() {
    return {
      activeTab: 'ongoing',
      activeCategory: 'EVENT',
      events: [],
      visibleCount: 21
    }
  },

  computed: {
    // In a real API scenario, we might just use 'events' directly if the API returns the correct list per tab.
    // Assuming API returns all events for the requested type.
    displayedEvents() {
      return this.events.slice(0, this.visibleCount)
    },

    hasMore() {
      return this.visibleCount < this.events.length
    }
  },

  created() {
    this.syncStateWithRoute();
  },

  watch: {
    '$route.params.status'() {
      this.syncStateWithRoute();
    },
    '$route.name'() {
      this.syncStateWithRoute();
    }
  },

  methods: {
    async syncStateWithRoute() {
      const routeName = this.$route.name;
      const status = this.$route.params.status;

      if (routeName === 'NoticeList') {
        this.activeCategory = 'NOTICE';
        this.events = []; // Clear events or load notices if API existed
      } else {
        this.activeCategory = 'EVENT';
        if (status && ['ongoing', 'end'].includes(status)) {
          this.activeTab = status;
        } else {
          this.activeTab = 'ongoing';
        }
        await this.fetchEvents();
      }
    },

    async fetchEvents() {
      try {
        let response;
        if (this.activeTab === 'ongoing') {
          response = await getOngoingEvents();
        } else {
          response = await getEndedEvents();
        }
        
        let fetchedData = response.data || [];
        
        // Ensure at least 21 items for the 7x3 grid visual
        if (fetchedData.length > 0) {
          while (fetchedData.length < 21) {
            // Clone items to avoid reference issues
            const clones = fetchedData.map((item, index) => ({
               ...item,
               // Generate a unique ID for the key to avoid Vue errors
               id: `${item.id}_copy_${fetchedData.length + index}` 
            }));
            fetchedData = [...fetchedData, ...clones];
          }
        }
        
        this.events = fetchedData;
      } catch (error) {
        console.error("Failed to fetch events:", error);
        this.events = [];
      }
    },

    changeCategory(category) {
      this.activeCategory = category;
      if (category === 'NOTICE') {
        this.$router.push('/news/notice');
      } else {
        this.$router.push('/news/event/ongoing');
      }
    },
    changeTab(tab) {
      this.activeTab = tab;
      this.visibleCount = 21;
      this.$router.push(`/news/event/${tab}`);
    },

    goToDetail(id) {
      this.$router.push(`/news/event/detail/${id}`)
    },

    loadMore() {
      this.visibleCount += 21
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

            <div class="category_tab">
              <ul>
                <li :class="{ active: activeCategory === 'EVENT' }"><a href="#" @click.prevent="changeCategory('EVENT')">이벤트</a></li>
                <li :class="{ active: activeCategory === 'NOTICE' }"><a href="#" @click.prevent="changeCategory('NOTICE')">공지사항</a></li>
              </ul>
            </div>

            <!-- EVENT CONTENT -->
            <div v-if="activeCategory === 'EVENT'">
              <div class="title_page WEB">
                <h2 class="tit">이벤트</h2>
              </div>

              <EventTab :active-tab="activeTab" @change="changeTab" />

              <ul class="event_list" v-if="displayedEvents.length > 0">
                <EventCard
                    v-for="event in displayedEvents"
                    :key="event.id"
                    :event="event"
                    @click="goToDetail(event.id)"
                />
              </ul>

              <div v-else class="nodata">
                <p>이벤트가 없습니다.</p>
              </div>

              <div class="c_btn" v-if="hasMore">
                <button type="button" class="btn02 btn_more02" @click="loadMore">더보기</button>
              </div>
            </div>

            <!-- NOTICE CONTENT (Placeholder) -->
            <div v-if="activeCategory === 'NOTICE'" class="notice_wrap">
              <div class="title_page WEB">
                <h2 class="tit">공지사항</h2>
              </div>
              <div class="nodata">
                <p>등록된 공지사항이 없습니다.</p>
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
/* Page Layout styles matching provided CSS snippet */
.ion-page {
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 100vh;
}

.subWrap {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.contentsWrap {
  flex: 1;
}

.cont_min_area {
  min-height: 500px; /* Adjust as needed */
  background: #f5ebdcdc; /* BEIGE BACKGROUND for the whole area? or just page? */
}

/* Ensure body or parent has beige if needed, but here we set it on container if visible */

.web_container {
  max-width: 1144px;
  margin: 0 auto;
  padding: 50px 0 100px;
}

.title_page.WEB {
  text-align: center;
  margin-bottom: 40px;
}

.title_page .tit {
  font-size: 40px;
  font-weight: 800;
  color: #000;
  font-family: var(--font-BKR);
}

/* Category Tab Styles (Brown Pill) */
.category_tab {
  display: flex;
  justify-content: center;
  margin-bottom: 50px;
  position: relative;
}
.category_tab ul {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 30px;
  background-color: #502314; /* Dark Brown */
  border-radius: 50px;
  padding: 10px 40px;
  list-style: none;
  margin: 0;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}
.category_tab li { position: relative; }
.category_tab li a {
  display: block;
  font-size: 1.125rem;
  font-weight: 700;
  color: #f5ebdc; /* Beige Text */
  text-decoration: none;
  font-family: var(--font-BKR);
  transition: color 0.2s;
}

/* Active/Hover State */
.category_tab li.active a,
.category_tab li a:hover {
  color: #E2221F; /* Highlight color */
}
/* Revert underline style if any global styles exist */
.category_tab li.active a::after { content: none; }

/* Existing Styles */
.event_list {
  display: grid;
  grid-template-columns: repeat(3, 1fr); /* 3 Columns */
  gap: 60px 24px; /* Larger gap matching screenshot */
  list-style: none;
  padding: 0;
  margin: 0;
}

.nodata {
  text-align: center;
  padding: 100px 0;
  font-size: 18px;
  color: #666;
  border-bottom: 1px solid #ddd;
}

.c_btn {
  margin-top: 60px;
  text-align: center;
}

.btn02 {
  display: inline-block;
  min-width: 300px;
  height: 56px;
  line-height: 56px;
  border: 1px solid #d4c3b4;
  border-radius: 56px;
  background: #fff;
  font-size: 18px;
  font-weight: 700;
  color: #000;
  cursor: pointer;
  font-family: 'Black Han Sans', sans-serif;
}

@media (max-width: 1024px) {
  .web_container {
    padding: 30px 16px;
  }

  .event_list {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .event_list {
    grid-template-columns: 1fr;
  }
  .title_page .tit { font-size: 28px; }
  .btn02 { min-width: 100%; }
}
</style>
```
