package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CollectionModel;
import model.UnitModel;

public class UnitTab extends BorderPane implements View{
	private SearchBar searchBar;
	private UnitsTable unitsTable;
	private UnitModel unitModel;
	private CollectionModel collectionModel;
	
	public UnitTab(){
		super();
		initUnitModel();
		init();
	}
	
	public void initUnitModel(){
		unitModel = new UnitModel();
		unitModel.attach(this);
	}
	
	public void initCollectionModel(){
		collectionModel = new CollectionModel();
		collectionModel.attach(this);
	}
	
	public void init(){
		getStylesheets().add("style/style.css");
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
	
	public void filter(int which){
		switch(which){
		case 0:
			unitsTable.setUnitList(unitModel.getUnits());
			break;
		case 1:
			unitsTable.setUnitList(unitModel.getAllPaidUnits());
			break;
		case 2:
			unitsTable.setUnitList(unitModel.getAllUnpaidUnits());
			break;
		}
	}

	@Override
	public void update() {
		this.unitsTable.setUnitList(unitModel.getUnits());
	}
}
