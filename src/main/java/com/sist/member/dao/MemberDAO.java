package com.sist.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author user
 *	������ ZipcodeDAO�� ���� ���� Service�� ������ �Ѵ�.
 */
@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mMapper;
	
	public List<ZipcodeVO> zipcodeListData(String dong){
		
		return mMapper.zipcodeListData(dong);
	}
	
	public int memberIdCheck(String id){
		
		return mMapper.memberIdCheck(id);
	}
	
	public void memberInsert(MemberVO vo){
		
		mMapper.memberInsert(vo);
	};
	
	public String memberGetPwd(String id){
		
		return mMapper.memberGetPwd(id);
	};	
	
	
	public MemberVO memberInfoData(String id){
		
		return mMapper.memberInfoData(id);
	};
}
