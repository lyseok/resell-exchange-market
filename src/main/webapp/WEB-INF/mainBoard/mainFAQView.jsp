<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<%@ page isELIgnored="false" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainBoard/mainBoard.css">
<script defer src="<%=request.getContextPath() %>/js/mainBoard/mainBoardFAQView.js"></script>

<%
	int faq_no = (int)request.getAttribute("faq_no");
	int faq_type = (int)request.getAttribute("faq_type");
	String faq_title = (String)request.getAttribute("faq_title");
	String faq_cont = (String)request.getAttribute("faq_cont");
	String faq_at = (String)request.getAttribute("faq_at");
	

    faq_cont = faq_cont.replaceAll("\\n", "<br>");
%>
<script>
	faq_no = <%=faq_no %>;
	faq_type = <%=faq_type %>;
	faq_title = <%=faq_title %>;
	faq_cont = <%=faq_cont %>;
	faq_at = <%=faq_at %>;
</script>
<div style="hidden">
<form id="tabForm" action="<%=request.getContextPath()%>/main/faq.do" method="post">
  <input type="hidden" name="tabName" id="tabNameInput">
  <button type="submit" style="display:none;">전송</button>
</form>
</div>
<!-- ■■■■■■■■■■■■■■■■■■■■ -->
<div class="inner kcy_board">
	<div class="kcy_boardHeader">
		<br><br>
		<span class="bHeader_normal">자주 묻는 질문</span>
	</div>
	<div class="kcy_boardFAQTab">
		<div class="tabItems" id="kcy_account" data-tabName="account">
			<a class="tabA" href="javascript:void(0)">
				<span class="tabItemSpan">회원/계정</span>
			</a>
		</div>ㅣ
		<div class="tabItems" id="kcy_transaction" data-tabName="transaction">
			<a class="tabA" href="javascript:void(0)">
				<span class="tabItemSpan">운영정책</span>
			</a>
		</div>ㅣ
		<div class="tabItems" id="kcy_safe_transaction" data-tabName="safeTransaction">
			<a class="tabA" href="javascript:void(0)">
				<span class="tabItemSpan">안전결제</span>
			</a>
		</div>ㅣ
		<div class="tabItems" id="kcy_store" data-tabName="store">
			<a class="tabA" href="javascript:void(0)">
				<span class="tabItemSpan">거래분쟁</span>
			</a>
		</div>ㅣ
		<div class="tabItems" id="kcy_etc" data-tabName="etc">
			<a class="tabA" href="javascript:void(0)">
					<span class="tabItemSpan">기타</span>
			</a>
		</div>
	</div>
	<br><br><hr style="color:#cecece;">
	
	<div class="kcy_boardView">
		<div class="kcy_viewHeader">
			<span id="viewTitle"><%=faq_title %></span>
			<div class="viewExtra">
				<span id="viewAt"><%=faq_at %></span>
			</div>
		</div>
		<div class="kcy_viewContent">
			<p><%=faq_cont %></p>
		</div>
	</div>
	
	<hr style="color:#cecece;"><br>
	<div class="kcy_boardBackToList" style="display:hidden;">
		<input type="button" value="목록" id="listButton" />
	</div>
</div>
<!-- ■■■■■■■■■■■■■■■■■■■■ -->


<script>
/* 2)_ 글 조회 시 목록으로 돌아가는 버튼 */
$("#listButton").on("click", function(){
	location.href = `<%=request.getContextPath()%>/main/faq.do`;
});

</script>
<%@include file="/WEB-INF/include/footer.jsp" %>