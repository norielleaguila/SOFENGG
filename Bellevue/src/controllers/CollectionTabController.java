package controllers;

import DB.DBaccess;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Fee;
import models.FeeList;
import views.AddCategory;
import views.AddCategory.OnAddEventHandler;
import views.CategoryDeleteDialog;
import views.CategoryItemDeleteDialog;
import views.CollectionDialog;
import views.CollectionTab;
import views.EditCategoryDialog;
import views.EditItemDialog;
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
		initEditCategoryDialog();
		initCategoryItemDeleteDialog();
		initEditItemDialog();
	}
	
	public void initAddCategory(){
		AddCategory ac = view.getACPopup();
		
		ac.setOnAddEventHandler(new AddCategory.OnAddEventHandler(){

			@Override
			public void onAction(String category) {
				if(!category.equals("")){
					Fee.addType(category);
					
					ac.hide();
					
					view.update();
				}
			}
		
		});
	}
	
	public void initCollectionDialog(){
		CollectionDialog cd = view.getCD();
		
		cd.setOnAddEventHandler(new views.CollectionDialog.OnAddEventHandler(){

			@Override
			public void onAction(String name, String cost, String category) {
				if((name!=null && cost!=null && category!=null) && !(name.equals("") || cost.equals("") || category.equals(""))){
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
			}
		
		});
	}
	public void initEditItemDialog(){
		EditItemDialog eid=view.getEID();
		eid.setOnAddEventHandler(new EditItemDialog.OnAddEventHandler() {
			
			@Override
			public void onAction(String category, String Ncategory, String name, String nameN, float price) {
				// TODO Auto-generated method stub
				if((category!=null && Ncategory!=null && name!=null && nameN!=null) &&
						!(category.equals("") || Ncategory.equals("") || name.equals("") || nameN.equals(""))){
					DBaccess.editFeeType(category,Ncategory,name,nameN,price);
					eid.hide();
					view.update();
					tcupate.update();
				}
			}
		});
	}
	public void initEditCategoryDialog(){
		System.out.println("------able to init EDIT-----");
		EditCategoryDialog ecd = view.getECD();
		ecd.setOnAddEventHandler(new EditCategoryDialog.OnAddEventHandler() {
			

			@Override
			public void onAction(String category, String name) {
				// TODO Auto-generated methediod stub
				System.out.println("Edit val is val is "+category);
				Fee.replaceType(category, name);
				DBaccess.globalEditCategory(category,name);
				ecd.hide();
				view.update();
				//tcupate.update();
				
				
			}
		
		});
	}
	public void initCategoryItemDeleteDialog(){
		CategoryItemDeleteDialog cidd=view.getCIDD();
		cidd.setOnAddEventHandler(new CategoryItemDeleteDialog.OnAddEventHandler() {
			
			@Override
			public void onAction(String category) {
				// TODO Auto-generated method stub
				DBaccess.deleteFeeType(category);
				cidd.hide();
				view.update();
				tcupate.update();
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
