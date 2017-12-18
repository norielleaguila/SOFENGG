package views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import DB.DBaccess;
import controllers.UnitTabController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import models.Unit;
import models.UnitList;

/**
 * @author AGUILA, Norielle
 */
public class UnitTab extends Tabs{ 
	
	private UnitTable table;
	private UnitList model;
	private ToolBar toolBar;
	private UnitTabController controller;
	
	public interface OnSearchListener{
		public void onAction(String query);
	}
	
	public interface OnFilterListener{
		public void onAction(int which);
	}
	
	public UnitTab(){
		super();
		
		this.setSpacing(5);
		
		table = new UnitTable();
		
		initLayout();
	}
	
	public UnitTab(UnitList model, UnitTabController controller){
		super();
		
		this.setSpacing(5);
		
		table = new UnitTable();
		
		this.model = model;
		this.controller = controller;
		
		initLayout();
	}
	
	public void initLayout(){
		addRows();
		
		initSearchHBox();
		
		this.getChildren().add(toolBar);
		this.getChildren().add(table.getHeader());
		this.getChildren().add(table.getTableScroll());
	}
	
	public void initSearchHBox(){
		toolBar = new ToolBar();
	}
	
	public UnitTable getTable(){
		return table;
	}
	
	public ToolBar getToolBar(){
		return toolBar;
	}
	
	public void addRows(){
		for(Unit unit: model.getUnits()){
			table.addRow(unit);
		}
	}

	@Override
	public void update() {
//		this.getChildren().clear();
//		initLayout();
		table.update();
		
		for(Unit unit : model.getUnits()){
			table.updateRow(unit);
		}
	}
	
	public void search(String query){
		ArrayList<Unit> units = model.searchForUnits(query);
		
		if(units == null)
			units = model.getUnits();
		table.setRows(units);
		controller.setUpButtons();
	}
	
	public void filter(int which){
		ArrayList<Unit> units = model.filterUnits(which);
		
		table.setRows(units);
		controller.setUpButtons();
	}
	
	public void updateRow(Unit unit){
		table.updateRow(unit);
		controller.setUpButtons();
	}
	
	public class ToolBar extends HBox{
		private SearchBox searchBox;
		private FilterBox filterBox;
		private OnSearchListener onSearchListener;
		private OnFilterListener onFilterListener;
		
		public ToolBar(){
			super();
			init();
		}
		
		public void init(){
			getStylesheets().add("/style/searchbarStylesheet.css");
			
			setAlignment(Pos.CENTER_LEFT);
			setPadding(new Insets(10));
			setSpacing(10);
			
			initSearchBox();
			initFilterBox();
		}
		
		public void initSearchBox(){
			searchBox = new SearchBox();
			getChildren().add(searchBox);
		}
		
		public void initFilterBox(){		
			filterBox = new FilterBox();
			getChildren().add(filterBox);
		}
		
		public void setOnSearchListener(OnSearchListener onSearchListener){
			this.onSearchListener = onSearchListener;
		}
		
		public void setOnFilterListener(OnFilterListener onFilterListener){
			this.onFilterListener = onFilterListener;
		}
		
		private class FilterBox extends HBox {
			private Label filterLbl;
			private ToggleGroup filterTglGrp;
			private RadioButton[] radioBtnArr;	// index 0: all, index 1: paid, index 2: unpaid
			private String[] radioStringArr;
			
			public FilterBox(){
				super();
				
				radioStringArr = new String[]{"All", "Paid", "Unpaid"};
				this.init();
			}
			
			private void init(){
				setAlignment(Pos.CENTER);
				setSpacing(5);
				
				initFilterLbl();
				initFilterTglGrp();
				initRadioArr();
			}
			
			public void initFilterLbl(){
				filterLbl = new Label();
				filterLbl.setText("Filter");
				filterLbl.setId("filterLbl");
				getChildren().add(filterLbl);
			}
			
			public void initFilterTglGrp(){
				filterTglGrp = new ToggleGroup();
			}
			
			public void initRadioArr(){
				radioBtnArr = new RadioButton[radioStringArr.length];
				
				for(int i = 0; i < radioBtnArr.length; i++){
					radioBtnArr[i] = new RadioButton();
					radioBtnArr[i].setToggleGroup(filterTglGrp);
					radioBtnArr[i].setText(radioStringArr[i]);
					
					int temp = i;
					radioBtnArr[i].setOnAction(e -> {
						int which = temp;
						
						if(onFilterListener != null)
							onFilterListener.onAction(which);
					});
				}
				
				radioBtnArr[0].setSelected(true);
				
				getChildren().addAll(radioBtnArr);
			}
			
		}
		
		private class SearchBox extends HBox{
			private TextField searchTF;
			private Button searchBtn;
			private boolean searching;
			
			public SearchBox(){
				super();
				
				searching = false;
				
				this.init();
			}
			
			private void init(){
				setId("searchBox");
				
				setAlignment(Pos.CENTER);
				
				initSearchTF();
				initSearchBtn();
			}
			
			public void initSearchTF(){
				searchTF = new TextField();
				
				searchTF.setPromptText("Search");
				searchTF.setId("searchTF");
				
				searchTF.setMinHeight(30);
				
				searchTF.setOnKeyPressed(e -> {
					if(searchBtn != null){
						if(e.getCode() == KeyCode.ENTER){
							
							if(!searching){
								searching = true;
								searchBtn.setId("cancelBtn");
							}
							
							if(searchTF.getText().equals("") && searching){
								searching = false;
								searchBtn.setId("searchBtn");
							}
								
							search();
						}
					}
				});
				
				getChildren().add(searchTF);
			}
			
			public void initSearchBtn(){
				searchBtn = new Button("");
				
				searchBtn.setPrefSize(30, 30);
				
				searchBtn.setId("searchBtn");
				
				searchBtn.setOnAction(e -> {
					if(onSearchListener != null && searching){
						
						searchBtn.setId("searchBtn");
						searchTF.setText("");
						
						search();
						
						searching = !searching;
					}
				});
				
				getChildren().add(searchBtn);
			}
			
			public void search(){
				onSearchListener.onAction(searchTF.getText());
			}
			
		}
}

	public UnitList getUnitList() {
		// TODO Auto-generated method stub
		return model;
	}
}
