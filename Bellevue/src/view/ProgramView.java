package view;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.*;

public class ProgramView extends BorderPane implements View{

	private TabPane tabPane;
	private List<Tab> tabs;

	public ProgramView(){
		super();
		init();
	}
	
	private void init(){
		getStylesheets().add("style/style.css");
		tabs = new ArrayList<> ();
		tabPane = new TabPane ();
		setCenter (tabPane);
		initTop ();
	}

	private void initTop () {
		BorderPane bp = new BorderPane ();
		VBox vBox = new VBox ();

		Button logout = new Button("LOGOUT");

		Label date = new Label ();

//		String d =


	}

	public void addTab (String name, Parent view) {
		Tab tab = new Tab (name, view);
		tab.setClosable (false);

		tabPane.getTabs ().add (tab);
	}

	@Override
	public void update() {
		
	}

}
