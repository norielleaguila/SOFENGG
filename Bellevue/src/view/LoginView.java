package view;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import controller.*;

public class LoginView extends VBox implements View {
	
	private Label appName;
	private TextField usernameTF;
	private TextField passwordTF;
	private Button loginBtn;
	
	private OnLoginListener onLoginListener;
	
	public LoginView(){
		super ();
		init ();
		
		
	}
	
	private void init(){
		
		getStylesheets().add("stylesheets/style.css");
		getStylesheets().add("stylesheets/loginStylesheet.css");
		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		
		initAppName();
		initLoginTF();
		initLoginBtn();
	}
	
	private void initAppName(){
		appName = new Label();
		
		appName.setText(ApplicationController.APP_NAME);
		
		appName.setId("appNameLbl");
		
		getChildren().add(appName);
	}
	
	private void initLoginTF(){
		usernameTF = new TextField();
		passwordTF = new TextField();
		
		// username field styling
		usernameTF.setMaxSize(Size.TF_PREF_WIDTH, Size.TF_PREF_HEIGHT);
		usernameTF.setMinSize(Size.TF_PREF_WIDTH, Size.TF_PREF_HEIGHT);
		
		usernameTF.setPromptText("Enter username");
		
		// password field styling
		passwordTF.setMaxSize(Size.TF_PREF_WIDTH, Size.TF_PREF_HEIGHT);
		passwordTF.setMinSize(Size.TF_PREF_WIDTH, Size.TF_PREF_HEIGHT);
		
		passwordTF.setPromptText("Enter password");
		
		// add elements to layout
		getChildren().add(usernameTF);
		getChildren().add(passwordTF);
	}
	
	private void initLoginBtn(){
		loginBtn = new Button();
		loginBtn.setText("Login");
		
		loginBtn.setId("loginBtn");
		
		loginBtn.setMaxSize(Size.BTN_PREF_WIDTH * 0.75, Size.BTN_PREF_HEIGHT);
		loginBtn.setMinSize(Size.BTN_PREF_WIDTH * 0.75, Size.BTN_PREF_HEIGHT);
		
		loginBtn.setOnAction(e -> {
			if(onLoginListener != null)
				onLoginListener.OnAction(usernameTF.getText(), passwordTF.getText());
		});
		
		getChildren().add(loginBtn);
	}
	
	@Override
	public void update(){
		
	}
	
	public void setOnLoginListener(OnLoginListener onLoginListener){
		this.onLoginListener = onLoginListener;
	}
	
	public interface OnLoginListener{
		public void OnAction(String username, String password);
	}
}