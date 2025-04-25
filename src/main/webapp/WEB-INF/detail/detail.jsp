<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="/WEB-INF/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/detail/detail.css">
<%@include file="/WEB-INF/include/category.jsp" %>
	<!-- 상품상세보기 시작 -->
	<div id="detail" class="inner">
		<!-- 상품정보 출력 영역 -->
		<div class="prod_info">
			상품정보 출력
		</div>
		<!-- // 상품정보 출력 end -->
		
		<!-- 상품 추천영역 -->
		<div class="prod_recommend">상품 추천영역(같은 카테고리 최신 5개 출력)</div>
		<!-- // 상품 추천영역 end -->
		
		<!-- 상품설명, 판매자 정보 출력 영역 -->
		<div class="discript_seller_wrap">
		
			<!-- 상품설명 영역 -->
			<div class="prod_descript">
				상품설명
			</div>
			
			<!-- 판매자 정보 -->
			<div class="seller_info ">
				상점(판매자)정보 출력
			</div>
			<!-- // 판매자 정보 end -->
		</div>
		<!-- // 상품설명, 판매자 정보 출력 영역 end -->
		
	</div>
	<!-- // 상품상세보기 end -->
	
	
<%@include file="/WEB-INF/include/footer.jsp" %>