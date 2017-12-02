package models;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import DB.DBaccess;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class Collection{
	private int unitNo;
	private String datePaid;
	private String dateBilled;
	private int CollectionID;
	private FeesIncurred feesIncurred;
	
	public Collection(){
		super();
	}
	
	public Collection(int CollectionID,int unitNo, String datePaid, String dateBilled){
		this.unitNo = unitNo;
		this.datePaid = datePaid;
		this.dateBilled = dateBilled;
		this.CollectionID=CollectionID;
	}
	public int getCollectionID(){
		return this.CollectionID;
	}
	public void addFees(){
		System.out.println("Collection "+CollectionID);
		feesIncurred = new FeesIncurred(DBaccess.getFeesIncurred(CollectionID));
	}
	public void addFee(FeeIncurred fee){
		FeeIncurred res=this.feesIncurred.addFee(fee);
		if(res==null){
			DBaccess.addFeeIncurred(fee);
			return;
		}
		DBaccess.addToExistingFee(res);
	}
	public void addFee(ArrayList<FeeIncurred> fees){
		for(FeeIncurred fee:fees)
			addFee(fee);
	}
	
	public ArrayList<FeeIncurred> getAllFee(){
		return feesIncurred.getFeesIncurred();
	}
	public Double getTotal(){
		return feesIncurred.getTotal();
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
		if(datePaid != null){
//			if(Integer.parseInt(dateBilled.split("-")[1]) == Integer.parseInt(datePaid.split("-")[1]))
				return true;
		}
		return false;
	}
	
	public boolean isOverdue(){
		if(isPaid())
			return false;
		
		if(datePaid != null){
			String splitDatePaid = datePaid.substring(0, 10);
			System.out.println(splitDatePaid);
			if(Integer.parseInt(splitDatePaid.split("-")[2]) > 15)
				return true;
			return false;
		}
		if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) > 15)
			return true;
		return false;
	}
}
