package controllers;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Unit;
import models.UnitList;
import views.UnitRow;
import views.UnitTab;

public class UnitTabController extends Controller{
	private UnitTab view;
	private Stage window;
	
	public UnitTabController(UnitList model, Stage window){
		view = new UnitTab(model);
		this.window = window;
		setUpButtons();
	}
	
	public UnitTab getView(){
		return view;
	}

	@Override
	public void setUpButtons() {
		ArrayList<UnitRow> rows = view.getTable().getUnitList().getAllRows();
		
		if(rows != null){
			for(UnitRow row: rows){
				row.setViewBtnListener(new UnitRow.viewBtnlistener() {
					@Override
					public void onAction(Unit unit) {
						// place all action things here
						Label unitnumLabel, ownerLabel, ownerLabel2, lotsizeLabel, lotsizeLabel2, totalfeeLabel, totalfeeLabel2;
						AnchorPane unitPane;
//						Scene unitScene;
//						Stage unitStage;
						Popup unitPopup;
						unitPane = new AnchorPane();
						unitPopup = new Popup();
						
						unitnumLabel = new Label("UNIT#" + unit.getUnitNo());
						unitnumLabel.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:white;-fx-padding: 5px;-fx-border-insets:5px;-fx-background-insets: 5px;");
						
						ownerLabel = new Label("Owner: ");
						ownerLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
						
						lotsizeLabel = new Label("Lot size: ");
						lotsizeLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
						
						ownerLabel2 = new Label(unit.getBilledTo());
						ownerLabel2.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:white;");
						
						lotsizeLabel2 = new Label();
						lotsizeLabel2.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:white;");
						
						totalfeeLabel = new Label("Total Fee");
						totalfeeLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
						
						totalfeeLabel2 = new Label("Sample total fee");
						totalfeeLabel2.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:white;");
						
						Region headerDivider = new Region();
						HBox.setHgrow(headerDivider, Priority.ALWAYS);
						
						HBox unitHeader = new HBox();
						VBox ownerlotsizeVBox = new VBox();
						VBox ownerlotsizeVBox2 = new VBox();
						VBox totalfeeVBox = new VBox();
						
						ownerlotsizeVBox.getChildren().addAll(ownerLabel, lotsizeLabel);
						ownerlotsizeVBox.setPrefHeight(20);
						ownerlotsizeVBox.setAlignment(Pos.BOTTOM_LEFT);
						
						ownerlotsizeVBox2.getChildren().addAll(ownerLabel2, lotsizeLabel2);
						ownerlotsizeVBox2.setPrefHeight(20);
						ownerlotsizeVBox2.setAlignment(Pos.BOTTOM_RIGHT);
						
						totalfeeVBox.getChildren().addAll(totalfeeLabel2,totalfeeLabel);
						totalfeeVBox.setAlignment(Pos.CENTER);
						totalfeeVBox.setStyle("-fx-padding: 5px;-fx-border-insets:5px;-fx-background-insets: 5px;");
						unitHeader.getChildren().addAll(unitnumLabel,ownerlotsizeVBox,ownerlotsizeVBox2,headerDivider,totalfeeVBox);
						unitHeader.setStyle("-fx-background-color: #AA6B5A;-fx-padding:10px;");
						unitHeader.setPrefWidth(1250);
						unitHeader.setPrefHeight(100);
						unitHeader.setAlignment(Pos.BOTTOM_LEFT);
						unitPane.getChildren().addAll(unitHeader);
//						unitScene = new Scene(unitPane, 1250, 750);
						
//						unitStage = new Stage();
//						unitStage.setScene(unitScene);
//						unitStage.initModality(Modality.APPLICATION_MODAL);
						//unitStage.show();
						
//						unitPopup.setX(300);
//						unitPopup.setY(300);
						unitPopup.getContent().addAll(unitHeader);
//						window.show();
						unitPopup.show(window);
//						System.out.println(unit.getUnitNo());
						
						
//						FlowPane unitInfoPane = new FlowPane();
//						Label l1 = new Label("test");
//						Button b = new Button("X");
//						unitInfoPane.getChildren().addAll(b, l1);
//						unitInfoPane.setStyle("-fx-background-color: white");
//						
//						Popup p = new Popup();
//						p.setX(300);
//						p.setY(10);
//						p.getContent().add(unitInfoPane);
//						
//						b.setOnAction(e -> p.hide());
//						
//						p.show(window);

					}
				});
			}
		}
	}
}
