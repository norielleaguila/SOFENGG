package controllers;

import java.util.ArrayList;

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
import javafx.scene.control.ToggleGroup;
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
import models.Unit;
import models.UnitList;
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
						
						Button close = new Button("X");
						close.getStylesheets().add("style.css");
						
						close.setId("closePopup");
						
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
						
						close.setAlignment(Pos.TOP_RIGHT);
						
						totalfeeVBox.getChildren().addAll(totalfeeLabel2,totalfeeLabel);
						totalfeeVBox.setAlignment(Pos.CENTER);
						totalfeeVBox.setStyle("-fx-padding: 5px;-fx-border-insets:5px;-fx-background-insets: 5px;");
						
						VBox rightSide = new VBox();
						rightSide.getChildren().addAll(close, totalfeeVBox);
						rightSide.setAlignment(Pos.TOP_RIGHT);
						
						
						unitHeader.getChildren().addAll(unitnumLabel,ownerlotsizeVBox,ownerlotsizeVBox2,headerDivider,rightSide);
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
						VBox unitContent = new VBox();
						unitContent.setPrefWidth(1250);
						unitContent.setPrefHeight(600);
						unitContent.setStyle("-fx-background-color:white;");
						TableView unitTable = new TableView();
						VBox tablePane = new VBox();
						unitTable.setEditable(true);
						
						unitTable.getStylesheets().add("style.css");
						
						TableColumn itemCol = new TableColumn("ITEM");
						TableColumn feeCol = new TableColumn("FEE");
						
						unitTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
						itemCol.prefWidthProperty().bind(unitTable.widthProperty().multiply(0.7));
						feeCol.prefWidthProperty().bind(unitTable.widthProperty().multiply(0.3));
						itemCol.minWidthProperty().bind(unitTable.widthProperty().multiply(0.7));
						feeCol.minWidthProperty().bind(unitTable.widthProperty().multiply(0.3));
						
						ObservableList<String> itemsList = FXCollections.observableArrayList();
						ObservableList<Double> feeList = FXCollections.observableArrayList();
						
						unitTable.getColumns().addAll(itemCol, feeCol);
						
						itemCol.getStyleClass().add("popupTable");
						feeCol.getStyleClass().add("popupTable");
						
						tablePane.getChildren().add(unitTable);
						tablePane.setPrefWidth(650);
						tablePane.setMinWidth(650);
						tablePane.setMaxWidth(650);
						unitTable.setPrefWidth(650);
						unitTable.setMinWidth(650);
						unitTable.setMaxWidth(650);
						tablePane.setStyle("-fx-background-color: #AA6B5A;");
						
						HBox bottomContainer = new HBox();
						Region region1 = new Region();
					    HBox.setHgrow(region1, Priority.ALWAYS);

						VBox buttonsVBox = new VBox();
						HBox paidHBox = new HBox();
						Label paidLabel = new Label("PAID");
						paidLabel.setStyle("-fx-font:bold 50px 'Segoe UI';-fx-text-fill:#618E21;");
						ToggleGroup paidToggle = new ToggleGroup();
						RadioButton paidRadio = new RadioButton("Paid");
						RadioButton unpaidRadio = new RadioButton("Unpaid");
						paidRadio.setToggleGroup(paidToggle);
						unpaidRadio.setToggleGroup(paidToggle);
						
						paidHBox.getChildren().addAll(paidLabel,paidRadio,unpaidRadio);
						paidHBox.setAlignment(Pos.CENTER_LEFT);
						paidHBox.setSpacing(10);
						
						HBox buttonsHBox = new HBox();
						Button saveButton = new Button("Save Changes");
						Button addButton = new Button("Add Expenses");
						Button printButton = new Button("Print Bill");
						
						saveButton.setPrefWidth(200);
						saveButton.setPrefHeight(20);
						
						addButton.setPrefWidth(200);
						
						printButton.setPrefWidth(200);
						saveButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
						addButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#F95959;-fx-text-fill:white;");
						printButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#F95959;-fx-text-fill:white;");
						
						buttonsHBox.getChildren().addAll(saveButton,addButton,printButton);
						buttonsHBox.setSpacing(10);
						buttonsVBox.getChildren().addAll(paidHBox,buttonsHBox);
						bottomContainer.getChildren().addAll(region1,buttonsVBox);
						unitContent.getChildren().addAll(unitTable, bottomContainer);
						
						VBox unitVBox = new VBox();
						unitVBox.getChildren().addAll(unitHeader,unitContent);
						unitPopup.getContent().addAll(unitVBox);
						unitPopup.show(window);
						unitPopup.setAutoHide(true);
						
						close.setOnAction(e -> {
							unitPopup.hide();
						});
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
