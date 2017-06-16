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
				System.out.println("���Ӽ���");
			}else {
				System.out.println("���ӽ���");
				
			}
			
			conn.setAutoCommit(false);
			
			//�ٽ��ڵ� ==>AOP����  Around���� ������ ���� �ѹ��� ����ȴ�.
			
			
			/*for(int i=1; i<=31; i++){
				int count=(int)(Math.random()*7)+5;//����ð����� 5������ 11�� ����
				
				String sql="Insert Into reserve_day Values(?, ?)";
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, i);
				ps.setString(2, getRand(count));
				ps.executeUpdate();
				ps.close();
				
				
			}*/
			/*for(int i=1; i<=125; i++){
				int count=(int)(Math.random()*11)+10;//����ð����� 10�Ϻ��� 20�ϱ����� ����
				
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
			System.out.println("����Ϸ�!!");
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
					//�ٽ� autocommit�� �Ҵ�.
					
				} catch (Exception e3) {
					e3.printStackTrace();
					System.out.println("main e3"+e3.getMessage());
				}
			}
			
			e.printStackTrace();
			System.out.println("MainClass main()����  "+e.getMessage());
			
		}
		//AFTER-RETURNING ==>15�� �ߺ��� �ȵǾ� �Ѵ�.
		
		
	}
	
	//�ߺ����� ���� �߻� �� ������������ ���������ϰ�  ,�� ����
	public static String getRand(int count) {
		String res="";
		
		//�ߺ����� ���� �߻�
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
					//�������� ������ ���� ������ ������ ���� ���ٸ�					
					if (su==com[b]) {
						bDash=true;//for���� ��� ����.
						break;//�迭�� �������� �ʴ´�.
						
					}
					
				}
			}
			
			com[a]=su;
		}
		
		int[][] data=new int[5][5];
		
		
		//��������
		for(int i=0; i<com.length-1; i++){
			for(int j=i+1; j<com.length; j++ ){
				if (com[i]>com[j]) {
					int temp=com[i];
					com[i]=com[j];
					com[j]=temp;
				}
				
			}
			
		}
		
		//for�������� string buffer��� �Ѵ�.
		for(int i=0; i<com.length; i++){
			res+=com[i]+",";
			
		}
		res=res.substring(0, res.lastIndexOf(","));//������ ,�� ����.
		
		
		return res;
	}
	
}






