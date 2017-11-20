package views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controllers.CollectionTabController;
import controllers.UnitTabController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
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
