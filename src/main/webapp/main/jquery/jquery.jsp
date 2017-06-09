<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
	/* 	$((document).ready(function(){
			==============
	        	생략가능
			}
		));  */
	
		$(function(){
			//selector : $('태그명') $('ID') $('class명') $(내장객체)
			/*
				내장객체 ==> 계층형
				window
				|
				document
				|
				form, location, history.....
			*/
			
			$('.h1').css("color", "red");
			$('#h4').css("color", "yellow");
			$('h1').click(function(){//태그선택
				//var data=$(this).text("<a href=#>JQuery</a>"); //this를 쓰면 클릭한 자신꺼 가져온다.
				//var data=$(this).text(); //this를 쓰면 클릭한 자신꺼 가져온다.
				//변수가 있으면 getter
				//변수가 없으면 setter
				
				//var data=$(this).html(); //tag까지 가져온다.
				//alert(data);
				
				
			});
			
			$('h1').hover(function(){//마우스를 가져다 댓을때 실행된다.
				$(this).css("opacity", 0.3);
				
			},function(){//else
				$(this).css("opacity", 1);				
				
			});
		});//window.onload(); main에 해당 한개만 있어야 한다.
	</script>
</head>
<body>
	<h1 class="h1"><span>Hello Jquery1</span></h1>
	<h1 class="h1">Hello Jquery2</h1>
	<h1 class="h2">Hello Jquery3</h1>
	<h1 class="h3">Hello Jquery4</h1>
	<h1 id="h4">Hello Jquery5</h1>
</body>
</html>