package controllers;

import DB.DBaccess;
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
		initCollectionDialog();
		
		view.getEditButton().setOnAction( e -> {
			view.allow(account.getType());
		});
	}
	
	public void initAddCategory(){
		AddCategory ac = view.getACPopup();
		
		ac.setOnAddEventHandler(new AddCategory.OnAddEventHandler(){

			@Override
			public void onAction(String category) {
				Fee.addType(category);
				
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
				Fee f=new Fee(name, category, Double.parseDouble(cost));
				model.addFee(f);
				DBaccess.addFee(f);
				cd.hide();
				
				view.update();
			}
		
		});
	}
}
