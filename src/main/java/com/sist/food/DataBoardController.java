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

@Controller//DispatcherServlet�Ҽ� DispatcherServlet�� @Controller�ٴ� �͸� �д´�.
public class DataBoardController {
	
	@Autowired
	private DataBoardService service;
	
	@RequestMapping("main/board_list.do")	
	public String board_list(String page, Model model){//int�� page ������ ��ó���� null�� ���� ���� ����.
		if (page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		
		//1 => 1~10 , 2 => 11~20
		int end=rowSize*curpage;
		
		//fromPage=(5*curpage/(5)-(5)) <- [1] [2] [3] [4] [5] -> ��� ���� ����
		map.put("start", start);
		map.put("end", end);
		List<DataBoardVO> list=service.databoardListData(map);
		model.addAttribute("list", list);
		
		//curpage
		//totalpage�� �Ѱ���� �Ѵ�.
		
		model.addAttribute("main_jsp", "board/board_list.jsp");
		
		return "main/main";
	}
	
	@RequestMapping("main/board_insert.do")
	public String board_insert(Model model){
		
		model.addAttribute("main_jsp", "board/board_insert.jsp");

		return "main/main";//forward request����-> model����.
	}
	
	//insert, update, delete _ok ���x dbó�����Ѵ�. redirect�� board_list�� board_updateȭ�� ������. 
	@RequestMapping("main/board_insert_ok.do")
	public String board_insert_ok(DataBoardVO vo){
		//db���� ������ ���ε� ������ �ִ´�.
		
		List<MultipartFile> list=vo.getUpload();
		if (list==null || list.size()==0) {//���ε尡 �ȵǾ��ٸ�
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
			
			service.databoardInsert(vo);//service�̿��ؼ� db�� ����
		}else{
			String strName=""; //db�� ,�� ���� ������ ���ڿ� a.jpg, b.jpg
			String strSize="";
			
			for (MultipartFile mf : list) {
				try {
					String filename=mf.getOriginalFilename();
					File file=new File("c:\\Upload\\"+filename);
					mf.transferTo(file); //�޸𸮿� �ִ� ������ ���Ϸ� ����
					
					//���α׷��� �ٸ� ����� ���� filesize�����ص־� �Ѵ�
					int size=(int)file.length();
					
					strName+=filename+",";
					strSize+=size+",";
					
				} catch (Exception e) {
					e.printStackTrace();
				}
								
				vo.setFilename(strName.substring(0, strName.lastIndexOf(",")));//stringtoken�̿��Ҷ� �����ȳ��� �Ǹ�����,�� �����.
				vo.setFilesize(strSize.substring(0, strSize.lastIndexOf(",")));
				vo.setFilecount(list.size());
				
				service.databoardInsert(vo);//service�̿��ؼ� db�� ����
			}
		}
		
		return "redirect:/main/board_list.do"; //�ٽ��ѹ� ���� redirect�� ������ .do�� �ٴ´�. model���� ����, request������ �ʿ� ����.
	}
	
	
	
}
