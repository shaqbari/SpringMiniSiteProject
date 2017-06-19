package com.sist.food;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.food.dao.CategoryVO;
import com.sist.food.dao.FoodDAO;
import com.sist.food.dao.FoodManager;
import com.sist.food.dao.FoodResVO;
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
		int d=Integer.parseInt(st.nextToken());
		
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
		
		String res_day=dao.reserveDayData(no);
		String[] rd=res_day.split(",");
		boolean[] bCheck=new boolean[31];//�⺻���� false;
		for (int i=0; i<rd.length; i++) {
			bCheck[Integer.parseInt(rd[i])-1]=true; //rd[i]-1��°�� true�� �ٲ۴�.
			
			
		}
		
		
		int week=total%7;
		model.addAttribute("year", y);
		model.addAttribute("month", m);
		model.addAttribute("week", week);		
		model.addAttribute("lastday", lastDay[m-1]);
		String[] strWeek={"��", "��", "ȭ", "��", "��", "��", "��"};
		model.addAttribute("strWeek", strWeek);//����Ҽ� �ְ� ������ ��� �����ش�.
		
		model.addAttribute("rd", bCheck);
		model.addAttribute("today", d);//���� ��¥
		
		System.out.println("rd : "+rd);
		System.out.println("today :"+d);
		
		return "main/food/reserve_date";
	}
	
	@RequestMapping("main/reserve_time.do")
	public String reserve_time(int day, Model model){
		List<String> list=new ArrayList<String>();
		String resTime=dao.reserveTimeData(day);
		String[] rt=resTime.split(",");
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
		
		for(String s : rt){
			System.out.println("s="+s);
			String realT=dao.reserveRealTime(Integer.parseInt(s));
			
			//null���̸� ������Ƿ� �װ��� �����Ѵ�.
			if (realT!=null) {
				//realT.isEmpty();
				System.out.println("realT="+realT);
				list.add(realT);
			}
			
			
		}
		
		model.addAttribute("list", list);
		
		return "main/food/reserve_time";
	}
	
	@RequestMapping("main/reserve_inwon.do")
	public String reserve_inwon(Model model){
		
		return "main/food/reserve_inwon";
	}
	
	@RequestMapping("main/reserve_insert.do")
	public String reserve_insert(FoodResVO vo, HttpSession session, Model model){
		
		System.out.println("inwon : "+vo.getRes_inwon());
		vo.setRes_date(vo.getRes_date().substring(vo.getRes_date().indexOf(":")+1));//������:�� �ڸ���.
		vo.setRes_time(vo.getRes_time().substring(vo.getRes_time().indexOf(":")+1));
		vo.setId((String)session.getAttribute("id"));
						
		//default�߰���mypage�� ����
		dao.foodResInsert(vo);		
		
		//model.addAttribute("id", (String)session.getAttribute("id"));//���ǿ�����Ǿ� �����Ƿ� �������Ǳ� �Ⱥ����� �ȴ�.�׸���url�� id�� ����ȴ�.
		//secure coding�� �ش� 
		//1) id pwd����x session�� ���
		//2) VO private and getter/setter ĸ��ȭ
		//3) ���� �Ҷ��� sysout �����.!
		//4) jsoup������ ������ ����� ���ڷ�?
		
		return "main/food/reserve_ok";
	}
	
	@RequestMapping("main/mypage.do")
	public String reserve_mypage(HttpSession session, Model model){
		
		List<FoodResVO> list=dao.foodMyPage((String)session.getAttribute("id"));
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "food/mypage.jsp");
				
		return "main/main";
	}
	
	/***************************������ ����ڿ� �������� ���� Ȯ�� ���̺��� �޶�� �Ѵ�. �̶� �ڵ����� ����triger�̿��Ѵ�. **************************/
	@RequestMapping("main/adminpage.do")
	public String reserve_adminpage(Model model){
		
		List<FoodResVO> list=dao.foodAdminPage();
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "food/adminpage.jsp");
		
		return "main/main";
	}	
	@RequestMapping("main/adminpage_ok.do")
	public String reserve_adminpage_ok(String[] res_ck){//checkbox�� �Ѿ���Ƿ� �迭�� �޾ƾ� �Ѵ�. getParameterValues();
		
		//Mybatis���� ��ü������ foreach�ε� ���� �� �ִ�.
		for(int i=0; i<res_ck.length; i++){
			dao.resStateChange(Integer.parseInt(res_ck[i]));
			
		}
		
		return "redirect:/main/adminpage.do";
	}
	/***************************������ ����ڿ� �������� ���� Ȯ�� ���̺��� �޶�� �Ѵ�. �̶� �ڵ����� ����triger�̿��Ѵ�. **************************/
}









