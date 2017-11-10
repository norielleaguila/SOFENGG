package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.FeeList;
import views.CollectionTable;

public class CollectionModule extends Application{
	private FeeList fees;
	
	public static void main(String[] ARG){
		launch(ARG);
	}

	@Override
	public void start(Stage window) throws Exception {
		window.show();
		window.setTitle("Bellevue Systems");
		
		fees = new FeeList();
		CollectionTable table = new CollectionTable(fees);
		
		Scene scene = new Scene(table);
		
		window.setScene(scene);
		
	}
	
	
}
