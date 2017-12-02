package controller;

import javafx.stage.*;

public class ApplicationController extends MainController {
	
	public ApplicationController (Stage mainStage) {
		super (mainStage);
	}
	
	protected void initControllers () {
		LoginController login = new LoginController (this);
		
		login.setAsScene ();
	}
	
}