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
                           alt="6���� ���� ī�� ����Ʈ 20�� ����"
                           onerror="this.src='https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80'"
                      />


                      <a href="/top_lists/1571_cafe_june"
                         onclick="common_ga('PG_MAIN','CLICK_LIST');">
                        <figure class="ls-item">
                          <figcaption class="info">
                            <div class="info_inner_wrap">
                              <span class="title">6���� ���� ī�� ����Ʈ 20��</span>

                              <p class="desc">"�� ���� �� �����ɿ�?"</p>
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
			 *  text() �±� ���� text
			 *  html() �±� ���� ���
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
			System.out.println("food������ :"+list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/*
	 *   <li class="toplist_list">
	              <div class="with-review">
	                <figure class="restaurant-item">
	                  <a href="/restaurants/mjR_ZoQd1e"
                       onclick="common_ga('PG_TOPLIST', 'CLICK_PICTURE_BIG')">
                      <div class="thumb">
                        <img class="center-croping"
                             src="https://mp-seoul-image-production-s3.mangoplate.com/added_restaurants/277947_1471513817405142.jpg?fit=around|738:738&amp;crop=738:738;*,*&amp;output-format=jpg&amp;output-quality=80"
                             alt="�ͳ������� ���� - ����� ������ ��ġ�� 896-5"
                             onerror="this.src='https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80'"
                        />
                      </div>
	                  </a>

	                  <figcaption>
	                    <div class="info">
	                      <!--<button class="btn-type-icon share-big" ng-click="open_share_layer('3000', 'mjR_ZoQd1e')">�����ϱ�</button>-->
	                      <div class="wannago_wrap">
	                        <button class="btn-type-icon favorite wannago_btn " data-restaurant_uuid="3000" data-action_id=""></button>
	                        <p class="wannago_txt">����ʹ� </p>
	                      </div>

	                      <span class="title"> <a href="/restaurants/mjR_ZoQd1e" onclick="common_ga('PG_TOPLIST', 'CLICK_RESTAURANT_NAME')">1.<h3> �ͳ�������</h3></a></span>
	                      <strong class="point"><span>4.5</span></strong>
	                      <p class="etc">����� ������ ��ġ�� 896-5</p>
	                    </div>
	                  </figcaption>
	                </figure>

	                <div class="review-content no-bottom">
	                  <figure class="user">
	                      <div class="thumb" style="background-image: url('http://mud-kage.kakao.co.kr/14/dn/btqfzSfSkAT/ZV8YIpeXbMnKNfuhEq6Kj0/o.jpg'), url('https://mp-seoul-image-production-s3.mangoplate.com/web/resources/jmcmlp180qwkp1jj.png?fit=around|*:*&crop=*:*;*,*&output-format=jpg&output-quality=80')">
	                      </div>
	                    <figcaption>
	                      ��
	                    </figcaption>
	                  </figure>
	                  <p class="short_review" onclick="common_ga('PG_TOPLIST', 'CLICK_FEATURED_REVIEW')" data-restaurant_key="mjR_ZoQd1e" data-is_long_reivew="">
	                      ���־��!! �̱��ϰ� ���麸���� �İ�...�ſ�� �峭�ƴϰ� �������� ���־ ��� �԰Եǿ�. �����Ѱ��̶� ������ ���־��.
	                  </p>

	                  <p class="long_review">
	                    ���־��!! �̱��ϰ� ���麸���� �İ�...�ſ�� �峭�ƴϰ� �������� ���־ ��� �԰Եǿ�. �����Ѱ��̶� ������ ���־��.
	                  </p>

	                </div>

	                <a href="/restaurants/mjR_ZoQd1e" class="btn-detail" onclick="common_ga('PG_TOPLIST', 'CLICK_MORE_INFO_BTN')">
	                  �ͳ������� ������ >
	                </a>
	              </div>
	            </li>
	 * 
	 * */
	public List<FoodVO> categoryDetail(String link){
		List<FoodVO> list=new ArrayList<FoodVO>();
		
		/* ������� list.do?page=1&no=10
		 * ������ list.do/1/10 pathvariable�̿�(� �������� �𸣰�!!)
		 * 
		 * java 1.8
		 * ���ٽ�
		 * ����ȥ : java���� js�ҷ����̴� ����
		 * ũ�Ѹ� Ŭ���� ���� croller4j
		 * => utuntu 16/17 ���ʹ� java 1.8���͸� �򸰴�. ���ٽľ˾ƾ� ��
		 * 
		 * */
		
		// link = /top_lists/1309_spicy
		try {
			Document doc=Jsoup.connect("https://www.mangoplate.com"+link).get();
			Elements nElem=doc.select("div.info span.title h3");
			Elements sElem=doc.select("div.info strong.point span");
			Elements aElem=doc.select("div.info p.etc");
			Elements pElem=doc.select("div.thumb img");
			Elements lElem=doc.select("div.with-review figure.restaurant-item a");
			/*Elements nElem=doc.select("div.info span.title h3");
			Elements sElem=doc.select("div.info span.strong.point"); �յڸ� �ߺ���
			Elements aElem=doc.select("div.info p.etc");
			Elements pElem=doc.select("div.thumb img"); //db���� ���� �ֳ��ϸ� ?, &��������̱� �����̴� replace��Ű�ų� �������̿�
			Elements lElem=doc.select("div.with-review figure.restaurant-item a");*/
			
			System.out.println("nElem������"+nElem.size());
			
			int j=0;
			for(int i=0; i<nElem.size(); i++){
				Element name=nElem.get(i);
				Element poster=pElem.get(i);
				Element address=aElem.get(i);
				Element score=sElem.get(i);
				Element link2=lElem.get(j);
				
				FoodVO vo=new FoodVO();
				vo.setName(name.text().trim()); //������ ���� �����ö��� ������ ��쿡 ����� ������ ���ִ� trim()�� ����
				vo.setPoster(poster.attr("src"));
				vo.setAddress(address.text());
				vo.setSocore(Double.parseDouble(score.text()));
				vo.setLink(link2.attr("href"));
				list.add(vo);
				
				j+=2;//link�� �ΰ��� �����Ƿ� �Ѿ�� �Ѵ�.
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/*
	 *  <div class="list-photo_wrap">
          <figure class="list-photo">
            <meta content="https://mp-seoul-image-production-s3.mangoplate.com/added_restaurants/65460_1479194636961663.jpg?fit=around|512:512&amp;crop=512:512;*,*&amp;output-format=jpg&amp;output-quality=80"/>
            <figure class="restaurant-photos-item" onclick="common_ga('PG_RESTAURANT', 'CLICK_PICTURE');" ng-click="mp20_gallery_open(0)" role="img" aria-label="�Ŵ���ƮĿ�� | ������� ������ī�� - �����÷���Ʈ" title="�Ŵ���ƮĿ�� | ������� ������ī�� - �����÷���Ʈ">
              <img class="center-croping"
                   src="https://mp-seoul-image-production-s3.mangoplate.com/added_restaurants/65460_1479194636961663.jpg?fit=around|512:512&amp;crop=512:512;*,*&amp;output-format=jpg&amp;output-quality=80"
                   alt="�Ŵ���ƮĿ�� ���� - ����� ������ �Ż絿 649-8"
                   onerror="this.src='https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80'" />
	 * */
	public FoodVO foodDetailData(String link){
		
		FoodVO vo=new FoodVO();
		
		try {
			Document doc=Jsoup.connect("https://www.mangoplate.com"+link).get();
			
			//tag�����ö� ���̾����� ������ �ҽ������ϸ� ���ϴ�.
			Element name=doc.select("span.title h1.restaurant_name").first(); //select�� �������� �������� ���̴� �Ѱ��� �����ö��� first
			Element score=doc.select("span.title span.rate-point").first(); //select�� �������� �������� ���̴� �Ѱ��� �����ö��� first
			Element poster=doc.select("div.list-photo_wrap figure.restaurant-photos-item img").first(); //select�� �������� �������� ���̴� �Ѱ��� �����ö��� first
			Element address=doc.select("tbody td a.addr-txt").first();//get(1); �ι�° �±׸� �����´�.
			Element tel=doc.select("tbody td a.tel_area").first();
			Element type=doc.select("tbody tr:eq(2) td").first();//tr:eq(2) ����° tr �±�
			Element price=doc.select("tbody tr:eq(3) td").first();
			Elements temp=doc.select("div.title_fliter_wrap li.review_fliter_item button");
			vo.setName(name.text());
			vo.setScore(Double.parseDouble(score.text().trim()));
			vo.setAddress(address.text());
			vo.setTel(tel.text());
			vo.setType(type.text());
			vo.setPrice(price.text());
			vo.setPoster(poster.attr("src"));
			
			for(int i=0; i<temp.size(); i++){
				Element elem=temp.get(i);
				
				//i=0�϶��� Ŭ����
				if (i==1) {
					vo.setGood(elem.text());
				}else if(i==2){
					vo.setSoso(elem.text());
					
				}else if(i==3){
					vo.setBad(elem.text());
				
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	/*public static void main(String[] args) {
		FoodManager fm=new FoodManager();
		fm.categoryAllData();
	}*/
}















