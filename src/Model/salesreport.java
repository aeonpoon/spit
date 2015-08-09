package Model;

import java.io.Serializable;

public class salesreport implements Serializable{
	private String prodname;
	private int quantity;
	private int month;
	private int year;
	
	public salesreport(String prodname, int quantity, int month, int year) {
		super();
		this.prodname = prodname;
		this.quantity = quantity;
		this.month = month;
		this.year = year;
	}

	public salesreport(String prodname, int quantity, int year) {
		super();
		this.prodname = prodname;
		this.quantity = quantity;
		this.year = year;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}
