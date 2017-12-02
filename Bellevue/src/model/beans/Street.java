package model.beans;

import javafx.beans.property.*;

public class Street {

	public static final String TABLE_NAME = "Street";
	public static final String COL_STREET_ID = "streetID";
	public static final String COL_STREET_NAME = "streetName";

	private final IntegerProperty streetID;
	private final StringProperty streetName;

	public Street () {
		streetID = new SimpleIntegerProperty ();
		streetName = new SimpleStringProperty ();
	}

	public Street (int streetID, String streetName) {
		this.streetID = new SimpleIntegerProperty (streetID);
		this.streetName = new SimpleStringProperty (streetName);
	}

	public int getStreetID () {
		return streetID.get ();
	}

	public IntegerProperty streetIDProperty () {
		return streetID;
	}

	public void setStreetID (int streetID) {
		this.streetID.set (streetID);
	}

	public String getStreetName () {
		return streetName.get ();
	}

	public StringProperty streetNameProperty () {
		return streetName;
	}

	public void setStreetName (String streetName) {
		this.streetName.set (streetName);
	}

}
