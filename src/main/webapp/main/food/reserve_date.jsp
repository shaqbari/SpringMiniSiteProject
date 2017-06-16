<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="food/table.css" />
<script src="http://code.jquery.com/jquery.js"></script>
<script>
$(function(){
	$('.resday').click(function(){
		var day=$(this).text();
		var year=$(this).attr('val1');
		var month=$(this).attr('val2');
		
		$.ajax({
			type:'post'
			, url:'reserve_time.do'
			, data: {"day":day}
			, success: function(response){
				$('#time_view').html(response);
				
				//총예약정보에 입력
				$('#r_day').text("예약일 : "+year+"-"+month+"-"+day);
			}
			
		});
	});
	
});
</script>
</head>
<body bgcolor="black">
	<center>
		<div style="overflow-y:auto; height:330px "> <!-- style에서는 px를 써야 한다. -->
			<table id="table_content" width="180">
				<tr>
					<td>
						<select name="year" >
							<c:forEach var="i" begin="2010" end="2030">
								<c:if test="${i==year }">
									<option selected>${i}</option>
								</c:if>
								<c:if test="${i!=year }">
									<option>${i}</option>
								</c:if>
							</c:forEach>
						</select>년도&nbsp;
						<select name="year" >
							<c:forEach var="i" begin="1" end="12">
								<c:if test="${i==month }">
									<option selected>${i}</option>
								</c:if>
								<c:if test="${i!=month }">
									<option>${i}</option>
								</c:if>
							</c:forEach>
						</select>월
					</td>
				</tr>
			</table>
				<c:set var="color" value="white"/>
				<table id="table_content" width="180">
					<!-- 아래 value에 no가 이전ajax에서 들어온다. -->
					<caption id="cap_no" value="">${year }년 ${month }월</caption>
					<tr>
						<c:forEach var="i" begin="0" end="6">
							<c:if test="${i==0 }">
								<c:set var="color" value="red"/>
							</c:if>
							<c:if test="${i==6}">
								<c:set var="color" value="blue"/>
							</c:if>
							<c:if test="${i!=0 && i!=6 }">
								<c:set var="color" value="white"/>
							</c:if>
							<th><font color="${color }">${strWeek[i] }</font></th>
							
						</c:forEach>
					</tr>
					<c:forEach var="i" begin="1" end="${lastday }">
					<c:if test="${week==0 }">
								<c:set var="color" value="red"/>
							</c:if>
							<c:if test="${week==6}">
								<c:set var="color" value="blue"/>
							</c:if>
							<c:if test="${week!=0 && week!=6 }">
								<c:set var="color" value="white"/>
							</c:if>
					
					<c:if test="${i==1 }">
						<tr>
						<c:forEach var="j" begin="1" end="${week }">
							<td>&nbsp;</td>
						</c:forEach>
					</c:if>
					<td>
						<c:choose>
							<c:when test="${rd[i-1] && i>=today }">
								<!-- class resday css로 마우스 대면 변하게 바꾸었다. -->
								<span style="color: black; background-color:#ccffcc" class="resday"
								 val1="${year }" val2="${month }">
									${i }
								</span>
							</c:when>
							<c:otherwise>
								<span style="color: gray;">${i }</span>
							</c:otherwise>					
						</c:choose>
						<%-- <c:if test="${rd[i-1] && i>=today}"><!-- 예약날짜이고 오늘 이후라면 -->
							<span style="color: black; background-color:#ccffcc">${i }</span>
						</c:if>
						<c:if test="${i!=rd[i] }">
							<span style="color: gray;">${i }</span>
						</c:if> --%>
						
					</td>
					
					<!-- 요일 초기화  -->
					<c:set var="week" value="${week+1 }"/>
					<c:if test="${week>6 }">						
						</tr>
						<tr>
						<c:set var="week" value="0"/>
					</c:if>
					
				</c:forEach>
				</tr>
			</table>			
			
		</div>
	</center>
</body>
</html>