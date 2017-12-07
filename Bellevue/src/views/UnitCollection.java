package views;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Collection;
import models.CollectionList;
import models.FeeIncurred;
import models.FeeList;
import models.Unit;

public class UnitCollection extends View{
	private CollectionList collectionModel;
	private Unit unit;
	private UnitRow row;
	private int accountType;
	private Stage window;
	private UnitTab view;
	
	private Label unitnumLabel, ownerLabel, ownerLabel2, tctLabel, tctLabel2, lotsizeLabel, lotsizeLabel2, addressLabel, addressLabel2, totalfeeLabel, totalfeeLabel2;
	private AnchorPane unitPane;
	private Popup unitPopup;
	private Collection collection;
	private ArrayList<FeeIncurred> addedFee;
	private ArrayList<displayval> displayList;
	private Button close;
	private Region headerDivider;
	private HBox unitHeader;
	private VBox ownerlotsizeVBox;
	private VBox ownerlotsizeVBox2;
	private VBox totalfeeVBox;
	private VBox rightSide;
	private VBox unitContent;
	private TableView unitTable;
	private TableColumn itemCol;
	private TableColumn noCol;
	private TableColumn feeCol;
	private ObservableList<displayval> itemsList;
	private HBox bottomContainer;
	private Region region1;
	private VBox buttonsVBox;
	private HBox paidHBox;
	private Label paidLabel;
	private ToggleGroup paidToggle;
	private RadioButton paidRadio;
	private RadioButton unpaidRadio;
	private HBox buttonsHBox;
	private Button saveButton;
	private Button addButton;
	private Button printButton;
	private Button deleteButton;
	private VBox unitVBox;
	private VBox tablePane;
	
	public UnitCollection(CollectionList collectionModel, Unit unit, UnitRow row, int accountType, Stage window, UnitTab view){
		this.collectionModel = collectionModel;
		this.unit = unit;
		this.row = row;
		this.accountType = accountType;
		this.window = window;
		this.view = view;
		
		initLayout();
	}

	@Override
	protected void initLayout() {
		// TODO Auto-generated method stub
		unitPane = new AnchorPane();
		unitPopup = new Popup();
		createElements();
		addToLayout();
		unitPopup.show(window);
	}

	@Override
	protected void createElements() {
		// TODO Auto-generated method stub
		collection = collectionModel.getUnit(unit.getUnitNo());
		addedFee = new ArrayList<FeeIncurred>();
		displayList = new ArrayList<displayval>();
		
		unitnumLabel = new Label("UNIT#" + unit.getUnitNo());
		unitnumLabel.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:white;-fx-padding: 5px;-fx-border-insets:5px;-fx-background-insets: 5px;");
		
		ownerLabel = new Label("Owner: ");
		ownerLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
		
		tctLabel = new Label("TCT: ");
		tctLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
		
		lotsizeLabel = new Label("Lot size: ");
		lotsizeLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
		
		addressLabel = new Label("Address");
		addressLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
		
		ownerLabel2 = new Label(unit.getBilledTo());
		ownerLabel2.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:white;");
		
		tctLabel2 = new Label(unit.accessTCT() + " sqm");
		tctLabel2.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:white;");
		
		lotsizeLabel2 = new Label(String.valueOf(unit.accessLotArea()));
		lotsizeLabel2.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:white;");
		
		addressLabel2 = new Label(unit.getAddressNo() + " " + unit.getStreet());
		addressLabel2.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:white;");
		
		totalfeeLabel = new Label("Total Fee");
		totalfeeLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
		
		totalfeeLabel2 = new Label(collection.getTotal()+"");
		totalfeeLabel2.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:white;");
		
		close = new Button("X");
		close.getStylesheets().add("style.css");
		
		close.setId("closePopup");
		
		headerDivider = new Region();
		HBox.setHgrow(headerDivider, Priority.ALWAYS);
		
		unitHeader = new HBox();
		ownerlotsizeVBox = new VBox();
		ownerlotsizeVBox2 = new VBox();
		totalfeeVBox = new VBox();
		
		if(accountType == 1)
			ownerlotsizeVBox.getChildren().addAll(ownerLabel, tctLabel,lotsizeLabel,addressLabel);
		else ownerlotsizeVBox.getChildren().addAll(ownerLabel, addressLabel);
		ownerlotsizeVBox.setPrefHeight(20);
		ownerlotsizeVBox.setAlignment(Pos.BOTTOM_LEFT);
		
		if(accountType == 1)
			ownerlotsizeVBox2.getChildren().addAll(ownerLabel2, tctLabel2,lotsizeLabel2, addressLabel2);
		else ownerlotsizeVBox2.getChildren().addAll(ownerLabel2, addressLabel2);
		ownerlotsizeVBox2.setPrefHeight(20);
		ownerlotsizeVBox2.setAlignment(Pos.BOTTOM_LEFT);
		
		close.setAlignment(Pos.TOP_RIGHT);
		
		totalfeeVBox.getChildren().addAll(totalfeeLabel2,totalfeeLabel);
		totalfeeVBox.setAlignment(Pos.CENTER);
		totalfeeVBox.setStyle("-fx-padding: 5px;-fx-border-insets:5px;-fx-background-insets: 5px;");
		
		rightSide = new VBox();
		rightSide.getChildren().addAll(close, totalfeeVBox);
		rightSide.setAlignment(Pos.TOP_RIGHT);
		
		unitHeader.getChildren().addAll(unitnumLabel,ownerlotsizeVBox,ownerlotsizeVBox2,headerDivider,rightSide);
		unitHeader.setStyle("-fx-background-color: #AA6B5A;-fx-padding:10px;");
		unitHeader.setPrefWidth(1250);
		unitHeader.setPrefHeight(100);
		unitHeader.setAlignment(Pos.BOTTOM_LEFT);
		unitPane.getChildren().addAll(unitHeader);
		
		unitContent = new VBox();
		unitContent.setPrefWidth(1250);
		unitContent.setPrefHeight(600);
		unitContent.setStyle("-fx-background-color:white;");
		unitTable = new TableView();
		tablePane = new VBox();
		unitTable.setEditable(true);
		
		unitTable.getStylesheets().add("style.css");
		
		itemCol = new TableColumn("ITEM");
		noCol = new TableColumn("No.");
		feeCol = new TableColumn("FEE");

		itemCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		noCol.setCellValueFactory(new PropertyValueFactory<>("times"));
		feeCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		unitTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		itemCol.prefWidthProperty().bind(unitTable.widthProperty().multiply(0.6));
		feeCol.prefWidthProperty().bind(unitTable.widthProperty().multiply(0.3));
		noCol.prefWidthProperty().bind(unitTable.widthProperty().multiply(0.1));
		itemCol.minWidthProperty().bind(unitTable.widthProperty().multiply(0.6));
		feeCol.minWidthProperty().bind(unitTable.widthProperty().multiply(0.3));
		noCol.minWidthProperty().bind(unitTable.widthProperty().multiply(0.1));
		
		//itemCol.setCellValueFactory(new PropertyValueFactory <String, String>("temp"));
		
		for(FeeIncurred fee:collection.getAllFee()){
			displayList.add(new displayval(fee.getName(),fee.getTimes(),fee.getPrice()));
		}
		//ObservableList<FeeIncurred> itemsList = FXCollections.observableArrayList(collection.getAllFee());
		itemsList = FXCollections.observableArrayList(displayList);
		//sStringProperty temp = new SimpleStringProperty();
		//temp.set("Monthly Fee");
		//String temp2 = "Monthly Fee";
		//itemsList.add(temp2);
		
		unitTable.getColumns().addAll(itemCol, noCol,feeCol);
		
		itemCol.getStyleClass().add("popupTable");
		feeCol.getStyleClass().add("popupTable");
		
		tablePane.setPrefWidth(650);
		tablePane.setMinWidth(650);
		tablePane.setMaxWidth(650);
		unitTable.setPrefWidth(650);
		unitTable.setMinWidth(650);
		unitTable.setMaxWidth(650);
		tablePane.setStyle("-fx-background-color: #AA6B5A;");
		unitTable.setItems(itemsList);
		tablePane.getChildren().add(unitTable);
		
		bottomContainer = new HBox();
		region1 = new Region();
	    HBox.setHgrow(region1, Priority.ALWAYS);

		buttonsVBox = new VBox();
		paidHBox = new HBox();
		paidLabel = new Label("PAID");
		paidLabel.setStyle("-fx-font:bold 50px 'Segoe UI';-fx-text-fill:#618E21;");
		paidToggle = new ToggleGroup();
		paidRadio = new RadioButton("Paid");
		unpaidRadio = new RadioButton("Unpaid");
		paidRadio.setToggleGroup(paidToggle);
		unpaidRadio.setToggleGroup(paidToggle);
		
		//paidHBox.getChildren().addAll(paidLabel,paidRadio,unpaidRadio);
		
		if(collectionModel.getUnit(unit.getUnitNo()).isPaid()){
			paidLabel.setText("PAID");
			paidLabel.setStyle("-fx-font:bold 50px 'Segoe UI';-fx-text-fill:#618E21;");
			row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#3F561E");
		}
		else if(collectionModel.getUnit(unit.getUnitNo()).isOverdue()){
			paidLabel.setText("OVERDUE");
			paidLabel.setStyle("-fx-font:bold 50px 'Segoe UI';-fx-text-fill:#F95959;");
			row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#FF0606");
		}
		else{
			paidLabel.setText("UNPAID");
			paidLabel.setStyle("-fx-font:bold 50px 'Segoe UI';-fx-text-fill:#ABAEAF;");	
			row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#95989A");
		}
		
		paidHBox.getChildren().addAll(paidLabel,paidRadio,unpaidRadio);
		
		paidHBox.setAlignment(Pos.CENTER_LEFT);
		paidHBox.setSpacing(10);
		
		buttonsHBox = new HBox();
		saveButton = new Button("Save Changes");
		addButton = new Button("Add Expenses");
		printButton = new Button("Print Bill");
		deleteButton = new Button("Delete Expense");
		
		saveButton.setDisable(true);
		saveButton.setPrefWidth(200);
		saveButton.setPrefHeight(20);
		
		addButton.setPrefWidth(200);
		
		printButton.setPrefWidth(200);
		
		deleteButton.setDisable(true);
		
		saveButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		addButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#F95959;-fx-text-fill:white;");
		printButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#F95959;-fx-text-fill:white;");
		deleteButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		
		unpaidRadio.setOnAction(e -> {
			saveButton.setDisable(false);
	        saveButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
	        
	        deleteButton.setDisable(true);
			deleteButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		});
		
		paidRadio.setOnAction(e -> {
			saveButton.setDisable(false);
	        saveButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
	        
	        deleteButton.setDisable(true);
			deleteButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		});
		
		unitTable.setOnMouseClicked(event -> {
			if(unitTable.getSelectionModel().getSelectedItem() != null){
				deleteButton.setDisable(false);
				deleteButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
			}
		});
		unitTable.setOnMouseReleased(event -> {
			deleteButton.setDisable(true);
			deleteButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		});
		
		if(accountType != 1)
			unpaidRadio.setDisable(true);
		
		if(collectionModel.getUnit(unit.getUnitNo()).isPaid())
			paidToggle.selectToggle(paidRadio);
		else
			paidToggle.selectToggle(unpaidRadio);
		
		deleteButton.setOnAction(e ->{
			
		});
		addButton.setOnAction(e ->{
			System.out.println("add button pushed");
			AddExpense ae = new AddExpense();
			ae.updateCat();
			ae.show(window);
			ae.setX(200);
			ae.setY(200);
			
			ae.setOnAddEventHandler(new views.AddExpense.OnAddEventHandler(){

				@Override
				public void onAction(String FeeName, String times) {
					System.out.println("add button actioned");
					boolean costNaN = false;
					int num=0;
					try{num=Integer.parseInt(times);}
					catch(NumberFormatException e){costNaN = true;}
					
					if(times == "")
						ae.error(1);
					else if(costNaN)
						ae.error(2);
					else if(FeeName == null)
						ae.error(3);
					else{
					
						//collection.getCollectionID();
						saveButton.setDisable(false);
						saveButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
						FeeIncurred feeincurred= new FeeIncurred(FeeList.getFee(FeeName),num,
								java.time.LocalDateTime.now().toString().split("T")[0],
								collection.getCollectionID(),collection.getUnitNo()); 
						addedFee.add(feeincurred);
						itemsList.removeAll(itemsList);
						
						boolean found=false;
						for(displayval temp:displayList){
							if(temp.getName()==feeincurred.getName()){
								found=true;
								temp.addTimes(feeincurred.getTimes());
							}
						}
						if(!found)
							displayList.add(new displayval(feeincurred.getName(),feeincurred.getTimes(),feeincurred.getPrice()));
						itemsList.addAll(displayList);
						//collection.addFee(feeincurred);
						//DBaccess.addFeeIncurred(feeincurred);
						//Fee f=new Fee(name, category, Double.parseDouble(cost));
						//model.addFee(f);
						//DBaccess.(f);
						ae.hide();
						ae.update();
						view.update();
					}
				}
			});
			
			
		});
		saveButton.setOnAction(e -> {
			collection.addFee(addedFee);	
			if(paidRadio.isSelected()){
				collectionModel.getUnit(unit.getUnitNo()).setDatePaid(java.time.LocalDateTime.now().toString().split("T")[0]);
				unit.setPaid(true);
				paidLabel.setText("PAID");
				paidLabel.setStyle("-fx-font:bold 50px 'Segoe UI';-fx-text-fill:#618E21;");
				row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#3F561E");
				DB.DBaccess.changeStatus(collectionModel.getUnit(unit.getUnitNo()));
			}
			else{
				if(accountType == 1){
					collectionModel.getUnit(unit.getUnitNo()).setDatePaid(null);
					unit.setPaid(false);
					paidLabel.setText("UNPAID");
					paidLabel.setStyle("-fx-font:bold 50px 'Segoe UI';-fx-text-fill:#ABAEAF;");
					
					DB.DBaccess.changeStatus(collectionModel.getUnit(unit.getUnitNo()));
					
					if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) > 15){
						row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#FF0606");
						unit.setOverdue(true);
					}
						
					else
						row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#95989A");
				}
			}
//			view.update();
		});
		buttonsHBox.getChildren().addAll(deleteButton,saveButton,addButton,printButton);
		buttonsHBox.setSpacing(10);
		buttonsVBox.getChildren().addAll(paidHBox,buttonsHBox);
		bottomContainer.getChildren().addAll(region1,buttonsVBox);
		unitContent.getChildren().addAll(unitTable, bottomContainer);
		
		unitVBox = new VBox();
		//unitVBox.getChildren().addAll(unitHeader,unitContent);
		//unitPopup.getContent().addAll(unitVBox);
		System.out.println("check");
		//unitPopup.show(window);
		unitPopup.setAutoHide(true);
		
		unitVBox.getStylesheets().add("style.css");
		unitVBox.setId("popup");
		
		close.setOnAction(e -> {
			unitPopup.hide();
			row.update();
		});
	}

	@Override
	protected void addToLayout() {
		// TODO Auto-generated method stub
		unitVBox.getChildren().addAll(unitHeader,unitContent);
		unitPopup.getContent().addAll(unitVBox);
		
	}

	@Override
	public void resetLayout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public class displayval{
		private Double price;
		private String name;
		private int times;
		public displayval(String name,int times,Double price){
			this.name=name;
			this.times=times;
			this.price=price;
		}
		public Double getPrice() {
			return price*times;
		}
		public String getName() {
			return name;
		}
		public void addTimes(int times){
			this.times+=times;
		}
		public int getTimes() {
			return times;
		}
	}
}
