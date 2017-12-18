package controllers;

import java.util.ArrayList;

import DB.DBaccess;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Collection;
import models.CollectionList;
import models.Fee;
import models.FeeIncurred;
import models.FeeList;
import models.Unit;
import models.UnitList;
import views.AddExpense;
import views.UnitCollection;
import views.UnitRow;
import views.UnitTab;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class UnitTabController extends Controller{
	private UnitTab view;
	private Stage window;
	private CollectionList collectionModel;
	boolean resetPaid, setOverdue;
	
	public UnitTabController(UnitList model, CollectionList collectionModel, Stage window){
		view = new UnitTab(model);
		this.window = window;
		this.collectionModel = collectionModel;
		resetPaid = true;
		setOverdue = true;
		setUpButtons();
	}
	
	public void reset(){
		view=new UnitTab(getUnitsModel());
		this.collectionModel = getCollectionModel();
		resetPaid = true;
		setOverdue = true;
		setUpButtons();
	}
	public CollectionList getCollectionModel(){
		// query db
		CollectionList model = new CollectionList(DB.DBaccess.getCollectionData());
		return model;
	}
	public UnitList getUnitsModel(){
		// query db
		
		UnitList model = new UnitList(DBaccess.getUnitsData());
		
		CollectionList colModel = getCollectionModel();
		
		for(Unit unit: model.getUnits()){
			if(colModel.getUnit(unit.getUnitNo())!= null){
				unit.setPaid(colModel.getUnit(unit.getUnitNo()).isPaid());
				unit.setOverdue(colModel.getUnit(unit.getUnitNo()).isOverdue());
			}
		}
		
		return model;
	}
	public UnitTab getView(){
		return view;
	}

	@Override
	public void setUpButtons() {
		ArrayList<UnitRow> rows = view.getTable().getUnitList().getAllRows();
		
		boolean changed = false;
		
		if(rows != null){
			for(UnitRow row: rows){
				System.out.println(row.getUnitNum());
				row.setViewBtnListener(new UnitRow.viewBtnlistener() {
					@Override
					public void onAction(Unit unit) {
						System.out.println(row.getUnitNum());
						new UnitCollection(collectionModel, unit, row, account.getType(), window, view);
						view.update();
					}
				});
				row.setPrintBtnListener(new UnitRow.printBtnlistener() {
					
					@Override
					public void onAction(Unit unit) {
						// TODO Auto-generated method stub
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
						/*ListView<String> printList = new ListView<>();
						ObservableList<String> items =FXCollections.observableArrayList ();
						printList.setItems(items);
						
						//System.out.println("");
						items.add(unit.getBilledTo());
						items.add(Integer.toString(unit.getUnitNo()));
						items.add("FEE NAME\t\t\tQUANTITY\t\t\tTOTAL PRICE");
						for(FeeIncurred fee:collectionModel.getUnit(unit.getUnitNo()).getAllFee()){
							//displayList.add(new displayval(fee.getName(),fee.getTimes(),fee.getPrice()));
							items.add(fee.getName() + "\t\t\t" + fee.getTimes() + "\t\t\t\t" + fee.getPrice());
						}
						printList.setPrefWidth(1000);
						//items.add(unitTable.rowFactoryProperty().getValue());*/
						if(printerJob.showPrintDialog(window) && printerJob.printPage(printOutput))
						       printerJob.endJob();
					}
				});
			}
		}
		
		view.getToolBar().setOnSearchListener(query -> {
			view.search(query);
		});
		
		view.getToolBar().setOnFilterListener(which -> {
			view.filter(which);
		});
		
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			if(!checkEqual(collectionModel.getCollection(),DB.DBaccess.getCollectionData())){
				collectionModel = new CollectionList(DB.DBaccess.getCollectionData());
				System.out.println("checker");
				int index = 0;
				for(Unit unit : view.getUnitList().getUnits()){
					unit.setOverdue(collectionModel.getUnit(unit.getUnitNo()).isOverdue());
					unit.setPaid(collectionModel.getUnit(unit.getUnitNo()).isPaid());
					DB.DBaccess.changeStatus(collectionModel.getUnit(unit.getUnitNo()));
					index++;
				}
				view.update();
			}
	        
	        if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) > 15 && setOverdue){
				for(Unit unit : view.getUnitList().getAllUnpaidUnits()){
					unit.setOverdue(true);
					DB.DBaccess.changeStatus(collectionModel.getUnit(unit.getUnitNo()));
				}
				view.update();
				setOverdue = false;
				
			}
	        if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) == 1 && resetPaid){
				for(Unit unit : view.getUnitList().getAllPaidUnits()){
					collectionModel.getUnit(unit.getUnitNo()).setDatePaid(null);
					unit.setPaid(false);
					DB.DBaccess.changeStatus(collectionModel.getUnit(unit.getUnitNo()));
				}
				view.update();
				resetPaid = false; 
			}
	        if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) != 1 && !resetPaid)
	        	resetPaid = true;
	        if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) < 16 && !setOverdue)
	        	setOverdue = true;
	    }),
	         new KeyFrame(Duration.seconds(1))
	    );
	    clock.setCycleCount(Animation.INDEFINITE);
	    clock.play();
	}
	public boolean checkEqual(ArrayList<Collection> collection1, ArrayList<Collection> collection2){
		//boolean equal = true;
		for(int i = 0 ; i < collection1.size(); i++){
			if(collection1.get(i).getDatePaid() == null && collection2.get(i).getDatePaid() != null)
				return false;
			else if(collection1.get(i).getDatePaid() != null && collection2.get(i).getDatePaid() == null)
				return false;
			else if(!(collection1.get(i).getDatePaid() == null && collection2.get(i).getDatePaid() == null) && 
					!collection1.get(i).getDatePaid().split(" ")[0].equals(collection2.get(i).getDatePaid().split(" ")[0])){
				System.out.println(collection1.get(i).getDatePaid().split(" ")[0].equals(collection2.get(i).getDatePaid().split(" ")[0]));
				System.out.println(collection1.get(i).getDatePaid().split(" ")[0]);
				System.out.println(collection2.get(i).getDatePaid().split(" ")[0]);
				return false;
			}
		}
		return true;
	}
	
	
}
