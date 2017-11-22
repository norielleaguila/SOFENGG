package models;

import java.util.ArrayList;

import DB.DBaccess;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class FeeList extends Model{
	private ArrayList<Fee> fees;
	
	public FeeList(){
		fees = new ArrayList<Fee>();
		getFeeData();
		sortByType();
	}
	
	public FeeList(ArrayList<Fee> fees){
		this.fees = fees;
		sortByType();
	}
	
	public Fee getFee(Fee fee){
		if(fees.contains(fee))
			return fees.get(fees.indexOf(fee));
		return null;
	}
	
	public Fee getFee(String name){
		
		for(Fee f: fees){
			if(f.getFeeName().equals(name)){
				return f;
			}
		}
		
		return null;
	}
	
	public Fee getFee(int index){
		if(index < fees.size())
			return fees.get(index);
		
		return null;
	}
	
	public ArrayList<Fee> getFees(){
		return fees;
	}
	
	/**
	 * Sorts the arraylist fees by type, in any order
	 * returns an arraylist of strings, containing all types
	 */

	
	public ArrayList<Fee> sortByType(){
		fees.sort((f1, f2) -> {
			return f1.getType().compareTo(f2.getType());
		});
		
		return fees;
	}
	
	/**
	 * Returns a list of fees of a certain type
	 * @param type
	 * @return
	 */
	public ArrayList<Fee> filterType(String type){
		ArrayList<Fee> temp = new ArrayList<Fee>();
		
		for(Fee f: fees){
			if(f.getType().equals(type))
				temp.add(f);
		}
		
		return temp;
	}
	
	public void addFee(Fee fee){
		fees.add(fee);
	}
	
	private void createDummyData(){
		fees.add(new Fee(0, "Monthly Dues", "Basic Charges", 2500));
		fees.add(new Fee(1, "Trash Bags", "Basic Charges", 100));
		fees.add(new Fee(2, "Others", "Others", 0));
		fees.add(new Fee(3, "Electricity", "Basic Charges", 5000));
		fees.add(new Fee(3, "Lmao", "New Category", 123));
	}
	private void getFeeData(){
		/*
		fees.add(new Fee(0, "Monthly Dues", "Basic Charges", 2500));
		fees.add(new Fee(1, "Trash Bags", "Basic Charges", 100));
		fees.add(new Fee(2, "Others", "Others", 0));
		fees.add(new Fee(3, "Electricity", "Basic Charges", 5000));*/
		fees.addAll(DBaccess.getFees());
	}
}
