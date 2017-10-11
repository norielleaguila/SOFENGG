package controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Controller extends Application{
	protected Stage window;
	
	protected void initWindow(Stage arg0, Scene scene){
		window = arg0;
		window.setScene(scene);
		window.show();
	}
}
