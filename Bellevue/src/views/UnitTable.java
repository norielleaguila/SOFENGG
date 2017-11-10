package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Unit;

/**
 * @author AGUILA, Norielle
 */

public class UnitTable {
	private HBox header;
	private ScrollPane tableScroll;
	private UnitList units;
	
	public UnitTable(){
		header = new HBox(20);
		tableScroll = new ScrollPane();
		units = new UnitList();
		
		initHeader();
		initTable();
	}
	
	public void initHeader(){
		Label[] labels = new Label[]{new Label("Status"), new Label("Unit #"), new Label("Name"), new Label("Actions")};
		
		labels[0].setMinWidth(30);
		labels[1].setMinWidth(105);
		labels[2].setMinWidth(510);
		labels[3].setMinWidth(310);
		
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
	
	public void addDummyRows(){
		addRow(123, "Alfonso Secuya", "P");
		addRow(223, "Raafi Bandrang", "P");
		addRow(323, "Norielle Aguila", "P");
		addRow(123, "Alfonso Secuya", "P");
		addRow(223, "Raafi Bandrang", "P");
		addRow(323, "Norielle Aguila", "P");
		addRow(123, "Alfonso Secuya", "P");
		addRow(223, "Raafi Bandrang", "P");
		addRow(323, "Norielle Aguila", "P");
		addRow(123, "Alfonso Secuya", "P");
		addRow(223, "Raafi Bandrang", "P");
		addRow(323, "Norielle Aguila", "P");
		addRow(123, "Alfonso Secuya", "P");
		addRow(223, "Raafi Bandrang", "P");
		addRow(323, "Norielle Aguila", "P");
		addRow(123, "Alfonso Secuya", "P");
		addRow(223, "Raafi Bandrang", "P");
		addRow(323, "Norielle Aguila", "P");
		addRow(123, "Alfonso Secuya", "P");
		addRow(223, "Raafi Bandrang", "P");
		addRow(323, "Norielle Aguila", "P");
		addRow(123, "Alfonso Secuya", "P");
		addRow(223, "Raafi Bandrang", "P");
		addRow(323, "Norielle Aguila", "P");
		addRow(123, "Alfonso Secuya", "P");
		addRow(223, "Raafi Bandrang", "P");
		addRow(323, "Norielle Aguila", "P");
		addRow(123, "Alfonso Secuya", "P");
		addRow(223, "Raafi Bandrang", "P");
		addRow(323, "Norielle Aguila", "P");
	}
	
	public HBox getHeader(){
		return header;
	}
	
	public ScrollPane getTableScroll(){
		return tableScroll;
	}
	
	public UnitList getUnitList(){
		return units;
	}
}
