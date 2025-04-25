/**
 * 
 */

const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

$('#loginBtn').on('click', function() {
	login();
});

$('#inPassword').on('keydown', function(e){
	if(e.keyCode == 13){
		login();
	}
});


const login = () => {
	let email = $('#inEmail').val();
	let password = $('#inPassword').val();

	if(email == '' || email == null) {
		alert('이메일을 입력해주세요.');
		return;
	}

	if(password == '' || password == null) {
		alert('비밀번호를 입력해주세요.');
		return;
	}
	
	$.ajax({
		url: `${mypath}/loginProcess.do`,
		type: 'POST',
		data: {
			inEmail: email,
			inPassword: password
		},
		success: function(data) {
			console.log(data);
			if(data == 'member') {
				location.href = `${mypath}/mainPage.do`;
			} else if(data == 'admin'){
				location.href = `${mypath}/admin/selectAdminMain.do`;
			} else if(data == 'false') {
				alert('로그인 실패! 이메일과 비밀번호를 확인해주세요.');
			}
		},
		error: function(xhr) {
			console.log(xhr.status);
		}
	});
}