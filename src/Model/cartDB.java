package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class cartDB {
	private boolean success;
	
	public Connection setupDBConn()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
		Connection conn = DriverManager.getConnection(connURL);
		return conn;
	}
	
	public boolean insertcart(int memid){
		try{
			Connection conn = setupDBConn();
			String sql = "insert into cart(memid) values(?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			int rec = pstmt.executeUpdate();
			
			if(rec > 0){
				return true;
			}
			else{
				return false;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	public int getcartid(int memid){
		try{
			Connection conn = setupDBConn();
			String sql = "select * from cart where memid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				int cartid = rs.getInt("cartid");
				return cartid;
			}
			else{
				return 0;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return 0;
		}
	}
	
	public boolean insertcarthasproduct(int cartid, int prodid, int catid, int quantity){
		try{
			Connection conn = setupDBConn();
			String sql = "insert into cart_has_product(cartid, prodid, catid, quantity) values(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cartid);
			pstmt.setInt(2, prodid);
			pstmt.setInt(3, catid);
			pstmt.setInt(4, quantity);
			
			int rec = pstmt.executeUpdate();
			
			if(rec > 0){
				return true;
			}
			else{
				return false;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	
	public boolean delProd(int prodid, int cartid){
		try{
			Connection conn = setupDBConn();
			String sql = "delete from cart_has_product where prodid=? and cartid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, prodid);
			pstmt.setInt(2, cartid);
						
			int rec = pstmt.executeUpdate();
			
			if(rec > 0){
				return true;
			}
			else{
				return false;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	
	public boolean upProd(int cartid, int prodid, int quantity){
		try{
			Connection conn = setupDBConn();
			String sql = "update cart_has_product set quantity=? where prodid=? and cartid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, prodid);
			pstmt.setInt(3, cartid);
			
			int rec = pstmt.executeUpdate();
			
			if(rec > 0){
				return true;
			}
			else{
				return false;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	
	public ArrayList<getCart> displayCart(int memid){
		try{
			Connection conn = setupDBConn();
			
			String sql = "select * from cart C, product P, cart_has_product CP where P.prodid = CP.prodid and P.catid = CP.catid and C.cartid = CP.cartid and memid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<getCart> al = new ArrayList<getCart>();
			
			while(rs.next()){
				int cartid = rs.getInt("cartid");
				int prodid = rs.getInt("CP.prodid");
				int catid = rs.getInt("catid");
				int price = rs.getInt("price");
				int quantity = rs.getInt("CP.quantity");
				int prodqty = rs.getInt("P.quantity");
				String brand = rs.getString("brand");
				String prodname = rs.getString("prodname");
				
				getCart dc = new getCart(cartid, prodid, catid, price, quantity, brand, prodname, prodqty);
				al.add(dc);
			}
			
			conn.close();
			return al;
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return null;
		}
	}
	
	public boolean removeCart(int memid){
		try{
			Connection conn = setupDBConn();
			
			String sql = "delete from cart where memid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
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
	
	public boolean removeprodfromCart(int prodid, int cartid){
		try{
			Connection conn = setupDBConn();
			
			String sql = "delete from cart_has_product where prodid=? and cartid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, prodid);
			pstmt.setInt(2, cartid);
			
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
	
	public boolean orderProd(int memid, int totalprice, String cctype, String ccnum, String ccname, int expdateM, int expdateY, int cvcnum, String vcode){
		try{
			Connection conn = setupDBConn();
			String sql = "insert into transaction(memid, totalprice, cctype, ccnum, ccname, expdateM, expdateY, cvcnum, vcode, transdate) values(?,?,?,?,?,?,?,?,?,current_timestamp)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			pstmt.setInt(2, totalprice);
			pstmt.setString(3, cctype);
			pstmt.setString(4, ccnum);
			pstmt.setString(5, ccname);
			pstmt.setInt(6, expdateM);
			pstmt.setInt(7, expdateY);
			pstmt.setInt(8, cvcnum);
			pstmt.setString(9, vcode);
			
			int rec = pstmt.executeUpdate();
			
			if(rec > 0){
				return true;
			}
			else{
				return false;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	public int getTransID(int memid){
		try{
			Connection conn = setupDBConn();
			String sql = "select max(transactionID)'transactionID' from transaction where memid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				int transid = rs.getInt("transactionID");
				
				return transid;
			}
			else{
				return 0;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return 0;
		}
	}
	
	
	public boolean confirmpurchaseProd(int transid, int memid, int confirmpurchaseID, int prodid, String brand, String prodname, int unitprice, int quantity, int subtotal){
		try{
			Connection conn = setupDBConn();
			String sql = "insert into transaction_has_confirmpurchase(transactionID, memid, confirmpurchaseID, prodid, brand, prodname, unitprice, quantity, subtotal, purchasedate) values(?,?,?,?,?,?,?,?,?, current_timestamp)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, transid);
			pstmt.setInt(2, memid);
			pstmt.setInt(3, confirmpurchaseID);
			pstmt.setInt(4, prodid);
			pstmt.setString(5, brand);
			pstmt.setString(6, prodname);
			pstmt.setInt(7, unitprice);
			pstmt.setInt(8, quantity);
			pstmt.setInt(9, subtotal);
			
			int rec = pstmt.executeUpdate();
			
			if(rec > 0){
				return true;
			}
			else{
				return false;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	
	public boolean memidForConfirmPurchase(int memid){
		try{
			Connection conn = setupDBConn();
			String sql = "insert into confirmpurchase(memid) values(?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			int rec = pstmt.executeUpdate();
			
			if(rec > 0){
				return true;
			}
			else{
				return false;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	
	public int getConfirmPurchaseID(int memid){
		try{
			Connection conn = setupDBConn();
			String sql = "select max(confirmpurchaseID)'confirmpurchaseID' from confirmpurchase where memid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memid);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				int confirmpurchaseID = rs.getInt("confirmpurchaseID");
				
				return confirmpurchaseID;
			}
			else{
				return 0;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return 0;
		}
	}
	
	public boolean removeqtyfromproduct(int prodid, int catid, int quantity){
		try{
			Connection conn = setupDBConn();
			String sql = "update product set quantity=? where prodid = ? and catid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, prodid);
			pstmt.setInt(3, catid);
			
			int rec = pstmt.executeUpdate();
			
			if(rec > 0){
				return true;
			}
			else{
				return false;
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
			return false;
		}
	}
}
