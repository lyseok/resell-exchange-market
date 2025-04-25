let idCk = false;
let pwCk = false;

let emailEl = $('#email');
let passwordEl = $('#password_ck');

$('#idck_btn').on('click',function() {
  $.ajax({
    url: `${mypath}/idCheck.do`,
    type: 'POST',
    data: {
      email: $('#email').val()
    },
    success: function(data) {
      if(data > 0) {
        alert('이미 사용중인 이메일입니다.');
        idCk = false;
        emailEl.css('border', '2px solid red');
        emailEl.focus();
      } else {
        alert('사용 가능한 이메일입니다.');
        idCk = true;
        emailEl.css('border', '2px solid blue');
      }
    },
    error: function(xhr) {
      console.log(xhr.status);
    }
  });
});

$('#pwck_btn').on('click',function() {
  let pw1 = $('#password').val();
  let pw2 = passwordEl.val();
  if(pw1 != pw2) {
    pwCk = false;
    alert('비밀번호가 일치하지 않습니다.');
    passwordEl.css('border', '2px solid red');
    passwordEl.focus();
  } else {
    pwCk = true;
    alert('비밀번호가 일치합니다.');
    passwordEl.css('border', '2px solid blue');
  }
});

emailEl.on('keydown', function(){
  idCk = false;
});

passwordEl.on('keydown', function(){
  pwCk = false;
});

$('#password').on('keydown', function(){  
  pwCk = false;
});

$('#singupBtn').on('click', function() {
console.log('click');
  let email = $('#email').val();
  let password_ck = $('#password_ck').val();
  let name = $('#name').val();
  if(!idCk) {
    alert('이메일 중복체크를 해주세요.');
    return;
  }
  if(!pwCk) {
    alert('비밀번호를 확인해주세요.');
    return;
  }

  $.ajax({
    url: `${mypath}/signUpProcess.do`,
    type: 'POST',
    data: {
      email: email,
      password: password_ck,
      name: name
    },
    success: function(data) {
      if(data > 0) {
        alert('회원가입이 완료되었습니다.');
        location.href = `${mypath}/loginPage.do`;
      } else {
        alert('회원가입에 실패했습니다. 다시 시도해주세요.');
      }
    },
    error: function(xhr) {
      console.log(xhr.status);
    }
  });
});

