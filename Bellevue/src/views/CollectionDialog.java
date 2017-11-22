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
	
	private class CollectionLayout extends VBox {
		
		private HBox hboxExit;
			private Button exit;
		
		private Label lblName;
		private TextField tfName;
		
		private Label lblCost;
		private TextField tfCost;

		private Label lblCategory;
		private ComboBox <String> cbCategory;

		private HBox hboxAdd;
			private Button btnAdd;
		
		public CollectionLayout () {
			super (10);
			
			init ();
		}
		
		private void init () {
			setMinSize (1000, 400);
			setStyle ("-fx-background-color: #aaa");
			initExit ();
			initName ();
			initCost ();
			initCategory ();
			initAdd ();
		}
		
		private void initExit () {
			hboxExit = new HBox ();
			hboxExit.setAlignment (Pos.TOP_RIGHT);
			
			exit = new Button ("X");
			
			exit.setOnAction ((e) -> {
				hide ();
			});
			
			hboxExit.getChildren ().add (exit);
			getChildren ().add (hboxExit);
		}
		
		private void initName () {
			lblName = new Label ("NAME OF ITEM");
			tfName = new TextField ();
			
			getChildren ().add (lblName);
			getChildren ().add (tfName);
		}
		
		private void initCost () {
			lblCost = new Label ("EXPENSE COST");
			tfCost = new TextField ();
			
			getChildren ().add (lblCost);
			getChildren ().add (tfCost);
		}
		
		private void initCategory () {
			lblCategory = new Label ("CATEGORY");
			cbCategory = new ComboBox<> ();
			
			getChildren ().add (lblCategory);
			getChildren ().add (cbCategory);
		}
		
		private void initAdd () {
			hboxAdd = new HBox ();
			btnAdd = new Button ("ADD");
			
			btnAdd.setOnAction ((e) -> {
				onAddEventHandler.onAction (tfName.getText (), tfCost.getText (), cbCategory.getSelectionModel ().getSelectedItem ());
			});
			
			hboxAdd.getChildren ().add (btnAdd);
			getChildren ().add (hboxAdd);
		}
		
	}
	
}

