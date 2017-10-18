package controllers;

import javafx.scene.input.KeyCode;
import views.LoginScreen;

/**
 * 
 * @author AGUILA, Norielle
 *
 */
public class LoginController extends Controller{
	private LoginScreen view;
	private String user = "admin", pass = "123";
	
	public LoginController(LoginScreen view){
		this.view = view;
		setUpButtons();
		onEnterLogin();
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
		/* empty failure condition */
		if(view.getUsername().getText().equals("") || view.getUsername().getText() == null){
			view.setNotif("Username cannot be empty!");
		}
		else if(view.getPassword().getText().equals("") || view.getPassword().getText() == null){
			view.setNotif("Password cannot be empty!");
		}
		
		/* invalid credentials condition */
		else if(!view.getUsername().getText().equals(user) || !view.getPassword().getText().equals(pass)){
			view.setNotif("Invalid login.");
		}
		
		else{
			view.setNotif("Welcome, " + view.getUsername().getText() + "!");
		}
		view.resetLayout();
	}
}
