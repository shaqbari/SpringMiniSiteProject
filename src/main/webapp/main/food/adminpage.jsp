<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/table.css">
<script src="http://code.jquery.com/jquery.js"></script>

</head>
<body>
	<center>
		<form method="post" action="adminpage_ok.do">
		<table>
			<tr>
				<td align="left"><input type="submit" value="승인완료" /></td>
			</tr>
		</table>
		<table id="table_content" width="800">
			<tr>
				<th></th>
				<th>예약번호</th>
				<th>업체명</th>
				<th>전화</th>
				<th>예약일</th>
				<th>예약시간</th>
				<th>인원</th>
				<th>예약상황</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>
						<c:if test="${vo.res_state=='n' }">
							<input type="checkbox" name="res_ck" value="${vo.res_no}" />
						</c:if>
					</td>
					<td>${vo.res_no }</td>
					<td>${vo.fvo.name }</td>
					<td>${vo.fvo.tel }</td>
					<td>${vo.res_date }</td>
					<td>${vo.res_time }</td>
					<td>${vo.res_inwon }</td>
					<td>${vo.res_state=='y'?"승인완료":"승인대기" }</td>
				</tr>
			</c:forEach>
		</table>
		</form>
	</center>
</body>
</html>