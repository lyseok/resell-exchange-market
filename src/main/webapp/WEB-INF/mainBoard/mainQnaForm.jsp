<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<%@ page isELIgnored="false" %>
<script defer src="<%=request.getContextPath() %>/js/mainBoard/mainBoardQnaList.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainBoard/mainBoard.css">
<!-- 
		Header	   (타이틀 위치.)
		--검색창 불필요.
		Form	   (필요 입력사항div를 flex-column으로 정렬)
		Footer     (페이지네이션 필요없어서 display:none; 처리)
		
		##스크립트에서 ajax 비동기로 입력 후, 결과의 성공/실패 여부를 응답데이터로 받음
		└ success: 만약 데이터가 '성공'이면, 동기 방식으로 문의게시판 이동.
		└ success: 만약 '실패'이면 alert()로 return.
		└ error: 일어나면 안 되는 케이스.
-->
<!-- ■■■■■■■■■■■■■■■■■■■■ -->
<div class="inner kcy_board">
	<div class="kcy_boardHeader">
		<br><br>
		<span class="bHeader_normal">1:1 문의하기</span>
	</div>
	<hr style="color:#cecece;">
	
	<div class="kcy_boardForm">
		<div class="formType">
			<div class="boardForm_column">
				<span class="boardForm_colText">유형 선택</span>
			</div>
			<div class="boardForm_value">
				<select id="qnaType">
					<option style="display:none;">^0^</option>
					<option>상품문의</option>
					<option>1:1문의</option>
				</select>
			</div>
		</div>
		<div class="formTitle">
			<div class="boardForm_column">
				<span class="boardForm_colText" id="kcy_fuckingWhat">제목</span>
			</div>
			<div class="boardForm_value">
				<input type="text" placeholder="제목을 입력하세요." id="qnaTitle"/>
			</div>
		</div>
		<div class="formTextarea">
			<div class="boardForm_column" style="height:240px;align-items:flex-start;padding-top:20px">
				<span class="boardForm_colText">내용</span>
			</div>
			<div class="boardForm_value" style="height:240px;align-items:flex-start;padding-top:10px">
				<textarea placeholder="내용을 자세히 입력하세요. (20자 이상)" id="qnaCont"></textarea>
			</div>
		</div>
		<div class="formAttach" style="margin-top:10px;">
			<div class="boardForm_column">
				<span class="boardForm_colText">사진 첨부</span>
			</div>
			<div class="boardForm_value">
				<input type="file" style="height:43px;"id="qnaImg"/>
			</div>
		</div>
	</div>
	<hr style="color:#cecece;"><br>
	<div class="kcy_viewFooter">
		<div class="null">
		</div>
		<div class="buttons" style="display:hidden;">
		</div>
		<div class="GeulSsooGi">
			<input type="button" value="1:1문의 등록" id="btnGeulSsooGi"> 
		</div>
	</div>
</div>
<!-- ■■■■■■■■■■■■■■■■■■■■ -->


<script>
const board = "<%=request.getAttribute("board")%>";
const urlContextPath = "<%=request.getContextPath()%>";

articleList = <%=new Gson().toJson(request.getAttribute("searchedList")) %>;
console.log(articleList);
articleSearchText = "<%=request.getAttribute("searchText") %>";
console.log(articleSearchText);
urlView = `<%=request.getContextPath()%>/main/${board}/view.do?${board}No=`;
$("#searchingWord").attr("placeholder", articleSearchText);

/* 1)_ 검색기능 */
$("#searchingBtn").on("click", function(){
	const search_text = $('#searchingWord').val();
	if (search_text == "") {
		$("#searchingWord").attr("placeholder", "검색어를 입력하세요..!");
		return false;
	} else {
		location.href = `<%=request.getContextPath()%>/main/${board}/search.do?sch=\${search_text}`;
	}
});

/* 2)_ 등록버튼 클릭 시 INSERT 후 QNA.DO로 이동 */
$("#btnGeulSsooGi").on("click", function(){
	let qnaType = $("#qnaType").val()
		if(qnaType=="상품문의") qnaType = 0;
		else if(qnaType="1:1문의") qnaType = 1;
		else {alert("문의 분류를 선택해주세요."); return;}
	let qnaTitle = $("#qnaTitle").val()
	let qnaCont = $("#qnaCont").val().replaceAll(/<br>/g, "\\n")
		if(qnaTitle== null || qnaTitle== "" ||
		   qnaCont == null || qnaCont == ""){
			alert("필수 항목을 모두 기입해주세요.");
			return;
		}
	let checkNewImg = "OK";
	const fileInput = document.getElementById("qnaImg");
    const file = fileInput.files[0];
    if (!file) {
        checkNewImg = "NO";
    }
    let memNo = <%=loginInfo.getMem_no()%>;
    
    const formData = new FormData();
    formData.append("qnaType", qnaType);
    formData.append("qnaTitle", qnaTitle);
    formData.append("qnaCont", qnaCont);
    formData.append("checkNewImg", checkNewImg);
    formData.append("qnaImg", file);
    
    $.ajax({
		url: "<%=request.getContextPath()%>/main/qna/submit.do",
		type: "POST",
		data: formData,
		dataType: "json",
		contentType: false,
		processData: false,
		success: function(data){
			console.log("success!");
			console.log("data : ",data);
			
			if(data.result=="success"){
				location.href= '<%=request.getContextPath()%>/main/qna.do';//주소를 재요청
			}else{//실패
				alert("에러 발생^0^: 입력사항이 저장되지 않았습니다. 관리자에게 문의해주세요.");
			}
		},
		error: function(xhr){
			console.log("■ AJAX ERROR ==> ", xhr.status);
		}
    });
});

$("#qnaType").on("change", function(){
	$("#kcy_fuckingWhat").text("^0^");
});
$("#kcy_fuckingWhat").on("click", function(){

	let tempQnaCont = `이게 무슨 일이랍니까.

제가 상품을 원하는데도 직거래가 아니면 거래하지 않겠다네요.
이게 말이나 되는 일입니까?
나이도 젊어보이는 것들이 기고만장해져선 떼잉 쯨...

관리자님이 어떻게 좀 해보셔야지 않갔슴니까`;
	let tempQnaTitle = `살다보니 이런 일도 다 겪네요 ㅡㅡ`;
	$("#qnaTitle").val(tempQnaTitle);
	$("#qnaCont").text(tempQnaCont);
});
</script>

<%@include file="/WEB-INF/include/footer.jsp" %>