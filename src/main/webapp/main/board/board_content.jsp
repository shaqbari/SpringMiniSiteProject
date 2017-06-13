<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="style/table.css">
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
		var i=0;
		var u=0;
		
		$(function(){
			$('.modify').click(function(){
				var no=$(this).attr("value");	
				if (u==0) {
					$('#u'+no).show();
					u=1;
					
				}else{
					$('#u'+no).hide();
					u=0;
					
				}
			});	
			
			$('.insert').click(function(){
				var no=$(this).attr("value");	
				if (i==0) {
					$('#i'+no).show();
					i=1;
					
				}else{
					$('#i'+no).hide();
					i=0;
					
				}
				
			});			
		});
		
	</script>
</head>
<body>
	<center>
		<h3>내용보기</h3>
		<table id="table_content" width="500">
			<tr>
				<th width="20%">번호</th>
				<td width="30%" align="center">${vo.no }</td>
				<th width="20%">작성일</th>
				<td width="30%" align="center">
					<%-- ${vo.regdate } regdate를 Date로 받아야 format할수 있다.--%>
					<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
				</td>				
			</tr>
			<tr>
				<th width="20%">이름</th>
				<td width="30%" align="center">${vo.name }</td>
				<th width="20%">조회수</th>
				<td width="30%" align="center">${vo.hit }</td>
			</tr>
			
			<!-- 첨부파일이 있을때만 출력 -->
			<c:if test="${vo.filecount>0}">
			<tr>
				<th width="20%">첨부파일</th>
				<td colspan="3" align="left">
					<c:forEach var="fvo" items="${vo.fileList }">
						<a href="board_download.do?fn=${fvo.filename }">${fvo.filename }</a>
						<!-- get방식이므로 한글처리를server.xml에서 처리해야 한다. filter는 post만 작용한다. -->
						(${fvo.filesize }Bytes)<br>
					</c:forEach>
				</td>				
			</tr>
			</c:if>
			
			<tr>
				<th width="20%">제목</th>
				<td colspan="3" align="left">${vo.subject }</td>				
			</tr>
			<tr>				
				<td colspan="4" align="left" valign="top" height="100">
					<pre>${vo.content }</pre><!-- pre를 붙여야 여러줄로 출력(enter가 먹힌다.)된다. -->
				</td>					
			</tr>
		</table>
		<table id="table_content" width="500">
			<tr>
				<td align="right">
					<a href="board_update.do?no=${vo.no }&page=${page}">수정</a>&nbsp;
					<a href="#">삭제</a>&nbsp;
					<a href="board_list.do?page=${page }">목록</a>
				</td>
			</tr>
		</table>			
		<div id="reply_view">
			<!--  회원가입 처리후 댓글 처리 -->
			<table id="table_content" width=500>
				<c:forEach var="rvo" items="${rList }">
					<tr>
						<td align="left" width="70%">
							<c:if test="${rvo.group_tab>0 }"><!-- 댓글달렸을때 앞에서 부터 들여쓰기 -->
								<c:forEach var="i" begin="1" end="${rvo.group_tab}">
									&nbsp;&nbsp;
								</c:forEach>
								<img src="images/icon_reply.gif"/>
							</c:if>
							${rvo.name }&nbsp;(${rvo.strDay }) <br>
							
							<c:if test="${rvo.group_tab>0 }"><!-- 댓글달렸을때 앞에서 부터 들여쓰기 -->
								<c:forEach var="i" begin="1" end="${rvo.group_tab}">
									&nbsp;&nbsp;
								</c:forEach>
							</c:if>
							${rvo.msg }
							
						</td>
						<td align="right" width="30%">
							<c:if test="${sessionScope.id==rvo.id }"><!-- 본인일때만 수정삭제 가증하게 한다. -->
								<a href="#" class="modify" value="${rvo.no }">└수정</a>&nbsp;└삭제
							</c:if> <!-- a태그에는 value태그가 없지만 html에서는 마음대로 태그를 만들어낼 수 있다. -->
							<a href="#" class="insert" value="${rvo.no }">└댓글</a>
						</td>
					</tr>
					<tr id="i${rvo.no}" style="display: none">
						<td colspan="2" >
							<div style="float: left; height: 40px">
								<textarea rows="3" cols="60" name="msg"></textarea>
							</div>
							<div>
								<input type="button" value="댓글쓰기" style="height: 50px" />
							</div>
						</td>
					</tr>
					<tr id="u${rvo.no}" style="display: none">
						<td colspan="2" >
							<div style="float: left; height: 40px">
								<textarea rows="3" cols="60" name="msg"></textarea>
							</div>
							<div>
								<input type="button" value="수정하기" style="height: 50px" />
							</div>
						</td>
					</tr>
				</c:forEach>					
				<c:if test="${sessionScope.id!=null }">
					<tr>
						<td colspan="2">
							<div style="float: left; height: 40px">
								<textarea rows="3" cols="60" name="msg"></textarea>
							</div>
							<div>
								<input type="button" value="댓글쓰기" style="height: 50px" />
							</div>
						</td>
					</tr>
				</c:if>
			</table>				
		</div>				
	</center>
</body>
</html>






