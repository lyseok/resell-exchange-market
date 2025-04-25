<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>

<%
	System.out.println("loginSession :" + login);
	if(login == null) {
		response.sendRedirect(request.getContextPath() + "/accessCheck.do");
	}

%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common/access.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login/pw_chk.css">

<script>
	$(function() {
		$("#conFirm").click(function() {
			if ($("#pw").val() == "") {
				alert("비밀번호를 입력하세요.");
				$("#pw").focus();
				return false;
			} else {
				$.ajax({
					type: "POST",
					url: "<%=request.getContextPath()%>/passwordCheck.do",
					data: { "pw": $("#pw").val() },
					success: function(data) {
						if (data == 1) {
							alert("비밀번호가 확인되었습니다.");
							location.href = "<%=request.getContextPath()%>/updateMemberPage.do";
						} else {
							alert("비밀번호가 틀립니다.");
							$("#pw").val("");
							$("#pw").focus();
						}
					},
					error: function() {
						alert("비밀번호 확인에 실패했습니다.");
					}
				});
			}
		});
	});
</script>


<div class="inner pw_chk_wrap">
	<h6>회원 정보 수정</h6>
	<input type="password" name="pw" id="pw">
	<div id="user_btn_wrap">
		<span id="conFirm">확인</span>
		<a href="<%=request.getContextPath()%>/mainPage.do" id="grayBtn">취소</a>
	</div>
</div>

<%@include file="/WEB-INF/include/footer.jsp" %>