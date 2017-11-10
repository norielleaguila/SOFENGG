package views;

/**
 * @author AGUILA, Norielle
 */
public class UnitTab extends Tabs{ 
	
	private UnitTable table;
	
	public UnitTab(){
		super();
		
		this.setSpacing(5);
		
		table = new UnitTable();
		
		table.addDummyRows();
		
		initLayout();
	}
	
	public void initLayout(){
		this.getChildren().add(table.getHeader());
		this.getChildren().add(table.getTableScroll());
	}
	
	public UnitTable getTable(){
		return table;
	}
	
}
