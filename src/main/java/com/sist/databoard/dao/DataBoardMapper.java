package com.sist.databoard.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;

/**
 * @author user
 * 상속
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
	
	@Select("SELECT CEIL(COUNT(*)/10) FROM dataBoard")//총페이지수 가져오기
	public int databoardTotalPage();
	
	@Insert("INSERT INTO databoard VALUES("
			+ "db_no_seq.nextVal, #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0,"
			+ "#{filename}, #{filesize}, #{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	//내용보기
	//hit수 증가
	@Update("UPDATE databoard SET"
			+ " hit=hit+1"
			+ "WHERE no=#{no}")
	public void databoardHitIncrement(int no);
	/*
	 * 1)결과값  : resultType=>리턴형
	 * 2)매개변수 : parameterType = >매개변수
	 * */	
	//내용보기
	@Select("Select no, name, subject, content, regdate, hit,"
			+ " filename, filesize, filecount "
			+ " From databoard"
			+ " Where no=#{no}")
	public DataBoardVO databoardContentData(int no);
	
	//수정하기
	@Select("Select pwd From dataBoard "
			+ " Where no=#{no}")
	public String databoardGetPwd(int no);
	
	//새로운 파일 들어오면 기존꺼 지우고 다시 업로드, 기존꺼 그대로면 기존꺼다시 넣어주기.
	@Select("Select filename, filesize, filecount"
			+ " From databoard"
			+ " Where no=#{no}")
	public DataBoardVO databoardGetFileInfo(int no);
	
	@Update("Update databoard SET"
			+ " name=#{name}, subject=#{subject}, content=#{content}, "
			+ " filename=#{filename}, filesize=#{filesize}, filecount=#{filecount}"
			+ " Where no=#{no}")//따옴표 실수 하지 말자.
	public void databoardUpdate(DataBoardVO vo);
	
	//삭제하기
	@Delete("Delete From dataBoard"
			+ " Where no=#{no}")
	public void dataBoardDelete(int no);
	
	
}
