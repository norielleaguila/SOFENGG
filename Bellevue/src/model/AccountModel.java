package model;

import java.util.ArrayList;
import java.util.List;

import model.beans.Account;

public class AccountModel extends Model {
	
	private List<Account> accountList;
	
	public AccountModel(){
		accountList = new ArrayList<Account>();
	}
	
	public AccountModel(ArrayList<Account> accountList){
		this.accountList = accountList;
	}
	
	public Account getAccount(Account account){
		if(accountList.contains(account))
			return account;
		return null;
	}
	
	/**
	 * Searches through accounts to find the account with a matching username, otherwise will return null
	 * @param username
	 * @return
	 */
	public Account getAccount(String username){
		
		for(Account temp: accountList){
			if(temp.getUsername().equals(username))
				return temp;
		}
		
		return null;
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
	
	/**
	 * Checks if the user exists in the db
	 * @param username
	 * @return
	 */
	public boolean exists(String username){
		if(getAccount(username) != null)
			return true;
		return false;
	}
}
