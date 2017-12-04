package controller;

import javafx.stage.*;

public class ApplicationController extends MainController {
	public static final String APP_NAME = "Bellevue Logger";
	
	public ApplicationController (Stage mainStage) {
		super (mainStage);
		mainStage.setMaximized (true);
		mainStage.setTitle (APP_NAME);
	}
	
	protected void initControllers () {
		LoginController login = new LoginController (this);
		
		login.setAsScene ();
	}
	
}