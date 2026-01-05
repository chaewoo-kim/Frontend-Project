<template>
  <section class="sticky_top">
    <nav class="tab01">
      <ul>
        <li :class="{ on: activeTab === 'ongoing' }">
          <button type="button" @click="$emit('change', 'ongoing')">
            <span>진행 중</span>
          </button>
        </li>
        <li :class="{ on: activeTab === 'end' }">
          <button type="button" @click="$emit('change', 'end')">
            <span>종료</span>
          </button>
        </li>
      </ul>
      <h2 class="hide">{{ activeTab === 'ongoing' ? '진행 중' : '종료' }}</h2>
    </nav>
  </section>
</template>

<script>
export default {
  name: 'EventTab',
  props: {
    activeTab: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>
.sticky_top {
  position: sticky;
  top: 90px;
  background: #f2ebe6; /* BK Beige */
  z-index: 99;
  padding-bottom: 0;
  margin-bottom: 60px;
}

.tab01 {
  max-width: 1144px;
  margin: 0 auto;
}

.tab01 ul {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
  border-bottom: 2px solid #502314; /* BK Brown border for the tab line */
}

.tab01 li {
  flex: 1;
  text-align: center;
}

.tab01 button {
  display: block;
  width: 100%;
  padding: 19px 0;
  background: none;
  border: none;
  font-size: 20px; /* Larger font */
  color: #b8b8b8;
  cursor: pointer;
  position: relative;
  font-family: var(--font-BKR);
  font-weight: 700;
}

/* Active State */
.tab01 li.on button {
  color: #e22219; /* BK Red */
  font-weight: 700;
}

.tab01 li.on button:after {
  content: '';
  position: absolute;
  bottom: -2px; /* Overlap border */
  left: 0;
  width: 100%;
  height: 6px; /* Thick underline */
  background: #e22219;
}

.tab01 button span {
  display: inline-block;
  vertical-align: middle;
}

.hide {
  position: absolute;
  width: 1px;
  height: 1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
}

@media (max-width: 1024px) {
  .sticky_top {
    top: 60px;
    padding: 0 16px;
  }
}
</style>