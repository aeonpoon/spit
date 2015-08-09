package Model;

import java.sql.*;
import java.util.*;

public class salesreportDB {
	public Connection setupDBConn()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
		Connection conn = DriverManager.getConnection(connURL);
		return conn;
	}
	
	public ArrayList<salesreport> getMonthlyreport(int month, int year){
		try{
			Connection conn = setupDBConn();
			String sql = "select CONCAT(brand, ' ', prodname)'Product', SUM(quantity)'Total Quantity' from transaction_has_confirmpurchase where Month(purchasedate) = ? and Year(purchasedate) = ? group by prodname order by SUM(quantity) desc limit 10";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<salesreport> al = new ArrayList<salesreport>();
			
			while(rs.next()){
				String prodname = rs.getString("Product");
				int quantity = rs.getInt("Total Quantity");
				
				salesreport rp = new salesreport(prodname, quantity);
				al.add(rp);
			}
			
			conn.close();
			return al;
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return null;
		}
		
	}
	
	public ArrayList<salesreport> getYearlyreport(int year){
		try{
			Connection conn = setupDBConn();
			String sql = "select prodname, SUM(quantity)'Total Quantity' from transaction_has_confirmpurchase where Year(purchasedate) = ? group by prodname order by SUM(quantity) desc limit 10";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, year);
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<salesreport> al = new ArrayList<salesreport>();
			
			while(rs.next()){
				String prodname = rs.getString("prodname");
				int quantity = rs.getInt("Total Quantity");
				
				salesreport rp = new salesreport(prodname, quantity);
				al.add(rp);
			}
			
			conn.close();
			return al;
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return null;
		}
		
	}
	
}
