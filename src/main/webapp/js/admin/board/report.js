
let list;
const maxContent = 15; // 한페이지에 보여줄 상품 갯수
const maxButton = 5; // 한번에 보여질 페이지 버튼 갯수
let numOfContent; // 전체 상품 갯수
let maxPage; // 전체 페이지 수
const buttonsEl = $(".buttons");
const contentsEl = $('#reportList'); // 글 목록을 담기 위한 리스트 요소
let page = 1;


const allReportBoard = () => {
  $.ajax({
    url: `${adminPath}/admin/selectAllReportBoard.do`,
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
		if(data.rpt_com_status == 0) {
		  com_status = "답변전";
		} else {
		  com_status = "답변완료";
		}
	  html += /* html */  `
	  <tr>
	    <td class="reportBoard">${data.rpt_no}</td>
	    <td class="reportBoard">${data.mem_no}</td>
	    <td class="reportBoard board_name">${data.rpt_title}</td>
	    <td class="reportBoard">${data.rpt_idx_no}</td>
	    <td class="reportBoard">${com_status}</td>
	    <td class="reportBoard">${data.rpt_time}</td>
	    <td><span id="deleteReportBoard" class="adm_del_btn">삭제</span></td>
	  </tr>
	  `;
	  return html; // 최종적으로 html 변수를 리턴합니다.
	};


  $(document).on("click", "#deleteReportBoard", function() {
    let rpt_no = $(this).closest("tr").find("td:eq(0)").text();
    console.log(rpt_no);
    if(confirm("정말 삭제하시겠습니까?")) {
      $.ajax({
        url: `${mypath}/admin/deleteReportBoard.do`,
        type: "post",
        data: {rpt_no: rpt_no},
        success: function(data) {
          alert("삭제되었습니다.");
          allReportBoard();
        },
        error: function(xhr) {
          console.log(xhr.status);
        },
        dataType: "json"
      });
    }
  });
  


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



