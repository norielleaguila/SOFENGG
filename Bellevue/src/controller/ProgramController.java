package controller;

import view.ProgramView;

public class ProgramController  extends Controller<ProgramView, ApplicationController> {

	protected ProgramController(ApplicationController mainController) {
		super(mainController);
	}

	@Override
	protected void initView() {
		view = new ProgramView(MainController.mainStage);
	}

}
