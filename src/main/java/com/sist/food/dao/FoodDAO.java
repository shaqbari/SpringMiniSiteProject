package com.sist.food.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper fMapper;
	
	public List<String> reserveLocData(String kind){
			
		return fMapper.reserveLocData(kind);
	};

	
	public List<FoodReserveVO> reserveListData(Map map){
		
		return fMapper.reserveListData(map);
	};

}
