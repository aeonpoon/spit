package Model;

import java.sql.*;
import java.util.ArrayList;

public class commentDB {
	public Connection setupDBConn()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
		Connection conn = DriverManager.getConnection(connURL);
		return conn;
	}
	
	public commentDB(){
		
	}
	
	public boolean delComment(String email, int delId){
		try{
			Connection conn = setupDBConn();
			
			String sql = "delete from comments where email=? and id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setInt(2, delId);
			
			int rec = pstmt.executeUpdate();
			boolean success;
			
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
	
	public ArrayList<comment> viewComment(String email){
		try{
			Connection conn = setupDBConn();
			
			String sql = "select * from comments C, product P, category CAT where C.prodid = P.prodid and C.catid = CAT.catid and email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<comment> al = new ArrayList<comment>();
							
			while(rs.next()){
				int comid = rs.getInt("id");
				String catname = rs.getString("catname");
				String brand = rs.getString("brand");
				String prodname = rs.getString("prodname");
				String comment = rs.getString("comment");
				String ratings = rs.getString("ratings");
				
				comment getcomment = new comment(comid, catname, brand, prodname, comment, ratings);
				if(getcomment != null){
					al.add(getcomment);
				}
			}
			
			conn.close();
			return al;
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return null;
		}
	}
}
