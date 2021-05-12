<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>
<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<!-- <a href="https://kauth.kakao.com/oauth/authorize?client_id=0540628c69ea1c2b2a530c1f3bc02666&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"><img height="38px" width="74px" src="/image/kakao_login_button.png"/></a> -->
		</form>
		<a href="/oauth2/authorization/google">구글 로그인</a>
		<a href="/oauth2/authorization/facebook">페이스북 로그인</a>
		<a href="/oauth2/authorization/naver">네이버 로그인</a>
</div>
<%@ include file="../layout/footer.jsp"%>
