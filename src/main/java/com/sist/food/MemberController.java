package com.sist.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.member.dao.MemberDAO;
import com.sist.member.dao.ZipcodeVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping("main/join.do")
	public String member_join(Model model){
			
		model.addAttribute("main_jsp", "member/join.jsp");
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
}
