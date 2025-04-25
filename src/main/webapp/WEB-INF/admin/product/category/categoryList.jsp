<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/admin/include/header_admin.jsp" %>	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/modal/modal.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/admin/product/category.css">
	<script>
		$(function(){
			// 대분류 가져오기
			const mainCateList=()=>{
				$.ajax({
					url: "<%=request.getContextPath()%>/admin/categoryPage.do",
					type: "post",
					success: res =>{
						console.log(res);
						code = "";
						$.each(res, function(i, v){
							code += 
									`<tr>
			                            <td>\${v.cate_main_id}</td>
			                            <td class="main_name" data-id="\${v.cate_main_id}">\${v.cate_main_name}</td>
			                            <td><span class="modify" data-name="\${v.cate_main_name}" data-id="\${v.cate_main_id}">수정</span></td>
			                        </tr>`;
						})
						$("#tBodyMain").html(code);
						
					},
					error: xhr => {
						alert("오류: " + xhr.status);
					},
					dataType: "json"
				})
			}
			// 중분류 가져오기 버튼 클릭
			$("#tBodyMain").on("click", ".main_name", function(){
				// 모든 td의 굵기 초기화 - 다른 대분류 클릭 시 css 초기화 하기 위해 설정
				$("tr td").css({"font-weight": "300", "color":"#333"}); 
				
				// 클릭한 대분류에 스타일 설정하기
				const clickBg = $(this).css({"font-weight":"bold", "color":"#ad49e1"})
				
				const mainId = $(this).data("id");
				
				subCateList(mainId);
			})
			
			// 중분류 가져오기
			const subCateList=(mainId)=>{
				$.ajax({
					url: "<%=request.getContextPath()%>/admin/subCategoryList.do",
					type: "post",
					data: {"mainId": mainId},
					success: res =>{
						console.log(res);
						code = "";
						if(res.length>0){
							$.each(res, function(i, v){
								code += 
								`<tr>
			                         <td>\${v.cate_sub_id}</td>
			                         <td class="sub_name" data-id="\${v.cate_sub_id}">\${v.cate_sub_name}</td>
			                         <td><span class="modify" data-name="\${v.cate_sub_name}" data-id="\${v.cate_sub_id}">수정</span></td>
			                     </tr>`;
							})
						} else{
							code += 
								`<tr>
		                            <td colspan="3">등록된 중분류가 없습니다.</td>
		                        </tr>`;							
						}
						$("#tBodySub").html(code);
					},
					error: xhr => {
						alert("오류: " + xhr.status);
					},
					dataType: "json"
				})
			}
			
			// 모달창
			// 대분류 추가 모달창 오픈
			$("#mainAddBtn").on("click", function(){
				$("#popup1").css('display','flex').hide().fadeIn();
			})
			// 모달창 닫기
			$(".close").on("click", function(){
				$("#popup1, #popup2, #popup3, #popup4").fadeOut();
			})
			
			
			
			// 대분류 추가 - 확인버튼 클릭시 MainCateInsert 호출
			$("#popup1 .confirm").on("click", function(){
				MainCateInsert();
			})
			// 대분류 추가하기
			const MainCateInsert = () => {
				value = $("#getValue").val();
				console.log(value);
				
				$.ajax({
					url: "<%=request.getContextPath()%>/admin/addMainCategory.do",
					type: "post",
					data: {
						"value" : value
					},
					success: res =>{
						console.log(res + "대분류 추가 console");
						code = "";

						code += 
							`<tr>
	                            <td>\${res.cate_main_id}</td>
	                            <td class="main_name" data-id="\${res.cate_main_id}">\${res.cate_main_name}</td>
	                            <td><a href="">수정</a></td>
	                        </tr>`;

	        			value = $("#getValue").val("");
						// 모달창 닫기
						$(".popup-wrap").fadeOut();
						

						mainCateList();
					},
					error: xhr => {
						alert("오류: " + xhr.status);
					},
					dataType: "json"
				})
			}
			
			
			
			// 중분류 추가 - 버튼 클릭 
			$("#subAddBtn").on("click", function(){
				$("#popup2").css('display','flex').hide().fadeIn();
			})
			
			// 중분류 추가하기 - insert (모달창 안에 confirm(확인) 버튼 클릭 시 실행)
			$("#popup2 .confirm").on("click", function(){
				const value = $("#getValue2").val();
				const mainId = $("#mainName").val();
				
				addSubCateModal(value, mainId);
			})
			
			const addSubCateModal = (value, mainId) => {
				$.ajax({
					url: "<%=request.getContextPath()%>/admin/addSubCategory.do",
					type: "post",
					data: {
						"value" : value,
						"mainId" : mainId
					},
					success: res =>{
						console.log("인서트 요청 성공 시 출력: " + res);

						
						
						// 모달창 닫기
						$(".popup-wrap").fadeOut();
						subCateList(mainId);
					},
					error: xhr => {
						alert("오류: " + xhr.status);
					},
					dataType: "json"
				})
			}
			// 대분류 수정 - 모달창 오픈
			$("#tBodyMain").on("click", ".modify", function(){
				const mainName = $(this).data("name");
				const mainId = $(this).data("id");
				// 모달창 오픈 시 직전에 입력했던 값이 있다면 공백으로 입력 값 초기화
				$("#getValue3").val("");
				$("#pop3MainName").val(mainName);
				$("#pop3MainName").attr("data-id", mainId);
				
				
				$("#popup3").css('display','flex').hide().fadeIn();
			})
			
			// 대분류 수정 - 모달창 입력 후 확인버튼 클릭 시 update 요청
			$("#popup3 .confirm").on("click", function(){
				const mainId = $("#pop3MainName").data("id");
				const mainName = $("#getValue3").val();
				
				
				$.ajax({
					url: "<%=request.getContextPath()%>/admin/updateMainCate.do",
 					type: "post",
					data: {
						"mainId" : mainId,
						"mainName" : mainName
					},
					success: res =>{
						console.log("업데이트 요청 성공 시 출력: " + res);
						
						
						// 코드 출력
						$(".main_name").filter(function() {
						    return $(this).data("id") == mainId; // mainId와 동일한 ID를 가진 요소 찾기
						}).text(mainName);

						
						
						// 모달창 닫기
						$(".popup-wrap").fadeOut();
					},
					error: xhr => {
						alert("오류: " + xhr.status);
					},
					dataType: "json"
				})
			})
			
			
			// 중분류 수정 - 모달창 오픈
			$("#tBodySub").on("click", ".modify", function(){
				const subId = $(this).data("id");
				const subName = $(this).data("name");
				
				// 모달창 오픈 시 직전에 입력했던 값이 있다면 공백으로 입력 값 초기화
				$("#getValue4").val("");
				
				// 현재 클릭한 요소의 text를 subName에 저장해뒀다가 초기화 후 여기서 text에 넣어줌
				$("#pop4SubName").val(subName);
				$("#pop4SubName").attr("data-id", subId);
				
				
				$("#popup4").css('display','flex').hide().fadeIn();
			})
			
			// 대분류 선택 후 중분류 수정시 -  모달창 세팅하기 
			$.ajax({
				url: "<%=request.getContextPath()%>/admin/subCategoryListModal.do",
				type: "post",
				success: res =>{
					console.log(res);
					code = "";
					$.each(res, function(i, v){
						code += `<option value="\${v.cate_main_id}">\${v.cate_main_name}</option>`;
					})
					$("#mainName").html(code);
				},
				error: xhr => {
					alert("오류: " + xhr.status);
				},
				dataType: "json"
			})
			
			// 중분류 수정 - 모달창 입력 후 확인버튼 클릭 시 update 요청
			$("#popup4 .confirm").on("click", function(){
				const subId = $("#pop4SubName").data("id");
				const subName = $("#getValue4").val()

				console.log(subId);
				console.log("변경할 텍스트가 찍히는 지 테스트: " + subName);
				console.log("변경할 subId 찍히는 지 테스트: " + subId);
				
				if(subName.length < 10){
					$.ajax({
						url: "<%=request.getContextPath()%>/admin/updateSubCate.do",
						type: "post",
						data: {
							"subId" : subId,
							"subName" : subName
						},
						success: res =>{
							console.log("업데이트 요청 성공 시 출력: " + res);
							
							
							// 코드 출력
							$(".sub_name").filter(function() {
							    return $(this).data("id") == subId; // subId와 동일한 ID를 가진 요소 찾기
							}).text(subName);
	
							// 모달창 닫기
							$(".popup-wrap").fadeOut();
						},
						error: xhr => {
							alert("오류: " + xhr.status);
						},
						dataType: "json"
					})
				} else{
					alert("카테고리 명은 10자 미만으로 입력해주세요.");
					
					// 다시 입력받기 위해  포커스
					$("#getValue4").focus();
				}
			})
			
			
			// 시작하자마자 실행
			mainCateList();
		})
	</script>
    <div id="wrapper">

        <div id="container">

            <h1 id="container_title">상품관리 > 카테고리</h1>
            <div class="container_wr">
            
                <section class="dp_f gap60">
                    <div class="tbl_head01 tbl_wrap">
                    	<div class="cate_tit_cont">
                    		<h3>대분류</h3>
                    		<span id="mainAddBtn">추가</span>
                    	</div>
                    	<div class="table_bd">
	                        <table>
	                            <caption class="dp_n">대분류</caption>
	                            <colgroup>
	                            	<col width="15%">
	                            	<col width="45%">
	                            	<col width="30%">
	                            </colgroup>
	                            <thead>
	                                <tr>
	                                    <th>코드</th>
	                                    <th>대분류명</th>
	                                    <th>관리</th>
	                                </tr>
	                            </thead>
	                            <tbody id="tBodyMain"></tbody>
	                        </table>
                        </div>
                    </div>
                    
                    <div class="tbl_head01 tbl_wrap">
                    	<div class="cate_tit_cont">
                    		<h3>중분류</h3>
                    		<span id="subAddBtn">추가</span>
                    	</div>
                    	<div class="table_bd">
	                        <table>
	                            <caption class="dp_n">중분류</caption>
	                            <colgroup>
	                            	<col width="15%">
	                            	<col width="45%">
	                            	<col width="30%">
	                            </colgroup>
	                            <thead>
	                                <tr>
	                                    <th>코드</th>
	                                    <th>중분류명</th>
	                                    <th>관리</th>
	                                </tr>
	                            </thead>
	                            <tbody id="tBodySub">
	                                <tr>
	                                    <td colspan="3">대분류 선택 시 표시됩니다.</td>
	                                </tr>
	                            </tbody>
	                        </table>
                        </div>
                    </div>
                </section>
                
            </div>
        </div>
    </div>
    
<div class="container">
  <div class="popup-wrap" id="popup1">
    <div class="popup">
      <div class="popup-head">
          <span class="head-title">대분류추가</span>
      </div>
      <div class="popup-body">
		<label>
			<span>대분류명</span>
			<input type="text" placeholder="이름 입력" id="getValue"/>
		</label>
      </div>
      <div class="popup-foot">
        <span class="pop-btn confirm">확인</span>
        <span class="pop-btn close" id="close">취소</span>
      </div>
    </div>
  </div>
</div>

<div class="container">
  <div class="popup-wrap" id="popup2">
    <div class="popup">
      <div class="popup-head">
          <span class="head-title">중분류추가</span>
      </div>
      <div class="popup-body">
		<label>
			<span>대분류</span>
			<select id="mainName"></select>
		</label>
		<label>
			<span>중분류명</span>
			<input type="text" placeholder="이름 입력" id="getValue2"/>
		</label>
      </div>
      <div class="popup-foot">
        <span class="pop-btn confirm">확인</span>
        <span class="pop-btn close" id="close">취소</span>
      </div>
    </div>
  </div>
</div>

<div class="container">
  <div class="popup-wrap" id="popup3">
    <div class="popup">
      <div class="popup-head">
          <span class="head-title">대분류 수정</span>
      </div>
      <div class="popup-body">
		<label>
			<span>선택 대분류명</span>
			<input type="text" placeholder="" id="pop3MainName" readonly disabled/>
		</label>
		<label>
			<span>변경 대분류명</span>
			<input type="text" placeholder="이름 입력" id="getValue3"/>
		</label>
      </div>
      <div class="popup-foot">
        <span class="pop-btn confirm">확인</span>
        <span class="pop-btn close" id="close">취소</span>
      </div>
    </div>
  </div>
</div>

<div class="container">
  <div class="popup-wrap" id="popup4">
    <div class="popup">
      <div class="popup-head">
          <span class="head-title">중분류 수정</span>
      </div>
      <div class="popup-body">
		<label>
			<span>선택 중분류명</span>
			<input type="text" placeholder="" id="pop4SubName" readonly disabled/>
		</label>
		<label>
			<span>변경 중분류명</span>
			<input type="text" placeholder="이름 입력" id="getValue4"/>
		</label>
      </div>
      <div class="popup-foot">
        <span class="pop-btn confirm">확인</span>
        <span class="pop-btn close" id="close">취소</span>
      </div>
    </div>
  </div>
</div>
</body>
</html>