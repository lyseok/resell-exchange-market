<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/board/notice.css">
<script src="<%=request.getContextPath() %>/js/admin/board/report.js" defer></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/admin_nav_botton.css">

<script>
mypath = '<%=request.getContextPath() %>';
$(function() {

	// ajax로 공지사항 리스트를 받아온다.
	allReportBoard();

  // 게시글 클릭시 상세 페이지로 이동
  $(document).on("click", ".reportBoard", function() {
    const rpt_no = $(this).closest('tr').find("td").eq(0).text();
    location.href = "<%=request.getContextPath() %>/admin/reportViewPage.do?bono=" + rpt_no;
  });
});

</script>
  <div id="wrapper">

    <div id="container" class="">

      <h1 id="container_title">글관리>신고</h1>
      <div class="container_wr">
        <section>
            
          <div class="local_desc local_desc03">
            <form action="" class="search_form">
              <select name="searchNotice" id="searchNotice">
                <option value="no">번호</option>
                <option value="title">제목</option>
              </select>
              <div>
                <input type="text">
                <span class="material-symbols-outlined" id="searchNoticeBtn">
                  search
                </span>
              </div>
            </form>
          </div>

          <div class="tbl_head01 tbl_wrap">
            <table>
              <colgroup>
                <col width="10%">
                <col width="10%">
                <col width="40%">
                <col width="10%">
                <col width="10%">
                <col width="13%">
                <col width="7%">
              </colgroup>
              <thead>
                <tr>
                  <th scope="col">번호</th>
                  <th scope="col">유형</th>
                  <th scope="col">제목</th>
                  <th scope="col">작성자</th>
                  <th scope="col">답변여부</th>
                  <th scope="col">작성일</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody id="reportList">
              </tbody>
            </table>
          </div>
        </section>
        <div class="buttons"></div>
      </div>
    </div>
  </div>
</body>

</html>