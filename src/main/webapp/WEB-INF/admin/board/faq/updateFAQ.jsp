<%@page import="rem.admin.board.faq.vo.FAQBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%

FAQBoardVO vo = (FAQBoardVO)request.getAttribute("ifbvo");

%>
</head>
<body>
<div id="wrapper">

    <div id="container" class="">

      <h1 id="container_title">글관리>FAQ</h1>
      <h1 id="container_title">FAQ 작성</h1>
      <div class="container_wr">
        <section>


          <form action="<%=request.getContextPath() %>/admin/faqInsert.do" method="post">
            <table>
              <colgroup>
                <col width="10%">
                <col width="90%">
              </colgroup>
              <tbody>
                <tr>
                  <th scope="col">유형선택</th>
                  <td>
    				<select id="faqType" name="faqType">
					    <option value="1"><%=vo.getFaq_type() %></option>        				
    				</select>
                  </td>
                </tr>
                <tr>
                  <th scope="col">제목</th>
                  <td>
                    <input type="text" id="faqTitle" name="faqTitle" class="input" value="<%=vo.getFaq_title()%>">
                  </td>
                </tr>
                <tr>
                  <th scope="col">내용</th>
                  <td>
                    <textarea id="faqContent" name="faqContent" class="input"><%=vo.getFaq_cont() %></textarea>
                  </td>
                </tr>
                
              </tbody>
            </table>
            <div class="btn_wrap">
	            <input type="submit" id="insertBtn" value="등록" class="btn btn-primary">
	            <input type="button" id="cancelBtn" value="취소" class="btn btn-secondary" onclick="location.href='<%=request.getContextPath()%>/admin/faqBoardView.do'">
            </div>
          </form>
        </section>
      </div>
    </div>

  </div>
</body>
</html>