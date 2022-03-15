<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓컬리 :: 내일의 장보기, 마켓컬리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="css/user/basket.css" rel="stylesheet" />
<link href="css/user/order.css" rel="stylesheet" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/layout/user/userHeader.jsp" %>
<div style="margin-top:50px;">
	<div>
		<table style="margin:auto; width:65%; border-collapse:collapse; font-family:Georgia;">
			<tr style="background-color:#fafafa">
				<th></th>
				<th>상품명</th>
				<th>수량</th>
				<th>가격</th>
				<th>상품 총 가격</th>
			</tr>
			<c:forEach var="list" items="${list}">
				<tr>
					<td style="width:150px; border-left: 1px solid #dcdcdc; text-align:center;">
						<img id="img_goods" style="width:40%; height:20%;"src="upload/${list.goods_filePath}" />
					</td>
					<td style="width:70px; border-left: 1px solid #dcdcdc; text-align:center;">
						${list.goods_name}
					</td>
					<td style="width:70px; border-left: 1px solid #dcdcdc; text-align:center;">
						${list.goods_select_quantity}개 
					</td>
					<td style="width:70px; border-left: 1px solid #dcdcdc; text-align:center;">
						<span id="price">${list.goods_price}</span>원
					</td>
					<td style="width:70px; border-left: 1px solid #dcdcdc; text-align:center;">
						<span id="totalPrice">${list.goods_select_quantity * list.goods_price}</span>원
					</td>
				</tr>
			</c:forEach>
				<tr style="background-color:#fafafa">
					<th id="bottom"colspan="7">
				</tr>
		</table>
		<br><br><br>
		<div class="resultPrice_ul" style="margin:auto; margin-right:710px;">
			<ul class="resultPrice">
			<li id="priceExplain">주문 금액 :</li>
			<li id="total"><c:out value="${resultPrice}" /></li>
			<li id="won">원</li>
			<li style="margin:10px 20px 0 20px;"><img src="img/user/plus.png" style="width:30px;"/></li>
			<li id="priceExplain">배송비 :</li>
			<li id="total"><c:out value="${deliveryPrice}" /></li>
			<li id="won">원</li>
			<li style="font-size:30px; font-weight:500; margin-left:10px; margin-top:5px;"> = </li>
			<li style="margin-left:10px; margin-right:10px; font-size:35px; font-weight:600;"><c:out value="${resultPrice + deliveryPrice}" /></li>
			<li id="won">원</li>
			</ul>
		</div>
		<br><br><br><br><br><br>
		<div>
			<h2 style="margin-left:370px; margin-bottom:20px;">배송지 설정</h2>
			<table id="asd" style="margin-left:370px;">
				<tr>
					<th colspan="7"></th>
				</tr>
				<tr>
					<td colspan="2" style="border-bottom:none;">
						<input type="radio" name="radio" value="basicAddress" onclick="funcAddress('e1')"/>기본 배송지
						<input type="radio" name="radio" value="newAddress" style="margin-left:30px;" onclick="funcAddress('e2')"/>신규 배송지 입력
					</td>
					<td />
				</tr>
				<tbody id="body">
				</tbody>
			</table>
		</div>
		<br><br><br><br>
		<div>
			<h2 style="margin-left:370px; margin-bottom:20px;">결제 수단</h2>
			<table style="margin-left:370px;">
				<tr>
					<th colspan="7"></th>
				</tr>
				<tr>
					<td style="width:1350px;">
						<input type="radio" name="radio" value="계좌이체" />계좌이체
						<input type="radio" name="radio" value="신용카드" style="margin-left:30px;"/>신용카드
						<input type="radio" name="radio" value="무통장입금" style="margin-left:30px;" />무통장입금
					</td>
				</tr>
			</table>
		</div>
		<br><br><br><br>
		<div>
			<input type="submit" id="btn_order" onclick="payment()" value="결제하기" />
		</div>
		<br><br><br><br>
	</div>
</div>
<%@include file="/WEB-INF/jsp/layout/user/userFooter.jsp" %>
</body>
<script>
$(document).ready(function(){
	var str="";

	str+="<tr>";
	str+="<td id='start' colspan='2'>배송지 설정을 선택하세요.</td>";
	str+="</tr>";
	
	$('#body').append(str);
})

function kakaomap() {
     //주소입력칸을 클릭하면
     //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address_kakao").value = data.address; // 주소 넣기
                document.querySelector("input[name=address_detail]").focus(); //상세입력 포커싱
            }
        }).open();
}

var address = '${address}';
var address_detail = '${address_detail}'
var name = '${name}';
var phone = '${phone}';

function funcAddress(type){
	var str = "";
	if(type == "e1"){
		$('#body').empty();
		str+="<tr>";
		str+="<td id='title'>이름 :</td>";
		str+="<td id='contents'>"+name+"</td>";
		str+="</tr>";
		str+="<tr>";
		str+="<td id='title'>전화번호 :</td>";
		str+="<td id='contents'>"+phone+"</td>";
		str+="</tr>";
		str+="<tr>";
		str+="<td id='title'>주소 :</td>";
		str+="<td id='contents'>" +address+ "</td>";
		str+="</tr>";
		str+="<tr>";
		str+="<td id='title'>상세주소 :</td>";
		str+="<td id='contents'>" +address_detail+"</td>";
		str+="</tr>";
		$('#body').append(str);
	}
	
	if(type == "e2"){
		$('#body').empty();
		
		str+="<tr>";
		str+="<td id='title'>이름 :</td>";
		str+="<td id='contents'>"+name+"</td>";
		str+="</tr>";
		str+="<tr>";
		str+="<td id='title'>전화번호 :</td>";
		str+="<td id='contents'>"+phone+"</td>";
		str+="</tr>";
		str+="<tr>";
		str+="<td id='title'>주소 :</td>";
		str+="<td id='contents'><input type='text' id='address_kakao' name='address' size=50 readonly onclick='kakaomap()' /></td>";
		str+="</tr>";
		str+="<tr>";
		str+="<td id='title'>상세주소 :</td>";
		str+="<td id='contents'><input type='text' id='address_detail' size=50 name='address_detail' /></td>";
		str+="</tr>";
		$('#body').append(str);
	}
}

function payment() {
	
	var goods_name = document.getElementById("price");
	var address = document.getElementById("address_kakao");
	var goods_name = $("input[name=radio]:checked").each(function(i))
	console.log(goods_name)
	console.log(address)
	
}
</script>
</html>