package view;

import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.VBox;

import java.util.*;

import model.*;
import model.beans.*;

public class CollectionTablesView extends VBox implements View{

	private List<TableView <Fee>> tableList;
	private final HashMap<String, ObservableList<Fee>> feeData;
	private CollectionModel collectionModel;
	
	public CollectionTablesView() {
		super(15);
		
		setId("scrollPane");
		
		setPadding(new Insets(30, 200, 0, 200));
		
		feeData = new HashMap<> ();
		collectionModel = new CollectionModel ();
		tableList = new ArrayList<> ();

		initData ();
		initTable ();
	}

	private void initData () {
		List<Fee> fees = collectionModel.getAllFees ();

		for (int i = 0; i < fees.size (); i++) {
			Fee fee = fees.get (i);

			String feeType =
					collectionModel.getFeeTypeName (fee.getFeeType ());

			ObservableList<Fee> observableFee = feeData.get (feeType);

			if (observableFee == null) {
				observableFee = FXCollections.observableArrayList ();
				feeData.put (feeType, observableFee);
			}

			observableFee.add (fee);
		}
	}

	private void initTable () {
		for (Map.Entry<String, ObservableList<Fee>> d: feeData.entrySet ()) {
			String key =  d.getKey ();
			ObservableList<Fee> val = d.getValue ();

			TableColumn<Fee, String> header = new TableColumn <> (key);
			TableColumn <Fee, String> feeName = new TableColumn <> (Fee.COL_FEE_NAME);
			TableColumn <Fee, String> feePrice = new TableColumn <>(Fee.COL_FEE_PRICE);

			feeName.setCellValueFactory (
					new PropertyValueFactory<Fee, String> (Fee.COL_FEE_NAME)
			);

			feePrice.setCellValueFactory (
					new PropertyValueFactory<Fee, String> (Fee.COL_FEE_PRICE)
			);

			header.getColumns ().add (feeName);
			header.getColumns ().add (feePrice);
			
			feeName.setId("hiddenCol");
			feePrice.setId("hiddenCol");
			
			feeName.getStyleClass().add("nameCol");
			feePrice.getStyleClass().add("priceCol");

			TableView<Fee> table = new TableView<> ();
			
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			feeName.prefWidthProperty().bind(table.widthProperty().multiply(0.75));
			feePrice.prefWidthProperty().bind(table.widthProperty().multiply(0.24));
			
			feeName.minWidthProperty().bind(table.widthProperty().multiply(0.75));
			feePrice.minWidthProperty().bind(table.widthProperty().multiply(0.24));
			
			table.getStyleClass().add("collectionTable");	
			
			table.getColumns ().add (header);
			table.setItems (val);
			
			table.setPrefHeight((val.size() + 1) * 50 + 3);

			tableList.add (table);
			getChildren ().add (table);
		}
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
