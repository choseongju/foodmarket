<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리 페이지</title>
<link href="/css/owner/styles.css" rel="stylesheet" />
<link href="/css/owner/goodsEnrollment.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/layout/owner/ownerHeader.jsp" %>
<%@ include file="/WEB-INF/jsp/layout/owner/ownerSidebar.jsp" %>
<style>
td{
	margin-top:12px;
}
</style>
<div id="layoutSidenav_content">
	<main>
		<h1 style="margin:20px 20px 20px 20px;">상품 등록</h1>
			<form name="goodsEnrollmentForm" action="/owner/enrollment" enctype="multipart/form-data" method="post">
			<table id="table_enrollment">
				<tr>
					<td style="float:right;">전체 카테고리 : </td>
					<td>
						<select id="goods_category" name="goods_category" onchange="selectCategory()">
							<option value=" ">--카테고리--</option>
						</select>
					</td>
					<td rowspan=4 style="padding:0 0 0 70px;">
						<img id="file" name="file" style="width:500px; height:500px;"/>
					</td>
				</tr>
				<tr>
					<td style="float:right;">종 류 : </td>
					<td>
						<select id="goods_kind" name="goods_kind">
							<option value=" ">--종류--</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="float:right;">물품명 : </td>
					<td><input type="text" id="goods_name" name="goods_name"></td>
				</tr>
				<tr>
					<td style="float:right;">금액 : </td>
					<td><input type="text" id="goods_price" name="goods_price"></td>
				</tr>
				<tr>
					<td style="float:right;">수량 : </td>
					<td><input type="number" id="goods_quantity" name="goods_quantity">
					<td style="text-align:center; padding:0 0 0 130px;"><input type="file" id="btn_file" accept="image/*" name="file" onchange="setThumbnail(event);"></td>
				</tr>
				<tr>
					<td style="float:right;">내용 : </td>
					<td><textarea id="goods_explain" name="goods_explain" cols="70" rows="6" style="font-size:15px;"></textarea></td>
				</tr>
				<tr>
					<td colspan=3 style="text-align:center;"><input type="button" value="등록" onclick="goodsEnrollment()"></td>
				</tr>
			</table>
		</form>
	</main>
<%@ include file="/WEB-INF/jsp/layout/owner/ownerFooter.jsp" %>
</body>
<script>
var categoryList = JSON.parse('${categoryList}');

window.onload = function() {
	for(var i=0; i<categoryList.length; i++){
		$("select[name='goods_category']").append("<option value=" +categoryList[i].categoryName +">" + categoryList[i].categoryName + "</option>");
	}
}

function selectCategory(){
	
	if($("#goods_category").val() == " "){
		$('#goods_kind').empty();
		$("select[name='goods_kind']").append("<option value=' '>---종류---</option>");
	}else{
		$.ajax({
			type: "POST",
			url : "/owner/selectCategory",
			data: {"category" : $("#goods_category").val()},
			success: function(result)
			{
			
				$('#goods_kind').empty();
				for(var i=0; i<result.length; i++){
					console.log(result[i].kindName)
					$("select[name='goods_kind']").append("<option value=" +result[i].kindName + ">" + result[i].kindName + "</option>");
				}
			}
		})
	}
}

// 상품 등록 이미지 썸네일
document.getElementById("btn_file").onchange = function () {
    var reader = new FileReader();

    reader.onload = function (e) {
        // get loaded data and render thumbnail.
        document.getElementById("file").src = e.target.result;
    };

    // read the image file as a data URL.
    reader.readAsDataURL(this.files[0]);
};

function goodsEnrollment(){
	var form = document.goodsEnrollmentForm;
	
	if($("#goods_category").val() == " "){
		alert("카테고리를 선택하세요!");
		return false;
	}
	
	if($("#goods_kind").val() == " "){
		alert("종류를 선택하세요!");
		return false;
	}
	
	if(form.goods_name.value == ""){
		alert("물품명을 입력해주세요!");
		return false;
	}
	
	if(form.goods_price.value == ""){
		alert("가격을 입력해주세요!");
		return false;
	}
	
	if(form.goods_quantity.value == ""){
		alert("수량을 입력해주세요!");
		return false;
	}
	
	if(form.goods_explain.value == ""){
		alert("상품 내용을 입력해주세요!");
		return false;
	}
	
	if(form.value != ""){
		alert("상품 등록이 되었습니다!");
		location.href="/owner/index";
	}
	form.submit();
}
</script>
</html>