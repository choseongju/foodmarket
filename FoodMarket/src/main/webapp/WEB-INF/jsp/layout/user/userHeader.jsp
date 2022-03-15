<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>

<%
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓컬리 :: 내일의 장보기, 마켓컬리</title>
<link href="css/user/mainheader.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<div class="user_header">
		<div class="userMenu">
			<a href="#" style="font-size:13px;">상품배송안내</a>
				<% if(authentication == null || authentication.getPrincipal() == "anonymousUser") {%>
					<ul class="ul_userMenu">
						<li class="li_help"><a href="#" onclick="javascript:center()">고객센터</a></li>
						<li> | </li>
						<li class="li_login"><a href="/login">로그인</a></li>
						<li> | </li>
						<li class="li_join"><a href="/join">회원가입</a></li>
					</ul>
				<%} %>
				<sec:authorize access="isAuthenticated()">
					<ul class="ul_userMenu">
						<li class="li_help"><a href="#" onclick="javascript:center()">고객센터</a></li>
						<li> | </li>
						<li class="li_login"><a href="/logout">로그아웃</a></li>
						<li> | </li>
						<li class="li_join"><a href="/myPage">내 정보</a></li>
					</ul>
				</sec:authorize>
		</div>
		<h1>
			<a href="/"><img class="logo" src="img/user/logo.png"/></a>
		</h1>
		<div class="category">
			<ul class="ul_category">
				<li id="li_catecory">
					<div class="total_category">
						<i class="fas fa-align-justify" style="margin-right:10px;"></i>
						<button class="btn_totalCategory">전체카테고리</button>
						<div class="totalCategory_content">
							<a href="/vegatables" id="category_name"><img id="icon_category" src="img/user/category/vegetables.png"/>채소</a>
							<a href="#" id="category_name"><img id="icon_category" src="img/user/category/fish.png"/>수산 · 건어물 · 해산</a>
							<a href="#" id="category_name"><img id="icon_category" src="img/user/category/meat.png"/>정육 · 계란</a>
							<a href="#" id="category_name"><img id="icon_category" src="img/user/category/salad.png"/>샐러드 · 간편식</a>
							<a href="#" id="category_name"><img id="icon_category" src="img/user/category/drink.png"/>생수 · 음료 · 우유 · 커피</a>
							<a href="#" id="category_name"><img id="icon_category" src="img/user/category/cosmetic.png"/>스킨케어 · 메이크업</a>
							<a href="#" id="category_name"><img id="icon_category" src="img/user/category/snack.png"/>간식 · 과자 · 떡</a>
							<a href="#" id="category_name"><img id="icon_category" src="img/user/category/bakery.png"/>베이커리 · 치즈 · 델리</a>
							<a href="#" id="category_name"><img id="icon_category" src="img/user/category/fruit.png"/>과일 · 견과 · 쌀</a>
						</div>
					</div>
				</li>
				<li id="li_catecory">
					<a href="#">
						<span>신상품</span>
					</a>
				</li>
				<li id="li_catecory">
					<a href="#">
						<span>베스트</span>
					</a>
				</li>
				<li id="li_catecory">
					<a href="#">
						<span>알뜰쇼핑</span>
					</a>
				</li>
				<li id="li_catecory">
					<a href="#">
						<span>특가/혜택</span>
					</a>
				</li>
				<sec:authorize access="hasRole('ROLE_OWNER')">
				<li id="li_catecory">
					<a href="/owner/index">
						<span>상품 등록</span>
					</a>
				</li>
				</sec:authorize>
				<li id="li_catecory">
					<a id="btn_basket" onclick="basketPageGo()"><img id="shopping_icon" src="img/user/shopping.png" /></a>
				</li>
				<li id="li_catecory">
					<a id="btn_goLike" onclick="likePageGo()"><img id="like_icon" src="img/user/heartblack.png" /></a>
				</li>
			</ul>
			<form style="width:600px; margin-left:800px;"id="keyword" name="keyword" action="/search" method="GET">
				<input type="text" id="keywordText" name="keywordText" onkeyup="search()">
				<input type="image" id="search_icon" src="img/user/search.png" />
				<!-- <button id="btn_search"><img id="search_icon" src="img/user/search.png" /></button> -->
			</form>
		</div>
		<hr style="height:10px; border:0; box-shadow: 0 10px 10px -10px #bbb inset;">
	</div>
</body>
<script>
var auth = JSON.parse('${auth}');
var authority = auth[0].authority;

function center(){
	alert("아직 개발중입니다.");
}

function search(){
	
	var form = document.keywordform;
	var keyword = $('#keywordText').val();
	
	console.log(keyword.length)
	if(window.event.keyCode == 13 && keyword.length == 0){
		form.submit();		
	}
}

function basketPageGo(){
	if(authority == "ROLE_ANONYMOUS"){
		if(confirm("로그인이 필요한 서비스입니다. 로그인을 하시겠습니까?")){
			location.href="/login";
		}else{
			return false;
		}
	}else{
		location.href="/basketForm";
	}
}

function likePageGo(){
	if(authority == "ROLE_ANONYMOUS"){
		if(confirm("로그인이 필요한 서비스입니다. 로그인을 하시겠습니까?")){
			location.href="/login";
		}else{
			return false;
		}
	}else{
		location.href="/likeForm";
	}
}
</script>
</html>