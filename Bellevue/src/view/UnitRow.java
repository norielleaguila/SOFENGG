package view;

import java.time.LocalDateTime;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import model.CollectionModel;
import model.CollectionModel.CollectionContainer;
import view.UnitsTable.OnActionListener;
import view.popup.ViewUnitPopup;

public class UnitRow extends HBox implements View{
	public final static String ID_STATUS_LBL = "_StatusLbl";
	
	private Label statusLbl;
	private Label unitNumLbl;
	private Button viewBtn;
	private Button printBtn;
	
	private String unitNum;
	
	private CollectionModel collectionModel;
	
	public OnActionListener onActionListener;
	
	public UnitRow(){
		super();
		init();
	}
	
	public UnitRow(String unitNum){
		super();
		
		this.unitNum = unitNum;
		this.collectionModel = new CollectionModel();
		
		init();
	}
	
	public void init(){
		
		getStylesheets().add("style/unitTableStylesheet.css");
		
		setSpacing(Size.TABLE_SPACING);
		setAlignment(Pos.CENTER);
		
		initStatusLbl();
		initUnitNumLbl();
		initViewBtn();
		initPrintBtn();
		
		setId("unitRow");
	}
	
	public void initStatusLbl(){
		statusLbl = new Label();
		
		statusLbl.getStyleClass().add("statusLbl");
		statusLbl.getStyleClass().add("unitRowLbl");
		
		statusLbl.setMaxSize(30, 30);
		statusLbl.setMinSize(30, 30);

//		String[] now = java.time.LocalDateTime.now().toString().split("T")[0].split("-");
//		
//		CollectionContainer cc = collectionModel.getCollection(unitNum, Integer.parseInt(now[2]), now[0] + "-1");
		
		setStatus(2);	// all units are unpaid by default
		
//		if(cc.getMonthlyCollection().getDatePaid() != null){
//			setStatus(1);
//		}
		
		getChildren().add(statusLbl);
	}
	
	public void initUnitNumLbl(){
		unitNumLbl = new Label();
		
		unitNumLbl.setText(unitNum);
		unitNumLbl.setMaxSize(Size.LBL_PREF_WIDTH, Size.LBL_PREF_HEIGHT);
		unitNumLbl.setPrefSize(Size.LBL_PREF_WIDTH, Size.LBL_PREF_HEIGHT);
		unitNumLbl.setMinHeight(Size.LBL_PREF_HEIGHT);
		
		unitNumLbl.setId("unitNumLbl");
		
		getChildren().add(unitNumLbl);
	}
	
	public void initViewBtn(){
		viewBtn = new Button();
		
		viewBtn.setText("VIEW");
		viewBtn.setMaxSize(Size.BTN_PREF_WIDTH, Size.BTN_PREF_HEIGHT);
		viewBtn.setMinSize(Size.BTN_PREF_WIDTH, Size.BTN_PREF_HEIGHT);
		
		viewBtn.setId("viewBtn");
		
		viewBtn.setOnAction(e -> {
			if(onActionListener != null)
				onActionListener.onAction(1, unitNum);
		});
		
		getChildren().add(viewBtn);
	}
	
	public void initPrintBtn(){
		printBtn = new Button();
		
		printBtn.setText("PRINT");
		printBtn.setMaxSize(Size.BTN_PREF_WIDTH, Size.BTN_PREF_HEIGHT);
		printBtn.setMinSize(Size.BTN_PREF_WIDTH, Size.BTN_PREF_HEIGHT);
		printBtn.setTextAlignment(TextAlignment.CENTER);
		
		printBtn.setId("printBtn");
		
		printBtn.setOnAction(e -> {
			if(onActionListener != null)
				onActionListener.onAction(2, unitNum);
		});
		
		getChildren().add(printBtn);
	}
	
	public void setStatus(int status){
		switch(status){
		case 1:
			statusLbl.setId("paid" + ID_STATUS_LBL);
			break;
		case 2:
			statusLbl.setId("unpaid" + ID_STATUS_LBL);
			break;
		case 3:
			statusLbl.setId("overdue" + ID_STATUS_LBL);
			break;
		}
	}
	
	public void setOnActionListener(OnActionListener onActionListener){
		this.onActionListener = onActionListener;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
