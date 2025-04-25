<%@page import="rem.product.vo.ViewCountVO"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/board/notice.css">
	<script src="<%=request.getContextPath() %>/js/admin/board/notice.js"></script>


<%

@SuppressWarnings("unchecked")
List<ViewCountVO> list = (List<ViewCountVO>)request.getAttribute("vcvl");

System.out.print("VO리스트 : " + list);

%>
<script>
$(function() {
 
  
 
  $('#searchviewbtn').on('click', function() {
	$('.search_form').submit();
  })

})

</script>
  <div id="wrapper">
    <div id="container" class="">
      <h1 id="container_title">상품관리>조회 내역</h1>
      <div class="container_wr">
        <section>
          <div class="local_desc local_desc03">
            <form action="<%=request.getContextPath() %>/admin/searchviewList.do" method="get" class="search_form">
              <select name="searchview" id="searchview">
                <option value="mem_no">회원번호</option>
                <option value="prod_no">상품번호</option>
                
              </select>
              <div>
                <input type="text" name="searchText" id="searchText" placeholder="검색어를 입력하세요." />
                <span class="material-symbols-outlined" id="searchviewbtn">search</span>
              </div>
            </form>
          </div>
          <div class="tbl_head01 tbl_wrap over_scroll_cont">
            <table>
              <colgroup>
                <col width="10%">
                <col width="35%">
                <col width="35%">
                <col width="20%">
                
              </colgroup>
              <thead>
                <tr>
                  <th scope="col">조회 번호</th>
                  <th scope="col">회원 번호</th>
                  <th scope="col">상품 번호</th>
                  <th scope="col">조회 시각</th>
                </tr>
              </thead>
              <tbody id="viewList">
              <%
              
              for(ViewCountVO vo : list){
              
              %>
              <tr class="viewBoard">
              	<td><%=vo.getView_no() %></td>
              	<td><%=vo.getMem_no() %></td>
              	<td><%=vo.getProd_no() %></td>
              	<td><%=vo.getView_at() %></td>
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