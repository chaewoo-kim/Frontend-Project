<template>
  <footer class="footer">
    <div class="container">
      <div class="footer_content">
        <!-- Left Section: Logo -->
        <div class="footer_left">
          <h1>
            <router-link to="/main" class="footer_logo">
              <img src="@/assets/img/bg_logo_b.svg" alt="Burger King">
            </router-link>
          </h1>
        </div>

        <!-- Center Section: Terms and Company Info -->
        <div class="footer_center">
          <div class="terms_menu">
            <router-link to="/terms">이용약관</router-link>
            <router-link to="/privacy" class="txt_line">개인정보처리방침</router-link>
            <router-link to="/location">위치정보 이용약관</router-link>
            <router-link to="/legal">법적고지</router-link>
            <router-link to="/video-policy">고정형 영상정보처리기기 운영관리방침</router-link>
          </div>
          <div class="company_info">
            <p>서울 특별시 종로구 삼봉로 71 G타워 4F,5F</p>
            <p>사업자등록번호 : 101-86-76277 <a href="#" class="txt_line">사업자정보확인</a></p>
            <p>통신판매업 신고 : 2016-서울종로-1050</p>
            <p>㈜ 비케이알 대표이사 : 이동형</p>
            <p>©BKR Co., Ltd. All right Reserved</p>
          </div>
        </div>

        <!-- Right Section: Recruit and SNS -->
        <div class="footer_right">
          <div class="recruit_area">
            <a href="https://bkr.recruiter.co.kr/career/home" class="btn_recruit" target="_blank" rel="noopener noreferrer">
              채용사이트
              <i class="icon_arrow"></i>
            </a>
          </div>
          <div class="social_area">
            <div class="sns_icons">
              <a href="https://www.instagram.com/burgerkingkorea/" class="btn_sns" title="Instagram" target="_blank" rel="noopener noreferrer"><img src="@/assets/img/ico_sns_instagram.svg" alt="Instagram"></a>
              <a href="https://www.facebook.com/burgerkingkorea" class="btn_sns" title="Facebook" target="_blank" rel="noopener noreferrer"><img src="@/assets/img/ico_sns_facebook.svg" alt="Facebook"></a>
              <a href="https://x.com/BurgerKing_KOR" class="btn_sns" title="X (Twitter)" target="_blank" rel="noopener noreferrer"><img src="@/assets/img/ico_sns_x.svg" alt="X"></a>
              <a href="https://www.youtube.com/@burgerking_korea" class="btn_sns" title="Youtube" target="_blank" rel="noopener noreferrer"><img src="@/assets/img/ico_sns_youtube.svg" alt="Youtube"></a>
            </div>
            <div class="vertical_line"></div>
            <div class="family_brand">
              <a href="https://www.tim-hortons.co.kr/WEB0100" class="btn_sns" title="Tim Hortons" target="_blank" rel="noopener noreferrer"><img src="@/assets/img/ico_timhortons.svg" alt="Tim Hortons"></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup>
</script>

<style scoped>
.footer {
  background-color: #FDF9F3;
  border-top: 1px solid #E5E5E5;
  padding: 60px 0;
  width: 100%;
}

.footer .container {
  max-width: 1520px;
  margin: 0 auto;
  padding: 0 40px;
}

.footer_content {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: 40px;
}

/* Left Section */
.footer_left {
  flex-shrink: 0;
}

.footer_logo img {
  height: 24px;
  width: auto;
}

/* Center Section */
.footer_center {
  flex: 1;
  max-width: 700px;
}

.terms_menu {
  display: flex;
  flex-wrap: wrap;
  gap: 24px;
  margin-bottom: 20px;
}

.terms_menu a {
  font-size: 0.85rem;
  color: #8D7E73;
  text-decoration: none;
  white-space: nowrap;
}

.terms_menu a.txt_line {
  font-weight: 700;
  text-decoration: underline;
}

.company_info {
  font-size: 0.8rem;
  color: #8D7E73;
  line-height: 1.6;
}

.company_info p {
  margin: 0;
  margin-bottom: 4px;
}

.company_info a.txt_line {
  text-decoration: underline;
  color: inherit;
  font-weight: 700;
}

/* Right Section */
.footer_right {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 30px;
}

.btn_recruit {
  display: inline-flex;
  align-items: center;
  background-color: #502314;
  color: #fff;
  padding: 10px 24px;
  border-radius: 40px;
  font-weight: 700;
  font-size: 0.9rem;
  text-decoration: none;
  transition: opacity 0.2s;
}

.btn_recruit:hover {
  opacity: 0.9;
}

.icon_arrow {
  display: inline-block;
  width: 18px;
  height: 18px;
  margin-left: 8px;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='white'%3E%3Cpath d='M12 4l-1.41 1.41L16.17 11H4v2h12.17l-5.58 5.59L12 20l8-8z'/%3E%3C/svg%3E");
  background-size: contain;
  background-repeat: no-repeat;
  filter: rotate(-45deg); /* Pointing up-right as in the image */
}

.social_area {
  display: flex;
  align-items: center;
  gap: 16px;
}

.sns_icons {
  display: flex;
  gap: 12px;
}

.btn_sns {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 32px;
  height: 32px;
  transition: transform 0.2s;
}

.btn_sns img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}

.vertical_line {
  width: 1px;
  height: 24px;
  background-color: #E5E5E5;
}

/* Responsive */
@media screen and (max-width: 1200px) {
  .footer_content {
    flex-wrap: wrap;
    justify-content: center;
    text-align: center;
  }

  .footer_center {
    max-width: 100%;
    order: 2;
  }

  .footer_right {
    align-items: center;
    order: 3;
    width: 100%;
  }

  .terms_menu {
    justify-content: center;
  }
}

@media screen and (max-width: 768px) {
  .footer {
    padding: 40px 0;
  }
  
  .footer_logo_text {
    font-size: 1.8rem;
  }

  .terms_menu {
    gap: 12px;
  }
}
</style>
