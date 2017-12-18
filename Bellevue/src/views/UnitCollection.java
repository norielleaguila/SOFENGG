package views;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import models.Fee;
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
	private ArrayList<Integer> sequence;
	private ArrayList<FeeIncurred> deletedFee;
	private ArrayList<FeeIncurred> preEditFee;
	private ArrayList<FeeIncurred> postEditFee;
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
	private Button editButton;
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
		deletedFee=new ArrayList<FeeIncurred>();
		preEditFee=new ArrayList<FeeIncurred>();
		postEditFee=new ArrayList<FeeIncurred>();
		sequence=new ArrayList<Integer>();
		displayList = new ArrayList<displayval>();
		
		unitnumLabel = new Label("UNIT#" + unit.getUnitNo());
		unitnumLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:white;-fx-padding: 5px;-fx-border-insets:5px;-fx-background-insets: 5px;");
		
		ownerLabel = new Label("Owner: ");
		ownerLabel.setStyle("-fx-font:bold 15px 'Segoe UI';-fx-text-fill:#F5C58F;");
		
		tctLabel = new Label("TCT: ");
		tctLabel.setStyle("-fx-font:bold 15px 'Segoe UI';-fx-text-fill:#F5C58F;");
		
		lotsizeLabel = new Label("Lot size: ");
		lotsizeLabel.setStyle("-fx-font:bold 15px 'Segoe UI';-fx-text-fill:#F5C58F;");
		
		addressLabel = new Label("Address");
		addressLabel.setStyle("-fx-font:bold 15px 'Segoe UI';-fx-text-fill:#F5C58F;");
		
		ownerLabel2 = new Label(unit.getBilledTo());
		ownerLabel2.setStyle("-fx-font:bold 15px 'Segoe UI';-fx-text-fill:white;");
		
		tctLabel2 = new Label(unit.accessTCT() + " sqm");
		tctLabel2.setStyle("-fx-font:bold 15px 'Segoe UI';-fx-text-fill:white;");
		
		lotsizeLabel2 = new Label(String.valueOf(unit.accessLotArea()));
		lotsizeLabel2.setStyle("-fx-font:bold 15px 'Segoe UI';-fx-text-fill:white;");
		
		addressLabel2 = new Label(unit.getAddressNo() + " " + unit.getStreet());
		addressLabel2.setStyle("-fx-font:bold 15px 'Segoe UI';-fx-text-fill:white;");
		
		totalfeeLabel = new Label("Total Fee");
		totalfeeLabel.setStyle("-fx-font:bold 15px 'Segoe UI';-fx-text-fill:#F5C58F;");
		
		totalfeeLabel2 = new Label(collection.getTotal()+"");
		totalfeeLabel2.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:white;");
		
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
		unitHeader.setPrefWidth(700);
		unitHeader.setPrefHeight(100);
		unitHeader.setAlignment(Pos.BOTTOM_LEFT);
		unitPane.getChildren().addAll(unitHeader);
		
		unitContent = new VBox();
		unitContent.setPrefWidth(700);
		unitContent.setPrefHeight(550);
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
		
		tablePane.setPrefWidth(700);
		tablePane.setMinWidth(700);
		tablePane.setMaxWidth(700);
		unitTable.setPrefWidth(700);
		unitTable.setMinWidth(700);
		unitTable.setMaxWidth(700);
		tablePane.setStyle("-fx-background-color: #AA6B5A;");
		unitTable.setItems(itemsList);
		tablePane.getChildren().add(unitTable);
		
		bottomContainer = new HBox();
		region1 = new Region();
	    HBox.setHgrow(region1, Priority.ALWAYS);

		buttonsVBox = new VBox();
		paidHBox = new HBox();
		paidLabel = new Label("PAID");
		paidLabel.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:#618E21;");
		paidToggle = new ToggleGroup();
		paidRadio = new RadioButton("Paid");
		unpaidRadio = new RadioButton("Unpaid");
		paidRadio.setToggleGroup(paidToggle);
		unpaidRadio.setToggleGroup(paidToggle);
		
		//paidHBox.getChildren().addAll(paidLabel,paidRadio,unpaidRadio);
		
		if(collectionModel.getUnit(unit.getUnitNo()).isPaid()){
			paidLabel.setText("PAID");
			paidLabel.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:#618E21;");
			row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#3F561E");
		}
		else if(collectionModel.getUnit(unit.getUnitNo()).isOverdue()){
			paidLabel.setText("OVERDUE");
			paidLabel.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:#F95959;");
			row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#FF0606");
		}
		else{
			paidLabel.setText("UNPAID");
			paidLabel.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:#ABAEAF;");	
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
		editButton=new Button("Edit Expense");
		saveButton.setDisable(true);
		saveButton.setPrefWidth(150);
		saveButton.setPrefHeight(20);
		
		addButton.setPrefWidth(150);
		
		printButton.setPrefWidth(100);
		
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		
		saveButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		addButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#F95959;-fx-text-fill:white;");
		printButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#F95959;-fx-text-fill:white;");
		deleteButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		editButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		
		unpaidRadio.setOnAction(e -> {
			saveButton.setDisable(false);
	        saveButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
	        
	        editButton.setDisable(true);
	        editButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
	        
	        deleteButton.setDisable(true);
			deleteButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		});
		
		paidRadio.setOnAction(e -> {
			saveButton.setDisable(false);
	        saveButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
	        
	        editButton.setDisable(true);
	        editButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
	        
	        deleteButton.setDisable(true);
			deleteButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		});
		
		unitTable.setOnMouseClicked(event -> {
			if(unitTable.getSelectionModel().getSelectedItem() != null){
				
				editButton.setDisable(false);
		        editButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
				
				deleteButton.setDisable(false);
				deleteButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
			}
		});
		unitTable.setOnMouseReleased(event -> {
			editButton.setDisable(true);
	        editButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
			
			deleteButton.setDisable(true);
			deleteButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		});
		
		if(accountType != 1)
			unpaidRadio.setDisable(true);
		
		if(collectionModel.getUnit(unit.getUnitNo()).isPaid())
			paidToggle.selectToggle(paidRadio);
		else
			paidToggle.selectToggle(unpaidRadio);
		editButton.setOnAction(e->{
			displayval temp=(displayval) unitTable.getSelectionModel().getSelectedItem();
			EditExpense ee = new EditExpense(displayList,temp.getName(),temp.getTimes());
			ee.updateCat();
			ee.show(window);
			ee.setX(200);
			ee.setY(200);
			
			ee.setOnAddEventHandler(new views.EditExpense.OnAddEventHandler(){

				@Override
				public void onAction(String FeeName, String times) {
					boolean costNaN = false;
					int num=0;
					try{num=Integer.parseInt(times);}
					catch(NumberFormatException e){costNaN = true;}
					
					if(times == "")
						ee.error(1);
					else if(costNaN)
						ee.error(2);
					else if(FeeName == null)
						ee.error(3);
					else{
						itemsList.removeAll(itemsList);
						FeeIncurred prev=null;
						for(FeeIncurred fee:collection.getAllFee()){
							if(fee.getName().equals(temp.getName())){
								prev=fee;
								break;
							}
						}
						int val=-1;
						for(int i=0;i<displayList.size();i++){
							if(displayList.get(i).getName().equals(temp.getName())){
								val=i;
								break;
							}
						}	
						Fee feetemp=FeeList.getFee(FeeName);
						FeeIncurred post= new FeeIncurred(feetemp,num,
								prev.getDateIncurred(),collection.getCollectionID(),collection.getUnitNo()); 
						displayval tempval=new displayval(FeeName,num,feetemp.getPrice());
						displayList.set(val, tempval);
						itemsList.addAll(displayList);
						
						preEditFee.add(prev);
						postEditFee.add(post);
						sequence.add(2);
						ee.hide();
						ee.update();
						view.update();
						saveButton.setDisable(false);
						saveButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
					}
				}
				
			});
			deleteButton.setDisable(true);
			deleteButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
			editButton.setDisable(true);
	        editButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
		});
		deleteButton.setOnAction(e ->{
			sequence.add(1);
			displayval temp=(displayval) unitTable.getSelectionModel().getSelectedItem();
			unitTable.getItems().remove(temp);
			displayList.remove(temp);
			for(FeeIncurred fee:collection.getAllFee()){
				if(fee.getName().equals(temp.getName())){
					deletedFee.add(fee);
					break;
				}
			}
			saveButton.setDisable(false);
			saveButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
			deleteButton.setDisable(true);
			deleteButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
			editButton.setDisable(true);
	        editButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
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
						saveButton.setStyle("-fx-font:17px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
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
						sequence.add(0);
					}
				}
			});
			
			
		});
		saveButton.setOnAction(e -> {
			for(int i:sequence){
				if(i==0){
					collection.addFee(addedFee.get(0));
					addedFee.remove(0);
				}else if(i==1){
					collection.deleteFee(deletedFee.get(0));
					deletedFee.remove(0);
				}else if(i==2){
					collection.editFee(preEditFee.get(0), postEditFee.get(0));
					preEditFee.remove(0);
					postEditFee.remove(0);
				}
				
			}
			
			;
			if(paidRadio.isSelected()){
				collectionModel.getUnit(unit.getUnitNo()).setDatePaid(java.time.LocalDateTime.now().toString().split("T")[0]);
				unit.setPaid(true);
				paidLabel.setText("PAID");
				paidLabel.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:#618E21;");
				row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#3F561E");
				DB.DBaccess.changeStatus(collectionModel.getUnit(unit.getUnitNo()));
			}
			else{
				if(accountType == 1){
					collectionModel.getUnit(unit.getUnitNo()).setDatePaid(null);
					unit.setPaid(false);
					paidLabel.setText("UNPAID");
					paidLabel.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:#ABAEAF;");
					
					DB.DBaccess.changeStatus(collectionModel.getUnit(unit.getUnitNo()));
					
					if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) > 15){
						row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#FF0606");
						unit.setOverdue(true);
					}
						
					else
						row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#95989A");
				}
			}
			//totalfeeLabel2 = new Label(collection.getTotal()+"");
			totalfeeLabel2.setText(collection.getTotal()+"");
			saveButton.setDisable(true);
			addedFee = new ArrayList<FeeIncurred>();
			preEditFee=new ArrayList<FeeIncurred>();
			postEditFee=new ArrayList<FeeIncurred>();
			deletedFee=new ArrayList<FeeIncurred>();
			sequence=new ArrayList<Integer>();
//			view.update();
		});
		printButton.setOnAction(e->{
			PrinterJob printerJob = PrinterJob.createPrinterJob();
			
			VBox printOutput = new VBox();
			printOutput.setPrefWidth(475);
			
			VBox header = new VBox();
			Label header1 = new Label("BELLEVUE HOMEOWNER ASSOCIATION");
			header.setStyle("-fx-font-weight:bold;");
			Label header2 = new Label("Apolonio Samson Road , The Bellevue QC");
			header.getChildren().addAll(header1,header2);
			
			HBox billingDate = new HBox();
			billingDate.setPrefWidth(475);
			billingDate.setAlignment(Pos.CENTER_RIGHT);
			Label billLabel = new Label("Billing Date: ");
			billLabel.setStyle("-fx-font-size:10px;");
			Label billDate = new Label(java.time.LocalDateTime.now().toString().split("T")[0]);
			billDate.setStyle("-fx-font-size:10px;");
			billingDate.getChildren().addAll(billLabel, billDate);
			
			VBox unitInfo = new VBox();
			Label nameofOwner = new Label("Name of Homeowner: " + unit.getBilledTo());
			nameofOwner.setStyle("-fx-font-size:10px;");
			Label unitNum = new Label("Unit Number: " + unit.getUnitNo());
			unitNum.setStyle("-fx-font-size:10px;");
			unitInfo.getChildren().addAll(nameofOwner, unitNum);
			
			HBox feeTable = new HBox();
			feeTable.setPrefWidth(475);
			
			VBox feeNameCol = new VBox();
			feeNameCol.setStyle("-fx-font-size:10px;");
			feeNameCol.setPrefWidth(150);
			Label feeNameLabel = new Label("FEE NAME");
			feeNameLabel.setStyle("-fx-font-weight:bold;-fx-font-size:10px;");
			feeNameCol.getChildren().add(feeNameLabel);
			for(FeeIncurred fee:collectionModel.getUnit(unit.getUnitNo()).getAllFee()){
				//displayList.add(new displayval(fee.getName(),fee.getTimes(),fee.getPrice()));
				//items.add(fee.getName() + "\t\t\t" + fee.getTimes() + "\t\t\t\t" + fee.getPrice());
				feeNameCol.getChildren().add(new Label(fee.getName()));
			}
			
			VBox quantityCol = new VBox();
			quantityCol.setStyle("-fx-font-size:10px;");
			quantityCol.setPrefWidth(150);
			Label quantityLabel = new Label("QUANTITY");
			quantityLabel.setStyle("-fx-font-weight:bold;");
			quantityCol.getChildren().add(quantityLabel);
			for(FeeIncurred fee:collectionModel.getUnit(unit.getUnitNo()).getAllFee()){
				//displayList.add(new displayval(fee.getName(),fee.getTimes(),fee.getPrice()));
				//items.add(fee.getName() + "\t\t\t" + fee.getTimes() + "\t\t\t\t" + fee.getPrice());
				quantityCol.getChildren().add(new Label(Integer.toString(fee.getTimes())));
			}
			
			VBox priceCol = new VBox();
			priceCol.setStyle("-fx-font-size:10px;");
			priceCol.setPrefWidth(150);
			Label priceLabel = new Label("PRICE");
			priceLabel.setStyle("-fx-font-weight:bold;");
			priceCol.getChildren().add(priceLabel);
			for(FeeIncurred fee:collectionModel.getUnit(unit.getUnitNo()).getAllFee()){
				//displayList.add(new displayval(fee.getName(),fee.getTimes(),fee.getPrice()));
				//items.add(fee.getName() + "\t\t\t" + fee.getTimes() + "\t\t\t\t" + fee.getPrice());
				priceCol.getChildren().add(new Label(Double.toString(fee.getPrice())));
			}
			
			feeTable.getChildren().addAll(feeNameCol,quantityCol,priceCol);
			
			HBox totalPrice = new HBox();
			totalPrice.setStyle("-fx-font-size:10px;");
			totalPrice.setPrefWidth(475);
			totalPrice.setAlignment(Pos.CENTER_RIGHT);
			Label totalPriceLabel = new Label("TOTAL PHP " + collectionModel.getUnit(unit.getUnitNo()).getTotal());
			totalPriceLabel.setStyle("-fx-font-weight:bold;");
			totalPrice.getChildren().addAll(totalPriceLabel);
			printOutput.getChildren().addAll(header,billingDate,unitInfo,feeTable,totalPrice);
			
			//items.add(unitTable.rowFactoryProperty().getValue());
			if(printerJob.showPrintDialog(window) && printerJob.printPage(printOutput))
			       printerJob.endJob();
			
		});
		buttonsHBox.getChildren().addAll(editButton,deleteButton,saveButton,addButton,printButton);
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
		public void setName(String name) {
			this.name = name;
		}
		public void setTimes(int times) {
			this.times = times;
		}
	}
}
