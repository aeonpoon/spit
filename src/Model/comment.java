package Model;

public class comment {
	private int id;
	private String catname;
	private String brand;
	private String prodname;
	private String comment;
	private String ratings;
	
	public comment(int id, String catname, String brand, String prodname,
			String comment, String ratings) {
		super();
		this.id = id;
		this.catname = catname;
		this.brand = brand;
		this.prodname = prodname;
		this.comment = comment;
		this.ratings = ratings;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRatings() {
		return ratings;
	}

	public void setRatings(String ratings) {
		this.ratings = ratings;
	}
}
