package models;

import java.util.ArrayList;

import DB.DBaccess;

/**
 * Contains all information about each Unit in Bellevue such as unit number,
 * unit owner, lot owner, address, lot area, and phase number. 
 * <br>
 * TCT (lot owner) and lot area are not accessible to any account types other than admins.
 * @author AGUILA, Norielle
 *
 */

public class Unit{
	public static int NUM_UNITS = 0;
	
	private int unitNo;
	private String billedTo;
	private String tct;		// non-admin accounts cannot have access to this information
	private int addressNo;
	private String street;
	private float lotArea; // non-admin accounts cannot have access to this information
	private int phaseNo;
	
	private FeesIncurred feesIncurred;
	
	private boolean paid;
	private boolean overdue;
	
	public Unit(){
		feesIncurred = new FeesIncurred();
		
		paid = false;
		overdue = false;
		
		NUM_UNITS++;
	}
	
	public Unit(FeesIncurred feesIncurred){
		this();
		this.feesIncurred = feesIncurred;
	}
	
	public Unit(ArrayList<FeeIncurred> feesIncurred){
		this();
		this.feesIncurred = new FeesIncurred(feesIncurred);
	}
	
	public Unit(int unitNo, String billedTo, String tct, int addressNo, String street, float lotArea, int phaseNo) {
		this();
		this.unitNo = unitNo;
		this.billedTo = billedTo;
		this.tct = tct;
		this.addressNo = addressNo;
		this.street = street;
		this.lotArea = lotArea;
		this.phaseNo = phaseNo;
	}
	
	public Unit(int unitNo, String billedTo, String tct, int addressNo, String street, float lotArea, int phaseNo, FeesIncurred feesIncurred) {
		this(unitNo, billedTo, tct, addressNo, street, lotArea, phaseNo);
		setFeesIncurred(feesIncurred);
	}

	/* GETTERS & SETTERS */
	public int getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(int unitNo) {
		this.unitNo = unitNo;
	}
	public String getBilledTo() {
		return billedTo;
	}
	public void setBilledTo(String billedTo) {
		this.billedTo = billedTo;
	}
	
//	public String getTct() {
//		return tct;
//	}
//	public void setTct(String tct) {
//		this.tct = tct;
//	}
	
	public int getAddressNo() {
		return addressNo;
	}
	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
//	public float getLotArea() {
//		return lotArea;
//	}
//	public void setLotArea(float lotArea) {
//		this.lotArea = lotArea;
//	}
	public int getPhaseNo() {
		return phaseNo;
	}
	public void setPhaseNo(int phaseNo) {
		this.phaseNo = phaseNo;
	}
	
	public FeesIncurred getFeesIncurred() {
		return feesIncurred;
	}

	public void setFeesIncurred(FeesIncurred feesIncurred) {
		this.feesIncurred = feesIncurred;
	}
	
	public void addFeeIncurred(Fee incurred){
		this.feesIncurred.addFee(incurred);
	}
	
	public void removeFeeIncurred(Fee incurred){
		this.feesIncurred.removeFee(incurred);
	}
	
	public boolean isPaid(){
		return paid;
	}
	
	public void setPaid(boolean paid){
		this.paid = paid;
	}
	
	public boolean isOverdue(){
		return overdue;
	}
	
	public void setOverdue(boolean overdue){
		this.overdue = overdue;
	}
	
	// VERS. 2.0 DATA SECURITY
	/**
	 * Allows access to the unit's TCT when the accessing account is of AccountType Admin 
	 * @param type Account must pass their account type
	 * @return String returns the class attribute tct
	 */
	public String accessTCT(){
		
		if(DBaccess.UserAccount.getType() == 1)	// admin
			return tct;
		return null;
	}
	
	/**
	 * Allows access to the unit's lot area when the accessing account is of AccountType Admin 
	 * @param type Account must pass their account type
	 * @return String returns the class attribute lotArea
	 */
	public float accessLotArea(){
		if(DBaccess.UserAccount.getType() == 1)
			return lotArea;
		
		return Float.NaN;
	}
}
