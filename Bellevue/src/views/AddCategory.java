package views;

import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;

public class AddCategory extends Popup {
	
	public AddCategory () {
		super ();
		
		setAutoHide(true);
		
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
	
	private class AddCategoryLayout extends Pane {
		
		private static final double WIDTH = 500;
		private static final double HEIGHT = 200;
		private static final double CHILD_PADDING = 20;
		
		private Button exitBtn;
		private Label categoryLbl;
		private TextField categoryTf;
		private Button addBtn;
		
		public AddCategoryLayout () {
			super ();
			
			init ();
		}
		
		private void init () {
			setMaxSize(WIDTH, HEIGHT);
			setMinSize(WIDTH, HEIGHT);
			getStylesheets().add("style.css");
			setId("acLayout");
			initExit ();
			initCategory ();
			initAdd ();
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
			categoryTf = new TextField ();
			
			categoryLbl.setLayoutY(CHILD_PADDING * 2 + 25);
			categoryLbl.setLayoutX(CHILD_PADDING);
			categoryLbl.setId("lblPopup");
			
			categoryTf.setId("tfPopup");
			categoryTf.setLayoutX(CHILD_PADDING);
			categoryTf.setLayoutY(CHILD_PADDING * 3 + 25);
			categoryTf.setMinWidth(200);
			categoryTf.setMaxWidth(200);
			
			getChildren ().addAll (categoryLbl, categoryTf);
		}
		
		private void initAdd () {
			addBtn = new Button ("Add");
			
			double addHeight = 25;
			double addWidth = 150;
			
			addBtn.setOnAction ((e) -> {
				onAddEventHandler.onAction (categoryTf.getText ());
			});
			
			addBtn.setMinSize(addWidth, addHeight);
			addBtn.setMaxSize(addWidth, addHeight);
			
			addBtn.setLayoutX(WIDTH - CHILD_PADDING - addWidth);
			addBtn.setLayoutY(HEIGHT - CHILD_PADDING - addHeight);
			
			addBtn.setId("addPopup");
			
			getChildren ().addAll (addBtn);
		}
	}
	
	
}