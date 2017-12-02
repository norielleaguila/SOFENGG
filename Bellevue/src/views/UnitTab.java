package views;

import javafx.scene.layout.HBox;
import models.Unit;
import models.UnitList;

/**
 * @author AGUILA, Norielle
 */
public class UnitTab extends Tabs{ 
	
	private UnitTable table;
	private UnitList model;
	private HBox searchHBox;
	
	public UnitTab(){
		super();
		
		this.setSpacing(5);
		
		table = new UnitTable();
		
		initLayout();
	}
	
	public UnitTab(UnitList model){
		super();
		
		this.setSpacing(5);
		
		table = new UnitTable();
		
		this.model = model;
		
		initLayout();
	}
	
	public void initLayout(){
		addRows();
		
		this.getChildren().add(table.getHeader());
		this.getChildren().add(table.getTableScroll());
	}
	
	public void initSearchHBox(){
		searchHBox = new HBox(10);
	}
	
	public UnitTable getTable(){
		return table;
	}
	
	public void addRows(){
		for(Unit unit: model.getUnits()){
			table.addRow(unit);
		}
	}

	@Override
	public void update() {
//		this.getChildren().clear();
		
		for(Unit unit : model.getUnits()){
			table.updateRow(unit);
		}
		
//		initLayout();
	}
	
	public void updateRow(Unit unit){
		table.updateRow(unit);
	}
	
}
