<%@page import="rem.file.vo.ImgFileVO"%>
<%@page import="java.util.List"%>
<%@page import="rem.product.vo.CateNameVO"%>
<%@page import="rem.product.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true"%>

<%@include file="/WEB-INF/include/header.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/product/purchase.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.1.js"></script>
<%@include file="/WEB-INF/include/category.jsp" %>

<%
	ProductVO pvo = (ProductVO)request.getAttribute("pvo");
	CateNameVO cvo = (CateNameVO)request.getAttribute("cvo");
	List<ImgFileVO> list = (List<ImgFileVO>)request.getAttribute("list");
	int mem_bal = (Integer)request.getAttribute("mem_bal");
	
	String content = pvo.getProd_content().replaceAll("\\n", "<br>");
	
	System.out.println("loginSession :" + login);
	if(login == null) {
		response.sendRedirect(request.getContextPath() + "/accessCheck.do");
	}
	
	int price = pvo.getProd_price();
	int result = mem_bal-price;
	
	String balWon = String.format("%,d", mem_bal);
	String priceWon = String.format("%,d", price);
	String resultWon = String.format("%,d", result);
%>
<script>
$(function(){
	pvoNo = <%=pvo.getProd_no()%>
	resultWon = $('#bal').text();
	console.log(resultWon);
	
	$('#allCheck').on('change', function(){
		
		isChecked = $(this).prop('checked');
		$('.checkbox').prop('checked', isChecked);
	})
	
	$('.checkbox').on('change', function(){
		
		allChecked = $('.checkbox').length === $('.checkbox:checked').length;
		anyChecked = $('.checkbox:checked').length > 0;
		$('#allCheck').prop('checked', allChecked);
		$('#allCheck').prop('indeterminate', !allChecked && anyChecked);
	})
	
	$('#order').on('click', function(){
		
		if(!$('#checkbox1').is(':checked') || !$('#checkbox2').is(':checked') || !$('#checkbox3').is(':checked')){
			alert('모든 약관에 동의해주세요.')
		}else if(resultWon.startsWith('-')){
			alert('페이 잔액이 부족합니다.')
		}else{
			
			location.href = `${mypath}/product/insertTransaction.do?prod_no=${pvoNo}`;
		}
	})
})
</script>
<div class="inner">
	<h1 class="sell_tit">구매하기</h1>
	<hr>
		<div class="cont">
			<div class="innerCont">
				<div class="text">
					<h3>거래방법</h3>
				</div>
				<div class="item" id="item">
					<%if(pvo.getProd_tr_approch()==0){ %>
						<span>직거래</span>
					<%}else if(pvo.getProd_tr_approch()==1) {%>
						<span>택배거래(선불)</span>
					<%}else{ %>
						<span>택배거래(착불)</span>
					<%} %>
				</div>		
			</div>
			<div class="innerCont innerCont2">
			
				<div class="cont2_box">
					<div class="text">
						<div class="text prod">
							<h3>주문상품</h3>
						</div>
					</div>
					<div class="item">
						<div class="prod buy_prod">
							<span><%=pvo.getProd_name() %></span>
							<% for(ImgFileVO fvo : list){%>
								<img src="<%=fvo.getFile_path() %>" alt="prod_img" id="prod_img">
							<% } %>
						</div>
					</div>
				</div>
				
				
				<div class="cont2_box">
					<div class="text">
						<div class="text prod">
							<h2>상품 정보</h2>
						</div>
					</div>
					<div class="item">
						<div class="prod buy_prod">
							<span><%=content %></span>
						</div>
					</div>
				</div>
				
			</div>
			<div class="innerCont">
				<div class="text">
					<div class="cate">
						<h3>카테고리</h3>
					</div>
				</div>
				<div class="item">
					<div class="cate">
						<span><%=cvo.getCate_main_name() %> > <%=cvo.getCate_sub_name() %></span>
					</div>
				</div>
			</div>
			<div class="innerCont">
				<div class="text">
					<div class="text condition">
						<h3>상품상태</h3>
					</div>
				</div>
				<div class="item">
					<div class="condition">
						<%if(pvo.getProd_condition()==1){ %>
							<span>새상품(미사용)</span>
						<%}else if(pvo.getProd_condition()==2){ %>
							<span>사용감 없음</span>
						<%}else if(pvo.getProd_condition()==3){ %>
							<span>사용감 적음</span>
						<%}else if(pvo.getProd_condition()==4){ %>
							<span>사용감 잦음</span>
						<%}else{ %>
							<span>고장/파손 상품</span>
						<%} %>
					</div>
				</div>
			</div>
			
			<div class="innerCont">
				<div class="text">
					<div class="price">
						<h3>가격</h3>
					</div>
				</div>
				<div class="item">
					<div class="price">
						<span><%=priceWon %> 원</span>
					</div>
				</div>
			</div> 
			
			<div class="innerCont innerCont2 mt60">
				<div class="cont2_box">
					<div class="item2">
						<div class="check">
							<label>
								<input type="checkbox" id="allCheck" class="checkbox"> 아래 내용에 전체 동의해요<br>
							</label>
							<label>
								<input type="checkbox" id="checkbox1" class="checkbox">띹장터 서비스 이용약관 동의(필수)<br>
							</label>
							<label>
								<input type="checkbox" id="checkbox2" class="checkbox">개인정보 수집 및 이용 동의(필수)<br>
							</label>
							<label>
								<input type="checkbox" id="checkbox3" class="checkbox">개인정보 제3자 제공 동의(필수)<br>
							</label>
						</div>
					</div>
				</div>
				<div class="cont2_box">
					
					<div class="text2">
						<div class="">
							<h2>결제금액</h2>
						</div>
					</div>
					<div class="innerCont3 item4">
						<div class="pay">
							 <div class="item3 pay balT">
							 	<span>페이 잔액</span>
							 </div>
							 <div class="item3 pay balI">
							 	<span class="bal"><%=balWon %> 원</span>
							 </div>
						</div>
						<div class="pay">
							 <div class="item3 pay balT">
							 	<span>상품 금액</span>
							 </div>
							 <div class="innerCont3 item3 pay balI">
							 	<span class="bal"><%=priceWon %> 원</span>
							 </div>
						</div>
						<div class="pay">
							 <div class="item3 pay balT">
							 	<span>거래 후 페이</span>
							 </div>
							 <div class="item3 pay balI">
							 	<span id="bal"><%=resultWon %> 원</span>
							 </div>
						</div>
					</div>
				</div>
			</div>
	</div>
	
	<div class="order">
		<input type="button" value="구입하기" id="order">
	</div> 
</div>

<%@include file="/WEB-INF/include/footer.jsp" %>