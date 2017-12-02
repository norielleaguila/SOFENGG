package model.beans;

import javafx.beans.property.*;

public class Account {

	public static final String TABLE_NAME = "Account";
	public static final String COL_TYPE_ID = "typeID";
	public static final String COL_USER = "username";
	public static final String COL_PASS = "password";

	private final IntegerProperty accountID;
	private final IntegerProperty typeID;
	private final StringProperty username;

	public Account () {
		accountID = new SimpleIntegerProperty ();
		typeID = new SimpleIntegerProperty ();
		username = new SimpleStringProperty ();
	}

	public Account (int accountID, int typeID, String username) {
		this.accountID = new SimpleIntegerProperty (accountID);
		this.typeID = new SimpleIntegerProperty (typeID);
		this.username = new SimpleStringProperty (username);
	}

	public int getAccountID () {
		return accountID.get ();
	}

	public IntegerProperty accountIDProperty () {
		return accountID;
	}

	public void setAccountID (int accountID) {
		this.accountID.set (accountID);
	}

	public int getTypeID () {
		return typeID.get ();
	}

	public IntegerProperty typeIDProperty () {
		return typeID;
	}

	public void setTypeID (int typeID) {
		this.typeID.set (typeID);
	}

	public String getUsername () {
		return username.get ();
	}

	public StringProperty usernameProperty () {
		return username;
	}

	public void setUsername (String username) {
		this.username.set (username);
	}

}
