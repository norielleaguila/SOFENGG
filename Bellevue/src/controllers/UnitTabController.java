package controllers;

import java.util.ArrayList;

import DB.DBaccess;
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
import models.Collection;
import models.CollectionList;
import models.Fee;
import models.FeeIncurred;
import models.FeeList;
import models.Unit;
import models.UnitList;
import views.AddExpense;
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
	
	public UnitTabController(UnitList model, CollectionList collectionModel, Stage window){
		view = new UnitTab(model);
		this.window = window;
		this.collectionModel = collectionModel;
		
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
				row.setViewBtnListener(new UnitRow.viewBtnlistener() {
					@Override
					public void onAction(Unit unit) {
						Label unitnumLabel, ownerLabel, ownerLabel2, tctLabel, tctLabel2, lotsizeLabel, lotsizeLabel2, addressLabel, addressLabel2, totalfeeLabel, totalfeeLabel2;
						AnchorPane unitPane;
						Popup unitPopup;
						unitPane = new AnchorPane();
						unitPopup = new Popup();
						Collection collection=collectionModel.getUnit(unit.getUnitNo());
						ArrayList<FeeIncurred> addedFee=new ArrayList<FeeIncurred>();
						ArrayList<displayval> displayList=new ArrayList<displayval>();
						
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
						
						Button close = new Button("X");
						close.getStylesheets().add("style.css");
						
						close.setId("closePopup");
						
						Region headerDivider = new Region();
						HBox.setHgrow(headerDivider, Priority.ALWAYS);
						
						HBox unitHeader = new HBox();
						VBox ownerlotsizeVBox = new VBox();
						VBox ownerlotsizeVBox2 = new VBox();
						VBox totalfeeVBox = new VBox();
						
						if(account.getType() == 1)
							ownerlotsizeVBox.getChildren().addAll(ownerLabel, tctLabel,lotsizeLabel,addressLabel);
						else ownerlotsizeVBox.getChildren().addAll(ownerLabel, addressLabel);
						ownerlotsizeVBox.setPrefHeight(20);
						ownerlotsizeVBox.setAlignment(Pos.BOTTOM_LEFT);
						
						if(account.getType() == 1)
							ownerlotsizeVBox2.getChildren().addAll(ownerLabel2, tctLabel2,lotsizeLabel2, addressLabel2);
						else ownerlotsizeVBox2.getChildren().addAll(ownerLabel2, addressLabel2);
						ownerlotsizeVBox2.setPrefHeight(20);
						ownerlotsizeVBox2.setAlignment(Pos.BOTTOM_LEFT);
						
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
						
						VBox unitContent = new VBox();
						unitContent.setPrefWidth(1250);
						unitContent.setPrefHeight(600);
						unitContent.setStyle("-fx-background-color:white;");
						TableView unitTable = new TableView();
						VBox tablePane = new VBox();
						unitTable.setEditable(true);
						
						unitTable.getStylesheets().add("style.css");
						
						TableColumn itemCol = new TableColumn("ITEM");
						TableColumn noCol = new TableColumn("No.");
						TableColumn feeCol = new TableColumn("FEE");
						

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
						ObservableList<displayval> itemsList = FXCollections.observableArrayList(displayList);
						//sStringProperty temp = new SimpleStringProperty();
						//temp.set("Monthly Fee");
						//String temp2 = "Monthly Fee";
						//itemsList.add(temp2);
						
						unitTable.getColumns().addAll(itemCol, noCol,feeCol);
						
						itemCol.getStyleClass().add("popupTable");
						feeCol.getStyleClass().add("popupTable");
						
						unitTable.setItems(itemsList);
						
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
						
						HBox buttonsHBox = new HBox();
						Button saveButton = new Button("Save Changes");
						Button addButton = new Button("Add Expenses");
						Button printButton = new Button("Print Bill");
						
						saveButton.setDisable(true);
						saveButton.setPrefWidth(200);
						saveButton.setPrefHeight(20);
						
						addButton.setPrefWidth(200);
						
						printButton.setPrefWidth(200);
						
						saveButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#EFF2E3;-fx-text-fill:white;-fx-border-insets:10px;");
						addButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#F95959;-fx-text-fill:white;");
						printButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#F95959;-fx-text-fill:white;");
						
						unpaidRadio.setOnAction(e -> {
							saveButton.setDisable(false);
					        saveButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
						});
						
						paidRadio.setOnAction(e -> {
							saveButton.setDisable(false);
					        saveButton.setStyle("-fx-font:25px 'Segoe UI';-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-border-insets:10px;");
						});
						
						if(account.getType() != 1)
							unpaidRadio.setDisable(true);
						
						if(collectionModel.getUnit(unit.getUnitNo()).isPaid())
							paidToggle.selectToggle(paidRadio);
						else
							paidToggle.selectToggle(unpaidRadio);
						
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
								if(account.getType() == 1){
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
//							view.update();
							
						});
						
						
						
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
						
						unitVBox.getStylesheets().add("style.css");
						unitVBox.setId("popup");
						
						close.setOnAction(e -> {
							unitPopup.hide();
							row.update();
						});
						
						

					}
				});
			}
		}
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
