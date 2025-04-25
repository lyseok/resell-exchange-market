<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>
<link rel="stylesheet" type="text/css"href="<%=request.getContextPath() %>/css/store/store.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/modal/modal.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/trans/trans.css">


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/admin/product/review.css">



<%@include file="/WEB-INF/include/category.jsp"%>


<div class="inner">
<div class="trans_manage_div">
	<div id="manageType">
		<input type="button" value="상품관리" id="prodm" class="t-btn manageBtn" data-type="상품관리" style="color:#AD49E1;"/> &nbsp;ㅣ&nbsp; 
		<input type="button" value="구매관리" id="buym" class="t-btn manageBtn" data-type="구매관리"/> &nbsp;ㅣ&nbsp;
		<input type="button" value="판매관리" id="sellm" class="t-btn manageBtn" data-type="판매관리"/>
	</div>
	
	<br>
	<br>

	<div id="deliverCon" style="display:none;">
		<input type="button" value="전체" id="viewAll" class="t-btn deliverBtn" data-status="4" style="color:#AD49E1;"> &nbsp;ㅣ&nbsp;
		<input type="button" value="거래중" id="deliverStatus" class="t-btn deliverBtn" data-status="0"> &nbsp;ㅣ&nbsp; 
		<input type="button" value="배송중" id="transStatus" class="t-btn deliverBtn" data-status="1"> &nbsp;ㅣ&nbsp;
		<input type="button" value="거래완료" id="completeStatus" class="t-btn deliverBtn" data-status="2">
		
	</div>
	<div id="prodCon" >
		<input type="button" value="전체" id="" class="t-btn prodBtn" data-status="4" style="color:#AD49E1;"> &nbsp;ㅣ&nbsp;
		<input type="button" value="판매중" id="" class="t-btn prodBtn" data-status="0"> &nbsp;ㅣ &nbsp;
		<input type="button" value="판매완료" id="" class="t-btn prodBtn" data-status="1"> &nbsp;ㅣ&nbsp;
		<input type="button" value="예약중" id="" class="t-btn prodBtn" data-status="2">
	</div>


	
	<hr style="color:#cecece;"><br>
	<div id="ajaxview">
			<table id="ajaxtable">

			</table>
	</div>
	
	<br>
	<br>
	
	
</div>
</div>
<br>


<div id ="modal-container">

</div>


<script src="<%=request.getContextPath() %>/js/transaction/transaction.js"></script>
<script type="text/javascript">

	let currentCategory ='상품관리';
	let currentStatus = '4';
	let clickType = "상품관리";	// 관리 메뉴를 클릭했을 때의 당시에 저장된 type 데이터
	
	loadTransactionData(currentCategory, currentStatus);
	
	// 상품관리 구매관리 판매관리 버튼 클릭 시, 하나의 메소드가 동작한다. (loadTransactionData())
	let manageBtn = $(".manageBtn");
	let deliverBtn = $(".deliverBtn");		// deliverBtn을 클릭했을때의 이벤트를 가져오기 위한 Element
	let deliverCon = $("#deliverCon");
	let prodCon = $("#prodCon");
	let prodBtn = $(".prodBtn");
	
	manageBtn.on("click", function(){
		let type = $(this).data("type");		// 현재 클릭한 버튼의 type 데이터
		clickType = type;	// 전역으로 설정
// 		currentCategory = '상품관리';
		if(type == "상품관리"){
			deliverCon.hide();
			prodCon.show();
		} else{
			deliverCon.show();
			prodCon.hide();
		} 
		manageBtn.css("color", "");
		$(this).css("color", "#AD49E1");
		loadTransactionData(type, currentStatus);
	});
	
	deliverBtn.on("click", function(){
		let status = $(this).data("status");		// 버튼 클릭 시 가져오기 위한 status 값
		deliverBtn.css("color", "");
		$(this).css("color", "#AD49E1");
		loadTransactionData(clickType, status);
	});
	
	prodBtn.on("click", function(){
		let status = $(this).data("status");		// 버튼 클릭 시 가져오기 위한 status 값
		prodBtn.css("color", "");
		$(this).css("color", "#AD49E1");
		loadTransactionProd(clickType, status);
	});
	
</script>



<%@include file="/WEB-INF/include/footer.jsp"%>

