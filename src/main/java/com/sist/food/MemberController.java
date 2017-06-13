package com.sist.food;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.member.dao.MemberDAO;
import com.sist.member.dao.MemberVO;
import com.sist.member.dao.ZipcodeVO;

/*
 * 	Spring
 * XML
 * Annotation
 * MVC
 * ORM
 * DI
 * AOP
 * */

@Controller
public class MemberController {//POZO 상속이나 인터페이스를 구현하지 않아 제약없이 마음대로 구현가능하다.
	//대신 찾을수 있는 index(annotaion나 xml)을 써줘야 한다. requestmapping의 경우 if문과 비슷하게 작동한다.
	
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping("main/join.do")
	public String member_join(Model model){
			
		model.addAttribute("main_jsp", "member/join.jsp"); //Model은 request를 캡슐화 시킨 것이다.
		return "main/main";		
		
	}
	
	@RequestMapping("main/join_ok.do")
	public String member_join_ok(MemberVO vo, Model model){
		
		System.out.println("성별 : "+ vo.getSex());
		
		vo.setPost(vo.getPost1()+"-"+vo.getPost2());
		vo.setTel(vo.getTel1()+"-"+vo.getTel2());
		dao.memberInsert(vo);
		
		model.addAttribute("main_jsp", "member/join_ok.jsp");		
		return "main/main";
	}
	
	
	@RequestMapping("main/postfind.do")	
	public String member_postfind(){
		
		return "main/member/postfind";//팝업창 띄우기
	}
	
	@RequestMapping("main/postfind_result.do")
	public String member_postfind_result(String dong, Model model){
		System.out.println("dong="+dong);//react나 angular js이용하면 바로 출력가능
		
		List<ZipcodeVO> list=dao.zipcodeListData(dong);
		
		System.out.println("address : "+list.get(0).getAddress());
		System.out.println("size : "+list.size());
		
		model.addAttribute("list", list);
		model.addAttribute("count", list.size());
		
		return "main/member/postfind_result";
	}
	
	
	@RequestMapping("main/idcheck.do")
	public String member_idcheck(){
		
		return "main/member/idcheck";
	}
	
	@RequestMapping("main/idcheck_result.do")
	public String member_idcheck_result(String id, Model model){
		System.out.println("idcheck들어왔어 id:"+id);
		
		
		int count=dao.memberIdCheck(id);
		model.addAttribute("count", count);
		model.addAttribute("id", id);
		
		return "main/member/idcheck_result";
	}
	
	@RequestMapping("main/login.do")
	public String member_login(String id, String pwd, HttpSession session, Model model){
		//id가 없다
		//pwd 틀리다.		
		//로그인		
		System.out.println("login들어왔어");
		
		int count=dao.memberIdCheck(id);
		String res="";
		
		if (count==0) {
			res="NOID";
		}else{
			String db_pwd=dao.memberGetPwd(id);
			if (pwd.equals(db_pwd)) {
				res="OK";
				MemberVO vo=dao.memberInfoData(id);
				session.setAttribute("id", vo.getId());
				session.setAttribute("name", vo.getName());
				session.setAttribute("admin", vo.getAdmin());
				//session.setMaxInactiveInterval(1800); default 은행 로그인 시장, 경매시간 등에 이용
				
				/*
				 *  session : 서버에 사용자의 일반 정보를 저장
				 * 1) 저장 setAttribute()
				 * 2) 값 가지고 오는 방법 getAttribute()
				 * 3) 삭제하는 방법
				 * 	removeAttribute()
				 * 	invalidate()		 
				 * 4) 기간설정 setMaxInactiveInterval(1800)		
				 * 5) session id==> 유저마다 1개만 생성 (실시간 채팅에도 이용한다.)
				 * 
				 *  *로그인상태 유지는 cookie를 이용(사용자 컴퓨터에 저장)한다.
				 *  cookie나 session은 request를 통해 얻어와야 한다.
				 *  request.getSession();
				 *  
				 *  스프링에서는 session을 dispathcerServlet을 통해 DI로 받는다.
				 *	Exception도 마찬가지다.
				 * */
			}else{
				res="NOPWD";
				
			}
			
		}
		
		model.addAttribute("res", res);
		return "main/member/login";
	}
	
	@RequestMapping("main/logout.do")
	public String member_login(HttpSession session){
				
		/*
		 * 1) 전체 삭제 invalidate() 로그아웃, 브라우저 종료
		 *
		 * 2) 부분 삭제(장바구니) removeAttribue(key)
		 * */
		
		session.invalidate();//session의 모든 내용을 지운다. logout이나 브라우저 종료시 호출
		return "redirect:main.do";//로그아웃후 main으로 돌아간다.
	}
	
	
}
