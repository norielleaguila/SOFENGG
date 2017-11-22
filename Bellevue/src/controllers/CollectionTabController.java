package controllers;

import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Fee;
import models.FeeList;
import views.AddCategory;
import views.AddCategory.OnAddEventHandler;
import views.CollectionDialog;
import views.CollectionTab;

public class CollectionTabController extends Controller{
	private CollectionTab view;
	private FeeList model;
	
	public CollectionTabController(FeeList model, Stage window){
		this.model = model;
		view = new CollectionTab(model, window);
		
		setUpButtons();
	}
	
	public CollectionTab getView(){
		return view;
	}

	@Override
	public void setUpButtons() {
		initAddCategory();
	}
	
	public void initAddCategory(){
		AddCategory ac = view.getACPopup();
		
		ac.setOnAddEventHandler(new AddCategory.OnAddEventHandler(){

			@Override
			public void onAction(String category) {
				model.addFee(new Fee(category));
				
				ac.hide();
				
				view.update();
			}
		
		});
	}
	
	public void initCollectionDialog(){
		CollectionDialog cd = view.getCD();
		
		cd.setOnAddEventHandler(new views.CollectionDialog.OnAddEventHandler(){

			@Override
			public void onAction(String name, String cost, String category) {
				model.addFee(new Fee(name, category, Double.parseDouble(cost)));
				
				cd.hide();
				
				view.update();
			}
		
		});
	}
}
