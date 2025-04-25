/**
 * 
 */
///////////**상품리스트 초기 변수 선언(feat.LYS) *///////////
let maxContent = 10; // 한페이지에 보여줄 상품 갯수
const maxButton = 5; // 한번에 보여질 페이지 버튼 갯수
let numOfContent; // 전체 상품 갯수
let maxPage; // 전체 페이지 수
let page = 1; // 시작 페이지
const buttonsEl = $(".buttons");
const contentsEl = $('#prod_list');
let tabName;
let storeProdList;
tabList = "상품";
//let tabCnt = 0; => numOfContent가 제 역할을 다 해줌.
maxPage = Math.ceil(numOfContent / maxContent); // 페이지 수 계산

// 데이터로 만들 코드
const makeContent = vo => {
	contentCode = "";
	if(tabName!="tabReview"){
		let dateAnnounce = timeAgo(vo.prod_newing);
		if(vo.prod_tr_status==1)
			contentCode+= `<li id='kcy_prodlist_li' class='sold_out' data-price='${vo.prod_price}' data-date='${vo.prod_newing}'>
						<a href='${mypath}/product/productDetail.do?prod_no=${vo.prod_no}'>
							<span class='img_box'>`;
		else if(vo.prod_tr_status==0)
			contentCode+= `<li data-price='${vo.prod_price}' data-date='${vo.prod_newing}'>
						<a href='${mypath}/product/productDetail.do?prod_no=${vo.prod_no}'>
							<span class='img_box'>`;
		contentCode+= `				<img src='${vo.file_path}' alt='${vo.prod_name}'/>
							</span>
							<span class='txt_box'>
								<span>${vo.prod_name}</span>
								<span id='priceNdate'>
									<span><b>${vo.prod_price.toLocaleString("ko-KR")}</b></span>
									<span>${dateAnnounce}</span>
								</span>
							</span></a></li>`;
	} else{
		let dateAnnounce = timeAgo(vo.review_timestamp);
		contentCode += `
		<li id='rev${vo.txn_no}' data-price='${vo.prod_price}' data-date='${vo.review_timestamp}'>
			<span class='img_box'>
				<a href='${mypath}/store/storePage.do?param=mem_no&value=${vo.mem_no}'>
				<img src='${vo.file_path}' alt='${vo.mem_alias}' class='thumb'/>
				</a>
			</span>
			<span class='txt_box'>
				<span id='memName'><b> <a href='${mypath}/store/storePage.do?param=mem_no&value=${vo.mem_no}'>${vo.mem_alias} </a></b></span>
				<a href='${mypath}/product/productDetail.do?prod_no=${vo.prod_no}'>
				<span class='kcy_reviewRating'>
					<span class='material-symbols-outlined star' style='color:yellow;'>
						star_half
					</span>
					<span>${vo.review_rating}</span>
				</span>
				
					<input type='button' value='${vo.prod_name}' id='${vo.prod_name}' style='margin-botton: 10px;'>
				
				<span id='reviewCont'> ${vo.review_cont} </span>
				</a>
			</span>
			<span class='date_box'>
				<p>${dateAnnounce}</p>
			</span>
		</li>`;
	}
	return contentCode;
}


const renderContent = page => {
	numOfContent = storeProdList.length;
	maxPage = Math.ceil(numOfContent / maxContent);
	code = '<ul>';//renderContent();
  // 글의 최대 개수를 넘지 않는 선에서, 화면에 최대 20개의 글 생성
  for (let id = (page - 1) * maxContent + 1; id <= page * maxContent && id <= numOfContent; id++) {
    code += makeContent(storeProdList[id-1]);
  }
  code+=`</ul>`;
  $('#prod_list').html(code);
};

const tabChanging = () => {
	page = 1;
	renderContent(page);
	renderButton(page);
	
	$("#prod_list").removeClass("kcy_rev");
	$("#prod_list").removeClass("kcy_prod");
	$(".store_list_cnt").html(tabList+" <span>"+numOfContent+"</span>");

}

///////////**버튼 생성 함수(feat.LYS) *///////////
const renderButton = (page) => {
  // 버튼 리스트 초기화
  $(".buttons").empty(); 
  console.log("렌더링 버튼 실행..!");
  // 화면에 최대 5개의 페이지 버튼 생성
  if(page < 3){
    for (let id = 1; id <= 5 && id <= maxPage; id++) {
      $(".buttons").append(makeButton(id));
    }
  } else if(page > maxPage - 3){
    for (let id = maxPage - 4; id <= maxPage && id <= maxPage; id++) {
      if(id < 1) id = 1;
      $(".buttons").append(makeButton(id));
    }
  } else {
    for(let id = page - 2; id <= page + 2 && id <= maxPage; id++){
      $(".buttons").append(makeButton(id));
    }
  } 

$.each($('.button'), function(_i, v){
    if($(v).text() == page){
      $(v).addClass("active");
    }
  });
}
const makeButton = (id) => {
  let code = /*html*/`
    <div class="button" id="${id}">${id}</div>
  `;
  return code;
}
$(document).on("click", ".button", function () {
  page = parseInt($(this).text());

  renderContent(page);
  renderButton(page);
});



function timeAgo(dateString) {
    const now = new Date(); // 현재 시간
    const targetDate = new Date(dateString); // 입력된 날짜 문자열을 Date 객체로 변환
    const diff = now - targetDate; // 현재 시간과의 차이 (밀리초 단위)

    const seconds = Math.floor(diff / 1000); // 초 단위로 변환
    const minutes = Math.floor(seconds / 60); // 분 단위
    const hours = Math.floor(minutes / 60); // 시간 단위
    const days = Math.floor(hours / 24); // 일 단위
    const years = Math.floor(days / 365); // 년 단위

    if (years > 0) {
        return `${years}년 전`;
    } else if (days > 0) {
        return `${days}일 전`;
    } else if (hours > 0) {
        return `${hours}시간 전`;
    } else if (minutes > 0) {
        return `${minutes}분 전`;
    } else {
        return `${Math.abs(seconds)}초 전`;
    }
}

const loadTabContent = () => {
	$(".store_tab_box li").on("click", function () {
		$(".store_tab_box li").removeClass("on");
		$(this).addClass("on");
	
	    // 데이터 속성으로부터 탭 이름 가져오기
		tabName = $(this).data("tab");
		
		const formData = new FormData();
		formData.append("storeId", storeId);
		formData.append("tabName", tabName);
		
		$.ajax({
			url: mypath + "/store/tabContent.do",
			type: "POST",
			data: formData,
			dataType: "json",
			contentType: false,
			processData: false,
			success: function(data){
				let tabList;	
				storeProdList = data;
				console.log("■AJAX data === , ",storeProdList);
				switch(tabName){
				case "tabProd":
					maxContent = 10;
					tabList = "상품";
					tabChanging();
					$("#prod_list").addClass("kcy_prod");
					
					$(".store_list_filter #sort_date").removeClass("on");
					$(".store_list_filter #sort_price_desc").removeClass("on");
					$(".store_list_filter #sort_price_asc").removeClass("on");
					$(".store_list_filter #sort_date").addClass("on");

					$(".store_list_cnt").html(`${tabList}<span>${numOfContent}</span>`);
					console.log("■■■■■■■■■■■■■■■■■■■■■■■■■■■■ tabList ==> ", tabList);
					console.log("■■■■■■■■■■■■■■■■■■■■■■■■■■■■ numOfContent ==> ", numOfContent);
					break;
				case "tabReview":
					maxContent = 2;
					tabList = "상점후기";
					tabChanging();
					$("#prod_list").addClass("kcy_rev");
					
					$(".store_list_filter #sort_date").removeClass("on");
					$(".store_list_filter #sort_price_desc").removeClass("on");
					$(".store_list_filter #sort_price_asc").removeClass("on");
					$(".store_list_filter #sort_date").addClass("on");

					$(".store_list_cnt").html(`${tabList}<span>${numOfContent}</span>`);
					console.log("■■■■■■■■■■■■■■■■■■■■■■■■■■■■ tabList ==> ", tabList);
					console.log("■■■■■■■■■■■■■■■■■■■■■■■■■■■■ numOfContent ==> ", numOfContent);
					
					break;
				case "tabSoldout":
					maxContent = 10;
					tabList = "판매완료";
					tabChanging();

					$("#prod_list").addClass("kcy_prod");
					
					$(".store_list_filter #sort_date").removeClass("on");
					$(".store_list_filter #sort_price_desc").removeClass("on");
					$(".store_list_filter #sort_price_asc").removeClass("on");
					$(".store_list_filter #sort_date").addClass("on");

					$(".store_list_cnt").html(`${tabList}<span>${numOfContent}</span>`);
					console.log("■■■■■■■■■■■■■■■■■■■■■■■■■■■■ tabList ==> ", tabList);
					console.log("■■■■■■■■■■■■■■■■■■■■■■■■■■■■ numOfContent ==> ", numOfContent);
					break;
				case "tabWishlist":
					maxContent = 10;
					tabList = "찜 목록";
					tabChanging();

					$("#prod_list").addClass("kcy_prod");
					
					$(".store_list_filter #sort_date").removeClass("on");
					$(".store_list_filter #sort_price_desc").removeClass("on");
					$(".store_list_filter #sort_price_asc").removeClass("on");
					$(".store_list_filter #sort_date").addClass("on");

					$(".store_list_cnt").html(`${tabList}<span>${numOfContent}</span>`);
					console.log("■■■■■■■■■■■■■■■■■■■■■■■■■■■■ tabList ==> ", tabList);
					console.log("■■■■■■■■■■■■■■■■■■■■■■■■■■■■ numOfContent ==> ", numOfContent);
					break;
				}
			},
			error: function(xhr){
				console.log("상태:: " + xhr.status);
			}
		});
	});
}


const loadProdTabAtFirst = () => {
	const formDataAT = new FormData();
	formDataAT.append("storeId", storeId);
	formDataAT.append("tabName", "tabProd");
	$.ajax({
		url: mypath + "/store/tabContent.do",
		type: "POST",
		data: formDataAT,
		dataType: "json",
		contentType: false,
		processData: false,
		success: function(data){
			storeProdList = data;
			tabChanging();
		},
		error: function(xhr){
			console.log("상태:: " + xhr.status);
		}
	});
}

function tabFiltering() {
	$("#sort_price_asc").on("click", function () {
	    $(".store_list_filter li").removeClass("on");
	    $(this).addClass("on");
	    sortList("price", "asc");
	});

	$("#sort_price_desc").on("click", function () {
	    $(".store_list_filter li").removeClass("on");
	    $(this).addClass("on");
	    sortList("price", "desc");
	});

	$("#sort_date").on("click", function () {
	    $(".store_list_filter li").removeClass("on");
	    $(this).addClass("on");
	    sortList("date", "desc");
	});
}

function sortList(criteria, order) {
    let $list = $("#prod_list ul"); // ul 요소 선택
    let $items = $list.children("li"); // 모든 li 요소 가져오기

    $items.sort(function (a, b) {
        let aValue = $(a).data(criteria); // 첫 번째 요소의 data 값
        let bValue = $(b).data(criteria); // 두 번째 요소의 data 값

        if (criteria === "price") {
            aValue = parseFloat(aValue); // 숫자로 변환
            bValue = parseFloat(bValue); // 숫자로 변환
        } else if (criteria === "date") {
            aValue = new Date(aValue.replace(" ", "T").replace("/", "-")); // 날짜로 변환
            bValue = new Date(bValue.replace(" ", "T").replace("/", "-")); // 날짜로 변환
            console.log(aValue, bValue);
        }

        // 정렬 기준에 따라 오름차순 또는 내림차순 결정
        return order === "asc" ? aValue - bValue : bValue - aValue;
    });

    // 정렬된 li를 ul에 다시 추가
    $list.html($items);
}

const viewRatingScore = () => {
	$(".ratingView .star").each(function(){
		let rating = parseInt($(this).data('const'));
		console.log(rating);
		console.log(avgRating);
		if(rating>avgRating)
			$(this).addClass('disable');
		else if(rating<avgRating || avgRating - rating>=0.55)
			$(this).addClass('able');
	});
}

const reviewRatingScore = () => {
	$(".kcy_reviewRating .star").each(function(){
		var rating = parseInt($(this).data('const'));
		var revRat = avgRating;
		if(rating>revRat || revRat-rating<=0.55)
			$(this).addClass('revDisable');
		else
			$(this).addClass('revAble');
	});
}

const backgroundThumbnail = () => {
    $('#kcy_imageInput').on('change', function (event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const imageUrl = e.target.result; // Data URL 생성

                // CSSOM을 통해 :after 가상 선택자의 background 동적으로 설정
                const styleSheet = document.styleSheets[0];
                const rules = styleSheet.cssRules || styleSheet.rules;

                for (let i = 0; i < rules.length; i++) {
                    if (rules[i].selectorText === '.store_info_box:after') {
						console.log(rules[i].selectorText);
                        rules[i].style.background = `url('${imageUrl}') no-repeat center / cover`;
                        break;
                    }
                }
            };
            reader.readAsDataURL(file); // 파일을 Data URL로 변환
        }
    });
}


const editProfileForm = () => {
	storeImgDiv = $(".store_info_box");
		thumbnailDiv = $(".thumb");
		profileImg = $("#kcy_previewImg");
	storeTxtDiv = $(".store_info_txt_wrap");
		storenameDiv = $(".info_txt_tit");
		introductDiv = $(".store_info_txt_box");
		editclickDiv = $(".edit");
	
		
	let imgUploadAct = $("<input>", {type:"file", id:"kcy_imageInput", accept:"image/*", style:"display: none;"});
	let imgUploadLie = $("<span>", {class: "material-symbols-outlined", onclick:"document.getElementById('kcy_imageInput').click();"});
	storeImgDiv.append(imgUploadAct);
	storeImgDiv.append(imgUploadLie.text("perm_media"));
		
		
	let memAlias = $("#kcy_storename").text();
	let storename = $("<input>", {type:"text", value:memAlias, id:"kcy_editAlias", style:"font-size:20px; color:#898989; font-weight:bold;"});
	let storenameAfterDiv = $("<div>", {class: "mem_ck"});

	storenameDiv.empty();
	storenameDiv.append(storename);
	storenameDiv.append(storenameAfterDiv);
	
	let storeintroductPtag = $("#kcy_storeintroduct"); 
	let prInfo = storeintroductPtag.text();
	let storeintroduct = $("<textarea>", 
		{id: "kcy_editPrInfo", 
		 style: "height: 100%; font-size: 20px;background-color: #8b8b8b1c;resize: none;color: black;border: none;border-bottom: 2px solid #0000007e;outline: none;"});
	storeintroductPtag.before(storeintroduct.text(prInfo));
	storeintroductPtag.remove();


	
	let completeEdit = $("<div>", {class: "completeEdit"});
	let completeEditChild_i = $("<span>", {class:"material-symbols-outlined", style: "color: #9da1e0;", id: "fuckingducking"}).text("save");
	let completeEditChild_ii= $("<span>", {id: "compleNsave"}).text("저장하기");
	completeEdit.append(completeEditChild_i); 
	completeEdit.append(completeEditChild_ii); 
	

	
	editclickDiv.before(completeEdit);
	editclickDiv.remove();
};




