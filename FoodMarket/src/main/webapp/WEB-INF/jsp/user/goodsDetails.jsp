<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓컬리 :: 내일의 장보기, 마켓컬리</title>
<link href="css/user/goodsDetails.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/layout/user/userHeader.jsp" %>
<c:forEach var="goods" items="${JSONGoodsList}">
	<div class="goods">
		<div class="goodsInfo">
			<div class="goodsImg">
				<img id="goods" style="width:500px; height:600px;" src="/upload/${goods.goods_filePath}" />
			</div>
			<div class="goodsExplain">
				<dl id="explain">
					<dt id="goodsName">${goods.goods_name}(${goods.goods_category})</dt>
					<dt id="goodsPrice">${goods.goods_price} 원</dt>
				</dl>
				<br>
				<hr id="goodsHR">
				<div class="explainText">
					<span style="font-size:20px; margin-top:25px; font-weight: bold; color:#58595b; font-family:'Noto Sans';">상품 설명 : </span>
					<dl>
						<dt id="goodsExplain">${goods.goods_explain}</dt>
					</dl>
				</div>
				<hr id="goodsHR">
				<div class="quantity">
					<span style="font-size:20px; margin-top:25px; font-weight: bold; color:#58595b;">남은 수량 : </span>
					<dl style="margin-top:8px;">
						<dt id="goodsQuantity"> ${goods.goods_quantity} 개</dt>
					</dl>
				</div>
				<hr id="goodsHR">
				<div class="checkquantity">
					<span style="font-size:20px; margin-top:38px; font-weight: bold; color:#58595b;">수량 선택 : </span>
					<dl>
						<dt id="goodsQuantity2">
							<input type="button" id="btn_minus" onclick="count('minus')" value="-">
							<span id="result">0</span>
							<input type="button" id="btn_plus" onclick="count('plus')" value="+">
						</dt>
					</dl>
				</div>
				<hr id="goodsHR">
				<br>
				<div class="totalPrice">
					<span id="totalPrice">총 상품 금액 :</span>
					<ul style="margin:0; padding:0;">
						<li id="goodsTotalPrice">0</li>
						<li style="font-size:20px; font-weight:600">원</li>
					</ul>
				
				</div>
				<br><br><br><br><br>
				<table style="margin-left:130px;">
				<tr>
					<td style="padding-right:40px;">
						<% if(authentication == null || authentication.getPrincipal() == "anonymousUser"){%>
							<button id="btn_like" onclick="goodsLikeCase1()"><img style="width:70%;"src="img/user/heartblack.png"/></button>
						<%}%>
						<sec:authorize access="isAuthenticated()">
							<c:choose>
								<c:when test="${goodsLike == 0}">
									<button id="btn_like" onclick="goodsLike()"><img style="width:70%;"src="img/user/heartblack.png"/></button>
								</c:when>
								<c:otherwise>
									<button id="btn_like" onclick="deleteGoodsLike()"><img style="width:70%;"src="img/user/heartred.png"/></button>
								</c:otherwise>
							</c:choose>
						</sec:authorize>			
					</td>
					<td><button id="btn_bucket" onclick="bucket()">장바구니</button>
				</tr>
				</table>
			</div>
		</div>
	</div>
</c:forEach>

<%@include file="/WEB-INF/jsp/layout/user/userFooter.jsp" %>
</body>
<script>
//document.getElementById("resultPrice").style.visibility = "hidden";
var goods = JSON.parse('${JSONGoodsList}');
var goods_id = goods[0].goods_uid;
var goods_name = goods[0].goods_name;
var goodsQuantity = goods[0].goods_quantity;
var goods_filePath = goods[0].goods_filePath;
var goodsPrice = goods[0].goods_price;

// 상품 수량 + -
function count(type)  {
	  // 결과를 표시할 element
	  const resultElement = document.getElementById('result');
	  const totalPriceElement = document.getElementById('goodsTotalPrice');
	  
	  //남은 수량 불러오기
	  var goodsQuantity = goods[0].goods_quantity;
	  var goodsPrice = goods[0].goods_price;
	  
	  // 현재 화면에 표시된 값
	  let number = resultElement.innerText;
	  let total = totalPriceElement.innerText;
	  
	  // 수량 더하기/빼기
	  if(type === 'plus') {
	    number = parseInt(number) + 1;
	    total = goodsPrice * number;
	    if(number > goodsQuantity){
	    	alert("남은 수량보다 더 선택할 수 없습니다!");
	    	return false;
	    }

	  }else if(type === 'minus')  {
	    number = parseInt(number) - 1;
	    total = goodsPrice * number;
	    if(number < 0){
	    	alert("0보다 작게 선택할 수 없습니다!");
	    	return false;
	    }
	  }
	  
	  // 결과 출력
	  resultElement.innerText = number;
	  totalPriceElement.innerText = total;
}

// 장바구니에 상품 담기
function bucket(){
	//사용자 권한 받아오기
	var auth = JSON.parse('${auth}');
	var authority = auth[0].authority;
	
	
	if(authority == "ROLE_ANONYMOUS"){
		if(confirm("로그인이 필요한 서비스입니다. 로그인을 하시겠습니까?")){
			location.href = "/login";
		}else{
			return false;
		}
	}else{
		if(confirm("장바구니에 추가하시겠습니까?")){
			var quantity = document.getElementById('result').innerText;
			
			$.ajax({
				type: "POST",
				url: "user/basket",
				data: {"id" : goods_id, "name" : goods_name, "filePath" : goods_filePath, "quantity" : quantity, "price" : goodsPrice}
			})
			
			alert("장바구니에 담기 성공!");
			if(confirm("장바구니로 이동하시겠습니까?")){
				location.href="/basketForm";
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}

// 상품 좋아요
function goodsLike(){
		$.ajax({
			type: "POST",
			url: "/goodsLike",
			data: {"id" : goods_id, "name" : goods_name}
		})
		
		location.reload();
}

// 상품 좋아요 취소
function deleteGoodsLike(){
	$.ajax({
		type: "POST",
		url: "/deleteGoodsLike",
		data: {"id": goods_id, "name" : goods_name}
	})
	location.reload();
}
//익명 사용자가 좋아요 버튼 눌렀을 시
function goodsLikeCase1(){
	if(confirm("로그인이 필요한 서비스입니다. 로그인을 하시겠습니까?")){
		location.href = "/login";
	}else{
		return false;
	}
}

</script>
</html>