<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@include file="/WEB-INF/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main/main.css">

<style>

</style>
<script>
  let prodList = <%=request.getAttribute("result")%>;
	
  seasrchText = '<%= request.getAttribute("seasrchText")%>';

</script>
<script defer src="<%=request.getContextPath() %>/js/search/search.js"></script>

	<!-- 메인 컨텐츠 시작 -->
	<div id="main">
		<!-- 카테고리 시작 -->
		
		<!-- // 카테고리 end -->
		<%@include file="/WEB-INF/include/category.jsp" %>
		
		
		
		<!-- 상품리스트 시작 -->
		<div class="list_wrap inner">
			<h1 id="searchText"></h1>
			
			<div id="prod_list">
				<ul>
					
				</ul>
				<div class="buttons">
				</div>
			</div>
			
		</div>
		<!-- // 상품리스트 end -->
		
	</div>
	<!-- // 메인 컨텐츠 end -->
	
<%@include file="/WEB-INF/include/footer.jsp" %>