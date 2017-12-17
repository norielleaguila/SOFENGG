package views;

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

public class CategoryDeleteDialog  extends Popup {
	private CategoryDeleteDialogLayout cddl;
	public CategoryDeleteDialog () {
		super ();
		setAutoHide(true);
		initLayout ();
	}
	
	private void initLayout () {
		cddl=new CategoryDeleteDialogLayout ();
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
	
	private class CategoryDeleteDialogLayout extends Pane {
		
		private static final double WIDTH = 500;
		private static final double HEIGHT = 200;
		private static final double CHILD_PADDING = 20;
		
		private Button exitBtn;
		private Label categoryLbl;
		private ComboBox <String> categoryCB;
		private Button deleteBtn;
		
		public CategoryDeleteDialogLayout () {
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
			initDelete ();
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
		
		private void initDelete () {
			deleteBtn = new Button ("Add");
			
			double addHeight = 25;
			double addWidth = 150;
			
			deleteBtn.setOnAction ((e) -> {
				onAddEventHandler.onAction (categoryCB.getSelectionModel ().getSelectedItem ());
			});
			
			deleteBtn.setMinSize(addWidth, addHeight);
			deleteBtn.setMaxSize(addWidth, addHeight);
			
			deleteBtn.setLayoutX(WIDTH - CHILD_PADDING - addWidth);
			deleteBtn.setLayoutY(HEIGHT - CHILD_PADDING - addHeight);
			
			deleteBtn.setId("addPopup");
			
			getChildren ().addAll (deleteBtn);
		}
		public void updateCat(){
			for(Node child:getChildren()){
				if(child instanceof ComboBox){
					ComboBox<String> temp=((ComboBox)child);
					temp.getItems().clear();
					temp.getItems().addAll(Fee.FEETYPE);
				}
			}
		}
	}
}
