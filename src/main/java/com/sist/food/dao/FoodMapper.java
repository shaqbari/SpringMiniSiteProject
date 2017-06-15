package com.sist.food.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;

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
	
}




