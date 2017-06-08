package com.sist.news;

import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import java.net.*;

/*							 메모리할당
 * ~VO, DTO                     (X)
 * ~Manager                  @Componenet
 * ~DAO						 @Repository
 * ~Service : DAO+DAO        @Service
 * ~Controller : Model       @Controller
 * 
 * 
 * */

@Component //Singleton으로 생성된다.
//@Scope("prototype") : 여러개 생성
//<bean scope="">
public class NewsManager {
	public List<Item> newsAllData(String data){
		List<Item> list=new ArrayList<Item>();
		try {
			URL url=new URL("http://newssearch.naver.com/search.naver?where=rss&query="
					+URLEncoder.encode(data, "UTF-8"));
			JAXBContext jc=JAXBContext.newInstance(Rss.class);//rootElement가 클래스로 넘어가야 한다.
			Unmarshaller un=jc.createUnmarshaller();
			
			Rss rss=(Rss)un.unmarshal(url);
			list=rss.getChannel().getItem();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
}
