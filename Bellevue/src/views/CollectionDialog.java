 package views;

import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class CollectionDialog extends Popup {
	
	public CollectionDialog () {
		super ();
		
		initLayout ();
	}
	
	private void initLayout () {
		getContent ().addAll (new CollectionLayout ());
	}

	private OnAddEventHandler onAddEventHandler;
	
	public interface OnAddEventHandler {
		public void onAction (String name, String cost, String category);
	}
	
	public void setOnAddEventHandler (OnAddEventHandler onAddEventHandler) {
		this.onAddEventHandler = onAddEventHandler;
	}
	
	private class CollectionLayout extends Pane {
		
		private static final double WIDTH = 500;
		private static final double HEIGHT = 300;
		private static final double CHILD_PADDING = 20;
		private int multiplier = 1;
		private final double fieldWidth = 200;
		
			private Button exitBtn;
		
		private Label nameLbl;
		private TextField nameTf;
		
		private Label costLbl;
		private TextField costTf;

		private Label categoryLbl;
		private ComboBox <String> categoryCB;
			private Button addBtn;
		
		public CollectionLayout () {
			super ();
			
			setAutoHide(true);
			
			init ();
		}
		
		private void init () {
			setMinSize (WIDTH, HEIGHT);
			setMaxSize(WIDTH, HEIGHT);
			getStylesheets().add("style.css");
			setId("popup");
			initExit ();
			initName ();
			initCost ();
			initCategory ();
			initAdd ();
		}
		
		private void initExit () {
			exitBtn = new Button ("X");
			
			exitBtn.setId("closePopup");
			
			exitBtn.setOnAction ((e) -> {
				hide ();
			});
			
			exitBtn.setLayoutX(WIDTH - CHILD_PADDING - 50);
			exitBtn.setLayoutY(CHILD_PADDING);
			exitBtn.setId("closePopup");
			
			getChildren ().add (exitBtn);
			
			multiplier += 1;
		}
		
		private void initName () {
			multiplier += 1;
			nameLbl = new Label ("NAME OF ITEM");
			nameTf = new TextField ();
			
			nameLbl.setLayoutX(CHILD_PADDING);
			nameLbl.setLayoutY(CHILD_PADDING * multiplier);
			nameLbl.setId("lblPopup");
			multiplier += 1;
			
			nameTf.setMinWidth(fieldWidth);
			nameTf.setMaxWidth(fieldWidth);
			nameTf.setLayoutX(CHILD_PADDING);
			nameTf.setLayoutY(CHILD_PADDING * multiplier);
			nameTf.setId("tfPopup");
			multiplier += 1;
			
			getChildren ().add (nameLbl);
			getChildren ().add (nameTf);
		}
		
		private void initCost () {
			multiplier += 1;
			
			costLbl = new Label ("EXPENSE COST");
			costTf = new TextField ();
			
			costLbl.setLayoutX(CHILD_PADDING);
			costLbl.setLayoutY(CHILD_PADDING * multiplier);
			costLbl.setId("lblPopup");
			multiplier += 1;
			
			costTf.setMinWidth(fieldWidth);
			costTf.setMaxWidth(fieldWidth);
			costTf.setLayoutX(CHILD_PADDING);
			costTf.setLayoutY(CHILD_PADDING * multiplier);
			costTf.setId("tfPopup");
			multiplier += 1;
			
			getChildren ().add (costLbl);
			getChildren ().add (costTf);
			
		}
		
		private void initCategory () {
			multiplier += 1;
			categoryLbl = new Label ("CATEGORY");
			categoryCB = new ComboBox<> ();
			
			categoryLbl.setLayoutX(CHILD_PADDING);
			categoryLbl.setLayoutY(CHILD_PADDING * multiplier);
			categoryLbl.setId("lblPopup");
			multiplier += 1;
			
			categoryCB.setMinWidth(fieldWidth);
			categoryCB.setMaxWidth(fieldWidth);
			categoryCB.setLayoutX(CHILD_PADDING);
			categoryCB.setLayoutY(CHILD_PADDING * multiplier);
			categoryCB.setId("tfPopup");
			multiplier += 1;
			
			getChildren ().add (categoryLbl);
			getChildren ().add (categoryCB);
		}
		
		private void initAdd () {
			multiplier += 1;
			int width = 150, height = 25;
			addBtn = new Button ("ADD");
			
			addBtn.setOnAction ((e) -> {
				onAddEventHandler.onAction (nameTf.getText (), costTf.getText (), categoryCB.getSelectionModel ().getSelectedItem ());
			});
			
			addBtn.setMaxSize(width, height);
			addBtn.setMinSize(width, height);
			
			addBtn.setLayoutX(WIDTH - width - CHILD_PADDING);
			addBtn.setLayoutY(CHILD_PADDING * multiplier);
			addBtn.setId("addPopup");
			multiplier += 1;
			
			getChildren ().add (addBtn);
		}
		
	}
	
}

