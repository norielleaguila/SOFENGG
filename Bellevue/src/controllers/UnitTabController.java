package controllers;

import java.util.ArrayList;
import views.UnitRow;
import views.UnitTab;

public class UnitTabController extends Controller{
	private UnitTab view;
	
	public UnitTabController(){
		view = new UnitTab();
		
		setUpButtons();
	}
	
	public UnitTab getView(){
		return view;
	}

	@Override
	public void setUpButtons() {
		ArrayList<UnitRow> rows = view.getTable().getUnitList().getAllRows();
		
		if(rows != null){
			for(UnitRow row: rows){
				row.setViewBtnListener(new UnitRow.viewBtnlistener() {
					
					@Override
					public void onAction(int unitNo) {
						// place all action things here
						
						
						
						System.out.println(unitNo);
					}
				});
			}
		}
	}
}
