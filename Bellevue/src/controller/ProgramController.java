package controller;

import javafx.scene.control.Tab;
import view.ProgramView;
import view.UnitTab;

public class ProgramController extends Controller<ProgramView, ApplicationController> {

	protected UnitTabController unitTabController;
	protected CollectionTabController collectionTabController;
	
	protected ProgramController(ApplicationController mainController) {
		super(mainController);
	}

	@Override
	protected void initView() {
		unitTabController = new UnitTabController (mainController);
		collectionTabController = new CollectionTabController (mainController);

		view = new ProgramView ();

		view.addTab (UnitTabController.TAB_NAME, unitTabController.view);
		view.addTab (CollectionTabController.TAB_NAME, collectionTabController.view);
	}

}
