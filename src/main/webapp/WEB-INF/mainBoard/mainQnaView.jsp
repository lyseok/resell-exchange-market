<%@page import="rem.file.vo.ImgFileVO"%>
<%@page import="rem.admin.board.qna.vo.QnaSetVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<%@ page isELIgnored="false" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainBoard/mainBoard.css">

<%
	//게시판 쪽은 기본적으로 동기방식을 사용.
	//신고글 작성에 쓸 VO도 여러 테이블 합친 형태로 새로 만드는 게 좋을듯.
	QnaSetVO qnaVO = (QnaSetVO)request.getAttribute("qnaViewSet");
	String qImgFile = (String)request.getAttribute("QimgFile");
	String mem_email = loginInfo.getMem_email();
	
	//attribute로 받은 vo에 Getter 사용.
    int qna_no = qnaVO.getQna_no();
	int mem_no = qnaVO.getMem_no();
	String qna_title = qnaVO.getQna_title();
	int qna_type = qnaVO.getQna_type();
	String qna_at = qnaVO.getQna_at();
	String qna_cont = qnaVO.getQna_cont();
	int qna_status = qnaVO.getQna_status();
	int qna_com_status = qnaVO.getQna_com_status();
	String cmt_cont = qnaVO.getCmt_cont();
	String cmt_at = qnaVO.getCmt_at();
	

    qna_cont = qna_cont.replaceAll("\\n", "<br>");
%>
<!-- 
		Header	   (타이틀 위치.)
		Search	   (글 검색창 위치.)
		View	   (내가 작성한 글 / 관리자의 답변)
		Footer 대신에 목록으로 돌아가는 버튼
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
	
	<div class="kcy_boardView">
		<div class="kcy_viewHeader">
			<span id="viewTitle"><%=qna_title %></span>
			<div class="viewExtra">
				<span id="viewAt"><%=qna_at %></span>
			</div>
		</div>
		<div class="kcy_boardSection">
			<div class="sectionEctra">
				<div class="ectraColumn"><span class="ectKey">유형</span><span class="ectValue">상품등록</span></div>
				<div class="ectraColumn"><span class="ectKey">작성자</span><span class="ectValue">^Q^</span></div>
			</div>
		</div>
		<div class="kcy_viewContent">
			<p><%=qna_cont %></p>
		</div>
		<!-- 이미지가 없으면 공백 -->
<%-- 		<div class="kcy_imgContainer">
			<img src="<%=qImgFile %>" style="max-height:600px;"/>
		</div> --%>
		<!-- 답변여부 삼항식으로 출력 -->
		<div class="kcy_whatTheFuck">
			<span class="cmt_status"><%=qna_com_status == 0 ? "처리중" : "답변완료" %></span>
		</div>

<!-- 답변이 달렸을 때: 답변용 div 생성하여 view에 삽입 -->
<%if(qna_com_status==1){ %>
		<hr style="color:#cecece;margin-bottom:5px;">
		<div class="kcy_cmtContent">
			<div class="kcy_viewContent .fuckingComments">
				<p><%=cmt_cont %></p>
			</div>
			<div style="float:right; padding:15px 20px 0px 0px;">
				<div id="cmt_viewAt">
					<span id="viewAt"><%=cmt_at %></span>
				</div>
				<div id="cmt_null">
				</div>
			</div>
		</div>
<%-- 		<div class="kcy_imgContainer">
			<img src="<%=qImgFile %>" style="max-height:600px;"/>
		</div> --%>
<!-- 아직 처리중일 때: 암것도 안 함 -->
<%} else { } %>
	</div>
	
	<!--  -->
	<hr style="color:#cecece;"><br>
	<div class="kcy_boardBackToList">
		<input type="button" value="목록" id="listButton" />
	</div>
</div>
<!-- ■■■■■■■■■■■■■■■■■■■■ -->


<script>
const board = "<%=request.getAttribute("board")%>";

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

/* 2)_ 글 조회 시 목록으로 돌아가는 버튼 */
$("#listButton").on("click", function(){
	location.href = `<%=request.getContextPath()%>/main/${board}.do`;
});

</script>
<%@include file="/WEB-INF/include/footer.jsp" %>