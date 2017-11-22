package views;

import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.control.*;

public class AddCategory extends Popup {
	
	public AddCategory () {
		super ();
		
		initLayout ();
	}
	
	private void initLayout () {
		getContent ().addAll (new AddCategoryLayout ());
	}
	
	private OnAddEventHandler onAddEventHandler;
	
	public interface OnAddEventHandler {
		public void onAction (String category);
	}
	
	public void setOnAddEventHandler (OnAddEventHandler onAddEventHandler) {
		this.onAddEventHandler = onAddEventHandler;
	}
	
	private class AddCategoryLayout extends VBox {
		
		private Button btnExit;
		private Label lblCategory;
		private TextField tfCategory;
		private Button btnAdd;
		
		public AddCategoryLayout () {
			super ();
			
			init ();
		}
		
		private void init () {
			setMinSize (1000, 400);
			setStyle ("-fx-background-color: #aaa");
			initExit ();
			initCategory ();
			initAdd ();
		}
		
		private void initExit () {
			btnExit = new Button ("X");
			
			btnExit.setOnAction ((e) -> hide ());
			
			getChildren ().add (btnExit);
		}
		
		private void initCategory () {
			lblCategory = new Label ("CATEGORY");
			tfCategory = new TextField ();
			
			getChildren ().addAll (lblCategory, tfCategory);
		}
		
		private void initAdd () {
			btnAdd = new Button ("ADD");
			
			btnAdd.setOnAction ((e) -> {
				onAddEventHandler.onAction (tfCategory.getText ());
			});
			
			getChildren ().addAll (btnAdd);
		}
	}
	
	
}