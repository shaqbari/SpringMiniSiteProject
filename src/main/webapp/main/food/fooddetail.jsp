<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style/table.css">
<link rel="stylesheet" href="style/style.css">
</head>
<body>

	<div id="news_area">
		<!--news area-->
		<center>
			<table id="table_content" width="700">
				<tr>
					<td width="40%" align="center" rowspan="5"><img
						src="${vo.poster }" width="280" height="300" /></td>
					<td colspan="2" align="center"><h2>${vo.name }&nbsp;&nbsp;${vo.score }</h2></td>
				</tr>
				<tr>
					<td width="10%" align="right">주소</td>
					<td width="50%" align="left">${vo.address }</td>
				</tr>
				<tr>
					<td width="10%" align="right">전화</td>
					<td width="50%" align="left">${vo.tel }</td>
				</tr>
				<tr>
					<td width="10%" align="right">음식종류</td>
					<td width="50%" align="left">${vo.type }</td>
				</tr>
				<tr>
					<td width="10%" align="right">가격대</td>
					<td width="50%" align="left">${vo.price }</td>
				</tr>


			</table>
			
			<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
			<script src="https://www.amcharts.com/lib/3/pie.js"></script>
			<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
			<div id="chartdiv"></div>
			<script src='https://code.jquery.com/jquery-1.11.2.min.js'></script>

			<script src="js/index.js"></script>

		</center>


		<!--### news area box start ###-->

		<div class="clr"></div>

	</div>
	<!--news area end-->
</body>
</html>