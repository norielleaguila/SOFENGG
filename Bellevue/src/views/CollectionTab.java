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
import javafx.stage.Popup;
import javafx.stage.PopupWindow.AnchorLocation;
import javafx.stage.Stage;
import models.FeeList;

/**
 * @author AGUILA, Norielle
 */

public class CollectionTab extends Tabs{
	
	private BorderPane layout;
	private VBox rightPane;
	private Button editBtn;
	private FeeList model;
	private ArrayList<MenuItem> menuItems;
	private ContextMenu menu;
	
	private AddCategory acdialog;
	private CollectionDialog cddialog;
	private EditItemDialog eidialog;
	private CategoryDeleteDialog cdddialog;
	private CategoryItemDeleteDialog ciddialog;
	private EditCategoryDialog ecdialog;
	
	private CollectionTable tables;
	
	private Stage window;
	
	public CollectionTab(FeeList model, Stage window){
		super();
		
		layout = new BorderPane();
		rightPane = new VBox();
		
		this.model = model;
		
		this.window = window;
		acdialog = new AddCategory();
		cddialog = new CollectionDialog();
		cdddialog=new CategoryDeleteDialog();
		ciddialog = new CategoryItemDeleteDialog();
		ecdialog= new EditCategoryDialog();
		eidialog = new EditItemDialog();
		initLayout();
	}
	
	public void initLayout(){
		initRightPane();
		
		tables = new CollectionTable(model);
		tables.setPrefHeight(View.HEIGHT);
		
		layout.setCenter(tables);
		layout.setRight(rightPane);
		
		layout.getStylesheets().add("style.css");
		
		this.getChildren().add(layout);
	}
	
	public void initMenuItems(){
		menuItems = new ArrayList<>();
		menuItems.add( new MenuItem("ADD COLLECTION ITEM"));
		menuItems.add(new MenuItem("ADD NEW CATEGORY"));
		menuItems.add(new MenuItem("EDIT ITEM"));
		menuItems.add(new MenuItem("EDIT CATEGORY"));
		menuItems.add(new MenuItem("DELETE COLLECTION ITEM"));
		menuItems.add(new MenuItem("DELETE CATEGORY"));
		
		for(MenuItem item: menuItems){
			item.setOnAction(e -> {
				executeSelection(menuItems.indexOf(item));
			});
		}
	}
	
	public void initMenu(){
		menu = new ContextMenu ();
		
		menu.getItems().addAll(menuItems);
		
		editBtn = new Button("+");
		editBtn.setId("collectionEditBtn");
		editBtn.setPrefSize(50, 50);
		
		editBtn.setContextMenu(menu);
		
		// put this in the controller instead of in here
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
			cddialog.updateCat();
			cddialog.show(window);
			cddialog.setX(200);
			cddialog.setY(200);
			break;
		case 1:
			acdialog.show(window);
			acdialog.setX(200);
			acdialog.setY(200);
			break;
		case 2:
			eidialog.updateCat();
			eidialog.show(window);
			eidialog.setX(200);
			eidialog.setY(200);
			break;
		case 3:
			ecdialog.updateCat();
			ecdialog.show(window);
			ecdialog.setX(200);
			ecdialog.setY(200);
			break;
		case 4:
			ciddialog.updateCat();
			ciddialog.show(window);
			ciddialog.setX(200);
			ciddialog.setY(200);
			break;
		case 5:
			cdddialog.updateCat();
			cdddialog.show(window);
			cdddialog.setX(200);
			cdddialog.setY(200);
			break;
		case 6:
			
			break;
		}
	}
	
	public ArrayList<MenuItem> getMenuItems(){
		return menuItems;
	}
	
	public AddCategory getACPopup(){
		return acdialog;
	}
	
	public CollectionDialog getCD(){
		return cddialog;
	}
	public CategoryDeleteDialog getCDD(){
		return cdddialog;
	}
	public EditCategoryDialog getECD(){
		return ecdialog;
	}
	public EditItemDialog getEID(){
		return eidialog;
	}
	public CategoryItemDeleteDialog getCIDD(){
		return ciddialog;
	}
	
	public void update(){
		tables.update();
	}
	
}
