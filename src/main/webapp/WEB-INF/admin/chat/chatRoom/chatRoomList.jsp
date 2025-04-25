<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>
	<script src="<%=request.getContextPath() %>/js/admin/chat/chatRoom.js" defer></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/chat/chatRoom.css">
    
    <div id="wrapper">

        <div id="container">

            <h1 id="container_title">상품관리 > 채팅방관리</h1>
            <div class="container_wr">
            
                <section class="dp_f gap60 fd_c">
                    <div class="tbl_head01">
		                    
						<div class="local_desc local_desc03 ai_fe">
                    		<h3>채팅방 관리</h3>
	                    	<form action="" class="search_form mt0">
				              <select name="searchChatRoom" id="chatRoomsearch">
				                <option value="chat_room_no">채팅방번호</option>
				                <option value="chat_name">채팅방명</option>
				              </select>
				              <div>
				                <input type="text" id="searchChatRoomText" placeholder="검색어를 입력하세요.">
				                <span class="material-symbols-outlined" id="searchChatRoomBtn">search</span>
				              </div>
				            </form>
						</div>
                    	<div class="over_scroll_cont over_scroll_cont2">
	                        <table>
	                            <caption class="dp_n">채팅방</caption>
	                            <colgroup>
	                            	<col width="15%">
	                            	<col width="55%">
	                            	<col width="30%">
	                            </colgroup>
	                            <thead>
	                                <tr>
	                                    <th>채팅방 번호</th>
	                                    <th>채팅방명</th>
	                                    <th>생성일자</th>
	                                </tr>
	                            </thead>
	                            <tbody id="chatRoom"></tbody>
	                        </table>
	                    </div>
                    </div>
                    
                    <div class="tbl_head01 tbl_wrap">
						<div class="local_desc local_desc03 ai_fe">
                    		<h3>채팅멤버 관리</h3>
	                    	<form action="" class="search_form mt0">
				              <select name="searchChatMem" id="chatMemSearch">
				                <option value="mem_no">회원번호</option>
				                <option value="mem_name">이름</option>
				                <option value="mem_alias">별명</option>
				              </select>
				              <div>
				                <input type="text" id="searchChatMemText" placeholder="검색어를 입력하세요.">
				                <span class="material-symbols-outlined" id="searchChatMemBtn">search</span>
				              </div>
				            </form>
                    	</div>
                        <table>
                            <caption class="dp_n">채팅멤버</caption>
                            <colgroup>
                            	<col width="10%">
                            	<col width="10%">
                            	<col width="10%">
                            	<col width="20%">
                            	<col width="10%">
                            	<col width="20%">
                            </colgroup>
                            <thead>
                                <tr>
                                    <!-- <th>번호</th> -->
                                    <th>회원번호</th>
                                    <th>아름</th>
                                    <th>별명</th>
                                    <th>연락처</th>
                                    <!-- <th>메세지번호</th> -->
                                    <th>상태</th>
                                    <th>가입일</th>
                                </tr>
                            </thead>
                            <tbody id="chatMem" data-ck="0">
                                <tr>
                                    <td colspan="6">채팅방 선택 시 표시됩니다.</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </section>
                
            </div>
        </div>
    </div>
    
</html>