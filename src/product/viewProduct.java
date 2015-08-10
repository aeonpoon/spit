package product;

public class viewProduct {
	private String brand;
	private String prodname;
	private int price;
	private String imgpath;
	
	public viewProduct(){
		
	}

	public viewProduct(String brand, String prodname, int price, String imgpath) {
		super();
		this.brand = brand;
		this.prodname = prodname;
		this.price = price;
		this.imgpath = imgpath;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	
	
}
