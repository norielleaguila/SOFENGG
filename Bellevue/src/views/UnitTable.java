package views;

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

public class UnitTable {
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
		Label[] labels = new Label[]{new Label("Status"), new Label("Unit #"), new Label("Name"), new Label("Actions")};
		
		labels[0].setMinWidth(100);
		labels[1].setMinWidth(120);
		labels[2].setMinWidth(520);
		labels[3].setMinWidth(350);
		
		
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
	
	public void addRow(int unitNum, String billedTo, String status){
		units.addRow(new UnitRow(unitNum, billedTo, status));
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
	
	/*
	 * When a unit is updated in the db, call this method to update the table
	 */
	public void updateRow(Unit unit){
//		units.getRow(unit)
	}
}
