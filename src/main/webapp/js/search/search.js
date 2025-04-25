$('#searchText').text(seasrchText + `의 검색결과`);

const maxContent = 15; // 한페이지에 보여줄 상품 갯수
const maxButton = 5; // 한번에 보여질 페이지 버튼 갯수
let numOfContent = prodList.length; // 전체 상품 갯수
let maxPage = Math.ceil(numOfContent / maxContent); // 전체 페이지 수
const buttonsEl = $(".buttons");
const contentsEl = $('#prod_list ul'); // 글 목록을 담기 위한 리스트 요소
let page = 1;



// code = '';
// $.each(list, function(i, v){
//   code += /* html */ `
//   <li>
//     <a href='${mypath}/product/productDetail.do?prod_no=${v.prod_no}'>
//       <span class="img_box">
//         <img src="<%=request.getContextPath() %>/images/shop/product1.png" alt="prod_img1" />
//       </span>
//       <span class="txt_box">
//         <span>${v.prod_name}</span>
//         <b>${v.prod_price}원</b>
//       </span>
//     </a>
//   </li>
//   `;
// });
console.log(prodList);
console.log('page:'+page);
console.log('numOfContent:'+numOfContent);
console.log('maxPage:'+maxPage);

//renderContent(page); // 시작페이지에 해당하는 상품 리스트 띄우기
//renderButton(page);

const makeContent = data => {
  if(data.prod_tr_status == 1){
    code = /* html */`
      <li class='sold_out'>
    `;
  } else {
    code = /* html */`
      <li>
    `;
  }
  code += /* html */`
        <a href="${mypath}/product/productDetail.do?prod_no=${data.prod_no}">
          <span class="img_box">
            <img src="${data.file_path}" alt="${data.file_org_name}" />
          </span>
          <span class="txt_box">
            <span>${data.prod_name}</span>
            <div class="dp_f jc_sb">
              <b>${data.prod_price.toLocaleString('ko-KR')}원</b>
              <span>${timeAgo(data.prod_newing)}</span>
            </div>
          </span>
        </a>
      </li>
    `;

  return code;
}

const renderContent = page => {
  code = '';
  // 글의 최대 개수를 넘지 않는 선에서, 화면에 최대 20개의 글 생성
  for (let id = (page - 1) * maxContent + 1; id <= page * maxContent && id <= numOfContent; id++) {
    code += makeContent(prodList[id-1]);
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

renderContent(page); // 시작페이지에 해당하는 상품 리스트 띄우기
renderButton(page);
$('#prod_list>ul').html(code);