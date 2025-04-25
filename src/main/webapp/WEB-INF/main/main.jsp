<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


  <script defer src="<%=request.getContextPath() %>/js/main/main.js"></script>
	<%@include file="/WEB-INF/include/header.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main/main.css">
<!-- SWIPER -->
	<!-- 메인 컨텐츠 시작 -->
	<div id="main">
		<!-- 카테고리 시작 -->
		
		<!-- // 카테고리 end -->
    <%@include file="/WEB-INF/include/category.jsp" %>

		<!-- 메인 슬라이드 시작 -->
    <div class="main_slide">
      
      <div class="inner">

        <div class="promo">
          <div class="swiper mySwiper">
            <div class="swiper-wrapper">
              <div class="swiper-slide">
                <a href="javascript:void(0)" class="btn">
                <img src="\remImg\promo(1).webp" alt="사진" />
                </a>
              </div>
              <div class="swiper-slide">
                <a href="javascript:void(0)" class="btn">
                <img src="\remImg\promo(2).webp" alt="사진" />
                </a>
              </div>
              <div class="swiper-slide">
                <a href="javascript:void(0)" class="btn">
                <img src="\remImg\promo(3).webp" alt="사진" />
                </a>
              </div>
              <div class="swiper-slide">
                <a href="javascript:void(0)" class="btn">
                <img src="\remImg\promo(4).webp" alt="사진" />
                </a>
              </div>
            </div>
            <div class="swiper-pagination"></div>
            <div class="swiper-button-prev arrow">
              <span class="material-symbols-outlined">arrow_back</span>
            </div>
            <div class="swiper-button-next arrow">
              <span class="material-symbols-outlined">arrow_forward</span>
            </div>
          </div>
        </div>

      </div>
      

    </div>
		<!-- // 메인 슬라이드 end -->
		
		<!-- 상품리스트 시작 -->
    <div class="list_wrap inner">
      <h1>오늘의 상품 추천</h1>
      
      <div class="inner list_cont">
        <!-- 상품리스트 내용 삽입 -->
        
        <div id="prod_list">
          <ul>
          </ul>
          <div class="buttons">
          </div>
        </div>
        
      </div>
    </div>
    <!-- // 상품리스트 end -->

    </div>
	<!-- // 메인 컨텐츠 end -->
	
<%@include file="/WEB-INF/include/footer.jsp" %>