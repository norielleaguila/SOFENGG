package controllers;

import views.View;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Account;
import models.AccountList;
import models.CollectionList;
import models.FeeList;
import models.Model;
import models.UnitList;

public abstract class Controller{
	protected Model model;
	protected View view;
	protected Stage window;
	protected static Account account;
	
	public abstract void setUpButtons();
	
	public Controller(){
		window = new Stage();
		window.setTitle(View.APP_NAME);
	}
	
	public Controller(Stage window){
		this.window = window;
	}
	
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
	}
	
	public Controller(Model model, View view, Stage window){
		this(model, view);
		this.window = window;
		this.window.setTitle(View.APP_NAME);
	}
	
	public void login(Account loggedInAccount){
		account = loggedInAccount;
	}
	
	public void logout(){
		account = null;
	}
	
	public void setScene(Scene scene){
		this.window.setScene(scene);
	}
	
	public void setWindow(Stage window){
		this.window = window;
	}
	
	public Stage getWindow(){
		return window;
	}
	
	public FeeList getFeesModel(){
		// query db
		
		FeeList model = new FeeList();
		
		return model;
	}
	
	public UnitList getUnitsModel(){
		// query db
		
		UnitList model = new UnitList();
		
		return model;
	}
	
	public AccountList getAccountsModel(){
		// query db
		
		AccountList model = new AccountList();
		
		return model;
	}
	
	public CollectionList getCollectionModel(){
		// query db
		
		CollectionList model = new CollectionList();
		
		return model;
	}
	
}
