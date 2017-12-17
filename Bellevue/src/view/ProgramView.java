package view;

import javafx.scene.*;
import javafx.scene.control.*;

import java.util.*;

public class ProgramView extends TabPane implements View{

	private List<Tab> tabs;

	public ProgramView(){
		super();
		init();
	}
	
	private void init(){
		getStylesheets().add("style/style.css");
		tabs = new ArrayList<> ();
	}

	public void addTab (String name, Parent view) {
		Tab tab = new Tab (name, view);
		tab.setClosable (false);

		getTabs ().add (tab);
	}

	@Override
	public void update() {
		
	}

}
