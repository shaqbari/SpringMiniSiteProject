<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="style/table.css">
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
		$(function(){
			$('#delBtn').click(function(){
				var no=$('#no').attr("value");
				var page=$('#page').attr("value");
				var pwd=$('#pwd').val();
				
				//비밀번호 반드시 입력하게 한다.
				if (pwd.trim()=="") {
					$('#pwd').focus
					return;
					
				}
				
				var json={"no":no, "page":page, "pwd":pwd};
				
				$.ajax({
					type:"post"
					, url:"board_delete_ok.do"
					, data: json
					, success: function(response){
						
						if (response.trim()==1) {
														
							parent.location.href="board_list.do?page="+page;
							parent.Shadowbox.close();
							
						}else{
							$('#pwd').val("");
							$('#pwd').focus("");
							$('#result').html("<font color=red>비밀번호가 틀립니다.</font>");
							
						}
						
					}
					
				});
				
				
			});
			
			$('#canBtn').click(function(){				
				parent.Shadowbox.close();
			})
			
			
		});
	</script>
</head>
<body>
	<center>
		<div style="height: 30px"></div>
		<table id="table_content" width="250">
			<tr>
				<td align="left">
					<input type="hidden" name="no" value="${no}" id="no" />
					<input type="hidden" name="page" value="${page}" id="page"/>
					비밀번호<input type="password" name=pwd id="pwd" size="12" />
				</td>
			</tr>
			<tr>
				<td id="result"></td>
			</tr>
			<tr>
				<td align="center">
					<input type="button" value="삭제" id="delBtn" />
					<input type="button" value="취소" id="canBtn" />
				</td>
			</tr>
		</table>
	</center>
</body>
</html>