package controller;

import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import view.*;
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
				mainController.getProgramController ().setAsScene ();
		});
	}
}