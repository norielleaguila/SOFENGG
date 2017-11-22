package models;

import java.util.ArrayList;

public class AccountTypeList {
	private ArrayList<AccountType> types;
	
	public AccountTypeList(){
		this.types = new ArrayList<AccountType>();
	}
	
	public AccountTypeList(ArrayList<AccountType> types){
		this.types = types;
	}
	
	public AccountType getAccountType(AccountType type){
		if(types.contains(type))
			return types.get(types.indexOf(type));
		return null;
	}
	
	public AccountType getAccountType(int typeNum){
		for(AccountType type: types){
			if(type.getTypeNum() == typeNum)
				return type;
		}
		return null;
	}
	
	public boolean addAccountType(AccountType type){
		if(type.getTypeNum() == 0){	//admin
			types.add(type);
			return true; 	// success
		}
		return false;	// cannot add; access prohibited
	}
	
	public boolean remove(AccountType type){
		if(type.getTypeNum() == 0){	//admin
			types.remove(type);
			return true; 	// success
		}
		return false; 	// cannot remove; access prohibited
	}
	
	public boolean clear(AccountType type){
		if(type.getTypeNum() == 0){
			types.clear();
			return true;
		}
		return false;	// cannot clear; access prohibited
	}
}
