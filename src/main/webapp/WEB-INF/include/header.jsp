<%@page import="com.google.gson.Gson"%>
<%@page import="rem.login.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품상세</title>

<%
	MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
	String login = (String)session.getAttribute("login");
	String infoJson = new Gson().toJson(loginInfo);
%>
<script>
var socket = null;
mypath = '<%=request.getContextPath() %>';
loginState = '<%=login%>';
headerInfo = <%=infoJson%>;
console.log(loginState == 'null');




</script>

<link rel="icon" href="<%=request.getContextPath() %>/images/favicon.png">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/reset.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common/header.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common/category.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common/prodList.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common/footer.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main/main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/detail/detail.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/store/store.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login/login.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/modal/modal.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/modal/header-modal.css">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/modal/modal.js"></script>
<script defer type="text/javascript" src="<%=request.getContextPath() %>/js/header/header.js"></script>
<script defer type="text/javascript" src="<%=request.getContextPath() %>/js/header/notification.js"></script>
<script defer type="text/javascript" src="<%=request.getContextPath() %>/js/header/diposit.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

<!-- 포트원 결제 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>


<script>

$(function(){
	if(loginState == 'null'){
		$('.recent_box').html(/* html */`
			<span class="material-symbols-outlined">visibility</span>
			<p>최근 본 상품이 존재하지 않습니다.</p>
		`);
	} else {
		swiperView();
	}
});

</script>
</head>
<body>
    
	<!-- 전체 헤더 시작 -->
	<header class="hd_warp">
	
		<!-- 유틸리티 시작 (최상단 헤더) -->
		<div class="util">
			<div class="inner">
			<%
				if(login == null || "false".equals(login)){ // 로그아웃된 경우
					%>
					
				<div class="util_link">
					<a href="<%=request.getContextPath()%>/main/notice.do">공지사항</a>
				</div>
				<div class="util_user">
					<a href="<%= request.getContextPath() %>/loginPage.do">로그인</a>
				</div>
					<%
				} else { // 로그인
					%>
					
				<div class="util_link">
					<a href="<%=request.getContextPath()%>/main/notice.do">공지사항</a>
					<a href="<%=request.getContextPath()%>/main/qna.do">1:1문의</a>
					<a href="<%=request.getContextPath()%>/main/faq.do">자주묻는질문</a>
				</div>
				<div class="util_user">
					<div class="notification-starter">
						<a href="javascript:void(0)">알림</a>
						<div class="notification">
							<div class="arrow"></div>

							<ul>
								<!-- 알림 내역이 들어가는 위치 -->
							</ul>
						</div>
					</div>

					<div class="diposit-starter">
						<a href="javascript:void(0)">페이</a>
						<div class="diposit">
							<div class="arrow"></div>
							<div class="info">
								<h2>띹페이</h2>
								<span id="balance">
									잔액<span></span>원
								</span>
							</div>
							<ul>
								<!-- 페이 내역이 들어가는 위치 -->
							</ul>
							<div>
								<span id="header-modal-open" class="charge">충전하기</span>
							</div>
						</div>
					</div>
					<!-- ↓ 회원정보 수정페이지로 링크 연결 -->
					<a href="<%=request.getContextPath() %>/passwordCheckPage.do" class="user_name"><%=loginInfo.getMem_name() %></a>
					<a href="<%= request.getContextPath() %>/logoutProcess.do">로그아웃</a>
				</div>
					<%
				}
			%>
			</div>
		</div>
		<!-- // 유틸리티 end -->
		
		
		<!-- 헤더 시작 -->
		<div class="hd_cont">
			<div class="inner">
				<div class="logo_box">
					<h1>
						<a href="<%=request.getContextPath() %>/mainPage.do">
							<img src="<%=request.getContextPath() %>/images/favicon.png" alt="로고">
							<span>띹장터</span>
						</a>
					</h1>
				</div>
				<div class="search_box">
					<input type="text" id="searchText" placeholder="상품명, 상점명 입력" />
					<span class="search_icon material-symbols-outlined" id="searchBtn">search</span>
				</div>
				<div class="user_box">
					<a href="<%=request.getContextPath() %>/product/insertProduct.do" class="sell material-symbols-outlined">
						<span class="material-symbols-outlined">add_shopping_cart</span>
						<span>판매하기</span>
					</a>
					<a href="<%=request.getContextPath() %>/transManagement/management.do" class="sell material-symbols-outlined">
						<span class="material-symbols-outlined">sync_alt</span>
						<span>거래관리</span>
					</a>
					
					<a href="<%= request.getContextPath() %>/store/storePage.do?param=mem_no&value=<%=loginInfo!=null ? loginInfo.getMem_no() : "" %>" class="mypage material-symbols-outlined ">
					<span class="material-symbols-outlined">person</span>
						<span>마이페이지</span>
					</a>
					<a href="<%=request.getContextPath() %>/chat/dditTalk.do?mem_no" class="chat material-symbols-outlined tooltip">
						<span class="material-symbols-outlined">tooltip</span>
						<span>띹톡</span>
					</a>
				</div>
			</div>
		</div>
		<!-- // 헤더 end -->
		
		
		
		<!-- 퀵메뉴 시작 -->
		<div id="quick_wrap">
			<div class="wish">
				<h6>찜한상품</h6>
				<a href="javascript:void(0)">
					<span class="material-symbols-outlined">favorite</span>
					<span id="countWishlist"></span>
				</a>
			</div>
			<div class="recent_view">
				<h6>최근본상품</h6>
				<div class="recent_box">
					<!-- <span class="material-symbols-outlined">visibility</span>
					<p>최근 본 상품이 존재하지 않습니다.</p> -->
					<span id="viewListCount" style="color: #AD49E1"></span>

					<div class="view">
						<div class="swiper mySwiper">
							<div class="swiper-wrapper">
								
							</div>

							<div class="main_arrow_wrap">
								<div class="swiper-button-prev arrow">
									<span class="material-symbols-outlined">arrow_back</span>
								</div>
								<div class="swiper-pagination"></div>
								<div class="swiper-button-next arrow">
									<span class="material-symbols-outlined">arrow_forward</span>
								</div>
							</div>
							
						</div>
					</div>
					
				</div>
			</div>
			<div id="top_btn">
				<a href="javascript:void(0)" class="top_btn">TOP</a>
			</div>
		</div>
		<!-- // 퀵메뉴 끝 -->
	</header>
	<!-- // 전체 헤더 end -->
	
	<div class="header-container">		
		<div class="header-popup-wrap" id="header-popup">
			<div class="header-popup">
				<div class="header-popup-head">
						<span class="header-head-title">

						</span>
				</div>
				<div class="header-popup-body">

				</div>
				<div class="header-popup-foot">

				</div>
			</div>
		</div>
	</div>