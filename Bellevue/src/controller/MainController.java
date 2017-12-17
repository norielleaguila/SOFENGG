package controller;

import view.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public abstract class MainController {
	
	protected Stage mainStage;
	protected Scene scene;
	
	public MainController (Stage stage) {
		mainStage = stage;
		mainStage.setMinWidth (500);
		mainStage.setMinHeight (500);
		
		scene = new Scene (new Group (), mainStage.getWidth (), mainStage.getHeight ());
		
		initControllers ();
		
		stage.setScene (scene);
		stage.show ();
	}
	
	protected abstract void initControllers ();
	
	public void setScene (Parent parent) {
		scene.setRoot (parent);
	}
	
	public Stage getStage () {
		return mainStage;
	}
	
}