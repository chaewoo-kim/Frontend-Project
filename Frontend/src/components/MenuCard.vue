<template>
  <div class="menu_card" @click="$emit('click')">
    <div class="card_flag">
       <em v-for="badge in menu.badges" :key="badge" 
           :class="getBadgeColor(badge)">
           {{ mapBadgeText(badge) }}
       </em>
    </div>
    
    <div class="prd_image">
      <span>
        <img 
          :src="menu.imageUrl || 'https://via.placeholder.com/400x400?text=No+Image'" 
          :alt="menu.name" 
        />
      </span>
    </div>

    <div class="cont">
      <p class="tit">
        <span>{{ menu.name }}</span>
      </p>
      
      <div class="set_info">
        <span>{{ menu.menuComposition }}</span>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  menu: Object
});

const getBadgeColor = (badge) => {
  switch(badge) {
    case 'NEW': return 'bg-[#E2221F]';      // Red
    case 'POPULAR': return 'bg-[#F58F00]';  // Orange (BEST)
    default: return 'bg-[#502314]';         // Dark Brown default
  }
};

const mapBadgeText = (badge) => {
    if(badge === 'POPULAR') return 'BEST';
    return badge;
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Noto+Sans+KR:wght@400;500;700;900&display=swap');

.menu_card { 
    position: relative; 
    height: 100%; 
    padding: 28px 8px 10px; 
    background-color: var(--bg-light); 
    box-shadow: 0 0 10px rgba(0, 0, 0, .05); 
    border-radius: 10px;
    display: flex;
    flex-direction: column; 
}

.menu_card .prd_image { 
    position: relative; 
    margin: 0 auto; 
    min-width: 100%; 
    min-height: auto; 
    max-height: 105px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.prd_image img {
    max-width: 100%;
    max-height: 100%;
    object-fit: contain;
}

.menu_card .cont {
    margin-top: 5px;
    font-size: .9375rem;
    text-align: center;
    flex: 1;
    display: flex;
    flex-direction: column;
}

.cont .tit {
    font-size: 1.25rem;
    color: #502314;
    margin-bottom: 4px;
}

.cont .set_info {
    color: #888;
    font-size: 13px;
    line-height: 1.4;
}

/* Badge Styles */
.card_flag {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 5;
  padding: 12px;
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.card_flag em {
  display: inline-block;
  height: 24px;
  padding: 0 8px;
  border-radius: 4px;
  font-style: normal;
  font-size: 11px;
  font-weight: 800;
  color: #fff;
  line-height: 24px;
  text-align: center;
}
.card_flag em.bg-_E2221F { background-color: #E2221F; }
.card_flag em.bg-_F58F00 { background-color: #F58F00; }
.card_flag em.bg-white { background-color: #fff; color: #E2221F; border: 1px solid #E2221F; }
</style>
