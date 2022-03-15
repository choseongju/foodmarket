<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/user/mainfooter.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<hr class="footer-end">
<div class="footer">
	<div class="footer-body">
		<div class="footer-left">
			<h1 class="center">고객행복센터</h1>
			<div class="left-first">
				<h2>
					<span class="tel">1577-1577</span>
				</h2>
				<dl class="tel-list">
					<dt>365고객센터</dt>
					<dt>오전 7시 - 오후 7시</dt>
				</dl>
			</div>
			<div class="left-second">
				<button id="btn-inquiry" onclick="javascript:pageGO()">1:1문의</button>
				<dl class="tel-list2">
					<dt>24시간 접수 가능</dt>
					<dt>고객센터 운영시간에 순차적으로 답변해드리겠습니다.</dt>
				</dl>
			</div>
		</div>
		<div class="footer-right">
			<div class="footer-right-body">
				<div class="right-first">
				<br><br><br>
					<dl class="">
						<dt>법인명(상호): xxxx xx | 사업자등록번호 : 000-00-0000 </dt>
						<dt>주소 : 서울특별시 xx구 xxxx로 000, 0층(xx동) | 대표이사 : xxx </dt>
						<dt>팩스 : 070 - xxxx - xxxx | 이메일 : xxxx@naver.com</dt>
						<dt>입점문의: <a id="a_inquiry" href="#">입점문의하기</a> | 제휴문의 : xxxx@naver.com</dt>
					</dl>
					<br>
					<dl>
						<dt style="font-size:8px;"> @copyright 2021 foodmarket </dt>
					</dl>
				</div>
			</div>
		</div>
	</div>
</div>
<hr class="footer-end">
</body>
<script>

$(document).ready(function(){
	auth();
});

function auth(){
	$.ajax({
		type : "POST",
		url : "/memberAuth",
		async : false,
		success : function(result)
		{
			console.log(result.auth)

		},error:function(request,status,error)
		{
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	})
}

function pageGO(){
	var auth = JSON.parse('${auth}');
	var authority = auth[0].authority;
	
	if(authority == "ROLE_ANONYMOUS"){
		if(confirm("로그인이 필요한 서비스입니다. 로그인을 하시겠습니까?")){
			location.href= "/login";
		}else{
			return false;
		}
	}else{
		location.href="/inquiry";
	}
}
</script>
</html>