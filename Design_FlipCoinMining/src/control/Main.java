package control;

import boundary.ViewLogic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static DataLogic blockLogic;

	public static void main(String[] args) {
		if (blockLogic == null) {
			System.out.println("Block logic is created");
			blockLogic = DataLogic.getInstance();
		}
		launch(args);
	}

	public void start(Stage primaryStage) {
		ViewLogic.initUI();
	}

}
