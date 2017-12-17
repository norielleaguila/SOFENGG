package controller;

import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import view.*;
import view.popup.AddExpenses;
import view.popup.ViewUnitPopup;

public class LoginController extends Controller<LoginView, ApplicationController> {
	public LoginController (ApplicationController mainController) {
		super (mainController);
	}

	protected void initView () {
		view = new LoginView ();

		view.setOnLoginListener ((user, pass) -> {
			boolean successful = AccountModel.getInstance ().login (user, pass);

			if (successful)
				mainController.programController.setAsScene ();
			else{
				if(user.equals("") || user == null){
					view.setNotif("Username cannot be empty!");
				}
				else if(pass.equals("") || pass == null){
					view.setNotif("Password cannot be empty!");
				}
				/*else if(!AccountModel.getInstance().login(user, pass)){
					view.setNotif("User does not exist.");
				}*/
				else {
					/* invalid credentials condition */
					view.setNotif("Invalid login.");
				}
			}
		});
	}
}