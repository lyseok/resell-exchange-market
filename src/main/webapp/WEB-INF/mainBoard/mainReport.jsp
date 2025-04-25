<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<%@ page isELIgnored="false" %>
<script defer src="<%=request.getContextPath() %>/js/mainBoard/mainBoardReportList.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainBoard/report.css">


<div class="inner kcy_board">
	<div class="kcy_boardHeader">
		<br><br>
		<span class="bHeader_normal">신고하기</span>
	</div>
	
	<div class="kcy_boardSearch">
		<div class="kcy_serch_box">
			<input type="text" id="searchingWord" placeholder="제목/내용" style="border:none; width:240px;"/>
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
			<input type="button" value="신고작성" id="btnGeulSsooGi"> 
		</div>
	</div>
</div>


<script>
	const board = "<%=request.getAttribute("board")%>";	
	const urlContextPath =  "<%=request.getContextPath()%>";
	
	
	
	articleList = <%=new Gson().toJson(request.getAttribute("searchedList")) %>;
	console.log(articleList);
	articleSearchText = "<%=request.getAttribute("searchText") %>";
	console.log(articleSearchText);
	urlView = `<%=request.getContextPath()%>/main/${board}/view.do?\${board}No=`;
	/* 1)_ 검색기능 */
	$("#searchingBtn").on("click", function(){
		const search_text = $('#searchingWord').val();
		if (search_text == "") {
			$("#searchingWord").attr("placeholder", "검색어를 입력하세요..!");
			return false;
		} else {
			location.href = `<%=request.getContextPath()%>/main/report/search.do?sch=\${search_text}`;
		}
	});
	
	/* 2)_ 글 등록 폼으로 이동하는 버튼 */
	$("#btnGeulSsooGi").on("click", function(){
		location.href = `<%=request.getContextPath()%>/main/report/wr.do`;
	});
</script>
<%@include file="/WEB-INF/include/footer.jsp" %>