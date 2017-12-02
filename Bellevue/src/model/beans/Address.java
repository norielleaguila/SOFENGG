package model.beans;

import javafx.beans.property.*;

public class Address {

	public static final String TABLE_NAME = "Address";
	public static final String COL_UNIT_NO = "unitNo";
	public static final String COL_ADDRESS_NO = "addressNo";
	public static final String COL_STREET_ID = "streetID";

	private final StringProperty unitNo;
	private final StringProperty addressNo;
	private final IntegerProperty streetID;

	public Address () {
		unitNo = new SimpleStringProperty ();
		addressNo = new SimpleStringProperty ();
		streetID = new SimpleIntegerProperty ();
	}

	public Address (String unitNo, String addressNo, int streetID) {
		this.unitNo = new SimpleStringProperty (unitNo);
		this.addressNo = new SimpleStringProperty (addressNo);
		this.streetID = new SimpleIntegerProperty (streetID);
	}

	public String getUnitNo () {
		return unitNo.get ();
	}

	public StringProperty unitNoProperty () {
		return unitNo;
	}

	public void setUnitNo (String unitNo) {
		this.unitNo.set (unitNo);
	}

	public String getAddressNo () {
		return addressNo.get ();
	}

	public StringProperty addressNoProperty () {
		return addressNo;
	}

	public void setAddressNo (String addressNo) {
		this.addressNo.set (addressNo);
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

}
