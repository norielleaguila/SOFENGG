package view;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;
import controller.*;

public class LoginView extends VBox implements View {

	private LoginController controller;
	
	public LoginView (LoginController controller) {
		super ();
		this.controller = controller;
		init ();
	}
	
	private void init () {
		Label lbl = new Label ("Hello");
		getChildren ().add (lbl);
	}
	
	public void update () {
		
	}
	
}