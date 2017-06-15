<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/table.css" />
<script src="http://code.jquery.com/jquery.js"></script>
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
				<caption>${year }년 ${month }월</caption>
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
				<td><font color="${color }">${i }</font></td>
				
				<!-- 요일 초기화  -->
				<c:set var="week" value="${week+1 }"/>
				<c:if test="${week>6 }">						
					</tr>
					<tr>
					<c:set var="week" value="0"/>
				</c:if>
			</c:forEach>
			</table>			
			
			<tr>
		</div>
	</center>
</body>
</html>