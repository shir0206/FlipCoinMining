package boundary;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EmpHomeController {

	@FXML
	private Button btn_riddles;

	@FXML
	private Button btn_lotteries;

	@FXML
	private Button btn_bonuses;

	/**
	 * This method opens 'Lotteries' window from 'Home' window
	 */
	@FXML
	void openEmpBonusesWindow(MouseEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmpBonuses.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.setTitle("Flip Coin Mining - Bonuses (Administrator)");
		stage.show();
	}

	/**
	 * This method opens 'Lotteries' window from 'Home' window
	 */
	@FXML
	void openEmpLotteriesWindow(MouseEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmpLotteries.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.setTitle("Flip Coin Mining - Lotteries (Administrator)");
		stage.show();
	}

	/**
	 * This method opens 'Lotteries' window from 'Home' window
	 */
	@FXML
	void openEmpRiddlesWindow(MouseEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmpRiddles.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.setTitle("Flip Coin Mining - Riddles (Administrator)");
		stage.show();
	}

}
