<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>
  <script defer src="<%=request.getContextPath() %>/js/admin/admin_member.js"></script>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/admin_nav_botton.css"/>
  <script>
    let memberList = <%= request.getAttribute("result")%>
    console.log(memberList);
  </script>
    <div id="wrapper">

      <div id="container" class="">

        <h1 id="container_title">회원관리</h1>
        <div class="container_wr">
          <section>
              
            <div class="local_desc">
              
              <p>총회원수 <span></span>명 중 제제 <span></span>명 탈퇴 <span></span>명</p>
            </div>

            <div class="tbl_head01 tbl_wrap">
              <table>
                <caption>신규가입회원</caption>
                <colgroup>
                  <col width="10%">
                  <col width="8%">
                  <col width="8%">
                  <col width="20%">
                  <col width="13%">
                  <col width="8%">
                  <col width="20%">
                  <col width="5%">
                </colgroup>
                <thead>
                  <tr>
                    <th scope="col">회원번호</th>
                    <th scope="col">이름</th>
                    <th scope="col">별명</th>
                    <th scope="col">연락처</th>
                    <th scope="col">잔액</th>
                    <th scope="col">상태</th>
                    <th scope="col">가입일</th>
                    <th scope="col">제제</th>
                  </tr>
                </thead>
                <tbody id="member_list">

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