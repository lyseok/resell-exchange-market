<%@page import="rem.admin.board.faq.vo.FAQBoardVO"%>
<%@page import="rem.admin.board.notice.vo.NoticeBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>	
    
<!DOCTYPE html>
<html>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/board/qna.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/board/notice.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	FAQBoardVO vo = (FAQBoardVO)request.getAttribute("vfbvo");

System.out.println(vo);
	
System.out.println(vo);
System.out.println(vo.getFaq_title());
System.out.println(vo.getFaq_no());
System.out.println(vo.getFaq_cont());
%>

<%if(vo != null){ 


System.out.println(vo.getFaq_title());
%>

<div id="wrapper">
		<div id="container">
			<h1 id="container_title">글관리 > FAQ</h1>
			<div class="container_wr">
				<section>
				<div class="local_desc local_desc02"></div>
				<div class="admin_view_page_wrap">
					<div class="user_cont">
						<div class="admin_bd_view_tit">
							<h6 id="faqTitle" name="faqTitle"><%=vo.getFaq_title() %></h6><div hidden id="faqNo" name="faqNo"><%=vo.getFaq_no() %></div>
							<b id="faqAt" name="faqAt"><%=vo.getFaq_at() %></b>
						</div>
						<div class="dp_f ai_c gap30">
							<label for="faqType">FAQ 유형 선택:</label>
    						<select id="faqType" name="faqType">
        					
					        <option value="faqType" name="faqType"><%=vo.getFaq_type() %></option>
					        </select>
					    </div>
						<div class="admin_bd_view_cont" id="faqCont" name="faqCont"><%=vo.getFaq_cont() %></div></div>
						<%} %>
						
						
						<div class="btn_list03 btn_list btn_wrap">
							<button id="updateBtn"onclick="location.href='<%=request.getContextPath()%>/admin/updateFAQ.do?faqNo=<%=vo.getFaq_no()%>'" >수정</button>
							<button id="deleteBtn" onclick="location.href='<%=request.getContextPath()%>/admin/deleteFAQ.do?faq_no=<%=vo.getFaq_no()%>'" class="btn-primary">삭제</button>
						</div>
					</div>
					<div class="listBtn">
						<a href="<%=request.getContextPath()%>/admin/faqBoardView.do">목록</a>
					</div>
				</section>
			</div>
		</div>
	</div>
</body>
</html>