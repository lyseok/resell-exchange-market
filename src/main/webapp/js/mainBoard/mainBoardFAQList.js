const maxContent = 10; // 한페이지에 보여줄 상품 갯수
const maxButton = 5; // 한번에 보여질 페이지 버튼 갯수
let numOfContent; // 전체 상품 갯수
let maxPage; // 전체 페이지 수
let page = 1; // 시작 페이지
const buttonsEl = $(".buttons");
const contentsEl = $('.kcy_boardList');
let FAQList = accountList;
numOfContent = accountList.length;
maxPage = Math.ceil(numOfContent / maxContent); // 페이지 수 계산

// 데이터로 만들 코드
const makeContent = vo => {

	return `
			      <li>
		        <span id="articleTitle" style="float:left;">
				  <a href="${urlView}${vo.faq_no}">
		            ${vo.faq_title}
				  </a>
				</span>
				<span id="articleAt" style="float:right;max-height:fit-content;">
				  ${vo.faq_at}
		        </span>
		      </li>`;
}


const renderContent = page => {
	numOfContent = FAQList.length;
	maxPage = Math.ceil(numOfContent / maxContent);
	code = '';//renderContent();
  // 글의 최대 개수를 넘지 않는 선에서, 화면에 최대 20개의 글 생성
  for (let id = (page - 1) * maxContent + 1; id <= page * maxContent && id <= numOfContent; id++) {
    code += makeContent(FAQList[id-1]);
  }
  contentsEl.html(code);
};


// 버튼 랜더 로직
const renderButton = (page) => {
  // 버튼 리스트 초기화
  buttonsEl.empty(); 
  console.log("렌더링 버튼 실행..!");
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

const loadFAQAt = () => {
	kcy_selectedTab = "kcy_";
	if(tabName=="account") {
		FAQList = accountList;
		kcy_selectedTab = kcy_selectedTab + "account";
	}
	if(tabName=="transaction"){
		FAQList = transactionList;
		kcy_selectedTab = kcy_selectedTab + "transaction";
	}
	if(tabName=="safeTransaction"){
		FAQList = safeTransactionList;
		kcy_selectedTab = kcy_selectedTab + "safe_transaction";
	}
	if(tabName=="store"){
		FAQList = storeList;
		kcy_selectedTab = kcy_selectedTab + "store";
	}
	if(tabName=="etc"){
		FAQList = etcList;
		kcy_selectedTab = kcy_selectedTab + "etc";
	} 

	renderContent(page);
	renderButton(page);
	$(".tabItemSpan").css("color", "#a7a7a7");
	$(`#${kcy_selectedTab}`).find("span").css("color", "#3a3a3a");
}

$(".tabItems").on("click", function(){
	
	tabFAQ = $(this).attr("id");
	
	if(tabFAQ=="kcy_account") FAQList = accountList;
	if(tabFAQ=="kcy_transaction") FAQList = transactionList;
	if(tabFAQ=="kcy_safe_transaction") FAQList = safeTransactionList;
	if(tabFAQ=="kcy_store") FAQList = storeList;
	if(tabFAQ=="kcy_etc") FAQList = etcList; 
	
	renderContent(page);
	renderButton(page);
	$(".tabItemSpan").css("color", "#a7a7a7");
	$(this).find("span").css("color", "#3a3a3a");
});

$(function(){
	loadFAQAt();
});