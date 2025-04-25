<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@include file="/WEB-INF/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/store/store.css">
<%@include file="/WEB-INF/include/category.jsp" %>
	<!-- 상점 상세보기 시작 -->
	<div id="store" class="inner">	
	
		<!-- 상점정보 -->
		<div class="store_info_wrap">
		
			<!-- 상점정보 - 왼쪽영역 -->
			<div class="store_info_box">
				<div class="thumb">
					<img src="<%=request.getContextPath() %>/images/shop/shop_info_img.png" alt="shop_info_img">
				</div>
				<b>정품수입멀티샵</b>
				<img src="<%=request.getContextPath() %>/images/shop/ico_point{$point_count}.png" alt="{$point_count}점">
				<p class="prod_cnt">상품 <span class="cnt">N</span></p>
			</div>
			<!-- // 상점정보 - 왼쪽영역 end -->
			
			<!-- 상점정보 - 오른쪽영역 -->
			<div class="store_info_txt_wrap">
				<div class="info_txt_tit">
					<!-- 타이틀 -->
					<h6>정품수입멀티샵</h6>
					<!-- // 타이틀 end  -->
					
					<!-- 본인인증 -->
					<div class="mem_ck">
						<% if(loginInfo.getMem_tel() != null){ %>
						<span class="material-symbols-outlined">check_circle</span>
						<p>본인인증 완료</p>
						<%} else { }%>
						
					</div>
					<!-- // 본인인증 end -->
				</div>
				
				<!-- 아이콘영역  -->
				<div class="store_info_icon_box">
					<ul>
						<li class="shop_open">
							<% Timestamp timestamp = Timestamp.valueOf(loginInfo.getMem_join_at());
							   SimpleDateFormat dateFormat = new SimpleDateFormat("yy.MM.dd");
        					   String shopOpenDate = dateFormat.format(timestamp);
							%>
							
							<span class="material-symbols-outlined">storefront</span>
							<div>상점오픈일 <span><%=shopOpenDate %></span></div>
						</li>
						<li class="shop_cart">
							<span class="material-symbols-outlined">shopping_cart</span>
							<div>상품판매 <span>4회</span></div>
						</li>
					</ul>
				</div>				
				<!-- // 아이콘영역 end  -->
				
				<div class="store_info_txt_box">
					<%= loginInfo.getPr_info() %>
					<p>
						100%정품🇱🇷해외직구 상품만 취급합니다!<br>
						정품 스토어🎁  택배 전국당일배송🚚<br>
						CJ 택배 당일 배송 (평균 배송 1일)<br>
						재고 있을시 사이즈 교환 ok 환불 no
					</p>
					<p>
						구매시 결제하시고 색상,사이즈 대화창에 말씀해주시면됩니다<br>
						프로상점은 안전결제로만 가능하십니다!!^^
					</p>
				</div>
				
				<div class="siren">
					<span class="material-symbols-outlined">siren</span>
					<span>신고하기</span>
				</div>
			</div>
			<!-- //상점정보 -오른쪽영역 end -->
			
		</div>
		<!-- // 상점정보 end -->
		
				
		<!-- 탭영역 -->
		<div class=store_tab_wrap>
		
			<!-- 상품설명 영역 -->
			<ul class="store_tab_box">
				<li class="on">
					<a href="javascript:void(0)">상품</a>
				</li>
				<li>
					<a href="javascript:void(0)">상점후기</a>
				</li>
				<li>
					<a href="javascript:void(0)">판매상품</a>
				</li>
			</ul>			
			<!-- // 판매자 정보 end -->
		</div>
		<!-- // 탭영역 -->
		
		
		<!-- 상품리스트 -->
		<div class="store_product_list_wrap">
			<h6 class="store_list_cnt">상품 <span>N</span></h6>
			<div class="store_list_filter">
				<ul>
					<li class="on"><a href="javascript:void(0)">최신순</a></li>
					<li><a href="javascript:void(0)">고가순</a></li>
					<li><a href="javascript:void(0)">저가순</a></li>
				</ul>
			</div>
		
			<%@include file="/WEB-INF/include/prodList.jsp" %>
		
		</div>
		<!-- // 상품리스트 end -->
		
		
	</div>
	<!-- // 상점 상세보기 end -->

<%@include file="/WEB-INF/include/footer.jsp" %>
	