package com.sist.food.dao;

/**
 * @author user
 *	$.ajax({
 *		type:"POST"
 *		, url:""
 *		, data:{
 *			id:aa,
 *			pwd:1234
 *		}
 *		, success:function(){
 *			
 *
 *		}
 *		error:function(){
 *			404, 500
 *		}		
 *	})
 */
/* <div class="top_list_slide">
              <ul class="list-toplist-slider" style="width: 531px;">
                    <li>
                      <img class="center-croping"
                           src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/6mthe-5w3p5tg67w.jpg?fit=around|600:400&amp;crop=600:400;*,*&amp;output-format=jpg&amp;output-quality=80"
                           alt="6월의 핫한 카페 베스트 20곳 사진"
                           onerror="this.src='https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80'"
                      />


                      <a href="/top_lists/1571_cafe_june"
                         onclick="common_ga('PG_MAIN','CLICK_LIST');">
                        <figure class="ls-item">
                          <figcaption class="info">
                            <div class="info_inner_wrap">
                              <span class="title">6월의 핫한 카페 베스트 20곳</span>

                              <p class="desc">"안 가볼 수 없을걸요?"</p>
 * 
 * */
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


@Component
public class FoodManager {
	public List<CategoryVO> categoryAllData(){
		List<CategoryVO> list=new ArrayList<CategoryVO>();
			
		try {
			Document doc=Jsoup.connect("https://www.mangoplate.com/").get();
			Elements pelem=doc.select("ul.list-toplist-slider li img.center-croping");//poster
			Elements lelem=doc.select("ul.list-toplist-slider a ");//link
			Elements telem=doc.select("figure.ls-item div.info_inner_wrap span.title");//title
			Elements selem=doc.select("figure.ls-item div.info_inner_wrap p.desc");//subject
			
			/*
			 * 	attr()
			 *  text() 태그 사이 text
			 *  html() 태그 사이 모든
			 * */
			
		/*	for (Element pe : pelem) {
				String poster=pe.attr("src");
				System.out.println(poster);				
				
			}*/
			
			/*for (Element le : lelem) {
				String link=le.attr("href");
				System.out.println(link);
			}*/
			
			for(int i=0; i<9; i++){
				Element pe=pelem.get(i);
				String poster=pe.attr("src");
				
				Element le=lelem.get(i);
				String link=le.attr("href");				
				
				Element te=telem.get(i);
				Element se=selem.get(i);
				System.out.println(te.text()+" "+se.text());
				
				CategoryVO vo=new CategoryVO();
				vo.setCate_no(i+1);
				vo.setCategory(te.text());
				vo.setSubject(se.text());
				vo.setPoster(poster);
				vo.setLink(link);
				list.add(vo);
								
			}
			System.out.println("food갯수는 :"+list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		FoodManager fm=new FoodManager();
		fm.categoryAllData();
	}
}
