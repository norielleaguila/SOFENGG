package controller;

import javafx.scene.control.*;
import view.*;
import view.LoginView.OnLoginListener;

public class LoginController extends Controller<LoginView> {
	
	public LoginController (MainController mainController) {
		super (mainController);
	}

	protected void initView () {
		view = new LoginView ();

		view.setOnLoginListener(new LoginView.OnLoginListener() {
			
			@Override
			public void OnAction(String username, String password) {
				// login
			}
		});
	}
	
	
}