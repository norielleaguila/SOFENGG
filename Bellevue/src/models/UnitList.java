package models;

import java.util.ArrayList;

import DB.DBaccess;

/**
 * 
 * @author Aguila, Norielle
 *
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
		
		try{
			if(getUnit(Integer.parseInt(searchParameter)) != null)
				searchResults.add(getUnit(Integer.parseInt(searchParameter)));
		}
		catch(NumberFormatException e){
			// display error (Please enter a valid unit number)
			
			// return null, signifying that this exception was triggered.
			return null;
		}
		
		// Look for all units that contain the given search parameter
		for(Unit unit: units){
			String unitNumberString = unit.getUnitNo() + "";
			
			if(unitNumberString.contains(searchParameter))
				searchResults.add(unit);
		}
		
		return searchResults;
	}
	
}
