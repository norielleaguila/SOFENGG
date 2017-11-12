package views;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.FeeList;

/**
 * @author AGUILA, Norielle
 */

public class CollectionTab extends Tabs{
	
	private BorderPane layout;
	private VBox rightPane;
	private ChoiceBox<String> editCB;
	private FeeList model;
	
	public CollectionTab(FeeList model){
		super();
		
		layout = new BorderPane();
		rightPane = new VBox();
		
		this.model = model;
		
		initLayout();
	}
	
	public void initLayout(){
		initRightPane();
		
		CollectionTable tables = new CollectionTable(model);
		tables.setPrefHeight(View.HEIGHT);
		
		layout.setCenter(tables);
		layout.setRight(rightPane);
		
		this.getChildren().add(layout);
	}
	
	public void initCB(){
		editCB = new ChoiceBox<String>();
		
		editCB.getItems().add("ADD COLLECTION ITEM");
		editCB.getItems().add("ADD NEW CATEGORY");
		editCB.getItems().add("EDIT ITEM");
		editCB.getItems().add("EDIT CATEGORY");
		editCB.getItems().add("DELETE COLLECTION ITEM");
		editCB.getItems().add("DELETE CATEGORY");
		
		editCB.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        executeSelection(editCB.getItems().indexOf(newValue));
                        System.out.println(newValue);
                        editCB.getSelectionModel().clearSelection();
                    }
                }
        );
		
		editCB.setId("CB");
		
	}
	
	public void initRightPane(){
		initCB();
		
		rightPane.getChildren().add(editCB);
		
		rightPane.setAlignment(Pos.BOTTOM_CENTER);
	}
	
	public void executeSelection(int index){
		switch(index){
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		}
	}
	
	
	
}
