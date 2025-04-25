/**
 * 
 */

$(document).on('click', '.image',function(){
		deletePhoto();
	})

const getMainCate = (type, m) =>{
	

		//contentType : 'application/json; charset=utf-8',
$.ajax({
		
		url:`${mypath}/product/getCateMain.do`,
		type:'post',
		success : res=>{
			console.log(res)
			code = '';
			subcode = `
					   <li data-value="1">중분류 선택</li>
					  `;
			$.each(res, function(i,v){
				code += `<li data-value="${v.cate_main_id}">${v.cate_main_name}</li>`
			})
			
			$('#cate_main_id').html(code);
			$('#cate_sub_id').html(subcode);
		},
		error : xhr=>{
			alert("오류 : ",xhr.status)
		},
		dataType : 'json'
	})
}

const getSubCate = (type, m, s) =>{
	$('#cate_main_id').on('click','li', function(){
			
			$('#cate_main_id li').removeClass('active');
			
			$(this).addClass('active');
			
			idValue = $(this).data('value');
			console.log(idValue);
			
			$.ajax({
				
				url:`${mypath}/product/getCateSub.do`,
				type : 'post',
				contendType : 'application/x-www-form-urlencoded',
				data : {cate_main_id : idValue},
				success : res=>{
					console.log(res)
					code='';
					
						$.each(res, function(i,v){
							let active = '';
							if(v.cate_sub_id===s){
								active = "active";
							}
							code += `<li data-value="${v.cate_sub_id}" class="${active}">${v.cate_sub_name}</li>`
					
						})
					$('#cate_sub_id').html(code);
				},
				error : xhr=>{
					alert(xhr.status)
				},
				dataType : 'json'
			})
		})
}

const clickSubCate = () =>{
	
	$('#cate_sub_id').on('click', 'li', function(){
			
			$('#cate_sub_id li').removeClass('active');
			$(this).addClass('active');
		})
}

$('#photo').on('change', function(){
		addPhoto();
	})

const addPhoto = () =>{
			const files = $('#photo')[0].files;
			
			if(files.length + selectedFiles.length > 5){
				alert("최대 5개까지 업로드 할 수 있습니다.");
				return;
			}
			
			$.each(files, function(i,v){
				
				const img = $('<img>').attr('src', URL.createObjectURL(v));
				$('div[id="add_img"]').append(img);
				
				selectedFiles.push(v);
				console.log(selectedFiles)
			})
			$('#imgCount').text(selectedFiles.length);
	}
	
const deletePhoto = () =>{
	
}

const insertProduct = () => {
	$('#insert').on('click', function(){
			prod_name = $('#prod_name').val();
			cate_sub_id = $('#cate_sub_id li').data('value');
			console.log(cate_sub_id);
			prod_condition = $('input[name="prod_condition"]:checked').val();
			prod_content = $('#prod_content').val();
			prod_price = $('#prod_price').val();
			price_offer = $('#price_offer').is(':checked')? $('#price_offer').val() : 0;
			prod_tr_approach = $('input[name="prod_tr_approach"]:checked').val();
			checkImg = "NEW-IMG";
			
			formData = new FormData();
			
			if(selectedFiles.length>0){
				$.each(selectedFiles, function(i, v){
					
					formData.append('files[]', v);
				})
			}
			if(!selectedFiles){
				checkImg = "NO";
			}
			formData.append('checkImg', checkImg);
			formData.append('prod_name', prod_name);
			formData.append('cate_sub_id', cate_sub_id);
			formData.append('prod_condition', prod_condition);
			formData.append('prod_content', prod_content);
			formData.append('prod_price', prod_price);
			formData.append('price_offer', price_offer);
			formData.append('prod_tr_approach', prod_tr_approach);
			console.log(formData);
			
			$.ajax({
				
				url:`${mypath}/product/insertProduct.do`,
				type : 'post',
				data : formData,
				contentType : false,
				processData : false,
				success : res=>{
					location.href = `${mypath}/product/productDetail.do?prod_no=${res}`;
				},
				error : xhr=>{
					alert(xhr.status)
				}
			})
		})
		
}

