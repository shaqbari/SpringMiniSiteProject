<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Company Name</title>
<link rel="stylesheet" type="text/css" href="style/style_sheet.css" media="screen" />
</head>

<body>

<div id="wrapper"><!--####  wrapper  ###-->

		<div id="top_strip"></div><!--top strip end-->
            
            
        <div id="header"><!--header--> 
        
       		         
       	  		<h1><a href="main.do">쌍용강북교육센터</a></h1>
                
                <h6>D강의장</h6>
            
        </div><!--header end-->

    
    		      <div id="menu-top"><!--menu top-->
    
                        <div id="top_nav"><!--top nav--> 
                            
                                <ul>
                                    
                                   <li><a href="main.do">Home</a></li>
                                   <li><a href="join.do">회원관리</a></li>
                                   <li><a href="loc.do">지역별 맛집</a></li>
                                   <li><a href="best.do">베스트 맛집</a></li>
                                   <li><a href="board_list.do">커뮤니티</a></li>
                                   <li><a href="news.do">뉴스검색</a></li>
                                 </ul>
                                 
                           </div><!--top nav end--> 
                           
                           
                          <!--  <div id="search_area">search box 
                           
                                  <form action="" method="get" id="form_search">
                                  
                                        <input name="input" type="text" id="search_box"/>
                                        
                                        <input name="" type="image" id="search_btn"  value=""/>
                                  
                                  
                                  </form>
                          
                           </div>search box end  -->
                           
            
				</div><!--menu top end-->   
        
        
        <div id="banner"><!--banner-->
        
        		 <img src="images/header1.jpg" alt="" width="940" height="280" />
        
  		</div><!--banner end-->
        
        
        
       
            
            
            <!-- contentarea -->
        	<jsp:include page="${main_jsp}"></jsp:include><!--page가 null이면 index.html이 들어가네  -->
        
       
        
        
                  <div id="footer"><!--footer-->
        
        				<div id="footer_nav">
                
                                <ul>                   
                                   <li><a href="main.do">Home</a></li>
                                   <li><a href="join.do">회원관리</a></li>
                                   <li><a href="loc.do">지역별 맛집</a></li>
                                   <li><a href="best.do">베스트 맛집</a></li>
                                   <li><a href="board.do">커뮤니티</a></li>
                                   <li><a href="news.do">뉴스검색</a></li>                 
                                </ul>
                
                		</div>
                        
                                                 
        
       			 </div><!--footer end-->

        
        

<div class="clr"></div>
</div><!--####  wrapper  ###-->

</body>
</html>
    