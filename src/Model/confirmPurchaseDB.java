package Model;

import java.sql.*;
import java.util.ArrayList;

public class confirmPurchaseDB {
	public Connection setupDBConn()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
		Connection conn = DriverManager.getConnection(connURL);
		return conn;
	}
	
	public ArrayList<confirmPurchase> getTransactionID(int memid){
		try{
			Connection conn = setupDBConn();
			
			String sql = "select * from transaction where memid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<confirmPurchase> al = new ArrayList<confirmPurchase>();
			
			while(rs.next()){
				int transactionID = rs.getInt("transactionID");
				
				confirmPurchase cp = new confirmPurchase(transactionID);
				al.add(cp);
			}
			
			conn.close();
			return al;
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return null;
		}
	}
	
	public ArrayList<confirmPurchase> displayPurchaseDetails(int memid){
		try{
			Connection conn = setupDBConn();
			
			String sql = "select * from transaction where memid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<confirmPurchase> al = new ArrayList<confirmPurchase>();
			
			while(rs.next()){
				int transactionID = rs.getInt("transactionID");
				int totalprice = rs.getInt("totalprice");
				String cctype = rs.getString("cctype");
				String ccnum = rs.getString("ccnum");
				String ccname = rs.getString("ccname");
				String vcode = rs.getString("vcode");
				String purchasedate = rs.getString("transdate");
				
				confirmPurchase cp = new confirmPurchase(transactionID, totalprice, cctype, ccnum, ccname, vcode, purchasedate);
				al.add(cp);
			}
			
			conn.close();
			return al;
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return null;
		}
	}

	public ArrayList<confirmPurchase> displayRecentPurchase(int memid){
		try{
			Connection conn = setupDBConn();
			
			String sql = "select * from transaction_has_confirmpurchase where memid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<confirmPurchase> al = new ArrayList<confirmPurchase>();
			
			while(rs.next()){
				int transactionID = rs.getInt("transactionID");
				String brand = rs.getString("brand");
				String prodname = rs.getString("prodname");
				int unitprice = rs.getInt("unitprice");
				int quantity = rs.getInt("quantity");
				int subtotal = rs.getInt("subtotal");
				confirmPurchase cp = new confirmPurchase(transactionID, brand, prodname, unitprice, quantity, subtotal);
				al.add(cp);
			}
			
			conn.close();
			return al;
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return null;
		}
	}
	
}
