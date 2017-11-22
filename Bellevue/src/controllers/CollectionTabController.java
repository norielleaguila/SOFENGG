package controllers;

import models.FeeList;
import views.CollectionTab;

public class CollectionTabController extends Controller{
	private CollectionTab view;
	
	public CollectionTabController(FeeList model){
		view = new CollectionTab(model);
	}
	
	public CollectionTab getView(){
		return view;
	}

	@Override
	public void setUpButtons() {
		// add category item
		view.getMenuItems().get(0).setOnAction(e -> {
			
		});
		
		// add category
		view.getMenuItems().get(1).setOnAction(e -> {
			
		});
	}
}
