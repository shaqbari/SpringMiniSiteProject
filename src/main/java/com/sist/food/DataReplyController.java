package com.sist.food;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.databoard.dao.DataBoardService;
import com.sist.databoard.dao.DataReplyVO;

@Controller
public class DataReplyController {
	
	@Autowired
	private DataBoardService service;
	
			
	@RequestMapping("main/reply_new_insert.do")
	//@PreDestroy 메소드 권한 걸어서 로그인시에만 이 메소드 사용가능하도록 해야 한다. db차원에서도 권한별로 차등적용 가능 
	public String reply_new_insert(int bno, int page, String msg, HttpSession session){
		
		DataReplyVO vo=new DataReplyVO();
		
		String id= (String) session.getAttribute("id");
		String name= (String) session.getAttribute("name");
		
		vo.setBno(bno);
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);
		service.replyNewInsert(vo);
		
		return "redirect:/main/board_content.do?no="+bno+"&page="+page;
		//포워드만 request(model).addAttribute()로 나머지는 redirect는 get방식으로 보낸다.
	}

	@RequestMapping("main/reply_update.do")
	public String reply_update(int page, DataReplyVO vo){
		
		service.replyUpdate(vo);
		
		return "redirect:/main/board_content.do?no="+vo.getBno()+"&page="+page;
	}
	
	@RequestMapping("main/reply_re_insert.do")
	public String reply_re_insert(int pno, int bno, int page, String msg, HttpSession session){
		DataReplyVO vo=new DataReplyVO();
		
		String id= (String) session.getAttribute("id");
		String name= (String) session.getAttribute("name");
		
		vo.setBno(bno);
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);		
		service.replyReInsert(pno, vo);
		
		return "redirect:/main/board_content.do?no="+bno+"&page="+page;
	}
	
	@RequestMapping("/main/reply_delete.do")
	public String reply_delete(int no, int bno, int page){//web.xml에서 설정하면 주소하나로 모든기능구현가능 //restful
		//ajax쓰면 편하다.
		
		//Db연동
		service.replyDelete(no);
		
		return "redirect:/main/board_content.do?no="+bno+"&page="+page;
	}
}









