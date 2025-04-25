<%@page import="rem.product.vo.ProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>
	<link rel="stylesheet" href="/REMProject/css/admin/admin_nav_botton.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/admin_nav_botton.css">
	<%
		@SuppressWarnings("unchecked")
		List<ProductVO> list = (List<ProductVO>)request.getAttribute("list");
		@SuppressWarnings("unchecked")
		List<ProductVO> Searchlist = (List<ProductVO>)request.getAttribute("Searchlist");
		
		String prodJson = gson.toJson(list);
	%>
	<script defer src="<%=request.getContextPath() %>/js/admin/product/product.js"></script>
	<script>
		// 자바 List<ProductVO>로 담겨온 객체를 js 객체로 변환
		const prodList = <%=prodJson%>
	</script>

	<div id="wrapper">
		<div id="container">
			<h1 id="container_title">글관리 > 상품게시판</h1>
			<div class="container_wr">
				<section>
				<div class="local_desc local_desc03 ai_fe">
                  	<h3>최근 등록 상품</h3>
                   	<form action="" class="search_form mt0">
		              <select name="searchProduct" id="searchProduct">
		                <option value="prod_no">상품번호</option>
		                <option value="prod_name">상품명</option>
		              </select>
		              <div>
		                <input type="text" id="searchProductText" placeholder="검색어를 입력하세요.">
		                <span class="material-symbols-outlined" id="searchProductBtn">search</span>
		              </div>
		            </form>
				</div>
				<div class="tbl_head01 tbl_wrap">
					<table>
			            <caption>최근 등록 상품</caption>
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
			                <th>상품번호</th>
			                <th>판매자</th>
			                <th>상품명</th>
			                <th>가격</th>
			                <th>판매상태</th>
			                <th>삭제여부</th>
			                <th>등록일</th>
			              </tr>
			            </thead>
			            <tbody id="prodTbody"></tbody>
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