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


public class CollectionTable extends ScrollPane{
	
	private VBox tableContainer;
	private ArrayList<TableView> tables;	// 1 VBox = 1 type
	private FeeList model; 	// the list is assumed to be sorted by type
	private ArrayList<String> types;
	
	public CollectionTable(FeeList model){
		tableContainer = new VBox(10);
		this.model = model;
		
		initContainer();
		
		this.setContent(tableContainer);
		
		this.setFitToHeight(true);
		this.setFitToWidth(true);
	}
	
	public void initContainer(){
		initTypes();
		initTables();
		
		tableContainer.getChildren().addAll(tables);

		tableContainer.setAlignment(Pos.TOP_CENTER);
		tableContainer.setPadding(new Insets(100));
		tableContainer.setId("tableContainer");
	}
	
	/**
	 * create type labels
	 */
	public void initTypes(){
		types = new ArrayList<String>();
		ArrayList<String> types = model.getTypes();
		
		for(String type: types){
			this.types.add(type);
		}
		
	}
	
	public void initTables(){
		tables = new ArrayList<>();
		
		// create tables
		for(int i = 0; i < types.size(); i++){
			tables.add(new TableView());
			TableColumn typeCol = new TableColumn(types.get(i).toUpperCase());
			TableColumn nameCol = new TableColumn("");
			TableColumn priceCol = new TableColumn("");
			
			nameCol.setCellValueFactory(new PropertyValueFactory <Fee, String>("feeName"));
			priceCol.setCellValueFactory(new PropertyValueFactory <Fee, String>("price"));
			
			ObservableList<Fee> data = FXCollections.observableArrayList(model.filterType(types.get(i)));

			nameCol.setId("hiddenCol");
			priceCol.setId("hiddenCol");
			
			nameCol.getStyleClass().add("nameCol");
			priceCol.getStyleClass().add("priceCol");
			

//			tables.get(i).setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			
			nameCol.prefWidthProperty().bind(tables.get(i).widthProperty().multiply(0.75));
			priceCol.prefWidthProperty().bind(tables.get(i).widthProperty().multiply(0.24));
			
			tables.get(i).getStyleClass().add("collectionTable");	
			
			tables.get(i).setItems(data);
			
			// create subcolumns
			typeCol.getColumns().addAll(nameCol, priceCol);
			
			tables.get(i).getColumns().add(typeCol);
			
			tables.get(i).setPrefHeight((data.size() + 1) * 50 + 20);
		}
	}
	
//	public void initTables(){
//		// create the vbox, then add the category/type label as a header
//		for(int i = 0; i < typeLbls.size(); i++){
//			tables.add(new VBox(5));
//			tables.get(i).getChildren().add(typeLbls.get(i));
//		}
//		
//		String type = "";
//		// each table has 2 columns; for fee name and price
//		for(int i = 0; i < typeLbls.size(); i++){
//			type = typeLbls.get(i).getText();
//			
//			// assumes that the fees list is already sorted by type
//			for(int j = 0; j < fees.getFees().size() && fees.getFee(j).getType().equals(type); j++){
//				Fee curr = fees.getFee(j);
//				
//				HBox row = new HBox(10);
//				
//				row.getChildren().add(new Label(curr.getFeeName()));
//				row.getChildren().add(new Label(curr.getPrice() + ""));
//				
//				tables.get(i).getChildren().add(row);
//			}
//		}
//	}
}
