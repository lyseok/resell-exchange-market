<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/transaction/safetransaction.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/admin/transaction/modal.css">
<script src="<%=request.getContextPath() %>/js/admin/transaction/safetransaction.js" defer></script>
<script src="<%=request.getContextPath() %>/js/modal/modal.js"></script>

<script>
mypath = '<%=request.getContextPath() %>';

$(function() {
  // ajax로 공지사항 리스트를 받아온다.
  txnListAll();
    
  
})

</script>

<div id="wrapper">
    <div id="container" class="">
      <h1 id="container_title">거래관리>안심거래관리</h1>
      <div class="container_wr">
        <section>
          <div class="local_desc local_desc03">
            <form action="" class="search_form">
              <select name="searchSafeTXN" id="searchSafeTXN">
                <option value="prod_name">상품이름</option>
                <option value="buy">구매자</option>
                <option value="sell">판매자</option>
              </select>
              <div>
                <input type="text" id="searchText" placeholder="검색어를 입력하세요." />
                <span class="material-symbols-outlined" id="txnsearchBTN">search</span>
              </div>
            </form>
          </div>
          
          
          <div class="tbl_head01 tbl_wrap over_scroll_cont">
            <table>
              <colgroup>
                <col width="10%">
                <col width="25%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="15%">
                <col width="10%">
              </colgroup>
              <thead>
                <tr>
                  <th scope="col">번호</th>
                  <th scope="col">상품명</th>
                  <th scope="col">가격</th>
                  <th scope="col">안심거래상태</th>
                  <th scope="col">판매자</th>
                  <th scope="col">구매자</th>
                  <th scope="col">일자</th>
                  <th scope="col">&nbsp;</th>
                </tr>
              </thead>
              <tbody id="TXNlist"></tbody>
              </table>
             </div>
             </section>
  	</div>
  	</div>
  	</div>
			

<div class="container">
  	<div class="popup-wrap">
	    <div class="popup">
	      <div class="popup-head">
	        <span class="head-title">거래정보</span>
	      </div>
	      <div class="popup-body">
	          <div class="txt_box">
		            <span id="mosp">거래번호</span>
		            <p id="safe_no"></p>
		            <p/>
		            <p/>
		            <p/>
		            <span id="mosp">상품명</span>
		            <p id="prod_name"></p>
		            <p/>
		            <p/>
		            <p/>
		            <span id="mosp">거래일시</span>
		            <p id="safe_at"></p>
		            <p/>
		            <p/>
		            <p/>
	          </div>
	          <div>
		        <span id="mosp">가격</span>
		      	<p  id="prod_price"></p>
		      	<p/>
		      	<p/>
		      	<p/>
	          </div>
	          
	          <div class="seller">
	            <span id="mosp">판매자</span>
	            <p id="seller"></p>
	            <p/>
	            <p/>
	            <p/>
	          </div>
	          
	          <span id="mosp">구매자</span>
	          <p id="buyer"></p>
	          <p/>
	          <p/>
	          <p/>
	          <span id="mosp">거래상태</span>
	          <p id="safe_status"></p>
	        	<p/>
	        	<p/>
	        	<p/>
	        </div>
		    <div class="popup-foot">
		        <span class="pop-btn confirm" id="closemodal">닫기</span>
		    </div>
	    </div>
	</div>
</div>


  
</body>

</html>