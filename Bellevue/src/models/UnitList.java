package models;

import java.util.ArrayList;

import DB.DBaccess;

public class UnitList extends Model{
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
		units.addAll(DBaccess.getUnitsData());
		//units.add(new Unit(Unit.NUM_UNITS, "Alfonso Secuya", "Alfonso Secuya", 17, "France", 123.0f, 1));
		//units.add(new Unit(Unit.NUM_UNITS, "Norielle Aguila", "Norielle Aguila", 17, "Malaysia", 123.0f, 1));
		//units.add(new Unit(Unit.NUM_UNITS, "Raafi Bandrang", "Raafi Bandrang", 17, "Lebanon", 123.0f, 1));
	}
	
}
