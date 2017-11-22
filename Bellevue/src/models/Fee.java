package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** 
 * 
 * @author AGUILA, Norielle
 *
 */

public class Fee{
//	private int feeID;
//	private String feeName;
//	private String type;
//	private double price;
	
	private IntegerProperty feeID;
	private StringProperty feeName;
	private StringProperty type;
	private DoubleProperty price;
	
	public Fee(){
//		super();
		feeID = new SimpleIntegerProperty();
		feeName = new SimpleStringProperty();
		type = new SimpleStringProperty();
		price = new SimpleDoubleProperty();
	}
	
	public Fee(String feeName, String type, double price){
		this();
//		this.feeID.set(get new id from db);
		this.feeName.set(feeName);
		this.type.set(type);
		this.price.set(price);
	}
	
	public Fee(String type){
		this();
		this.type.set(type);
	}
	
	public Fee(int feeID, String feeName, String type, double price){
		this();
		this.feeID.set(feeID);
		this.feeName.set(feeName);
		this.type.set(type);
		this.price.set(price);
	}
	
	public void setFeeID (int id) { feeID.set (id); }
	public int getFeeID () { return feeID.get (); }
	public IntegerProperty feeIDProperty () { return feeID; }
	
	public void setFeeName (String name) { feeName.set (name); }
	public String getFeeName () { return feeName.get (); }
	public StringProperty feeNameProperty () { return feeName; }
	
	public void setType (String type) { this.type.set (type); }
	public String getType () { return type.get (); }
	public StringProperty typeProperty () { return type; }
	
	public void setPrice (double price) { this.price.set (price); }
	public double getPrice () { return price.get (); }
	public DoubleProperty priceProperty () { return price; }
	
//	public Fee(int feeID, String feeName, String type, double price){
//		this.feeID = feeID;
//		this.feeName = feeName;
//		this.type = type;
//		this.price = price;
//	}
//
//	public int getFeeID() {
//		return feeID;
//	}
//
//	public void setFeeID(int feeID) {
//		this.feeID = feeID;
//	}
//
//	public String getFeeName() {
//		return feeName;
//	}
//
//	public void setFeeName(String feeName) {
//		this.feeName = feeName;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//	
//	public double getPrice(){
//		return price;
//	}
//	
//	public void setPrice(double price){
//		this.price = price;
//	}
}
