/**
 * 
 */
const maxContent = 10; 
const maxButton = 5; 
let numOfContent; 
let maxPage; 
const buttonsEl = $(".buttons");
const contentsEl = $('.kcy_boardTable');


numOfContent = articleList.length;
maxPage = Math.ceil(numOfContent / maxContent);
let page = 1; 



const makeContent = data => {
	
	
	let status = data.rpt_com_status;
	rptNo = data.rpt_no;
	memNo = data.mem_no;
	rptTitle = data.rpt_title;
	rptTime = data.rpt_time;
	
	urlView = `${urlContextPath}/main/report/view.do?reportNo=${rptNo}`;

	if(status==0)
		status = "처리중";
	else
		status = "답변완료";
    return /*html*/ `
    	<tr>
			<td class="no first_col">${rptNo}</td>
			<td class="no first_col">${memNo}</td>
			<td class="title second_col">
				<a href="${urlView}">${rptTitle}</a>
			</td>
			<td class="time third_col">${rptTime}</td>
			<td class="com_status fourth_col">${status}</td>
		</tr>`;
}
const renderContent = page => {
  code = `	<tr class="first_row">
				<th class="no" style="width:10%;">번호</th>
				<th class="mem" style="width:10%;">작성자</th>
				<th class="title" style="width:50%;">제목</th>
				<th class="time" style="width:20%;">시간</th>
				<th class="com_status">답변여부</th>
			</tr>`;
  for (let id = (page - 1) * maxContent + 1; id <= page * maxContent && id <= numOfContent; id++) {
    code += makeContent(articleList[id-1]);
	console.log("renderContent 실행 중 ==> ", code)
  }
  //여기까지 코드 생성이 잘 됐는데도, 웹페이지에 출력이 안 되고 있다면 contentsEl 대신 $("~")로 직접 지정해줘본다.
  contentsEl.html(code);
};



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
  let page = parseInt($(this).text());
  renderContent(page);
  renderButton(page);
});

//READY FUNCTION으로 페이지 접속 시 리스트 출력

$(function(){
	renderContent(page);
	renderButton(page);
});