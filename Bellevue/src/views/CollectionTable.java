package views;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Fee;
import models.FeeList;

/**
 * @author AGUILA, Norielle
 */


public class CollectionTable extends ScrollPane{
	
	private VBox tableContainter;
	private ArrayList<VBox> tables;	// 1 VBox = 1 type
	private FeeList fees; 	// the list is assumed to be sorted by type
	private ArrayList<Label> typeLbls;
	
	public CollectionTable(FeeList fees){
		tableContainter = new VBox(10);
		this.fees = fees;
		
		initContainer();
		
		this.setContent(tableContainter);
	}
	
	public void initContainer(){
		initTypes();
		initTables();
	}
	
	/**
	 * create type labels
	 */
	public void initTypes(){
		typeLbls = new ArrayList<Label>();
		ArrayList<String> types = fees.sortByType();
		
		for(String type: types){
			typeLbls.add(new Label(type));
		}
		
	}
	
	public void initTables(){
		// create the vbox, then add the category/type label as a header
		for(int i = 0; i < typeLbls.size(); i++){
			tables.add(new VBox(5));
			tables.get(i).getChildren().add(typeLbls.get(i));
		}
		
		String type = "";
		// each table has 2 columns; for fee name and price
		for(int i = 0; i < typeLbls.size(); i++){
			type = typeLbls.get(i).getText();
			
			// assumes that the fees list is already sorted by type
			for(int j = 0; j < fees.getFees().size() && fees.getFee(j).getType().equals(type); j++){
				Fee curr = fees.getFee(j);
				
				HBox row = new HBox(10);
				
				row.getChildren().add(new Label(curr.getFeeName()));
				row.getChildren().add(new Label(curr.getPrice() + ""));
				
				tables.get(i).getChildren().add(row);
			}
		}
	}

	
}
