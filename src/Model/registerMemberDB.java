package Model;

import java.sql.*;

public class registerMemberDB {
	private boolean success;
	
	public Connection setupDBConn()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
		Connection conn = DriverManager.getConnection(connURL);
		return conn;
	}
	
	public boolean createMember(registerMember reg){
		try{
			Connection conn = setupDBConn();
			String sql = "insert into member(email, password, name, gender, dobday, dobmonth, dobyear, address, contactno, subscribe, joindate) values(?,?,?,?,?,?,?,?,?,?,current_timestamp)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reg.getEmail());
			pstmt.setString(2, reg.getPassword());
			pstmt.setString(3, reg.getName());
			pstmt.setString(4, reg.getGender());
			pstmt.setInt(5, Integer.parseInt(reg.getDobday()));
			pstmt.setString(6, reg.getDobmonth());
			pstmt.setInt(7, Integer.parseInt(reg.getDobyear()));
			pstmt.setString(8, reg.getAddress());
			pstmt.setInt(9, Integer.parseInt(reg.getContactno()));
			pstmt.setString(10, reg.getSubscribe());
			
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
	
	public boolean checkemail(String email){
		try{
			Connection conn = setupDBConn();
			String sql = "select email from member where email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, email);
			
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
	
	public registerMember updateMember(int memid, String email, String enpass, String password, String name,
			String gender, String dobday, String dobmonth, String dobyear,
			String address, String contactno, String subscribe){
		try{
			Connection conn = setupDBConn();
			String sql = "update member set password=?, name=?, gender=?, dobday=?, dobmonth=?, dobyear=?, address=?, contactno=?, subscribe=? where memid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, enpass);
			pstmt.setString(2, name);
			pstmt.setString(3, gender);
			pstmt.setInt(4, Integer.parseInt(dobday));
			pstmt.setString(5, dobmonth);
			pstmt.setInt(6, Integer.parseInt(dobyear));
			pstmt.setString(7, address);
			pstmt.setInt(8, Integer.parseInt(contactno));
			pstmt.setString(9, subscribe);
			pstmt.setInt(10, memid);
			
			int rec = pstmt.executeUpdate();
			registerMember success;
			if(rec > 0){
				registerMember memDetails = new registerMember(memid, email, password, name, gender, dobday, dobmonth, dobyear, address, contactno, subscribe);
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
	
	public boolean encryptPass(String email, byte[] password){
		try{
			Connection conn = setupDBConn();
			String sql = "insert into crypt(email,enpass) values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setBytes(2, password);
			
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
	
	public boolean enpassString(String email, String password){
		try{
			Connection conn = setupDBConn();
			String sql = "update member set password=? where email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			
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
	
	public boolean encryptUpdatePass(String email, byte[] password){
		try{
			Connection conn = setupDBConn();
			String sql = "update crypt set enpass = ? where email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setBytes(1, password);
			pstmt.setString(2, email);
			
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
}
