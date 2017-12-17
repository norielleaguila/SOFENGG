package models;

import java.util.ArrayList;
import java.util.Arrays;

import DB.DBaccess;
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
	public static ArrayList<String> FEETYPE = new ArrayList<String>();
	private IntegerProperty feeID;
	private StringProperty feeName;
	private StringProperty type;
	private DoubleProperty price;
	
	public Fee(){
		//String[] startTypes = new String[]{"Basic Charges","Monthly Association Dues","Others","Renovation","Rentals",
		//		"Special Activities and Events"};
		
		feeID = new SimpleIntegerProperty();
		feeName = new SimpleStringProperty();
		type = new SimpleStringProperty();
		price = new SimpleDoubleProperty();
		
	}
	
	public Fee(String feeName, String type, double price){
		this();
		this.feeID.set(DBaccess.getFeeID());
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
	
	public static void initType(){
		FEETYPE=DBaccess.getTypes();
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
	public static void removeType(String type){
		int val=-1;
		for(int i=0;i<FEETYPE.size();i++){
			if(FEETYPE.get(i).equals(type))
				val=i;
		}
		if(val>=0)
			FEETYPE.remove(val);
		
	}
	public static void replaceType(String typeo,String typen){
		int val=-1;
		for(int i=0;i<FEETYPE.size();i++){
			if(FEETYPE.get(i).equals(typeo))
				val=i;
		}
		if(val>=0)
			FEETYPE.set(val, typen);
	}
	public static void addType(String type){
		DBaccess.addType(type);
		FEETYPE.add(type);
	}
}
