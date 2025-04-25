
console.log(member);

$.ajax({
  url: `${mypath}/selectUpdateMember.do`,
  type: 'post',
  data: {"no": member.mem_no},
  success: function(vo){
    console.log(vo);
    $('#mem_no').val(vo.mem_no);
    $('#email').val(vo.mem_email);
    $('#name').val(vo.mem_name);
    if(vo.mem_tel !== 'null'){
      $('#phone').val(vo.mem_tel);
    }
    if(vo.zipcode > 0){
      $('#zipcode').val(vo.zipcode);
      $('#address').val(vo.add1);
      $('#detailAddress').val(vo.add2);
    }
  },
  error: function(xhr){
    console.log(xhr.status)
  },
  dataType:'json'
});


$(function(){
  const pwEl = $('#password');
  const pw_ckEl = $('#password_ck');
  const ckEl = $('#pw_ck');
  const pwLenEl = $('#pwLen');
  
  
  let pw_ck = false;
  
  $('#udpateBtn').on('click', function() {
    let no = $('#mem_no').val();
    let email = $('#email').val();
    let name = $('#name').val();
    let pw = pw_ckEl.val();
    let phone = $('#phone').val();
    let zipcode = $('#zipcode').val();
    let address = $('#address').val();
    let detailAddress = $('#detailAddress').val();

    $.ajax({
      url: `${mypath}/updateMember.do`,
      type: 'post',
      data: {
        no: no,
        email: email,
        pw: pw,
        name: name,
        tel: phone,
        zipcode: zipcode,
        address: address,
        address_detail: detailAddress
      },
      success: function(data){
        if(data > 0){
          alert('계정 정보 수정 완료');
		  location.href = `${mypath}/mainPage.do`;
        }
      },
      error: function(xhr){
        console.log(xhr.status);
      }
    });
  });
  
  pwEl.on('keyup', function() {
    let pw = pwEl.val();
    let pw2 = pw_ckEl.val();
    pwLenEl.text(pw.length + '/20').css('color', pw.length < 6 ? 'red' : 'green');
  
    if (pw === pw2 && pw !== '' && pw.length !== 0) {
      pw_ck = true;
      ckEl.text('check_circle').css('color', 'green');
    } else {
      pw_ck = false;
      ckEl.text('cancel').css('color', 'red');
    }
  });
  
  pw_ckEl.on('keyup', function() {
    let pw = pwEl.val();
    let pw2 = pw_ckEl.val();
    if (pw === pw2 && pw !== '' && pw.length !== 0) {
      pw_ck = true;
      ckEl.text('check_circle').css('color', 'green');
    } else {
      pw_ck = false;
      ckEl.text('cancel').css('color', 'red');
    }
  });
});


