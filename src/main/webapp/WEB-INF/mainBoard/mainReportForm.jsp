<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<%@ page isELIgnored="false" %>
<script defer src="<%=request.getContextPath() %>/js/mainBoard/mainBoardReportList.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainBoard/report.css">


<div class="inner kcy_board">
	<div class="kcy_boardHeader">
		<br><br>
		<span class="bHeader_normal">신고 작성하기</span>
	</div>
	<hr style="color:#cecece;">
	
	<div class="kcy_boardForm">
		<div class="formType">
			<div class="boardForm_column">
				<span class="boardForm_colText">유형 선택</span>
			</div>
			<div class="boardForm_value">
				<select id="reportType">
					<option style="display:none;">상품</option>
					<option>회원</option>
				</select>
			</div>
		</div>
		
		
		<div class="formTitle">
			<div class="boardForm_column">
				<span class="boardForm_colText">번호</span>
			</div>
				<div class="boardForm_value">
				<input type="text" value = "${reportNo}" id="reportProdNo"/>
			</div>
		</div>
		
		<div class="formTitle">
			<div class="boardForm_column">
				<span class="boardForm_colText">제목</span>
			</div>
			<div class="boardForm_value">
				<input type="text" placeholder="제목을 입력하세요." id="reportTitle"/>
			</div>
		</div>
		<div class="formTextarea">
			<div class="boardForm_column" style="height:240px;align-items:flex-start;padding-top:20px">
				<span class="boardForm_colText">내용</span>
			</div>
			<div class="boardForm_value" style="height:240px;align-items:flex-start;padding-top:10px">
				<textarea placeholder="내용을 자세히 입력하세요. (20자 이상)" id="reportCont"></textarea>
			</div>
		</div>
		<div class="formAttach" style="margin-top:10px;">
			<div class="boardForm_column">
				<span class="boardForm_colText">사진 첨부</span>
			</div>
			<div class="boardForm_value">
				<input type="file" style="height:43px;"id="reportImg"/>
			</div>
		</div>
	</div>
	<hr style="color:#cecece;"><br>
	<div class="kcy_viewFooter">
		<div class="null">
		</div>
		<div class="buttons" style="display:hidden;">
		</div>
		
		<div class="kcy_boardBackToList">
			<input type="button" value="목록" id="listButton2" />
		</div>
		<div class="GeulSsooGi">
			<input type="button" value="신고하기" id="btnGeulSsooGi"> 
		</div>
	</div>
</div>


<script>
const board = "<%=request.getAttribute("board")%>";
const urlContextPath = "<%=request.getContextPath()%>";

articleList = <%=new Gson().toJson(request.getAttribute("searchedList")) %>;
console.log(articleList);
articleSearchText = "<%=request.getAttribute("searchText") %>";
console.log(articleSearchText);
$("#searchingWord").attr("placeholder", articleSearchText);

/* 1)_ 검색기능 */
$("#searchingBtn").on("click", function(){
	const search_text = $('#searchingWord').val();
	if (search_text == "") {
		$("#searchingWord").attr("placeholder", "검색어를 입력하세요..!");
		return false;
	} else {
		location.href = `<%=request.getContextPath()%>/main/report/search.do?sch=\${search_text}`;
	}
});

$("#btnGeulSsooGi").on("click", function(){
	let reportType = $("#reportType").val()
		if(reportType=="상품") {
			reportType = 0;
		}
		else if(reportType=="회원") {
			reportType = 1;
		}
		else {alert("신고 분류를 선택해주세요")
			return;
		}
	let reportProdNo = $("#reportProdNo").val();
	let reportTitle = $("#reportTitle").val()
	let reportCont = $("#reportCont").val().replaceAll(/<br>/g, "\\n")
		if(reportTitle== null || reportTitle== "" ||
		   reportCont == null || reportCont == ""){
			alert("필수 항목을 모두 기입해주세요.");
			return;
		}
	let checkNewImg = "OK";
	const fileInput = document.getElementById("reportImg");
    const file = fileInput.files[0];
    if (!file) {
        checkNewImg = "NO";
    }
    let memNo = <%=loginInfo.getMem_no()%>;
    
    const formData = new FormData();
    formData.append("reportType", reportType);
    formData.append("reportProdNo", reportProdNo);
    formData.append("reportTitle", reportTitle);
    formData.append("reportCont", reportCont);
    formData.append("checkNewImg", checkNewImg);
  
    
    if (file) {
    	formData.append("reportImg", file);
    } else {
    	formData.append("reportImg", new Blob());  // 빈 Blob 객체라도 전달
    }
    
    $.ajax({
		url: `<%=request.getContextPath()%>/main/report/submit.do`,
		type: "POST",
		data: formData,
		dataType: "json",
		contentType: false,
		processData: false,
		success: function(data){
			console.log("success!");
			console.log("data : ",data);
			
			if(data.result=="success"){
				location.href= '<%=request.getContextPath()%>/main/report.do';//주소를 재요청
			}else{//실패
				alert("에러: 입력사항이 저장되지 않았습니다. 관리자에게 문의해주세요.");
			}
		},
		error: function(xhr){
			console.log("AJAX ERROR ==> ", xhr.status);
		}
    });
});


$("#listButton2").on("click", function(){
	location.href = '<%=request.getContextPath()%>/main/report.do';
});
</script>

<%@include file="/WEB-INF/include/footer.jsp" %>