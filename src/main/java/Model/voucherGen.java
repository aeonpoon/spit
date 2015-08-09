package Model;

public class voucherGen {
	
	private String stringChars;
	private String email;
	private int discount;
	
	public voucherGen(String email, String stringChars) {
		super();
		this.email = email;
		this.stringChars = stringChars;
	}
	
	public voucherGen(int discount) {
		super();
		this.discount = discount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public voucherGen(String stringChars) {
		super();
		this.stringChars = stringChars;
	}

	public String getStringChars() {
		return stringChars;
	}

	public void setStringChars(String stringChars) {
		this.stringChars = stringChars;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
