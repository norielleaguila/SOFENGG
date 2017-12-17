package view;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.geometry.*;
import controller.*;

public class LoginView extends VBox implements View {

	private VBox loginVBox;
	
	private Label appName;
	private TextField usernameTF;
	private PasswordField passwordPF;
	private Button loginBtn;
	
	private OnLoginListener onLoginListener;
	
	public interface OnLoginListener{
		public void onAction(String username, String password);
	}
	
	public LoginView(){
		super ();
		init ();
	}
	
	private void init(){
		getStylesheets().add("style/loginStylesheet.css");
		
		this.setAlignment(Pos.CENTER);
		this.setFillWidth(false);

		initLoginVBox();
		
		this.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER)
				loginBtn.fire();
		});
		
		getChildren().add(loginVBox);
	}
	
	private void initLoginVBox(){
		loginVBox = new VBox();
		
		loginVBox.setAlignment(Pos.CENTER);
		loginVBox.setSpacing(15);
		
		loginVBox.setId("loginVBox");
		
		initAppName();
		initLoginTF();
		initLoginBtn();
	}
	
	private void initAppName(){
		appName = new Label();
		
		appName.setText(ApplicationController.APP_NAME);
		
		appName.setId("appNameLbl");
		
		loginVBox.getChildren().add(appName);
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
		loginVBox.getChildren().add(usernameTF);
		loginVBox.getChildren().add(passwordPF);
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
		
		loginVBox.getChildren().add(loginBtn);
	}
	
	@Override
	public void update(){
		
	}
	
	public void setOnLoginListener(OnLoginListener onLoginListener){
		this.onLoginListener = onLoginListener;
	}
	
	
}