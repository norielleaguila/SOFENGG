package views;

<<<<<<< HEAD
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
=======
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import models.Unit;
import models.UnitList;

/**
 * @author AGUILA, Norielle
 */
public class UnitTab extends Tabs{ 
	
	private UnitTable table;
	private UnitList model;
	private Pane toolBar;
	
	public UnitTab(){
		super();
		
		this.setSpacing(5);
		
		table = new UnitTable();
		
		initLayout();
	}
	
	public UnitTab(UnitList model){
		super();
		
		this.setSpacing(5);
		
		table = new UnitTable();
		
		this.model = model;
		
		initLayout();
	}
	
	public void initLayout(){
		addRows();
		
		this.getChildren().add(table.getHeader());
		this.getChildren().add(table.getTableScroll());
	}
	
	public void initSearchHBox(){
		toolBar = new Pane();
	}
	
	public UnitTable getTable(){
		return table;
	}
	
	public void addRows(){
		for(Unit unit: model.getUnits()){
			table.addRow(unit);
		}
	}

	@Override
	public void update() {
//		this.getChildren().clear();
		
		for(Unit unit : model.getUnits()){
			table.updateRow(unit);
		}
		
//		initLayout();
	}
	
	public void updateRow(Unit unit){
		table.updateRow(unit);
	}
	
	public class ToolBar extends Pane{
		private TextField searchTF;
		private Button searchBtn;
		private ToggleGroup filterGroup;
		private Toggle allToggle;
		private Toggle paidToggle;
		private Toggle unpaidToggle;
		
		private Toggle currentlyToggled;
		
		private int numLeftChild = 0;
		private int numRightChild = 0;
		
		private static final double CHILD_PADDING = 20;
		
		public ToolBar(){
			
		}
		
		public ToolBar(Toggle currentlyToggled){
			this.currentlyToggled = currentlyToggled;
		}
		
		public void initLayout(){
			initSearch();
		}
		
		private void initSearch(){
			int tfWidth = 200;
			int btnWidth = 100;
			
			searchTF = new TextField();
			
			numLeftChild++;
			
			searchTF.setMinWidth(tfWidth);
			searchTF.setMaxWidth(tfWidth);
			
			searchTF.setLayoutX(CHILD_PADDING * numLeftChild); 
			
			searchBtn = new Button("Search");
			
			numLeftChild++;
			
			searchBtn.setMinWidth(btnWidth);
			searchBtn.setMaxWidth(btnWidth);
			
			searchTF.setLayoutX(CHILD_PADDING * numLeftChild);
			
			searchBtn.setOnAction(e -> {
				// something
			});
			
			getChildren().addAll(searchTF, searchBtn);
		}
		
		private void initFitler(){
			
		}
		
	public UnitList getUnitList(){
		return model;
	}
	public void initTimer(){
		/*Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) > 15){
				for(Unit unit : model.getUnits()){
					if(!unit.isPaid()){
						unit.setOverdue(true);
						DB.DBaccess.changeStatus(model.getUnit(unit.getUnitNo()));
					}
				}
				update();
			}
			else if(Integer.parseInt(java.time.LocalDateTime.now().toString().split("T")[0].split("-")[2]) == 1){
				for(Unit unit : model.getUnits()){
					if(unit.isPaid()){
						unit.setPaid(false);
						DB.DBaccess.changeStatus(model.getUnit(unit.getUnitNo()));
					}
				}
				update();
			}
	    }),
	        new KeyFrame(Duration.seconds(1))
	    );
	    clock.setCycleCount(Animation.INDEFINITE);
	    clock.play();*/
	}
	
}
