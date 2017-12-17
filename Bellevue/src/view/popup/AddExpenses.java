package view.popup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import model.*;
import model.beans.*;
import model.database.FeeHelper;
import view.Size;

import java.util.*;

public class AddExpenses extends Popup{
	private static final double WIDTH = 500,
			HEIGHT = 300;
	private static final double LBL_WIDTH = 100;
	private static final double CHILD_GAP = 10;
	
	private double square = 30;

	private BorderPane mainLayoutBP;
	private Pane headerPane;
	private VBox controlVBox;
	private HBox btnContainerHBox;
	private HBox feeHBox;
	private HBox qtyHBox;
	
	private Label titleLbl;
	private Label feeNameLbl;
	private Label qtyLbl;
	
	private TextField qtyTF;
	private ComboBox<String> feeNameCB;
	
	private Button exitBtn;
	private Button addQtyBtn;
	private Button minusQtyBtn;
	private Button cancelBtn;
	private Button addBtn;
	
	private OnAddExpenseListener onAddExpenseListener;
	
	public interface OnAddExpenseListener{
		public void onAction(String feeName, int count);
	}
	
	public AddExpenses(){
		super();
		init();
	}
	
	public void init(){
		mainLayoutBP = new BorderPane();
		mainLayoutBP.getStylesheets().add("style/popupStylesheet.css");
		mainLayoutBP.setMaxSize(WIDTH, HEIGHT);

		mainLayoutBP.setId("popup");
		
		initHeader();
		initControl();
		initBtnContainer();
		
		setAutoHide(true);
		
		getContent().addAll(mainLayoutBP);
	}
	
	private void initHeader(){
		headerPane = new Pane();
		
		headerPane.setId("popupHeader");
		
		headerPane.setMinHeight(Size.CHILD_GAP * 2 + 15);
		
		initExitBtn();
		
		mainLayoutBP.setTop(headerPane);
	}
	
	private void initExitBtn(){
		double width = 15, height = 15;
		
		exitBtn = new Button();
		exitBtn.setMinSize(width, height);
		exitBtn.setMaxSize(width, height);
		
		exitBtn.setId("exitBtn");
		
		exitBtn.setLayoutX(325);
		exitBtn.setLayoutY(CHILD_GAP);
		
		exitBtn.setOnAction(e -> hide());
		
		headerPane.getChildren().add(exitBtn);
	}
	
	private void initControl(){
		controlVBox = new VBox(10);
		controlVBox.getStyleClass().add("container");
		
		initTitleLbl();
		initFeeHBox();
		initQtyHBox();
		
		mainLayoutBP.setCenter(controlVBox);
	}
	
	private void initBtnContainer(){
		btnContainerHBox = new HBox(10);
		btnContainerHBox.setAlignment(Pos.CENTER_RIGHT);
		
		btnContainerHBox.getStyleClass().add("container");
		
		initCancelBtn();
		initAddBtn();
		
		mainLayoutBP.setBottom(btnContainerHBox);
	}
	
	private void initTitleLbl(){
		titleLbl = new Label("Add Expenses");
		titleLbl.setId("unitNumLbl");
		
		controlVBox.getChildren().add(titleLbl);
	}
	
	private void initFeeHBox(){
		feeHBox = new HBox(15);
		feeHBox.setAlignment(Pos.CENTER_LEFT);
		
		initFeeNameLbl();
		initFeeNameCB();
		
		controlVBox.getChildren().add(feeHBox);
	}
	
	private void initFeeNameLbl(){
		feeNameLbl = new Label("Fee Name:");
		
		feeHBox.getChildren().add(feeNameLbl);
	}
	
	private void initFeeNameCB(){
		feeNameCB = new ComboBox<>();
		
		feeNameCB.setMinSize(Size.LBL_PREF_WIDTH, Size.LBL_PREF_HEIGHT);

		CollectionModel collectionModel = new CollectionModel ();
		List<Fee> fees = collectionModel.getAllFees ();

		for (Fee fee: fees)
			feeNameCB.getItems ().add (fee.getFeeName ());

		
		feeHBox.getChildren().add(feeNameCB);
	}
	
	private void initQtyHBox(){
		qtyHBox = new HBox(10);
		qtyHBox.setAlignment(Pos.CENTER_LEFT);
		
		initQtyLbl();
		initMinusQtyBtn();
		initQtyTF();
		initAddQtyBtn();
		
		controlVBox.getChildren().add(qtyHBox);
	}
	
	private void initQtyLbl(){
		qtyLbl = new Label("Quantity:");
		
		qtyHBox.getChildren().add(qtyLbl);
	}
	
	private void initMinusQtyBtn(){
		minusQtyBtn = new Button("-");
		minusQtyBtn.setMaxSize(square, square);
		minusQtyBtn.setMinSize(square, square);
		
		minusQtyBtn.setOnAction(e -> {
			if(qtyTF != null && Integer.parseInt(qtyTF.getText())- 1 >= 0){
				qtyTF.setText(Integer.parseInt(qtyTF.getText())- 1 + "");
			}
				
		});
		
		minusQtyBtn.setId("qtyBtn");
		
		qtyHBox.getChildren().add(minusQtyBtn);
	}
	
	private void initQtyTF(){
		qtyTF = new TextField();
		qtyTF.setMaxSize(square + 10, square);
		qtyTF.setMinSize(square + 10, square);
		
		qtyTF.setAlignment(Pos.CENTER);
		
		qtyTF.setText("0");
		
		qtyTF.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	qtyTF.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		qtyHBox.getChildren().add(qtyTF);
	}
	
	private void initAddQtyBtn(){
		addQtyBtn = new Button("+");
		addQtyBtn.setMaxSize(square, square);
		addQtyBtn.setMinSize(square, square);
		
		addQtyBtn.setOnAction(e -> {
			if(qtyTF != null)
				qtyTF.setText(Integer.parseInt(qtyTF.getText()) + 1 + "");
		});
		
		addQtyBtn.setId("qtyBtn");
		
		qtyHBox.getChildren().add(addQtyBtn);
	}
	
	private void initCancelBtn(){
		cancelBtn = new Button("CANCEL");
		cancelBtn.setMinSize(Size.BTN_PREF_WIDTH, Size.BTN_PREF_HEIGHT);
		cancelBtn.getStyleClass().add("btn");
	
		cancelBtn.setOnAction(e->{
			hide();
		});
		
		btnContainerHBox.getChildren().add(cancelBtn);
	}
	
	private void initAddBtn(){
		addBtn = new Button("ADD");
		addBtn.setMinSize(Size.BTN_PREF_WIDTH, Size.BTN_PREF_HEIGHT);
		
		addBtn.getStyleClass().add("btn");
		
		addBtn.setOnAction(e -> {
			if(onAddExpenseListener != null)
				onAddExpenseListener.onAction(feeNameCB.getValue(), Integer.parseInt(qtyTF.getText()));
			hide();
		});
		
		btnContainerHBox.getChildren().add(addBtn);
	}

	public void setOnAddExpenseListener(OnAddExpenseListener oae){
		this.onAddExpenseListener = oae;
	}
}
