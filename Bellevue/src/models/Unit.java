package models;

/**
 * Unit class
 * 
 * @author AGUILA, Norielle
 *
 */

public class Unit{
	private int unitNo;
	private String billedTo;
	private String tct;		// non-admin accounts cannot have access to this information
	private int addressNo;
	private String street;
	private float lotArea; // non-admin accounts cannot have access to this information
	private int phaseNo;
	
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
	
	// VERS. 2.0 DATA INTEGRITY
	public void accessTCT(){
		
	}
	
	public void accessLotArea(){
		
	}
}
