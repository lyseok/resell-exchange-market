<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="rem.product.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true"%>

<%@include file="/WEB-INF/include/header.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/product/transaction.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.1.js"></script>
<%@include file="/WEB-INF/include/category.jsp" %>

<%
	ProductVO pvo = (ProductVO)request.getAttribute("pvo");
	int txn_no = (Integer)request.getAttribute("trans");
	
	int price = pvo.getProd_price();
	
	String won = String.format("%,d", price);
%>
<script>
$(function(){
	loginInfo = <%=loginInfo.getMem_no()%>
	
	$('#main').on('click', function(){
		
		location.href = `${mypath}/mainPage.do`;
	})
	
	$('#order').on('click', function(){
		
		location.href = `${mypath}/transManagement/management.do`;
	})
	
	$('#mpage').on('click', function(){
		
		location.href = `${mypath}/store/storePage.do?param=mem_no&value=${loginInfo}`;
	})
})
</script>

<div class="inner">
	
		<div class="cont">
			<br>
			<div class="innerCont">
				<div class="innerCont text">
					<h1><span id="purple">주문이 정상적으로 완료</span>되었습니다.</h1>
				</div>
			</div>
			
			<div class="innerCont2">
				<div class="innerCont2 text2">
					<h2>거래번호</h2>
				</div>
				<div class="innerCont2 item2">
					<span id="txn_no"><%=txn_no %></span>
				</div>
			</div>
			
			<div class="innerCont2">
				<div class="innerCont2 text2">
					<h2>주문상품</h2>
				</div>
				<div class="innerCont2 item2">
					<span><%=pvo.getProd_name() %></span>
				</div>
			</div>
			
			<div class="innerCont2">
				<div class="innerCont2 text2">
					<h2>가격</h2>
				</div>
				<div class="innerCont2 item2">
					<span><%=won %> 원</span>
				</div>
			</div>
			
			<div class="innerCont item button">
				<button class="btn" id="main">
					<span class="material-symbols-outlined but">home<span class="btnText" id="mbtn">메인</span></span>
				</button>
				<button class="btn" id="order">
					<span class="material-symbols-outlined but">shopping_bag<span class="btnText" id="obtn">주문상세</span></span>
				</button>
				<button  class="btn" id="mpage">
					<span class="material-symbols-outlined but">person<span class="btnText" id="mbtn">마이페이지</span></span>
				</button>  		
			</div>
		</div>
		
		
</div>