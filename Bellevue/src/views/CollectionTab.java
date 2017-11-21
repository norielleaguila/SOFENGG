package views;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow.AnchorLocation;
import models.FeeList;

/**
 * @author AGUILA, Norielle
 */

public class CollectionTab extends Tabs{
	
	private BorderPane layout;
	private VBox rightPane;
	private ChoiceBox<String> editCB;
	private Button editBtn;
	private FeeList model;
	private ArrayList<MenuItem> menuItems;
	private ContextMenu menu;
	
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
	
	public void initMenuItems(){
		menuItems = new ArrayList<>();
		menuItems.add( new MenuItem("ADD COLLECTION ITEM"));
		menuItems.add(new MenuItem("ADD NEW CATEGORY"));
		menuItems.add(new MenuItem("EDIT ITEM"));
		menuItems.add(new MenuItem("EDIT CATEGORY"));
		menuItems.add(new MenuItem("DELETE COLLECTION ITEM"));
		menuItems.add(new MenuItem("DELETE CATEGORY"));
	}
	
	public void initMenu(){
		menu = new ContextMenu ();
		menu.getItems().addAll(menuItems);
//		menu.setAnchorLocation(AnchorLocation.WINDOW_BOTTOM_RIGHT);
		
		editBtn = new Button("+");
		editBtn.getStylesheets().add("style.css");
		editBtn.setId("collectionEditBtn");
		editBtn.setPrefSize(50, 50);
		
		editBtn.setContextMenu(menu);
		
		editBtn.setOnAction(e -> {
			menu.show(editBtn, Side.TOP, editBtn.getTranslateX(), editBtn.getTranslateY());
		});
	}
	
	public void initRightPane(){
		initMenuItems();
		initMenu();
		
		rightPane.getChildren().add(editBtn);
		
		rightPane.setAlignment(Pos.BOTTOM_CENTER);
		rightPane.setPadding(new Insets(30));
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
	
	
	public ArrayList<MenuItem> getMenuItems(){
		return menuItems;
	}
	
}
