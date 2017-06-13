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
		for(DataBoardVO vo: list){
			vo.setReplycount(service.replyCount(vo.getNo()));
			
		}
		
		
		model.addAttribute("list", list);
		
		System.out.println("regdate : "+list.get(0).getRegdate());
		
		//curpage
		int totalpage=service.databoardTotalPage();
		model.addAttribute("curpage", curpage);
		
		//totalpage도 넘겨줘야 한다.
		model.addAttribute("totalpage", totalpage);
		
		
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
		//if (list==null || list.size()==0) {//업로드가 안되었다면 list. list가 널이면 size구할때 error난다.
		if (list==null) {//업로드가 안되었다면 list.
			//vo.setFilename("");""이 아닌 공백을 넣어야 null이 안들어간다.
			//vo.setFilesize("");
			vo.setFilename(" ");
			vo.setFilesize(" ");
			vo.setFilecount(0);
			
			
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
				
			}
			vo.setFilename(strName.substring(0, strName.lastIndexOf(",")));//stringtoken이용할때 에러안나게 맨마지막,를 지운다.
			vo.setFilesize(strSize.substring(0, strSize.lastIndexOf(",")));
			vo.setFilecount(list.size());
		}
		service.databoardInsert(vo);//service이용해서 db에 저장
		
		return "redirect:/main/board_list.do"; //다시한번 실행 redirect가 있으면 .do가 붙고 model쓰지 않음, 왜냐하면 request유지할 필요 없기 때문이다.
	}
	
	//세부내용 출력하기
	@RequestMapping("main/board_content.do")
	public String board_content(int no, int page, Model model){
		
		DataBoardVO vo=service.dataBoardContentData(no);
		if (vo.getFilecount()>0) {//파일업로드가 되었다면
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
		model.addAttribute("main_jsp", "board/board_content.jsp"); //화면 바꾸기
		return "main/main";
	}
	
	//파일 다운로드
	@RequestMapping("main/board_download.do")
	public void board_download(String fn, HttpServletResponse response){//화면이 안바뀌므로 return은 void
		System.out.println("fn:"+fn);
		try {
			//사전에 파일명과 사이즈를 보내야 다운로드 할까요? 라 물어보고 진행바를 띄울 수 있다. 
			response.setHeader("Content-Disposition", "attachment;filename="
					+URLEncoder.encode(fn, "UTF-8")
				);//attachment가 download창을 띄워주는 것이다.
			File file=new File("c:\\Upload\\"+fn);
			response.setContentLength((int)file.length());
			
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());//상대방에 데이터 전송
			
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
	
	//수정창 띄우기
	@RequestMapping("main/board_update.do")
	public String board_update(int no, int page, Model model){
		
		DataBoardVO vo=service.dataBoardUpdateData(no);
		
		//아래부분은 반복되므로 aop로 뺄수 있다.
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		model.addAttribute("main_jsp", "board/board_update.jsp");		
	
		return "main/main";
	}
	
	@RequestMapping("main/board_update_ok.do")
	@ResponseBody //원래의 board_update.jsp의 버퍼 내용을 지우고 아래의 return 내용을 덮어쓴다. restful에서도 이용할거 같은데? ajax로 받을 수 있다.
	//js를 쓰기 위해 쓰는 Annotation이다.. 크롬과 파이어폭스에서만 써야한다. 한글이 깨지기 때문에 dispatcher-servlet설정해줘야 한다.
	public String board_update_ok(DataBoardVO vo, int page){
		System.out.println("board_update_ok");
		boolean bCheck=false;
		try {//pwd맞는지 확인
			String pwd=service.databoardGetPwd(vo.getNo());
			if (pwd.equals(vo.getPwd())) {
				bCheck=true;
				
				//파일이 보내졌는지 확인 
				List<MultipartFile> list=vo.getUpload();
				DataBoardVO fvo=service.databoardGetFileInfo(vo.getNo());
				if (list==null) {//파일업로드 안했다면 기존의 파일정보 그대로 입력
					vo.setFilename(fvo.getFilename());
					vo.setFilesize(fvo.getFilesize());
					vo.setFilecount(fvo.getFilecount());
					
				}else{//파일업로드 했다면 기존파일이 없었다면 그냥 업로드 기존파일이 있었다면 기존파일 지우고 다시 업로드  
					if (fvo.getFilecount()>0) {//기존파일 있었다면 지운다.
						String[] files=fvo.getFilename().split(",");
						for (String f : files) {
							File ff=new File("c:\\Upload\\"+f);
							ff.delete();
						}
						
					}
					
					//업로드 // 메소드화 시키면 편하겠다.
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
						
					}
					vo.setFilename(strName.substring(0, strName.lastIndexOf(",")));//stringtoken이용할때 에러안나게 맨마지막,를 지운다.
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
		//board_update_ok.jsp에 해당하는 내용이다. 익스플로러도 되려면 따로 만들어야 한다. 익스플로러에서 ajax를 통해서도 받을수 있다.
		if (bCheck==true) {
			
			send="<script>"
				+"location.href=\"board_content.do?no="+vo.getNo()+"&page="+page+"\";"
				+"</script>";
			
			
		}else{
			send="<script>"
				+ "alert(\"비밀번호가 틀립니다.\");"/*항상java에서 js쓸때 문장 마지막에 ;를 써주자*/
				+"history.back();"
				+"</script>";			
		}
		
		return send;
		
	}
	
}
