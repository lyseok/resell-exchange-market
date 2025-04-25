
const maxContent = 10; 
const maxButton = 5; 
let numOfContent; 
let maxPage; 
const buttonsEl = $(".buttons");
const contentsEl = $('.kcy_boardTable');

//동기로 리스트를 받아오기 때문에 전역에서 정해놓는다.
numOfContent = articleList.length;
maxPage = Math.ceil(numOfContent / maxContent);
let page = 1; // 시작 페이지

console.log(mypath);
// 데이터로 만들 코드
// renderContent함수 내부에서 반복문으로 코드를 생성한다.
// 그 하나하나의 반복 횟수마다 호출되는 makeContent 함수 선언.
// 반복횟수는 서블릿에서 받아온 VO객체 리스트의 길이로 결정(numOfContent)
// 그러니 이 함수의 매개변수 data는 하나하나의 VO이다.
const makeContent = data => {
	let status = data.qna_com_status;
	qnaNo = data.qna_no;
	qnaTitle = data.qna_title;
	qnaAt = data.qna_at;
	if(status==0)
		status = "처리중";
	else
		status = "답변완료";
    return /*html*/ `
    	<tr>
			<td class="no first_col">${qnaNo}</td>
			<td class="title second_col">
				<a href="${mypath}/main/${board}/view.do?qnaNo=${qnaNo}">${qnaTitle}</a>
			</td>
			<td class="time third_col">${qnaAt}</td>
			<td class="com_status fourth_col">
				<a href="${mypath}/main/${board}/view.do?qnaNo=${qnaNo}">${status}</a>
			</td>
		</tr>`;
}
const renderContent = page => {
  code = `	<tr class="first_row">
				<th class="no" style="width:10%;">번호</th>
				<th class="title" style="width:50%;">제목</th>
				<th class="time" style="width:20%;">날짜</th>
				<th class="com_status">답변여부</th>
			</tr>`;
  for (let id = (page - 1) * maxContent + 1; id <= page * maxContent && id <= numOfContent; id++) {
    code += makeContent(articleList[id-1]);
	console.log("■■■■■■■■■■renderContent 실행 중 ==> ", code)
  }
  //여기까지 코드 생성이 잘 됐는데도, 웹페이지에 출력이 안 되고 있다면 contentsEl 대신 $("~")로 직접 지정해줘본다.
  contentsEl.html(code);
};


//윤석이의 버튼 렌더 로직 
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
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
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
$(function(){
	renderContent(page);
	renderButton(page);
});