package model;

import java.util.ArrayList;
import java.util.List;

import model.beans.MonthlyCollection;

public class MonthlyCollectionModel extends Model{
	
	private List<MonthlyCollection> monthlyCollectionList;

	public MonthlyCollectionModel(){
		monthlyCollectionList = new ArrayList<MonthlyCollection>();
	}
	
	public MonthlyCollectionModel(ArrayList<MonthlyCollection> monthlyCollectionList){
		this.monthlyCollectionList = monthlyCollectionList;
	}
	
	/**
	 * 
	 * @param month month to be filtered for
	 * @return Returns all monthly collection that are due in input month
	 */
	public List<MonthlyCollection> getAllCollectionInMonth(String month){
		List<MonthlyCollection> allMonthlyCollectionInMonth = new ArrayList<MonthlyCollection>();
		
		for(MonthlyCollection monthlyCollection: monthlyCollectionList){
			if(monthlyCollection.getDateBilled().split("-")[1].equals(month))
				allMonthlyCollectionInMonth.add(monthlyCollection);
		}
		
		return allMonthlyCollectionInMonth;
	}
	
	/**
	 * @return Returns all units that have paid their current bill.
	 */
	public ArrayList<String> getAllPaidUnits(String month){
		ArrayList<String> paidUnitNos = new ArrayList<String>();
		
		for(MonthlyCollection mc : monthlyCollectionList){
			if(mc.getDatePaid().split("-")[1].equals(month))
				paidUnitNos.add(mc.getUnitNo());
		}
		
		return paidUnitNos;
	}
	
	/**
	 * @return Returns all units that have not paid their current bill.
	 */
	public ArrayList<String> getAllUnpaidUnits(String month){
		ArrayList<String> unpaidUnitNos = new ArrayList<String>();
		
		for(MonthlyCollection mc : monthlyCollectionList){
			if(mc.getDateDue().split("-")[1].equals(month))
				if(mc.getDatePaid().equals(null))
					unpaidUnitNos.add(mc.getUnitNo());
		}
		
		return unpaidUnitNos;
	}
	
	/**
	 * Filters all units into either paid, unpaid, or both through a filter parameter. 
	 * Will only be called when the {@code All} radio button is unselected.
	 * @param filterParameter 
	 * 			<p>	The {@code String} parameter(s) which the unit list will be filtered by.
	 * 				Parameters are split by space as its delimiter. </p>
	 * 			<p>	Example filter parameters: {@code "paid"}, {@code "paid unpaid"}. </p>
	 * @return <b>filterResults</b>
	 * 			<p>	{@code ArrayList} of {@code Units} that match the given filter parameter.</p>
	 * 
	 */
	public ArrayList<String> filterUnits(String filterParameter, String month){
		ArrayList<String> filterResults = new ArrayList<String>();
		
		// if units are to be filtered by more than 1, the String will be split with a space as its delimiter
		String[] filterByList =  filterParameter.split(" ");
		
		for(String filterBy : filterByList){
			switch(filterBy){
			case "paid":
				filterResults.addAll(getAllPaidUnits(month));
				break;
			case "unpaid":
				filterResults.addAll(getAllUnpaidUnits(month));
				break;
			default:
				// error, filter parameter incorrect
			}
		}
		
		return filterResults;
	}
	
}
