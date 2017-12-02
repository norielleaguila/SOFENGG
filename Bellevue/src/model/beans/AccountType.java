package model.beans;

import javafx.beans.property.*;

public class AccountType {

	public static final String TABLE_NAME = "AccountType";
	public static final String COL_TYPE_ID = "typeID";
	public static final String COL_TYPE_NAME = "typeName";

	private final IntegerProperty typeID;
	private final StringProperty typeName;

	public AccountType () {
		typeID = new SimpleIntegerProperty ();
		typeName = new SimpleStringProperty ();
	}

	public AccountType (int typeID, String typeName) {
		this.typeID = new SimpleIntegerProperty (typeID);
		this.typeName = new SimpleStringProperty (typeName);
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

	public String getTypeName () {
		return typeName.get ();
	}

	public StringProperty typeNameProperty () {
		return typeName;
	}

	public void setTypeName (String typeName) {
		this.typeName.set (typeName);
	}

}
