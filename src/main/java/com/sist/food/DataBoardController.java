package com.sist.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.databoard.dao.DataBoardService;
import com.sist.databoard.dao.DataBoardVO;

import java.util.*;
import java.io.*;

@Controller//DispatcherServlet소속 DispatcherServlet는 @Controller붙는 것만 읽는다.
public class DataBoardController {
	
	@Autowired
	private DataBoardService service;
	
	@RequestMapping("main/board_list.do")	
	public String board_list(String page, Model model){//int로 page 받으면 맨처음에 null이 들어와 예외 난다.
		if (page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		
		//1 => 1~10 , 2 => 11~20
		int end=rowSize*curpage;
		
		//fromPage=(5*curpage/(5)-(5)) <- [1] [2] [3] [4] [5] -> 블록 숫자 유지
		map.put("start", start);
		map.put("end", end);
		List<DataBoardVO> list=service.databoardListData(map);
		model.addAttribute("list", list);
		
		//curpage
		//totalpage도 넘겨줘야 한다.
		
		model.addAttribute("main_jsp", "board/board_list.jsp");
		
		return "main/main";
	}
	
	@RequestMapping("main/board_insert.do")
	public String board_insert(Model model){
		
		model.addAttribute("main_jsp", "board/board_insert.jsp");

		return "main/main";//forward request유지-> model쓴다.
	}
	
	//insert, update, delete _ok 출력x db처리만한다. redirect로 board_list나 board_update화면 보낸다. 
	@RequestMapping("main/board_insert_ok.do")
	public String board_insert_ok(DataBoardVO vo){
		//db저장 파일은 업로드 폴더에 넣는다.
		
		List<MultipartFile> list=vo.getUpload();
		if (list==null || list.size()==0) {//업로드가 안되었다면
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
			
			service.databoardInsert(vo);//service이용해서 db에 저장
		}else{
			String strName=""; //db에 ,로 묶어 삽입할 문자열 a.jpg, b.jpg
			String strSize="";
			
			for (MultipartFile mf : list) {
				try {
					String filename=mf.getOriginalFilename();
					File file=new File("c:\\Upload\\"+filename);
					mf.transferTo(file); //메모리에 있는 파일을 파일로 저장
					
					//프로그레스 바를 만들기 위해 filesize저장해둬야 한다
					int size=(int)file.length();
					
					strName+=filename+",";
					strSize+=size+",";
					
				} catch (Exception e) {
					e.printStackTrace();
				}
								
				vo.setFilename(strName.substring(0, strName.lastIndexOf(",")));//stringtoken이용할때 에러안나게 맨마지막,를 지운다.
				vo.setFilesize(strSize.substring(0, strSize.lastIndexOf(",")));
				vo.setFilecount(list.size());
				
				service.databoardInsert(vo);//service이용해서 db에 저장
			}
		}
		
		return "redirect:/main/board_list.do"; //다시한번 실행 redirect가 있으면 .do가 붙는다. model쓰지 않음, request유지할 필요 없다.
	}
	
	
	
}
