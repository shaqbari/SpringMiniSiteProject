package com.sist.databoard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/*
 * 1. Jsp(��û) : ��ũ �ɱ� => board_content.do
 * 2. DispatcherServlet ==> DataBoardController
 * 3. ����Ŭ�� �ִ� ������ �ʿ� ===> DataBoardController
 * 4. Model ==> JSP(���)
 * 
 * */

@Service
public class DataBoardService {
	@Autowired
	private DataBoardMapper bMapper; //dispatcher-servlet.xml���� interface�� �����ϰ� ssf�� ������ �Ҵ��� �ش�.
	
	@Autowired
	private DataReplyMapper rMapper;
	
	public List<DataBoardVO> databoardListData(Map map){		
		return bMapper.databoardListData(map);
	}
	
	public void databoardInsert(DataBoardVO vo){		
		bMapper.databoardInsert(vo);
	}
	
	//��������
	public int databoardTotalPage(){
		return bMapper.databoardTotalPage();
		
	};
	
	//���뺸��
	public DataBoardVO dataBoardContentData(int no){
		
		//���� ��ȸ�� ����
		bMapper.databoardHitIncrement(no);
		
		//���뺸��
		return bMapper.databoardContentData(no);
	}
	
	//�������� ���뺸��
	public DataBoardVO dataBoardUpdateData(int no){
		return bMapper.databoardContentData(no);
	}	
	//��й�ȣ�� �´��� Ȯ��
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
	//���ε�� ���� �����Ǿ����� Ȯ�� �����Ǿ����� ���ϻ����ؾ� �Ѵ�.
	public DataBoardVO databoardGetFileInfo(int no) {
		
		return bMapper.databoardGetFileInfo(no);
	}
	//db�����ϱ�
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
	
	//���⼭ ����� ��� ���� �޼ҵ� ����
	public void replyReInsert(int root, DataReplyVO vo){
		//1.�θ���������������
		DataReplyVO pvo=rMapper.replyParentInfoData(root);
		
		//2.���� ����� ������ ��۵��� gs����
		rMapper.replyStepIncrement(pvo);
		
		//3. ����� ��� ����
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(root);
		rMapper.replyReInsert(vo);
		//4. ���� ����� depth����
		rMapper.replyDepthIncrement(root);
		
	}
	
	//���� Ʈ����� ó���ؾ���
	public void replyDelete(int no){
		DataReplyVO vo=rMapper.replyGetDepthData(no);//depth�� root���� ����ִ�.
		
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











