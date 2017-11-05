package models;

import java.util.ArrayList;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class AccountList extends Model{
	private ArrayList<Account> accounts;
	
	public AccountList(){
		accounts = new ArrayList<Account>();
		createDummyData();
	}
	
	public AccountList(ArrayList<Account> accounts){
		this.accounts = accounts;
	}
	
	public Account getAccount(Account account){
		if(accounts.contains(account))
			return accounts.get(accounts.indexOf(account));
		return null;
	}
	
	/**
	 * Searches through accounts to find the account with a matching username, otherwise will return null
	 * @param username
	 * @return
	 */
	public Account getAccount(String username){
		
		for(Account temp: accounts){
			if(temp.getUsername().equals(username))
				return temp;
		}
		
		return null;
	}
	
	public ArrayList<Account> getAccounts(){
		return accounts;
	}
	
	/**
	 * Accepts 2 string inputs username and password and verifies if it exists, then verifies if the password matches
	 * @param username
	 * @param password
	 * @return true if valid account, else false
	 */
	public boolean verifyAccount(String username, String password){
		return getAccount(username).verifyPassword(password);
	}
	
	// temporary
	private void createDummyData(){
		accounts.add(new Account("admin", "123"));
		accounts.add(new Account("secretary", "123"));
	}
	
	
}
