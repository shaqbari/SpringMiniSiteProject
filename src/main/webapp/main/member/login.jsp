<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${res=='NOID' }">
		<script>
			alert("아이디가 존재하지 않습니다.");
			history.back();
		</script>
	</c:when>
	<c:when test="${res=='NOPWD' }">
		<script>
			alert("비밀번호가 틀립니다.");
			history.back();
		</script>
	</c:when>
	<c:when test="${res=='OK' }">
		<c:redirect url="main.do"/>
	</c:when>

</c:choose>