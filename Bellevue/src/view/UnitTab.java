package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.UnitModel;

public class UnitTab extends BorderPane implements View{
	private SearchBar searchBar;
	private UnitsTable unitsTable;
	private UnitModel unitModel;
	
	public UnitTab(){
		super();
		initUnitModel();
		init();
	}
	
	public void init(){
		getStylesheets().add("style/style.css");
	}
	
	public void initUnitModel(){
		unitModel = new UnitModel();
	}
	
	public void setSearchBar(SearchBar searchBar){
		this.searchBar = searchBar;
		setTop(this.searchBar);
	}
	
	public void setUnitsTable(UnitsTable unitsTable){
		this.unitsTable = unitsTable;
		
		this.unitsTable.setUnitList(unitModel.getUnits());
		this.unitsTable.initUnitListScroll();
		
		setCenter(this.unitsTable);
	}

	@Override
	public void update() {
		
	}
}
