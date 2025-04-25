<%@page import="rem.admin.badge.vo.AchievementsVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/board/notice.css">

<%
	@SuppressWarnings("unchecked")
	List<AchievementsVO> list = (List<AchievementsVO>)request.getAttribute("result");

	System.out.print(list);
%>

<script>
$(function(){
	$('#searchAchoevementsBtn').on('click', function() {
    	$('.search_form').submit();
	})
})
</script>

<script>

</script>
  <div id="wrapper">
    <div id="container" class="">
      <h1 id="container_title">뱃지관리>뱃지관리</h1>
      <div class="container_wr">
        <section>
          <div class="local_desc local_desc03">
            <form action="<%=request.getContextPath() %>/admin/searchAchoevements.do" method="get" class="search_form">
              <select name="searchAchoevements" id="searchAchoevements">
                <option value="ach_no">번호</option>
                <option value="ach_name">이름</option>
              </select>
              <div>
                <input type="text" name="searchText" id="searchText" placeholder="검색어를 입력하세요." />
                <span class="material-symbols-outlined" id="searchAchoevementsBtn">search</span>
              </div>
            </form>
          </div>
          <div class="tbl_head01 tbl_wrap">
            <table>
              <colgroup>
                <col width="10%">
                <col width="20%">
                <col width="40%">
                <col width="15%">
                <col width="15%">
              </colgroup>
              <thead>
                <tr>
                  <th scope="col">뱃지번호</th>
                  <th scope="col">이름</th>
                  <th scope="col">설명</th>
                  <th scope="col">타입</th>
                  <th scope="col">조건</th>
                </tr>
              </thead>
              <tbody id="noticeList">
                <%
                for(AchievementsVO vo : list){
                	%>
             	<tr class="noticeBoard">
	                <td><%=vo.getAch_no() %></td>
	                <td><%=vo.getAch_name() %></td>
	                <td><%=vo.getAch_desc() %></td>
	                <td><%=vo.getAch_type() %></td>
	                <td><%=vo.getAch_condition() %></td> 
                </tr>
                <%
                }
                %>
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </div>
  </div>
</body>

</html>