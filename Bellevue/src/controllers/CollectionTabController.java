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
		// TODO Auto-generated method stub
		
	}
}
