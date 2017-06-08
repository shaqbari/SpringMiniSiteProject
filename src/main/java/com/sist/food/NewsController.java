package com.sist.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.news.Item;
import com.sist.news.NewsManager;

@Controller
public class NewsController {
	
	@Autowired
	private NewsManager nm;
	
	@RequestMapping("main/news.do")
	public String newsFindData(String data, Model model){
		
		if (data==null) {//처음페이지는 data가 없을 것이므로 null일때의 default값을 꼭 지정해 주자
			data="신뉴스";
		}
		List<Item> list=nm.newsAllData(data);
		
		model.addAttribute("list", list); //news.jsp를 include했으므로 그냥 보내도 된다. 
		model.addAttribute("main_jsp", "news/news.jsp");
		
		return "main/main";
	}
}
