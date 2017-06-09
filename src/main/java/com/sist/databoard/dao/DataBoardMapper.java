package com.sist.databoard.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.*;

/**
 * @author user
 * »ó¼Ó
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
	
	@Insert("INSERT INTO databoard VALUES("
			+ "db_no_seq.nextVal, #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0,"
			+ "#{filename}, #{filesize}, #{filecount})")
	public void databoardInsert(DataBoardVO vo);


}
