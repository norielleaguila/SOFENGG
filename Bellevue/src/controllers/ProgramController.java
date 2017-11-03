package controllers;

import javafx.stage.Stage;
import views.ProgramScreen;

/**
 * @author AGUILA, Norielle
 */

public class ProgramController extends Controller{
	
	public ProgramController(ProgramScreen view, Stage window){
		this.view = view;
		this.window = window;
		this.setScene(view.getScene());
		this.window.setFullScreen(true);
	}

	@Override
	public void setUpButtons() {
		
	}

}
