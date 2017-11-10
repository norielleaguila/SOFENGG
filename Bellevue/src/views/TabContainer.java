package views;

import java.util.ArrayList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import models.FeeList;

/**
 * @author AGUILA, Norielle
 */

public class TabContainer extends TabPane{
	
	private ArrayList<Tab> tabs;
	FeeList feesModel;
	
	public TabContainer(FeeList feesModel){
		super();
		
		this.feesModel = feesModel;
				
		
		initTabs();
		
		this.getStylesheets().add("style.css");
	}
	
	public void initTabs(){
		tabs = new ArrayList<>();
		
		tabs.add(new Tab("UNITS", new UnitTab()));
		tabs.add(new Tab("COLLECTION", new CollectionTab(feesModel)));
		
		for(int i = 0; i < tabs.size(); i++){
			tabs.get(i).setClosable(false);
			
		}
		
		this.getTabs().addAll(tabs);
	}
	
}
