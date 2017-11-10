
import controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import models.AccountList;
import views.LoginScreen;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class Driver extends Application{
	public static boolean validated = false;
	private Stage window;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.show();
		window.setTitle("Bellevue Systems");
//		window.setMaximized(true);
		window.setFullScreen(true);
		
		LoginScreen loginScreen = new LoginScreen();
		AccountList accounts = new AccountList();
		
		LoginController login = new LoginController(accounts, loginScreen, window);
		
		login.setUpButtons();
		login.onEnterLogin();
	}
}
