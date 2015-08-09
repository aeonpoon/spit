package Model;

import java.sql.*;

public class voucherGenDB {
	private boolean success;
	
	public voucherGenDB(){
		
	}
	
	public Connection setupDBConn() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
		Connection conn = DriverManager.getConnection(connURL);
		return conn;
	}
	
	public boolean insertVoucher(int memid, String vcode){
		try{
			Connection conn = setupDBConn();
			String sql = "insert into coupons(memid, vcode, vcollect) values(?,?, current_timestamp)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			pstmt.setString(2, vcode);
			
			int rec = pstmt.executeUpdate();
			
			if(rec > 0){
				success = true;
			}else{
				success = false;
			}
			conn.close();
			return success;
		}catch(Exception e){
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	public voucherGen getvCode(int memid){
		try{
			Connection conn = setupDBConn();
			String sql = "select * from coupons where memid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			ResultSet rs = pstmt.executeQuery();
			voucherGen success;
			
			if(rs.next()){
				String code = rs.getString("vcode");
				voucherGen getcode = new voucherGen(code);
				success = getcode;
			}else{
				success = null;
			}
			conn.close();
			return success;
		}catch(Exception e){
			System.out.println("Error: " + e);
			return null;
		}
	}
	
	public boolean getVoucherDetails(int memid){
		try{
			Connection conn = setupDBConn();
			String sql = "select * from coupons where memid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				success = true;
			}else{
				success = false;
			}
			conn.close();
			return success;
		}catch(Exception e){
			System.out.println("Error: " + e);
			return false;
		}
	}
}
