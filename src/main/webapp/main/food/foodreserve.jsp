<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="style/table.css" />
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	$(function(){
		$('#select_image img').click(function(){
			var kind=$(this).attr('value');
			//alert(kind); js는 에러찾기 힘드니깐 자주 test해두자 
			
			$.ajax({
				type:'post'
				, url:'reserve_gu_find.do'
				, data: {"kind":kind}
				, success:function(response){
					$('#gu_view').html(response);
					
					
				}
				
			})
		});
		
	});
</script>
<body>

	<div id="news_area">
		<!--news area-->
		<center>
			<table id="table_content" width=900 height="100">
				<tr>
					<td align="left">
						<!--
							$('img')
							$('#select_image img')
						 -->
						<div id="select_image">
							<img src="food_image/한식.jpg" width="150" height="100" value="한식"/>
							<img src="food_image/중식.jpg" width="150" height="100" value="중식"/>
							<img src="food_image/양식.jpg" width="150" height="100" value="양식"/>
							<img src="food_image/일식.jpg" width="150" height="100" value="일식"/>
						</div>
					</td>
				</tr>
			</table>
			<table id="tabale_content" width="900" height="500">
				<tr>
					<td width="15%" height="400" valign="top">
						<table id="table_content" width="135">
							<tr>
								<th>맛집지역현황</th>
							</tr>
							<div id="gu_view">
								
							</div>
							
						</table>
					</td>
					<td width="35%" height="400" valign="top">
						<table id="table_content" width="315">
							<tr>
								<th>맛집목룍</th>
							</tr>
							<div id="list_view">
							
							</div>						
						</table>
					</td>
					<td width="30%" height="400" valign="top">
						<table id="table_content" width="270">
							<tr>
								<th>예약일</th>
							</tr>
							
						</table>
					</td>
					<td width="20%" rowspan="2" valign="top">
					<table id="table_content" width="180">
							<tr>
								<th>예약정보</th>
							</tr>
							
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="100" valign="top">
						<table id="table_content" width="450">
							<tr>
								<th>시간정보</th>
							</tr>
							
						</table>
					</td>
					<td width="30%" height="100" valign="top">
						<table id="table_content" width="270">
							<tr>
								<th>인원정보</th>
							</tr>
							
						</table>
					</td>
				</tr>
				
			</table>
		</center>

		<div class="clr"></div>

	</div>
	<!--news area end-->
</body>
</html>