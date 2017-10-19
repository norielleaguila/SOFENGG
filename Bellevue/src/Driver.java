import java.util.ArrayList;

import controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import models.Account;
import views.LoginScreen;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class Driver extends Application{
	private Stage window;
	private ArrayList<Account> accounts;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		accounts = new ArrayList<>();
		
		initAccounts();
		
		LoginScreen loginScreen = new LoginScreen();
		LoginController login = new LoginController(accounts, loginScreen);
		

		login.setUpButtons();
		login.onEnterLogin();

		window = primaryStage;
		window.setScene(loginScreen.getScene());
		window.show();
	}
	
	// temporary
	public void initAccounts(){
		accounts.add(new Account("admin", "123"));
		accounts.add(new Account("secretary", "123"));
	}
	
}
