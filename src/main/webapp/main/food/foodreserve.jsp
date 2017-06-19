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
		
		$('#rbtn').click(function(){
			var no=$('#r_name').attr("value");
			var day=$('#r_day').text();			
			var time=$('#r_time').text();			
			var inwon=$('#r_inwon').text();			
			var json={"house_no":no, "res_date":day, "res_time":time, "res_inwon":inwon};
			
			
			$.ajax({
				type:'post'
				, url:'reserve_insert.do'
				, data: json
				, success:function(response){
					alert(response);
					
					//location.href="mypage.do?id"+response;//session이용해서 보안강화
					location.href="mypage.do";
					
				}
				
			})
		})
		
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
			<table id="table_content" width="900" height="500">
				<tr>
					<td width="15%" height="400" valign="top">
						<table id="table_content" width="135">
							<tr>
								<th>맛집지역현황</th>
							</tr>
							<div id="gu_view"></div>
							
						</table>
					</td>
					<td width="35%" height="400" valign="top">
						<table id="table_content" width="315">
							<tr>
								<th>맛집목룍</th>
							</tr>
						</table>
						<div id="list_view"></div>						
					</td>
					<td width="30%" height="400" valign="top">
						<table id="table_content" width="270">
							<tr>
								<!-- <th id="r_time">예약일</th> 아이디가 중복되면 적용되지 않는다.-->
								<th>예약일</th>
							</tr>
						</table>
						<div id="reserve_date"></div>
					</td>
					<td width="20%" rowspan="2" valign="top">
						<table id="table_content" width="180">
							<tr>
								<th>예약정보</th>
							</tr>							
						</table>
						<table id="table_content">
							<tr>
								<td id="r_poster">
									<img src="#" width="160" height="120" id="rp" />
								</td>
							</tr>
							<tr>
								<td id="r_name" value=""><!-- db에 저장할때는 no이용. reserve_list.jsp에서  js로 입력해 줄것이다. -->
									업체명:
								</td>
							</tr>
							<tr>
								<td id="r_addr">
									주소:
								</td>
							</tr>
							<tr>
								<td id="r_day">
									예약일:
								</td>
							</tr>
							<tr>
								<td id="r_time">
									예약시간:
								</td>
							</tr>
							<tr>
								<td id="r_inwon">
									성인: ,아동:
								</td>
							</tr>
							<tr id="reserve_btn" style="display: none">
								<td align="center">
									<input type="button" value="예약하기" id="rbtn"/>
								</td>
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
						<div id="time_view"></div>
					</td>
					<td width="30%" height="100" valign="top">
						<table id="table_content" width="270">
							<tr>
								<th>인원정보</th>
							</tr>							
						</table>
						<div id="inwon_view"></div>
					</td>
				</tr>
				
			</table>
		</center>

		<div class="clr"></div>

	</div>
	<!--news area end-->
</body>
</html>