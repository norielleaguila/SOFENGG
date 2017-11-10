package views;

import javafx.scene.layout.BorderPane;
import models.FeeList;

/**
 * @author AGUILA, Norielle
 */

public class CollectionTab extends Tabs{
	
	private BorderPane layout;
	private FeeList model;
	
	public CollectionTab(FeeList model){
		super();
		
		layout = new BorderPane();
		
		this.model = model;
		
	}
	
	public void initLayout(){
		layout.setCenter(new CollectionTable(model));
		
	}
	
	
	
	
}
