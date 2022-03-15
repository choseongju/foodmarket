<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/user/mainbody.css" rel="stylesheet" />
<title>마켓컬리 :: 내일의 장보기, 마켓컬리</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/layout/user/userHeader.jsp" %>
<%@include file="/WEB-INF/jsp/layout/user/imageSlide.html" %>
	<div class="recommend_title">
	<div class="image">
		<div class="">
			<div class="">
				<span class="recommend_title_txt">이 상품 어때요?</span>
			</div>
			<div style="margin-bottom:100px;">
			<c:forEach var="goods" items="${goodsList}">
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
			</div>
			<div class="">
				<span class="recommend_title_txt">조회수 TOP 4</span>
			</div>
			<div style="margin-bottom:100px;">
				<c:forEach var="top4" items="${top4List}">
					<div class="today" style="width:350px; height:400px; display: inline-block;">
						<a href="/goodsDetails?goods_uid=${top4.goods_uid}"><img src="/upload/${top4.goods_filePath}" /></a>
							<div style="width:250px;">
								<div>
									<a href="/goodsDetails?goods_uid=${top4.goods_uid}"><span style="float:left; padding-left:20px; margin-top:10px; font-weight:400;">${top4.goods_name}</span></a><br><br>
								</div>
							<div>
								<span style="float:left;  padding-left:20px; margin-top:10px; font-weight:600; ">${top4.goods_price}원</span>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="">
				<span class="recommend_title_txt">좋아요 수 TOP 4</span>
			</div>
			<div>
				<c:forEach var="likeTop4" items="${likeTop4List}">
					<div class="today" style="width:350px; height:400px; display: inline-block;">
						<a href="/goodsDetails?goods_uid=${likeTop4.goods_uid}"><img src="/upload/${likeTop4.goods_filePath}" /></a>
							<div style="width:250px;">
								<div>
									<a href="/goodsDetails?goods_uid=${likeTop4.goods_uid}"><span style="float:left; padding-left:20px; margin-top:10px; font-weight:400;">${likeTop4.goods_name}</span></a><br><br>
								</div>
								<div>
									<span style="float:left;  padding-left:20px; margin-top:10px; font-weight:600;">${likeTop4.goods_price}원</span>
								</div>
							</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
	<!-- 사용자 푸터 -->
<br><br><br><br>
<%@include file="/WEB-INF/jsp/layout/user/userFooter.jsp" %>
</body>
</html>