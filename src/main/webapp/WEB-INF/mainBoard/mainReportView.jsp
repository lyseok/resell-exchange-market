

<%@page import="rem.admin.board.report.vo.ReportSetVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<%@ page isELIgnored="false" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainBoard/report.css">

<%
	ReportSetVO rptVO = (ReportSetVO)request.getAttribute("reportViewSet");
	String mem_email = loginInfo.getMem_email();
	
	
	int rpt_no = rptVO.getRpt_no();
	int mem_no = rptVO.getMem_no();
	String rpt_title = rptVO.getRpt_title();
	int rpt_type = rptVO.getRpt_type();
	String rpt_time = rptVO.getRpt_time();
	int rpt_idx_no = rptVO.getRpt_idx_no();
	String rpt_text = rptVO.getRpt_text();
	int rpt_status = rptVO.getRpt_status();
	int rpt_com_status = rptVO.getRpt_com_status();
	String cmt_cont = rptVO.getCmt_cont();
	String cmt_at = rptVO.getCmt_at();
	
	
	String rptType = "";
	String rptIdx = "";
	String rptIdxStr = "";
	
	if( rpt_type  == 0){
		rptType = "상품신고";
		
		rptIdxStr = String.valueOf(rpt_idx_no);
		rptIdx = rptIdxStr + "(상품번호)";
	}
	else if(rpt_type == 1){
		rptType = "회원신고";
		rptIdxStr = String.valueOf(rpt_idx_no);
		rptIdx = rptIdxStr + "(신고대상 회원번호)";
	}
	
	
%>


<div class="inner kcy_board">
	<div class="kcy_boardHeader">
		<br><br>
		<span class="bHeader_normal">신고목록</span>
	</div>
	
	<div class="kcy_boardSearch">
		<div class="kcy_serch_box">
			<input type="text" id="searchingWord" placeholder="제목/내용" style="border:none; width:240px;"/>
			<span class="material-symbols-outlined" id="searchingBtn" style="font-size:27px;cursor:pointer;">search</span>
		</div>
	</div>
	
	<br><br><hr style="color:#cecece;">
	
	<div class="kcy_boardView">
		<div class="kcy_viewHeader">
			<span id="viewTitle"><%=rpt_title %></span>
			<div class="viewExtra">
				<span id="viewAt"><%=rpt_time %></span>
			</div>
		</div>
		<div class="kcy_boardSection">
			<div class="sectionEctra">
				<div class="ectraColumn"><span class="ectKey">신고유형:</span><span class="ectValue"><%=rptType %></span></div>
				<div class="ectraColumn"><span class="ectKey">번호:</span><span class="ectValue"><%=rptIdx %></span></div>
				<div class="ectraColumn"><span class="ectKey">작성자: </span><span class="ectValue"><%=mem_email %></span></div>
			</div>
		</div>
		<div class="kcy_viewContent">
			<p><%=rpt_text%></p>
		</div>
		<!-- 이미지가 없으면 공백 -->
	
		<!-- 답변여부 삼항식으로 출력 -->
		<div class="kcy_whatTheFuck">
			<span class="cmt_status"><%=rpt_com_status == 0 ? "처리중" : "답변완료" %></span>
		</div>

<!-- 답변이 달렸을 때: 답변용 div 생성하여 view에 삽입 -->
<%if(rpt_com_status==1){ %>
		<hr style="color:#cecece;margin-bottom:5px;">
		<div class="kcy_cmtContent">
			<div class="kcy_viewContent .fuckingComments">
				<p><%=cmt_cont %></p>
			</div>
			<div style="float:right; padding:15px 20px 0px 0px;">
				<div id="cmt_viewAt">
					<span id="viewAt"><%=cmt_at%></span>
				</div>
				<div id="cmt_null">
				</div>
			</div>
		</div>
	
<!-- 아직 처리중일 때: 암것도 안 함 -->
<%} else { } %>
	</div>
	
	<!--  -->
	<hr style="color:#cecece;"><br>
	<div class="kcy_boardBackToList">
		<input type="button" value="목록" id="listButton" />
	</div>
</div>



<script>

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

/* 2)_ 글 조회 시 목록으로 돌아가는 버튼 */
$("#listButton").on("click", function(){
	location.href = `<%=request.getContextPath()%>/main/report.do`;
});

</script>
<%@include file="/WEB-INF/include/footer.jsp" %>