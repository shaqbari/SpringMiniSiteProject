<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="style/table.css" />
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
		$(function(){
			$('#idBtn').click(function(){
				var id=$('#id').val();
				if(id.trim()==""){//공백제거
					//alert("ID를 입력하세요");
					$('#id').focus();//입력안하면 진행못하고 포커스가 가게한다.
					return;				
				}
				
				/*
				HttpRequest ==> req
				req.open(Method, URL, true); //true면 비동기적
				req.onreadystatechanged=callback;
				req.send("id="+id);
				
				function callback(){
					if(req.readyState==4 && req.status==200){
						//var dir=document.getElementById("");
						//dir.innerHTML=req.responseText;
						
					}
					
				}
				*/
				$.ajax({
					type:"POST"
					, url:"idcheck_result.do"
					, data:{"id": id}
					, success:function(response){					
						//var dir=document.getElementById("");
						//dir.innerHTML=req.responseText;
						$('#id_view').html(response);
						
					}			
					//, error:function(){} 404파일명x, 500소스에러, 412한글변환코드에러
				});
			});
			
			
			
			$('#okBtn').click(function(){
				parent.join_frm.id.value=$('#id').val();
				parent.Shadowbox.close();
				
			});	
			
		});
	</script>
</head>
<body>
	<center>
		<div style="height: 30px"></div>
		<table id="table_content" width=300 >
			<tr>
				<td align="left">
					ID: <input type="text" name="id" id="id" size="12" />
					<input type="button" value="아이디체크" id="idBtn"/>
				</td>
			</tr>
		</table>
		<div id="id_view"></div>
	</center>
</body>
</html>