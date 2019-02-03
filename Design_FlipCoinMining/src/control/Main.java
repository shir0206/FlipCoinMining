package control;

import boundary.ViewLogic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static BlockLogic blockLogic;

	public static void main(String[] args) {
		if (blockLogic == null) {
			System.out.println("Block logic is created");
			blockLogic = BlockLogic.getInstance();
		}
		launch(args);
	}

	public void start(Stage primaryStage) {
		ViewLogic.initUI();
	}

}
