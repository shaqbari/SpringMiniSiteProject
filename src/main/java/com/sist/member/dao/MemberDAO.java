package com.sist.member.dao;

import java.util.List;

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
}
