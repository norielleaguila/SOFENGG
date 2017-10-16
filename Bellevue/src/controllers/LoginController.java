package controllers;

import views.LoginScreen;

/**
 * 
 * @author AGUILA, Norielle
 *
 */
public class LoginController extends Controller{
	private LoginScreen view;
	
	public LoginController(LoginScreen view){
		this.view = view;
		setUpButtons();
	}
	
	public void setUpButtons(){
		String user = "admin", pass = "pass";
		
		view.getLoginBtn().setOnAction(e -> {
			/* empty failure condition */
			if(view.getUsername().getText().equals("") || view.getUsername().getText() == null){
				view.setNotif("Username cannot be empty!");
				view.resetLayout();
			}
			else if(view.getPassword().getText().equals("") || view.getPassword().getText() == null){
				view.setNotif("Password cannot be empty!");
				view.resetLayout();	
			}
			
			/* invalid credentials condition */
			else if(!view.getUsername().getText().equals(user) || !view.getPassword().equals(pass)){
				view.setNotif("Invalid login.");
				view.resetLayout();
			}
				
			
		});
	}
}
