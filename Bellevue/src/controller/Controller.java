package controller;

import javafx.scene.*;
import view.*;

public abstract class Controller <T extends Parent & View, E extends MainController> {
	
	protected T view;
	protected E mainController;
	
	protected Controller (E mainController) {
		this.mainController = mainController;
		initView ();
	}
	
	protected abstract void initView ();
	
	public void setAsScene () {
		mainController.setScene (view);
	}
	
}