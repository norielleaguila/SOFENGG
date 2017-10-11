package views;

import javafx.scene.Scene;

public abstract class View {
	protected Scene scene;
	
	protected abstract void setUpLayout();
	protected abstract void createElements();
	protected abstract void addToLayout();
	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
}
