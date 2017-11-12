package views;

import java.util.ArrayList;

import controllers.CollectionTabController;
import controllers.UnitTabController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import models.FeeList;

/**
 * @author AGUILA, Norielle
 */

public class TabContainer extends TabPane{
	
	private ArrayList<Tab> tabs;
	private UnitTabController unitTabController;
	private CollectionTabController collectionTabController;
	
	public TabContainer(FeeList feesModel){
		super();
		
		unitTabController = new UnitTabController();
		collectionTabController = new CollectionTabController(feesModel);
				
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
