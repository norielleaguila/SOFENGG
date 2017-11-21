package models;

import java.util.ArrayList;

/**
 * Unit class
 * 
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
	
	public Unit(){
		feesIncurred = new FeesIncurred();
		
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
	
	public FeesIncurred getFeesIncurred() {
		return feesIncurred;
	}

	public void setFeesIncurred(FeesIncurred feesIncurred) {
		this.feesIncurred = feesIncurred;
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
	
	// VERS. 2.0 DATA INTEGRITY
	public String accessTCT(int type){
		if(type == 0)	// admin
			return tct;
		return null;
	}
	
	public float accessLotArea(int type){
		if(type == 0) 	// admin
			return lotArea;
		return Float.NaN;
	}
}
