package com.sist.temp;

import java.sql.*;

import oracle.jdbc.driver.OracleTypes;
public class EmpMainClass {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:ORCL";
			Connection conn=DriverManager.getConnection(url, "scott", "tiger");
			String sql="{Call empAllData(?)}";
			
			//�Ʒ��� ���ν��� ȣ���Ҷ� ���� Ŭ���� �̴�.
			CallableStatement cs=conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.executeUpdate();
			
			ResultSet rs=(ResultSet) cs.getObject(1);
			while (rs.next()) {
				System.out.println(rs.getInt(1)+" "
						+rs.getString(2)+" "
						+rs.getString(3)
				);
			}
			
		} catch (Exception e) {			
			e.getMessage();
			e.printStackTrace();
		}
	}
}
