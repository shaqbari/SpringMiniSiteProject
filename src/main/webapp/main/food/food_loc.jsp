<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="food/food.css">
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	$(function() {
		//지도에 마우스 가져다대면 색바뀌기		
		var no=0;
		$('.gu').hover(function(){
			no=$(this).attr("value");
			$('#gu'+no).attr("src", "food/map/gu_"+no+"_on.png");
			
		},function(){
			no=$(this).attr("value");
			$('#gu'+no).attr("src", "food/map/gu_"+no+"_off.png");
			
		});
	});
</script>
</head>
<body>
	<center>
		<div style="height: 550px"></div>
		<div id="a">
			<img src="food/map/1111.png" id="seoul_1">
			<c:forEach var="i" begin="1" end="25">
				<a href="#" class="gu" value="${i }">
					<img id="gu${i }" src="food/map/gu_${i }_off.png" border=0>
				</a>
			</c:forEach>
		</div>
	</center>
</body>
</html>