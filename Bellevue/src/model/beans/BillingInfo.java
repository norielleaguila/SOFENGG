package model.beans;

import javafx.beans.property.*;

public class BillingInfo {

	public static final String TABLE_NAME = "BillingInfo";
	public static final String COL_UNIT_NO = "unitNo";
	public static final String COL_BILLED_TO = "billedTo";
	public static final String COL_TCT = "TCT";

	private final StringProperty unitNo;
	private final StringProperty billedTo;
	private final StringProperty TCT;

	public BillingInfo () {
		unitNo = new SimpleStringProperty ();
		billedTo = new SimpleStringProperty ();
		TCT = new SimpleStringProperty ();
	}

	public BillingInfo (String unitNo, String billedTo, String TCT) {
		this.unitNo = new SimpleStringProperty (unitNo);
		this.billedTo = new SimpleStringProperty (billedTo);
		this.TCT = new SimpleStringProperty (TCT);
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

	public String getBilledTo () {
		return billedTo.get ();
	}

	public StringProperty billedToProperty () {
		return billedTo;
	}

	public void setBilledTo (String billedTo) {
		this.billedTo.set (billedTo);
	}

	public String getTCT () {
		return TCT.get ();
	}

	public StringProperty TCTProperty () {
		return TCT;
	}

	public void setTCT (String TCT) {
		this.TCT.set (TCT);
	}

}
