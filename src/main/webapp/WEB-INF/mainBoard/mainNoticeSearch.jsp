<%@page import="rem.admin.board.notice.vo.NoticeBoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<%@ page isELIgnored="false" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainBoard/mainBoard.css">
<script defer src="<%=request.getContextPath() %>/js/mainBoard/mainNewBoardNoticeList.js"></script>

<!-- ■■■■■■■■■■■■■■■■■■■■ -->
<div class="inner kcy_board">
	<div class="kcy_boardHeader">
		<br><br>
		<span class="bHeader_normal">띹장터 서비스의 </span>
		<span class="bHeader_highlight">신규</span>
		<span class="bHeader_normal"> 및 </span>
		<span class="bHeader_highlight">업데이트</span>
		<span class="bHeader_normal"> 소식을 알려드립니다!</span>
	</div>
	
	<div class="kcy_boardSearch">
		<div class="kcy_serch_box">
			<input type="text" id="searchingWord" placeholder="제목/내용으로 검색" style="border:none; width:240px;"/>
			<span class="material-symbols-outlined" id="searchingBtn" style="font-size:27px;cursor:pointer;">search</span>
		</div>
	</div>
	<br><br><hr style="color:#cecece;">
	<div class="kcy_boardList_div">
		<ul class="kcy_boardList">

		</ul>
	</div>
	<div class="buttons">
	</div>
</div>
<!-- ■■■■■■■■■■■■■■■■■■■■ -->

<script>
const board = "<%=request.getAttribute("board")%>";
articleList = <%=new Gson().toJson(request.getAttribute("searchedList")) %>;
console.log(articleList);
articleSearchText = "<%=request.getAttribute("searchText") %>";
urlView = `<%=request.getContextPath()%>/main/${board}/view.do?${board}No=`;

/**/
$("#searchingBtn").val(articleSearchText);


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
</script>

<%@include file="/WEB-INF/include/footer.jsp" %>