package controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import views.ProgramScreen;
import views.UnitList;
import views.UnitTab;

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
//		ProgramScreen view = (ProgramScreen) this.view;
//		UnitTab tab = (UnitTab) view.getTabContainer().getTabs().get(0).getContent();
//		UnitList units = (UnitList) tab.getTable().getTableScroll().getContent();
//		Label unit = (Label) units.getRow(0).getChildren().get(1);
//		System.out.println(unit.getText());
	}

}
