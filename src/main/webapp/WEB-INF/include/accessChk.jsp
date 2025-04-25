<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common/access.css">

<div class="inner accss_chk_wrap">
	<h6>이 페이지에 접근 권한이 없습니다.</h6>
	<div id="user_btn_wrap">
		<a href="<%=request.getContextPath()%>/loginPage.do" id="conFirm">로그인</a>
		<a href="<%=request.getContextPath()%>/mainPage.do" id="grayBtn">메인으로</a>
	</div>
</div>

<%@include file="/WEB-INF/include/footer.jsp" %>