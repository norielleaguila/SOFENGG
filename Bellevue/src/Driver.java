import javafx.application.*;
import javafx.stage.*;
import controller.*;
import model.database.*;
import view.popup.ViewUnitPopup;

public class Driver extends Application {

	public static void login () {
		DatabaseManager databaseManager = MySQLDatabase.getInstance ();
		String host = "localhost";
		int port = 3306;
		String dbname = "bellevue_logger";
		String username = "root";
		String password = "0825";

		databaseManager.setConnection (host, port, dbname, username, password);
	}

	public void start (Stage mainStage) {
		Driver.login ();
		
		
		mainStage.show();
		
		
		
		
		new ApplicationController (mainStage);
	}
	
	public static void main (String[] args) {
		launch (args);
	}
	
}