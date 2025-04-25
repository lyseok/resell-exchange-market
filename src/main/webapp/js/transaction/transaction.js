/**
 * 
 */
function isThereReviewBuy() {
	let reviewInsertBtnList = $(".insertReview-btn");
	let transactions = [];
	$.each(reviewInsertBtnList, function(){
		txnNo = $(this).data("txnno");
		console.log("■■■■■■■■■■■■■ isThereReview function 실행 중...", txnNo);
		transactions.push(txnNo);
	});
	console.log("■■■■■■■■■■■■■ isThereReview function 실행 중...", transactions);
	$.ajax({
		url: `${mypath}/isThereReview.do`,
		type: "Post",
		data: {list: transactions},
		traditional: true,
		success:function(res){
				$.each(transactions, function(i,no){
					console.log(res);
					console.log(res[no]);
					if(res[no]	 == 1){
						kcy_btn = $(`.insertReview-btn[data-txnno="${no}"]`);
						kcy_btn.removeClass("insertReview-btn");
						kcy_btn.addClass("trackReview-btn");
						kcy_btn.css("color", "#ccc");
						console.log("결과는 어떠할까요 ^0^", kcy_btn);
					}
				});
				
		},
		errors:function(xhr){
			console.log("isThereReview함수 에러발생...", xhr.status);
		}
		
	});
}

function isThereReviewSell() {
	let reviewTrackBtnList = $(".trackReview-btn");
	let transactions = [];
	$.each(reviewTrackBtnList, function(){
		txnNo = $(this).data("txnno");
		console.log("■■■■■■■■■■■■■ isThereReview function 실행 중...", txnNo);
		transactions.push(txnNo);
	});
	console.log("■■■■■■■■■■■■■ isThereReview function 실행 중...", transactions);
	$.ajax({
		url: `${mypath}/isThereReview.do`,
		type: "Post",
		data: {list: transactions},
		traditional: true,
		success:function(res){
				console.log(res);
				$.each(transactions, function(i,no){
					console.log("gkgkgkgkadskfkajieojrioe",no);
					if(res[no]==0){
						kcy_btn = $(`.trackReview-btn[data-txnno="${no}"]`);
						kcy_btn.removeClass("trackReview-btn");
						kcy_btn.css("disabled", "disabled");
						kcy_btn.css("color", "#ccc");

						console.log("결과는 어떠할까요 ^0^", kcy_btn);
					}
				});
		},
		errors:function(xhr){
			console.log("isThereReview함수 에러발생...", xhr.status);
		}
		
	});
}
//테이블 생성 부분
function loadTransactionData(category, status) {    //동적 이미 만들어진거에 적용
	$.ajax({
		url: `${mypath}/getTransactionData.do`,
		data: {
			"category": category,
			"status": status
		},
		contentType: 'application/json;charset=UTF-8',
		success: res => {
			console.log(res);
			updateTable(res, category);
			if(category=="구매관리"){ isThereReviewBuy(); }
			else if(category=="판매관리"){ isThereReviewSell(); }
		},
		dataType: 'json'
	});
	
	
}



//테이블 생성 부분
function loadTransactionProd(category, status) {    //동적 이미 만들어진거에 적용
	$.ajax({
		url: `${mypath}/getTransactionProd.do`,
		data: {
			"category": category,
			"status": status
		},
		contentType: 'application/json;charset=UTF-8',
		success: res => {
			console.log(res);
			updateTable(res, category);
		},
		dataType: 'json'
	});
	
}











//테이블 업데이트
function updateTable(data, category) {
	console.log("■■■■■ updateTable 함수 실행....");
	let tableCode = "<table>";

	if (category == '상품관리') {

		tableCode += `	
				           <thead>
						   		<tr>
				                   <th class="img_col">사진</th>
				                   <th class="type_col">판매상태</th>
				                   <th class="name_col">상품명</th>
				                   <th class="price_col">가격</th>
				                   <th class="newing_col">끌올시간</th>
				                   <th class="modalBtn_col">기능</th>
				               </tr>
							   
				           </thead>
				           <tbody>
						   `;

	}

	else if (category == '구매관리') {

		tableCode += `
			            <thead>
			                <tr>
			                    <th class="img_col">사진</th>
			                    <th>거래유형</th>
			                    <th class="type_col">상태</th>
			                    <th class="name_col">상품명</th>
			                    <th class="price_col">가격</th>
			                    <th class="txnDate_col">구입일</th>
			                    <th class="modalBtn_col">기능</th>
			                </tr>
			            </thead>
			            <tbody>`;

	}

	else if (category == '판매관리') {

		tableCode += `
			            <thead>
			                <tr>
			                    <th class="img_col">사진</th>
			                    <th>거래유형</th>
			                    <th class="type_col">상태</th>
			                    <th class="name_col">상품명</th>
			                    <th class="price_col">가격</th>
			                    <th class="txnDate_col">판매일</th>
			                    <th class="modalBtn_col">기능</th>
			                </tr>
			            </thead>
			            <tbody>`;

	}



	$.each(data, function(i, v) {



		if (category == '상품관리') {

			tableCode += `
			<tr>
				<td>
					<img src="${v.file_path}" alt ="prod_img" class= "pimg">
				</td>
				<td>	
					<select class = "prod-status" data-prodno= "${v.prod_no}">
						<option value ="판매중" ${v.prod_tr_status == 0 ? 'selected' : ''}>판매중</option>
						<option value ="판매완료" ${v.prod_tr_status == 1 ? 'selected' : ''}>판매완료</option>
						<option value ="예약중" ${v.prod_tr_status == 2 ? 'selected' : ''}>예약중</option>
					</select>
				</td>
				<td>${v.prod_name}</td>
				<td>${v.prod_price.toLocaleString()}</td>
				<td>${v.prod_newing}</td>
				<td>
					<div class="modal-btn-box">
					  <input type = "button" class ="p-btn" id = "up-btn"  data-prodno= "${v.prod_no}"  value = "UP" id="modal-open">
					  <input type = "button" class ="p-btn" id = "edit-btn"  value = "수정">
					  <input type = "button" class ="p-btn" id = "delete-btn" data-prodno="${v.prod_no}"  value = "삭제">
					</div>
				</td>
			</tr>`;
		} 		else if (category === '구매관리' || category === '판매관리') {


		    // 거래 상태 설정
		    let txnStatus = '';
		    switch (v.txn_status) {
		        case 0: txnStatus = '거래중'; break;
		        case 1: txnStatus = '배송중';  // 기본값 설정
					// 직거래일 때만 변경 
			        if (v.prod_tr_approch === 0) txnStatus = '거래중';break;
		        case 2:txnStatus = '거래완료'; break;
				
		    }

		    // 거래 방식 설정
		    let prodApproch = '';
		    switch (v.prod_tr_approch) {
		        case 0:prodApproch = '직거래';break;
		        case 1:prodApproch = '택배거래(선불)';break;
		        case 2:prodApproch = '택배거래(착불)';break;
		    }
			const productInfo = `
			    <tr>
			        <td><img src="${v.file_path}" alt="prod_img" class="pimg"></td>
			        <td>${prodApproch}</td>
			        <td>${txnStatus}</td>
			        <td>${v.prod_name}</td>
			        <td>${v.prod_price.toLocaleString()}</td>
			        <td>${v.txn_create_at}</td>
			        <td id="kcy_lol">`;


		    if (category === '구매관리') {
		        tableCode += productInfo;

		        if (v.txn_status < 2) {
		            if (v.prod_tr_approch === 0) {
		                tableCode += `<input type="button" class="m-btn confirmation-btn" data-txnno="${v.txn_no}" value="구매확정">`;
		            } else {
		                tableCode += `
		                    <input type="button" class="m-btn confirmation-btn" data-txnno="${v.txn_no}" value="구매확정"><br>
		                    <input type="button" class="m-btn trackInfoView-btn" data-txnno="${v.txn_no}" value="배송조회">`;
		            }
		        } else if (v.txn_status === 2) {
					
		          		tableCode += `<input type="button" class="m-btn insertReview-btn" data-txnno="${v.txn_no}" value="리뷰작성">`;
						//<br><input type="button" class="m-btn trackInfoView-btn" data-txnno="${v.txn_no}" value="배송조회">`;
		        } else {
		            tableCode += `구매완료`;
		        }

		        tableCode += `</td></tr>`;
				
		    } else if (category === '판매관리') {
		        tableCode += productInfo;

		        if (v.prod_tr_approch === 0) {
		            tableCode += `<input type="button" class="m-btn trackReview-btn" data-txnno="${v.txn_no}" value="리뷰 확인">`;
		        } else if (v.txn_status === 0) {
		            tableCode += `<input type="button" class="m-btn trackInfoInsert-btn" data-txnno="${v.txn_no}" value="운송정보 입력">`;
		        } else{
		            tableCode += `<input type="button" class="m-btn trackReview-btn" data-txnno="${v.txn_no}" value="리뷰 확인">`;
		        }

		        tableCode += `</td></tr>`;
		    }
			
			
		}
	});



	tableCode += "</tbody></table>";
	$('#ajaxview').html(tableCode);

	
}






// ---------------------------------------------------------------------------------
//상품 끌올(등록시간 업데이트)
$(document).on('click', '#up-btn', function() {   //이후에 만들어진 버튼
	const prodNo = $(this).data("prodno");
	
	updateTimeCode = 	`
					<div class="container">
					  <div class="popup-wrap" id="updateTimeModal">
					    <div class="popup">
					      <div class="popup-head">
					          <span class="head-title">
					            상품 끌어올리기
					          </span>
					      </div>
					      <div class="popup-body">
					        <p>
					          상품을 끌어올리시겠습니까?
					        </p>
					      </div>
					      <div class="popup-foot">
					        <span class="pop-btn confirm" id="updatebtn" data-prodno = "${prodNo}">확인</span>
					        <span class="pop-btn close" id="close">취소</span>
					      </div>
					    </div>
					  </div>
					</div>`;
					
	//$("#modal-container").html(updateTimeCode);
	//$("#updateTimeModal").css('display','flex').hide().fadeIn();
	modalEvent(updateTimeCode);

});


$(document).on('click', '#updatebtn', function(){
	const prodNo = $(this).data("prodno");
		//상품 등록시간 업데이트
		
			$.ajax({
				url: `${mypath}/upProduct.do`,
				data: { prodNo: prodNo },
				success: res => {
					console.log(res);
					
					//$(".popup-wrap").fadeOut();
					loadTransactionData('상품관리', 4);
				},
				error: function(xhr) {
					console.log(xhr);
					console.log('up처리오류');
				}

			});
	})



//---------------------------------------------------------------------------------



//상품 삭제 버튼
$(document).on('click', '#delete-btn', function() {
	const prodNo = $(this).data("prodno");
	deleteProdCode = `	
	
					<div class="container">
			 			<div class = "popup-wrap" id = "deleteProdModal">
							<div class ="popup">
								<div class="popup-head">
									<span class = "head-title">
										상품 삭제
									</span>
							</div>
							<div class= "popup-body">
								<p>
									상품을 삭제하시겠습니까
								</p>
							</div>
							<div class = "popup-foot">
								<span class="pop-btn confirm" id = "delete"  data-prodno = "${prodNo}"> 확인</span>
								<span class="pop-btn close" id = "close"> 취소 </span>
							</div>
						</div>
					</div>
				</div>`;
						
	//$("#modal-container").html(deleteProdCode);
	//$("#deleteProdModal").css('display','flex').hide().fadeIn();
	
	modalEvent(deleteProdCode);
	
	
	

});




//상품삭제
$(document).on('click', '#delete', function()  {
	const prodNo = $(this).data("prodno");
	$.ajax({
		url: `${mypath}/deleteProduct.do`,
		data: { prodNo: prodNo },
		success: res => {
			console.log(res);
			
			//$(".popup-wrap").fadeOut();
			loadTransactionData('상품관리', 4);
		},
		error: function(xhr) {
			console.log(xhr);
			console.log('삭제처리 오류')
		}
	});

	})

//----------------------------------------------------------------------------




//상품 수정 버튼
$(document).on('click', '#edit-btn', function() {
	location.href = `${mypath}/product/updateProduct.do`;
});








//-----------------------------------------------------------------------------











// 운송정보 입력 이벤트
$(document).on('click', '.trackInfoInsert-btn', function() {
	txnNo = $(this).data("txnno");
	console.log("txnNo 값 확인:", txnNo);
	
	
	$.ajax({
		url: `${mypath}/getProdInfo.do`, 
		
				data: {
					"txnNo": txnNo
				},
				dataType: 'json',
				success: res => {
					// 운송정보 입력 모달 생성
					trackModalCode = `<div class="container">
								 			<div class = "popup-wrap" id = "trackModal">
												<div class ="popup">
													<div class="popup-head">
														<span class = "head-title">
															운송정보 입력
														</span>
													</div>
													
													<div class= "popup-body">
														<div class = "trans_info">
															<div id = "trans_img_box"> 
																<img src ="${res.file_path}" alt ="prod_img" class= "img">
															</div>
															<div class = "trans_box">
																	<span> ${res.prod_name}</span>
																	<span>${res.prod_price}</span>
															<div>
														</div>	
														
														<div class ="trans_insert">
															<input type="text" id="ship-porv" placeholder="운송업체 입력">
									               			<input type="text" id="track-num" placeholder="운송장 번호 입력">
														</div>
															
													</div>
													
												</div>
												<div class = "popup-foot">
													<span class="pop-btn confirm" id = "submitTrackInfo"> 확인</span>
													<span class="pop-btn close" > 닫기 </span>
												</div>
											</div>
										</div>
									</div>`;
					
					
					
					//$("#modal-container").html(trackModalCode);
					//$("#trackModal").css('display','flex').hide().fadeIn();
					modalEvent(trackModalCode);

					
			
		        },
				error: err => {
							console.log("상품 정보 불러오기 실패:", err);
				}

	})

	

	
});





// 운송정보 입력 전송
$(document).on('click', '#submitTrackInfo', function() {
	const shipPorv = $("#ship-porv").val().trim();
	const trackNum = $("#track-num").val().trim();
	const txnNo = $(".trackInfoInsert-btn").data("txnno");

	if (!shipPorv || !trackNum) {
		alert("운송업체와 운송장 번호를 입력해주세요.");
		return;
	}

	$.ajax({
		url: `${mypath}/insertTrackInfo.do`, 
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		method: 'post',
		data: {
			"shipPorv": shipPorv,
			"trackNum": trackNum,
			"txnNo": txnNo
		},
		success: res => {
			console.log(res);

			if (res.status == 'success') {
				$(`.trackInfoInsert-btn[data-txnno="${txnNo}"]`).replaceWith(`
		                <input type="button" class="m-btn trackReview-btn"  data-txnno="${txnNo}" value="리뷰 확인">`);
						
				// 거래 상태 "배송중"으로 변경
				const $row = $(`.trackReview-btn[data-txnno="${txnNo}"]`).closest('tr');
				$row.find('td').eq(2).text('배송중'); // 상태 들어간 칸, 0부터 시작이라 3번째 칸이 eq(2)

				//$('#trackModal').hide();
				//$(".popup-wrap").fadeOut();
			}
		},
		error: function(xhr) {
			alert('운송장 정보 입력 실패');
		}
	});
});


//-------------------------------------------------------------------------------------------

//리뷰확인버튼
$(document).on('click', '.trackReview-btn', function() {
	const txnNo = $(this).data('txnno');
	console.log("txnNo 값 확인:", txnNo);


	$.ajax({
		url: `${mypath}/getReview.do`,
		method: 'post',
		//data : {txnNo},
		data: {
			"txnNo": txnNo
		},
		dataType: 'json',
		success: res => {
			console.log(res);


			reviewCheckModalCode = `
				
								<div class="container">
								  	<div class="popup-wrap" id = "review1">
								    	<div class="popup">
								      		<div class="popup-head">
								         		 <span class="head-title">리뷰확인</span>
								      		</div>
								      		<div class="popup-body">
								      	  		<div class="review_info">
								          			<div id="img_box">
								      	  				<img src = "${res.file_path}" alt = "prod_img" class= "img">
								      	  			</div>
								      	  		<div class="txt_box">
								      	  			<span id="prod_name">${res.prod_name}</span>
								      	  			<span id="prod_price">${res.prod_price}</span>
								      	  		</div>
								      	  </div>
										  
								      	  <div class="review_rating">
									      	  ${getStarHTML(res.review_rating)}
								      	  </div>
								      	  <div class="review_txt_box">
									      	  	<div class="writer">
									      	  		<span>작성자</span>
									      	  		<p id="mem_name">${res.mem_name}</p>
									      	  	</div>
								      	  <div id="review_cont"><p>${res.review_cont}</p></div>
								      	  </div>
								      </div>
								      <div class="popup-foot">
								        <span class="pop-btn confirm">확인</span>
								      </div>
								    </div>
								  </div>
								</div>`;

			modalEvent(reviewCheckModalCode);
		},
		error: function() {
			reviewModalCode1 = `
									<div class="container">
						 			<div class = "popup-wrap"  id = "review2">
										<div class ="popup">
											<div class="popup-head">
												<span class = "head-title">
													리뷰확인
												</span>
										</div>
										<div class= "popup-body">
											<p>
												리뷰 작성전입니다.
											</p>
										</div>
										<div class = "popup-foot">
											<span class="pop-btn confirm">확인</span>
										</div>
									</div>
								</div>
							</div>`;
		//$("#modal-container").html(reviewModalCode1);
		//$(".popup-wrap").css('display','flex').hide().fadeIn();

		modalEvent(reviewModalCode1);
		}


	})


})

//------------------------------------------------------------------------------

//구매확정 이벤트
$(document).on('click', '.confirmation-btn', function() {


    let txnNo = $(this).data('txnno');
	console.log("txnNo값 확인: ", txnNo);


	confrimModalCode = `
						<div class="container">
				 			<div class = "popup-wrap" id = "confrimModal">
								<div class ="popup">
									<div class="popup-head">
										<span class = "head-title">
											구매 확정
										</span>
								</div>
								<div class= "popup-body">
									<p>
										구매를 확정하시겠습니까?
									</p>
								</div>
								<div class = "popup-foot">
									<span class="pop-btn confirm" id = "submitConfrimbtn">확인</span>
									<span class="pop-btn close"> 취소 </span>
								</div>
							</div>
						</div>
					</div>`;



	//$("#modal-container").html(confrimModalCode);
	//$(".popup-wrap").css('display','flex').hide().fadeIn();
	modalEvent(confrimModalCode);
	

	$(document).on('click', '#submitConfrimbtn', function() {
		//$("#confrimModal").hide();
		//$(".popup-wrap").fadeOut();
		changeReview(txnNo);
	})

})



function changeReview (txnNo){
	$.ajax({
			url: `${mypath}/buyConfrimation.do`,
			data: {
				"txnNo": txnNo
			},
			contentType: 'application/json;charset=UTF-8',
			dataType: 'json',

			success: res => {
				console.log(res);
				confrimCheckCode = `<div class="container">
						 			<div class = "popup-wrap" id = "confrimCheckModal">
										<div class ="popup">
											<div class="popup-head">
												<span class = "head-title">
													구매 확정
												</span>
											</div>
										<div class= "popup-body">
											<p>구매가 확정되었습니다.</p> 
										</div>
										<div class = "popup-foot">
											<span class="pop-btn confirm"> 확인 </span>
										</div>
										</div>
									</div>
								</div>`;
			
		
							
							const $row = $(`.confirmation-btn[data-txnno='${txnNo}']`).closest('tr');
										$row.find('td').eq(2).text('거래완료'); // 상태가 들어간 3번째 td(0부터 시작하므로 eq(2))

							$(`.confirmation-btn[data-txnno="${txnNo}"]`).replaceWith(`
														<input type="button" class="m-btn insertReview-btn"  data-txnno="${txnNo}" value="리뷰 작성">`);

						
			
			
			//$("#modal-container").html(confrimCheckCode);
			//$(".popup-wrap").css('display','flex').hide().fadeIn();
			
			modalEvent(confrimCheckCode);
		}

		})
}

//------------------------------------------------------------------------------------------------------------------
//리뷰작성
$(document).on('click', '.insertReview-btn', function() {
	const txnNo = $(this).data('txnno');
	console.log("txnNo 값 확인:", txnNo);


	reviewModalCode = '';
	$.ajax({
		url: `${mypath}/getProdInfo.do`,
		method: 'post',
		data: {
			"txnNo": txnNo
		},

		dataType: 'json',
		success: res => {
			console.log(res);
			reviewModalCode += 			
							`<div class="container">
								  <div class="popup-wrap" id = "insertReviewModal">
								    <div class="popup">
								      <div class="popup-head">
								          <span class="head-title">리뷰작성</span>
								      </div>
								      <div class="popup-body">
								      	  <div class="review_info">
									          	<div id="img_box">
									      	  		<img src = "${res.file_path}" alt = "prod_img" class= "img">
									      	  	</div>
								      	 		 <div class="txt_box">
									      	  		<span id="prod_name">${res.prod_name}</span>
									      	  		<span id="prod_price">${res.prod_price}</span>
								      	 		 </div>
								      	 	</div>
										 
								      	  <div class="review_rating">`;
										  
									      reviewModalCode += `${getStarHTML()}`;	
									      	 	
								   reviewModalCode +=    	 `</div>
										 
								      	  <div class="review_txt_box">
									      	  	<div id="review_cont">
										      	  	<textarea id = "reviewArea" placeholder= "리뷰를 작성해주세요(최대 200자)"></textarea>
									      	  	</div>
								      	  </div>
								      </div>
								      <div class="popup-foot">
								        <span class="pop-btn confirm" id = "reviewSubmitbtn">확인</span>
								        <span class="pop-btn close">닫기</span>
								      </div>
								    </div>
								  </div>
								</div>`;

			//$("#modal-container").html(reviewModalCode);
			//$(".popup-wrap").css('display','flex').hide().fadeIn();
			
			modalEvent(reviewModalCode);
			
			
		},
		error: err => {
			console.log("상품 정보 불러오기 실패:", err);
		}

	})

	
	
	$(document).on('click', '.star', function() {
		rating = $(this).data('rating');
		$("#reviewRating").val(rating);

		$(".star").each(function() {
			starValue = $(this).data('rating');
			$(this).toggleClass("selected", starValue <= rating);
		});
	})

	
	

	//리뷰 입력 전송
	$(document).on('click', '#reviewSubmitbtn', function() {

		reviewCont = $('#reviewArea').val();
		reviewRating = $('#reviewRating').val();

		console.log("txnNo:", txnNo);
		console.log("reviewContent", reviewCont);
		console.log("reviewRating", reviewRating);

		$.ajax({
			url: `${mypath}/insertReview.do`,
			method: 'post',
			data: {
				"txnNo": txnNo,
				"reviewCont": reviewCont,
				"reviewRating": reviewRating
			},
			success: res => {
				response = JSON.parse(res);
				if (response.status === "success") {
					$(`input.insertReview-btn[data-txnno=${txnNo}]`).css("color", "#ccc"); /////////■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
					modalClose();
					
				} else {
					alert("리뷰 등록 실패: " + response.message);
					
					
				}
			},
			error: err => {
				console.log("리뷰 등록 실패:", err);
			}

		})
	})
})

//---------------------------------------------------------------------------






//----------------------------------------------------------------------------------------------

//배송조회 이벤트
$(document).on('click', '.trackInfoView-btn', function() {
	const txnNo = $(this).data('txnno');
	console.log("txnNo 값 확인:", txnNo);
	$.ajax({
		url: `${mypath}/getTrackInfo.do`,
		data: {
			"txnNo": txnNo
		},
		contentType: 'application/json;charset=UTF-8',
		dataType: 'json',

		success: res => {
			console.log(res)
			trackViewModalCode = 
							`<div class="container">
						 			<div class = "popup-wrap" id = "trackViewModal">
										<div class ="popup">
											<div class="popup-head">
												<span class = "head-title">
														배송조회
												</span>
										</div>
										<div class= "popup-body">`;
										
											if (res.status == 'null') {
												trackViewModalCode += `<p>배송지 입력 전입니다.<p>`;
											}
											
											else if (res.status == 'notNull') {
												trackViewModalCode += `<p>${res.ship_porv}</p>`;
												trackViewModalCode += `<p>${res.track_num}</p>`;
											}
											
				trackViewModalCode += `</div>`;
										
		 trackViewModalCode +=	`<div class = "popup-foot">
											<span class="pop-btn confirm" id = "close"> 확인 </span>
								</div>
							</div>
						</div>
					</div>`;
						
			
			//$("#modal-container").html(trackViewModalCode);
			//$(".popup-wrap").css('display','flex').hide().fadeIn();
			modalEvent(trackViewModalCode);

		}

	})

})



//-----------------------------------------------------------------


function modalEvent(modalCode){
	$("#modal-container").html(modalCode);
	$(".popup-wrap").css('display','flex').hide().fadeIn();

}

$(document).on('click', '.confirm', function(){
  //modalClose();
  $(".popup-wrap").fadeOut();

});

$(document).on('click', '.close', function(){
	$(".popup-wrap").fadeOut();

	
});

function modalClose(){
 $(".popup-wrap").fadeOut();
}

//---------------------------------------------------


function getStarHTML(selectedRating = 0) {
	let starHTML = '<div id="rating-container">';
	for (let i = 1; i <= 5; i++) {
		const selectedClass = i <= selectedRating ? 'selected' : '';
		starHTML += `<span class="star ${selectedClass}" data-rating="${i}">★</span>`;
	}
	starHTML += `<input type="hidden" id="reviewRating" value="${selectedRating}">`;
	starHTML += '</div>';
	return starHTML;
}


//------------------------------------------------------------------------------------------------

//판매중, 판매완료, 예약중 상태 업데이트
$(document).on('change', '.prod-status', function(){
	const newStatus = $(this).val();
	const prodNo = $(this).data('prodno');
	
	
	$.ajax({
		url : `${mypath}/updateProdStatus.do`,
		method : 'post',
		data: {
		  "newStatus": newStatus,
		  "prodNo": prodNo
		},
		success : res => {
			console.log('update 완료')
			console.log(res);
		
		},
		error : xhr =>{
			console.log('update 실패')
			console.log(xhr);
		}
		
	})
	
});










