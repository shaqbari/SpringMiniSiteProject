<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="style/table.css">
	
	<script src="http://code.jquery.com/jquery.js"></script>
	<!-- 
		jquery : js라이브러리(DOMSCRIPT)
		======
		DOM : 태그를 제어하는 프로그램
		1)selector
	-->	
	<script>
		var fileIndex=0;
		$(function(){
			$('#addBtn').click(function(){
				$('#fileView').append(
					'<tr id=f'+fileIndex+'>'
					+'<td width=20%>파일'+(fileIndex+1)+'</td>'
					+'<td width=80%><input type=file name=upload['+fileIndex+'] size=20></td>'
					+'</tr>'
				);
				fileIndex++;
				
			});
			
			$('#removeBtn').click(function(){
				//alert('삭제눌렀어?');
				if (fileIndex>0) {
					$('#f'+(fileIndex-1)).remove();
					fileIndex--;					
				};
				
			});
			
		});
	</script>
</head>
<body>
	<center>
		<h3>수정하기</h3>
		<form method="post" action="board_update_ok.do"
		enctype="multipart/form-data"><!-- 파일올릴때 꼭 설정해 줘야 한다.
		또한 dispatcherServlet에서 id=multipartResolver로 대소문자포함해서 설정해야 하고
		 CommonMultipartResolver로 메모리 할당 해야 한다. -->
			<table id="table_content" width=600>
				<tr>
					<td width=15%>이름</td>
					<td width=85%>
						<input type="text" name="name" size=12 value="${vo.name }"/>
						<input type="hidden" name="no" value="${vo.no }"/>
						<input type="hidden" name="page" value="${page }"/>
					</td>				
				</tr>
				<tr>
					<td width=15%>제목</td>
					<td width=85%>
						<input type="text" name="subject" size=50 value="${vo.subject }" /><!--""안쓰면   -->
					</td>				
				</tr>
				<tr>
					<td width=15%>내용</td>
					<td width=85%>
						<textarea name="content" cols="55" rows="10">${vo.content }</textarea>
					</td>				
				</tr>
				<tr>
					<td width=15%>첨부파일</td>
					<td width=85%>
						<table border=0 width=450>
							<tr>
								<td align="right">
									<input type="button" id="addBtn" value="추가">
									<input type="button" id="removeBtn" value="삭제">
								</td>
							</tr>
						</table>
						<table border=0 width=425 id="fileView">
							<!-- js로 파일추가창 동적으로 추가 제거할 부분 -->
						</table>
					</td>				
				</tr>
				<tr>
					<td width=15%>비밀번호</td>
					<td width=85%>
						<input type="password" name="pwd" size=10 />
					</td>				
				</tr>
				<tr>
					<td colspan=2 align="center">				
						<input type="submit" value="수정" /><!-- html에서 공백이 들어가면 무조건 ""를 붙여야 한다. -->
						<input type="button" value="취소" onclick="javascript:history.back()"/>					
					</td>				
				</tr>
			</table>
		</form>		
	</center>
</body>
</html>