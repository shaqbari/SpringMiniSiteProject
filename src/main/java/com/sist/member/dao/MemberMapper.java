package com.sist.member.dao;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
	@Select("Select zipcode, sido, gugun, dong, NVL(bunji, ' ') as bunji " //������ ������ null�� �߹Ƿ� ��� ������ �־�� �Ѵ�.''�� �ƴ� ' '��ĭ ���� �Ѵ�.
			+ " From zipcode"
			+ " Where dong Like '%'||#{dong}||'%'")
	public List<ZipcodeVO> zipcodeListData(String dong);

	//rs.getString("zipcode")
	//rs.getString("NVL(bunji, ' ')") (X)==> rs.getString("bunji")
	//�Լ��� �̿��ϰ� �Ǹ� �ݵ�� alias�� �ٿ��� �Ѵ�.
	
	
	/*--------------------- ȸ������ -----------------------*/
	//id�ߺ�üũ //�ֳľ��Ĵ� count�� check�ؾ� �Ѵ�.
	@Select("Select Count(*) From member"
			+ " Where id=#{id}")
	public int memberIdCheck(String id);	
	//ȸ������ ����
	@Insert("Insert into member Values("
			+ " #{id}, #{pwd}, #{name}, #{sex}, #{bday}, #{email}, #{post}, #{addr1}, #{addr2}, #{tel}, 'n')")	
	public void memberInsert(MemberVO vo);
	
	/*-------------------�α���-----------------------*/ 
	//��й�ȣȮ��
	@Select("Select pwd from member Where id=#{id}")
	public String memberGetPwd(String id);	
	//user���� ��������
	@Select("Select id, name, email, admin From member"
			+ " Where id=#{id}")
	public MemberVO memberInfoData(String id);
}
