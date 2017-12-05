package controller;

import view.UnitTab;

public class UnitTabController extends Controller<UnitTab, ApplicationController>{

	public UnitTabController (ApplicationController mainController) {
		super (mainController);
	}

	@Override
	protected void initView() {
		view = new UnitTab();
	}
}
