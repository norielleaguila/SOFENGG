package models;

import java.util.GregorianCalendar;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class Collection{
	private int unitNo;
	private GregorianCalendar DatePaid;
	private GregorianCalendar DateBilled;
	
	public Collection(){
		super();
	}

	public int getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(int unitNo) {
		this.unitNo = unitNo;
	}

	public GregorianCalendar getDatePaid() {
		return DatePaid;
	}

	public void setDatePaid(GregorianCalendar datePaid) {
		DatePaid = datePaid;
	}

	public GregorianCalendar getDateBilled() {
		return DateBilled;
	}

	public void setDateBilled(GregorianCalendar dateBilled) {
		DateBilled = dateBilled;
	}
}
