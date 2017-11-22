package views;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Fee;
import models.FeeList;

/**
 * @author AGUILA, Norielle
 */


public class CollectionTable extends ScrollPane implements ViewInterface{
	
	private VBox tableContainer;
	private ArrayList<TableView> tables;	// 1 VBox = 1 type
	private FeeList model; 	// the list is assumed to be sorted by type
	
	public CollectionTable(FeeList model){
		tableContainer = new VBox(10);
		this.model = model;
		initContainer();

		this.setContent(tableContainer);
		
		this.setFitToWidth(true);
	}
	
	public void initContainer(){
		//initTypes();
		initTables();
		
		tableContainer.getChildren().addAll(tables);

		tableContainer.setAlignment(Pos.TOP_CENTER);
		tableContainer.setPadding(new Insets(10, 200, 10, 200));
		tableContainer.setId("tableContainer");
	}
	
	public void initTables(){
		tables = new ArrayList<>();
		
		// create tables
		for(int i = 0; i < Fee.FEETYPE.size(); i++){
			tables.add(new TableView());
			TableColumn typeCol = new TableColumn(Fee.FEETYPE.get(i));
			TableColumn nameCol = new TableColumn("");
			TableColumn priceCol = new TableColumn("");
			
			nameCol.setCellValueFactory(new PropertyValueFactory <Fee, String>("feeName"));

			priceCol.setCellValueFactory(new PropertyValueFactory <Fee, Double>("price"));
			ObservableList<Fee> data = FXCollections.observableArrayList(model.filterType(Fee.FEETYPE.get(i)));

			nameCol.setId("hiddenCol");
			priceCol.setId("hiddenCol");
			
			nameCol.getStyleClass().add("nameCol");
			priceCol.getStyleClass().add("priceCol");
			
			tables.get(i).setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			nameCol.prefWidthProperty().bind(tables.get(i).widthProperty().multiply(0.75));
			priceCol.prefWidthProperty().bind(tables.get(i).widthProperty().multiply(0.24));
			
			nameCol.minWidthProperty().bind(tables.get(i).widthProperty().multiply(0.75));
			priceCol.minWidthProperty().bind(tables.get(i).widthProperty().multiply(0.24));
			
			tables.get(i).getStyleClass().add("collectionTable");	
			
			tables.get(i).setItems(data);
			
			// create subcolumns
			typeCol.getColumns().addAll(nameCol, priceCol);
			
			tables.get(i).getColumns().add(typeCol);
			
			tables.get(i).setPrefHeight((data.size() + 1) * 50 + 3);
			
			tables.get(i).setPlaceholder(new Label(""));
		}
	}
	
	public void updateTables(){
		initTables();
		
		tableContainer.getChildren().addAll(tables);
	}
	
	public void update(){
		
		tableContainer.getChildren().clear();
		
		updateTables();
		
	}
}
