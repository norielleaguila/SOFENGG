package models;

public class AccountType {
	private int typeNum;
	private String typeName;
	
	public AccountType(int typeNum, String typeName){
		this.typeNum = typeNum;
		this.typeName = typeName;
	}
	
	public int getTypeNum(){
		return typeNum;
	}
	
	public String getTypeName(){
		return typeName;
	}
	
	public boolean setTypeName(String type, int accountType){
		if(accountType == 0){
			this.typeName = type;
			return true;
		}
		return false;	// cannot remove; access prohibited
	}
}
