package views;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Fees;

/**
 * @author AGUILA, Norielle
 */


public class CollectionTable extends ScrollPane{
	
	private VBox tableContainter;
	private ArrayList<VBox> tables;	// 1 VBox = 1 type
	private ArrayList<Fees> fees; 	// the list is assumed to be sorted by type
	private ArrayList<Label> typeLbls;
	
	public CollectionTable(ArrayList<Fees> fees){
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

		typeLbls.add(new Label(fees.get(0).getType()));

		// start at index 1
		for(int i = 1; i < fees.size() - 1; i++){
			String curr = fees.get(i).getType();
			String next = fees.get(i + 1).getType();

			if(!curr.equals(next)){
				typeLbls.add(new Label(next));
			}
		}
	}
	
	/**
	 * creates a separate table for each categorical type
	 */
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
			for(int j = 0; j < fees.size() && fees.get(j).getType().equals(type); j++){
				Fees curr = fees.get(j);
				
				HBox row = new HBox(10);
				
				row.getChildren().add(new Label(curr.getFeeName()));
				row.getChildren().add(new Label(curr.getPrice() + ""));
				
				tables.get(i).getChildren().add(row);
			}
		}
	}

	
}
