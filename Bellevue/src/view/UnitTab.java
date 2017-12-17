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
		unitModel.attach(this);
	}
	
	public void setSearchBar(SearchBar searchBar){
		this.searchBar = searchBar;
		setTop(this.searchBar);
	}
	
	public void setUnitsTable(UnitsTable unitsTable){
		this.unitsTable = unitsTable;

		this.unitsTable.initUnitListScroll();
		this.unitsTable.setUnitList(unitModel.getUnits());
		
		setCenter(this.unitsTable);
	}
	
	public void search(String query){
		unitModel.searchUnits(query);
	}

	@Override
	public void update() {
		this.unitsTable.setUnitList(unitModel.getUnits());
	}
}
