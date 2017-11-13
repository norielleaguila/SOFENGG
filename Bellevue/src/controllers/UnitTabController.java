package controllers;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import views.UnitRow;
import views.UnitTab;

public class UnitTabController extends Controller{
	private UnitTab view;
	
	public UnitTabController(){
		view = new UnitTab();
		
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
					public void onAction(int unitNo) {
						// place all action things here
						Label unitnumLabel, ownerLabel, ownerLabel2, lotsizeLabel, lotsizeLabel2, totalfeeLabel, totalfeeLabel2;
						AnchorPane unitPane;
						Scene unitScene;
						Stage unitStage;
						Popup unitPopup;
						unitPane = new AnchorPane();
						unitPopup = new Popup();
						
						unitnumLabel = new Label("UNIT# " + unitNo);
						unitnumLabel.setStyle("-fx-font:bold 30px 'Segoe UI';-fx-text-fill:white;-fx-padding: 5px;-fx-border-insets:5px;-fx-background-insets: 5px;");
						
						ownerLabel = new Label("Owner: ");
						ownerLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
						
						ownerLabel2 = new Label();
						lotsizeLabel = new Label("Lot size: ");
						lotsizeLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
						
						
						totalfeeLabel = new Label("Total Fee");
						totalfeeLabel.setStyle("-fx-font:bold 20px 'Segoe UI';-fx-text-fill:#F5C58F;");
						
						Region headerDivider = new Region();
						HBox.setHgrow(headerDivider, Priority.ALWAYS);
						
						HBox unitHeader = new HBox();
						VBox ownerlotsizeVBox = new VBox();
						VBox totalfeeVBox = new VBox();
						
						ownerlotsizeVBox.getChildren().addAll(ownerLabel, lotsizeLabel);
						ownerlotsizeVBox.setPrefHeight(20);
						ownerlotsizeVBox.setAlignment(Pos.BOTTOM_LEFT);
						
						totalfeeVBox.getChildren().addAll(totalfeeLabel);
						totalfeeVBox.setAlignment(Pos.BOTTOM_LEFT);
						totalfeeVBox.setStyle("-fx-padding: 5px;-fx-border-insets:5px;-fx-background-insets: 5px;");
						unitHeader.getChildren().addAll(unitnumLabel,ownerlotsizeVBox,headerDivider,totalfeeVBox);
						unitHeader.setStyle("-fx-background-color: #AA6B5A;");
						unitHeader.setPrefWidth(1250);
						unitHeader.setPrefHeight(100);
						unitHeader.setAlignment(Pos.BOTTOM_LEFT);
						unitPane.getChildren().addAll(unitHeader);
						unitScene = new Scene(unitPane, 1250, 750);
						
						unitPopup.setX(300);
						unitPopup.setY(200);
						unitPopup.getContent().addAll(unitHeader,unitnumLabel);
						//unitStage = new Stage();
						//unitStage.setScene(unitScene);
						//unitStage.initModality(Modality.APPLICATION_MODAL);
						//unitStage.show();
						
						unitPopup.show(window);
						System.out.println(unitNo);
					}
				});
			}
		}
	}
}
