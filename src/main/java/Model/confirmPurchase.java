package Model;

import java.io.Serializable;

public class confirmPurchase implements Serializable{
	private int transactionID;
	private int totalprice;
	private String ccnum;
	private String ccname;
	private String cctype;
	private String vcode;
	private int expdateM;
	private int expdateY;
	
	private String brand;
	private String prodname;
	private int unitprice;
	private int quantity;
	private int subtotal;
	private String purchasedate;

	public confirmPurchase(int transactionID) {
		super();
		this.transactionID = transactionID;
	}

	public confirmPurchase(int transactionID, String brand, String prodname,
			int unitprice, int quantity, int subtotal) {
		super();
		this.transactionID = transactionID;
		this.brand = brand;
		this.prodname = prodname;
		this.unitprice = unitprice;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	public confirmPurchase(String ccnum, String ccname, String cctype,
			int expdateM, int expdateY) {
		super();
		this.ccnum = ccnum;
		this.ccname = ccname;
		this.cctype = cctype;
		this.expdateM = expdateM;
		this.expdateY = expdateY;
	}

	public confirmPurchase(int transactionID, int totalprice, String cctype,
			String ccnum, String ccname, String vcode, String purchasedate) {
		super();
		this.transactionID = transactionID;
		this.totalprice = totalprice;
		this.cctype = cctype;
		this.ccnum = ccnum;
		this.ccname = ccname;
		this.vcode = vcode;
		this.purchasedate = purchasedate;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public int getExpdateM() {
		return expdateM;
	}

	public void setExpdateM(int expdateM) {
		this.expdateM = expdateM;
	}

	public int getExpdateY() {
		return expdateY;
	}

	public void setExpdateY(int expdateY) {
		this.expdateY = expdateY;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public String getCctype() {
		return cctype;
	}

	public void setCctype(String cctype) {
		this.cctype = cctype;
	}

	public String getCcnum() {
		return ccnum;
	}

	public void setCcnum(String ccnum) {
		this.ccnum = ccnum;
	}

	public String getCcname() {
		return ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
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

	public int getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}
	
}
