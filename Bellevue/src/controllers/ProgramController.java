package controllers;

import com.sun.prism.paint.Color;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import views.ProgramScreen;
import views.UnitList;
import views.UnitTab;

/**
 * @author AGUILA, Norielle
 */

public class ProgramController extends Controller{
	
	public ProgramController(ProgramScreen view, Stage window){
		this.view = view;
		this.window = window;
		this.setScene(view.getScene());
		this.window.setFullScreen(true);
	}

	@Override
	public void setUpButtons() {
		ProgramScreen view = (ProgramScreen) this.view;
		UnitTab tab = (UnitTab) view.getTabContainer().getTabs().get(0).getContent();
		UnitList units = (UnitList) tab.getTable().getTableScroll().getContent();
		Label unit = (Label) units.getRow(0).getChildren().get(2);
		Button viewBtn = (Button) units.getRow(0).getChildren().get(3);
		viewBtn.setOnAction(e -> {
			viewUnitInfo();
		});
		System.out.println(viewBtn.getText());
		System.out.println(unit.getText());
	}
	/**
	 * @author SECUYA, Alfonso
	 */
	public void viewUnitInfo(){
		FlowPane unitInfoPane = new FlowPane();
		HBox unitHeader = new HBox();
		Label testLabel = new Label();
		testLabel.setText("lol");
		Scene testScene;
		unitInfoPane.getChildren().addAll(testLabel);
		
		testScene = new Scene(unitInfoPane,500,500);
		Stage testStage = new Stage();
		testStage.setScene(testScene);
		testStage.initModality(Modality.NONE);
		testStage.show();
		
		/*FlowPane unitInfoPane = new FlowPane();
		Label testLabel = new Label();
		testLabel.setText("lol");
		unitInfoPane.getChildren().addAll(testLabel);
		unitInfoPane.setStyle("-fx-background-fill:black;");
		
		Popup popup = new Popup();
        popup.setX(300);
        popup.setY(200);
        popup.getContent().addAll(unitInfoPane);
        popup.show(window);*/
	}

}
