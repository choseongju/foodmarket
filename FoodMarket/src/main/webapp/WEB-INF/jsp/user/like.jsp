<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓컬리 :: 내일의 장보기, 마켓컬리</title>
<link href="css/user/basket.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/layout/user/userHeader.jsp" %>
<div style="margin-top:50px;">
	<div>
		<table style="margin:auto; width:65%; border-collapse:collapse; font-family:Georgia;">
			<tr style="background-color:#fafafa">
				<th></th>
				<th>상품명</th>
				<th>가격</th>
				<th></th>
			</tr>
			<c:choose>
				<c:when test="${goodsList.size() != 0}">
					<c:forEach var="list" items="${goodsList}">
						<tr style="text-align:center;">
							<td style="width:100px; border-left: 1px solid #dcdcdc;"><img id="img_goods" src="upload/${list.goods_filePath}" /></td>
							<td style="width:100px; border-left: 1px solid #dcdcdc;"><a href="/goodsDetails?goods_uid=${list.goods_uid}">${list.goods_name}</a></td>
							<td style="width:100px; border-left: 1px solid #dcdcdc;"><span id="price">${list.goods_price}</span>원</td>
							<td style="width:50px;"><a onclick="likeDelete('${list.goods_uid}')" style="color:red; font-size:13px;">삭제</a></td>
						</tr>
					</c:forEach>
					<tr style="background-color:#fafafa">
						<th id="bottom"colspan="7">
					</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="8" style="text-align:center; padding-top:20px; padding-bottom:20px; font-size:13px;">장바구니에 담긴 상품이 없습니다.</td>
						</tr>
					</c:otherwise>
			</c:choose>
		</table>
		<br><br>
		<div>
			<table style="margin:auto;">
				<tr>
					<td id="td_continue" style="padding-left:20px;"><button id="btnContinue" onclick="btnContinue()">계속 쇼핑하기</button></td>
				</tr>
			</table>
		</div>
		<br><br><br><br>
	</div>
</div>
<%@include file="/WEB-INF/jsp/layout/user/userFooter.jsp" %>
</body>

<script>
 
 var test = JSON.parse('${list}');
 console.log(test)
 
function btnContinue(){
	location.href="/";
}

function likeDelete(uid){
	$.ajax({
		type:"POST",
		url: "/deleteLike",
		data : {"uid" : uid},
		success: function(result)
		{
			alert("선택 상품을 좋아요 목록에서 삭제했습니다!");
			location.reload();
		}
	})
}


</script>
</html>