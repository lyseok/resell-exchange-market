<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품상세</title>
<link rel="icon" href="<%=request.getContextPath() %>/images/favicon.png">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/reset.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/modal/modal.css">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/modal/modal.js"></script>
</head>
<body>
<!-- 모달 참고: https://muzi-muzi.tistory.com/6 -->
<div class="container">
  <div class="modal-btn-box">
    <button type="button" id="modal-open">모달창 열기</button>  
  </div>
  
  <div class="popup-wrap" id="popup">
    <div class="popup">
      <div class="popup-head">
          <span class="head-title">
            상품 구매 확정
          </span>
      </div>
      <div class="popup-body">
        <p>
          해당상품을<br> 구매 확정하시겠습니까?
        </p>
      </div>
      <div class="popup-foot">
        <span class="pop-btn confirm" id="confirm">확인</span>
        <span class="pop-btn close" id="close">취소</span>
      </div>
    </div>
  </div>
</div>


</body>
</html>