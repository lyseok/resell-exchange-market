<%@page import="rem.chat.vo.ChatVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true"%>

<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<%@include file="/WEB-INF/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/chat/dditTalk.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.1.js"></script>

<% 
	System.out.println("loginSession :" + login);
	if(login == null) {
		response.sendRedirect(request.getContextPath() + "/accessCheck.do");
	}
	
	@SuppressWarnings("unchecked")
	List<ChatVO> userlist = (List<ChatVO>)request.getAttribute("userlist");
	@SuppressWarnings("unchecked")
	List<ChatVO> roomlist = (List<ChatVO>)request.getAttribute("roomlist");
	MemberVO mvo = (MemberVO)request.getAttribute("mvo");
	int room_no = (Integer)request.getAttribute("room_no");
	
	
%> 

<script>
$(function(){
	loginState = '<%=login%>';
	if(loginState){
		socket = new WebSocket("ws://192.168.34.84" + mypath + "/chatSocketbasic.do");

		socket.onopen = function() {
	    	console.log("WebSocket 연결됨");
		};
	}
	
	socket.onclose = (event) => {
		  console.log("WebSocket 연결 종료", event);
		  console.log("종료 코드:", event.code);
		  console.log("종료 이유:", event.reason);

		  // 연결 끊김 시 로깅
		  console.error("WebSocket 연결이 끊어졌습니다.");

		  // (선택 사항) 재연결 시도
		  setTimeout(() => {
		    console.log("재연결 시도...");
		    socket = new WebSocket("ws://192.168.34.84" + mypath + "/chatSocketbasic.do");
		    setupWebSocketHandlers(); // 재연결된 웹소켓에 이벤트 핸들러 다시 설정
		  }, 1000); 
		};
		
		function setupWebSocketHandlers() {
			  socket.onopen = () => {
			    console.log("WebSocket 연결 성공 (재연결)");
			    
			};
		}
		setupWebSocketHandlers();
	
	loginInfo = <%=loginInfo.getMem_no()%>
	let currentRoom;
	
	const activeRoom = $('.prof.active').find('.room_no');
	if(activeRoom.length > 0){
		currentRoom = activeRoom.text();
		$('#sendInput').val(currentRoom);
	}else{
		currentRoom = '';
	}
	
	$(document).on('click', '.prof', function(){
		
		$('.prof').removeClass('active'); 
		
		$(this).addClass('active');
		
		room_no = $(this).find('.room_no').text();
		console.log(room_no)
		$('#sendInput').val(room_no);
		currentRoom = room_no
		
		$.ajax({
			
			url :`${mypath}/chat/dditTalk.do`,
			type : 'post',
			data : {chat_room_no : room_no},
			dataType : 'json',
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success : res=>{
				code = ``;
				if(res===null){
					
				}else{
				$.each(res, function(i,v){
					if(loginInfo===v.mem_no){
						code += `<div class="myMessage">
									<span class="myContent">${v.msg_cont}</span>
								 </div>`;
					}else{
						code += `
						<div class="user">
							<div class="userprof">
								<img src="${v.file_path}" alt="prfileImg" id="chprofile">
								<span class="chalias">${v.mem_alias}</div>
							</div>
							<div class="userMessage">
								<span class="userContent">${v.msg_cont}</span>
							</div>
						</div>`;
					}
				})
				}
				$('.chbox').html(code);
				$('.chbox').scrollTop($('.chbox')[0].scrollHeight);
			},
			error : xhr =>{
				alert(xhr.status)
			}
		})
	})
	
	$('#msg').on('keydown', function(e){
		if(e.keyCode == 13){
			sendMsg();
		}
	})
	
	$('#sendMessage').on('click', function(){
		sendMsg();
	})
	const sendMsg = () => {
		console.log($("#sendInput").val());
		console.log($("#msg").val());
		
		chat_room_no = $("#sendInput").val();
		msg_cont = $("#msg").val();
		
		message = {
				chat_room_no : chat_room_no,
				msg_cont : msg_cont
		}
		
		socket.send(JSON.stringify(message));
		$('#msg').val('');
	}
	socket.onmessage = function (event){
		setMessage = event.data;
		
		try {
			jsonData = $.parseJSON(setMessage);
			from = jsonData.from;
			chat_room_no = jsonData.chat_room_no;
			msg_cont = jsonData.msg_cont;
			file_path = jsonData.file_path;
			mem_alias = jsonData.mem_alias;
			
			console.log(from)
			console.log(chat_room_no)
			console.log(msg_cont)
			console.log(file_path)
			
			if(chat_room_no == currentRoom){
				let sMessage = '';
				if(from == loginInfo){
					sMessage =  `<div class="myMessage">
									<span class="myContent">${msg_cont}</span>
								 </div>`;
				}else {
					sMessage = `
						<div class="user">
							<div class="userprof">
								<img src="${file_path}" alt="prfileImg" id="chprofile">
								<span class="chalias">${mem_alias}</div>
							</div>
							<div class="userMessage">
								<span class="userContent">${msg_cont}</span>
							</div>
						</div>`;
				}
				$('.chbox').append(sMessage);
				$('.chbox').scrollTop($('.chbox')[0].scrollHeight);
			}
			
			
		} catch (error) {
			console.error("받은 데이터 : ", setMessage);
		}
	}
	
})

</script>

	<%@include file="/WEB-INF/include/category.jsp" %>
<div class="wrap">
	<div class="inner outer">
		<div class="ccontainer">
	 		<div class="box">
				<div class="citem">
					<span>띹톡</span>
				</div>
			</div>
	 		<div class="rbox">
				<div class="chatroom">
					<%if(mvo.getMem_no()!=0) {%>
						<div class="prof active">
									<div class="room_no"><%=room_no %></div>
									<div class="room">
										<span class="mem_no"><%=mvo.getMem_no() %></span>
										<span class="mem_alias">
											<%=mvo.getMem_alias() %>
										</span>
										<span class="msg_cont">
											
										</span>
									</div>
									<div class="gostore">
										<a href="<%= request.getContextPath() %>/store/storePage.do?param=mem_no&value=<%=mvo.getMem_no() %>">
											<button class="storebtn" id="storebtn">상점가기</button>
										</a>
									</div>
								</div>
					<%}else{} %>
					<%for(ChatVO cvo : userlist) { %>
							<%if(cvo.getMem_no()==loginInfo.getMem_no()) {%>
							<div class="prof">
								<div class="room_no"><%=cvo.getChat_room_no() %></div>
								<div class="room">
									<span class="mem_no"><%=cvo.getChat_user() %></span>
									<span class="mem_alias">
										<%=cvo.getMem_alias() %>
									</span>
									<span class="msg_cont">
										<%=cvo.getMsg_cont() %>
									</span>
								</div>
								<div class="gostore">
									<a href="<%= request.getContextPath() %>/store/storePage.do?param=mem_no&value=<%=cvo.getChat_user() %>">
										<button class="storebtn" id="storebtn">상점가기</button>
									</a>
								</div>
							</div>
							<%}else{} %>
					<%} %>
					<%for(ChatVO cvo : roomlist) {%>
					<div class="prof">
						<div class="room_no"><%=cvo.getChat_room_no() %></div>
						<div class="room">
							<span class="mem_no"><%=cvo.getMem_no() %></span>
							<span class="mem_alias">
								<%=cvo.getMem_alias() %>
							</span>
							<span class="msg_cont">
								<%=cvo.getMsg_cont() %>
							</span>
						</div>
						<div class="gostore">
							<a href="<%= request.getContextPath() %>/store/storePage.do?param=mem_no&value=<%=cvo.getMem_no() %>">
								<button class="storebtn" id="storebtn">상점가기</button>
							</a>
						</div>
					</div>
					<%} %>
				
				</div>
			</div>
		</div>
		<div class="ccontainer">
			<div class="chbox">
				
			</div>
			<div class="bbox">
				<div class="aitem">
					<input type="text" id="msg" placeholder="메시지를 입력하세요">
				</div>
				<div class="bitem">
					<button id="sendMessage" >
						<span class="material-symbols-outlined send">send</span>
						<input type="hidden" id="sendInput"/>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>