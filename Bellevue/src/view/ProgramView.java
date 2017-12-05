package view;

import java.util.ArrayList;
import java.util.List;

import controller.MainController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import view.popup.ViewUnitPopup;

public class ProgramView extends TabPane implements View{
	
	private List<Tab> tabs;
	private Tab unitTab;
	private Tab collectionTab;
	
	public ProgramView(Stage window){
		super();
		
		init();

		
	}
	
	private void init(){
		
		initTabs();
		
		getStylesheets().add("stylesheets/style.css");
		
	}
	
	private void initTabs(){
		tabs = new ArrayList<Tab>();
		
		// create individual tabs
		unitTab = new Tab("UNIT", new UnitTab());
		collectionTab = new Tab("COLLECTION");
		
		tabs.add(unitTab);
		tabs.add(collectionTab);
		
		for(int i = 0; i < tabs.size(); i++){
			tabs.get(i).setClosable(false);
		}
		
		getTabs().addAll(tabs);
	}
	
	@Override
	public void update() {
		
	}
	
}
