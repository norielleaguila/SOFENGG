package views;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import models.FeeList;
//import views.AddExpense.ExpenseLayout;
//import views.AddExpense.OnAddEventHandler;
import views.UnitCollection.displayval;

public class EditExpense extends Popup{
	private ExpenseLayout el;
	public EditExpense (ArrayList<displayval> nonSel,String Fee,int noi) {
		super ();
		initLayout (nonSel,Fee,noi);
	}
	private void initLayout (ArrayList<displayval> nonSel,String Fee,int noi) {
		el=new ExpenseLayout(nonSel,Fee,noi);
		getContent ().addAll (el);
	}

	private OnAddEventHandler onAddEventHandler;
	
	public interface OnAddEventHandler {
		public void onAction (String FeeName, String times);
	}
	
	public void setOnAddEventHandler (OnAddEventHandler onAddEventHandler) {
		this.onAddEventHandler = onAddEventHandler;
	}
	
	public void update(){
		el.update();
	}
	
	public void updateCat() {
		el.updateCat();
	}
	
	/**
	 * Called when there is an {@code Error} in the input fields of the category dialog.
	 * Activates when the user presses the {@code Add} button.
	 * @param errorCode {@code int} value that corresponds to a certain key.
	 * 					<br>1 - Name text field is empty
	 * 					<br>2 - Cost is not a double value
	 * 					<br>3 - Category is not selected
	 */
	public void error(int errorCode){
		el.displayError(errorCode);
	}
	
	private class ExpenseLayout extends Pane implements ViewInterface{
		
		private static final double WIDTH = 500;
		private static final double HEIGHT = 300;
		private static final double CHILD_PADDING = 20;
		private int multiplier = 1;
		private final double fieldWidth = 200;
		
			private Button exitBtn;
		
		//private Label nameLbl;
		//private TextField nameTf;
		
		private Label timesLbl;
		private TextField timesTf;

		private Label FeeLbl;
		private ComboBox <String> FeeCB;
			private Button editBtn;
		private ArrayList<displayval> nondisp;
		private String defval;
			
			
		private	StringProperty errorMessage;
		private	Label errorLbl;
		
		public ExpenseLayout (ArrayList<displayval> nonSel,String Fee,int noi) {
			super ();
			setAutoHide(true);
			nondisp=nonSel;
			defval=Fee;
			init (noi);
		}
		
		private void init (int noi) {
			setMinSize (WIDTH, HEIGHT);
			setMaxSize(WIDTH, HEIGHT);
			getStylesheets().add("style.css");
			setId("popup");
			initExit ();
			initName ();
			//initCost ();
			//initCategory ();
			initTimes(noi);
			initEdit();
			initErrorDisplay();
		}
		
		private void initErrorDisplay(){
			errorMessage = new SimpleStringProperty();
			errorLbl = new Label();

			errorMessage.set("");
			errorLbl.textProperty().bind(errorMessage);
			
			errorLbl.setLayoutX(CHILD_PADDING * 2 + fieldWidth);
			
			getChildren().add(errorLbl);
			
		}
		
		private void initExit () {
			exitBtn = new Button ("X");
			
			exitBtn.setId("closePopup");
			
			exitBtn.setOnAction ((e) -> {
				reset();
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
			FeeLbl = new Label ("Fee Name");
			FeeCB = new ComboBox<> ();
			
			FeeLbl.setLayoutX(CHILD_PADDING);
			FeeLbl.setLayoutY(CHILD_PADDING * multiplier);
			FeeLbl.setId("lblPopup");
			multiplier += 1;
			
			FeeCB.setMinWidth(fieldWidth);
			FeeCB.setMaxWidth(fieldWidth);
			FeeCB.setLayoutX(CHILD_PADDING);
			FeeCB.setLayoutY(CHILD_PADDING * multiplier);
			
			FeeCB.setId("tfPopup");
			multiplier += 1;
			
			getChildren ().add (FeeLbl);
			getChildren ().add (FeeCB);
		}
		/*
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
		*/
		private void initTimes (int noi) {
			multiplier += 1;
			timesLbl = new Label ("No of times");
			timesTf = new TextField (noi+"");
			
			timesLbl.setLayoutX(CHILD_PADDING);
			timesLbl.setLayoutY(CHILD_PADDING * multiplier);
			timesLbl.setId("lblPopup");
			multiplier += 1;
			
			timesTf.setMinWidth(fieldWidth);
			timesTf.setMaxWidth(fieldWidth);
			timesTf.setLayoutX(CHILD_PADDING);
			timesTf.setLayoutY(CHILD_PADDING * multiplier);
			timesTf.setId("tfPopup");
			multiplier += 1;
			
			getChildren ().add (timesLbl);
			getChildren ().add (timesTf);
		}
		
		private void initEdit () {
			multiplier += 1;
			int width = 150, height = 25;
			editBtn = new Button ("EDIT");
			
			editBtn.setOnAction ((e) -> {
				onAddEventHandler.onAction (
						FeeCB.getSelectionModel ().getSelectedItem (), 
						timesTf.getText());
			});
			
			editBtn.setMaxSize(width, height);
			editBtn.setMinSize(width, height);
			
			editBtn.setLayoutX(WIDTH - width - CHILD_PADDING);
			editBtn.setLayoutY(CHILD_PADDING * multiplier);
			editBtn.setId("editPopup");
			multiplier += 1;
			
			getChildren ().add (editBtn);
		}
		
		public void updateCat(){
			for(Node child:getChildren()){
				if(child instanceof ComboBox){
					ComboBox<String> temp=((ComboBox)child);
					temp.getItems().clear();
					ArrayList<String> feetemp=FeeList.getStringList();
					for(int i=0;i<nondisp.size();i++){
						if((!nondisp.get(i).getName().equals(defval))&&feetemp.contains(nondisp.get(i).getName()))
							feetemp.remove(searchIndex(feetemp,nondisp.get(i).getName()));
					}
					temp.getItems().addAll(feetemp);
					temp.getSelectionModel().select(defval);
				}
			}
		}
		private int searchIndex(ArrayList<String> feetemp,String name){
			int val=-1;
			for(int i=0;i<feetemp.size();i++){
				if(feetemp.get(i).equals(name)){
					val=i;
					break;
				}
			}
			return val;
		}
		
		public void displayError(int errorCode){
			errorLbl.setId("cd_error");
			switch(errorCode){
			case 1: // did not enter a name
				errorMessage.set("Please do not leave name field blank.");
				errorLbl.setLayoutY(timesTf.getLayoutY());
				break;
			case 2:	// NumberFormatException, did not enter a proper Double inside costTf
				errorMessage.set("Please enter a number.");
				errorLbl.setLayoutY(timesTf.getLayoutY());
				break;
			case 3:	// Did not select a category
				errorMessage.set("Please select a category.");
				errorLbl.setLayoutY(FeeCB.getLayoutY());
				break;
			default:
			}
		}
		
		public void reset(){
			timesTf.setText("");
			FeeCB.setValue(null);
			errorMessage.set("");
			errorLbl.setId("cd_error_hidden");
		}

		@Override
		public void update() {
			reset();
		}
		
	}
}
