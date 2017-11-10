package views;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.FeeList;

/**
 * ProgramScreen is the view that contains all necessary information.
 * @author AGUILA, Norielle
 */

public class ProgramScreen extends View{
	
	private BorderPane layout;
	private FeeList feesModel;
	
	public ProgramScreen(FeeList feesModel){
		super();
		
		this.feesModel = feesModel;
		
		initLayout();
		
		initScreen();
		
		scene = new Scene(layout, WIDTH, HEIGHT);
	}

	@Override
	protected void initLayout() {
		layout = new BorderPane();
		
		createElements();
		
		addToLayout();
	}

	@Override
	protected void createElements() {
		
	}

	@Override
	protected void addToLayout() {
		layout.setCenter(new TabContainer(feesModel));
	}

	@Override
	public void resetLayout() {
		
	}

}
