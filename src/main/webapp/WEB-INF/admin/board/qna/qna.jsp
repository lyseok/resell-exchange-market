<%@page import="rem.admin.board.qna.vo.QnaBoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/admin_nav_botton.css">
	<script>
		$(function(){

			let list;
			const maxContent = 15; // 한페이지에 보여줄 상품 갯수
			const maxButton = 5; // 한번에 보여질 페이지 버튼 갯수
			let numOfContent; // 전체 상품 갯수
			let maxPage; // 전체 페이지 수
			const buttonsEl = $(".buttons");
			const contentsEl = $('#tBody'); // 글 목록을 담기 위한 리스트 요소
			let page = 1;

			
			// ajax로 qna 리스트를 받아온다.
			const qnaList = () => {
			  $.ajax({
			    url: "<%=request.getContextPath()%>/admin/qnaPage.do",
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
			
			const makeContent = data => {
			  let html = ""; // html 변수를 선언합니다.
			  html += /* html */ `
			    <tr>
			      <td>\${data.qna_no}</td>
			      <td>`;
			      
			  if (data.qna_type == 0) {
			    html += "상품문의";
			  } else {
			    html += "1:1문의";
			  }

			  html += `</td>
			      <td class="board_name"><a href="<%=request.getContextPath()%>/admin/qnaViewPage.do?qnaNo=\${data.qna_no}">\${data.qna_title}</a></td>
			      <td>\${data.mem_no}</td>
			      <td>`;

			  if (data.qna_com_status == 0) {
			    html += "답변 전";
			  } else if (data.qna_com_status == 1) {
			    html += "답변완료";
			  }

			  html += `
			      </td>
			      <td>\${data.qna_at}</td>
			      <td><button type="button" data-qnano='\${data.qna_no}' id="deleteBtn" class="adm_del_btn">삭제</button></td>
			    </tr>
			  `;

			  return html; // 최종적으로 html 변수를 리턴합니다.
			};
			
			$("#tBody").on("click", "#deleteBtn", function(){
				const qnaNo = $(this).data("qnano");
				if(confirm("삭제하시겠습니까?")){
					$.ajax({
						url: "<%=request.getContextPath()%>/admin/qnaDelete.do",
						data : "qnaNo=" + qnaNo,
						type: "get",
						success: res =>{
							location.reload();
						},
						error: xhr => {
							alert("오류: " + xhr.status);		
						}
					})
					alert("정상적으로 삭제되었습니다.");
				}else{
					alert("삭제실패");
				}

			})
			
			$("#searchNoticeBtn").on("click", function(){
				const searchText = $("#searchText").val();
				const searchSelect = $("#searchSelect").val();
				if(searchText==""){
					alert("검색어를 입력해주세요.");
				} else if(searchSelect == 'mem_no' && isNaN(searchText)){
					alert("숫자를 입력해주세요");
				} else{
					$.ajax({
						url: "<%=request.getContextPath()%>/admin/qnaSearch.do",
						data : {
							searchText : searchText,
							searchSelect : searchSelect
						},
					    type: "post",
						success: res =>{
							console.log(res);
							code = "";
							if(res.length > 0){
								$.each(res, function(i,v){
									code += 
										`<tr class="noticeBoard">
							              <td>\${v.qna_no}</td>
							              <td>
							             `;
							             if(v.qna_type == 0 ){
							            	 code += "상품문의";
							             } else if(v.qna_type == 1){
							            	 code += "1:1문의";					            	 
							             }
							        code +=  
							        	`</td>
							              <td class="board_name">\${v.qna_title}</td>
							              <td>\${v.mem_no}</td>
							              <td>
								             `;
							              
							             if(v.qna_com_status == 0 ){
							            	 code += "답변 전";
							             } else if(v.qna_com_status == 1){
							            	 code += "답변완료";					            	 
							             }
							        code +=`</td>					              
							              <td>\${v.qna_at}</td>
							              <td><button type="button" data-qnano='\${v.qna_no}' id="deleteBtn" class="adm_del_btn">삭제</button></td>
							            </tr>`;
								})
							} else{
								code +=  
						        	`<tr class="noticeBoard">
						              <td colspan="7">검색 결과가 없습니다.</td>
						             </tr>`;
							}
							$("#tBody").html(code);
						},
						error: xhr => {
							alert("오류: " + xhr.status);		
						}
					})
				}
			})
			
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
			    <div class="button" id="\${id}">\${id}</div>
			  `;
			  return code;
			}

			// 버튼을 클릭할때 해당 페이지로 이동하고 버튼을 새로 랜더링
			$(document).on("click", ".button", function () {
			  let page = parseInt($(this).text());

			  renderContent(page);
			  renderButton(page);
			});
		qnaList();
	})
	</script>
	
	<%
		List<QnaBoardVO> list = (List<QnaBoardVO>)request.getAttribute("qnalist");
	%>
    <div id="wrapper">

        <div id="container">

            <h1 id="container_title">글관리 > 문의</h1>
            <div class="container_wr">
                <section>
                    
                    <div class="local_desc local_desc03 jc_fe ">
                    	<form action="" class="search_form mt0">
			              <select name="searchNotice" id="searchSelect">
			                <option value="mem_no">작성자번호</option>
			                <option value="qna_title">제목</option>
			              </select>
			              <div>
			                <input type="text" id="searchText" placeholder="검색어를 입력하세요.">
			                <span class="material-symbols-outlined" id="searchNoticeBtn">search</span>
			              </div>
			            </form>
                    </div>

                    <div class="tbl_head01 tbl_wrap">
                        <table>
                            <caption class="dp_n">문의 게시판</caption>
                            <colgroup>
                            	<col width="10%">
                            	<col width="10%">
                            	<col width="10%">
                            	<col width="30%">
                            	<col width="10%">
                            	<col width="15%">
                            	<col width="8%">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">번호</th>
                                    <th scope="col">유형</th>
                                    <th scope="col">제목</th>
                                    <th scope="col">작성자</th>
                                    <th scope="col">답변여부</th>
                                    <th scope="col">작성일</th>
                                    <th scope="col">&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody id="tBody"></tbody>
                        </table>
                    </div>

                </section>
	            <div class="buttons">
	            </div>
                
            </div>
        </div>

    </div>
</body>

</html>