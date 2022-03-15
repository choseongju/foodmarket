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
				<th style="font-size:13px;"><input type="checkbox" name="all" value="selectAll" onclick="selectAll(this)" />전체선택</th>
				<th></th>
				<th>상품명</th>
				<th>수량</th>
				<th>가격</th>
				<th>상품 총 가격</th>
				<th></th>
			</tr>
			<c:choose>
				<c:when test="${basketList.size() != 0}">
					<c:forEach var="basket" items="${basketList}">
						<tr style="text-align:center;">
							<td style="width:50px;"><input type="checkbox" id="checkBox" name="checkBox" value="${basket.basket_uid}" onclick="checkBox(this)"></td>
							<td style="width:150px; border-left: 1px solid #dcdcdc;"><img id="img_goods" src="upload/${basket.goods_filePath}" /></td>
							<td style="width:100px; border-left: 1px solid #dcdcdc;"><a href="/goodsDetails?goods_uid=${basket.goods_uid}">${basket.goods_name}</a></td>
							<td style="width:100px; border-left: 1px solid #dcdcdc;">
								<input type="hidden" id="quantity" value="${basket.goods_select_quantity}" onclick="count()" disabled>${basket.goods_select_quantity}개 
							</td>
							<td style="width:100px; border-left: 1px solid #dcdcdc;"><span id="price">${basket.goods_price}</span>원</td>
							<td style="width:100px; border-left: 1px solid #dcdcdc;"><span id="totalPrice">${basket.goods_select_quantity * basket.goods_price}</span>원</td>
							<td style="width:50px;"><a onclick="basketDelete('${basket.basket_uid}')" style="color:red; font-size:13px;">삭제</a></td>
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
		<c:if test="${basketList.size() != 0}">
		<div class="resultPrice_ul">
			<ul class="resultPrice">
			<li id="priceExplain">총 상품 금액 : </li>
			<li id="total">0</li>
			<li id="won">원</li>
			</ul>
		</div>
		<br><br><br><br>
		<div>
				<table style="margin:auto;">
					<tr>
						<td id="td_order" style="padding-right:20px;"><button id="order" onclick="order()">주문하기</button></td>
						<td id="td_continue" style="padding-left:20px;"><button id="btnContinue" onclick="btnContinue()">계속 쇼핑하기</button></td>
					</tr>
				</table>
		</div>
		</c:if>
		<br><br><br>
	</div>
</div>
<%@include file="/WEB-INF/jsp/layout/user/userFooter.jsp" %>
</body>
<script>
//페이지 시작시
$(document).ready(function(){
	//cnt(DB 조회해서 cnt를 받아온 값)
	var cnt = '${cnt}'
	
	//cnt가 0이 아니면 구매창으로 이동할 여부 알람
	if(cnt != 0){
		if(confirm("전에 구매하시던 내역이 있습니다. 구매 창으로 이동하시겠습니까?")){
			location.href="/orderForm"; // 확인을 누르면 구매창으로 이동
		}else{ // 아니오를 누르면 order_cnt를 0으로 update한 후 페이지 reload
			$.ajax({
				type: "POST",
				url: "/orderCntReset"
			})
			
			location.reload();
		}
	}
})

var JSONbasketList = JSON.parse('${basketList}');


function order(){
	var orderArr = new Array();
	$("input[name=checkBox]:checked").each(function(i){
		orderArr.push($(this).val());
	})
	
	console.log(orderArr)
	
	if(orderArr.length != 0){
		if(confirm("선택 상품을 주문하시겠습니까?")){
			$.ajax({
				type: "POST",
				url:"/order",
				data: {"orderArr" : orderArr}
			})
			
			setTimeout('location.href="/orderForm"',100);
		}else{
			return false;
		}		
	}else{
		alert("상품을 선택해주세요!");
		return false;
	}
}

function btnContinue(){
	location.href="/"
}

// checkbox value값 배열에 담은 함수
function checkBox(){
	
	var arr = new Array(); // 체크된 상품 장바구니 uid를 저장할 배열
	var priceArr = new Array(); // 체크된 총 가격을 저장할 배열
	
	var sum = 0; // 총 결과(최종합계)를 0으로 선언하고
	
	// input에 name이 checkBox인 것이 선택됐을 때 배열에 uid를 push
	$("input[name=checkBox]:checked").each(function(i){
		arr.push($(this).val());
	})
	
	//그 후 push가 되어 arr배열이 null 이 아니면 arr에 크기만큼 for문 돌리기
	if(arr != null){
		for(var i=0; i<arr.length; i++){
			var uid = arr[i]; // arr배열에서 요소 꺼내오고
			
			//for문 한번 더 돌려서 controller에서 받아온 장바구니 배열크기만큼 for문 돌리고
			for(var j=0; j<JSONbasketList.length; j++){
				//에 basket_uid와 arr배열(체크된 basket_uid)과 일치하면
				if(JSONbasketList[j].basket_uid == uid){
					var price = JSONbasketList[j].goods_price; // 상품 가격을 받아오고
					var select_quantity = JSONbasketList[j].goods_select_quantity; // 선택 수량을 받아오고
					var totalPrice = price * select_quantity; // 선택 수량 * 가격을 곱하면 선택 상품 총 금액
					
					priceArr.push(totalPrice);
				}
			}
		}
		
		// priceArr 배열에 크기만큼 for문을 돌려 배열의 총합계를 구하면 선택 상품들의 총 금액
		for(var i=0; i<priceArr.length; i++){
			sum += priceArr[i];
		}
	}
	
	const total = document.getElementById('total'); // 현재 화면에 표시된 최종 금액
	total.innerText = sum; // 구한 최종 금액 화면에 출력
}

function basketDelete(uid){

	$.ajax({
		type: "POST",
		url: "/deleteBasket",
		data : {"uid" : uid},
		success: function(result)
		{
			alert("선택 상품을 장바구니에서 삭제했습니다!");	
			location.reload();
		}
	})
}

</script>
</html>