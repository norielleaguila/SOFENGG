package views;

import java.util.ArrayList;

import DB.DBaccess;
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

public class EditItemDialog extends Popup {
	private EditItemDialogLayout ec;
	public EditItemDialog () {
		super ();
		initLayout ();
	}
	
	private void initLayout () {
		ec=new EditItemDialogLayout ();
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
	
	
	private class EditItemDialogLayout extends Pane{
		
		private static final double WIDTH = 500;
		private static final double HEIGHT = 500;
		private static final double CHILD_PADDING = 20;
		private int multiplier = 1;
		private final double fieldWidth = 200;
		
		private Button exitBtn;
		
		private Label categoryLbl;
		private ComboBox <String> categoryCB;
		private Label itemLbl;
		private ComboBox <String> itemCB;
		
		private Label nameLbl;
		private TextField nameTf;
		private Label priceLbl;
		private TextField priceTf;
		
		private Button edtBtn;
			

		
		public EditItemDialogLayout () {
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
			initItem();
			initName ();
			initPrice();
			initEdit ();
			initCBListener();
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
			categoryCB = new ComboBox<> ();
			categoryCB.getSelectionModel().selectFirst();
			categoryCB.setId("catCB");
			nameTf = new TextField ();
			priceTf = new TextField ();
			categoryLbl.setLayoutX(CHILD_PADDING);
			categoryLbl.setLayoutY(CHILD_PADDING * multiplier);
			categoryLbl.setId("lblPopup");
			multiplier += 1;
			/*categoryCB.valueProperty().addListener(new ChangeListener<String>() {
		        @Override public void changed(ObservableValue ov, String t, String t1) {
		        	nameTf.setText(t1);;
		        	System.out.println(ov);
		            System.out.println(t);
		            System.out.println(t1);
		        }    
		    });*/
			categoryCB.setMinWidth(fieldWidth);
			categoryCB.setMaxWidth(fieldWidth);
			categoryCB.setLayoutX(CHILD_PADDING);
			categoryCB.setLayoutY(CHILD_PADDING * multiplier);
			multiplier += 1;
			
			getChildren ().add (categoryLbl);
			getChildren ().add (categoryCB);
		}
		
		private void initItem () {
			multiplier += 1;
			itemLbl = new Label ("ITEM");
			itemCB = new ComboBox<String> ();
			itemCB.getSelectionModel().selectFirst();
			itemCB.setId("itemCB");
			nameTf = new TextField ();
			itemLbl.setLayoutX(CHILD_PADDING);
			itemLbl.setLayoutY(CHILD_PADDING * multiplier);
			itemLbl.setId("lblPopup");
			multiplier += 1;
			itemCB.valueProperty().addListener(new ChangeListener<String>() {
		        @Override public void changed(ObservableValue ov, String t, String t1) {
		        	nameTf.setText(t1);
		        	for(Fee fee: DBaccess.getFees()){
		        		if(fee.getFeeName().equals(t1)){
		        			priceTf.setText(Double.toString(fee.getPrice()));
		        		}
		        	}
		        	System.out.println(ov);
		            System.out.println(t);
		            System.out.println(t1);
		        }    
		    });
			itemCB.setMinWidth(fieldWidth);
			itemCB.setMaxWidth(fieldWidth);
			itemCB.setLayoutX(CHILD_PADDING);
			itemCB.setLayoutY(CHILD_PADDING * multiplier);
			multiplier += 1;
			
			getChildren ().add (itemLbl);
			getChildren ().add (itemCB);
		}
		private void initName () {
			multiplier += 1;
			nameLbl = new Label ("Item Name");
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
		private void initPrice () {
			multiplier += 1;
			priceLbl = new Label ("Price");
			priceLbl.setLayoutX(CHILD_PADDING);
			priceLbl.setLayoutY(CHILD_PADDING * multiplier);
			priceLbl.setId("lblPopup");
			multiplier += 1;
			
			priceTf.setMinWidth(fieldWidth);
			priceTf.setMaxWidth(fieldWidth);
			priceTf.setLayoutX(CHILD_PADDING);
			priceTf.setLayoutY(CHILD_PADDING * multiplier);
			priceTf.setId("tfPopup");
			multiplier += 1;
			
			getChildren ().add (priceLbl);
			getChildren ().add (priceTf);
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
				onAddEventHandler.onAction(
						itemCB.getSelectionModel ().getSelectedItem (),nameTf.getText());
			});
			multiplier += 1;
			
			getChildren ().add (edtBtn);
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
					System.out.println(child.getId());
					if(child.getId().equals("catCB")){
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
	
}