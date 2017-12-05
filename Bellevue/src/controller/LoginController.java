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
		
		ViewUnitPopup v = new ViewUnitPopup();
		v.show(MainController.mainStage);
		v.setX(100);
		v.setY(100);

		view.setOnLoginListener ((user, pass) -> {
			boolean successful = AccountModel.getInstance ().login (user, pass);

			if (successful)
				mainController.getProgramController ().setAsScene ();
		});
	}
}