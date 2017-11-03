package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * @author AGUILA, Norielle
 */
public class UnitRow extends HBox {
	
	public static int rowNum = 0;

	private Label unitNumLbl;
	private Label billedToLbl;
	private Label statusLbl;
	
	private Button viewBtn;
	private Button printBtn;
	
	public UnitRow(int unitNum, String billedTo, String status){
		super(20);
		
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(5));
		
		rowNum++;
		
		unitNumLbl = new Label(unitNum + "");
		billedToLbl = new Label(billedTo);
		statusLbl = new Label(status);
		
		this.getStylesheets().add("style.css");
		
		initLayout();
		
	}
	
	public void initStatus(){
		statusLbl.setId("statusLbl");
		
		statusLbl.setMinSize(30, 30);
		statusLbl.setMaxSize(30, 30);
	}
	
	public void initUnitNum(){
		unitNumLbl.setId("unitRowLbl");
		
		unitNumLbl.setMinWidth(100);
		unitNumLbl.setMaxWidth(100);
	}
	
	public void initBilledTo(){
		billedToLbl.setId("unitRowLbl");
		
		billedToLbl.setMinWidth(500);
		billedToLbl.setMaxWidth(500);
	}
	
	public void initButtons(){
		viewBtn = new Button("View");
		printBtn = new Button("Print");
		
		viewBtn.setMinWidth(150);
		viewBtn.setMaxWidth(150);
		viewBtn.setId("viewBtn");
		
		printBtn.setMinWidth(150);
		printBtn.setMaxWidth(150);
		printBtn.setId("printBtn");
		
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
	
	/* GETTERS & SETTERS */
	public int getUnitNum(){
		return Integer.parseInt(unitNumLbl.getText());
	}
	
	public String getBilledTo(){
		return billedToLbl.getText();
	}
	
	public String getStats(){
		return statusLbl.getText();
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
}
