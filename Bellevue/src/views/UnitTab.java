package views;

import models.Unit;
import models.UnitList;

/**
 * @author AGUILA, Norielle
 */
public class UnitTab extends Tabs{ 
	
	private UnitTable table;
	private UnitList model;
	
	public UnitTab(){
		super();
		
		this.setSpacing(5);
		
		table = new UnitTable();
		
//		table.addDummyRows();
		
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
		// TODO Auto-generated method stub
		
	}
	
}
