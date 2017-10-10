package controllers;

import javafx.application.Application;
import javafx.stage.Stage;
import views.LoginScreen;

public class LoginDriver extends Application{
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		LoginScreen loginScreen = new LoginScreen();
		window.setScene(loginScreen.getScene());
		window.show();
	}
	
}
