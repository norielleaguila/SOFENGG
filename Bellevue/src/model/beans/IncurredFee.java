package model.beans;

import javafx.beans.property.*;

public class IncurredFee {

	public static final String TABLE_NAME = "IncurredFee";
	public static final String COL_UNIT_NO = "unitNo";
	public static final String COL_FEE_ID = "feeID";
	public static final String COL_DATE = "dateIncurred";
	public static final String COL_COUNT = "count";
	public static final String COL_TOTAL = "total";
	public static final String COL_PAYMENT = "payment";

	private final StringProperty unitNo;
	private final IntegerProperty feeID;
	private final StringProperty date;
	private final IntegerProperty count;
	private final DoubleProperty total;
	private final DoubleProperty payment;

	public IncurredFee () {
		unitNo = new SimpleStringProperty ();
		feeID = new SimpleIntegerProperty ();
		date = new SimpleStringProperty ();
		count = new SimpleIntegerProperty ();
		total = new SimpleDoubleProperty ();
		payment = new SimpleDoubleProperty ();
	}

	public IncurredFee (String unitNo, int feeID, String date, int count, double total, double payment) {
		this.unitNo = new SimpleStringProperty (unitNo);
		this.feeID = new SimpleIntegerProperty (feeID);
		this.date = new SimpleStringProperty (date);
		this.count = new SimpleIntegerProperty (count);
		this.total = new SimpleDoubleProperty (total);
		this.payment = new SimpleDoubleProperty (payment);
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

	public int getFeeID () {
		return feeID.get ();
	}

	public IntegerProperty feeIDProperty () {
		return feeID;
	}

	public void setFeeID (int feeID) {
		this.feeID.set (feeID);
	}

	public String getDate () {
		return date.get ();
	}

	public StringProperty dateProperty () {
		return date;
	}

	public void setDate (String date) {
		this.date.set (date);
	}

	public int getCount () {
		return count.get ();
	}

	public IntegerProperty countProperty () {
		return count;
	}

	public void setCount (int count) {
		this.count.set (count);
	}

	public double getTotal () {
		return total.get ();
	}

	public DoubleProperty totalProperty () {
		return total;
	}

	public void setTotal (double total) {
		this.total.set (total);
	}

	public double getPayment () {
		return payment.get ();
	}

	public DoubleProperty paymentProperty () {
		return payment;
	}

	public void setPayment (double payment) {
		this.payment.set (payment);
	}

}
