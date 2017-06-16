package com.sist.food.dao;

import java.util.Date;

public class FoodResVO {
	private int res_no;
	private int house_no;
	private String id;
	private String res_date;
	private String res_time;
	private String res_state;
	private Date regdate;
	private FoodReserveVO fvo = new FoodReserveVO();

	public FoodReserveVO getFvo() {
		return fvo;
	}

	public void setFvo(FoodReserveVO fvo) {
		this.fvo = fvo;
	}

	public int getRes_no() {
		return res_no;
	}

	public void setRes_no(int res_no) {
		this.res_no = res_no;
	}

	public int getHouse_no() {
		return house_no;
	}

	public void setHouse_no(int house_no) {
		this.house_no = house_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRes_date() {
		return res_date;
	}

	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}

	public String getRes_time() {
		return res_time;
	}

	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}

	public String getRes_state() {
		return res_state;
	}

	public void setRes_state(String res_state) {
		this.res_state = res_state;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
