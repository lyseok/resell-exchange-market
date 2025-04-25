<%@page import="rem.chat.vo.MessageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/admin_nav_botton.css">
	<script>
		$(function(){
			let list;
			const maxContent = 15; // 한페이지에 보여줄 상품 갯수
			const maxButton = 5; // 한번에 보여질 페이지 버튼 갯수
			let numOfContent; // 전체 상품 갯수
			let maxPage; // 전체 페이지 수
			const buttonsEl = $(".buttons");
			const contentsEl = $('#tbody'); // 글 목록을 담기 위한 리스트 요소
			let page = 1;
			

			const messageList = () => {
				$.ajax({
				    url: "<%=request.getContextPath()%>/admin/messageList.do",
				    type: "post",
				    dataType: "json", // JSON 데이터를 기대
				    success: function(data) {
			          list = data;
			          numOfContent = list.length; // 전체 상품 갯수
			          maxPage = Math.ceil(numOfContent / maxContent);
			          renderContent(page); // 시작페이지에 해당하는 상품 리스트 띄우기
			          renderButton(page);
			        },
				    error: xhr => {
				        alert("오류: " + xhr.status);
				    }
				});
			}
			

			// 데이터로 만들 코드
			const makeContent = data => {
			  html = "";
			  html += 
				  `<tr>
		              <td>\${data.msg_no}</td>
		              <td>\${data.chat_room_no}</td>
		              <td>\${data.mem_no}</td>
		              <td>\${data.msg_imgck == 0 ? "파일 없음" : "파일 있음"}</td>
		              <td>\${data.msg_cont}</td>
		              <td>\${data.msg_create_at}</td>
		          </tr>`;
			  return html;
			}
			
			
			$("#searchMsgBtn").on("click", function(){
				let select = $("#searchMsg").val();
				let value = $("#MsgText").val();
				if (value == "") {
					alert("검색어를 입력하세요.");
					return false;
				} else if(select == "mem_no" && isNaN(value)){
					alert("숫자로 입력해주세요.");					
				} else {
					$.ajax({
						url:"<%=request.getContextPath()%>/admin/searchMessageNo.do",
						type: "post",
						data: {
							"select" : select,
							"value" : value
						},
					    success: function(data) {
							if(data.length > 0){
					          list = data;
					          numOfContent = list.length; // 전체 상품 갯수
					          maxPage = Math.ceil(numOfContent / maxContent);
					          renderContent(page); // 시작페이지에 해당하는 상품 리스트 띄우기
					          renderButton(page);
							} else{
								html = "";
								html += `<tr><td colspan="6">검색된 채팅방이 없습니다.</td></tr>`;
								
								$("#tbody").html(html);
							}
						},
						error: xhr => {
							alert("오류: " + xhr.status);
						},
						dataType:"json"
						
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
			
			messageList();

		})
	</script>
    <div id="wrapper">

        <div id="container" class="">

            <h1 id="container_title">채팅관리 > 메세지 관리</h1>
            <div class="container_wr">
                <section>
                    
                    <div class="local_desc local_desc03 jc_sb ai_c">
                    	<h2>최근 거래내역</h2>
                        <form action="" class="search_form mt0">
                            <select name="searchMsg" id="searchMsg">
                                <option value="mem_no">발신자 번호</option>
                                <option value="msg_cont">내용</option>
                            </select>
                            <div>
                                <input type="text" id="MsgText" name="searchText">
                                <span class="material-symbols-outlined" id="searchMsgBtn">
                                    search
                                </span>
                            </div>
                        </form>
                    </div>

                    <div class="tbl_head01 tbl_wrap">
                        <table>
                            <caption>메세지 내역</caption>
                            <colgroup>
                            	<col width="10%">
                            	<col width="8%">
                            	<col width="8%">
                            	<col width="20%">
                            	<col width="18%">
                            	<col width="20%">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">메세지번호</th>
                                    <th scope="col">채팅방번호</th>
                                    <th scope="col">발신자</th>
                                    <th scope="col">사진여부</th>
                                    <th scope="col">내용</th>
                                    <th scope="col">생성일자</th>
                                </tr>
                            </thead>
                            <tbody id="tbody"></tbody>
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