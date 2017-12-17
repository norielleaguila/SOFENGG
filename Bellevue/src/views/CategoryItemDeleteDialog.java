package views;

import java.util.ArrayList;

import DB.DBaccess;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
//import views.AddCategory.AddCategoryLayout;
//import views.AddCategory.OnAddEventHandler;
import models.Fee;

public class CategoryItemDeleteDialog  extends Popup {
	private CategoryDeleteItemDialogLayout cddl;
	public CategoryItemDeleteDialog () {
		super ();
		setAutoHide(true);
		initLayout ();
	}
	
	private void initLayout () {
		cddl=new CategoryDeleteItemDialogLayout ();
		getContent ().addAll (cddl);
	}
	
	private OnAddEventHandler onAddEventHandler;
	
	public interface OnAddEventHandler {
		public void onAction (String category);
	}
	
	public void setOnAddEventHandler (OnAddEventHandler onAddEventHandler) {
		this.onAddEventHandler = onAddEventHandler;
	}
	public void updateCat() {
		cddl.updateCat();
	}
	
	private class CategoryDeleteItemDialogLayout extends Pane {
		
		private static final double WIDTH = 500;
		private static final double HEIGHT = 200;
		private static final double CHILD_PADDING = 20;
		
		private Button exitBtn;
		private Label categoryLbl;
		private ComboBox <String> categoryCB;
		private Label itemLbl;
		private ComboBox <String> itemCB;
		private Button deleteBtn;
		
		public CategoryDeleteItemDialogLayout () {
			super ();
			
			init ();
		}
		
		private void init () {
			setMaxSize(WIDTH, HEIGHT);
			setMinSize(WIDTH, HEIGHT);
			getStylesheets().add("style.css");
			setId("popup");
			initExit ();
			initCategory ();
			initItem();
			initDelete ();
			initCBListener();
		}
		
		private void initExit () {
			exitBtn = new Button ("X");
			
			exitBtn.setMaxWidth(25);
			exitBtn.setMaxHeight(25);
			
			exitBtn.setOnAction ((e) -> hide ());
			
			exitBtn.setId("closePopup");
			
			exitBtn.setLayoutX(WIDTH - CHILD_PADDING - 50);
			exitBtn.setLayoutY(CHILD_PADDING);
			
			getChildren ().add (exitBtn);
		}
		
		private void initCategory () {
			categoryLbl = new Label ("NAME OF CATEGORY");
			categoryCB = new ComboBox<> ();
			
			categoryLbl.setLayoutY(CHILD_PADDING * 2 + 25);
			categoryLbl.setLayoutX(CHILD_PADDING);
			categoryLbl.setId("lblPopup");
			
			categoryCB.setId("tfPopup");
			categoryCB.setLayoutX(CHILD_PADDING);
			categoryCB.setLayoutY(CHILD_PADDING * 3 + 25);
			
			
			getChildren ().addAll (categoryLbl, categoryCB);
		}
		
		private void initItem () {
			itemLbl = new Label ("NAME OF ITEM");
			itemCB = new ComboBox<> ();
			
			itemLbl.setLayoutY(CHILD_PADDING * 4 + 25);
			itemLbl.setLayoutX(CHILD_PADDING);
			itemLbl.setId("lblPopup");
			
			itemCB.setId("itemPopup");
			itemCB.setLayoutX(CHILD_PADDING);
			itemCB.setLayoutY(CHILD_PADDING * 5 + 25);
			
			
			getChildren ().addAll (itemLbl, itemCB);
		}
		
		private void initDelete () {
			deleteBtn = new Button ("Add");
			
			double addHeight = 25;
			double addWidth = 150;
			
			deleteBtn.setOnAction ((e) -> {
				onAddEventHandler.onAction (itemCB.getSelectionModel ().getSelectedItem ());
			});
			
			deleteBtn.setMinSize(addWidth, addHeight);
			deleteBtn.setMaxSize(addWidth, addHeight);
			
			deleteBtn.setLayoutX(WIDTH - CHILD_PADDING - addWidth);
			deleteBtn.setLayoutY(HEIGHT - CHILD_PADDING - addHeight);
			
			deleteBtn.setId("addPopup");
			
			getChildren ().addAll (deleteBtn);
		}
		private void initCBListener(){
			categoryCB.valueProperty().addListener((obs,oldValue,newValue)->{
				
				if(newValue!=null){
					System.out.println(newValue);
					ArrayList<Fee> fees = DBaccess.getFees();
					System.out.println(fees.get(0).getFeeName());
					itemCB.getItems().clear();
					for(Fee fee:fees){
						if(fee.getType().equals(newValue)){
						itemCB.getItems().add(fee.getFeeName());
						}
					}
				}
			});
		}
		public void updateCat(){
			for(Node child:getChildren()){
				if(child instanceof ComboBox){
					if(child.getId().equals("tfPopup")){
						ComboBox<String> temp=((ComboBox)child);
						temp.getItems().clear();
						temp.getItems().addAll(Fee.FEETYPE);
					}
				}
			}
		}
	}
}
