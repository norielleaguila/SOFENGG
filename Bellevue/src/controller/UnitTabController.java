package controller;

import view.UnitTab;
import view.popup.ViewUnitPopup;

public class UnitTabController extends Controller<UnitTab, ApplicationController>{

	public static final String TAB_NAME = "UNIT";
	
	protected SearchBarController searchBarController;
	protected UnitsTableController unitsTableController;

	public UnitTabController (ApplicationController mainController) {
		super (mainController);
	}

	@Override
	protected void initView() {
		searchBarController = new SearchBarController(mainController);
		unitsTableController = new UnitsTableController(mainController);
		
		view = new UnitTab();
		
		initSearchBar();
		initUnitsTable();
	}
	
	public void initSearchBar(){
		view.setSearchBar(searchBarController.view);
	}
	
	public void initUnitsTable(){
		view.setUnitsTable(unitsTableController.view);
	}

}
