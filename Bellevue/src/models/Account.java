package models;

/**
 * 
 * @author AGUILA, Norielle
 *
 */
public class Account {
	private String username;
	private String password;
	private int type;	// type of account
	
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
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}
/*
 * V2.0
 * Removed due to data security, replaced with verifyPassword()
 */
//	public String getPassword() {
//		return password;
//	}
	
	/**
	 * Verifies the input password
	 * @param password
	 * @return true if given password matches, else false
	 */
	public boolean verifyPassword(String password){
		if(this.password.equals(password))
			return true;
		return false;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
