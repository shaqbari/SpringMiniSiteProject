<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/table.css" />
<script src="http://code.jquery.com/jquery.js"></script><!-- 다른라이브러리 쓰면 버전 충돌날수도 있으니 유의 --><!-- 같은함수이름 쓰면 맨 마지막거로 오버라이딩 된다. -->
<script>
$(function(){
	$('#inwonBtn').click(function(){
		var s=$('#s').val();
		var b=$('#b').val();
		
		$('#r_inwon').html("성인 : "+s+"명, 아동 : "+b+"명");
		$('#reserve_btn').show();
		
	});
	
})
//카프카 센서정보 모아줌, 데이터 유실방지, 
</script>
</head>
<body>
	<center>
		<table id="table_content" width="135">
			<tr class="dataTr">
				<td>성인:
					<select name="s" id="s">
						<c:forEach var="i" begin="1" end="10">
							<option value="${i }">${i}명</option>
						</c:forEach>						
					</select>
				</td>				
				<td>아동:
					<select name="b" id="b">
						<c:forEach var="i" begin="1" end="10">
							<option value="${i }">${i}명</option>
						</c:forEach>						
					</select>
				</td>				
				<td align="center"><input type="button" id="inwonBtn" value="확인" /></td>
				
			</tr>
		</table>
	</center>
</body>
</html>