<template>
  <li>
    <div class="cont" @click="$emit('click')">
      <!-- Badge Logic -->
      <em class="txt_event_noti" v-if="event.isOngoing && event.daysLeft > 0">{{ event.daysLeft }} 일 남았어요!</em>
      <em class="txt_event_noti" v-else-if="event.isOngoing && event.daysLeft === 0">오늘이 마지막이에요</em>
      <em class="txt_event_noti end" v-else-if="!event.isOngoing">종료</em>

      <div class="image">
        <!-- New Badge (Optional overlap if needed, keeping simple for now based on snippet) -->
        <img :src="event.image" :alt="event.title">
      </div>

      <p class="tit_event">
        <span class="txt_box03">{{ event.category || '일반' }}</span>
        <span>{{ event.title }}</span>
      </p>
    </div>
    <button type="button" class="btn_detail"><span>detail</span></button>
  </li>
</template>

<script>
export default {
  name: 'EventCard',
  props: {
    event: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
li {
  position: relative;
  cursor: pointer;
}

.cont {
  position: relative;
  border-radius: 12px;
  background-color: #fff;
  overflow: hidden;
  box-shadow: 0 5px 15px rgba(0,0,0,0.05);
  height: 100%;
  display: flex;
  flex-direction: column;
}

/* Badge (D-Day / End) */
.txt_event_noti {
  position: absolute;
  top: 0;
  left: 0;
  background: #e22219;
  color: #fff;
  padding: 6px 16px;
  border-bottom-right-radius: 12px;
  border-top-left-radius: 12px; /* Optional adjustment if border-radius on cont clips it */
  font-size: 16px;
  font-weight: 800;
  font-style: normal;
  z-index: 2;
  line-height: 1.2;
}

.txt_event_noti.end {
  background: #512314;
}

/* Image Area */
.image {
  position: relative;
  width: 100%;
  overflow: hidden;
  display: flex;
  align-items: flex-start;
}

.image img {
  width: 100%;
  height: auto;
  object-fit: contain;
  object-position: top left;
  transition: transform 0.3s ease;
  display: block;
}

.cont:hover .image img {
  transform: scale(1.05);
}

.flag_new {
  position: absolute;
  top: 0;
  right: 0;
  background: url('https://www.burgerking.co.kr/dist/img/icon_new.png') no-repeat center / contain;
  width: 54px;
  height: 54px;
  text-indent: -9999px;
  z-index: 2;
}

/* Title Area */
.tit_event {
  padding: 24px 20px 30px;
  margin: 0;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 8px;
  flex: 1;
}

/* Category Badge */
.txt_box03 {
  display: inline-block;
  font-size: 14px;
  font-weight: 700;
  color: #000;
  text-decoration: underline;
  text-underline-offset: 4px;
}

/* Title Text */
.tit_event span:last-child {
  font-size: 22px;
  font-weight: 800;
  color: #000;
  line-height: 1.4;
  word-break: keep-all;
}

/* Detail Button (Hidden/Overlay?) 
   In the provided HTML, it's a sibling. 
   We'll make it part of the click area or just visually hidden 
   if the card click handles it. 
   Usually in list items, it might be an arrow icon.
*/
.btn_detail {
  display: none; /* Hiding for now unless specific icon style is known, as li click works */
}

@media (max-width: 768px) {
  .tit_event span:last-child {
    font-size: 18px;
  }
}
</style>
