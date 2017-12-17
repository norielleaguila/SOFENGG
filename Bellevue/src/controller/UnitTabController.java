package controller;

import view.SearchBar.OnFilterListener;
import view.SearchBar.OnSearchListener;
import model.UnitModel;
import view.UnitTab;
import view.popup.ViewUnitPopup;

public class UnitTabController extends Controller<UnitTab, ApplicationController>{

	public static final String TAB_NAME = "UNIT";
	
	protected SearchBarController searchBarController;
	protected UnitsTableController unitsTableController;
	
	protected UnitModel unitModel;

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
		
		searchBarController.view.setOnSearchListener(new OnSearchListener(){
			@Override
			public void onAction(String query) {
				view.search(query);
			}
		});
		
		searchBarController.view.setOnFilterListener(new OnFilterListener(){

			@Override
			public void onAction(int which) {
				
			}
			
		});
		
	}
	
	public void initUnitsTable(){
		view.setUnitsTable(unitsTableController.view);
		
		
	}

}
