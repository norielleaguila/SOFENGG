package controller;

import view.CollectionTab;

public class CollectionTabController extends Controller<CollectionTab, ApplicationController>{

	public static final String TAB_NAME = "COLLECTION";

	protected CollectionTabController(ApplicationController mainController) {
		super(mainController);
	}

	@Override
	protected void initView() {
		view = new CollectionTab();
	}

}
