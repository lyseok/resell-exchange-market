/**
 * 
 */

const maxContent = 15; // 한페이지에 보여줄 상품 갯수
const maxButton = 5; // 한번에 보여질 페이지 버튼 갯수
let numOfContent = memberList.length; // 전체 상품 갯수
let maxPage = Math.ceil(numOfContent / maxContent);; // 전체 페이지 수
const buttonsEl = $(".buttons");
const contentsEl = $('#member_list'); // 글 목록을 담기 위한 리스트 요소
let page = 1;


const setMemberList = () => {
  let reportMember = 0;
  let revokeMember = 0;
  for(let i = 0; i < memberList.length; i++){
    if(memberList[i].mem_status == 1) revokeMember++;
    if(memberList[i].mem_status == 2) reportMember++;
  }
  
  $('.local_desc p span:first-child').html(memberList.length);
  $('.local_desc p span:nth-child(2)').html(reportMember);
  $('.local_desc p span:nth-child(3)').html(revokeMember);
}


// // 페이지 로드시 전체 상품 리스트를 가져오는 코드
// $.ajax({
//   url: `${mypath}/selectAllMainPageProd.do`,
//   type: 'get',
//   success: function(data) {
//     prodList = data; // DB에서 받아온 리스트 저장
//     numOfContent = prodList.length; // 전체 상품 갯수 저장
//     maxPage = Math.ceil(numOfContent / maxContent); // 페이지 수 계산
//     let page = 1; // 시작 페이지

//     renderContent(page); // 시작페이지에 해당하는 상품 리스트 띄우기
//     renderButton(page);
//   },
//   error: function(xhr){
//     console.log(xhr.status)
//   },
//   dataType: 'json'
// });

// 데이터로 만들 코드
const makeContent = data => {
  return /* html */`
    <tr>
      <td class="td_mbid">${data.mem_no}</td>
      <td class="td_mbname">${data.mem_name}</td>
      <td class="td_mbname sv_use">${data.mem_alias}</td>
      <td class="td_num">${data.mem_tel == undefined ? '-' : data.mem_tel}</td>
      <td>${data.mem_bal}</td>
      <td class="td_boolean">${data.mem_status == 0 ? '일반회원' : data.mem_status == 1 ? '탈퇴회원' : '제제회원'}</td>
      <td class="td_boolean">${data.mem_join_at}</td>
      <td class="td_boolean" id="report">제제</td>
    </tr>
    `;
}

$('#member_list').on('click', '#report', function(){
  if(confirm(`${$(this).closest('tr').children('.td_mbname').text()} 회원을 제제시키시겠습니까?`)){
    console.log($(this).closest('tr').children('.td_mbid').text());
    $.ajax({
      url: `${adminPath}/admin/updateBanMember.do`,
      type: 'post',
      data: {
        mem_no: $(this).closest('tr').children('.td_mbid').text()
      },
      success: function(data) {
        if(data > 0){
          alert(`${$(this).closest('tr').children('.td_mbname').text()} 회원을 제제시켰습니다.`);
          let banMemberCount = parseInt($('.local_desc p span:nth-child(2)').text());
          banMemberCount += 1;
          $('.local_desc p span:nth-child(2)').html(banMemberCount);

        } else {
          alert('작업에 실패했습니다.');
        }
      },
      error: function(xhr) {
        console.log(xhr.status);
      },
      dataType: 'json'
    });
  }
});


const renderContent = page => {
  code = '';
  // 글의 최대 개수를 넘지 않는 선에서, 화면에 최대 20개의 글 생성
  for (let id = (page - 1) * maxContent + 1; id <= page * maxContent && id <= numOfContent; id++) {
    code += makeContent(memberList[id-1]);
  }
  console.log(code);
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

renderContent(page); // 시작페이지에 해당하는 상품 리스트 띄우기
renderButton(page);
setMemberList();