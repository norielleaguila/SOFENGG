package controllers;

import javafx.stage.Stage;
import models.AccountList;
import views.LoginScreen;
import views.ProgramScreen;

/**
 * @author AGUILA, Norielle
 */

public class ProgramController extends Controller{
	private ProgramScreen view;
	
	public ProgramController(ProgramScreen view, Stage window){
		this.view = view;
		super.window = window;
		this.setScene(view.getScene());
		this.window.setFullScreen(true);
	}

	@Override
	public void setUpButtons() {
		view.getLogoutBtn().setOnAction(e -> {
			window.hide();
			window = new Stage();
			window.show();
			window.setTitle("Bellevue Systems");
//			window.setMaximized(true);
			window.setFullScreen(true);
			
			LoginScreen loginScreen = new LoginScreen();
			AccountList accounts = new AccountList();
			
			LoginController login = new LoginController(accounts, loginScreen, window);
			
			login.setUpButtons();
			login.onEnterLogin();
		});
	}

}
