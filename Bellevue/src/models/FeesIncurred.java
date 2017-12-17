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
	public double getTotal(){
		double total=0;
		for(FeeIncurred fee: fees){
			total+=(fee.getTimes()*fee.getPrice());
		}
		return total;
	}
	public void addFee(Fee newFee,int times){
		this.fees.add(new FeeIncurred(newFee,times));
	}
	public void deleteFee(FeeIncurred fee){
		int val=-1;
		for(int i=0;i<fees.size();i++){
			if(fees.get(i).getName().equals(fee.getName())){
				val=i;
				break;
			}
		}
		if(val>=0)
			fees.remove(val);
	}
	public void editFee(FeeIncurred pre,FeeIncurred post){
		int val=-1;
		for(int i=0;i<fees.size();i++){
			if(fees.get(i).getName().equals(pre.getName())){
				val=i;
				break;
			}
		}
		if(val>=0)
			fees.set(val, post);
	}
	public FeeIncurred addFee(FeeIncurred fee){
		FeeIncurred temp =getFeeIncurred(fee.getFeeID());
		if(temp==null){
			this.fees.add(fee);
			return null;
		}
		temp.addTimes(fee.getTimes());
		return temp;
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

