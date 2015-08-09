package Model;

public class salesreport {
	private String prodname;
	private int quantity;
	
	public salesreport(String prodname, int quantity) {
		super();
		this.prodname = prodname;
		this.quantity = quantity;
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
	
}
