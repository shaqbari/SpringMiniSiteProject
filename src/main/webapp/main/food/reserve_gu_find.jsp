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
		$('.res_gu').click(function(){
			var kind=$(this).attr('val');
			var gu=$(this).text();
			//attr(), text(), html(), val() => getter
			//attr("value", "aaa"), text(''), html(''), val('') => setter
			
			$.ajax({
				type:'post'
				, url:'reserve_list.do'
				, data:{"kind":kind, "addr":gu}
				, success:function(response){
					$('#list_view').html(response);//이파일은 foodreserve에 속하기 때문에  foodreserve에 있는 id를 쓸수 있다.
					//소스보기에는 안보이지만 가상으로 기억하고 있다.
					
					$('#r_addr').text('주소 : '+gu);
				}
				
			});
			
		});
		
	});
</script>
</head>
<body>
	<center>
		<div style="overflow-y:auto; height:330px "> <!-- style에서는 px를 써야 한다. -->
			<table id="table_content" width="135">
				<c:forEach var="gu" items="${list }">
					<tr class="dataTr">
						<td><span class="res_gu" val="${kind}">${gu }</span></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</center>
</body>
</html>