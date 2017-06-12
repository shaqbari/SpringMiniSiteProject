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
	
	/*@Autowired
	private DataReplyMapper rMapper;*/
	
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
	
}
