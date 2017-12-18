
import controllers.LoginController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.AccountList;
import views.LoginScreen;
import views.View;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class Driver extends Application{
	private Stage window;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.show();
		window.setTitle(View.APP_NAME);
//		window.setMaximized(true);
//		window.setFullScreen(true);
		
		window.getIcons().add(new Image("/style/images/bellevue.png"));
		
		LoginScreen loginScreen = new LoginScreen();
		AccountList accounts = new AccountList();
		
		LoginController login = new LoginController(accounts, loginScreen, window);
		
		login.setUpButtons();
		login.onEnterLogin();
		
//		System.out.println(java.time.LocalDateTime.now().toString());
	}
}
