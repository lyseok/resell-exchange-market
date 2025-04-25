<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/reset.css">
<%@include file="/WEB-INF/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/product/uploadProd.css">
<%@include file="/WEB-INF/include/category.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.1.js"></script>
<script src="<%=request.getContextPath() %>/js/product/insertProduct.js"></script>

<%

	System.out.println("loginSession :" + login);
	if(login == null) {
		response.sendRedirect(request.getContextPath() + "/accessCheck.do");
	}

%>
<script>
let selectedFiles = [];
const imgCount = document.getElementById("imgCount");

$(function(){
	let imgUploadAct = $("<input>", {type:"file", id:"photo", accept:"image/*", style:"display: none;"});
	let imgUploadLie = $("<span>", {class: "material-symbols-outlined add_photo", onclick:"document.getElementById('photo').click();"});
	$('.photo').append(imgUploadAct);
	$('.photo').append(imgUploadLie.text("add_a_photo"));
	
	$('#prod_name').on('input', function(){
		const namelength = $(this).val().length;
		$('#nameCount').text(namelength + '/50')
		if(namelength > 50){
			alert("최대 50자까지 입력할 수 있습니다");
		}
	})
	
	$('#prod_content').on('input', function(){
		const contlength = $(this).val().length;
		$('#contCount').text(contlength + '/500')
		if(contlength > 500){
			alert("최대 500자까지 입력할 수 있습니다");
		}
	})
	
	getMainCate();
	getSubCate();
	clickSubCate();
	insertProduct();
	
	$('#photo').on('change', function(){
		
		addPhoto();
	})
})

</script>

<div class="inner">
	<h1 class="prod_tit">상품정보</h1>
	<hr>
		<div class="cont">
			<br>
			<div class="prod_cont" id="prod_cont">
				<div class=" text">
					<h3>상품이미지(<span id="imgCount">0</span>/5)</h3>					
				</div>
				<div class="item" id="add_img">
					<div class="photo">
						<input type="file" id="photo" name="photo" multiple>
					</div> 
				</div>		
			</div>
			<div class="prod_cont">
				<div class=" text">
					<h3>상품명</h3>					
				</div>
				<div class=" item">
					<div class="name">
						<input type="text" id="prod_name" name="prod_name" maxlength="50" placeholder="상품명을 입력해 주세요."><span id="nameCount">0/50</span>
					</div> 
				</div>		
			</div>
			
			<div class="prod_cont">
				<div class=" text">
					<h3>카테고리</h3>					
				</div>
				<div class=" item">
					<div class="cate">
						<div class="cate_item">
							<ul id="cate_main_id" name="cate_main_id" class="category">
							
							</ul>
						</div>
							
						<div class="cate_item">
							<ul id="cate_sub_id" name="cate_sub_id" class="category">
								
							</ul>
						</div>
					</div> 
				</div>		
			</div>
			
			<div class="prod_cont">
				<div class=" text">
					<h3>상품상태</h3>					
				</div>
				<div class=" item">
					<div class="stat">
						<div class="rdobox">
							<input type="radio" class="radio" name="prod_condition" value="1">새상품(미사용)
						</div>
						<div class="rdobox">
							<input type="radio" class="radio" name="prod_condition" value="2">사용감 없음
						</div>
						<div class="rdobox">
							<input type="radio" class="radio" name="prod_condition" value="3">사용감 적음
						</div>
						<div class="rdobox">
							<input type="radio" class="radio" name="prod_condition" value="4">사용감 잦음
						</div>
						<div class="rdobox">
							<input type="radio" class="radio" name="prod_condition" value="5">고장/파손 상품
						</div>
					</div> 
				</div>		
			</div>
			
			<div class="prod_cont">
				<div class=" text">
					<h3>설명</h3>					
				</div>
				<div class=" item">
					<div class="intro">
						<textarea id="prod_content" name="prod_content" 
						placeholder="브랜드, 모델명, 구매시기, 하자 유무 등 상품 설명을 최대한 자세히 적어주세요 전화번호,&#13;&#10;SNS계정 등 개인정보 입력은 제한될 수 있습니다."></textarea><br> <span id="contCount">0/500</span>				
					</div> 
				</div>		
			</div>
			
			<div class="prod_cont">
				<div class=" text">
					<h3>가격</h3>					
				</div>
				<div class=" item">
					<div class="price">
						<input type="number" id="prod_price" name="prod_price" placeholder="가격을 입력해 주세요.">원<br>
						<div class="ckbox">
							<input type="checkbox" id="price_offer" name="price_offer" value="1">가격 제안받기
							<label for="price_offer"></label>
						</div>
					</div> 
				</div>		
			</div>
			
			<div class="prod_cont">
				<div class=" text">
					<h3>거래방식</h3>					
				</div>
				<div class=" item">
					<div class="method">
						<div class="rdobox">
							<input type="radio" class="radio" name="prod_tr_approach" value="1"">
							택배거래(선불)
						</div>
						<div class="rdobox">
							<input type="radio" class="radio" name="prod_tr_approach" value="2">택배거래(착불)<br>
						</div>
						<div class="rdobox">
							<input type="radio" class="radio" name="prod_tr_approach" value="0">직거래
						</div>
					</div> 
				</div>		
			</div>
			
			
	</div>
</div>
<div class="innerGray">
	<div class="inner">
		<div class="insert">
			<input type="button" value="등록하기" id="insert">
		</div> 
	</div>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>