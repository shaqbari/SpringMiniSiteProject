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
	
	public String reserveDayData(int no){
		
		return fMapper.reserveDayData(no);
	};
	
	public String reserveTimeData(int rd){
		
		return fMapper.reserveTimeData(rd);
	};
	
	public String reserveRealTime(int rt){
		
		return fMapper.reserveRealTime(rt);
	};

	
	public void foodResInsert(FoodResVO vo){
		
		fMapper.foodResInsert(vo);
	};
	
	
	public List<FoodResVO> foodMyPage(String id){;

		return fMapper.foodMyPage(id);

	}
