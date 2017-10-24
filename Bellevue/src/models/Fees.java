package models;

public class Fees extends Model{
	private int feeID;
	private String feeName;
	private String type;
	private double price;
	
	public Fees(){
		super();
	}

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
