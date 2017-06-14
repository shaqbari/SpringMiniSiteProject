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
	
	@Autowired
	private DataReplyMapper rMapper;
	
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
	
	public int replyCount(int bno) {
		
		return rMapper.replyCount(bno);
	}
	
	public List<DataReplyVO> replyListData(int bno){
		
		return rMapper.replyListData(bno);
	};
	
	public void replyNewInsert(DataReplyVO vo){
		
		rMapper.replyNewInsert(vo);
		
	};
	
	public void replyUpdate(DataReplyVO vo){
		
		rMapper.replyUpdate(vo);
	};
	
	//여기서 댓글의 댓글 삽입 메소드 조합
	public void replyReInsert(int root, DataReplyVO vo){
		//1.부모댓글정보가져오기
		DataReplyVO pvo=rMapper.replyParentInfoData(root);
		
		//2.상위 댓글의 이전의 댓글들의 gs증가
		rMapper.replyStepIncrement(pvo);
		
		//3. 댓글의 댓글 삽입
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(root);
		rMapper.replyReInsert(vo);
		//4. 상위 댓글의 depth증가
		rMapper.replyDepthIncrement(root);
		
	}
	
	//원래 트랜잭션 처리해야함
	public void replyDelete(int no){
		DataReplyVO vo=rMapper.replyGetDepthData(no);//depth와 root값이 들어있다.
		
		if (vo.getDepth()==0) {
			rMapper.replyDelete(no);			
		}else{
			rMapper.replyMsgUpdate(no);
		}
		rMapper.replyDepthDecrement(vo.getRoot());
		
	}
	
	public void boardDelete(int no){
		rMapper.replyAllDelete(no);
		bMapper.dataBoardDelete(no);
		
	}
	
}











