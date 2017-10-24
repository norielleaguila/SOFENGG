package controllers;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import models.Account;
import views.LoginScreen;

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
			if(view.getUsernameField().getText().equals(accounts.get(i).getUsername()) && view.getPasswordField().getText().equals(accounts.get(i).getPassword())){
				view.setNotif("Welcome, " + view.getUsernameField().getText() + "!");
				pass = true;
			}
			i++;
		}
		
		/* empty field failure condition */
		if(!pass){
			if(view.getUsernameField().getText().equals("") || view.getUsernameField().getText() == null){
				view.setNotif("Username cannot be empty!");
			}
			else if(view.getPasswordField().getText().equals("") || view.getPasswordField().getText() == null){
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
