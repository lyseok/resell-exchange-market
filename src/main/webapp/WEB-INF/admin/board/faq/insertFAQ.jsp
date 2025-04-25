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
                  <th scope="col">제목</th>
                  <td>
                    <input type="text" id="faqTitle" name="faqTitle" class="input" placeholder="제목을 입력하세요">
                            <label for="faqType">FAQ 유형 선택:</label>
    						<select id="faqType" name="faqType">
        					
					        <option value="1">회원/계정</option>
        					<option value="2">운영정책</option>
        					<option value="3">안전결제</option>
        					<option value="4">거래분쟁</option>
        					<option value="5">기타</option>
    				</select>
                  </td>
                </tr>
                <tr>
                  <th scope="col">내용</th>
                  <td>
                    <textarea id="faqContent" name="faqContent" class="input" placeholder="내용을 입력하세요"></textarea>
                  </td>
                </tr>
                
              </tbody>
            </table>
            <div class="btn_list">
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