package models;

import java.util.ArrayList;

import DB.DBaccess;

/**
 * Model class for the {@code Unit} object. Contains all logic and data manipulation for {@code Unit}
 * @author AGUILA, Norielle
 * @see Unit Unit class
 */

public class UnitList extends Model{
	private ArrayList<Unit> units;
	
	public UnitList(){
		units = new ArrayList<Unit>();
	}
	
	public UnitList(ArrayList<Unit> units){
		this.units = units;
	}
	
	public Unit getUnit(Unit unit){
		if(units.contains(unit))
			return units.get(units.indexOf(unit));
		
		return null;
	}
	
	public Unit getUnit(int unitNo){
		for(Unit unit: units){
			if(unit.getUnitNo() == unitNo)
				return unit;
		}
		
		return null;
	}
	
	public ArrayList<Unit> getUnits(){
		return units;
	}
	
	/**
	 * Method used for the Searching feature of the program. 
	 * Searches for all units that contain the searched {@code String} value.
	 * @param searchParameter <p>The parameter in which the list of units will be searched for.
	 * 						  <br>Must be convertible to an {@code integer} value.</p>
	 * @return <b>searchResults</b> <p>Resulting {@code ArrayList} of the search done. 
	 * 						 		<br>All units returned contain the searchParameter in its unit number.</p>
	 * 								<p><i>Example:</i></p>
	 * 						 		When the user enters the {@code String} {@literal 10}, the method returns
	 * 								all units containing 10 in its unit number such as 10, 101, 210, etc.
	 */
	public ArrayList<Unit> searchForUnits(String searchParameter){
		ArrayList<Unit> searchResults = new ArrayList<Unit>();
		
		// Look for all units that contain the given search parameter
		for(Unit unit: units){
			String unitNumberString = unit.getUnitNo() + "";
			
			if(unitNumberString.contains(searchParameter))
				searchResults.add(unit);
		}
		
		return searchResults;
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
	public ArrayList<Unit> filterUnits(int filterParameter){
		ArrayList<Unit> filterResults = new ArrayList<Unit>();
		
			switch(filterParameter){
			case 0:
				filterResults = units;
				break;
			case 1:
				filterResults = getAllPaidUnits();
				break;
			case 2:
				filterResults = getAllUnpaidUnits();
				break;
			default:
			}
		
		return filterResults;
	}
	
	/**
	 * @return Returns all units that have paid their current bill.
	 */
	public ArrayList<Unit> getAllPaidUnits(){
		ArrayList<Unit> paidUnits = new ArrayList<Unit>();
		
		for(Unit unit : units){
			if(unit.isPaid())
				paidUnits.add(unit);
		}
		
		return paidUnits;
	}
	
	/**
	 * @return Returns all units that have not paid their current bill.
	 */
	public ArrayList<Unit> getAllUnpaidUnits(){
		ArrayList<Unit> paidUnits = new ArrayList<Unit>();
		
		for(Unit unit : units){
			if(!unit.isPaid())
				paidUnits.add(unit);
		}
		
		return paidUnits;
	}
	
}
