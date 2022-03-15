<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓컬리 :: 내일의 장보기, 마켓컬리</title>
<link href="css/user/mainbody.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/layout/user/userHeader.jsp" %>
<%@include file="/WEB-INF/jsp/layout/user/imageSlide.html" %>
	<div class="recommend_title">
	<div class="image">
				<span class="recommend_title_txt">검색 결과</span>
			</div>
			<div style="margin-bottom:100px; margin-left:200px; margin-right:200px;">
				<c:choose>
					<c:when test="${goodsSearchList.size() != 0}">
					<c:forEach var="goods" items="${goodsSearchList}">
						<div class="today" style="width:350px; height:400px; display: inline-block;">
							<a href="/goodsDetails?goods_uid=${goods.goods_uid}"><img src="/upload/${goods.goods_filePath}" /></a>
								<div style="width:250px;">
									<div>
										<a href="/goodsDetails?goods_uid=${goods.goods_uid}"><span style="float:left; padding-left:20px; margin-top:10px; font-weight:400;">${goods.goods_name}</span></a><br><br>
									</div>
									<div>
										<span style="float:left;  padding-left:20px; margin-top:10px; font-weight:600;">${goods.goods_price}원</span>
									</div>
								</div>
							</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="result">
								<span id="resultText">검색 결과가 없습니다.</span>
							</div>
						</c:otherwise>
					</c:choose>
			</div>
		</div>
<%@include file="/WEB-INF/jsp/layout/user/userFooter.jsp" %>
</body>
</html>