package com.sist.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.food.dao.CategoryVO;
import com.sist.food.dao.FoodManager;
import com.sist.food.dao.FoodVO;


@Controller
public class FoodMainController {
	
	@Autowired
	private FoodManager fmgr;
	
	
	@RequestMapping("main/main.do")
	public String main_page(Model model){
		List<CategoryVO> list=fmgr.categoryAllData();
		model.addAttribute("list", list);			
		
		model.addAttribute("main_jsp", "default.jsp");
		
		return "main/main";
		
	}
	
	@RequestMapping("main/loc.do")
	public String main_loc(Model model ){
		
		model.addAttribute("main_jsp", "food/food_loc.jsp");
		
		return "main/main";
	}
	
	@RequestMapping("main/foodmain.do")
	public String main_foodpage(String title, String link, Model model){
		//test
		/*List<CategoryVO> list=fmgr.categoryAllData();
		model.addAttribute("list", list);*/	
		
		
		List<FoodVO> list=fmgr.categoryDetail(link);
		
		
		model.addAttribute("list", list);
		model.addAttribute("title", title);
		model.addAttribute("main_jsp", "food/foodmain.jsp");
		
		return "main/main";
		
	}
	
	@RequestMapping("main/fooddetail.do")
	public String main_fooddetail(String link, String poster, Model model){
		FoodVO vo=fmgr.foodDetailData(link);
		
		model.addAttribute("vo", vo);
		model.addAttribute("poster", poster);
		model.addAttribute("main_jsp", "food/fooddetail.jsp");		
		return "main/main";
	}
	
}









