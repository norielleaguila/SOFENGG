package views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
	private Stage window;
	private StackPane tabs_date;
	
	public ProgramScreen(FeeList feesModel, UnitList unitsModel, Stage window){
		super();
		
		this.feesModel = feesModel;
		this.unitsModel = unitsModel;
		this.window = window;
		
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
		tabContainer = new TabContainer(feesModel, unitsModel, window);
		tabs_date=new StackPane();
		Label t=getcurrentDate();
		t.setStyle("-fx-border-color: transparent;-fx-text-fill:white;-fx-font:23px 'Segoe UI';"
				+ "-fx-font-weight: bold; -fx-padding: 5px; -fx-border-insets: 5px;-fx-background-insets: 5px;");
		tabs_date.getChildren().addAll(tabContainer,t);
		tabs_date.setAlignment(t,Pos.TOP_RIGHT);
		
		initHeader();
	}

	@Override
	protected void addToLayout() {
		layout.setCenter(tabs_date);
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
	private Label getcurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
		Date date = new Date();
		return new Label(dateFormat.format(date));
	}
	public void initHeader(){
		header = new FlowPane(Orientation.VERTICAL);
		header.setColumnHalignment(HPos.RIGHT); // align labels on left
		header.setPrefWrapLength(30); // preferred height = 200
		header.setAlignment(Pos.TOP_RIGHT);
		header.setPadding(new Insets(20));
		header.setStyle("-fx-background-color:#FC9586;");
		
		logoutBtn = new Button("LOGOUT");
		logoutBtn.setStyle("-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-font:15px 'Segoe UI';");
		logoutBtn.setPrefSize(100, 30);
		header.getChildren().addAll(logoutBtn);
		
	}

}
