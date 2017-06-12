<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/table.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h3>게시판 목록</h3>
		<table id="table_content" width=700>
			<tr>
				<td><a href="board_insert.do">새글</a></td>				
			</tr>
		</table>
		<table id="table_content" width=700>
			<tr>
				<th width="10%">번호</th>
				<th width="45%">제목</th>
				<th width="15%">이름</th>
				<th width="20%">작성일</th>
				<th width="10%">조회수</th>				
			</tr>
			<c:forEach var="vo" items="${list}">
				<tr class="dataTr">
					<td width="10%" align=center>${vo.no}</td>
					<td width="45%" align=center>
						<a href="board_content.do?no=${vo.no }&page=${curpage}">${vo.subject }</a>
					</td>
					<td width="15%" align=center>${vo.name}</td>
					<td width="20%" align=center>
						<%-- ${vo.regdate} --%>
						<fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/>
					</td>
					<td width="10%" align=center>${vo.hit}</td>	
				</tr>
			</c:forEach>
		</table>
		<table>
			<tr>
				<td align="left">
					
				</td>
				<td align=right>
					
				</td>
			</tr>
		</table>
	</center>	
</body>
</html>