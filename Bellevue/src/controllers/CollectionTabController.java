package controllers;

import DB.DBaccess;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Fee;
import models.FeeList;
import views.AddCategory;
import views.AddCategory.OnAddEventHandler;
import views.CategoryDeleteDialog;
import views.CollectionDialog;
import views.CollectionTab;
import views.TabContainer;

public class CollectionTabController extends Controller{
	private CollectionTab view;
	private FeeList model;
	private TabContainer tcupate;
	public CollectionTabController(TabContainer tcupate,FeeList model, Stage window){
		this.model = model;
		view = new CollectionTab(model, window);
		this.tcupate=tcupate;
		setUpButtons();
	}
	
	public CollectionTab getView(){
		return view;
	}

	@Override
	public void setUpButtons() {
		initAddCategory();
		initCollectionDialog();
		 initCategoryDeleteDialog();
		
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
				boolean costNaN = false;
				try{Double.parseDouble(cost);}
				catch(NumberFormatException e){costNaN = true;}
				
				if(name == "")
					cd.error(1);
				else if(costNaN)
					cd.error(2);
				else if(category == null)
					cd.error(3);
				else{
					Fee f=new Fee(name, category, Double.parseDouble(cost));
					model.addFee(f);
					DBaccess.addFee(f);
					cd.hide();
					cd.update();
					view.update();
				}
			}
		
		});
	}
	public void initCategoryDeleteDialog(){
		CategoryDeleteDialog cdd = view.getCDD();
		cdd.setOnAddEventHandler(new CategoryDeleteDialog.OnAddEventHandler(){

			@Override
			public void onAction(String category) {
				//Fee.addType(category);
				System.out.println("deleted val is "+category);
				Fee.removeType(category);
				DBaccess.globalRemoveCategory(category);
				cdd.hide();
				view.update();
				tcupate.update();
			}
		
		});
	}
}
