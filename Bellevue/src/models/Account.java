package models;

public class Account {
	private String username;
	private String password;
	
	public Account(){
		this.username = "";
		this.password = "";
	}
	
	public Account(String username, String password){
		this.username = username;
		this.password = password;
	}

	/* getts and setters */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
