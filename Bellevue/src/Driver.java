import javafx.application.*;
import javafx.stage.*;
import controller.*;
import model.database.*;

public class Driver extends Application {

	public static void login () {
		DatabaseManager databaseManager = MySQLDatabase.getInstance ();
		String host = "localhost";
		int port = 3306;
		String dbname = "bellevue_logger";
		String username = "root";
		String password = "root";

		databaseManager.setConnection (host, port, dbname, username, password);
	}

	public void start (Stage mainStage) {
		Driver.login ();

		new ApplicationController (mainStage);
	}
	
	public static void main (String[] args) {
		launch (args);
	}
	
}