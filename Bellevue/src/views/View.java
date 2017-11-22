package views;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javafx.scene.Scene;

/**
 * @author AGUILA, Norielle
 */

public abstract class View implements ViewInterface{
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
		
		Rectangle bounds = gd.getDefaultConfiguration().getBounds();
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gd.getDefaultConfiguration());
		
		Rectangle safeBounds = new Rectangle(bounds);
		safeBounds.x += insets.left;
		safeBounds.y += insets.top;
		safeBounds.width -= (insets.left + insets.right);
		safeBounds.height -= (insets.top + insets.bottom);
		
		WIDTH = gd.getDisplayMode().getWidth();
		HEIGHT = gd.getDisplayMode().getHeight() ;
	}
	
}
