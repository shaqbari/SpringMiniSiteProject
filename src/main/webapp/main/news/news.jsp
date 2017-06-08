<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/table.css" media="screen" />
</head>
<body>
	<div id="news_area" align="center">
		<!--news area-->
		<table id="table_content" width=700>
			<tr>
				<td align="left">
					<form action="news.do" method="post">
					검색:<input type="text" name="data" size="15">
					<input type="submit" value="검색">
					</form>
				</td>
			</tr>
		
		</table>
		<table id="table_content" width=700>
			<c:forEach var="vo" items="${list }">
				<tr>
					<th>${vo.title }</th>
				</tr>
				<tr class="dataTr">
					<td><a href="${vo.link }">${vo.description }</a></td>					
				</tr>
				<tr class="dataTr">
					<a href="${vo.link }"><td>${vo.link }</td></a><!--td를 a태그로 감싸면 링크가 안걸린다.  -->
				</tr>
			</c:forEach>		
		</table>


		<div class="clr"></div>
	</div>
	<!--news area end-->
</body>
</html>