<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>마켓컬리 :: 내일의 장보기, 마켓컬리</title>
<link href="css/user/login.css" rel="stylesheet" />
</head>
<body>
<%@ include file="/WEB-INF/jsp/layout/user/userHeader.jsp" %>
  <div>
	<div>
		<div style="margin:0 800px 0 800px; text-align:center;">
			<h5 style="font-size:23px; font-family:Noto Sans;">로그인</h5>
		</div>
		<div>
		<form action="loginOk" method="post">
			<table style="margin:auto;">
			<tr>
			<td><input type="text" name="username" placeholder="아이디를 입력해주세요." style="font-size:15px; margin-bottom:30px; width:345px; height:50px; padding: 0 19px;"></td>
			</tr>
			<tr>
			<td><input type="password" name="password" placeholder="비밀번호를 입력해주세요." style="font-size:15px; margin-bottom:30px; width:345px; height:50px; padding: 0 19px;"></td>
			</tr>
		</table>
		<div class="error">
			<span id="errorMsg" th:text="${errorMessage}"></span>
		</div><br>
		<input type="submit" class="btn-login" value="로그인">
		<br>
		</form>
		</div>
		<a href="/join"><button class="btn-signup">회원가입</button></a>
		<br><br><br><br>
	</div>
</div>
</body>
<%@ include file="/WEB-INF/jsp/layout/user/userFooter.jsp" %>
</html>