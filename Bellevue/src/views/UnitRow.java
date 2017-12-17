package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Unit;

/**
 * @author AGUILA, Norielle
 */
public class UnitRow extends HBox implements ViewInterface{
	
	public static int rowNum = 0;
	
	private Unit unit;

	private Label unitNumLbl;
	private Label billedToLbl;
	private Label statusLbl;
	
	private Button viewBtn;
	private Button printBtn;
	
	private viewBtnlistener vbl;
	private printBtnlistener pbl;
	
	public UnitRow(int unitNum, String billedTo){
		super();
		
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(5));
		
		rowNum++;
		
		unitNumLbl = new Label(unitNum + "");
		billedToLbl = new Label(billedTo);
		statusLbl = new Label();
		
		this.getStylesheets().add("style.css");
		
		initLayout();
		
	}
	
	public UnitRow(Unit unit){
		this(unit.getUnitNo(), unit.getBilledTo());
		this.unit = unit;
		
		updateColor();
	}
	
	public void initStatus(){
		HBox.setMargin(statusLbl, new Insets(0, 30, 0, 0));
		statusLbl.setMinSize(30, 30);
		statusLbl.setMaxSize(30, 30);
	}
	
	public void initUnitNum(){
		unitNumLbl.setId("unitNumLbl");
		HBox.setMargin(unitNumLbl, new Insets(0, 10, 0, 10));
		unitNumLbl.setMinWidth(100);
		unitNumLbl.setMaxWidth(100);
	}
	
	public void initBilledTo(){
		billedToLbl.setId("unitOwnerLbl");
		HBox.setMargin(billedToLbl, new Insets(0, 10, 0, 10));
		billedToLbl.setMinWidth(500);
		billedToLbl.setMaxWidth(500);
	}
	
	public void initButtons(){
		viewBtn = new Button("View");
		printBtn = new Button("Print");
		
		viewBtn.setMinWidth(150);
		viewBtn.setMaxWidth(150);
		viewBtn.setId("viewBtn");
		
		viewBtn.setOnAction ((e) -> {
			if(vbl != null){
				vbl.onAction (unit);
			}
		});
		
		printBtn.setMinWidth(150);
		printBtn.setMaxWidth(150);
		printBtn.setId("printBtn");
		
		printBtn.setOnAction ((e) -> {
			if(pbl != null){
				pbl.onAction (unit);
			}
		});
		
		HBox.setMargin(viewBtn, new Insets(0, 10, 0, 5));
		HBox.setMargin(printBtn, new Insets(0, 5, 0, 0));
	}
	
	public void initLayout(){
		
		initStatus();
		initUnitNum();
		initBilledTo();
		initButtons();
		
		getChildren().add(statusLbl);
		getChildren().add(unitNumLbl);
		getChildren().add(billedToLbl);
		getChildren().add(viewBtn);
		getChildren().add(printBtn);
	}

	public interface viewBtnlistener {
		public void onAction (Unit unit);
	}
	public interface printBtnlistener {
		public void onAction (Unit unit);
	}

	public void setViewBtnListener (viewBtnlistener vbl) {
		this.vbl = vbl;
	}
	public void setPrintBtnListener (printBtnlistener pbl) {
		this.pbl = pbl;
	}
	public String getTct(){
		return unit.accessTCT();
	}
	
	/* GETTERS & SETTERS */
	public int getUnitNum(){
		return Integer.parseInt(unitNumLbl.getText());
	}
	
	public String getBilledTo(){
		return billedToLbl.getText();
	}
	
	public String getStatus(){
		return statusLbl.getText();
	}
	
	public Label getStatusLabel(){
		return statusLbl;
	}
	
	public Button getViewBtn(){
		return viewBtn;
	}
	
	public Button getPrintBtn(){
		return printBtn;
	}
	
	public void setStatus(String status){
		statusLbl.setText(status);
	}

	@Override
	public void update() {
		this.getChildren().clear();
		updateColor();
		initLayout();
	}
	
	public void updateColor(){
		if(unit.isPaid()){
			statusLbl.setId("paid");
		}
		else if(unit.isOverdue()){
			statusLbl.setId("overdue");
		}
		else{
			statusLbl.setId("unpaid");
		}
		
	}
}
