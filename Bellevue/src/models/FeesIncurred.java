package models;

import java.util.ArrayList;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class FeesIncurred extends Model{
	
	private ArrayList<FeeIncurred> fees;
	
	public FeesIncurred(){
		fees = new ArrayList<>();
	}
	
	public FeesIncurred(ArrayList<FeeIncurred> feesIncurred){
		this.fees = feesIncurred;
	}
	
	public void addFee(Fee newFee){
		this.fees.add(new FeeIncurred(newFee));
	}
	
	public void removeFee(Fee fee){
		for(FeeIncurred temp: fees){
			if(temp.getFee().equals(fee))
				fees.remove(temp);
		}
	}
	
	public FeeIncurred getFeeIncurred(int feeNo){
		for(FeeIncurred temp: fees){
			if(temp.getFeeID() == feeNo)
				return temp;
		}
		
		return null;
	}
	
	public FeeIncurred getFeeIncurred(Fee fee){
		return getFeeIncurred(fee.getFeeID());
	}
	
	public ArrayList<FeeIncurred> getFeesIncurred() {
		return fees;
	}

	public void setFeesIncurred(ArrayList<FeeIncurred> feesIncurred) {
		this.fees = feesIncurred;
	}
	
	public void paid(boolean all){
		if(all)
			this.fees.clear();
		else{
			
		}
	}
	
	

}

