package Model;

public class Login {
	private String loginid;
	private String password;
	private String name;
	
	public Login(){
		
	}
	
	public Login(String loginid, String password){
		this.loginid = loginid;
		this.password = password;
	}

	public Login(String name) {
		super();
		this.name = name;
	}
	
	public String getLoginid(){
		return loginid;		
	}
	
	public void setLoginid(String loginid){
		this.loginid = loginid;
	}
	
	public String getPassword(){
		return password;		
	}
	
	public void setPassword(String password){
		this.password = password;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
