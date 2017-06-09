<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
		var i=0; 
		$(function(){
			$('#addBtn').click(function(){
				alert(i);
				$('#tab').append(
					'<tr id=f'+i+'>'
					+'<td width=20%>파일'+(i+1)+'</td>'
					+'<td width=80%>'
					+'<input type=file size=15>'
					+'<td>'
					+'<tr>'
				);//기존에 있는것에 붙인다. //html();싹지우고 다시넣는다.
				i++;
				
			});
			
			$('#removeBtn').click(function(){
				alert(i);
				if(i>0){
					$('#f'+(i-1)).remove();				
					i--;
				}
				
			});			
			
		});
	</script>
</head>
<body>
	<center>
		<table border="1" width="300">
			<tr>
				<td align="right">
					<input type="button" value="추가" id="addBtn" />
					<input type="button" value="제거" id="removeBtn" />
				</td>
			</tr>
		</table>
		<table border="1" width="300" id="tab">
			
		</table>
	</center>
</body>
</html>