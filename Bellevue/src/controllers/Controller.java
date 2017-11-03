package controllers;

import views.View;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Model;

public abstract class Controller{
	protected Model model;
	protected View view;
	protected Stage window;
	
	public abstract void setUpButtons();
	
	public Controller(){
		window = new Stage();
	}
	
	public Controller(Stage window){
		this.window = window;
	}
	
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
	}
	
	public Controller(Model model, View view, Stage window){
		this.model = model;
		this.view = view;
		this.window = window;
	}
	
	public void setScene(Scene scene){
		this.window.setScene(scene);
	}
	
	public void setWindow(Stage window){
		this.window = window;
	}
	
	
}
