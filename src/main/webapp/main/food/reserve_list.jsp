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
		$('.img_click').click(function(){
			var no=$(this).attr('value');
			//attr(), text(), html(), val() => getter
			//attr("value", "aaa"), text(''), html(''), val('') => setter
			var d1=$(this).attr('data1');
			var d2=$(this).attr('data2');
			
			console.log(no);
			
			$.ajax({
				type:'post'
				, url:'reserve_date.do'
				, data:{"no":no}
				, success:function(response){
					$('#reserve_date').html(response);//이파일은 foodreserve에 속하기 때문에  foodreserve에 있는 id를 쓸수 있다.
					//소스보기에는 안보이지만 가상으로 기억하고 있다.
					
					//위에서 html을 붙인후에 거기에 no를 넘겨준다.
					$('#cap_no').attr("value", no);
					
					
					$('#rp').attr("src", d1);
					$('#r_name').text("업체명:"+d2);//:에서 자를것이므로 :다음에 공백있으면 안된다.
										
					$('#r_name').attr("value", no);//db저장용 업체번호
				}
				
			});
			
		});
		
	});
</script>
</head>
<body background="black">
	<center>
		<div style="overflow-y:auto; height:330px "> <!-- style에서는 px를 써야 한다. -->
			<table id="table_content" width="315">
				<c:forEach var="vo" items="#{list }">
					<tr >
						<td rowspan="3" align="center" width="30%">
							<a href="#" value="${vo.no }" class="img_click"
							 data1="food_image/big_${vo.poster }"
							 data2="${vo.name}">
								<img src="food_image/big_${vo.poster }" width="70" height="80" />
							</a>
						</td>
						<td colspan="2" align="center">${vo.name}</td>
					</tr>
					<tr >
						<td width="15%" align="right">주소</td>
						<td width="55%" align="left">${vo.addr }</td>
					</tr>
					<tr >
						<td>전화</td>
						<td>${vo.tel }</td>
					</tr>
					<tr>
						<td colspan="2"><hr/></td>
					</tr>
				</c:forEach>
			</table>
			
		</div>
	</center>
</body>
</html>