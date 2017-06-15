package com.sist.food.dao;

public class FoodReserveVO {
	/*
	 * no
	 * name
	 * kind
	 * addr
	 * tel
	 * image
	 * reserve ==> ��¥(time)
	 * 
	 * 
	 * ����Ư���� ������ ���ǵ� 202-6
	 *                              ó����ġ				            ó������ ���°()
	 * Select DISTINCT SUBSTR(addr, instr(addr, ' ', 1, 1)+1, length(instr(addr, ' ', 1, 2))+1) as gu
			From food"
			Where kind=#{kind} Order by gu asc
	 * */
	
	private int no;
	private String name;
	private String kind;
	private String addr;
	private String tel;
	private String image;
	private String gu;
	private String poster; //contoller���� image�� ,�� �и��ؼ� �� ó���Ÿ�
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getGu() {
		return gu;
	}
	public void setGu(String gu) {
		this.gu = gu;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	
	
	
	
	
	
}


