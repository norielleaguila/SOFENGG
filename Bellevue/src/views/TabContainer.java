package views;

import java.util.ArrayList;

import controllers.CollectionTabController;
import controllers.UnitTabController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import models.FeeList;
import models.UnitList;

/**
 * @author AGUILA, Norielle
 */

public class TabContainer extends TabPane{
	
	private ArrayList<Tab> tabs;
	private UnitTabController unitTabController;
	private CollectionTabController collectionTabController;
	
	public TabContainer(FeeList feesModel, UnitList unitsModel, Stage window){
		super();
		
		unitTabController = new UnitTabController(unitsModel, window);
		collectionTabController = new CollectionTabController(feesModel, window);
				
		initTabs();
		
		this.getStylesheets().add("style.css");
	}
	
	public void initTabs(){
		tabs = new ArrayList<>();
		
		tabs.add(new Tab("UNITS", unitTabController.getView()));
		tabs.add(new Tab("COLLECTION", collectionTabController.getView()));
		
		for(int i = 0; i < tabs.size(); i++){
			tabs.get(i).setClosable(false);
		}
		
		this.getTabs().addAll(tabs);
	}
	
}
