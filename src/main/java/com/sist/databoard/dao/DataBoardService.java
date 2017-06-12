package com.sist.databoard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/*
 * 1. Jsp(요청) : 링크 걸기 => board_content.do
 * 2. DispatcherServlet ==> DataBoardController
 * 3. 오라클에 있는 데이터 필요 ===> DataBoardController
 * 4. Model ==> JSP(출력)
 * 
 * */

@Service
public class DataBoardService {
	@Autowired
	private DataBoardMapper bMapper; //dispatcher-servlet.xml에서 interface를 구현하고 ssf를 받은걸 할당해 준다.
	
	/*@Autowired
	private DataReplyMapper rMapper;*/
	
	public List<DataBoardVO> databoardListData(Map map){		
		return bMapper.databoardListData(map);
	}
	
	public void databoardInsert(DataBoardVO vo){		
		bMapper.databoardInsert(vo);
	}
	
	//총페이지
	public int databoardTotalPage(){
		return bMapper.databoardTotalPage();
		
	};
	
	//내용보기
	public DataBoardVO dataBoardContentData(int no){
		
		//먼저 조회수 증가
		bMapper.databoardHitIncrement(no);
		
		//내용보기
		return bMapper.databoardContentData(no);
	}
	
	//수정위한 내용보기
	public DataBoardVO dataBoardUpdateData(int no){
		return bMapper.databoardContentData(no);
	}	
	//비밀번호가 맞는지 확인
	public String databoardGetPwd(int no){			
		return bMapper.databoardGetPwd(no);
		/*boolean bCheck=false;
		
		String db_pwd=bMapper.databoardGetPwd(vo.getNo());
		if (db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			bMapper.databoardUpdate(vo);
		}
		return bCheck;*/
	};
	//업로드된 파일 수정되었는지 확인 수정되었으면 파일삭제해야 한다.
	public DataBoardVO databoardGetFileInfo(int no) {
		
		return bMapper.databoardGetFileInfo(no);
	}
	//db수정하기
	public void databoardUpdate(DataBoardVO vo){		
		bMapper.databoardUpdate(vo);
	}
	
}
