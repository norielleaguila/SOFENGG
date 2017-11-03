import java.util.ArrayList;

import controllers.LoginController;
import controllers.ProgramController;
import javafx.application.Application;
import javafx.stage.Stage;
import models.Account;
import views.LoginScreen;
import views.ProgramScreen;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class Driver extends Application{
	public static boolean validated = false;
	private Stage window;
	private ArrayList<Account> accounts;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.show();
		window.setTitle("Bellevue Systems");
		window.setMaximized(true);
		
		accounts = new ArrayList<>();
		
		initAccounts();
		
		LoginScreen loginScreen = new LoginScreen();
		LoginController login = new LoginController(accounts, loginScreen, window);
		
		login.setUpButtons();
		login.onEnterLogin();
	}
	
	// temporary
	public void initAccounts(){
		accounts.add(new Account("admin", "123"));
		accounts.add(new Account("secretary", "123"));
	}
	
}
