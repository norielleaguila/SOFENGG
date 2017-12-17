package controller;

import javafx.scene.image.Image;
import javafx.stage.*;
import view.popup.AddExpenses;

public class ApplicationController extends MainController {

	public static final String APP_NAME = "Bellevue Logger";

	protected LoginController loginController;
	protected ProgramController programController;
	
	public ApplicationController (Stage mainStage) {
		super (mainStage);
		mainStage.setMaximized (true);
		mainStage.setTitle (APP_NAME);
		mainStage.getIcons().add(new Image("file:src/style/images/bellevue.png"));

		loginController.setAsScene ();
	}
	
	protected void initControllers () {
		loginController = new LoginController (this);
		programController = new ProgramController (this);
	}

}