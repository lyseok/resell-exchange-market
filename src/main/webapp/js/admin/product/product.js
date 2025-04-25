
let list;
const maxContent = 15; // 한페이지에 보여줄 상품 갯수
const maxButton = 5; // 한번에 보여질 페이지 버튼 갯수
let numOfContent; // 전체 상품 갯수
let maxPage; // 전체 페이지 수
const buttonsEl = $(".buttons");
const contentsEl = $('#prodTbody'); // 글 목록을 담기 위한 리스트 요소
let page = 1;


// ajax로 상품 리스트를 받아온다.
const productList = () => {
  $.ajax({
    url: `${adminPath}/admin/prodListPage.do`,
    type: "post",
    success: function(data) {
      list = data;
      numOfContent = list.length; // 전체 상품 갯수
      maxPage = Math.ceil(numOfContent / maxContent);
      renderContent(page); // 시작페이지에 해당하는 상품 리스트 띄우기
      renderButton(page);
    },
    error: function(xhr) {
      console.log(xhr.status);
    },
    dataType: "json"
  });
}

$("#searchProductBtn").on("click", function(){
	productSearch();
})
// 검색 아이콘 클릭 시
const productSearch= () =>{
	const select = $("#searchProduct").val();
	const value = $("#searchProductText").val();
	
	if (value == ""){
	  productList();
	} else if( select == 'prod_no' && isNaN(value)){
	  alert("숫자로 입력해주세요");
	  return;	
	}else {		
		$.ajax({
			url: `${adminPath}/admin/adminProdSearch.do`,
			type: "post",
			data: {
				"select" : select,
				"value" : value			
			},		
			success: function(data) {
				if(data.length > 0) {
					list = data;
					numOfContent = list.length; // 전체 상품 갯수
					maxPage = Math.ceil(numOfContent / maxContent);
					renderContent(page); // 시작페이지에 해당하는 상품 리스트 띄우기
					renderButton(page);
				} else{
					code = `
					<tr>
					  <td colspan="7">검색된 채팅방이 없습니다.</td>
					</tr>
					`;
					contentsEl.html(code);
				}
			},
			error: xhr =>{
				alert("오류: " + xhr.status);
			},
			dataType: "json"		
		})
	}
	
}



// 데이터로 만들 코드
const makeContent = data => {
  return /* html */`
    <tr>
	  <td class="td_category">${data.prod_no}</td>
	  <td class="td_category">${data.mem_no}</td>
	  <td class="td_num">${data.prod_name}</td>
	  <td>${data.prod_price}</td>
	  <td class="td_boolean">${data.prod_tr_status == 0 ? '판매중' : data.prod_tr_status == 1 ? '판매완료' : '예약중'}</td>
	  <td class="td_boolean">${data.prod_deleted == 0 ? 'x' : 'o'}</td>
	  <td class="td_datetime">${data.prod_at}</td>
    </tr>
    `;
}


const renderContent = page => {
  code = '';
  // 글의 최대 개수를 넘지 않는 선에서, 화면에 최대 20개의 글 생성
  for (let id = (page - 1) * maxContent + 1; id <= page * maxContent && id <= numOfContent; id++) {
    code += makeContent(list[id-1]);
  }
  contentsEl.html(code);
};


// 버튼 랜더 로직
const renderButton = (page) => {
  // 버튼 리스트 초기화
  buttonsEl.empty(); 

  // 화면에 최대 5개의 페이지 버튼 생성
  if(page < 3){
    for (let id = 1; id <= 5 && id <= maxPage; id++) {
      buttonsEl.append(makeButton(id));
    }
  } else if(page > maxPage - 3){
    for (let id = maxPage - 4; id <= maxPage && id <= maxPage; id++) {
      if(id < 1) id = 1;
      buttonsEl.append(makeButton(id));
    }
  } else {
    for(let id = page - 2; id <= page + 2 && id <= maxPage; id++){
      buttonsEl.append(makeButton(id));
    }
  } 

  $.each($('.button'), function(i, v){
    if($(v).text() == page){
      $(v).addClass("active");
    }
  });
}

// 버튼을 만드는 코드
const makeButton = (id) => {
  let code = /*html*/`
    <div class="button" id="${id}">${id}</div>
  `;
  return code;
}

// 버튼을 클릭할때 해당 페이지로 이동하고 버튼을 새로 랜더링
$(document).on("click", ".button", function () {
  let page = parseInt($(this).text());

  renderContent(page);
  renderButton(page);
});

productList();
