package model.beans;

import javafx.beans.property.*;

public class FeeType {

	public static final String TABLE_NAME = "FeeType";
	public static final String COL_FEE_TYPE_ID = "feeTypeID";
	public static final String COL_FEE_TYPE = "feeType";

	private final IntegerProperty feeTypeID;
	private final StringProperty feeType;

	public FeeType () {
		feeTypeID = new SimpleIntegerProperty ();
		feeType = new SimpleStringProperty ();
	}

	public FeeType (int feeTypeID, String feeType) {
		this.feeTypeID = new SimpleIntegerProperty (feeTypeID);
		this.feeType = new SimpleStringProperty (feeType);
	}

	public int getFeeTypeID () {
		return feeTypeID.get ();
	}

	public IntegerProperty feeTypeIDProperty () {
		return feeTypeID;
	}

	public void setFeeTypeID (int feeTypeID) {
		this.feeTypeID.set (feeTypeID);
	}

	public String getFeeType () {
		return feeType.get ();
	}

	public StringProperty feeTypeProperty () {
		return feeType;
	}

	public void setFeeType (String feeType) {
		this.feeType.set (feeType);
	}

}
