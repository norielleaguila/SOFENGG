package views;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import models.Fees;

/**
 * @author AGUILA, Norielle
 */


public class CollectionTable extends ScrollPane{
	
	private GridPane table;
//	private ArrayList<Label> labels;
	private ArrayList<Fees> fees; 	// the list is assumed to be sorted by type
	private ArrayList<Label> typeLbls;
	
	public CollectionTable(ArrayList<Fees> fees){
		table = new GridPane();
		this.fees = fees;
		
		initTable();
		
		this.setContent(table);
	}
	
	public void initTable(){
		initTypes();
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

	
}
