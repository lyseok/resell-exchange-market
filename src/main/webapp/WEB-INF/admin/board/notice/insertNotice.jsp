<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/board/notice.css">


<script>
$(function() {
  $('#insertBtn').on('click', function() {
    var title = $('#noticeTitle').val().trim();  
    var cont = $('#noticeContent').val().replaceAll(/\n/g, "<br>");
    $.ajax({
      url: `${mypath}/admin/insertNotice.do`,
      type: "post",
      data: {noticeTitle: title, noticeContent: cont},
      success: function(data){
        console.log(data);
        location.href = "<%=request.getContextPath()%>/admin/noticePage.do";
      },
      error: function(xhr){
        location.href = "<%=request.getContextPath()%>/admin/noticePage.do";
        console.log(xhr.status);
      },
      dataType: "json"
    });
  });

  $('#cancelBtn').on('click', function() {
    location.href = "<%=request.getContextPath()%>/admin/noticePage.do";
  });
})

</script>

  <div id="wrapper">

    <div id="container" class="">

      <h1 id="container_title">글관리>공지사항</h1>
      <h1 id="container_title">공지사항 작성</h1>
      <div class="container_wr">
        <section class="notice_wrap notice_insert_wrap">


          <form action="<%=request.getContextPath() %>/admin/insertNotice.do" method="post">
            <table>
              <colgroup>
                <col width="10%">
                <col width="90%">
              </colgroup>
              <tbody>
                <tr>
                  <th scope="col">제목</th>
                  <td>
                    <input type="text" id="noticeTitle" name="noticeTitle" class="input" placeholder="제목을 입력하세요">
                  </td>
                </tr>
                <tr>
                  <th scope="col">내용</th>
                  <td>
                    <textarea id="noticeContent" name="noticeContent" class="input" placeholder="내용을 입력하세요"></textarea>
                  </td>
                </tr>
                <tr>
                  <th scope="col">사진첨부</th>
                  <td>
                    <input type="file" id="noticeFile" name="noticeFile" class="input">
                  </td>
                </tr>
            
              </tbody>
            </table>
            <div class="btn_wrap">
	            <input type="button" id="insertBtn" value="등록" class="btn btn-primary">
	            <input type="button" id="cancelBtn" value="취소" class="btn btn-secondary">
            </div>
          </form>
        </section>
      </div>
    </div>

  </div>

</body>
</html>