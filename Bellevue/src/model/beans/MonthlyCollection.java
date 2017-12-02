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
	private final StringProperty monthlyDue;
	private final StringProperty monthlyPaid;
	private final StringProperty monthlyOverdue;

	public MonthlyCollection () {
		transactionID = new SimpleIntegerProperty ();
		unitNo = new SimpleStringProperty ();
		dateBilled = new SimpleStringProperty ();
		dateDue = new SimpleStringProperty ();
		datePaid = new SimpleStringProperty ();
		monthlyDue = new SimpleStringProperty ();
		monthlyPaid = new SimpleStringProperty ();
		monthlyOverdue = new SimpleStringProperty ();
	}

	public MonthlyCollection (int transactionID, String unitNo,
							  String dateBilled, String dateDue, String datePaid,
							  String monthlyDue, String monthlyPaid, String monthlyOverdue) {
		this.transactionID = new SimpleIntegerProperty (transactionID);
		this.unitNo = new SimpleStringProperty (unitNo);
		this.dateBilled = new SimpleStringProperty (dateBilled);
		this.dateDue = new SimpleStringProperty (dateDue);
		this.datePaid = new SimpleStringProperty (datePaid);
		this.monthlyDue = new SimpleStringProperty (monthlyDue);
		this.monthlyPaid = new SimpleStringProperty (monthlyPaid);
		this.monthlyOverdue = new SimpleStringProperty (monthlyOverdue);
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

	public String getMonthlyDue () {
		return monthlyDue.get ();
	}

	public StringProperty monthlyDueProperty () {
		return monthlyDue;
	}

	public void setMonthlyDue (String monthlyDue) {
		this.monthlyDue.set (monthlyDue);
	}

	public String getMonthlyPaid () {
		return monthlyPaid.get ();
	}

	public StringProperty monthlyPaidProperty () {
		return monthlyPaid;
	}

	public void setMonthlyPaid (String monthlyPaid) {
		this.monthlyPaid.set (monthlyPaid);
	}

	public String getMonthlyOverdue () {
		return monthlyOverdue.get ();
	}

	public StringProperty monthlyOverdueProperty () {
		return monthlyOverdue;
	}

	public void setMonthlyOverdue (String monthlyOverdue) {
		this.monthlyOverdue.set (monthlyOverdue);
	}

}
