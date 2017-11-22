package controllers;

import DB.DBaccess;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import models.Account;
import models.AccountList;
import models.FeeList;
import views.LoginScreen;
import views.ProgramScreen;

/**
 * 
 * @author AGUILA, Norielle
 *
 */
public class LoginController extends Controller{
	private LoginScreen view;
	private AccountList model;
	
	public LoginController(AccountList model, LoginScreen view, Stage window){
		super(window);
		
		this.model = model;
		this.view = view;
		this.setScene(view.getScene());
		
		window.setMaximized(false);
		window.setMaximized(true);
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
		
		// checks if there is a matching account in the db
		for(int i = 0; i < model.getAccounts().size() && !pass; i++){
			if(model.exists(view.getUsernameField().getText())){
				if(model.verifyAccount(view.getUsernameField().getText(), view.getPasswordField().getText())){
					view.setNotif("Welcome, " + view.getUsernameField().getText() + "!");
					login(model.getAccount(view.getUsernameField().getText()));
					pass = true;
				}
			}
		}
		

		Account temp = DBaccess.login(view.getUsernameField().getText(), view.getPasswordField().getText());
		if(temp!=null)
			super.account = temp;
			pass=true;
		System.out.println(pass);
		/* empty field failure condition */
		if(!pass){
			if(view.getUsernameField().getText().equals("") || view.getUsernameField().getText() == null){
				view.setNotif("Username cannot be empty!");
			}
			else if(view.getPasswordField().getText().equals("") || view.getPasswordField().getText() == null){
				view.setNotif("Password cannot be empty!");
			}
			else if(!model.exists(view.getUsernameField().getText())){
				view.setNotif("User does not exist.");
			}
			else {
				/* invalid credentials condition */
				view.setNotif("Invalid login.");
			}
		}
		
		/* updates the view */
		view.resetLayout();
		
		if(pass)
			openProgram();
	}

	// switch to program on valid login
	public void openProgram(){
		ProgramScreen ps = new ProgramScreen(getFeesModel(), getUnitsModel(), window);
		ProgramController program = new ProgramController(ps, window, model);
		
		program.setUpButtons();
	}
}
