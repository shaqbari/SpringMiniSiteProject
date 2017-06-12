package com.sist.member.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
	@Select("Select zipcode, sido, gugun, dong, NVL(bunji, ' ') as bunji " //번지가 없으면 null이 뜨므로 대신 공백을 넣어야 한다.''가 아닌 ' '한칸 띄어야 한다.
			+ " From zipcode"
			+ " Where dong Like '%'||#{dong}||'%'")
	public List<ZipcodeVO> zipcodeListData(String dong);

	//rs.getString("zipcode")
	//rs.getString("NVL(bunji, ' ')") (X)==> rs.getString("bunji")
	//함수를 이용하게 되면 반드시 alias를 붙여야 한다.
	
	
	
}
