package controller;

import view.SearchBar;

public class SearchBarController extends Controller<SearchBar, ApplicationController> {

	protected SearchBarController(ApplicationController mainController) {
		super(mainController);
	}

	@Override
	protected void initView() {
		view = new SearchBar();
	}
	
}
