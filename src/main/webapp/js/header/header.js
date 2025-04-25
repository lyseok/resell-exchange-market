/**
 * 
 */


document.getElementById('top_btn').addEventListener('click', function () {
  //창의 스크롤을 본문 최상단으로 부드럽게 이동시킵니다.
  window.scroll({
  	top: 0,
    behavior: 'smooth',
  });
}, { once: false });

$('#searchBtn').on('click', function() {
	search();
});

$('#searchText').on('keydown', function(e){
	if(e.keyCode == 13){
		search();
	}
});

const recountWishlist = () => {
	$.ajax({
		url: `${mypath}/wishlist/countWishlist.do`,
		type: "post",
		success: function(data) {
			$('#countWishlist').text(data);
		},
		error: xhr => {
			console.log(xhr.status);
		},
		dataType: "json"
	});
};




const search = () => {
	let searchKeyword = $('.search_box input').val();
	if(searchKeyword == "") {
		alert("검색어를 입력하세요.");
		return false;
	} else {
		location.href = `${mypath}/searchPage.do?searchText=${searchKeyword}`;
	}
}

const swiperView = () => {
	$.ajax({
		url:`${mypath}/selectNewView.do`,
		type: 'post',
		success: function(data){
			//console.log(data);
			//console.log(data.length);
			$('#viewListCount').text(data.length);
			code = '';
			$.each(data, function(i, v) {
				code += /* html */`
					<div class="swiper-slide">
						<a href="http://localhost/product/productDetail.do?prod_no=${v.prod_no}" class="btn">
							<img src="${v.file_path}" alt="${v.prod_name}" />
						</a>
					</div>
				`;
			});
			$('.view .swiper-wrapper').html(code);
			swiperOption();
		},
		error: function(xhr){
			console.log(xhr.status);
		},
		dataType: 'json'
	});
};

const swiperOption = ()=> {
	headerSwiper = new Swiper(".view .mySwiper", {
		cssMode: true,
		slidesPerView: 1,
		spaceBetween: 10,
		speed: 400,
		loop: true,
		navigation: {
			nextEl: ".view .swiper-button-next",
			prevEl: ".view .swiper-button-prev",
		},
		pagination: {
			el: ".view .swiper-pagination",
			type: "fraction",
		},
		mousewheel: true,
		keyboard: true
	});
}
let headerSwiper;
// swiperView();
recountWishlist();
