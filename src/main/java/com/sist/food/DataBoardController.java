package com.sist.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sist.databoard.dao.DataBoardFileVO;
import com.sist.databoard.dao.DataBoardService;
import com.sist.databoard.dao.DataBoardVO;
import com.sist.databoard.dao.DataReplyVO;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.*;

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
		for(DataBoardVO vo: list){
			vo.setReplycount(service.replyCount(vo.getNo()));
			
		}
		
		
		model.addAttribute("list", list);
		
		System.out.println("regdate : "+list.get(0).getRegdate());
		
		//curpage
		int totalpage=service.databoardTotalPage();
		model.addAttribute("curpage", curpage);
		
		//totalpage�� �Ѱ���� �Ѵ�.
		model.addAttribute("totalpage", totalpage);
		
		
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
		//if (list==null || list.size()==0) {//���ε尡 �ȵǾ��ٸ� list. list�� ���̸� size���Ҷ� error����.
		if (list==null) {//���ε尡 �ȵǾ��ٸ� list.
			//vo.setFilename("");""�� �ƴ� ������ �־�� null�� �ȵ���.
			//vo.setFilesize("");
			vo.setFilename(" ");
			vo.setFilesize(" ");
			vo.setFilecount(0);
			
			
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
				
			}
			vo.setFilename(strName.substring(0, strName.lastIndexOf(",")));//stringtoken�̿��Ҷ� �����ȳ��� �Ǹ�����,�� �����.
			vo.setFilesize(strSize.substring(0, strSize.lastIndexOf(",")));
			vo.setFilecount(list.size());
		}
		service.databoardInsert(vo);//service�̿��ؼ� db�� ����
		
		return "redirect:/main/board_list.do"; //�ٽ��ѹ� ���� redirect�� ������ .do�� �ٰ� model���� ����, �ֳ��ϸ� request������ �ʿ� ���� �����̴�.
	}
	
	//���γ��� ����ϱ�
	@RequestMapping("main/board_content.do")
	public String board_content(int no, int page, Model model){
		
		DataBoardVO vo=service.dataBoardContentData(no);
		if (vo.getFilecount()>0) {//���Ͼ��ε尡 �Ǿ��ٸ�
			List<DataBoardFileVO> list=new ArrayList<DataBoardFileVO>();
			String[] fileArr=vo.getFilename().split(",");
			String[] sizeArr=vo.getFilesize().split(",");
			
			for (int i=0; i<fileArr.length; i++) {
				DataBoardFileVO fvo=new DataBoardFileVO();
				fvo.setFilename(fileArr[i]);
				fvo.setFilesize(Long.parseLong(sizeArr[i]));
				
				list.add(fvo);
				
			}
			
			vo.setFileList(list);
		}
		
		List<DataReplyVO> rList=service.replyListData(no);
				
		model.addAttribute("rList", rList);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		model.addAttribute("main_jsp", "board/board_content.jsp"); //ȭ�� �ٲٱ�
		return "main/main";
	}
	
	//���� �ٿ�ε�
	@RequestMapping("main/board_download.do")
	public void board_download(String fn, HttpServletResponse response){//ȭ���� �ȹٲ�Ƿ� return�� void
		System.out.println("fn:"+fn);
		try {
			//������ ���ϸ�� ����� ������ �ٿ�ε� �ұ��? �� ����� ����ٸ� ��� �� �ִ�. 
			response.setHeader("Content-Disposition", "attachment;filename="
					+URLEncoder.encode(fn, "UTF-8")
				);//attachment�� downloadâ�� ����ִ� ���̴�.
			File file=new File("c:\\Upload\\"+fn);
			response.setContentLength((int)file.length());
			
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());//���濡 ������ ����
			
			int i=0;
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer, 0, 1024))!=-1){
				
				bos.write(buffer, 0, i);
			}
			bis.close();
			bos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//����â ����
	@RequestMapping("main/board_update.do")
	public String board_update(int no, int page, Model model){
		
		DataBoardVO vo=service.dataBoardUpdateData(no);
		
		//�Ʒ��κ��� �ݺ��ǹǷ� aop�� ���� �ִ�.
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		model.addAttribute("main_jsp", "board/board_update.jsp");		
	
		return "main/main";
	}
	
	@RequestMapping("main/board_update_ok.do")
	@ResponseBody //������ board_update.jsp�� ���� ������ ����� �Ʒ��� return ������ �����. restful������ �̿��Ұ� ������? ajax�� ���� �� �ִ�.
	//js�� ���� ���� ���� Annotation�̴�.. ũ�Ұ� ���̾����������� ����Ѵ�. �ѱ��� ������ ������ dispatcher-servlet��������� �Ѵ�.
	public String board_update_ok(DataBoardVO vo, int page){
		System.out.println("board_update_ok");
		boolean bCheck=false;
		try {//pwd�´��� Ȯ��
			String pwd=service.databoardGetPwd(vo.getNo());
			if (pwd.equals(vo.getPwd())) {
				bCheck=true;
				
				//������ ���������� Ȯ�� 
				List<MultipartFile> list=vo.getUpload();
				DataBoardVO fvo=service.databoardGetFileInfo(vo.getNo());
				if (list==null) {//���Ͼ��ε� ���ߴٸ� ������ �������� �״�� �Է�
					vo.setFilename(fvo.getFilename());
					vo.setFilesize(fvo.getFilesize());
					vo.setFilecount(fvo.getFilecount());
					
				}else{//���Ͼ��ε� �ߴٸ� ���������� �����ٸ� �׳� ���ε� ���������� �־��ٸ� �������� ����� �ٽ� ���ε�  
					if (fvo.getFilecount()>0) {//�������� �־��ٸ� �����.
						String[] files=fvo.getFilename().split(",");
						for (String f : files) {
							File ff=new File("c:\\Upload\\"+f);
							ff.delete();
						}
						
					}
					
					//���ε� // �޼ҵ�ȭ ��Ű�� ���ϰڴ�.
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
						
					}
					vo.setFilename(strName.substring(0, strName.lastIndexOf(",")));//stringtoken�̿��Ҷ� �����ȳ��� �Ǹ�����,�� �����.
					vo.setFilesize(strSize.substring(0, strSize.lastIndexOf(",")));
					vo.setFilecount(list.size());
					
				}
				
				System.out.println("no: "+vo.getNo()
									+" ;name: "+vo.getName()
									+" ;content: "+vo.getContent()
									+" ;subject: "+vo.getSubject()
									+" ;filecount: "+vo.getFilecount()
									+" ;filename: "+vo.getFilename()
									+" ;filesize: "+vo.getFilesize()
				);
				service.databoardUpdate(vo);
				
			}else{
				bCheck=false;				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		
		String send="";		
		//board_update_ok.jsp�� �ش��ϴ� �����̴�. �ͽ��÷η��� �Ƿ��� ���� ������ �Ѵ�. �ͽ��÷η����� ajax�� ���ؼ��� ������ �ִ�.
		if (bCheck==true) {
			
			send="<script>"
				+"location.href=\"board_content.do?no="+vo.getNo()+"&page="+page+"\";"
				+"</script>";
			
			
		}else{
			send="<script>"
				+ "alert(\"��й�ȣ�� Ʋ���ϴ�.\");"/*�׻�java���� js���� ���� �������� ;�� ������*/
				+"history.back();"
				+"</script>";			
		}
		
		return send;
		
	}
	
}
