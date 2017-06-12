package com.sist.member.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
	@Select("Select zipcode, sido, gugun, dong, NVL(bunji, ' ') as bunji " //������ ������ null�� �߹Ƿ� ��� ������ �־�� �Ѵ�.''�� �ƴ� ' '��ĭ ���� �Ѵ�.
			+ " From zipcode"
			+ " Where dong Like '%'||#{dong}||'%'")
	public List<ZipcodeVO> zipcodeListData(String dong);

	//rs.getString("zipcode")
	//rs.getString("NVL(bunji, ' ')") (X)==> rs.getString("bunji")
	//�Լ��� �̿��ϰ� �Ǹ� �ݵ�� alias�� �ٿ��� �Ѵ�.
	
	
	
}
