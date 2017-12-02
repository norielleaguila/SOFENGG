package model.beans;

import javafx.beans.property.*;

public class MonthlyCollection {

	public static final String TABLE_NAME = "Collection";
	public static final String COL_T_ID = "transactionID";
	public static final String COL_UNIT_NO = "unitNo";
	public static final String COL_DATE_BILLED = "dateBilled";
	public static final String COL_DATE_DUE = "dateDue";
	public static final String COL_DATE_PAID = "datePaid";
	public static final String COL_MONTHLY_DUE = "monthlyDue";
	public static final String COL_MONTHLY_PAID = "monthlyPaid";
	public static final String COL_MONTHLY_OVERDUE = "monthlyOverdue";

	private final IntegerProperty transactionID;
	private final StringProperty unitNo;
	private final StringProperty dateBilled;
	private final StringProperty dateDue;
	private final StringProperty datePaid;
	private final DoubleProperty monthlyDue;
	private final DoubleProperty monthlyPaid;
	private final DoubleProperty monthlyOverdue;

	public MonthlyCollection () {
		transactionID = new SimpleIntegerProperty ();
		unitNo = new SimpleStringProperty ();
		dateBilled = new SimpleStringProperty ();
		dateDue = new SimpleStringProperty ();
		datePaid = new SimpleStringProperty ();
		monthlyDue = new SimpleDoubleProperty ();
		monthlyPaid = new SimpleDoubleProperty ();
		monthlyOverdue = new SimpleDoubleProperty ();
	}

	public MonthlyCollection (int transactionID, String unitNo,
							  String dateBilled, String dateDue, String datePaid,
							  double monthlyDue, double monthlyPaid, double monthlyOverdue) {
		this.transactionID = new SimpleIntegerProperty (transactionID);
		this.unitNo = new SimpleStringProperty (unitNo);
		this.dateBilled = new SimpleStringProperty (dateBilled);
		this.dateDue = new SimpleStringProperty (dateDue);
		this.datePaid = new SimpleStringProperty (datePaid);
		this.monthlyDue = new SimpleDoubleProperty (monthlyDue);
		this.monthlyPaid = new SimpleDoubleProperty (monthlyPaid);
		this.monthlyOverdue = new SimpleDoubleProperty (monthlyOverdue);
	}

	public int getTransactionID () {
		return transactionID.get ();
	}

	public IntegerProperty transactionIDProperty () {
		return transactionID;
	}

	public void setTransactionID (int transactionID) {
		this.transactionID.set (transactionID);
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

	public String getDateBilled () {
		return dateBilled.get ();
	}

	public StringProperty dateBilledProperty () {
		return dateBilled;
	}

	public void setDateBilled (String dateBilled) {
		this.dateBilled.set (dateBilled);
	}

	public String getDateDue () {
		return dateDue.get ();
	}

	public StringProperty dateDueProperty () {
		return dateDue;
	}

	public void setDateDue (String dateDue) {
		this.dateDue.set (dateDue);
	}

	public String getDatePaid () {
		return datePaid.get ();
	}

	public StringProperty datePaidProperty () {
		return datePaid;
	}

	public void setDatePaid (String datePaid) {
		this.datePaid.set (datePaid);
	}

	public double getMonthlyDue () {
		return monthlyDue.get ();
	}

	public DoubleProperty monthlyDueProperty () {
		return monthlyDue;
	}

	public void setMonthlyDue (double monthlyDue) {
		this.monthlyDue.set (monthlyDue);
	}

	public double getMonthlyPaid () {
		return monthlyPaid.get ();
	}

	public DoubleProperty monthlyPaidProperty () {
		return monthlyPaid;
	}

	public void setMonthlyPaid (double monthlyPaid) {
		this.monthlyPaid.set (monthlyPaid);
	}

	public double getMonthlyOverdue () {
		return monthlyOverdue.get ();
	}

	public DoubleProperty monthlyOverdueProperty () {
		return monthlyOverdue;
	}

	public void setMonthlyOverdue (double monthlyOverdue) {
		this.monthlyOverdue.set (monthlyOverdue);
	}

}
