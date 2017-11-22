package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * @author AGUILA, Norielle
 */
public abstract class Tabs extends VBox{

	public Tabs(){
		super(20);
		
		this.setMinHeight(300);
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		this.setAlignment(Pos.TOP_CENTER);
		this.setPadding(new Insets(20, 20, 20, 20));
		
		this.getStylesheets().add("style.css");
	}
	
}
