/**
 * 
 */

// 채팅방 관리 라스트 출력
$.ajax({
	url:`${adminPath}/admin/chatRoomList.do`,
	type: "post",
	success: res => {
		code = "";
		$.each(res, function(i, v){
			code += 
				`
				<tr>
					<td>${v.chat_room_no}</td>
					<td class="board_name" data-room="${v.chat_room_no}">${v.chat_name}</td>
					<td>${v.chat_create_at}</td>
				</tr>
				`;
		})
		$("#chatRoom").html(code);
	},
	error: xhr => {
		alert("오류: " + xhr.status);
	},
	dataType:"json"
})


// 채팅방 명 클릭 시 동작 
$(document).on("click", ".board_name", function(){
	const roomNo = $(this).data("room");
	const chatMemCkChg = $("#chatMem").attr("data-ck", 1);
	chatMemList(roomNo);
})

// 채팅방 관리 리스트 출력
const chatMemList = (roomNo) => {
	console.log(roomNo);
	
	$.ajax({
		url: `${adminPath}/admin/chatMemberList.do`,
		type: "post",
		data: {
			"roomNo" : roomNo
		},
		success: res =>{
			console.log(res + " 채팅멤버리스트 테스트");
			code = "";

			$.each(res, function(i, v){
				code += 
					`
					<tr>
						<td>${v.mem_no}</td>
						<td>${v.mem_name}</td>
						<td>${v.mem_alias}</td>
						<td>
						`
							if(v.mem_tel === undefined) {
								code += "미인증회원";
							} else{
								code += v.mem_tel;	
							} 
				code += `</td>
						<td>
					`
							if(v.mem_status == 0) {
								code += "일반회원";
							} if(v.mem_status == 1){
								code += "탈퇴회원";
							} 

				code += `</td>
						<td>${v.mem_join_at}</td>
					</tr>
					`;
			})

			
			$("#chatMem").html(code);
		},
		error: xhr => {
			alert("오류: " + xhr.status);
		},
		dataType: "json"
		
	})
}


$("#searchChatRoomBtn").on("click", function(){
	chatRoomSearch();
})

const chatRoomSearch = () => {
//	const select = $("#chatRoomsearch").val();
	const value = $("#searchChatRoomText").val();
	console.log(select, value);
	if( select=='chat_room_no' && isNaN(value)){
		alert("숫자로 입력해주세요");
		return;
	}
	$.ajax({
		url: `${adminPath}/admin/chatRoomSearch.do`,
		type: "post",
		data: {
			"select" : select,
			"value" : value
		},
		success: res => {
			code = "";
			if(res.length > 0){
				$.each(res, function(i, v){
					console.log(res)
					code += 
							`
							<tr>
								<td>${v.chat_room_no}</td>
								<td class="caht_name">${v.chat_name}</td>
								<td>${v.chat_user}</td>
								<td>${v.chat_create_at}</td>
							</tr>
							`;
				})
			} else{
				code += 
					`
					<tr>
						<td colspan="4">해당 채팅방이 존재하지 않습니다.</td>
					</tr>
					`;
			}
			$("#chatRoom").html(code);
		},
		error: xhr => {
			alert("오류: " + xhr.status);
		},
		dataType: "json"
	})
}

$("#searchChatMemBtn").on("click", function(){
	const chatMemCk = $("#chatMem").data("ck");
	
	if(chatMemCk==0){
		alert("채팅방 선택 후 검색해주세요.");	
		return chatMemCk;
	} else if(chatMemCk==1){
		chatMemSearch();
	}
})


const chatMemSearch = () => {
	const select = $("#chatMemSearch").val();
	const value = $("#searchChatMemText").val();
	
	if(select=='mem_no' && isNaN(value)){
		alert("숫자를 입력해주세요");
		return;
	} 
		
	$.ajax({
		url: `${adminPath}/admin/chatMemberSearch.do`,
		type: "post",
		data: {
			"select" : select,
			"value" : value
		},
		success: res => {
			code = "";
			if(res.length > 0){
				$.each(res, function(i, v){
					code += 
						`
						<tr>
							<td>${v.chat_user}</td>
							<td> ${v.mem_no}</td>
							<td>${v.mem_name}</td>
							<td>${v.mem_alias}</td>
							<td>
						`
							if(v.mem_tel === undefined) {
								code += "미인증회원";
							} else{
								code += v.mem_tel;	
							} 
					code += `
							</td>
							<td> ${v.msg_no}</td>
							<td>
						`
							if(v.mem_status == 0) {
								code += "일반회원";
							} if(v.mem_status == 1){
								code += "탈퇴회원";
							} 


					code += `</td>
							<td>${v.mem_join_at}</td>
						</tr>
						`;
				})
			} else{
				code += 
					`
					<tr>
						<td colspan="8">해당 채팅방이 존재하지 않습니다.</td>
					</tr>
					`;
			}
			$("#chatMem").html(code);
		},
		error: xhr => {
			alert("오류: " + xhr.status);
		},
		dataType: "json"
	})
}
