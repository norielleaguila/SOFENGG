package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {
	private Scene scene;
	private VBox layout;
	
	private TextField username;
	private PasswordField password;
	private Button loginBtn;
	
	public LoginScreen(){
		
		setUpLayout();
		
		createElements();
		
		scene = new Scene(layout);
	}
	
	/*
	 * setUpLayout initializes a new VBox element as the main layout of this view
	 */
	public void setUpLayout(){
		layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setAlignment(Pos.CENTER);
	}
	
	/*
	 * createElements instantiates the elements of this view 
	 */
	public void createElements(){
		username = new TextField();
		password = new PasswordField();
		loginBtn = new Button("Login");
	}
	
	public void addToLayout(){
		layout.getChildren().add(username);
		layout.getChildren().add(password);
		layout.getChildren().add(loginBtn);
	}
	
}
