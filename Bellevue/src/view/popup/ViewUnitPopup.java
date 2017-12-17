package view.popup;

import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import model.CollectionModel;
import model.UnitModel.UnitContainer;
import model.beans.*;
import model.database.FeeHelper;
import model.database.IncurredFeeHelper;
import view.Size;
import view.UnitRow;

public class ViewUnitPopup extends Popup{
	private static final double WIDTH = 500,
								HEIGHT = 500;
	private static final double LBL_WIDTH = 100;
	private static final double CHILD_GAP = Size.CHILD_GAP;
	
	private BorderPane mainLayout;
	private Pane headerPane;
	private Pane collectionInfoPane;
	private HBox buttonContainerHBox;

	private TableView<CollectionModel.UnitFeeContainer> tableView;
	private CollectionModel collectionModel;
	
	private Button exitBtn;
	private Label unitNumLbl;
	private Label billNameLbl;
	private Label tctLbl;
	private Label lotAreaLbl;
	private Label addressLbl;
	private Label statusLbl;
	private Label totalLbl;
	private Label dueLbl;
	
	private Button editBtn;
	private Button deleteBtn;
	private Button payBtn;
	private Button saveBtn;
	private Button addBtn;
	private Button printBtn;
	
	private int numChildren = 0;
	private double headerHeight = 10;
	private Stage mainStage;
	
	private boolean changed;
	private UnitContainer unit;
	
	public ViewUnitPopup(UnitContainer unit, Stage mainStage){
		super();
		this.unit = unit;
		this.mainStage = mainStage;
		this.changed = false;
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
		initTable();
		initStatusLbl();
		initTotalLbl();
		initDueLbl();
	}
	
	private void initButtonContainer(){
		buttonContainerHBox = new HBox(CHILD_GAP);
		buttonContainerHBox.setAlignment(Pos.CENTER);
		buttonContainerHBox.setPadding(new Insets(10));
		
		initSaveBtn();
		initAddBtn();
		initPrintBtn();
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

	private void initTable(){
		numChildren++;
		
		tableView = new TableView<> ();
		collectionModel = new CollectionModel ();

		ObservableList<CollectionModel.UnitFeeContainer> unitFeeContainers = collectionModel.getUnitFee (unit.getUnit ().getUnitNo ());

		TableColumn<CollectionModel.UnitFeeContainer, String> feeName = new TableColumn<> (Fee.COL_FEE_NAME);
		TableColumn<CollectionModel.UnitFeeContainer, String > count = new TableColumn<> (IncurredFee.COL_COUNT);
		TableColumn<CollectionModel.UnitFeeContainer, String> total = new TableColumn<> (IncurredFee.COL_TOTAL);

		feeName.setCellValueFactory (new PropertyValueFactory<CollectionModel.UnitFeeContainer, String> (Fee.COL_FEE_NAME));
		count.setCellValueFactory (new PropertyValueFactory<CollectionModel.UnitFeeContainer, String > (IncurredFee.COL_COUNT));
		total.setCellValueFactory (new PropertyValueFactory<CollectionModel.UnitFeeContainer, String> (IncurredFee.COL_TOTAL));

		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView.getColumns ().add (feeName);
		tableView.getColumns ().add (count);
		tableView.getColumns ().add (total);

		tableView.setItems (unitFeeContainers);
		
		tableView.setLayoutX(CHILD_GAP);
		tableView.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		
		tableView.setPrefHeight(200);
		tableView.setPrefWidth(WIDTH - CHILD_GAP * 2);

		headerHeight += CHILD_GAP + (unitFeeContainers.size() + 1) * 50 + 3;
//		numChildren++;

		collectionInfoPane.getChildren ().add (tableView);
	}
	
	private void initStatusLbl(){
		numChildren++;
		
		statusLbl = new Label();
		
		statusLbl.setMaxSize(Size.BTN_PREF_HEIGHT, Size.BTN_PREF_HEIGHT);
		statusLbl.setMinSize(Size.BTN_PREF_HEIGHT, Size.BTN_PREF_HEIGHT);
		
		statusLbl.setLayoutX(CHILD_GAP * 2);
		statusLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		
		statusLbl.getStyleClass().add("statusLbl");
		
		// set to unpaid by default
		statusLbl.setId("unpaid" + UnitRow.ID_STATUS_LBL);
		
//		headerHeight += CHILD_GAP;
		
		collectionInfoPane.getChildren().add(statusLbl);
	}
	
	private void initTotalLbl(){
		Label unitInfo = new Label("Total: ");
		unitInfo.setLayoutX(LBL_WIDTH * 2);
		unitInfo.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		unitInfo.setId("lblBold");
		
		totalLbl = new Label();
		
		totalLbl.setText("1234.56");
		
		totalLbl.setLayoutX(LBL_WIDTH * 2 + 50);
		totalLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		
		headerHeight += CHILD_GAP;
		
		numChildren++;
		
		collectionInfoPane.getChildren().add(totalLbl);
		collectionInfoPane.getChildren().add(unitInfo);
	}
	
	private void initDueLbl(){
		Label unitInfo = new Label("Due: ");
		unitInfo.setLayoutX(LBL_WIDTH * 2);
		unitInfo.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		unitInfo.setId("lblBold");
		
		dueLbl = new Label();
		
		dueLbl.setText("1234.56");
		
		dueLbl.setLayoutX(LBL_WIDTH * 2 + 50);
		dueLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight + 5);
		
		dueLbl.setId("dueLbl");
		
		initPayBtn();
		
		headerHeight += CHILD_GAP;
		
		numChildren++;
		
		collectionInfoPane.getChildren().add(dueLbl);
		collectionInfoPane.getChildren().add(unitInfo);
	}
	
	private void initPayBtn(){
		payBtn = new Button();
		payBtn.setMinSize(25, 25);
		payBtn.setLayoutX(LBL_WIDTH * 3 + 15);
		payBtn.setLayoutY(CHILD_GAP * numChildren + headerHeight);
		
		payBtn.setId("payBtn");
		
		collectionInfoPane.getChildren().add(payBtn);
	}
	
	private void initSaveBtn(){
		saveBtn = new Button();
		saveBtn.setText("SAVE");
		saveBtn.setMinSize(Size.BTN_PREF_WIDTH, Size.BTN_PREF_HEIGHT);
		
		saveBtn.getStyleClass().add("btn");
		
		buttonContainerHBox.getChildren().add(saveBtn);
	}
	
	private void initAddBtn(){
		addBtn = new Button();
		addBtn.setText("ADD EXPENSES");
		addBtn.setMinSize(Size.BTN_PREF_WIDTH, Size.BTN_PREF_HEIGHT);
		addBtn.getStyleClass().add("btn");

		addBtn.setOnAction (event -> {
			AddExpenses p = new AddExpenses ();
			p.show (mainStage);
			p.setX (250);
			p.setY (200);
			
			p.setOnAddExpenseListener((String name, int count)->{
				FeeHelper feeHelper = new FeeHelper();
				Fee fee = feeHelper.getFee(name);
				IncurredFee infee = new IncurredFee();
				infee.setCount(count);
				infee.setFeeID(fee.getFeeID());
				infee.setUnitNo(unit.getUnit().getUnitNo());
				infee.setDate(java.time.LocalDateTime.now().toString().split("T")[0]);
				infee.setTotal(feeHelper.getFee(fee.getFeeID()).getFeePrice());
				infee.setPayment(0);
				
				IncurredFeeHelper ifh = new IncurredFeeHelper();
				System.out.println(ifh.addIncurredFee(infee));
				
				collectionInfoPane.getChildren().remove(tableView);
				collectionInfoPane.getChildren ().add (tableView);
				
			});
		});

		buttonContainerHBox.getChildren().add(addBtn);
	}
	
	private void initPrintBtn(){
		printBtn = new Button();
		printBtn.setText("PRINT");
		printBtn.setMinSize(Size.BTN_PREF_WIDTH, Size.BTN_PREF_HEIGHT);
		printBtn.getStyleClass().add("btn");
		buttonContainerHBox.getChildren().add(printBtn);
	}
}
