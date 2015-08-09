package Model;

import java.io.Serializable;

public class getCart implements Serializable{
	private int cartid;
	private int prodid;
	private int catid;
	private int price;
	private int quantity;
	private int prodqty;
	private String brand;
	private String prodname;
	
	public getCart(int cartid, int prodid, int catid, int price, int quantity,
			String brand, String prodname, int prodqty) {
		super();
		this.cartid = cartid;
		this.prodid = prodid;
		this.catid = catid;
		this.price = price;
		this.quantity = quantity;
		this.brand = brand;
		this.prodname = prodname;
		this.prodqty = prodqty;
	}

	public int getProdqty() {
		return prodqty;
	}

	public void setProdqty(int prodqty) {
		this.prodqty = prodqty;
	}

	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
}
