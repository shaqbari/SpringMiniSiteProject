<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../shadow/css/shadowbox.css" />
	<script src="../shadow/js/shadowbox.js"></script>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
		/* Shadowbox.init({
			players:["iframe"]			
		}); */
		Shadowbox.init({
			players:["iframe"]
		});
		$(function(){
			$('#btn').click(function(){
				//alert("xx");
				//window.open("temp_input.jsp", "ti", "width=300, height=300");
				Shadowbox.open({
					content:"temp_input.jsp"
					, player:"iframe"
					, title:"ID중복체크"
					, width:300
					, height:200
					
				});
			});		
		});
		
		
	</script>
</head>
<body>
	<input type="button" value="ID체크" id="btn"/>
</body>
</html>