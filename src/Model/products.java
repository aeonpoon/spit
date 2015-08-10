package Model;

public class products {
	private String prodname;
	private int price;
	private String brand;
	private String imgpath;
	private int prodid;
	
	public products(){
		super();
	}
	
	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	
	public String getImgpath(){
		return imgpath;
	}

	public void setImg(String imgpath) {
		this.imgpath = imgpath;
	}
	
	public String getBrand(){
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getProdname(){
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
	
	public products(int prodid, String imgpath, String brand, String prodname, int price) {
		super();
		this.prodid = prodid;
		this.imgpath = imgpath;
		this.brand = brand;
		this.prodname = prodname;
		this.price = price;
	}
}
