package views;

import java.util.ArrayList;

import javafx.scene.layout.VBox;
import models.Unit;
import models.UnitList;

/**
 * @author AGUILA, Norielle
 */

/*
 * TO-DO:
 * 1. Find optimal sorting algo
 * 2. Find way to insert without having to sort every time
 */

public class UnitRowList extends VBox {
	
	private ArrayList<UnitRow> units;
	
	public UnitRowList(){
		super();
		
		units = new ArrayList<>();
		
		this.getChildren().addAll(units);
		this.setMaxHeight(Double.MAX_VALUE);
		this.setMaxWidth(Double.MAX_VALUE);
		
		this.getStylesheets().add("style.css");
		this.setId("table");
		
	}
	
	public void setRows(ArrayList<UnitRow> units){
		this.units.clear();
		this.units.addAll(units);
		
		resetLayout();
	}
	
	public void addRow(UnitRow unit){
		this.units.add(unit);
		
		resetLayout();
	}
	
	public void addRows(ArrayList<UnitRow> units){
		this.units.addAll(units);
		
		resetLayout();
	}
	
	public void resetLayout(){
		this.getChildren().clear();
		this.getChildren().addAll(units);
	}
	
	public void update(){
		resetLayout();
	}
	
	public UnitRow getRow(int index){
		return units.get(index);
	}
	
	public UnitRow getRow(Unit unit){
		
		for(UnitRow temp: units){
			if(temp.getUnitNum() == unit.getUnitNo())
				return temp;
		}
	
		return null;
		
	}
	
	public ArrayList<UnitRow> getAllRows(){
		return units;
	}
}
