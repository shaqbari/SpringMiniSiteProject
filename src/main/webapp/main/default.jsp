<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
        <div id="news_area"><!--news area-->
        
        	<!--### news area box start ###-->
          	
            <c:forEach var="vo" items="${list }">
            <div class="news_area1">
            
                    <h2>${vo.category } </h2>
                    <span style="font-size: 9pt">${vo.subject }</h3>
                    <a href="https://www.mangoplate.com${vo.link }"><img src="${vo.poster }" alt="" width=275 height=125/></a>
            		 
            
            
            </div>
            </c:forEach>
            
            
             <!--### news area box start ###-->
            
            <div class="clr"></div>
                           
        </div><!--news area end-->
</body>
</html>