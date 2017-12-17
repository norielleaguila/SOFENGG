package view.popup;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import model.UnitModel.UnitContainer;
import model.beans.Unit;
import model.database.StreetHelper;

public class ViewUnitPopup extends Popup{
	
	private static final double CHILD_GAP = 10;
	private static final double WIDTH = 500,
								HEIGHT = 500;
	private static final double LBL_WIDTH = 100;
	
	private BorderPane mainLayout;
	private Pane headerPane;
	private Pane collectionInfoPane;
	private HBox buttonContainerHBox;
	
	private Button exitBtn;
	private Label unitNumLbl;
	private Label billNameLbl;
	private Label tctLbl;
	private Label lotAreaLbl;
	private Label addressLbl;
	
	private int numChildren = 0;
	private double headerHeight = 0;
	
	private UnitContainer unit;
	
	public ViewUnitPopup(UnitContainer unit){
		super();
		
		this.unit = unit;
		
		init();
		
		setAutoHide(true);
		
		getContent().addAll(mainLayout);
	}
	
	private void init(){
		initHeader();
		initCollectionInfo();
		initButtonContainer();
		
		mainLayout = new BorderPane();
		
		mainLayout.setTop(headerPane);
		mainLayout.setCenter(collectionInfoPane);
		mainLayout.setBottom(buttonContainerHBox);
		
		mainLayout.setMinSize(WIDTH, HEIGHT);
		mainLayout.setMaxSize(WIDTH, HEIGHT);
		mainLayout.setId("popup");
		
		mainLayout.getStylesheets().add("style/style.css");
		mainLayout.getStylesheets().add("style/popupStylesheet.css");
	}
	
	private void initHeader(){
		headerPane = new Pane();
		
		headerPane.setId("popupHeader");
		
		headerPane.setMinHeight(CHILD_GAP * 2 + 15);
		
		initExitBtn();
	}
	
	private void initCollectionInfo(){
		collectionInfoPane = new Pane();

		initUnitNumLbl();
		initBillNameLbl();
		initTCTLbl();
		initLotAreaLbl();
		initAddressLbl();
	}
	
	private void initButtonContainer(){
		buttonContainerHBox = new HBox(CHILD_GAP);
	}
	
	private void initExitBtn(){
		double width = 15, height = 15;
		
		exitBtn = new Button();
		exitBtn.setMinSize(width, height);
		exitBtn.setMaxSize(width, height);
		
		exitBtn.setId("exitBtn");
		
		exitBtn.setLayoutX(WIDTH - CHILD_GAP - width);
		exitBtn.setLayoutY(CHILD_GAP);
		
		exitBtn.setOnAction(e -> hide());
		
		headerPane.getChildren().add(exitBtn);
	}
	
	private void initUnitNumLbl(){
		unitNumLbl = new Label();
		
		unitNumLbl.setText("Unit " + unit.getUnit().getUnitNo());
		
		unitNumLbl.setLayoutX(CHILD_GAP);
		unitNumLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight);
		
		headerHeight += CHILD_GAP;
		
		numChildren++;
		
		unitNumLbl.setId("unitNumLbl");
		
		collectionInfoPane.getChildren().add(unitNumLbl);
	}

	private void initBillNameLbl(){
		billNameLbl = new Label();
		
		billNameLbl.setText("Bill To: ");
		
		billNameLbl.setLayoutX(CHILD_GAP);
		billNameLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);

		billNameLbl.setId("lblBold");
		
		Label unitInfo = new Label("" + unit.getBillingInfo().getBilledTo());
		unitInfo.setLayoutX(LBL_WIDTH);
		unitInfo.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		
		headerHeight += CHILD_GAP;
		
		numChildren++;
		
		collectionInfoPane.getChildren().add(billNameLbl);
		collectionInfoPane.getChildren().add(unitInfo);
	}
	
	private void initTCTLbl(){
		tctLbl = new Label();
		
		tctLbl.setText("TCT: ");
		
		tctLbl.setLayoutX(CHILD_GAP);
		tctLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);

		tctLbl.setId("lblBold");
		
		Label unitInfo = new Label("" + unit.getBillingInfo().getTCT());
		unitInfo.setLayoutX(LBL_WIDTH);
		unitInfo.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		
		headerHeight += CHILD_GAP;
		
		numChildren++;
		
		collectionInfoPane.getChildren().add(tctLbl);
		collectionInfoPane.getChildren().add(unitInfo);
	}
	
	private void initLotAreaLbl(){
		lotAreaLbl = new Label();
		
		lotAreaLbl.setText("Lot Area: ");
		
		lotAreaLbl.setLayoutX(CHILD_GAP);
		lotAreaLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);

		lotAreaLbl.setId("lblBold");
		
		Label unitInfo = new Label(""  + unit.getUnit().getLotArea() + " sqm");
		unitInfo.setLayoutX(LBL_WIDTH);
		unitInfo.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		
		headerHeight += CHILD_GAP;
		
		numChildren++;
		
		collectionInfoPane.getChildren().add(lotAreaLbl);
		collectionInfoPane.getChildren().add(unitInfo);
		
	}
	
	private void initAddressLbl(){
		addressLbl = new Label();
		
		addressLbl.setText("Address: ");
		
		addressLbl.setLayoutX(CHILD_GAP);
		addressLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		
		addressLbl.setId("lblBold");
		
		Label unitInfo = new Label("" + unit.getAddress().getAddressNo() + " " + 
				unit.getStreet().getStreetName());
		unitInfo.setLayoutX(LBL_WIDTH);
		unitInfo.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		
		headerHeight += CHILD_GAP;
		
		numChildren++;
		
		collectionInfoPane.getChildren().add(addressLbl);
		collectionInfoPane.getChildren().add(unitInfo);
	}
}
