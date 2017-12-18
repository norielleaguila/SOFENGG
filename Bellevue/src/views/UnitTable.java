package views;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Unit;
import models.UnitList;

/**
 * @author AGUILA, Norielle
 */

public class UnitTable implements ViewInterface{
	private HBox header;
	private ScrollPane tableScroll;
	private UnitRowList units;
	
	public UnitTable(){
		header = new HBox();
		tableScroll = new ScrollPane();
		units = new UnitRowList();
		
		initHeader();
		initTable();
	}
	
	public void initHeader(){
		Label[] labels = new Label[]{new Label("Status"), new Label("Unit Num"), new Label("Actions")};
		
		labels[0].setMinWidth(50);
		labels[1].setMinWidth(150);
		labels[2].setMinWidth(300);
		
		labels[0].setPadding(new Insets(0, 30, 0, 0));
		labels[1].setPadding(new Insets(0, 30, 0, 0));
		labels[2].setPadding(new Insets(0, 60, 0, 0));
		
		for(int i = 0; i < labels.length; i++){
			labels[i].setId("tableHeaderLbl");
		}
		
		header.setAlignment(Pos.CENTER);
		header.setPadding(new Insets(5));
		header.getChildren().addAll(labels);
	}
	
	public void initTable(){
		tableScroll.setContent(units);
		tableScroll.setFitToWidth(true);
		
		units.setOnScroll(e -> {
			double deltaY = e.getDeltaY()*6; // *6 to make the scrolling a bit faster
			double width = tableScroll.getContent().getBoundsInLocal().getWidth();
			double vvalue = tableScroll.getVvalue();
			tableScroll.setVvalue(vvalue + -deltaY/width);
		});
	}
	
	public void addRow(int unitNum, String billedTo){
		units.addRow(new UnitRow(unitNum, billedTo));
	}
	
	public void addRow(Unit unit){
		units.addRow(new UnitRow(unit));
//		System.out.println(unit.getUnitNo());
	}
	
	public HBox getHeader(){
		return header;
	}
	
	public ScrollPane getTableScroll(){
		return tableScroll;
	}
	
	public UnitRowList getUnitList(){
		return units;
	}
	
	public void setRows(ArrayList<Unit> units){
		ArrayList<UnitRow> unitRows = new ArrayList<UnitRow>();
		
		for(Unit unit: units){
			unitRows.add(new UnitRow(unit));
		}
		
		this.units.setRows(unitRows);
	}
	
	/*
	 * When a unit is updated in the db, call this method to update the table
	 */
	public void updateRow(Unit unit){
		units.resetLayout();
		units.getRow(unit).update();
	}

	@Override
	public void update() {
		units.resetLayout();
		this.getUnitList().getChildren().clear();
	}
}
