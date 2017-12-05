package view;

import javafx.scene.layout.VBox;
import model.UnitModel;

public class UnitTab extends VBox implements View{
	
	private UnitModel unitModel;
	
	public UnitTab(){
		unitModel = new UnitModel();
	}

	@Override
	public void update() {

	}
}
