package model;

import java.util.List;
import java.util.ArrayList;

import model.beans.Fees;

public class FeesModel extends Model{

	private List<Fees> feeList;
	
	public FeesModel(){
		this.feeList = new ArrayList<Fees>();
	}
	
	public FeesModel(ArrayList<Fees> feeList){
		this.feeList = feeList;
	}
	
	public Fees getFee(Fees fee){
		if(feeList.contains(fee))
			return fee;
		return null;
	}
	
	public Fees getFee(String name){
		for(Fees f: feeList){
			if(f.getFeeName().equals(name)){
				return f;
			}
		}
		
		return null;
	}
	
	public Fees getFee(int index){
		if(index < feeList.size())
			return feeList.get(index);
		
		return null;
	}
	
	/**
	 * Sorts the list fees by type, in any order
	 * returns an arraylist of strings, containing all types
	 */
	public ArrayList<Fees> sortByType(){
		
		feeList.sort((f1, f2) -> {
			return f1.feeTypeProperty().getValue().compareTo(f2.feeTypeProperty().getValue());
		});
		
		return (ArrayList<Fees>) feeList;
	}
	
	/**
	 * Returns a list of fees of a certain type
	 * @param type
	 * @return
	 */
	public ArrayList<Fees> filterType(int type){
		ArrayList<Fees> temp = new ArrayList<Fees>();
		
		for(Fees f: feeList){
			if(f.getFeeType() == type)
				temp.add(f);
		}
		
		return temp;
	}
	
	public void addFee(Fees fee){
		feeList.add(fee);
	}
	
}
