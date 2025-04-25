<%@page import="rem.admin.board.notice.vo.NoticeBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>	
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/board/qna.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/board/notice.css">

	
	<%
		NoticeBoardVO vo = (NoticeBoardVO)request.getAttribute("board");
		String json = gson.toJson(vo);
	%>

<script src="<%=request.getContextPath() %>/js/admin/board/notice.js"></script>
<script>

mypath = '<%=request.getContextPath() %>';
noticeVO = <%=json%>;
noticeNo = noticeVO.notice_no;

$(function(){
	$('#noticeTitle').html(noticeVO.notice_title);
	$('#noticeCont').html(noticeVO.notice_cont.replaceAll(/\n/g, "<br>"));
	$('#noticeAt').html(noticeVO.notice_at);

	// 수정 버튼을 클릭하면 textarea로admin_bd_view_cont의 내용을 가져와서 textarea에 넣어준다.
	$('#updateBtn').on('click', function(){
		noticeBoardUpdateForm();
	});
	
	// 삭제 버튼을 누르면 해당 게시글의 번호를 가지고 서블릿 호출
	$('#deleteBtn').on('click', function(){
		deleteNoticeBoard();
	});
	
	// 확인 버튼을 클릭하면 textarea의 내용을 가져와서 ajax로 보내준다.
	$(document).on('click', '#confirmBtn', function(){
		noticeBoardUpdate();
	});
	
	// 취소 버튼을 클릭하면 textarea의 내용을 초기화한다.
	$(document).on('click', '#cancelBtn', function(){
		var cont = $('.admin_bd_view_cont').text().replaceAll(/\n/g, "<br>");
		$('.admin_bd_view_cont').html(cont);
	});


});

</script>
	
	<div id="wrapper">
		<div id="container">
			<h1 id="container_title">글관리 > 공지사항</h1>
			<div class="container_wr">
				<section>
				<div class="local_desc local_desc02"></div>
				<div class="admin_view_page_wrap">
					<div class="user_cont">
						<div class="admin_bd_view_tit">
							<h6 id="noticeTitle"></h6>
							<b id="noticeAt"></b>
						</div>
						<div class="admin_bd_view_cont" id="noticeCont"></div></div>
						<div class="btn_list03 btn_list btn_wrap">
							<button id="updateBtn" class="">수정</button>
							<button id="deleteBtn">삭제</button>
						</div>
					</div>
					<div class="listBtn">
						<a href="<%=request.getContextPath() %>/admin/noticePage.do">목록</a>
					</div>
				</section>
			</div>
		</div>
	</div>
</body>
</html>