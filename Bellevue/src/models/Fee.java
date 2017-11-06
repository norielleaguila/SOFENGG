package models;

/** 
 * 
 * @author AGUILA, Norielle
 *
 */

public class Fee{
	private int feeID;
	private String feeName;
	private String type;
	private double price;
	
	public Fee(){
		super();
	}
	
	public Fee(int feeID, String feeName, String type, double price){
		this.feeID = feeID;
		this.feeName = feeName;
		this.type = type;
		this.price = price;
	}

//	VERS 2.0 Removed for data security
	public int getFeeID() {
		return feeID;
	}

	public void setFeeID(int feeID) {
		this.feeID = feeID;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
}
