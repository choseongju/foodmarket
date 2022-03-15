<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓컬리 :: 내일의 장보기, 마켓컬리</title>
<link href="css/user/myPage.css" rel="stylesheet" />
<script src="js/user/tabMenu.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/layout/user/userHeader.jsp" %>
<br><br><br>
  <div class="tab">
    <ul class="tabnav">
      <li><a href="#tab01">회원정보</a></li>
      <li><a href="#tab02">배송정보</a></li>
      <li><a href="#tab03">구매내역</a></li>
    </ul>
    <div class="tabcontent">
      <div id="tab01">
      	<c:forEach var="list" items="${memberList}">
	      	<table id="info" style="margin-top:50px; margin-left:50px;">
	      		<tr>
	      			<td>이름</td>
	      			<td>${list.name}</td>
	      		</tr>
	      		<tr>
	      			<td>연락처</td>
	      			<td>${list.phone}</td>
	      		</tr>
	      		<tr>
	      			<td>이메일</td>
	      			<td>${list.email}</td>
	      		</tr>
	      		<tr>
	      			<td>주소</td>
	      			<td>${list.address}</td>
	      		</tr>
	      		<tr>
	      			<td></td>
	      			<td>${list.address_detail}</td>
	      		</tr>
	      		<tr>
	      			<td colspan="2" style="text-align:center;"><button onclick="modifyModal()">수정</button></td>
	      		</tr>
	      	</table>
      	</c:forEach>
      </div>
      <div id="tab02">
      	<table border=1 style="margin:auto; ">
      		<tr>
      			<td style="width:450px; height:40px; text-align:center;">대기 중</td>
      			<td style="width:450px; text-align:center;">배송 중</td>
      			<td style="width:450px; text-align:center;">배송 완료</td>
      		</tr>
      		<tr>
      			<td style="height:90px;"></td>
      			<td style="height:90px;"></td>
      			<td style="height:90px;"></td>
      		</tr>
      	</table>
      </div>
      <div id="tab03">
      </div>
    </div>
  </div><!--tab-->
  <br><br><br><br><br><br><br><br><br><br><br><br>
<%@include file="/WEB-INF/jsp/layout/user/userFooter.jsp" %>
</body>
</html>