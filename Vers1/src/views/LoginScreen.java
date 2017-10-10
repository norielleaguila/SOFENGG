package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
public class LoginScreen {
	private Scene scene;
	private VBox layout;
	
	private Label[] textPrompts;
	private TextField username;
	private PasswordField password;
	private Button loginBtn;
	
	public LoginScreen(){
		setUpLayout();
		
		createElements();
		
		addToLayout();
		
		scene = new Scene(layout);
	}
	
	/*
	 * setUpLayout initializes a new VBox element as the main layout of this view
	 */
	public void setUpLayout(){
		layout = new VBox(10);
		layout.setPadding(new Insets(25, 25, 25, 25));
		layout.setAlignment(Pos.CENTER_LEFT);
	}
	
	/*
	 * createElements instantiates the elements of this view 
	 */
	public void createElements(){
		// initialize text prompts
		textPrompts = new Label[]{new Label("Username:"), new Label("Password:")};
		
		// create username field
		username = new TextField();
		username.setPrefSize(200, 20);
		
		// create password field
		password = new PasswordField();
		password.setPrefSize(200, 20);
		
		// create login button
		loginBtn = new Button("Login");
		loginBtn.setPrefSize(200, 20);
	}
	
	public void addToLayout(){
		layout.getChildren().add(textPrompts[0]);
		layout.getChildren().add(username);
		layout.getChildren().add(textPrompts[1]);
		layout.getChildren().add(password);
		layout.getChildren().add(loginBtn);
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

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
	
	
}
