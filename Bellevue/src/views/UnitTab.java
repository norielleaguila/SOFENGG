package views;

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
	private HBox searchHBox;
	
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
		searchHBox = new HBox(10);
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
