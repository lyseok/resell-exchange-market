<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/board/notice.css">
	<script src="<%=request.getContextPath() %>/js/admin/board/notice.js" defer></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/admin_nav_botton.css">
<script>
mypath = '<%=request.getContextPath() %>';

$(function() {
  
  // 공지사항 리스트를 클릭하면 공지사항 상세보기 페이지로 이동한다.
  $(document).on("click", ".noticeBoard", function() {
    const bono = $(this).find("td").eq(0).text();
    location.href = `\${mypath}/admin/noticeViewPage.do?bono=\${bono}`;
  });

  $('#searchNoticeBtn').on('click', function() {
	searchNoticeBoard();
  });

})

</script>
  <div id="wrapper">
    <div id="container" class="">
      <h1 id="container_title">글관리>공지사항</h1>
      <div class="container_wr">
        <section>
          <div class="local_desc local_desc03">
            <form action="" class="search_form">
              <select name="searchNotice" id="searchNotice">
                <option value="notice_no">번호</option>
                <option value="notice_title">제목</option>
              </select>
              <div>
                <input type="text" id="searchText" placeholder="검색어를 입력하세요." />
                <span class="material-symbols-outlined" id="searchNoticeBtn">search</span>
              </div>
            </form>
          </div>
          <div class="tbl_head01 tbl_wrap">
            <table>
              <colgroup>
                <col width="10%">
                <col width="60%">
                <col width="10%">
                <col width="20%">
              </colgroup>
              <thead>
                <tr>
                  <th scope="col">번호</th>
                  <th scope="col">제목</th>
                  <th scope="col">조회수</th>
                  <th scope="col">작성일</th>
                </tr>
              </thead>
              <tbody id="noticeList">
              </tbody>
            </table>
          </div>
          <div class="btn_list03 btn_list btn_wrap">
              <a href="<%=request.getContextPath() %>/admin/insertNoticePage.do" class="boardInsertBtn">공지 글쓰기</a>
          </div>
        </section>
        <div class="buttons"></div>
      </div>
    </div>
  </div>
</body>

</html>