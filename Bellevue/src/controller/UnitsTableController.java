package controller;

import javafx.stage.Popup;
import view.SearchBar;
import view.UnitsTable;
import view.popup.ViewUnitPopup;

public class UnitsTableController extends Controller<UnitsTable, ApplicationController> {
	protected UnitRowController unitRowController;
	
	protected UnitsTableController(ApplicationController mainController) {
		super(mainController);
	}

	@Override
	protected void initView() {
		unitRowController = new UnitRowController(mainController);
		view = new UnitsTable(unitRowController);
	}
}
