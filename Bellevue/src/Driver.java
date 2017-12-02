import javafx.application.*;
import javafx.stage.*;
import controller.*;

public class Driver extends Application {

	public void start (Stage mainStage) {
		new ApplicationController (mainStage);
	}
	
	public static void main (String[] args) {
		launch (args);
	}
	
}