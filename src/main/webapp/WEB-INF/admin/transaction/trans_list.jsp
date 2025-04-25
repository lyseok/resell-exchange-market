<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/board/notice.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/admin_nav_botton.css">
<script defer src="<%=request.getContextPath() %>/js/admin/transaction/trans.js"></script>
<script>
mypath = '<%=request.getContextPath() %>';

$(function() {
  // ajax로 공지사항 리스트를 받아온다.
  allTransBoard();

  $('#searchTransBtn').on('click', function() {
	  searchTransBoard();
  });

})

</script>
  <div id="wrapper">
    <div id="container" class="">
      <h1 id="container_title">거래관리>거래관리</h1>
      <div class="container_wr">
        <section>
          <div class="local_desc local_desc03">
            <form action="" class="search_form">
              <div>
                <input type="text" id="searchText" placeholder="거래번호입력" />
                <span class="material-symbols-outlined" id="searchTransBtn">search</span>
              </div>
            </form>
          </div>
          <div class="tbl_head01 tbl_wrap">
            <table>
              <caption>최근 거래내역</caption>
              <colgroup>
                <col width="10%">
                <col width="8%">
                <col width="8%">
                <col width="24%">
                <col width="18%">
                <col width="8%">
                <col width="20%">
              </colgroup>
              <thead>
                <tr>
                  <th scope="col">거래번호</th>
                  <th scope="col">판매자</th>
                  <th scope="col">구매자</th>
                  <th scope="col">상품명</th>
                  <th scope="col">가격</th>
                  <th scope="col">거래상태</th>
                  <th scope="col">거래일</th>
                </tr>
              </thead>
              <tbody id="transTbody">

              </tbody>
            </table>
          </div>
        </section>
        <div class="buttons">
            </div>
      </div>
    </div>
  </div>
</body>

</html>