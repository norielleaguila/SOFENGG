package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * LoginScreen is the view that greets users in order to access the program.
 * @author AGUILA, Norielle
 */

public class LoginScreen extends View {
	private VBox layout;
	
	private TextField usernameField;
	private PasswordField passwordField;
	private Button loginBtn;
	
	private Label appNameLbl;
	private Label notifLbl;
	
	public LoginScreen(){
		initLayout();
		
		createElements();
		
		addToLayout();
		
		initScreen();		
		
		scene = new Scene(layout, WIDTH, HEIGHT);
	}
	
	/**
	 * setUpLayout initializes a new VBox element as the main layout of this view
	 */
	protected void initLayout(){
		layout = new VBox(10);
		layout.setPadding(new Insets(25, 25, 25, 25));
		layout.setAlignment(Pos.CENTER);
		
		layout.getStylesheets().add("style.css");
	}
	
	/**
	 * createElements instantiates the elements of this view 
	 */
	protected void createElements(){
		// create program name label
		appNameLbl = new Label("[Application Name]");
		appNameLbl.setAlignment(Pos.CENTER);
		appNameLbl.setId("appNameLbl");
		
		// create notif label
		notifLbl = new Label("");
		notifLbl.setMinSize(200, 20);
		notifLbl.setAlignment(Pos.CENTER);
		notifLbl.setId("notifLbl");
		
		// create username field
		usernameField = new TextField();
		usernameField.setPromptText("Username");
		usernameField.setMinSize(200, 20);
		usernameField.setMaxSize(200, 50);
		
		// create password field
		passwordField = new PasswordField();
		passwordField.setPromptText("Password");
		passwordField.setMinSize(200, 20);
		passwordField.setMaxSize(200, 50);
		
		// create login button
		loginBtn = new Button("LOGIN");
		loginBtn.setMinSize(200, 20);
		loginBtn.setMaxSize(200, 50);
		loginBtn.setId("loginBtn");
	}
	
	protected void addToLayout(){
		layout.getChildren().add(appNameLbl);
		layout.getChildren().add(notifLbl);
		layout.getChildren().add(usernameField);
		layout.getChildren().add(passwordField);
		layout.getChildren().add(loginBtn);
	}

	@Override
	public void resetLayout() {
		layout.getChildren().clear();
		addToLayout();
	}

	/* GETTERS & SETTERS */

	public VBox getLayout() {
		return layout;
	}

	public void setLayout(VBox layout) {
		this.layout = layout;
	}

	public TextField getUsernameField() {
		return usernameField;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public Button getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(Button loginBtn) {
		this.loginBtn = loginBtn;
	}
	
	public Label getNotifLbl(){
		return notifLbl;
	}
	
	public void setNotif(String notif){
		this.notifLbl.setText(notif);
		this.notifLbl.setId("notifLbl2");
	}
}
