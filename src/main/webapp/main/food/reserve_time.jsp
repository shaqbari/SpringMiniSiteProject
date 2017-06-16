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
	$('.res_time').click(function(){
		var time=$(this).text();
		
		$.ajax({
			type:'post'
			, url:'reserve_inwon.do'
			, success:function(response){
				$('#inwon_view').html(response);
				
				$('#r_time').text("예약시간 : "+time);
				
			}
			
		});
		
	});
	
});
</script>
</head>
<body>
	<center>
		<table id="table_content" width="450">
			<tr class="dataTr">
				<c:forEach var="time" items="${list }">
					<td><span class="res_time">${time }</span></td>
				</c:forEach>
			</tr>
		</table>
	</center>
</body>
</html>