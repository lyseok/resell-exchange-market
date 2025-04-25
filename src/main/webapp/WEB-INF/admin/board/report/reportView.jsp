<%@page import="rem.admin.board.report.vo.ReportBoardVO"%>
<%@page import="rem.admin.board.qna.vo.QnaBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>	
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/board/report.css">

	
<%
	ReportBoardVO board = (ReportBoardVO)request.getAttribute("board");
	String json = gson.toJson(board);
%>

<script defer src="<%=request.getContextPath()%>/js/admin/board/reportView.js"></script>
<script>
mypath = '<%=request.getContextPath() %>';
noticeVO = <%=json%>;

</script>
	
	<div id="wrapper">

		<div id="container">

			<h1 id="container_title">글관리 > 신고</h1>
			<div class="container_wr">
				<section>
						
					<div class="local_desc local_desc02"></div>

					<div class="admin_view_page_wrap">
						<div class="user_cont">
							<div class="admin_bd_view_tit">
								<h6 id="rpt_title"></h6>
								<b id="rpt_time"></b>
							</div>
							
							<div class="admin_bd_view_info">
								<div><span>유형</span><b id="rpt_type"></b>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span>번호</span><b id="rpt_idx_no"></b></div>							
								<div><span>작성자</span><b id="mem_no"></b></div>
							</div>
							
							<div class="admin_bd_view_cont" id="rpt_text">
								<!-- 신고사유 -->
							</div>
						</div>

						<div class="admin_cont ai_fe" id="status_ck">
							<div class="admin_status" id="rpt_status"></div>
						</div>						
						
						<div id="commentsArea">
						</div>
					</div>
					<div class="listBtn">
						<a href="<%=request.getContextPath() %>/admin/reportBoardPage.do">목록</a>
					</div>
				</section>
					
			</div>
		</div>

	</div>
</body>

</html>