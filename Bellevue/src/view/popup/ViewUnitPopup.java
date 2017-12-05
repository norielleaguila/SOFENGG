package view.popup;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class ViewUnitPopup extends Popup{
	
	private static final double CHILD_GAP = 10;
	private static final double WIDTH = 500,
								HEIGHT = 500;
	
	private BorderPane mainLayout;
	private Pane headerPane;
	private Pane collectionInfoPane;
	private HBox buttonContainerHBox;
	
	private Button exitBtn;
	private Label unitNumLbl;
	private Label billNameLbl;
	private Label tctLbl;
	private Label lotAreaLbl;
	
	private int numChildren = 0;
	private double headerHeight = 0;
	
	public ViewUnitPopup(){
		super();
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
		
		mainLayout.getStylesheets().add("stylesheets/style.css");
		mainLayout.getStylesheets().add("stylesheets/popupStylesheet.css");
	}
	
	private void initHeader(){
		headerPane = new Pane();
		
		headerPane.setId("popupHeader");
		
		headerPane.setMinHeight(CHILD_GAP * 2 + 15);
		
		initExitBtn();
		initUnitNumLbl();
	}
	
	private void initCollectionInfo(){
		collectionInfoPane = new Pane();
		
		initBillNameLbl();
		initTCTLbl();
		initLotAreaLbl();
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
		
		unitNumLbl.setText("Unit 1088a");
		
		unitNumLbl.setLayoutX(CHILD_GAP);
		unitNumLbl.setLayoutY(CHILD_GAP);
		
		unitNumLbl.setId("unitNumLbl");
		
		headerPane.getChildren().add(unitNumLbl);
	}

	private void initBillNameLbl(){
		billNameLbl = new Label();
		
		billNameLbl.setText("Bill To: ");
		
		billNameLbl.setLayoutX(CHILD_GAP);
		billNameLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight);
		
		headerHeight += CHILD_GAP;
		
		numChildren++;
		
		collectionInfoPane.getChildren().add(billNameLbl);
	}
	
	private void initTCTLbl(){
		tctLbl = new Label();
		
		tctLbl.setText("TCT: ");
		
		tctLbl.setLayoutX(CHILD_GAP);
		tctLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight);
		
		headerHeight += CHILD_GAP;
		
		numChildren++;
		
		collectionInfoPane.getChildren().add(tctLbl);
	}
	
	private void initLotAreaLbl(){
		lotAreaLbl = new Label();
		
		lotAreaLbl.setText("Lot Area: ");
		
		lotAreaLbl.setLayoutX(CHILD_GAP);
		lotAreaLbl.setLayoutY(CHILD_GAP * numChildren + headerHeight);
		
		headerHeight += CHILD_GAP;
		
		numChildren++;
		
		collectionInfoPane.getChildren().add(lotAreaLbl);
		
	}
}
