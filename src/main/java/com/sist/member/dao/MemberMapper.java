package com.sist.member.dao;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
	@Select("Select zipcode, sido, gugun, dong, NVL(bunji, ' ') as bunji " //번지가 없으면 null이 뜨므로 대신 공백을 넣어야 한다.''가 아닌 ' '한칸 띄어야 한다.
			+ " From zipcode"
			+ " Where dong Like '%'||#{dong}||'%'")
	public List<ZipcodeVO> zipcodeListData(String dong);

	//rs.getString("zipcode")
	//rs.getString("NVL(bunji, ' ')") (X)==> rs.getString("bunji")
	//함수를 이용하게 되면 반드시 alias를 붙여야 한다.
	
	
	/*--------------------- 회원가입 -----------------------*/
	//id중복체크 //있냐없냐는 count로 check해야 한다.
	@Select("Select Count(*) From member"
			+ " Where id=#{id}")
	public int memberIdCheck(String id);	
	//회원정보 저장
	@Insert("Insert into member Values("
			+ " #{id}, #{pwd}, #{name}, #{sex}, #{bday}, #{email}, #{post}, #{addr1}, #{addr2}, #{tel}, 'n')")	
	public void memberInsert(MemberVO vo);
	
	/*-------------------로그인-----------------------*/ 
	//비밀번호확인
	@Select("Select pwd from member Where id=#{id}")
	public String memberGetPwd(String id);	
	//user정보 가져오기
	@Select("Select id, name, email, admin From member"
			+ " Where id=#{id}")
	public MemberVO memberInfoData(String id);
}
