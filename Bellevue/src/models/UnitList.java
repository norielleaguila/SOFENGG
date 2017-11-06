package models;

import java.util.ArrayList;

public class UnitList {
	private ArrayList<Unit> units;
	
	public UnitList(){
		units = new ArrayList<Unit>();
		createDummyData();
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
	
	private void createDummyData(){
		
	}
	
}
