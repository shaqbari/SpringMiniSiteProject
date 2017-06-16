package com.sist.temp;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

public class MainClass {
	public static void main(String[] args) {
		Connection conn=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:ORCL";
			conn=DriverManager.getConnection(url, "scott", "tiger");
			if (conn!=null) {
				System.out.println("접속성공");
			}else {
				System.out.println("접속실패");
				
			}
			
			conn.setAutoCommit(false);
			
			//핵심코딩 ==>AOP에서  Around쓰면 이전과 이후 한번씩 수행된다.
			
			
			/*for(int i=1; i<=31; i++){
				int count=(int)(Math.random()*7)+5;//예약시간때가 5개에서 11개 나옴
				
				String sql="Insert Into reserve_day Values(?, ?)";
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, i);
				ps.setString(2, getRand(count));
				ps.executeUpdate();
				ps.close();
				
				
			}*/
			/*for(int i=1; i<=125; i++){
				int count=(int)(Math.random()*11)+10;//예약시간때가 10일부터 20일까지가 나옴
				
				String sql="Update food SET"
						+ " res_day=?"
						+ " Where no=?";
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, getRand(count));
				ps.setInt(2, i);
				ps.executeUpdate();
				ps.close();
				
				
			}*/
			
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
			System.out.println(sdf.format(date));
			conn.commit();
			System.out.println("저장완료!!");
			/*int[] com=getRand(7);
			for (int i : com) {
				System.out.println(i+", ");
			}*/
			
		} catch (Exception e) {
			
			try {
				conn.rollback(); //AFTER-Throwing
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("main e2"+e2.getMessage());
				
			} finally {
				try {
					conn.setAutoCommit(true); //AFTER
					//다시 autocommit를 켠다.
					
				} catch (Exception e3) {
					e3.printStackTrace();
					System.out.println("main e3"+e3.getMessage());
				}
			}
			
			e.printStackTrace();
			System.out.println("MainClass main()에서  "+e.getMessage());
			
		}
		//AFTER-RETURNING ==>15개 중복은 안되야 한다.
		
		
	}
	
	//중복없는 난수 발생 후 오름차순으로 선택정렬하고  ,로 연결
	public static String getRand(int count) {
		String res="";
		
		//중복없는 난수 발생
		int[] com=new int[count];
		int su=0;
		boolean bDash=false;
		
		for(int a=0; a<count; a++){
			bDash=true;
			
			while (bDash) {
				//su=(int)(Math.random()*15)+1;
				su=(int)(Math.random()*31)+1;//1~31
				bDash=false;
				
				for(int b=0; b<a; b++){
					//랜덤으로 생성된 수가 이전에 생성된 수와 같다면					
					if (su==com[b]) {
						bDash=true;//for문이 계속 돈다.
						break;//배열에 저장하지 않는다.
						
					}
					
				}
			}
			
			com[a]=su;
		}
		
		int[][] data=new int[5][5];
		
		
		//선택정렬
		for(int i=0; i<com.length-1; i++){
			for(int j=i+1; j<com.length; j++ ){
				if (com[i]>com[j]) {
					int temp=com[i];
					com[i]=com[j];
					com[j]=temp;
				}
				
			}
			
		}
		
		//for문에서는 string buffer써야 한다.
		for(int i=0; i<com.length; i++){
			res+=com[i]+",";
			
		}
		res=res.substring(0, res.lastIndexOf(","));//마지막 ,를 뺀다.
		
		
		return res;
	}
	
}






