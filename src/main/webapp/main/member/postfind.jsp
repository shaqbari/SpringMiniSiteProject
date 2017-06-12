<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="style/table.css" />
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
		/* 예약할때 많이 나온다. */
		$(function(){
			$('#findBtn').click(function(){
				var dong=$('#dong').val();
				if (dong.trim()=="") {
					alert("동/읍/면을 입력하세요");
					return;
				}
				
				$.ajax({
					type:'POST'
					, url:'postfind_result.do'
					, data:{'dong':dong}
					, success:function(response){
						$('#post_view').html(response);						
					}					
				});
				
			});
			
		});
	</script>
</head>
<body>
	<div style="height: 30px"></div>
	<center>
		<table id="table_content" width="430">
			<tr>
				<td>
					입력:<input type=text name=dong size=12 id="dong">
					<input type=button value="검색" id="findBtn" ><!-- 여기서 ajax를 써야 창이 안닫힌다. 한번더 ~.do로가면 창이 닫혀버린다.-->
				</td>
			</tr>			
		</table>
		<div id="post_view" >
			<!-- 주소검색결과가 들어갈 부분 -->
		
		</div>
		
	</center>
</body>
</html>