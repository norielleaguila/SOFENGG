package views;

import java.util.ArrayList;

import javafx.scene.layout.VBox;

/**
 * @author AGUILA, Norielle
 */

/*
 * TO-DO:
 * 1. Find optimal sorting algo
 * 2. Find way to insert without having to sort every time
 */

public class UnitList extends VBox {
	
	ArrayList<UnitRow> units;
	
	public UnitList(){
		super();
		
		units = new ArrayList<>();
		
		this.getChildren().addAll(units);
		this.setMaxHeight(Double.MAX_VALUE);
		this.setMaxWidth(Double.MAX_VALUE);
		
	}
	
	public void addRow(UnitRow unit){
		this.units.add(unit);
		
		sortList();
		resetLayout();
	}
	
	public void addRows(ArrayList<UnitRow> units){
		this.units.addAll(units);
		
		sortList();
		resetLayout();
	}
	
	/**
	 * !!UNFINISHED
	 * sorts the list by unit number
	 */
	public void sortList(){
		ArrayList<UnitRow> temp  = units;
		ArrayList<UnitRow> newList;
		
		resetLayout();
		
	}
	
	/**
	 * !!UNFINISHED
	 * displays only the rows that match the given search filter
	 * @param filterBy
	 */
	public void filter(String filterBy){
		/*
		 * TO-DO:
		 * 1. filter
		 * 2. sort
		 */
		
		resetLayout();
	}
	
	public void resetLayout(){
		this.getChildren().clear();
		this.getChildren().addAll(units);
	}
	
}
