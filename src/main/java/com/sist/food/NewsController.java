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
		
		if (data==null) {//ó���������� data�� ���� ���̹Ƿ� null�϶��� default���� �� ������ ����
			data="�Ŵ���";
		}
		List<Item> list=nm.newsAllData(data);
		
		model.addAttribute("list", list); //news.jsp�� include�����Ƿ� �׳� ������ �ȴ�. 
		model.addAttribute("main_jsp", "news/news.jsp");
		
		return "main/main";
	}
}
