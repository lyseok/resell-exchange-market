<%@page import="rem.login.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="icon" href="<%=request.getContextPath() %>/images/favicon.png">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/reset.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login/login.css">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.1.js"></script>

<script defer src="<%=request.getContextPath() %>/js/login/login.js"></script>
<script defer src="<%=request.getContextPath() %>/js/login/signup.js"></script>

<%

String login = (String)session.getAttribute("login");	

%>
<script>
mypath = '<%=request.getContextPath() %>';
</script>

</head>
<body id="login">

  <a href="<%=request.getContextPath() %>/mainPage.do">메인으로 돌아가기</a>

<div class="container" id="container">
  <div class="form-container sign-up-container">
    <form action="#">
      <h1 id="title">회원가입</h1>
        <label for="email" id="email-label">
          <input type="email" id="email" placeholder="Email" name="email" required />
          <button type="button" id="idck_btn" class="pwck_btn">확인</button>
        </label>
        <label for="password" id="password-label">
          <input type="password" id="password" placeholder="password" name="password" required />
        </label>
        <label for="password_ck" id="password-ck-label">
          <input type="password" id="password_ck" placeholder="password" name="password_ck" required />
          <button type="button" id="pwck_btn" class="pwck_btn">확인</button>
        </label>
        <label for="name" id="name-label">
          <input type="text" id="name" placeholder="Name" name="name" required />
        </label>
        <button type="button" id="singupBtn">확인</button>
    </form>
  </div>
      
  <div class="form-container sign-in-container">
    <form id="loginForm" action="<%=request.getContextPath() %>/loginProcess.do" method="post">
      <h1 id="title">로그인</h1>
        <label for="inEmail" id="email-label">
          <input type="email" id="inEmail" placeholder="Email" name="inEmail" required />
        </label>
        <label for="inPassword" id="password-label">
          <input type="password" id="inPassword" placeholder="Password" name="inPassword" required />
        </label>
      <!-- <a href="#">비밀번호 찾기</a> -->
      <button type="button" id="loginBtn">로그인</button>
    </form>
  </div>

  <div class="overlay-container">
    <div class="overlay">
      <div class="overlay-panel overlay-left">
        <h1 id="title">Welcome!</h1>
        <p>로그인 하시겠습니까?</p>
        <button class="ghost" id="signIn">로그인</button>
      </div>

      <div class="overlay-panel overlay-right">
        <h1 id="title">Hello!</h1>
        <p>띹장터 회원가입 하러하기</p>
        <button class="ghost" id="signUp">회원가입</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>