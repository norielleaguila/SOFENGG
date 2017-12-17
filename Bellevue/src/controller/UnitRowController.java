package controller;

import javafx.stage.Popup;
import model.UnitModel;
import model.UnitModel.UnitContainer;
import view.UnitRow;
import view.UnitsTable.OnActionListener;
import view.popup.ViewUnitPopup;

public class UnitRowController extends Controller<UnitRow, ApplicationController>{
	protected UnitModel unitModel;
	
	protected UnitRowController(ApplicationController mainController) {
		super(mainController);
		
		unitModel = new UnitModel();
	}

	@Override
	protected void initView() {
		view = new UnitRow();
	}
	
	public void addListener(UnitRow row){
		row.setOnActionListener(new OnActionListener(){

			@Override
			public void onAction(int button, String unitNum) {
				
				showPopup(button, unitNum);
			}
			
		});
	}
	
	public void showPopup(int which, String unitNum){
		Popup p = null;
		
		if(which == 1)	// view unit
			p = new ViewUnitPopup(unitModel.getUnit(unitNum));
		
		p.show(mainController.mainStage);
		p.setX(200);
		p.setY(100);
	}


}
