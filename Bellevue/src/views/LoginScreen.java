package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * LoginScreen is the view that greets users in order to access the program.
 * @author AGUILA, Norielle
 */

public class LoginScreen extends View {
	private VBox layout;
	
	private TextField username;
	private PasswordField password;
	private Button loginBtn;
	
	private Label notif;
	
	public LoginScreen(){
		setUpLayout();
		
		createElements();
		
		addToLayout();
		
		scene = new Scene(layout, 800, 500);
	}
	
	/**
	 * setUpLayout initializes a new VBox element as the main layout of this view
	 */
	protected void setUpLayout(){
		layout = new VBox(10);
		layout.setPadding(new Insets(25, 25, 25, 25));
		layout.setAlignment(Pos.CENTER);
		
		layout.setBackground(new Background((new BackgroundFill(Color.rgb(252, 149, 134), CornerRadii.EMPTY, Insets.EMPTY))));
	}
	
	/**
	 * createElements instantiates the elements of this view 
	 */
	protected void createElements(){
		// create notif label
		notif = new Label("");
		notif.setMinSize(200, 20);
		notif.setPadding(new Insets(10));
		notif.setAlignment(Pos.CENTER);
		
		// create username field
		username = new TextField();
		username.setPromptText("Username");
		username.setMaxSize(200, 20);
		
		// create password field
		password = new PasswordField();
		password.setPromptText("Password");
		password.setMaxSize(200, 20);
		
		// create login button
		loginBtn = new Button("LOGIN");
		loginBtn.setBackground(new Background((new BackgroundFill(Color.rgb(184, 196, 128), CornerRadii.EMPTY, Insets.EMPTY))));
		loginBtn.setTextFill(Color.WHITE);
		loginBtn.setMaxSize(100, 20);
	}
	
	protected void addToLayout(){
		layout.getChildren().add(notif);
		layout.getChildren().add(username);
		layout.getChildren().add(password);
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

	public TextField getUsername() {
		return username;
	}

	public void setUsername(TextField username) {
		this.username = username;
	}

	public PasswordField getPassword() {
		return password;
	}
	
	public void setPassword(PasswordField password) {
		this.password = password;
	}

	public Button getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(Button loginBtn) {
		this.loginBtn = loginBtn;
	}
	
	public Label getNotif(){
		return notif;
	}
	
	public void setNotif(String notif){
		this.notif.setText(notif);
		this.notif.setBackground(new Background((new BackgroundFill(Color.WHITE, new CornerRadii(2), Insets.EMPTY))));
	}
}
