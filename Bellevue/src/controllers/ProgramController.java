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
	private Account loggedAccount;
	private AccountList accountModel;
	
	public ProgramController(ProgramScreen view, Stage window, AccountList accountModel){
		this.view = view;
		super.window = window;
		this.setScene(view.getScene());
//		this.window.setFullScreen(true);
		System.out.println("Logged in: " + account.getUsername());
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
