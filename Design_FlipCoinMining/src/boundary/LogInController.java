package boundary;

import java.io.IOException;
import java.util.ArrayList;

import control.DataLogic;
import entity.BusinessCompany;
import entity.Consts;
import entity.Miner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LogInController {

	private ArrayList<Miner> allMiners = DataLogic.getInstance().getAllMiners();


	@FXML
	private TextField tf_address;

	@FXML
	private TextField tf_password;

	@FXML
	private Button btn_logIn;



	/**
	 * This method opens 'Home' window
	 * @throws IOException 
	 */

	@FXML
	void logIn(MouseEvent event) throws IOException {
		System.out.println("Initialize " + this.getClass().getName() + " window");

		String minerAddress = tf_address.getText();
		String minerPassword = tf_password.getText();

		// check if user is an admin
		if (minerAddress.equals("admin") && minerPassword.equals("admin")) {
			Consts.currentMinerAddress = "admin";
			Consts.isWorker = true;
			setEmpHome();

		}

		// check if user is a miner
		else {

			boolean correctAddress = false;
			boolean correctPassword = false;

			for (Miner m : allMiners) {

				// check address
				if (m.getUniqueAddress().equals(minerAddress)) {
					correctAddress = true;
					
					// check password
					if (m.getPassword().equals(minerPassword)) {
						correctPassword = true;
						Consts.currentMinerAddress = minerAddress;
						Consts.isWorker = false;

						setHome();
						break;
					}
				}
			}

			if (!correctAddress) 
				incorrectAddress();
			if (!correctPassword)
				incorrectPassword();
		}
	}




	void incorrectAddress() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Wrong address");
		alert.setContentText("Your addres is incorrect");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.showAndWait();
	}

	void incorrectPassword() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Wrong password");
		alert.setContentText("Your password is incorrect");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.showAndWait();
	}


	void setEmpHome() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmpHome.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));  
		stage.setTitle("Flip Coin Mining (Administrator)");
		stage.show();
	}


	void setHome() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));  
		stage.setTitle("Flip Coin Mining");
		stage.show();
	}

}
