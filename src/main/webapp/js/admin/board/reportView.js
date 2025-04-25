// 신고 정보 출력
$('#mem_no').html(noticeVO.mem_no); // 신고자 번호 출력
$('#rpt_idx_no').html(noticeVO.rpt_idx_no); // 신고 대상 번호 출력
$('#rpt_title').html(noticeVO.rpt_title); // 신고 제목 출력
$('#rpt_text').html(noticeVO.rpt_text.replaceAll(/\n/g, "<br>")); // 신고 사유에서 줄바꿈을 <br>로 변환하여 출력
$('#rpt_time').html(noticeVO.rpt_time); // 신고일자 출력

// 신고 유형에 따라 텍스트 설정
if(noticeVO.rpt_type == 0){
  $('#rpt_type').text('사용자');
} else {
  $('#rpt_type').text('상품');		
}

// 댓글 등록 여부 확인
if(noticeVO.rpt_com_status == 0){ // 아직 답변 전인 경우
  $('#rpt_status').text('답변 전');

  // '답변하기' 버튼 생성
  code = /* html */ `
    <div class="btn_list03 btn_list">
      <button id="addComments">답변하기</button>
    </div>`;
    
  // 버튼을 상태 표시 영역(status_ck)에 추가
  $('#status_ck').append(code);

} else { // 이미 답변이 등록된 경우
  $('#rpt_status').text('답변 완료');

  // 댓글 내용 조회 요청
  $.ajax({
    url: mypath + "/admin/selectReportComments.do",
    type: "get",
    data: {no: noticeVO.rpt_no}, // 신고 번호로 조회
    success: function(data){
      // 조회된 댓글 내용 표시용 HTML 구성
      code = /* html */`
        <div class="admin_bd_view_cont commentsText">${data.cmt_cont}</div>
        <div class="admin_cmt_box_rt">
          <b>${data.cmt_at}</b>
          <b class="updateComments">수정</b>
        </div>
      `;

      // 댓글 영역에 스타일 클래스 추가 및 내용 출력
      $('#commentsArea').addClass('admin_cont');	
      $('#commentsArea').html(code);
    },
    error: function(xhr){
      console.log(xhr.status);
    },
    dataType: "json"
  });
}

// '답변하기' 버튼 클릭 시 동작
$('#status_ck').on('click', '#addComments', function(){
  // 댓글 입력 폼 HTML 생성
  code = /* html */	
	`
	<form action='${mypath}/admin/insertReportComments.do' id="report_comments" method="post">
		<input type="hidden" name="rpt_mem_no" id="rpt_mem_no" value=${noticeVO.rpt_no}>
		<div id="rpt_comments_form" class="admin_comments_form">
			<textarea name="commentsText" class="commentsText" cols="120" rows="7"></textarea>
			<button type="button" id="insertReportCommentsBtn">등록하기</button>
		</div>
	</form>
	`;

  // 댓글 입력 폼을 댓글 영역에 삽입
  $('#commentsArea').html(code);
});

// '등록하기' 버튼 클릭 시 동작
$(document).on('click', '#insertReportCommentsBtn', function(){
  // Ajax를 통해 댓글 등록 요청
  $.ajax({
    url: mypath + "/admin/insertReportComments.do",
    type: "post",
    data: {
      rpt_mem_no: $('#rpt_mem_no').val(), // 신고 번호
      commentsText: $('.commentsText').val().replaceAll(/\n/g, "<br>") // 입력된 댓글 (줄바꿈 처리)
    },
    success: function(data){
      // 등록된 댓글 내용을 출력할 HTML 구성
      code = /* html */`
      <div class="admin_bd_view_cont">
          ${data.cmt_cont}
        </div>
        <div class="admin_cmt_box_rt">
          <b >${data.cmt_at}</b>
          <b class="updateComments">수정</b>
        </div>
      `;

      // 댓글 영역 스타일 추가 및 내용 출력
      $('#commentsArea').addClass('admin_cont');	
      $('#rpt_status').text('답변 완료'); // 상태 텍스트 변경
      $('#status_ck').find('.btn_list03.btn_list').remove(); // '답변하기' 버튼 제거
      $('#commentsArea').html(code); // 댓글 출력
    },
    error: function(xhr){
      console.log(xhr.status);
    },
    dataType: "json"
  });
});

// '수정' 버튼 클릭 시 동작
$('#commentsArea').on('click', '.updateComments', function(){
  console.log($('.commentsText').html());
  // 댓글 수정 폼 HTML 생성
  code = /* html */`
    <form action='${mypath}/admin/insertReportComments.do' id="report_comments" method="post">
      <input type="hidden" name="rpt_mem_no" id="rpt_mem_no" value=${noticeVO.rpt_no}>
      <div id="rpt_comments_form">
		<textarea name="commentsText" class="commentsText" cols="120" rows="7">${$('.commentsText').html().replaceAll(/<br>/g, "\n")}</textarea>
        <button type="button" id="updateReportCommentsBtn">수정하기</button>
      </div>
    </form>`;

  // 댓글 입력 폼을 댓글 영역에 삽입
  $('#commentsArea').html(code);
});

// '등록하기' 버튼 클릭 시 동작
$(document).on('click', '#updateReportCommentsBtn', function(){
  // Ajax를 통해 댓글 등록 요청
  $.ajax({
    url: mypath + "/admin/updateReportComments.do",
    type: "post",
    data: {
      rpt_mem_no: $('#rpt_mem_no').val(), // 신고 번호
      commentsText: $('.commentsText').val().replaceAll(/\n/g, "<br>") // 입력된 댓글 (줄바꿈 처리)
    },
    success: function(data){
      // 등록된 댓글 내용을 출력할 HTML 구성
      code = /* html */`
      <div class="admin_bd_view_cont commentsText">${data.cmt_cont}</div>
        <div class="admin_cmt_box_rt">
          <b >${data.cmt_at}</b>
          <b class="updateComments">수정</b>
        </div>
      `;

      // 댓글 영역 스타일 추가 및 내용 출력
      $('#commentsArea').addClass('admin_cont');	
      $('#rpt_status').text('답변 완료'); // 상태 텍스트 변경
      $('#status_ck').find('.btn_list03.btn_list').remove(); // '답변하기' 버튼 제거
      $('#commentsArea').html(code); // 댓글 출력
    },
    error: function(xhr){
      console.log(xhr.status);
    },
    dataType: "json"
  });
});