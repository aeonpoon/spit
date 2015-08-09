package Model;

import java.sql.*;

public class LoginDB {
	private boolean success;
	
	public Connection setupDBConn()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
		Connection conn = DriverManager.getConnection(connURL);
		return conn;
	}
	
	public registerMember loginMember(String loginid, String password){
		try{
			Connection conn = setupDBConn();
			String sql = "select * from member where email=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			
			pstmt.setString(1, loginid);
			
			ResultSet rs = pstmt.executeQuery();
			registerMember success;
			
			if(rs.next()){
				int memid = rs.getInt("memid");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String dobday = rs.getString("dobday");
				String dobmonth = rs.getString("dobmonth");
				String dobyear = rs.getString("dobyear");
				String address = rs.getString("address");
				String contactno = rs.getString("contactno");
				String sub = rs.getString("subscribe");
				registerMember memDetails = new registerMember(memid, email, password, name, gender, dobday, dobmonth, dobyear, address, contactno, sub);
				
				success = memDetails;
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
	
	public byte[] passVerify(String email){
		try{
			Connection conn = setupDBConn();
			String sql = "select * from crypt where email=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			byte[] success;
			
			if(rs.next()){
				byte[] enpass = rs.getBytes("enpass");
				success = enpass;
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
	
	public boolean loginAdmin(String loginid, String password){
		try{
			Connection conn = setupDBConn();
			String sql = "select * from login where loginid=? and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			
			pstmt.setString(1, loginid);
			pstmt.setString(2, password);
			
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
	
	public byte[] forgetPass(String email){
		try{
			Connection conn = setupDBConn();
			String sql = "select * from crypt where email=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			
			pstmt.setString(1, email);
			byte[] success;
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				byte[] pass = rs.getBytes("enpass");
				success = pass;
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
}
