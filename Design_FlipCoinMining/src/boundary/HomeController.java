package boundary;

import java.io.IOException;

import entity.Consts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController {

	private String currentMinerAddress = Consts.currentMinerAddress; //the current miner that  is logged in
	private boolean isWorker = Consts.isWorker;
	
	@FXML
	private Button btn_lotteries;

	@FXML
	private Button btn_profile;

	@FXML
	private Button btn_miners;

	@FXML
	private Button btn_blocks;

	@FXML
	private Button btn_riddles;

	/**
	 * This method opens 'Block' window from 'Home' window
	 */
	@FXML
	void openBlocksWindow(MouseEvent event) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Block.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));  
		stage.setTitle("Flip Coin Mining - Blocks");
		stage.show();
	}

	/**
	 * This method opens 'Lotteries' window from 'Home' window
	 */
	@FXML
	void openLotteriesWindow(MouseEvent event) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lotteries.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));  
		stage.setTitle("Flip Coin Mining - Lotteries");
		stage.show();
	}

	/**
	 * This method opens 'Miners' window from 'Home' window
	 */
	@FXML
	void openMinersWindow(MouseEvent event) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OtherMiners.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));  
		stage.setTitle("Flip Coin Mining - Miners");
		stage.show();
	}

	/**
	 * This method opens 'Profile' window from 'Home' window
	 */
	@FXML
	void openProfileWindow(MouseEvent event) throws IOException { 

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Profile.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));  
		stage.setTitle("Flip Coin Mining - Profile");
		stage.show();
	}
	
	/**
	 * This method opens 'Riddles' window from 'Home' window
	 */
	@FXML
	void openRiddlesWindow(MouseEvent event) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Riddles.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));  
		stage.setTitle("Flip Coin Mining - Riddles");
		stage.show();
	}

}
