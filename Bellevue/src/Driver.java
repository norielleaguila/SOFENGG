import controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.LoginScreen;

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
		LoginScreen loginScreen = new LoginScreen();
		LoginController login = new LoginController(loginScreen);

		window = primaryStage;
		window.setScene(loginScreen.getScene());
		window.show();
	}
	
}
