let list;
const maxContent = 15;
const maxButton = 5;
let numOfContent;
let maxPage;
const buttonsEl = $(".buttons");
const contentsEl = $('#TXNlist');
let page = 1;



const txnListAll = () => {
  $.ajax({
    url: `${mypath}/admin/safeTXNBoardView.do`, // 데이터 로드 URL
    type: "get",
    success: function (data) {
      console.log(data); // 서버 응답 확인

      let code = ""; // HTML 코드 생성 변수
      $.each(data, function (i, v) {
        code += `
          <tr class="safeTXNBoard">
            <td>${v.safe_no}</td>
            <td>${v.prod_name}</td>
            <td>${v.prod_price}</td>
            <td>${v.safe_status}</td>
            <td>${v.sell}</td>
            <td>${v.buy}</td>
            <td>${v.safe_at}</td>
			<td>
				<div class="modal-btn-box">
				<button type="button" class="modal-open" data-idx="${v.safe_no}" id="modalbtn">상세정보</button>  
			</div>
			</td>
          </tr>
        `;
      });
      $("#TXNlist").html(code); // 테이블 업데이트
	  setmodal();

	      },

    error: function (xhr) {
      console.error("데이터 로드 실패:", xhr.status);
    },
    dataType: "json",
  });
};
	// 검색 기능 
$('#txnsearchBTN').on('click', function () {
  const searchSafeTXN = $('#searchSafeTXN').val();
  const searchText = $('#searchText').val();

  if (searchText == "") {
    alert("검색어를 입력하세요.");
    return false;
  }else{

  $.ajax({
    type: "get",
    url: `${mypath}/admin/safeTXNBoardSearch.do`,
    data: { searchSafeTXN, searchText },
    success: function (data) {
      let code = "";
      $.each(data, function (i, v) {
        code += `
		<tr class="safeTXNBoard">
		         <td>${v.safe_no}</td>
		         <td>${v.prod_name}</td>
		         <td>${v.prod_price}</td>
		         <td>${v.safe_status}</td>
		         <td>${v.sell}</td>
		         <td>${v.buy}</td>
		         <td>${v.safe_at}</td>
				<td>
					<div class="modal-btn-box">
					<button type="button" class="modal-open" data-idx="${v.safe_no}" id="modalbtn">상세정보</button>  
				</div>
				</td>
		       </tr>
        `;
      });
	  
	  
	  
      $("#TXNlist").html(code); // 검색 결과 업데이트
	  setmodal();

	        },

	     error: function (xhr) {
	       console.error("데이터 로드 실패:", xhr.status);
	     },
	     dataType: "json",
  });
  }
});

const setmodal = () => {
$(document).on("click", ".modal-open", function () {
        const safe_no = $(this).data("idx");
		console.log("거래 번호:", safe_no);
		openModal(safe_no); // 모달 열기 함수 호출
      });
};
setmodal();

const openModal = (safe_no) => {       
	$.ajax({
			url: `${mypath}/admin/safeTXNBoardModal.do`,
			type: "post",
			data: {
			safe_no
			},
			success: res =>{
					
					$("#safe_no").text(res.safe_no);
					
					code = "";
					
					$("#safe_no").text(res.safe_no);
					$("#prod_name").text(res.prod_name);
					$("#safe_at").text(res.safe_at);
					$("#prod_price").text(res.prod_price);
					$("#seller").text(res.sell);
					$("#buyer").text(res.buy);
					$("#safe_status").text(res.safe_status);
					
					
					$(".popup-wrap").css('display','flex').hide().fadeIn();
				},				
				error: xhr => {
					alert("오류: " + xhr.status) ;
					consol.log("==========값 확인 : " + safe_no);
				},
				dataType: "json"
				
			  });
		
		}
		$(document).on("click", ".popup-wrap", function (e) {
		  // 클릭한 곳이 모달 바깥 영역인지 확인
		  if ($(e.target).hasClass("popup-wrap")) {
		    $(".popup-wrap").fadeOut(); // 모달 닫기
		  }
		});
		$(document).on("click", "#closemodal", function () {
		  $(".popup-wrap").fadeOut(); // 모달 닫기
		});

const renderContent = (page) =>{
	code = '';
	
	for(let id = (page -1) * maxContent + 1; id <= page * maxContent && id <= numOfContent; id++){
		code += makecontent(list[id-1]);
	}
	contentsEl.html(code);
};

const renderButton = (page) => {
	buttonsEl.empty();
	
	if(page < 3){
		for (let id = 1; id <=5 && id <= maxPage; id++){
			
			buttonsEl.append(makeButton(id));
		}
	}else if(page > maxPage -3){
		for(let id = maxPage - 4; id <= maxPage && id <= maxPage; id++){
			if(id < 1) id = 1;
			buttonsEl.append(makeButton(id));	
		}
	}else{
		for(let id = page -2; id <= page + 2 && id <= maxPage; id++){
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
	let code =`
		<div class="button" id="${id}">${id}</div>
	`;
	
	return code; 
}

$(document).on("click", ".button", function(){
	let page = parseInt($(this).text());
	
	renderContent(page);
	renderButton(page);
	
})	;
	
txnListAll();



