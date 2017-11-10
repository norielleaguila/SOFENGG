package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import models.FeeList;
import views.CollectionTab;
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
		
		TabPane layout = new TabPane();
		
		CollectionTab tab = new CollectionTab(fees);
		
		layout.getTabs().add(new Tab("COLLECTION", tab));
		
		Scene scene = new Scene(layout);
		
		window.setScene(scene);
		
	}
	
	
}
