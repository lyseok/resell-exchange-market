<%@page import="rem.product.vo.ProdImgVO"%>
<%@page import="rem.product.vo.CateNameVO"%>
<%@page import="rem.file.vo.ImgFileVO"%>
<%@page import="java.util.List"%>
<%@page import="rem.product.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true"%>


<%@include file="/WEB-INF/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/product/productDetail.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.1.js"></script>

<% 
	System.out.println("loginSession :" + login);
	if(login == null) {
		response.sendRedirect(request.getContextPath() + "/accessCheck.do");
	}
	
	@SuppressWarnings("unchecked")
	List<ImgFileVO> list = (List<ImgFileVO>)request.getAttribute("imgfile");
	ProductVO pvo = (ProductVO)request.getAttribute("productDetail");
	ImgFileVO filevo = (ImgFileVO)request.getAttribute("profileImg");
	int countWish = (Integer)request.getAttribute("countWish");
	int countReview = (Integer)request.getAttribute("countReview");
	int countProduct = (Integer)request.getAttribute("countProduct");
	MemberVO memInfo = (MemberVO)request.getAttribute("memInfo");
	CateNameVO svo = (CateNameVO)request.getAttribute("svo");
	@SuppressWarnings("unchecked")
	List<ProdImgVO> clist = (List<ProdImgVO>)request.getAttribute("clist");
	
	String content = pvo.getProd_content().replaceAll("\\n", "<br>");
	
	int price = pvo.getProd_price();
	String priceWon = String.format("%,d", price);
	
%>
<script>
$(function(){
	
	pvoNo = <%=pvo.getProd_no()%>;
	loginInfo = <%=loginInfo.getMem_no()%>
	memNo = <%=pvo.getMem_no()%>
	status = <%=pvo.getProd_tr_status()%>
	code = '';
	data = '';
	
	
	$('#confirm').on('click', function(){
		
		location.href = `${mypath}/product/purchase.do?prod_no=${pvoNo}`;
	})
	
	if(loginInfo===memNo || status==1){
		$('#wish').prop('disabled', true).css('background-color', '#ccc');
		$('#chat').prop('disabled', true).css('background-color', '#ccc');
		$('#modal-open').prop('disabled', true).css('background-color', '#ccc');
	}
	
	 if(status==1){
	      $(".proSwiper").addClass("sold_out");
	   }
	
	$('.proImg').on('click', function(){
		
		location.href = `${mypath}/store/storePage.do?param=mem_no&value=${memNo}`;
	})
	
	
	$(document).on('click', '#wish', function(){
		
		$.ajax({
			
			url:`${mypath}/wishlist/insertWish.do`,
			type : 'post',
			data : {prod_no : pvoNo},
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			dataType : 'json',
			success : res=>{
				if(res.distinct == 0){	// 찜 해제
					$("#wish").find(".material-symbols-outlined").removeClass("heart");
					$("#wish").find(".material-symbols-outlined").addClass("but");
				}else{		// 찜 함
					$("#wish").find(".material-symbols-outlined").addClass("heart");
					$("#wish").find(".material-symbols-outlined").removeClass("but");
				}
				recountWishlist();
				$("#wbtn").text(res.countWish);
				$('#wishcnt').text(res.countWish);
			},
			error : xhr=>{
				alert(xhr.status)
			}
		})
	})
	
	/*var swiper = new Swiper(". .mySwiper", {
		  cssMode: true,
		  slidesPerView: 1,
		  spaceBetween: 10,
		  speed: 400,
		  loop: true,
		  navigation: {
		    nextEl: ". .swiper-button-next",
		    prevEl: ". .swiper-button-prev",
		  },
		  pagination: {
		    el: ". .swiper-pagination",
		    clickable: true
		  },
		  mousewheel: true,
		  keyboard: true
		});*/

		const swiperOptionDetail = () => {
	const slideCount = $('.product_wrap .swiper-slide').length;
	const shouldLoop = slideCount > 1; // 2개 이상일 때만 loop

	headerSwiper = new Swiper(".product_wrap .proSwiper", {
		cssMode: true,
		slidesPerView: 1,
		spaceBetween: 10,
		speed: 400,
		loop: shouldLoop,
		navigation: {
			nextEl: ".product_wrap .swiper-button-next",
			prevEl: ".product_wrap .swiper-button-prev",
		},
		pagination: {
			el: ".product_wrap .swiper-pagination",
			clickable: true
		},
		mousewheel: true,
		keyboard: true
	});
};
	swiperOptionDetail();
	$('#chat').on('click', function(){
		
		location.href = `${mypath}/chat/dditTalk.do?mem_no=${memNo}`;
	})
	
})
</script>

<div class="inner">
	
	<%@include file="/WEB-INF/include/category.jsp" %>
		<div class="product_wrap">
			<div class="dp_f prod_info_cont">
				<div class="swiper proSwiper">
					<div class="swiper-wrapper">
							<%for(ImgFileVO fvo : list) {%>
							<div class="swiper-slide">
								<img src="<%=fvo.getFile_path() %>" alt="prod_Img">
							</div>
							<%} %>
						</div>
						<div class="swiper-pagination"></div>
					<div class="swiper-button-prev arrow">
							<span class="material-symbols-outlined">arrow_back</span>
					</div>
					<div class="swiper-button-next arrow">
						<span class="material-symbols-outlined">arrow_forward</span>
					</div>
				</div>
				<div class="prod_info_txt_box">
					<div class="  status">
						<%if(pvo.getProd_tr_status()==0){ %>
						<span id="testBtn">판매중</span>
						<%}else{ %>
						<span>판매완료</span>
						<%} %>
					</div>
					<div class="name">
						<span id="name"><%=pvo.getProd_name() %></span>
						<span id="price"><%=priceWon %> <span>원</span></span>
					</div>
					<div id="divview" class="viewDetail">
						<div class="viewDetail_icon_box">
							<span class="material-symbols-outlined">favorite<span id="wishcnt" class="number"><%=countWish %></span></span>
							<span class="material-symbols-outlined">visibility<span class="number"><%=pvo.getProd_view() %></span></span>
							<span class="material-symbols-outlined">schedule<span class="number"><%=pvo.getProd_at() %></span></span>
						</div>
						<span class="material-symbols-outlined siren">siren<span class="report">
							<a href="<%=request.getContextPath()%>/main/report/wr.do?prod_no=<%=pvo.getProd_no()%>">신고하기</a></span>
						</span>
					</div>
					<div class="  trade">
						<%if(pvo.getProd_tr_approch()==0) {%>
						<span class="stat">거래 방식<span class="stat2">직거래</span></span>
						<%}else if(pvo.getProd_tr_approch()==1){ %>
						<span class="stat">거래 방식<span class="stat2">택배거래(선불)</span></span>
						<%}else if(pvo.getProd_tr_approch()==2) {%>
						<span class="stat">거래 방식<span class="stat2">택배거래(착불)</span></span>
						<%} %>
						<%if(pvo.getPrice_offer()==1){ %>
						<span class="stat">가격 제안<span class="stat2">가능</span></span>
						<%}else{ %>
						<span class="stat">가격 제안<span class="stat2">불가능</span></span>
						<%} %>
						<%if(pvo.getProd_condition()==1) {%>
						<span class="stat">상품 상태<span class="stat2">새상품(미사용)</span></span>
						<%}else if(pvo.getProd_condition()==2) {%>
						<span class="stat">상품 상태<span class="stat2">사용감 없음</span></span>
						<%}else if(pvo.getProd_condition()==3) {%>
						<span class="stat">상품 상태<span class="stat2">사용감 적음</span></span>
						<%}else if(pvo.getProd_condition()==4) {%>
						<span class="stat">상품 상태<span class="stat2">사용감 잦음</span></span>
						<%}else { %>
						<span class="stat">상품 상태<span class="stat2">고장/파손 상품</span></span>
						<%} %>
					</div>
					<div id="divbtn" class="buttons">
						<%
							if(pvo.getProd_tr_status()==0){
								if(pvo.getWish_count() == 0){
						%>
								<button class="btn" id="wish">
									<span class="material-symbols-outlined but">
										heart_plus<span class="btnText">찜</span>
										<span class="btnText" id="wbtn"><%=countWish %></span>
									</span>
								</button>
								<button class="btn" id="chat">
									<span class="material-symbols-outlined but">
										chat_bubble<span class="btnText" id="cbtn">띹톡</span>
									</span>
								</button>
								<button class="btn" type="button" id="modal-open">구매하기</button>
							<%
								} else{
							%>
								<button class="btn" id="wish">
									<span class="material-symbols-outlined heart">
										heart_plus<span class="btnText">찜</span>
										<span class="btnText" id="wbtn"><%=countWish %></span>
									</span>
								</button>
								<button class="btn" id="chat">
									<span class="material-symbols-outlined but">
										chat_bubble<span class="btnText" id="cbtn">띹톡</span>
									</span>
								</button>
								<button  class="btn" type="button" id="modal-open">구매하기</button>
						<%
								}
							} else{
						%>
								<button class="btn" id="wish">
									<span class="material-symbols-outlined but">
										heart_plus<span class="btnText">찜</span>
									</span>
								</button>
								<button class="btn" id="chat">
									<span class="material-symbols-outlined but">
										chat_bubble<span class="btnText" id="cbtn">띹톡</span>
									</span>
								</button>
								<button  class="btn" type="button" id="modal-open">구매하기</button>
						<%
								}
						%>
					</div>
				</div>		
			</div>
			
			<div class="dp_f prod_info_txt_cont">
				<div class="prod_descript_wrap">
					<h3>상품 정보</h3>
					<div class="prod_info_txt">
						<span><%=content %></span>
					</div>		
					<div class="prod_info_cate">
						<span class="material-symbols-outlined cate">inbox_text<span>카테고리</span></span>
						<span><%=svo.getCate_main_name() %> > <%=svo.getCate_sub_name() %></span>
					</div>		
				</div>
				<div class="prod_seller_info_cont">
					<div class="">
						<h3>상점 정보</h3>
					</div>
					<div class="prod_seller_box">
						<div class="store proImg">
							<img src="<%=filevo.getFile_path() %>" alt="profiile_img" id="profileImg">
						</div>
						<div class="store profile">
							<div class="store profile alias">							
								<span id="alias"><%=memInfo.getMem_alias() %></span>
							</div>
							<div class="store profile review">
								<span>상품 <%=countProduct %> | 후기 <%=countReview %></span>
							</div>
						</div>
						
					</div>
				</div>		
			</div>
			
			<div class="list_wrap inner">
      			<h1>추천 상품</h1>
      
      			<div class="inner list_cont">
        		<!-- 상품리스트 내용 삽입 -->
        
        			<div id="prod_list">
          				<ul>
          				<%for(ProdImgVO vo : clist) {%>
          					<li>
        						<a href="<%=request.getContextPath() %>/product/productDetail.do?prod_no=<%=vo.getProd_no()%>">
          						<span class="img_box">
            						<img src="<%=vo.getFile_path() %>" alt="<%=vo.getFile_org_name() %>" />
         						 </span>
         						 <span class="txt_box">
           							 <span><%=vo.getProd_name() %></span>
          						</span>
        						</a>
      						</li>
      					<%} %>
          				</ul>
        			</div>
        
      			</div>
    		</div>
	</div>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>

<div class="container">
  <div class="modal-btn-box">
  </div>
  
  <div class="popup-wrap" id="popup">
    <div class="popup">
      <div class="popup-head">
          <span class="head-title">
            상품 구매 
          </span>
      </div>
      <div class="popup-body">
      	<p>
      		해당상품을<br> 구매 하시겠습니까?
      	</p>
      </div>
      <div class="popup-foot">
        <span class="pop-btn confirm" id="confirm">확인</span>
        <span class="pop-btn close" id="close">취소</span>
      </div>
    </div>
</div>
</div>