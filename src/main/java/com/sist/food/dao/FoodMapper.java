package com.sist.food.dao;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

/**
 * @author user
 *	
 *	형태소 분석
 *	몬테칼로 : 애매한 예측에 쓴다.
 *	퍼지 : 애매한 예측에 쓴다
 *	가능하면 서류100%
 */
public interface FoodMapper {
	
	//중복없는 구 찾기
	// rs.getString("gu") --> setGu(rs.getString("gu"))
	@Select("Select DISTINCT SUBSTR(addr, instr(addr, ' ', 1, 1)+1, instr(addr, ' ', 1, 2)-instr(addr, ' ', 1, 1) ) as gu"
			+ " From food"
			+ " Where kind=#{kind} Order by gu asc")
	public List<String> reserveLocData(String kind);
	
	@Select("Select no, name, tel, addr, image FROM food"
			+ " WHERE addr LIKE '%'||#{addr}||'%' AND kind=#{kind}")
	public List<FoodReserveVO> reserveListData(Map map);
	
	@Select("Select res_day FROM food"
			+ " Where no=#{no}")
	public String reserveDayData(int no);
	
	
	@Select("Select rd_time From reserve_day"
			+ " Where rd=#{rd}")
	public String reserveTimeData(int rd);
	
	@Select("Select rt_time From reserve_time"
			+ " Where rt=#{rt} AND rt_time>TO_CHAR(SYSDATE, 'HH24:MI')")//rt_time이 현재시간이후인 시간만 가져온다.
	public String reserveRealTime(int rt);

	
	/*//예약
	@SelectKey(keyProperty="res_no", resultType=int.class, before=true
			, statement="Select NVL(MAX(res_no)+1, 1) as res_no From foodres")//서브쿼리로도 가능하다.
	@Insert("Insert Into foodres(res_no, house_no, id, res_date, res_date, res_inwon) Values("
			+ " #{res_no}, #{house_no}, #{id}, #{res_date}, #{res_time}, #{res_inwon})")
	public void foodResInsert(FoodResVO vo);*/
	@SelectKey(keyProperty="res_no",resultType=int.class, before=true,
			    statement="SELECT NVL(MAX(res_no)+1,1) as res_no FROM foodres")
	@Insert("INSERT INTO foodres(res_no,house_no,id,res_date,res_time,res_inwon) VALUES("
			 +" #{res_no}, #{house_no}, #{id}, #{res_date}, #{res_time}, #{res_inwon})")
	public void foodResInsert(FoodResVO vo);

	
	//View를 쓴다면 편하다.!!
	//XML에서 resultmap에 해당
	@Results({			
		@Result(property="res_no", column="res_no")
		//, javaType="java.lang.Integer", jdbcType="INTEGER"원래는 다 입력해야하지만 생략가능 date나 timestamp에서 유용
		//setRes_no(rs.getInt("res_no"));
		, @Result(property="house_no", column="house_no")
		, @Result(property="id", column="id")
		, @Result(property="res_date", column="res_date")
		, @Result(property="res_time", column="res_time")
		, @Result(property="res_inwon", column="res_inwon")
		, @Result(property="res_state", column="res_state")
		, @Result(property="regdate", column="regdate")
		, @Result(property="fvo.name", column="name")
		// fvo.setName(rs.getString("name"));
		, @Result(property="fvo.addr", column="addr")
		, @Result(property="fvo.image", column="image")
		, @Result(property="fvo.tel", column="tel")
		}
	)
	@Select("Select res_no, house_no, id, res_date, res_time, res_inwon, res_state, regdate"
			+ " , name, addr, image, tel"
			+ " From foodres, food"
			+ " Where id=#{id} AND house_no=no") //join으로 가져왔다.
	public List<FoodResVO> foodMyPage(String id);
	
/*	@Select("Select no, name, tel, image, addr From food"
			+ " Where no=#{no}")
	public FoodReserveVO  */
	
	@Results({			
		@Result(property="res_no", column="res_no")
		//, javaType="java.lang.Integer", jdbcType="INTEGER"원래는 다 입력해야하지만 생략가능 date나 timestamp에서 유용
		//setRes_no(rs.getInt("res_no"));
		, @Result(property="house_no", column="house_no")
		, @Result(property="res_date", column="res_date")
		, @Result(property="res_time", column="res_time")
		, @Result(property="res_inwon", column="res_inwon")
		, @Result(property="res_state", column="res_state")
		, @Result(property="regdate", column="regdate")
		, @Result(property="fvo.name", column="name")
		// fvo.setName(rs.getString("name"));
		, @Result(property="fvo.addr", column="addr")
		, @Result(property="fvo.image", column="image")
		, @Result(property="fvo.tel", column="tel")
		}
	)
	@Select("Select res_no, house_no, id, res_date, res_time, res_inwon, res_state, regdate"
			+ " , name, addr, image, tel"
			+ " From foodres, food"
			+ " Where house_no=no") //join으로 가져왔다.
	public List<FoodResVO> foodAdminPage();
	
	@Update("UPDATE foodres SET"
			+ " res_state='y'"
			+ " WHERE res_no=#{res_no}")
	public void resStateChange(int res_no);
	
}








