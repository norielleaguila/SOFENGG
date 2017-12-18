package views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import models.CollectionList;
import models.Fee;
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
	private CollectionList collectionModel;
	private TabContainer tabContainer;
	private FlowPane header;
	private Button logoutBtn;
	private Stage window;
	private StackPane tabs_date;
	private Label currTime;
	
	public ProgramScreen(FeeList feesModel, UnitList unitsModel, CollectionList collectionModel, Stage window){
		super();
		Fee.initType();
		this.feesModel = feesModel;
		this.unitsModel = unitsModel;
		this.collectionModel = collectionModel;
		this.window = window;
		
		initLayout();
		
		initScreen();
		
		scene = new Scene(layout, WIDTH, HEIGHT);
	}

	@Override
	protected void initLayout() {
		layout = new BorderPane();
		currTime = new Label();
		createElements();
		addToLayout();
	}

	@Override
	protected void createElements() {
		tabContainer = new TabContainer(feesModel, unitsModel, collectionModel, window);
		tabs_date=new StackPane();
		
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
	        currTime.setText(getcurrentDate());
	    }),
	         new KeyFrame(Duration.seconds(1))
	    );
	    clock.setCycleCount(Animation.INDEFINITE);
	    clock.play();
	    
	    currTime.setStyle("-fx-border-color: transparent;-fx-text-fill:white;-fx-font:23px 'Segoe UI';"
				+ "-fx-font-weight: bold; -fx-padding: 5px; -fx-border-insets: 5px;-fx-background-insets: 5px;");
		tabs_date.getChildren().addAll(tabContainer,currTime);
		tabs_date.setAlignment(currTime,Pos.TOP_RIGHT);
		
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
	private String getcurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void initHeader(){
		header = new FlowPane(Orientation.VERTICAL);
		header.setColumnHalignment(HPos.RIGHT); // align labels on left
		header.setPrefWrapLength(30); // preferred height = 200
		header.setAlignment(Pos.TOP_RIGHT);
		header.setPadding(new Insets(20));
		header.setStyle("-fx-background-color: linear-gradient(to top right, #EF6461 0%, #EF6461 70%, #f7bb97 100%)");
		
		logoutBtn = new Button("LOGOUT");
		logoutBtn.setStyle("-fx-background-color:#A6BC3F;-fx-text-fill:white;-fx-font:15px 'Segoe UI';");
		logoutBtn.setPrefSize(100, 30);
		
		logoutBtn.setId("logout");
		
		header.getChildren().addAll(logoutBtn);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
