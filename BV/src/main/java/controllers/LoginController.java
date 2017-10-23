package controllers;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import models.Account;
import views.LoginScreen;
import Utilities.SheetsDB;

/**
 * 
 * @author AGUILA, Norielle
 *
 */
public class LoginController extends Controller{
	private LoginScreen view;
//	private String user = "admin", pass = "123";
	private ArrayList<Account> accounts;
	
	public LoginController(ArrayList<Account> accounts, LoginScreen view){
		this.accounts = accounts;
		this.view = view;
	}
	
	public void onEnterLogin(){
		view.getScene().setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)){
				validate();
			}
		});
	}
	
	public void setUpButtons(){
		view.getLoginBtn().setOnAction(e -> {
			validate();
		});
	}
	
	public void validate(){
		
		boolean pass = false;
		
		/* checks if there is a matching account in the db */
		int i = 0;
		while(i < accounts.size() && !pass){
			if(SheetsDB.LogIn(view.getUsername().getText(), view.getPassword().getText())){
				view.setNotif("Welcome, " + view.getUsername().getText() + "!");
				pass = true;
				
			}
			i++;
		}
		
		/* empty field failure condition */
		if(!pass){
			if(view.getUsername().getText().equals("") || view.getUsername().getText() == null){
				view.setNotif("Username cannot be empty!");
			}
			else if(view.getPassword().getText().equals("") || view.getPassword().getText() == null){
				view.setNotif("Password cannot be empty!");
			}
			else {
				/* invalid credentials condition */
				view.setNotif("Invalid login.");
			}
		}
		
		/* updates the view */
		view.resetLayout();
	}
}
