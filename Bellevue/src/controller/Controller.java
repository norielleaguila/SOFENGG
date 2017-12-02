package controller;

import javafx.scene.*;
import view.*;

public abstract class Controller <T extends Parent & View> {
	
	protected T view;
	protected MainController mainController;
	
	protected Controller (MainController mainController) {
		this.mainController = mainController;
		initView ();
	}
	
	protected abstract void initView ();
	
	public void setAsScene () {
		mainController.setScene (view);
	}
	
}