package controllers;

import javafx.stage.Stage;
import views.LoginScreen;

public class LoginDriver extends Controller{

	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		LoginScreen loginScreen = new LoginScreen();
		
		initWindow(arg0, loginScreen.getScene());
		
	}
	
}
