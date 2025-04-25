<%@page import="com.google.gson.Gson"%>
<%@page import="rem.login.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>띹장터 관리자 페이지</title>
<link rel="icon" href="<%=request.getContextPath() %>/images/favicon.png">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/reset.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/common.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/header/header.css">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/admin/admin.js" defer></script>
</head>

<%

	MemberVO adminInfo = (MemberVO)session.getAttribute("loginInfo");
	String login = (String)session.getAttribute("login");

	Gson gson = new Gson();
	String jsonMemInfo = gson.toJson(adminInfo);
	request.setAttribute("jsonMemInfo", jsonMemInfo);
	

	System.out.println("loginSession :" + login);
	if(login == null) {
		response.sendRedirect(request.getContextPath() + "/accessCheck.do");
	}
	
	if(adminInfo != null && adminInfo.getMem_lv() == 0){
		response.sendRedirect(request.getContextPath() + "/mainPage.do");
	}
%>

<script>
	const adminPath = "<%=request.getContextPath() %>";
	const loginCk = "<%= login %>";
</script>

<body>
<header id="hd">
    <h1>띹장터</h1>
    <div id="hd_top">
        <div id="logo"><a href="<%= request.getContextPath() %>/admin/selectAdminMain.do"><img src="https://demo.sir.kr/gnuboard5/adm/img/logo.png" alt="그누보드5(영카트5) 관리자" title=""></a></div>

        <div id="tnb">
            <ul>
            <%
            	if(adminInfo != null){
            		%>
				<li class="tnb_li">관리자 <%=adminInfo.getMem_name() %>님</li>
            		<%
            	}
            %>
				<li class="tnb_li"id="tnb_logout">
					<a href="<%= request.getContextPath() %>/logoutProcess.do">로그아웃</a>
				</li>
            </ul>
        </div>
    </div>
    <nav id="gnb" class="gnb_large ">
        <h2>관리자 주메뉴</h2>
        <ul class="gnb_ul">
            <li class="gnb_li on">
                <a href="<%= request.getContextPath() %>/admin/selectAdminMain.do" class="btn_op menu-100" title="메인">
					<span class="material-symbols-outlined">home</span>
					메인
                </a>
            </li>
            <li class="gnb_li">
                <a href="<%= request.getContextPath() %>/admin/selectAllMemberList.do" class="btn_op menu-200" title="회원관리">
					<span class="material-symbols-outlined">person</span>
					회원관리
                </a>
            </li>
            <li class="gnb_li">
                <a href="javascript:void(0)" class="btn_op menu-300" title="글관리">
					<span class="material-symbols-outlined">menu</span>
					글관리
                </a>
                <div class="gnb_oparea_wr">
                    <div class="gnb_oparea">
                        <ul>
                            <li><a href="<%= request.getContextPath() %>/admin/prodListPage.do" class="gnb_2da">상품 게시판</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/noticePage.do" class="gnb_2da">공지시항</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/qnaPage.do" class="gnb_2da gnb_grp_style gnb_grp_div">문의</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/faqBoardView.do" class="gnb_2da gnb_grp_style gnb_grp_div">FAQ</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/reportBoardPage.do" class="gnb_2da  ">신고</a></li>
                        </ul>
                    </div>
                </div>
            </li>
            <li class="gnb_li">
                <a href="javascript:void(0)" class="btn_op menu-400" title="상품관리">
					<span class="material-symbols-outlined">sell</span>
					상품관리
                </a>
                <div class="gnb_oparea_wr">
                    <div class="gnb_oparea">
                        <ul>
                            <li><a href="<%= request.getContextPath() %>/admin/categoryPage.do" class="gnb_2da  ">카테고리 수정</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/reviewList.do" class="gnb_2da gnb_grp_style gnb_grp_div">후기 내역</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/wishlistPage.do" class="gnb_2da gnb_grp_style gnb_grp_div">찜내역</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/viewList.do" class="gnb_2da">조회내역</a></li>
                        </ul>
                    </div>
                </div>
            </li>
            <li class="gnb_li">
                <a href="javascript:void(0)" class="btn_op menu-500 menu-order-5" title="쇼핑몰현황/기타">
					<span class="material-symbols-outlined">shopping_cart</span>
					거래관리
                </a>
                <div class="gnb_oparea_wr">
                    <div class="gnb_oparea">
                        <ul>
                            <li><a href="<%=request.getContextPath() %>/admin/transPage.do" class="gnb_2da  ">거래관리</a></li>
                            <li><a href="<%=request.getContextPath() %>/admin/safeTXNBoardPage.do" class="gnb_2da  ">안심 거래 관리</a></li>
                            <li><a href="" class="gnb_2da gnb_grp_style gnb_grp_div">페이관리</a></li>
                        </ul>
                    </div>
                </div>
            </li>
            <li class="gnb_li">
                <a href="javascript:void(0)" class="btn_op menu-900 menu-order-6" title="채팅관리">
					<span class="material-symbols-outlined">forum</span>
					채팅관리
                </a>
                <div class="gnb_oparea_wr">
                    <div class="gnb_oparea">
                        <ul>
                            <li><a href="<%=request.getContextPath() %>/admin/chatRoomPage.do" class="">채팅방관리</a></li>
                            <li><a href="<%=request.getContextPath() %>/admin/messageList.do" class="gnb_2da  ">메세지 관리</a></li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
    </nav>

</header>