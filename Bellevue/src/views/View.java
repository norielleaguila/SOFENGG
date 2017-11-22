package views;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.scene.Scene;

/**
 * @author AGUILA, Norielle
 */

public abstract class View {
	public static final String APP_NAME = "Bellevue Logger";
	
	public static double HEIGHT;
	public static double WIDTH;
	protected Scene scene;
	protected GraphicsDevice gd;
	
	protected abstract void initLayout();
	protected abstract void createElements();
	protected abstract void addToLayout();
	
	public abstract void resetLayout();
	
	public View(){

	}
	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public void initScreen(){
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		WIDTH = gd.getDisplayMode().getWidth();
		HEIGHT = gd.getDisplayMode().getHeight();
	}
	
}
