<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓컬리 :: 내일의 장보기, 마켓컬리</title>
<link href="css/user/byCategory.css" rel="stylesheet" />
<link href="css/user/mainbody.css" rel="stylesheet" />
</head>
<body>
<%@include file="/WEB-INF/jsp/layout/user/userHeader.jsp" %>
<div class="main" style="margin-bottom:100px;">
	<div class="img_body">
	<c:forEach var="vegatables" items="${vegatablesList}">
		<div class="today" style="width:350px; height:400px; margin-bottom:100px; display: inline-block;">
					<a href="/goodsDetails?goods_uid=${vegatables.goods_uid}"><img src="/upload/${vegatables.goods_filePath}" /></a>
						<div style="width:250px;">
							<div>
								<a href="/goodsDetails?goods_uid=${vegatables.goods_uid}"><span style="float:left; padding-left:20px; margin-top:10px; font-weight:400;">${vegatables.goods_name}</span></a><br><br>
							</div>
							<div>
								<span style="float:left;  padding-left:20px; margin-top:10px; font-weight:600;">${vegatables.goods_price}원</span>
							</div>
						</div>
					</div>
	</c:forEach>
	</div>
</div>
<%@include file="/WEB-INF/jsp/layout/user/userFooter.jsp" %>
</body>
</html>