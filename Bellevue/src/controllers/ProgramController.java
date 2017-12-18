package controllers;

import javafx.stage.Stage;
import models.Account;
import models.AccountList;
import views.LoginScreen;
import views.ProgramScreen;

/**
 * @author AGUILA, Norielle
 */

public class ProgramController extends Controller{
	private ProgramScreen view;
	private AccountList accountModel;
	
	public ProgramController(ProgramScreen view, Stage window, AccountList accountModel){
		this.view = view;
		super.window = window;
		this.setScene(view.getScene());

		this.window.setMaximized(false);
		this.window.setMaximized(true);
		this.accountModel = accountModel;
		
	}

	@Override
	public void setUpButtons() {
		view.getLogoutBtn().setOnAction(e -> {
			logout();
			
			LoginScreen loginScreen = new LoginScreen();
			LoginController login = new LoginController(accountModel, loginScreen, window);
			
			login.setUpButtons();
			login.onEnterLogin();
			
		});
	}

}
