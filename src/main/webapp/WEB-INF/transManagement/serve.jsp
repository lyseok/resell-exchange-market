<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/store/store.css">




<style>
	#ajaxview table {
  	font-family: arial, sans-serif;
  	border: 1px solid black;
  	width: 80%;
}

th, td{
  border: 1px solid black;
  text-align: center;
  padding: 8px;
  
}
.inner .t-btn{
	background-color: white;
}






 .trans-container{
	padding-left: 200px;
}


/* 모달 전체 스타일 */
#trackModal, #trackViewModal, #confrimModal, #confrimCheckModal, #reviewModal, #insertReviewModal {
    display: block;  /* 모달을 화면에 표시 */
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 400px;
    background-color: white;
    padding: 20px;
    z-index: 1000;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    text-align: center;
}

/* 버튼 스타일 */
.modal-footer button {
    margin-top: 20px;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
}

#submitTrackInfo {
    background-color: #4CAF50;
    color: white;
    border: none;
}

.modal-closebtn {
    background-color: #f44336;
    color: white;
    border: none;
}

#rating-container {
    text-align: center;
    font-size: 25px;
    cursor: pointer;
}

.star {
    color: #ccc;
    transition: color 0.2s;
}

.star.selected {
    color: gold;
}
.transImg{
	width : 100px;
	height: 100px;
}





</style>

<%@include file="/WEB-INF/include/category.jsp" %>





<div class="inner">
	  <div id = "manageType">
	 	<input type = "button" value = "상품관리" id ="prodm" class = "t-btn"> 
	 	<input type = "button" value = "구매관리" id ="buym" class = "t-btn"> 
	 	<input type = "button" value = "판매관리" id ="sellm" class = "t-btn">
	  </div>
	  
	  <br><br>
	  
	  <div id ="deliverCon">
	  	<input type = "button" value = "전체" id ="viewAll" class = "t-btn"> 
	 	<input type = "button" value = "배송중" id ="deliverStatus"class = "t-btn"> 
	 	<input type = "button" value = "거래중" id ="transStatus" class = "t-btn"> 
	 	<input type = "button" value = "거래완료" id ="completeStatus" class = "t-btn">
	  </div>
  
  <br><br>
	<div id = ajaxview>
		
		<table>
	  		<tr>
		    <th>사진</th>
		    <th>판매상태</th>
		    <th>상태</th>
		    <th>상품명</th>
		    <th>가격</th>
		    <th>끌어 올린 시간</th>
		    <th>기능</th>
		  </tr>
	  		
	</table>
		
	</div>

</div>


<div id = "modal-container"></div>
<script src="<%=request.getContextPath() %>/js/transaction/transaction.js"></script>
<script>

	let currentCategory ='상품관리';
	let currentStatus = '4';
	
	loadTransactionData(currentCategory, currentStatus);
	
	$('#prodm').on('click', function() {
		
		currentCategory = '상품관리';
		loadTransactionData1(currentCategory, currentStatus);
	
	});
	
	$('#buym').on('click', function() {
		currentCategory = '구매관리';
		loadTransactionData2(currentCategory, currentStatus);
	
	});
	
	$('#sellm').on('click', function() {
		currentCategory = '판매관리';
		loadTransactionData3(currentCategory, currentStatus);
	
	});
	
	
	
	

	$('#viewAll').on('click', function () {
		//currentStatus = $('#view').val();
		currentStatus = 4;
		loadTransactionData(currentCategory, currentStatus);
	})

	$('#transStatus').on('click', function () {
		//currentStatus = $('#transStatus').val();
		currentStatus = 0;
		loadTransactionData(currentCategory, currentStatus);
	})
	
		$('#deliverStatus').on('click', function () {
		//currentStatus = $('#deliverStatus').val();
		currentStatus = 1;
		loadTransactionData(currentCategory, currentStatus);
	})
	$('#completeStatus').on('click', function () {
		//currentStatus = $('#completeStatus').val();
		currentStatus = 2;
		loadTransactionData(currentCategory, currentStatus);
	})
	
</script>



<%@include file="/WEB-INF/include/footer.jsp" %>

