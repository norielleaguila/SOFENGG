package views;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import models.FeeList;
import models.UnitList;

/**
 * ProgramScreen is the view that contains all necessary information.
 * @author AGUILA, Norielle
 */

public class ProgramScreen extends View{
	
	private BorderPane layout;
	private FeeList feesModel;
	private UnitList unitsModel;
	private TabContainer tabContainer;
	private FlowPane header;
	private Button logoutBtn;
	
	public ProgramScreen(FeeList feesModel, UnitList unitsModel){
		super();
		
		this.feesModel = feesModel;
		this.unitsModel = unitsModel;
		
		initLayout();
		
		initScreen();
		
		scene = new Scene(layout, WIDTH, HEIGHT);
	}

	@Override
	protected void initLayout() {
		layout = new BorderPane();
		
		createElements();
		
		addToLayout();
	}

	@Override
	protected void createElements() {
		tabContainer = new TabContainer(feesModel, unitsModel);
		
		initHeader();
	}

	@Override
	protected void addToLayout() {
		layout.setCenter(tabContainer);
		layout.setTop(header);
	}

	@Override
	public void resetLayout() {
		
	}
	
	public BorderPane getLayout(){
		return layout;
	}
	
	public TabContainer getTabContainer(){
		return tabContainer;
	}
	
	public Button getLogoutBtn(){
		return logoutBtn;
	}
	
	public void initHeader(){
		header = new FlowPane(Orientation.VERTICAL);
		header.setColumnHalignment(HPos.RIGHT); // align labels on left
		header.setPrefWrapLength(30); // preferred height = 200
		header.setAlignment(Pos.TOP_RIGHT);
		header.setPadding(new Insets(20));
		
		logoutBtn = new Button("LOGOUT");
		logoutBtn.setPrefSize(100, 30);
		
		header.getChildren().add(logoutBtn);
	}

}
