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
public class MemberController {//POZO ����̳� �������̽��� �������� �ʾ� ������� ������� ���������ϴ�.
	//��� ã���� �ִ� index(annotaion�� xml)�� ����� �Ѵ�. requestmapping�� ��� if���� ����ϰ� �۵��Ѵ�.
	
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping("main/join.do")
	public String member_join(Model model){
			
		model.addAttribute("main_jsp", "member/join.jsp"); //Model�� request�� ĸ��ȭ ��Ų ���̴�.
		return "main/main";		
		
	}
	
	@RequestMapping("main/join_ok.do")
	public String member_join_ok(MemberVO vo, Model model){
		
		System.out.println("���� : "+ vo.getSex());
		
		vo.setPost(vo.getPost1()+"-"+vo.getPost2());
		vo.setTel(vo.getTel1()+"-"+vo.getTel2());
		dao.memberInsert(vo);
		
		model.addAttribute("main_jsp", "member/join_ok.jsp");		
		return "main/main";
	}
	
	
	@RequestMapping("main/postfind.do")	
	public String member_postfind(){
		
		return "main/member/postfind";//�˾�â ����
	}
	
	@RequestMapping("main/postfind_result.do")
	public String member_postfind_result(String dong, Model model){
		System.out.println("dong="+dong);//react�� angular js�̿��ϸ� �ٷ� ��°���
		
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
		System.out.println("idcheck���Ծ� id:"+id);
		
		
		int count=dao.memberIdCheck(id);
		model.addAttribute("count", count);
		model.addAttribute("id", id);
		
		return "main/member/idcheck_result";
	}
	
	@RequestMapping("main/login.do")
	public String member_login(String id, String pwd, HttpSession session, Model model){
		//id�� ����
		//pwd Ʋ����.		
		//�α���		
		System.out.println("login���Ծ�");
		
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
				//session.setMaxInactiveInterval(1800); default ���� �α��� ����, ��Žð� � �̿�
				
				/*
				 *  session : ������ ������� �Ϲ� ������ ����
				 * 1) ���� setAttribute()
				 * 2) �� ������ ���� ��� getAttribute()
				 * 3) �����ϴ� ���
				 * 	removeAttribute()
				 * 	invalidate()		 
				 * 4) �Ⱓ���� setMaxInactiveInterval(1800)		
				 * 5) session id==> �������� 1���� ���� (�ǽð� ä�ÿ��� �̿��Ѵ�.)
				 * 
				 *  *�α��λ��� ������ cookie�� �̿�(����� ��ǻ�Ϳ� ����)�Ѵ�.
				 *  cookie�� session�� request�� ���� ���;� �Ѵ�.
				 *  request.getSession();
				 *  
				 *  ������������ session�� dispathcerServlet�� ���� DI�� �޴´�.
				 *	Exception�� ����������.
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
		 * 1) ��ü ���� invalidate() �α׾ƿ�, ������ ����
		 *
		 * 2) �κ� ����(��ٱ���) removeAttribue(key)
		 * */
		
		session.invalidate();//session�� ��� ������ �����. logout�̳� ������ ����� ȣ��
		return "redirect:main.do";//�α׾ƿ��� main���� ���ư���.
	}
	
	
}
