<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link href="css/user/join.css" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body style="margin-left:0; margin-right:0;">
<%@include file="/WEB-INF/jsp/layout/user/userHeader.jsp" %>
		<div class="joinform">
			<br><br><br>
			<h3>회원가입</h3>
			<br>
			<div>
				<span style="margin:0 630px 0 1200px; float:right; font-size:15px;">*는 필수입력사항입니다.</span>
			</div>
			<hr class="join_hr">
			<form name="joinform" action="/joinOk" method="post">
			<table style="margin:auto; font-family:noto sans">
				<tr>
					<th>아이디*</th>
					<td><input type="text" id="id" name="id" placeholder="아이디를 입력해주세요" onkeydown="javascript:funcidck()" onkeyup="this.value=this.value.replace(/[^a-zA-z0-9]/ig, '')" style="font-size:15px; width:345px; height:45px; padding: 0 19px;"></td>
					<td><input type="button" class="btn-check" onclick="javascript:checkID()" value="중복확인" />
				</tr>
				<tr>
					<th>비밀번호*</th>
					<td><input type="password" id="pw" name="pw" placeholder="비밀번호를 입력해주세요" onkeyup="this.value=this.value.replace(/[^a-zA-z0-9]/ig, '')" style="font-size:15px; width:345px; height:45px; padding: 0 19px;"></td>
				</tr>
				<tr>
					<th>비밀번호 재입력*</th>
					<td><input type="password" id="pw2" name="pw2" placeholder="비밀번호를 한번 더 입력해주세요" style="font-size:15px; width:345px; height:45px; padding: 0 19px;"></td>
				</tr>
				<tr>
					<th>이름*</th>
					<td><input type="text" id="name" name="name" placeholder="이름을 입력해주세요" style="font-size:15px; width:345px; height:45px; padding: 0 19px;"></td>
				</tr>
				<tr>
					<th>이메일*</th>
					<td><input type="text" id="email" name="email" placeholder="예: marketkurly@kurly.com" onkeydown="javascript:funcEmailck()" style="font-size:15px; width:345px; height:45px; padding: 0 19px;"></td>
					<td><input type="button" class="btn-check" onclick="javascript:checkEmail()" value="중복확인">
				</tr>
				<tr>
					<th>휴대폰*</th>
					<td><input type="text" id="phone" name="phone" placeholder="숫자만 입력해주세요( - 제외)" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="11" style="font-size:15px; width:345px; height:45px; padding: 0 19px;"></td>
				</tr>
				<tr>
					<th>주소*</th>
					<td><input type="text" id="address_kakao" name="address" readonly style="font-size:15px; width:345px; height:45px; padding: 0 19px;"/></td>
				</tr>
				<tr>
           			<th>상세 주소*</th>
            		<td><input type="text" name="address_detail" style="font-size:15px; width:345px; height:45px; padding: 0 19px;"/></td>
        		</tr>
			</table>
				<hr class="join_hr">
			<input type="button" value="가입하기" class="btn-join" onclick="javascript:join()"/>
			</form>
			<br>
			<br><br><br>
		</div>
<%@include file="/WEB-INF/jsp/layout/user/userFooter.jsp" %>
</body>
<script>
window.onload = function(){
    document.getElementById("address_kakao").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address_kakao").value = data.address; // 주소 넣기
                document.querySelector("input[name=address_detail]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}

var idck = 0;
var emailck = 0

function funcidck(){
	idck = 0;
}

function funcEmailck(){
	emailck = 0;
}

function checkID(){
	var id = $("#id").val();
	var idLength = id.length;
	
	if(id == ""){
		alert("아이디를 입력하세요!");
		return false;
	}
	if(idLength < 6){
		alert("최소 6자 이상 입력하세요!");
		return false;
	}
	if(idLength > 25){
		alert("25자 미만 입력하세요!");
		return false;
	}
	
	if(id != ""){
		$.ajax({
			type: "POST",
			url: "/checkID",
			data: {"id" : $("#id").val()},
			success: function(result)
			{
				if(result == 0){
					alert("사용 가능한 아이디입니다.");
					idck = 1;
				}else{
					alert("사용 불가능한 아이디입니다.");
				}
				console.log(idck);
			}
		})
	}
}

function checkEmail(){
	
	var email = $("#email").val();
	
	if(email.indexOf("@") == -1){
		alert("이메일 형식에 맞게 입력하세요!");
		return false;
	}
	
	if(email == ""){
		alert("이메일을 입력하세요!");
	}else{
		$.ajax({
			type: "POST",
			url: "/checkEmail",
			data: {"email" : $("#email").val()},
			success: function(result)
			{
				if(result == 0){
					alert("사용가능한 이메일입니다!");
					emailck = 1;
				}else{
					alert("사용 불가능한 이메일입니다!");
				}
			}
		})
	}
}

function join(){
	var form = document.joinform;
	var pw = $("#pw").val();
	var email = $("#email").val();
	pwLength = pw.length;
	
	if(form.id.value == ""){
		alert("아이디를 입력하세요!");
		form.id.focus();
		return false;
	}
	
	if(form.pw.value == ""){
		alert("비밀번호를 입력하세요!");
		form.pw.focus();
		return false;
	}
	
	if(pwLength < 6){
		alert("비밀번호를 6자리 이상 입력하세요!");
		form.pw.focus();
		return false;
	}
	
	if(pwLength > 20){
		alert("비밀번호를 20자리 미만 입력하세요!");
		form.pw.focus();
		return false;
	}
	
	if(form.pw2.value == ""){
		alert("비밀번호 재입력을 해주세요!");
		return false;
	}
	
	if(form.pw.value != form.pw2.value){
		alert("비밀번호가 틀립니다. 비밀번호를 확인해주세요!");
		return false;
	}
	
	if(form.name.value == ""){
		alert("이름을 입력하세요!");
		form.name.focus();
		return false;
	}
	
	if(form.email.value == ""){
		alert("이메일을 입력하세요!");
		form.email.focus();
		return false;
	}
	if(form.phone.value == ""){
		alert("휴대폰을 입력하세요!");
		form.phone.focus();
		return false;
	}
	
	if(form.address_kakao.value == ""){
		alert("주소를 입력하세요!");
		form.address_kakao.focus();
		return false;
	}
	
	if(form.address_detail.value == ""){
		alert("상세주소를 입력하세요!");
		form.address_detail.focus();
		return false;
	}
	if(idck == 0){
		alert("중복확인을 해주세요!");
		form.id.focus();
		return false;
	}
	
	if(emailck == 0){
		alert("이메일 중복확인을 해주세요!");
		form.email.focus();
		return false;
	}
	
	if(form.value != "" && idck == 1 && emailck == 1 && 6 <= pwLength <= 20){
		alert("회원가입이 완료되었습니다!");
	}
	
	form.submit();
}
</script>
</html>