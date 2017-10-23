package views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Temporary alert dialog box for login
 * @author AGULIA, Norielle
 *
 */
public class LoginDialog {
	public static void create(String title, String message){
		Stage dialog = new Stage();
		
		VBox layout = new VBox(10);
		Label displayedMessage = new Label(message);
		Button confirm = new Button("CONFIRM");
		
		layout.getChildren().addAll(displayedMessage, confirm);
		
		confirm.setOnAction(e -> {
			dialog.close();
		});
		
		dialog.setTitle(title);
	}
}
