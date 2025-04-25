<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<%@ page isELIgnored="false" %>
<script defer src="<%=request.getContextPath() %>/js/mainBoard/mainBoardQnaList.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainBoard/mainBoard.css">
<!-- 
		Header	   (타이틀 위치.)
		Search	   (글 검색창 위치.)
		List/Table (게시판 글 목록 위치.)
		Footer     (페이지네이션, 글쓰기 버튼 위치.)
-->
<!-- ■■■■■■■■■■■■■■■■■■■■ -->
<div class="inner kcy_board">
	<div class="kcy_boardHeader">
		<br><br>
		<span class="bHeader_normal">문의 내역</span>
	</div>
	
	<div class="kcy_boardSearch">
		<div class="kcy_serch_box">
			<input type="text" id="searchingWord" placeholder="제목/내용으로 검색" style="border:none; width:240px;"/>
			<span class="material-symbols-outlined" id="searchingBtn" style="font-size:27px;cursor:pointer;">search</span>
		</div>
	</div>
	<br><br><hr style="color:#cecece;">
	
	<div class="kcy_boardTable_div">
		<table class="kcy_boardTable" border="1">

		</table>
	</div>
	<hr style="color:#cecece;"><br>
	<div class="kcy_viewFooter">
		<div class="null">
		</div>
		<div class="buttons">
		</div>
		<div class="GeulSsooGi">
			<input type="button" value="문의글 작성하기" id="btnGeulSsooGi"> 
		</div>
	</div>
</div>
<!-- ■■■■■■■■■■■■■■■■■■■■ -->


<script>
// 변수 board: 게시판 유형 (필요없음. ${board}들어간 자리에 qna,faq등 필요한 거 넣어두면 됨)
const board = "<%=request.getAttribute("board")%>";
const urlContextPath = "<%=request.getContextPath()%>";
mypath = '<%=request.getContextPath() %>';

/*
	불러온 게시판 리스트는 JSON으로 변환하여 script변수에 할당.
	메인으로 넘어오는 서블릿이나, 검색으로 넘어오는 서블릿이나,
	하는 일은 같다.
	굳이 분리시켜둔 이유는, url 구분을 위해.
	console은 디버깅용.
	검색기능은 손 안 봐도 괜찮을듯.
 */
articleList = <%=new Gson().toJson(request.getAttribute("searchedList")) %>;
console.log(articleList);
articleSearchText = "<%=request.getAttribute("searchText") %>";
console.log(articleSearchText);
urlView = `<%=request.getContextPath()%>/main/${board}/view.do?${board}No=`;
/* 1)_ 검색기능 */
$("#searchingBtn").on("click", function(){
	const search_text = $('#searchingWord').val();
	if (search_text == "") {
		$("#searchingWord").attr("placeholder", "검색어를 입력하세요..!");
		return false;
	} else {
		location.href = `<%=request.getContextPath()%>/main/${board}/search.do?sch=\${search_text}`;
	}
});

/* 2)_ 글 등록 폼으로 이동하는 버튼 */
$("#btnGeulSsooGi").on("click", function(){
	location.href = `<%=request.getContextPath()%>/main/${board}/wr.do`;
});
</script>
<%@include file="/WEB-INF/include/footer.jsp" %>