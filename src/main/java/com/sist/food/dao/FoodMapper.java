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
 *	���¼� �м�
 *	����Į�� : �ָ��� ������ ����.
 *	���� : �ָ��� ������ ����
 *	�����ϸ� ����100%
 */
public interface FoodMapper {
	
	//�ߺ����� �� ã��
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
			+ " Where rt=#{rt} AND rt_time>TO_CHAR(SYSDATE, 'HH24:MI')")//rt_time�� ����ð������� �ð��� �����´�.
	public String reserveRealTime(int rt);

	
	/*//����
	@SelectKey(keyProperty="res_no", resultType=int.class, before=true
			, statement="Select NVL(MAX(res_no)+1, 1) as res_no From foodres")//���������ε� �����ϴ�.
	@Insert("Insert Into foodres(res_no, house_no, id, res_date, res_date, res_inwon) Values("
			+ " #{res_no}, #{house_no}, #{id}, #{res_date}, #{res_time}, #{res_inwon})")
	public void foodResInsert(FoodResVO vo);*/
	@SelectKey(keyProperty="res_no",resultType=int.class, before=true,
			    statement="SELECT NVL(MAX(res_no)+1,1) as res_no FROM foodres")
	@Insert("INSERT INTO foodres(res_no,house_no,id,res_date,res_time,res_inwon) VALUES("
			 +" #{res_no}, #{house_no}, #{id}, #{res_date}, #{res_time}, #{res_inwon})")
	public void foodResInsert(FoodResVO vo);

	
	//View�� ���ٸ� ���ϴ�.!!
	//XML���� resultmap�� �ش�
	@Results({			
		@Result(property="res_no", column="res_no")
		//, javaType="java.lang.Integer", jdbcType="INTEGER"������ �� �Է��ؾ������� �������� date�� timestamp���� ����
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
			+ " Where id=#{id} AND house_no=no") //join���� �����Դ�.
	public List<FoodResVO> foodMyPage(String id);
	
/*	@Select("Select no, name, tel, image, addr From food"
			+ " Where no=#{no}")
	public FoodReserveVO  */
	
	@Results({			
		@Result(property="res_no", column="res_no")
		//, javaType="java.lang.Integer", jdbcType="INTEGER"������ �� �Է��ؾ������� �������� date�� timestamp���� ����
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
			+ " Where house_no=no") //join���� �����Դ�.
	public List<FoodResVO> foodAdminPage();
	
	@Update("UPDATE foodres SET"
			+ " res_state='y'"
			+ " WHERE res_no=#{res_no}")
	public void resStateChange(int res_no);
	
}








