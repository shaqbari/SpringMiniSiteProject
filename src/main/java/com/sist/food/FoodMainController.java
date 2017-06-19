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
		System.out.println("맛집검색 들어왔어");
		System.out.println(kind);
		System.out.println(addr);
		Map map=new HashMap();
		map.put("kind", kind);
		map.put("addr", addr);
		
		List<FoodReserveVO> list=dao.reserveListData(map);	
		System.out.println("list사이즈 : "+list.size());
		
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
		//MM(01, 02, 08, 09)//앞에 0이 있으면 8진법으로 인식하기 때문에 08과 09에서 에러난다.  M(1, 2, 3, )
		StringTokenizer st=new StringTokenizer(sdf.format(date), "-");
		
		String strYear="";
		String strMonth="";		
		if (year==null) {
			strYear=st.nextToken();//올해로 초기화
			
		}		
		if (month==null) {
			strMonth=st.nextToken();//이번달로 초기화
			
		}
		int y=Integer.parseInt(strYear);
		int m=Integer.parseInt(strMonth);
		int d=Integer.parseInt(st.nextToken());
		
		//week이번달의 시작요일, lastday이번달의 마지막 날짜도 필요
		//전년도까지 총날수
		int total=(y-1)*365
				+(y-1)/4 //윤년 4년마다 
				-(y-1)/100 //100년에 한번 제외
				+(y-1)/400; //400년마다 한번 추가
		//전달까지의 날수 ===>6월이면  5/31 +1 %7
		int[] lastDay={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		if((y%4==0 && y%100!=0)||(y%400==0)){ //올해가 윤년이라면
			lastDay[1]=29;			
		}
		
		for(int i=0; i<m-1; i++){
			total+=lastDay[i];
			
		}
		total++;
		
		String res_day=dao.reserveDayData(no);
		String[] rd=res_day.split(",");
		boolean[] bCheck=new boolean[31];//기본값은 false;
		for (int i=0; i<rd.length; i++) {
			bCheck[Integer.parseInt(rd[i])-1]=true; //rd[i]-1번째만 true로 바꾼다.
			
			
		}
		
		
		int week=total%7;
		model.addAttribute("year", y);
		model.addAttribute("month", m);
		model.addAttribute("week", week);		
		model.addAttribute("lastday", lastDay[m-1]);
		String[] strWeek={"일", "월", "화", "수", "목", "금", "토"};
		model.addAttribute("strWeek", strWeek);//출력할수 있게 로직을 모두 보내준다.
		
		model.addAttribute("rd", bCheck);
		model.addAttribute("today", d);//오늘 날짜
		
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
			
			//null값이면 공백들어가므로 그것을 방지한다.
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
		vo.setRes_date(vo.getRes_date().substring(vo.getRes_date().indexOf(":")+1));//예약일:을 자른다.
		vo.setRes_time(vo.getRes_time().substring(vo.getRes_time().indexOf(":")+1));
		vo.setId((String)session.getAttribute("id"));
						
		//default중간을mypage로 변경
		dao.foodResInsert(vo);		
		
		//model.addAttribute("id", (String)session.getAttribute("id"));//세션에저장되어 있으므로 보내도되구 안보내도 된다.그리고url에 id가 노출된다.
		//secure coding에 해당 
		//1) id pwd노출x session에 등록
		//2) VO private and getter/setter 캡슐화
		//3) 배포 할때는 sysout 지운다.!
		//4) jsoup데이터 긁을때 꺽쇠는 문자로?
		
		return "main/food/reserve_ok";
	}
	
	@RequestMapping("main/mypage.do")
	public String reserve_mypage(HttpSession session, Model model){
		
		List<FoodResVO> list=dao.foodMyPage((String)session.getAttribute("id"));
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "food/mypage.jsp");
				
		return "main/main";
	}
	
	/***************************원래는 사용자와 관리자의 예약 확인 테이블이 달라야 한다. 이때 자동으로 들어가게triger이용한다. **************************/
	@RequestMapping("main/adminpage.do")
	public String reserve_adminpage(Model model){
		
		List<FoodResVO> list=dao.foodAdminPage();
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "food/adminpage.jsp");
		
		return "main/main";
	}	
	@RequestMapping("main/adminpage_ok.do")
	public String reserve_adminpage_ok(String[] res_ck){//checkbox로 넘어오므로 배열로 받아야 한다. getParameterValues();
		
		//Mybatis에서 자체적으로 foreach로도 넣을 수 있다.
		for(int i=0; i<res_ck.length; i++){
			dao.resStateChange(Integer.parseInt(res_ck[i]));
			
		}
		
		return "redirect:/main/adminpage.do";
	}
	/***************************원래는 사용자와 관리자의 예약 확인 테이블이 달라야 한다. 이때 자동으로 들어가게triger이용한다. **************************/
}









