package view;

import java.util.ArrayList;
import java.util.List;

import controller.UnitRowController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import model.UnitModel;
import model.UnitModel.UnitContainer;
import model.beans.Unit;

public class UnitsTable extends VBox implements View{
	private TableHeader tableHeader;
	private UnitScroll unitListScrollPane;
	private List<Unit> unitList;
	private UnitRowController unitRowController;

	public interface OnActionListener{
		public void onAction(int button, String unitNum);	// 1 - view, 2 - print
	}
	
	public UnitsTable(UnitRowController unitRowController){
		super();
		
		this.unitRowController = unitRowController;
		this.unitList = new ArrayList<>();
		
		init();
	}
	
	public UnitsTable(ArrayList<Unit> unitList){
		super();
		
		this.unitList = unitList;
		
		init();
	}
	
	public void init(){
		getStylesheets().add("/style/unitTableStylesheet.css");
		setFillWidth(true);
		
		initHeader();
	}
	
	public void initHeader(){
		tableHeader = new TableHeader();
		
		getChildren().add(tableHeader);
	}
	
	public void initUnitListScroll(){
		unitListScrollPane = new UnitScroll();
		
		getChildren().add(unitListScrollPane);
	}
	
	public void setUnitList(List<Unit> list){
		this.unitList = list;
		unitListScrollPane.resetList();
	}
	
	public UnitScroll getScrollPane(){
		return unitListScrollPane;
	}
	
	public class TableHeader extends HBox{
		private Label statusLbl;
		private Label unitNumLbl;
		private Label actionsLbl;
		
		public TableHeader(){
			super();
			init();
		}
		
		private void init(){
			setSpacing(Size.TABLE_SPACING);
			setAlignment(Pos.CENTER);
			
			setId("tableHeader");
			
			initStatusLbl();
			initUnitNumLbl();
			initActionsLbl();
		}
		
		private void initStatusLbl(){
			statusLbl = new Label();
			statusLbl.setText("Status");
			statusLbl.setAlignment(Pos.CENTER);
			statusLbl.setMinWidth(30);
			statusLbl.setPadding(new Insets(0, 15, 0, 0));
			statusLbl.setId("x");
			
			getChildren().add(statusLbl);
		}
		
		private void initUnitNumLbl(){
			unitNumLbl = new Label();
			unitNumLbl.setText("Unit No.");
			unitNumLbl.setAlignment(Pos.CENTER);
			unitNumLbl.setPadding(new Insets(0, 30, 0, 0));
			unitNumLbl.setMinWidth(Size.LBL_PREF_WIDTH);
			unitNumLbl.setMaxWidth(Size.LBL_PREF_WIDTH);
			
			unitNumLbl.setId("x");
			
			getChildren().add(unitNumLbl);
		}
		
		private void initActionsLbl(){
			actionsLbl = new Label();
			actionsLbl.setText("Actions");
			actionsLbl.setAlignment(Pos.CENTER);
			actionsLbl.setPadding(new Insets(0, 40, 0, 0));
			actionsLbl.setMinWidth(Size.BTN_PREF_WIDTH * 2 + Size.TABLE_SPACING);
			actionsLbl.setMaxWidth(Size.BTN_PREF_WIDTH * 2 + Size.TABLE_SPACING);
			actionsLbl.setId("x");
			
			getChildren().add(actionsLbl);
		}
	}
	
	public class UnitScroll extends ScrollPane{
		private VBox unitListVBox;
		private List<UnitRow> unitRowList;
		
		public UnitScroll(){
			super();
			this.init();
		}
		
		public void resetList(){
			unitRowList = new ArrayList<UnitRow>();
			
			for(Unit unit: unitList){
				UnitRow row = new UnitRow(unit.getUnitNo());
				unitRowController.addListener(row);
				unitRowList.add(row);
			}
			
			unitListVBox.getChildren().clear();
			unitListVBox.getChildren().addAll(unitRowList);
		}
		
		private void init(){
			this.setFitToWidth(true);
			
			initUnitListVBox();
			initUnitRowList();
		}
		
		private void initUnitListVBox(){
			unitListVBox = new VBox();
			unitListVBox.setSpacing(0);
			
			this.setContent(unitListVBox);
		}
		
		private void initUnitRowList(){
			unitRowList = new ArrayList<UnitRow>();
			
			for(Unit unit: unitList){
				UnitRow row = new UnitRow(unit.getUnitNo());
				unitRowController.addListener(row);
				unitRowList.add(row);
			}
			
			unitListVBox.getChildren().addAll(unitRowList);
		}
	}

	@Override
	public void update() {
		
	}
}
