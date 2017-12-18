package views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import models.Fee;
//import views.CollectionDialog.CollectionLayout;
//import views.CollectionDialog.OnAddEventHandler;

public class EditCategoryDialog extends Popup {
	private EditCategoryLayout ec;
	public EditCategoryDialog () {
		super ();
		initLayout ();
	}
	
	private void initLayout () {
		ec=new EditCategoryLayout ();
		getContent ().addAll (ec);
	}

	private OnAddEventHandler onAddEventHandler;
	
	public interface OnAddEventHandler {
		public void onAction (String category,String name );
	}
	
	public void setOnAddEventHandler (OnAddEventHandler onAddEventHandler) {
		this.onAddEventHandler = onAddEventHandler;
	}
	public void updateCat() {
		ec.updateCat();
	}
	
	/**
	 * Called when there is an {@code Error} in the input fields of the category dialog.
	 * Activates when the user presses the {@code Add} button.
	 * @param errorCode {@code int} value that corresponds to a certain key.
	 * 					<br>1 - Name text field is empty
	 * 					<br>2 - Cost is not a double value
	 * 					<br>3 - Category is not selected
	 */
	
	
	private class EditCategoryLayout extends Pane{
		
		private static final double WIDTH = 500;
		private static final double HEIGHT = 300;
		private static final double CHILD_PADDING = 20;
		private int multiplier = 1;
		private final double fieldWidth = 200;
		
			private Button exitBtn;
		

		private Label categoryLbl;
		private ComboBox <String> categoryCB;
		
		private Label nameLbl;
		private TextField nameTf;
			private Button edtBtn;
			

		
		public EditCategoryLayout () {
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
			initCategory ();
			initName ();
			initEdit ();
			
			//initErrorDisplay();
		}
		/*
		private void initErrorDisplay(){
			errorMessage = new SimpleStringProperty();
			errorLbl = new Label();

			errorMessage.set("");
			errorLbl.textProperty().bind(errorMessage);
			
			errorLbl.setLayoutX(CHILD_PADDING * 2 + fieldWidth);
			
			getChildren().add(errorLbl);
			
		}*/
		
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
		
		private void initCategory () {
			multiplier += 1;
			categoryLbl = new Label ("CATEGORY");
			categoryCB = new ComboBox<String> ();
			categoryCB.getItems().clear();
			categoryCB.getItems().addAll(Fee.FEETYPE);
			categoryCB.getSelectionModel().selectFirst();
			nameTf = new TextField ();
			categoryLbl.setLayoutX(CHILD_PADDING);
			categoryLbl.setLayoutY(CHILD_PADDING * multiplier);
			categoryLbl.setId("lblPopup");
			multiplier += 1;
			categoryCB.valueProperty().addListener(new ChangeListener<String>() {
		        @Override public void changed(ObservableValue ov, String t, String t1) {
		        	nameTf.setText(t1);;
		        	System.out.println(ov);
		            System.out.println(t);
		            System.out.println(t1);
		        }    
		    });
			categoryCB.setMinWidth(fieldWidth);
			categoryCB.setMaxWidth(fieldWidth);
			categoryCB.setLayoutX(CHILD_PADDING);
			categoryCB.setLayoutY(CHILD_PADDING * multiplier);
			categoryCB.setId("tfPopup");
			multiplier += 1;
			
			getChildren ().add (categoryLbl);
			getChildren ().add (categoryCB);
		}
		
		
		private void initName () {
			multiplier += 1;
			nameLbl = new Label ("Category Name");
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
		
		
		
		
		private void initEdit () {
			multiplier += 1;
			int width = 150, height = 25;
			edtBtn = new Button ("EDIT");
			
			edtBtn.setMaxSize(width, height);
			edtBtn.setMinSize(width, height);
			
			edtBtn.setLayoutX(WIDTH - width - CHILD_PADDING);
			edtBtn.setLayoutY(CHILD_PADDING * multiplier);
			edtBtn.setId("addPopup");
			
			
			edtBtn.setOnAction ((e) -> {
				if(!nameTf.getText().equals("")){
					onAddEventHandler.onAction(
							categoryCB.getSelectionModel ().getSelectedItem (),nameTf.getText());
				}
			});
			multiplier += 1;
			
			getChildren ().add (edtBtn);
		}
		
		public void updateCat(){
			for(Node child:getChildren()){
				if(child instanceof ComboBox){
					ComboBox<String> temp=((ComboBox)child);
					temp.getItems().clear();
					temp.getItems().clear();
					temp.getItems().addAll(Fee.FEETYPE);
					temp.getSelectionModel().selectFirst();
					
				}
			}
		}

		
		
	
		
	}
	
}