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
						ListView<String> printList = new ListView<>();
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
						//items.add(unitTable.rowFactoryProperty().getValue());
						if(printerJob.showPrintDialog(window) && printerJob.printPage(printList))
						       printerJob.endJob();
					}
				});
			}
		}
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
