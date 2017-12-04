package model.beans;

import javafx.beans.property.*;

public class Fee {

	public static final String TABLE_NAME = "Fee";
	public static final String COL_FEE_ID = "feeID";
	public static final String COL_FEE_NAME = "feeName";
	public static final String COL_FEE_TYPE_ID = "feeTypeID";
	public static final String COL_FEE_PRICE = "feePrice";

	private final IntegerProperty feeID;
	private final StringProperty feeName;
	private final IntegerProperty feeType;
	private final DoubleProperty feePrice;

	public Fee () {
		feeID = new SimpleIntegerProperty ();
		feeName = new SimpleStringProperty ();
		feeType = new SimpleIntegerProperty ();
		feePrice = new SimpleDoubleProperty ();
	}

	public Fee (int feeID, String feeName, int feeType, double feePrice) {
		this.feeID = new SimpleIntegerProperty (feeID);
		this.feeName = new SimpleStringProperty (feeName);
		this.feeType = new SimpleIntegerProperty (feeType);
		this.feePrice = new SimpleDoubleProperty (feePrice);
	}

	public int getFeeID () {
		return feeID.get ();
	}

	public IntegerProperty feeIDProperty () {
		return feeID;
	}

	public void setFeeID (int feeID) {
		this.feeID.set (feeID);
	}

	public String getFeeName () {
		return feeName.get ();
	}

	public StringProperty feeNameProperty () {
		return feeName;
	}

	public void setFeeName (String feeName) {
		this.feeName.set (feeName);
	}

	public int getFeeType () {
		return feeType.get ();
	}

	public IntegerProperty feeTypeProperty () {
		return feeType;
	}

	public void setFeeType (int feeType) {
		this.feeType.set (feeType);
	}

	public double getFeePrice () {
		return feePrice.get ();
	}

	public DoubleProperty feePriceProperty () {
		return feePrice;
	}

	public void setFeePrice (double feePrice) {
		this.feePrice.set (feePrice);
	}

}
