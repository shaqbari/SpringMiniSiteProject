package com.sist.food;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.food.dao.CategoryVO;
import com.sist.food.dao.FoodDAO;
import com.sist.food.dao.FoodManager;
import com.sist.food.dao.FoodReserveVO;
import com.sist.food.dao.FoodVO;


@Controller
public class FoodMainController {
	
	@Autowired
	private FoodManager fmgr;
	
	@Autowired	
	private FoodDAO dao;
	
	
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
	
	@RequestMapping("main/reserve.do")
	public String main_reserve(Model model){
		
		model.addAttribute("main_jsp", "food/foodreserve.jsp");	
		return "main/main";
	}
	
	@RequestMapping("main/reserve_gu_find.do")
	public String main_reserve_gu_find(String kind, Model model){
				
		List list=dao.reserveLocData(kind);
		
		model.addAttribute("kind", kind);
		model.addAttribute("list", list);
		return "main/food/reserve_gu_find";
	}
	
	@RequestMapping("main/reserve_list.do")
	public String main_reserve_list(String kind, String addr, Model model){
		//C /S		
		System.out.println("�����˻� ���Ծ�");
		System.out.println(kind);
		System.out.println(addr);
		Map map=new HashMap();
		map.put("kind", kind);
		map.put("addr", addr);
		
		List<FoodReserveVO> list=dao.reserveListData(map);	
		System.out.println("list������ : "+list.size());
		
		for (FoodReserveVO vo : list) {
			String [] data= vo.getImage().split(",");
			vo.setPoster(data[0]);
			
		}
		
		model.addAttribute("list", list);
		
		return "main/food/reserve_list";
	}
	
	@RequestMapping("main/reserve_date.do")
	public String reserve_date(String year, String month, int no, Model model){
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
		//MM(01, 02, 08, 09)//�տ� 0�� ������ 8�������� �ν��ϱ� ������ 08�� 09���� ��������.  M(1, 2, 3, )
		StringTokenizer st=new StringTokenizer(sdf.format(date), "-");
		
		String strYear="";
		String strMonth="";		
		if (year==null) {
			strYear=st.nextToken();//���ط� �ʱ�ȭ
			
		}		
		if (month==null) {
			strMonth=st.nextToken();//�̹��޷� �ʱ�ȭ
			
		}
		int y=Integer.parseInt(strYear);
		int m=Integer.parseInt(strMonth);
		
		//week�̹����� ���ۿ���, lastday�̹����� ������ ��¥�� �ʿ�
		//���⵵���� �ѳ���
		int total=(y-1)*365
				+(y-1)/4 //���� 4�⸶�� 
				-(y-1)/100 //100�⿡ �ѹ� ����
				+(y-1)/400; //400�⸶�� �ѹ� �߰�
		//���ޱ����� ���� ===>6���̸�  5/31 +1 %7
		int[] lastDay={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		if((y%4==0 && y%100!=0)||(y%400==0)){ //���ذ� �����̶��
			lastDay[1]=29;			
		}
		
		for(int i=0; i<m-1; i++){
			total+=lastDay[i];
			
		}
		total++;
		
		int week=total%7;
		model.addAttribute("year", y);
		model.addAttribute("month", m);
		model.addAttribute("week", week);		
		model.addAttribute("lastday", lastDay[m-1]);
		String[] strWeek={"��", "��", "ȭ", "��", "��", "��", "��"};
		model.addAttribute("strWeek", strWeek);//����Ҽ� �ְ� ������ ��� �����ش�.
		
		return "main/food/reserve_date";
	}
}









