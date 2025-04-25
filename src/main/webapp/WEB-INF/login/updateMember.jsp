<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>

<%
	System.out.println("loginSession :" + login);
	if(login == null) {
		response.sendRedirect(request.getContextPath() + "/accessCheck.do");
	}

	Gson gson = new Gson();
	String jsonMemInfo = gson.toJson(loginInfo);
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common/access.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login/pw_chk.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login/updateMember.css">

<script type="text/javascript">
  let member = <%=jsonMemInfo %>;
</script>
<!-- 우편번호 daum api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="<%=request.getContextPath() %>/js/login/daumAddressAPI.js"></script>
<script src="<%=request.getContextPath() %>/js/login/updateMember.js"></script>

<div class="inner">
	<div class="headText">
		<h4>회원 정보 수정</h4>
	</div>
	<div class="upData">
		<div class="dataInput">
			<input type="text" name="no" id="mem_no" hidden>
			<label for="email">
				<b>이메일</b>
				<input type="text" name="email" id="email" readonly>
			</label>
			<label for="name">
				<b>이 름</b>
				<input type="text" name="name" id="name">
				<span id="nameLen">3/10</span>
			</label>
			<label for="password">
				<b>비밀번호</b>
				<input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요.">
				<span id="pwLen">0/20</span>
			</label>
			<label for="password_ck">
				<b>비밀번호 확인</b>
				<input type="password" name="password_ck" id="password_ck" placeholder="비밀번호를 입력하세요.">
				<span id="pw_ck" class="material-symbols-outlined">check_circle</span>
			</label>
			<label for="phone">
				<b>연락처</b>
				<input type="text" name="phone" id="phone" placeholder="010-0000-0000">
			</label>
			<label for="address" id="addressLabel">
				<b>주 소</b>
				<span class="addr">
					<span>
						<input type="text" name="zipcode" id="zipcode" readonly placeholder="우편번호">
						<button type="button" id="addressBtn" onclick="DaumPostcode()">검색</button>
					</span>
					<input type="text" name="address" id="address" readonly placeholder="주소">
					<input type="text" name="address_detail" id="detailAddress" placeholder="상세주소">
				</span>
			</label>
			<div id="user_btn_wrap">
				<span id="udpateBtn">확인</span>
				<a href="<%=request.getContextPath()%>/mainPage.do" id="grayBtn">취소</a>
			</div>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/include/footer.jsp" %>