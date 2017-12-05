package view;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import view.popup.ViewUnitPopup;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import controller.*;

public class LoginView extends VBox implements View {
	
	private Label appName;
	private TextField usernameTF;
	private PasswordField passwordPF;
	private Button loginBtn;
	
	private OnLoginListener onLoginListener;
	
	public LoginView(){
		super ();
		init ();
	}
	
	private void init(){
		
		getStylesheets().add("stylesheets/style.css");
		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		
		initAppName();
		initLoginTF();
		initLoginBtn();
		
		this.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER)
				loginBtn.fire();
		});
	}
	
	private void initAppName(){
		appName = new Label();
		
		appName.setText(ApplicationController.APP_NAME);
		
		appName.setId("appNameLbl");
		
		getChildren().add(appName);
	}
	
	private void initLoginTF(){
		usernameTF = new TextField();
		passwordPF = new PasswordField();
		
		// username field styling
		usernameTF.setMaxSize(Size.TF_PREF_WIDTH, Size.TF_PREF_HEIGHT);
		usernameTF.setMinSize(Size.TF_PREF_WIDTH, Size.TF_PREF_HEIGHT);
		
		usernameTF.setPromptText("Enter username");
		
		// password field styling
		passwordPF.setMaxSize(Size.TF_PREF_WIDTH, Size.TF_PREF_HEIGHT);
		passwordPF.setMinSize(Size.TF_PREF_WIDTH, Size.TF_PREF_HEIGHT);
		
		passwordPF.setPromptText("Enter password");
		
		// add elements to layout
		getChildren().add(usernameTF);
		getChildren().add(passwordPF);
	}
	
	private void initLoginBtn(){
		loginBtn = new Button();
		loginBtn.setText("Login");
		
		loginBtn.setId("loginBtn");
		
		loginBtn.setMaxSize(Size.BTN_PREF_WIDTH * 0.75, Size.BTN_PREF_HEIGHT);
		loginBtn.setMinSize(Size.BTN_PREF_WIDTH * 0.75, Size.BTN_PREF_HEIGHT);
		
		loginBtn.setOnAction(e -> {
			if(onLoginListener != null)
				onLoginListener.onAction(usernameTF.getText(), passwordPF.getText());
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
		public void onAction(String username, String password);
	}
}