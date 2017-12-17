package view;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CollectionTab extends BorderPane implements View{

	private VBox actionsVBox;
	private ScrollPane tablesScroll;
	private CollectionTablesView tablesView;
	
	private Button actionBtn;
	private ContextMenu actionContextMenu;

	public CollectionTab(){
		super();
		init();
	}
	
	public void init(){
		initActionsVBox();
		initTablesScroll();

		getStylesheets().add("style/collectionTabStylesheet.css");
		
		setRight(actionsVBox);
	}
	
	public void initActionsVBox(){
		actionsVBox = new VBox();
		
		actionsVBox.setAlignment(Pos.BOTTOM_CENTER);
		
		actionsVBox.setId("actionsVBox");
		
		initActionContextMenu();
		initActionBtn();
	}
	
	public void initActionBtn(){
		actionBtn = new Button();
		actionBtn.setText("+");
		
		actionBtn.setId("actionBtn");
		
		actionBtn.setMinSize(50, 50);
		actionBtn.setMaxSize(50, 50);
		
		actionBtn.setContextMenu(actionContextMenu);
		
		actionBtn.setOnAction(e -> {
			actionContextMenu.show(actionBtn, Side.TOP, actionBtn.getTranslateX(), actionBtn.getTranslateY());
		});
		
		actionsVBox.getChildren().add(actionBtn);
	}
	
	public void initActionContextMenu(){
		actionContextMenu = new ContextMenu();
		
		actionContextMenu.getItems().addAll(createMenuItems());
	}
	
	public void initTablesScroll(){
		tablesScroll = new ScrollPane ();
		tablesScroll.setFitToWidth(true);
		tablesScroll.setId("scrollPane");
		
		tablesScroll.setOnScroll(e -> {
			double deltaY = e.getDeltaY()*10; // *6 to make the scrolling a bit faster
			double width = tablesScroll.getContent().getBoundsInLocal().getWidth();
			double vvalue = tablesScroll.getVvalue();
			tablesScroll.setVvalue(vvalue + -deltaY/width);
		});
		
		initCollectionTables ();
		setCenter (tablesScroll);
	}
	
	public void initCollectionTables(){
		tablesView = new CollectionTablesView ();
		tablesScroll.setContent (tablesView);
	}
	
	public List<MenuItem> createMenuItems(){
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		String[] menuText = new String[]{"ADD COLLECTION ITEM", "ADD NEW CATEGORY", 
										 "EDIT ITEM", "EDIT CATEGORY", 
										 "DELETE COLLECTION ITEM", "DELETE CATEGORY"};

		for(String text : menuText)
			menuItems.add(new MenuItem(text));
		
		return menuItems;
	}
	
	@Override
	public void update() {

		
		
	}

}
