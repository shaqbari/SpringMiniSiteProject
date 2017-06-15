<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>

<head>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="js/js/jquery.easing.1.3.js"></script>

<script src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	google.charts.load("current", {packages:["corechart"]});
	google.charts.setOnLoadCallback(drawChart);
	function drawChart() {
	  var data = google.visualization.arrayToDataTable([
	    ['음식점', '별점 현황'],
	    <c:forEach var="vo" items="${list}">
	    ['<c:out value="${vo.name}"/>',   <c:out value="${vo.score}"/> ],
	 	</c:forEach>
	  ]);

	  var options = {
	    title: '음식점별 별점 현황',
	    is3D:true
	  };
	
	  var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
	  chart.draw(data, options);
	}



$(document).ready(function() {

	//Custom settings
	var style_in = 'easeOutBounce';
	var style_out = 'jswing';
	var speed_in = 1000;
	var speed_out = 300;


	//Calculation for corners
	var neg = Math.round($('.qitem').width() / 2) * (-1);
	var pos = neg * (-1);
	var out = pos * 2;
	
	var wneg = Math.round($('.qitem').width() / 2) * (-1);
	var wpos = wneg * (-1);
	
	var hneg = Math.round($('.qitem').height() / 2) * (-1);
	var hpos = hneg * (-1);
	
	$('.qitem').each(function () {
	
		url = $(this).find('a').attr('href');
		img = $(this).find('img').attr('src');
		alt = $(this).find('img').attr('img');
		
		$('img', this).remove();
		$(this).append('<div class="topLeft"></div><div class="topRight"></div><div class="bottomLeft"></div><div class="bottomRight"></div>');
		$(this).children('div').css('background-image','url('+ img + ')');
		$(this).children('div').css('background-size','170px 200px');

		$(this).find('div.topLeft').css({top:0, left:0, width:wpos , height:hpos});	
		$(this).find('div.topRight').css({top:0, left:wpos, width:wpos , height:hpos});	
		$(this).find('div.bottomLeft').css({bottom:0, left:0, width:wpos , height:hpos});	
		$(this).find('div.bottomRight').css({bottom:0, left:wpos, width:wpos , height:hpos});	

	}).hover(function () {
	
		$(this).find('div.topLeft').stop(false, true).animate({top:wneg, left:hneg}, {duration:speed_out, easing:style_out});	
		$(this).find('div.topRight').stop(false, true).animate({top:wneg, left:out}, {duration:speed_out, easing:style_out});	
		$(this).find('div.bottomLeft').stop(false, true).animate({bottom:wneg, left:hneg}, {duration:speed_out, easing:style_out});	
		$(this).find('div.bottomRight').stop(false, true).animate({bottom:wneg, left:out}, {duration:speed_out, easing:style_out});	
				
	},
	
	function () {

		$(this).find('div.topLeft').stop(false, true).animate({top:0, left:0}, {duration:speed_in, easing:style_in});	
		$(this).find('div.topRight').stop(false, true).animate({top:0, left:wpos}, {duration:speed_in, easing:style_in});	
		$(this).find('div.bottomLeft').stop(false, true).animate({bottom:0, left:0}, {duration:speed_in, easing:style_in});	
		$(this).find('div.bottomRight').stop(false, true).animate({bottom:0, left:wpos}, {duration:speed_in, easing:style_in});	
	
	}).click (function () {
		window.location = $(this).find('a').attr('href');	
	});	

});
</script>

<style>
	.qitem {
		width:170px;
		height:200px;	
		border:2px solid #222;	
		margin:5px 5px 5px 0;
		background: url('images/bg.gif') no-repeat; 
		background-size:cover;
		
		/* required to hide the image after resized */
		overflow:hidden;
		
		/* for child absolute position */
		position:relative;
		
		/* display div in line */
		float:left;
		cursor:hand; cursor:pointer;
	}

	.qitem img {
		border:0;
	
		/* allow javascript moves the img position*/
		position:absolute;
		z-index:200;
	}

	.qitem .caption {
		position:absolute;
		z-index:0;	
		color:#ccc;
		display:block;
		height: 100%;
		line-height: 22px;
		width: 100%;
		text-align: center;
		vertical-align: middle;
	}

	.qitem .caption h4 {
		font-size:13px;
		padding:10px 5px 0 8px;
		margin:0;
		color:#369ead;
	}

	.qitem .caption p {
		font-size:10px;	
		padding:3px 5px 0 8px;
		margin:0;
	}


	/* Setting for corners */

	.topLeft, .topRight, .bottomLeft, .bottomRight {
		position:absolute;
		background-repeat: no-repeat; 
		float:left;
	}
	
	.topLeft {
		background-position: top left; 	
	} 
	
	.topRight {
		background-position: top right; 
	} 
	
	.bottomLeft {
		background-position: bottom left; 
	} 
	
	.bottomRight {
		background-position: bottom right; 
	}
	
	.clear {
		clear:both;	
	}
</style>
</head>

<c:set var="i" value="1"/>
<div style="text-align: center">
  <h1>${title }</h1>
</div>
<div style="margin-left: 30px">
<c:forEach var="hvo" items="${list }" varStatus="status">	
	<div class="qitem">
		<%--
		 <a href="fooddetail.do?link=${hvo.link }&poster=${hvo.poster}"><img src="${hvo.poster }" alt="${hvo.name }" title="" width="170" height="200"/></a>
		 poster에 http가 붙어있고, 길이가 길고 *.*같은 특수문자가 있어 안넘어 간다.
		 --%>
		<a href="fooddetail.do?link=${hvo.link }"><img src="${hvo.poster }" alt="${hvo.name }" title="" width="170" height="200"/></a>
		<span class="caption">
			<h4>${hvo.name } (${hvo.score })</h4>
			<p>${hvo.address }</p>
		</span>
	</div>
	
	<c:if test="${i%5==0}">
		<div class="clear"></div>
	</c:if>
	
	<c:set var="i" value="${i+1 }"/>	
</c:forEach>

</div>
<div id="donutchart" style="width: 900px; margin-left:30px; height: 500px;"></div>