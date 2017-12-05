package controller;

import javafx.stage.*;

public class ApplicationController extends MainController {

	public static final String APP_NAME = "Bellevue Logger";

	private LoginController loginController;
	private UnitTabController unitTabController;
	private ProgramController programController;
	
	public ApplicationController (Stage mainStage) {
		super (mainStage);
		mainStage.setMaximized (true);
		mainStage.setTitle (APP_NAME);
	}
	
	protected void initControllers () {
		loginController = new LoginController (this);
		unitTabController = new UnitTabController (this);
		programController = new ProgramController (this);

		loginController.setAsScene ();
	}

	public LoginController getLoginController () {
		return loginController;
	}

	public UnitTabController getUnitTabController () {
		return unitTabController;
	}
	
	public ProgramController getProgramController (){
		return programController;
	}

}