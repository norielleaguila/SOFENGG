package model;

import java.util.List;
import java.util.ArrayList;

import model.beans.FeesIncurred;
import model.beans.Unit;

public class FeesIncurredModel {
	private List<FeesIncurred> feesIncurredList;
	
	public FeesIncurredModel(){
		this.feesIncurredList = new ArrayList<FeesIncurred>();
	}

	public FeesIncurredModel(List<FeesIncurred> feesIncurredList) {
		this.feesIncurredList = feesIncurredList;
	}
	
	public void addFeesIncurred(FeesIncurred newFeesIncurred){
		if(newFeesIncurred != null)
			this.feesIncurredList.add(newFeesIncurred);
	}
	
	public FeesIncurred getFeeIncurred(int feeNo){
		for(FeesIncurred temp: feesIncurredList){
			if(temp.getFeeID() == feeNo)
				return temp;
		}
		
		return null;
	}
	
	public FeesIncurred getFeeIncurred(FeesIncurred fee){
		if(feesIncurredList.contains(fee))
			return fee;
		return null;
	}
	
	public boolean isFeePaid(Unit unit, String month){
		
		for(FeesIncurred fi : feesIncurredList){
			if(fi.getUnitNo().equals(unit.getUnitNo()))	// if unit exists in feesIncurredList
				if(fi.getDate().split("-")[1].equals(month))	// if date incurred == month
					return fi.getTotal() == fi.getPayment();
		}
		
		return false;
		
	}
}
