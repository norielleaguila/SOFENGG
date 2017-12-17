package views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DB.DBaccess;
import controllers.CollectionTabController;
import controllers.UnitTabController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.CollectionList;
import models.FeeList;
import models.Unit;
import models.UnitList;

/**
 * @author AGUILA, Norielle
 */

public class TabContainer extends TabPane implements ViewInterface{
	
	private ArrayList<Tab> tabs;
	private UnitTabController unitTabController;
	private CollectionTabController collectionTabController;
	private Stage window;
	
	public TabContainer(FeeList feesModel, UnitList unitsModel, CollectionList collectionModel, Stage window){
		super();
		this.window=window;
		unitTabController = new UnitTabController(unitsModel, collectionModel, window);
		collectionTabController = new CollectionTabController(this,feesModel, window);
				
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


	@Override
	public void update() {
		// TODO Auto-generated method stub
		unitTabController.reset();
	}
	
	
	
}
