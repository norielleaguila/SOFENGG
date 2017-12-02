package controller;

import view.*;

public class LoginController extends Controller<LoginView> {
	
	public LoginController (MainController mainController) {
		super (mainController);
	}

	protected void initView () {
		view = new LoginView (this);
	}
	
	
}