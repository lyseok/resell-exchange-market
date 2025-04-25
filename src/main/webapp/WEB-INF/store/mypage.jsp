<%@page import="rem.file.vo.ImgFileVO"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/store/store.css">
<script src="<%=request.getContextPath() %>/js/store/store.js"></script>
<%
	MemberVO storeVO = (MemberVO)request.getAttribute("storeVO");
	int storeId = (Integer)request.getAttribute("storeId");
	int countAllProducts     = (Integer)request.getAttribute("countAllProducts");
    int countSoldoutProducts = (Integer)request.getAttribute("countSoldoutProducts");
    ImgFileVO profileImg = (ImgFileVO)request.getAttribute("profileImg");
    String profileImgSrc = profileImg.getFile_path();
    double averageRating = (Double)request.getAttribute("storeRatingAvg");
    
    String storeAlias = storeVO.getMem_alias();
    String introductText = storeVO.getPr_info();
    if(introductText == null) introductText = storeAlias +  "님의 상점입니다. 환영합니다 ^0^";
    introductText = introductText.replaceAll("\\n", "<br>");
    
    Timestamp timestamp = Timestamp.valueOf(storeVO.getMem_join_at());
	SimpleDateFormat dateFormat = new SimpleDateFormat("yy.MM.dd");
	String shopOpenDate = dateFormat.format(timestamp);
%>
<script>
	storeId = <%=storeId%>;
	avgRating = <%=averageRating%>;
	profileImgSrc = "<%=profileImgSrc %>";
	console.log(avgRating);
	
	$(document).ready(function () {
		viewRatingScore();
		tabFiltering();
		loadProdTabAtFirst();
		loadTabContent();
	});
</script>
	<div id="store" class="inner">
		<div class="store_info_wrap">
			<div class="store_info_box">
				<div class="thumb">
					<img src="<%=profileImgSrc %>" alt="shop_info_img" id="kcy_previewImg">
				</div>
				<b><%=storeAlias %></b>
				<span class="ratingView">
				<span class="material-symbols-outlined star" data-const="1" title="<%=storeAlias%> 상점평점: <%=averageRating%>">star</span>
				<span class="material-symbols-outlined star" data-const="2" title="<%=storeAlias%> 상점평점: <%=averageRating%>">star</span>
				<span class="material-symbols-outlined star" data-const="3" title="<%=storeAlias%> 상점평점: <%=averageRating%>">star</span>
				<span class="material-symbols-outlined star" data-const="4" title="<%=storeAlias%> 상점평점: <%=averageRating%>">star</span>
				<span class="material-symbols-outlined star" data-const="5" title="<%=storeAlias%> 상점평점: <%=averageRating%>">star</span>
				</span>
				
				<p class="prod_cnt">상품 <span class="cnt"><%=countAllProducts %></span></p>
				<img class="store_info_wrap_biggerThumb" src="<%=profileImgSrc %>" alt="shop_info_img">
			</div>
			<div class="store_info_txt_wrap">
				<div class="info_txt_tit">
					<h6 id="kcy_storename"><%=storeVO.getMem_alias() %></h6>
					<div class="mem_ck">
						<% if(storeVO.getMem_tel() != null){ %>
						<span class="material-symbols-outlined">check_circle</span>
						<p>본인인증 완료</p>
						<%} else { }%>
					</div>
				</div>
				<div class="store_info_icon_box">
					<ul>
						<li class="shop_open">
							<span class="material-symbols-outlined">storefront</span>
							<div>상점오픈일 <span><%=shopOpenDate %></span></div>
						</li>
						<li class="shop_cart">
							<span class="material-symbols-outlined">shopping_cart</span>
							<div>상품판매 <span><%=countSoldoutProducts %></span></div>
						</li>
					</ul>
				</div>
				<div class="store_info_txt_box">
					<p id="kcy_storeintroduct"><%=introductText %></p>
				</div>

<%if(loginInfo.getMem_no() == storeVO.getMem_no()){ %>
				<div class="edit">
					<span class="material-symbols-outlined">edit</span>
					<span id="edit">수정하기</span>
				</div>
<%} else { %>
					<div class="siren">
						<span class="material-symbols-outlined siren">siren<span class="report">
							<a href="<%=request.getContextPath()%>/main/report/wr.do?storeId=<%=storeId%>">신고하기</a></span></span>
					</div>
<%} %>
			</div>
		</div>
<script>
$(".edit *").on("click", function(){
	editProfileForm();
});
$(document).on("click", ".completeEdit", function(){
	$(document).on("click", ".completeEdit", function(){
		editedAlias  = $("#kcy_editAlias").val();
		editedPrInfo = $("#kcy_editPrInfo").val().replaceAll(/<br>/g, "\\n");;
		
		checkNewImg = "NEW-IMG";
		
		const fileInput = document.getElementById("kcy_imageInput");
	    const file = fileInput.files[0];
	    if (!file) {
	        checkNewImg = "NO";
	    }
	    const formData = new FormData();
	    formData.append("checkNewImg", checkNewImg);
	    formData.append("editedImage", file);
		formData.append("editedAlias", editedAlias);
		formData.append("editedPrInfo", editedPrInfo);
	    
		console.log(editedAlias +" "+ editedPrInfo);
		
		$.ajax({
			url: "<%=request.getContextPath()%>/store/mypageProfileEdit.do?",
			type: "POST",
			dataType:"json",
			data: formData,
			contentType: false,
			processData: false,
			success: function(data){
				if(data.result=="success"){
					location.href= '<%=request.getContextPath()%>/store/storePage.do?param=mem_no&value=<%=loginInfo!=null ? loginInfo.getMem_no() : "" %>';//주소를 재요청
				}else{
					alert("에러 발생^0^: 수정사항이 저장되지 않았습니다. 관리자에게 문의해주세요.");
				}
				
			},
			error: function(xhr){
				console.log("상태: " + xhr.status);
			}
			
		});
	});
});
$(document).on("click", ".completeEdit", function(){
	editedAlias  = $("#kcy_editAlias").val();
	editedPrInfo = $("#kcy_editPrInfo").val().replaceAll(/<br>/g, "\\n");;
	
	checkNewImg = "NEW-IMG";
	
	const fileInput = document.getElementById("kcy_imageInput");
    const file = fileInput.files[0];
    if (!file) {
        checkNewImg = "NO";
    }
    const formData = new FormData();
    formData.append("checkNewImg", checkNewImg);
    formData.append("editedImage", file);
	formData.append("editedAlias", editedAlias);
	formData.append("editedPrInfo", editedPrInfo);
    
	console.log(editedAlias +" "+ editedPrInfo);
	
	$.ajax({
		url: "<%=request.getContextPath()%>/store/mypageProfileEdit.do?",
		type: "POST",
		dataType:"json",
		data: formData,
		contentType: false,
		processData: false,
		success: function(data){
			if(data.result=="success"){
				location.href= '<%=request.getContextPath()%>/store/storePage.do?param=mem_no&value=<%=loginInfo!=null ? loginInfo.getMem_no() : "" %>';//주소를 재요청
			}else{
				alert("에러 발생^0^: 수정사항이 저장되지 않았습니다. 관리자에게 문의해주세요.");
			}
			
		},
		error: function(xhr){
			console.log("상태: " + xhr.status);
		}
		
	});
});
$(document).on("change", "#kcy_imageInput", function () {
    const input = this;
    const previewImage = $("#kcy_previewImg")[0];
    const file = input.files[0];

    if (file) {
        previewImage.style.display = "block";
        previewImage.src = URL.createObjectURL(file);
        input.addEventListener("blur", () => URL.revokeObjectURL(previewImage.src));
    } else {
        previewImage.style.display = "none";
    }
});
</script>
		<div class=store_tab_wrap>
			<ul class="store_tab_box">
				<li class="on" data-tab="tabProd">
					<a href="javascript:void(0)">상품</a>
				</li>
				<li data-tab="tabReview">
					<a href="javascript:void(0)">상점후기</a>
				</li>
				<li data-tab="tabSoldout">
					<a href="javascript:void(0)">판매상품</a>
				</li>
<%if(loginInfo.getMem_no() == storeVO.getMem_no()){ %>
				<li data-tab="tabWishlist">
					<a href="javascript:void(0)">찜목록</a>
				</li>
<%} %>
			</ul>
		</div>
		<div class="store_product_list_wrap">
			<h6 class="store_list_cnt">상품 <span>N</span></h6>
			<div class="store_list_filter">
				<ul>
					<li class="on" id="sort_date"><a href="javascript:void(0)">최신순</a></li>
					<li id="sort_price_desc"><a href="javascript:void(0)">고가순</a></li>
					<li id="sort_price_asc"><a href="javascript:void(0)">저가순</a></li>
				</ul>
			</div>
			<div id="tabContent">
				<div id="prod_list">
				</div>
			</div>
			<br><br>
			<div class="kcy_viewFooter">
				<div class="null">
				</div>
				<div class="buttons">
				</div>
				<div class="GeulSsooGi">
					<input type="button" value="문의글 작성하기" id="btnGeulSsooGi" style="display:none;"> 
				</div>
			</div>
		</div>
	</div>
<%@include file="/WEB-INF/include/footer.jsp" %>