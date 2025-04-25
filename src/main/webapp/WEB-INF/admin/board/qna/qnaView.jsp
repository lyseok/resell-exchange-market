<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="rem.admin.board.qna.vo.QnaCommentsVO"%>
<%@page import="rem.admin.board.qna.vo.QnaBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>	
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/board/qna.css">
	
	<%
		QnaBoardVO vo = (QnaBoardVO)request.getAttribute("qnaVo");
	%>
	<script>
		$(function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/admin/qnaCommentView.do",
				type:"post",
			    data: {"qnaNo": <%= vo.getQna_no() %>},
				dataType: "json",
				success: res => {
					console.log(res);
					if(res != null){
						html = "";
						html += `
	                    	<div class="admin_cont ai_fe" id="status_ck">
								<div class="admin_status">답변 완료</div>
							</div>
	
	                    	<div id="commentsArea" class="admin_cont">
	                    		
		                    	<div class="admin_bd_view_cont">\${res.cmt_cont}</div>
		                    	<div class="admin_cmt_box_rt">
		                    		<b>\${res.cmt_at}</b>
		                    		<b class="updateComments">수정</b>
		                    	</div>
	                    	</div>`;
	                    $("#qna_cmt_result").html(html);
	                    console.log(res.cmt_cont);
	                    console.log(res.cmt_at);
					}
				},
				error: xhr =>{
					alert("오류: " + xhr.status);
				}
			})
			
			
			// 답변하기 버튼 클릭 시 동작
			$("#addComments").on("click", function(){
				qnaNo = <%= vo.getQna_no() %>;
				console.log(qnaNo);
				code = "";
				code += 
					`
					<form action="${mypath}/admin/insertQnaComments.do" id="qna_comments" method="post" >
				    	<input type="hidden" name="mem_no" id="qna_no" value="\${qnaNo}">
				        <div id="qna_comments_form" class="admin_comments_form">
<textarea name="commentsText" class="commentsText" cols="120" rows="7"></textarea>
							<button type="button" id="insertQnaCommentsBtn">등록하기</button>
				      </div>						
					</form>
					`;
			  // 댓글 입력 폼을 댓글 영역에 삽입
			  $('#commentsArea').html(code);
			})
			
			// 답변하기 클릭 후 폼에 입력 한 뒤 등록하기 클릭 시 
			$(document).on("click", "#insertQnaCommentsBtn", function(){
				$.ajax({
					url:"<%=request.getContextPath()%>/admin/insertQnaComments.do",
					type:"post",
					data: {
						qna_no: $("#qna_no").val(),
// 						commentsText: $(".commentsText").val().replaceAll(/\n/g, "<br>") // 줄바꿈 처리
						commentsText: $(".commentsText").val() // 줄바꿈 처리
					},
					success: res =>{
						console.log(res);
						$.ajax({
							url: "<%=request.getContextPath() %>/admin/updateQnaComments.do",
							type: "post",
							data: {
								qna_no: $("#qna_no").val(),
								commentsText: $(".commentsText").val().replaceAll(/\n/g, "<br>")
							},
							success: res => {
								console.log(res);
								code = "";
								code += 
							        `<div id="commentsArea" class="admin_cont">	                    		
				                    	<div class="admin_bd_view_cont">\${res.cmt_cont}</div>
				                    	<div class="admin_cmt_box_rt">
			                    			<b>\${res.cmt_at}</b>
				                    		<b class="updateComments">수정</b>
				                    	</div>
			                    	</div>`;
							      $("#commentsArea").html(code);
							},
							error: xhr => {
								alert("오류: " + xhr.status);
							}
						})

					}, 
					error: xhr =>{
						alert("오류: " + xhr.status);
					},
					dataType:"json"
				})
			})
			
			
			// 수정버튼 클릭 시 동작
			$(document).on("click", ".updateComments", function(){
				text = $("#commentsArea > .admin_bd_view_cont").html().replaceAll("<br>", "\n");
				qnaNo = <%= vo.getQna_no() %>; 
				console.log(qnaNo);
				code = "";
				code += 
					`<form action="${mypath}/admin/updateQnaComments.do" id="qna_comments" method="post" >
				    	<input type="hidden" name="mem_no" id="qna_no" value="\${qnaNo}">
				        <div id="qna_comments_form" class="admin_comments_form">
<textarea name="commentsText" class="commentsText" cols="120" rows="7">\${text}</textarea>
					        <button type="button" id="updateQnaCommentsBtn">등록하기</button>
				      </div>						
					</form>
					`;
			  // 댓글 입력 폼을 댓글 영역에 삽입
			  $('.admin_cont:last-child').html(code);
			})
			
			// 수정내용 입력 후 수정완료 버튼 클릭 시
			$(document).on("click", "#updateQnaCommentsBtn", function(){
				$.ajax({
					url: "<%=request.getContextPath() %>/admin/updateQnaComments.do",
					type: "post",
					data: {
						qna_no: $("#qna_no").val(),
						commentsText: $(".commentsText").val().replaceAll(/\n/g, "<br>")
					},
					success: res => {
						console.log(res);
						code = "";
						code += 
						`<div class="admin_bd_view_cont commentsText">\${res.cmt_cont}</div>
					        <div class="admin_cmt_box_rt">
					          <b>\${res.cmt_at}</b>
					          <b class="updateComments">수정</b>
					        </div>`;
					      $("#commentsArea").html(code);
					},
					error: xhr => {
						alert("오류: " + xhr.status);
					}
				})
			})
			
		})
	</script>
    <div id="wrapper">

        <div id="container">

            <h1 id="container_title">글관리 > 문의</h1>
            <div class="container_wr">
                <section>
                    
                    <div class="local_desc local_desc02"></div>

                    <div class="admin_view_page_wrap">
                    	<div class="user_cont">
	                    	<div class="admin_bd_view_tit">
	                    		<h6><%=vo.getQna_title() %></h6>
	                    		<b><%= vo.getQna_at() %></b>
	                    	</div>
	                    	
	                    	<div class="admin_bd_view_info">
	                    		<div>
	                    			<span>유형</span>
                    				<%
									    int qnaType = vo.getQna_type(); // 예제 변수
									    if (qnaType == 0) {
									%>
									        <b>상품문의</b>
									<%
									    } else if (qnaType == 1) {
									%>
									        <b>1:1 문의</b>
									<%
									    } else {
									%>
									        <b>알 수 없는 유형입니다.</b>
									<%
									    }
									%>
	                    		</div>
	                    		<div><span>작성자</span><b><%= vo.getMem_no() %></b></div>
	                    	</div>
	                    	
	                    	<div class="admin_bd_view_cont"><%=vo.getQna_cont() %></div>
                    	</div>
                    	
                    	<%
                    		if(vo.getQna_com_status() == 0){
                    	%>
                    	<div class="admin_cont ai_fe" id="status_ck">
							<div class="admin_status">답변 전</div>
													
						    <div class="btn_list03 btn_list">
						      <button id="addComments">답변하기</button>
						    </div>
						</div>
						<div id="commentsArea"></div>
                    	<%
                    		} else {
                    	%>
                    		<div id="qna_cmt_result"></div>
                    	<%
                    		}
                    	%>
                    </div>
                    <div class="listBtn">
                    	<a href="<%=request.getContextPath() %>/admin/qnaPage.do">목록</a>
                    </div>
                </section>
                
            </div>
        </div>

    </div>
</body>

</html>