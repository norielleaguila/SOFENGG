package model;

import java.util.ArrayList;
import java.util.List;

import model.beans.Unit;

public class UnitModel extends Model{
	
	private List<Unit> unitList;
	
	public UnitModel(){
		unitList = new ArrayList<Unit>();
	}
	
	public UnitModel(ArrayList<Unit> unitList){
		this.unitList = unitList;
	}
	
	public Unit getUnit(Unit unit){
		if(unitList.contains(unit))
			return unit;
		
		return null;
	}
	
	public Unit getUnit(String unitNo){
		for(Unit unit: unitList)
			if(unit.getUnitNo().equals(unitNo))
				return unit;
		return null;
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
			if(getUnit(searchParameter) != null)
				searchResults.add(getUnit(searchParameter));
		}
		catch(NumberFormatException e){
			// display error (Please enter a valid unit number)
			
			// return null, signifying that this exception was triggered.
			return null;
		}
		
		// Look for all units that contain the given search parameter
		for(Unit unit: unitList){
			if(unit.getUnitNo().contains(searchParameter))
				searchResults.add(unit);
		}
		
		return searchResults;
	}
}

