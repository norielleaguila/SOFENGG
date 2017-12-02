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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
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
						new UnitCollection(collectionModel, unit, row, account.getType(), window, view);
						view.update();
					}
				});
			}
		}
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
	        //System.out.println(java.time.LocalDateTime.now().toString().split("T")[0]);
	        /*if(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2].equals("15") && !unit.isPaid()){
	        	paidLabel.setText("OVERDUE");
	        	paidLabel.setStyle("-fx-font:bold 50px 'Segoe UI';-fx-text-fill:#F95959;");
				row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#FF0606");
	        }
	        else if(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2].equals("01") && unit.isPaid()){
	        	paidLabel.setText("UNPAID");
				paidLabel.setStyle("-fx-font:bold 50px 'Segoe UI';-fx-text-fill:#ABAEAF;");	
				row.getStatusLabel().setStyle("-fx-border-radius: 200px;-fx-background-radius: 200px;-fx-background-color:#95989A");
	        }*/
	        
	        /*if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) > 15 && setOverdue){
				for(Unit unit : view.getUnitList().getAllUnpaidUnits()){
					unit.setOverdue(true);
					DB.DBaccess.changeStatus(collectionModel.getUnit(unit.getUnitNo()));
				}
				view.getChildren().clear();
				view.update();
				setOverdue = false;
				
			}
	        if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) == 1 && resetPaid){
				for(Unit unit : view.getUnitList().getAllPaidUnits()){
					unit.setPaid(false);
					DB.DBaccess.changeStatus(collectionModel.getUnit(unit.getUnitNo()));
				}
				view.getChildren().clear();
				view.update();
				resetPaid = false; 
			}
	        if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) != 1 && !resetPaid)
	        	resetPaid = true;
	        if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) < 16 && !setOverdue)
	        	setOverdue = true;*/
	    }),
	         new KeyFrame(Duration.seconds(1))
	    );
	    clock.setCycleCount(Animation.INDEFINITE);
	    clock.play();
	}
}
