<template>
  <footer class="footer">
    <div class="footer_container">
      <div class="footer_left">
        <h2 class="footer_logo">BURGER KING</h2>
        <div class="footer_content">
          <nav class="footer_links">
            <router-link to="/terms">이용약관</router-link>
            <router-link to="/privacy" class="bold">개인정보처리방침</router-link>
            <router-link to="/location">위치정보 이용약관</router-link>
            <router-link to="/legal">법적고지</router-link>
            <router-link to="/video-policy">고정형 영상정보처리기기 운영관리방침</router-link>
          </nav>
          <div class="footer_info">
            <p>서울특별시 종로구 삼봉로 71 G타워 4F,5F</p>
            <p>
              사업자등록번호 : 101-86-76277 
              <a href="#" class="btn_info_check">사업자정보확인</a>
            </p>
            <p>통신판매업 신고 : 2016-서울종로-1050</p>
            <p>(주) 비케이알 대표이사 : 이동형</p>
            <p class="copyright">©BKR Co., Ltd. All right Reserved</p>
          </div>
        </div>
      </div>
      
      <div class="footer_right">
        <a href="#" class="btn_recruit">
          채용사이트
          <i class="ico_outlink"></i>
        </a>
        <div class="social_links">
          <a href="#" class="sns_icon insta"></a>
          <a href="#" class="sns_icon face"></a>
          <a href="#" class="sns_icon x"></a>
          <a href="#" class="sns_icon youtube"></a>
          <span class="divider"></span>
          <a href="#" class="sns_icon maple"></a>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup>
</script>

<style scoped>
.footer {
  background-color: #FDF9F3; /* Very light beige */
  border-top: 1px solid #E5E5E5;
  padding: 60px 0;
  width: 100%;
}

.footer_container {
  max-width: 1140px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  padding: 0 20px;
}

.footer_left {
  display: flex;
  gap: 60px;
}

.footer_logo {
  font-family: 'Flame', sans-serif;
  font-size: 2.2rem;
  font-weight: 900;
  color: #B2A296; /* Greyish brown from image */
  margin: 0;
  white-space: nowrap;
}

.footer_content {
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.footer_links {
  display: flex;
  flex-wrap: wrap;
  gap: 25px;
}

.footer_links a {
  font-size: 0.95rem;
  color: #8D7E73;
  text-decoration: none;
  font-weight: 500;
}

.footer_links a.bold {
  font-weight: 900;
  text-decoration: underline;
}

.footer_info {
  font-size: 0.9rem;
  color: #8D7E73;
  line-height: 1.6;
}

.footer_info p {
  margin: 0;
}

.btn_info_check {
  font-weight: 900;
  text-decoration: underline;
  margin-left: 10px;
  color: inherit;
}

.copyright {
  margin-top: 10px !important;
  font-size: 0.85rem;
}

.footer_right {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 40px;
}

.btn_recruit {
  background-color: #502314; /* Dark brown */
  color: #fff;
  padding: 12px 25px;
  border-radius: 30px;
  font-weight: 900;
  text-decoration: none;
  font-size: 0.95rem;
  display: flex;
  align-items: center;
  gap: 8px;
}

.ico_outlink {
  width: 14px;
  height: 14px;
  background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 14 14'%3E%3Cpath d='M3 1h10v10M13 1L1 13' fill='none' stroke='white' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'/%3E%3C/svg%3E") no-repeat center;
}

.social_links {
  display: flex;
  align-items: center;
  gap: 15px;
}

.sns_icon {
  width: 32px;
  height: 32px;
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center;
}

.sns_icon.insta {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 32 32'%3E%3Cpath d='M16 2.8c4.3 0 4.8 0 6.5.1 1.6.1 2.4.3 3 .6.7.3 1.3.7 1.8 1.2.5.5 1 1.1 1.2 1.8.2.6.5 1.4.6 3 .1 1.7.1 2.2.1 6.5s0 4.8-.1 6.5c-.1 1.6-.3 2.4-.6 3-.3.7-.7 1.3-1.2 1.8-.5.5-1.1 1-1.8 1.2-.6.2-1.4.5-3 .6-1.7.1-2.2.1-6.5.1s-4.8 0-6.5-.1c-1.6-.1-2.4-.3-3-.6-.7-.3-1.3-.7-1.8-1.2-.5-.5-1-1.1-1.2-1.8-.2-.6-.5-1.4-.6-3-.1-1.7-.1-2.2-.1-6.5s0-4.8.1-6.5c.1-1.6.3-2.4.6-3s.7-1.3 1.2-1.8.5.5-1 1.1-1.8-1.2-1.8.2-.6 1.4-.5 1-3 1.6-1.7.1-2.2.1-6.5.1z' fill='%23E1306C'/%3E%3Cpath d='M16 7.8c-4.5 0-8.2 3.7-8.2 8.2s3.7 8.2 8.2 8.2 8.2-3.7 8.2-8.2-3.7-8.2-8.2-8.2zm0 13.5c-2.9 0-5.3-2.4-5.3-5.3s2.4-5.3 5.3-5.3 5.3 2.4 5.3 5.3-2.4 5.3-5.3 5.3zm8.5-12.7c0 1-.8 1.9-1.9 1.9s-1.9-.8-1.9-1.9.8-1.9 1.9-1.9 1.9.9 1.9 1.9z' fill='white'/%3E%3C/svg%3E");
}

/* For other social icons, I will use circles with simplified logos if SVGs are complex */
.sns_icon.face { background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 32 32'%3E%3Ccircle cx='16' cy='16' r='14' fill='%231877F2'/%3E%3Cpath d='M21 16.5h-3V25h-4v-8.5h-2v-4h2V10c0-2.5 1.5-4 4-4 1.2 0 2.2.1 2.5.1v3h-2c-1.2 0-1.5.6-1.5 1.5v2h3.5l-.5 4z' fill='white'/%3E%3C/svg%3E"); }
.sns_icon.x { background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 32 32'%3E%3Ccircle cx='16' cy='16' r='14' fill='black'/%3E%3Cpath d='M18.5 16l6 8h-2.5l-4.5-6.2L13 24H10l6.5-8.5-5.5-7.5h2.5l4 5.5L22 8h3l-6.5 8z' fill='white'/%3E%3C/svg%3E"); }
.sns_icon.youtube { background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 32 32'%3E%3Ccircle cx='16' cy='16' r='14' fill='%23FF0000'/%3E%3Cpath d='M22 16l-8-5v10z' fill='white'/%3E%3C/svg%3E"); }
.sns_icon.maple { background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 32 32'%3E%3Ccircle cx='16' cy='16' r='14' fill='%23F5EBDC'/%3E%3Cpath d='M16 8l1 3 3 1-3 1-1 3-1-3-3-1 3-1 1-3z' fill='%23D62300'/%3E%3C/svg%3E"); }

.divider {
  width: 1px;
  height: 20px;
  background-color: #E5E5E5;
  margin: 0 5px;
}

@media screen and (max-width: 1024px) {
  .footer_container {
    flex-direction: column;
    gap: 40px;
    align-items: center;
    text-align: center;
  }
  .footer_left {
    flex-direction: column;
    align-items: center;
    gap: 30px;
  }
  .footer_links {
    justify-content: center;
  }
  .footer_right {
    align-items: center;
  }
}
</style>
