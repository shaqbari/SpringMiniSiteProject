package com.sist.databoard.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;

/**
 * @author user
 * ���
 *	C = C extends
 *	I = C implements
 *	C = I extends(error)
 *	I = I extends
 *
 */
//{call databoardListData(?, ?)}
public interface DataBoardMapper {

	@Select("SELECT no, subject, name, regdate, hit, num"
			+ " FROM ( SELECT no, subject, name, regdate, hit, rownum as num"
			+ " FROM ( SELECT no, subject, name, regdate, hit"
			+ "	FROM dataBoard ORDER BY no desc))"
			+ " WHERE num BETWEEN #{start} and #{end}")
	public List<DataBoardVO> databoardListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10) FROM dataBoard")//���������� ��������
	public int databoardTotalPage();
	
	@Insert("INSERT INTO databoard VALUES("
			+ "db_no_seq.nextVal, #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0,"
			+ "#{filename}, #{filesize}, #{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	//���뺸��
	//hit�� ����
	@Update("UPDATE databoard SET"
			+ " hit=hit+1"
			+ "WHERE no=#{no}")
	public void databoardHitIncrement(int no);
	/*
	 * 1)�����  : resultType=>������
	 * 2)�Ű����� : parameterType = >�Ű�����
	 * */	
	//���뺸��
	@Select("Select no, name, subject, content, regdate, hit,"
			+ " filename, filesize, filecount "
			+ " From databoard"
			+ " Where no=#{no}")
	public DataBoardVO databoardContentData(int no);
	
	//�����ϱ�
	@Select("Select pwd From dataBoard "
			+ " Where no=#{no}")
	public String databoardGetPwd(int no);
	
	//���ο� ���� ������ ������ ����� �ٽ� ���ε�, ������ �״�θ� �������ٽ� �־��ֱ�.
	@Select("Select filename, filesize, filecount"
			+ " From databoard"
			+ " Where no=#{no}")
	public DataBoardVO databoardGetFileInfo(int no);
	
	@Update("Update databoard SET"
			+ " name=#{name}, subject=#{subject}, content=#{content}, "
			+ " filename=#{filename}, filesize=#{filesize}, filecount=#{filecount}"
			+ " Where no=#{no}")//����ǥ �Ǽ� ���� ����.
	public void databoardUpdate(DataBoardVO vo);
	
	//�����ϱ�
	@Delete("Delete From dataBoard"
			+ " Where no=#{no}")
	public void dataBoardDelete(int no);
	
	
}
