package Model;

public class registerMember {
	private int memid;
	private String password;
	private String enpass;
	private String name;
	private String gender;
	private String dobday;
	private String dobmonth;
	private String dobyear;
	private String address;
	private String contactno;
	private String email;
	private String subscribe;
	
	public registerMember(int memid, String email, String password, String name,
			String gender, String dobday, String dobmonth, String dobyear,
			String address, String contactno, String subscribe) {
		super();
		this.memid = memid;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.dobday = dobday;
		this.dobmonth = dobmonth;
		this.dobyear = dobyear;
		this.address = address;
		this.contactno = contactno;
		this.email = email;
		this.subscribe = subscribe;
	}
	
	public registerMember(int memid, String email, String enpass, String password, String name,
			String gender, String dobday, String dobmonth, String dobyear,
			String address, String contactno, String subscribe) {
		super();
		this.memid = memid;
		this.enpass = enpass;
		this.name = name;
		this.gender = gender;
		this.dobday = dobday;
		this.dobmonth = dobmonth;
		this.dobyear = dobyear;
		this.address = address;
		this.contactno = contactno;
		this.email = email;
		this.subscribe = subscribe;
	}

	public String getEnpass() {
		return enpass;
	}

	public void setEnpass(String enpass) {
		this.enpass = enpass;
	}

	public int getMemid() {
		return memid;
	}

	public void setMemid(int memid) {
		this.memid = memid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDobday() {
		return dobday;
	}

	public void setDobday(String dobday) {
		this.dobday = dobday;
	}

	public String getDobmonth() {
		return dobmonth;
	}

	public void setDobmonth(String dobmonth) {
		this.dobmonth = dobmonth;
	}

	public String getDobyear() {
		return dobyear;
	}

	public void setDobyear(String dobyear) {
		this.dobyear = dobyear;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	
}
