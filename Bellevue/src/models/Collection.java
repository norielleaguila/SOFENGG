package models;

import java.util.GregorianCalendar;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class Collection{
	private int unitNo;
	private String datePaid;
	private String dateBilled;
	
	public Collection(){
		super();
	}

	public int getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(int unitNo) {
		this.unitNo = unitNo;
	}

	public String getDatePaid() {
		return this.datePaid;
	}

	public void setDatePaid(String datePaid) {
		this.datePaid = datePaid;
	}

	public String getDateBilled() {
		return this.dateBilled;
	}

	public void setDateBilled(String dateBilled) {
		this.dateBilled = dateBilled;
	}
	
	public boolean isPaid(){
		if(Integer.parseInt(dateBilled.split("-")[1]) == Integer.parseInt(datePaid.split("-")[1]))
			return true;
		return false;
	}
	
	public boolean isOverdue(){
		if(Integer.parseInt(datePaid.split("-")[2]) <= 15)
			return true;
		return false;
	}
}
