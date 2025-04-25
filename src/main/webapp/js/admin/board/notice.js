
let list;
const maxContent = 15; // 한페이지에 보여줄 상품 갯수
const maxButton = 5; // 한번에 보여질 페이지 버튼 갯수
let numOfContent; // 전체 상품 갯수
let maxPage; // 전체 페이지 수
const buttonsEl = $(".buttons");
const contentsEl = $('#noticeList'); // 글 목록을 담기 위한 리스트 요소
let page = 1;


// ajax로 공지사항 리스트를 받아온다.
const allNoticeBoard = () => {
  $.ajax({
    url: `${adminPath}/admin/selectAllNoticeBoard.do`,
    type: "get",
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

// 데이터 삽입
const makeContent = data => {
	  let html = ""; // html 변수를 'let'으로 선언
	  html += /* html */  `
	  <tr class="noticeBoard">
	      <td>${data.notice_no}</td>
	      <td class="board_name">${data.notice_title}</td>
	      <td>${data.notice_cnt}</td>
	      <td>${data.notice_at.substring(0,10)}</td>
	    </tr>
	  `;
	  return html; // 최종적으로 html 변수를 리턴합니다.
	};



const searchNoticeBoard = () => {
  const search_notice = $('#searchNotice').val();
  const search_text = $('#searchText').val();
  if (search_text == "") {
    alert("검색어를 입력하세요.");
    return false;
  } else {
    $.ajax({
      type: "get",
      url: `${mypath}/admin/searchNoticeBoard.do`,
      data: {
        searchNotice: search_notice,
        searchText: search_text
      },
      success: function(data) {
        code = '';
        $.each(data, function(i , v) {
          code += /* html */`
            <tr class="noticeBoard">
              <td>${v.notice_no}</td>
              <td>${v.notice_title}</td>
              <td>${v.notice_cnt}</td>
              <td>${v.notice_at}</td>
            </tr>
          `;
        });
        
        console.log(code);
        $("#noticeList").html(code);
      },
      error: function(xhr) {
        console.log(xhr.status);
      },
      dataType: "json"
    });
  }
}

// 수정 버튼을 클릭하면 textarea로admin_bd_view_cont의 내용을 가져와서 textarea에 넣어준다.
const noticeBoardUpdateForm = () => {
  var cont = $('.admin_bd_view_cont').text().replaceAll(/<br>/g, "\n");
  var code=`<textarea id="notice_cont" class="input" placeholder="내용을 입력하세요">${cont}</textarea>
        <div class="btn_list03 btn_list btn_wrap">
          <button id="confirmBtn" class="btn-primary">확인</button>
          <button id="cancelBtn">취소</button>
        </div>`;
  $('.admin_bd_view_cont').html(code);
}

// 삭제 버튼을 누르면 해당 게시글의 번호를 가지고 서블릿 호출
const deleteNoticeBoard = () => {
  $.ajax({
    url: `${mypath}/admin/deleteNoticeBoard.do`,
    type: "post",
    data: {notice_no: noticeNo},
    success: function(data) {
      console.log(data);
    if(data > 0) location.href = `${mypath}/admin/noticePage.do`;
    },
    error: function(xhr) {
      console.log(xhr.status);
    },
    dataType: "json"
  });
}

// 확인 버튼을 클릭하면 textarea의 내용을 가져와서 ajax로 보내준다.
const noticeBoardUpdate = () => {
  var cont = $('#notice_cont').val().replaceAll(/\n/g, "<br>");
  $.ajax({
    url: `${mypath}/admin/updateNotice.do`,
    type: "post",
    data: {notice_no: noticeNo, notice_cont: cont},
    success: function(data){
      console.log(data);
      $('.admin_bd_view_cont').html(cont);
    },
    error: function(xhr){
      console.log(xhr.status);
    },
    dataType: "json"
  });
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



  // ajax로 공지사항 리스트를 받아온다.
  allNoticeBoard();
	