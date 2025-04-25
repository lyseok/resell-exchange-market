<%@page import="rem.admin.main.vo.AdminTransVO"%>

<%@page import="rem.product.vo.ProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>
	
<%
@SuppressWarnings("unchecked")
List<MemberVO> memberList = (List<MemberVO>)request.getAttribute("memList");
@SuppressWarnings("unchecked")
List<ProductVO> prodList = (List<ProductVO>)request.getAttribute("prodList");
@SuppressWarnings("unchecked")
List<AdminTransVO> transList = (List<AdminTransVO>)request.getAttribute("transList");

String memJson = gson.toJson(memberList);
String prodJson = gson.toJson(prodList);
String transJson = gson.toJson(transList);

%>
<script defer src="<%=request.getContextPath() %>/js/admin/admin_main.js"></script>
<script type="text/javascript">
const memList = <%=memJson%>
const prodList = <%=prodJson%>
const transList = <%=transJson%>

</script>

  <div id="wrapper">

    <div id="container" class="">

      <h1 id="container_title">관리자 메인</h1>
      <div class="container_wr">
        <section>
            
          <div class="local_desc">
            <p class="color_bk">신규가입회원 <span>5</span>건 목록</p>
          </div>

          <div class="tbl_head01 tbl_wrap">
            <table>
              <caption>신규가입회원</caption>
              <colgroup>
                <col width="10%">
                <col width="8%">
                <col width="8%">
                <col width="20%">
                <col width="18%">
                <col width="8%">
                <col width="20%">
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
                </tr>
              </thead>
              <tbody id="memTbody">
                
              </tbody>
            </table>
          </div>

          <div class="btn_list03 btn_list">
              <a href="<%=request.getContextPath() %>/admin/selectAllMemberList.do">회원 전체보기</a>
          </div>

      </section>


      <section>
        <h2>최근 등록 상품</h2>

        <div class="tbl_head01 tbl_wrap">
          <table>
            <caption>신규가입회원</caption>
            <colgroup>
              <col width="10%">
              <col width="12%">
              <col width="24%">
              <col width="18%">
              <col width="12%">
              <col width="8%">
              <col width="12%">
            </colgroup>
            <thead>
              <tr>
                <th scope="col">상품번호</th>
                <th scope="col">판매자</th>
                <th scope="col">상품명</th>
                <th scope="col">가격</th>
                <th scope="col">판매상태</th>
                <th scope="col">삭제여부</th>
                <th scope="col">등록일</th>
              </tr>
            </thead>
            <tbody id="prodTbody">
              
            </tbody>
          </table>
        </div>

          

        <div class="btn_list03 btn_list">
          <a href="<%=request.getContextPath() %>/admin/prodListPage.do">상품 더보기</a>
        </div>
      </section>


      <section>
        <h2>최근 거래내역</h2>

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

        <div class="btn_list03 btn_list">
            <a href="<%=request.getContextPath() %>/admin/transPage.do">내역 더보기</a>
        </div>
      </section>

      </div>
    </div>

  </div>
</body>

</html>