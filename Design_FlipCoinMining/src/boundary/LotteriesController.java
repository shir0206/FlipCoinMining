package boundary;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import control.LotteryLogic;
import entity.Consts;
import entity.Lottery;
import entity.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

public class LotteriesController {

	private String currentMinerAddress = Consts.currentMinerAddress; // the current miner that is logged in
	private HashMap<String, Lottery> allLotteries = LotteryLogic.getInstance().getAllLotteriesHM();
	private ArrayList<Participant> allParticipants = LotteryLogic.getInstance().getAllParticipants();

	@FXML
	private TextField tf_number;

	@FXML
	private TextField tf_maxParticipants;

	@FXML
	private TextField tf_winners;

	@FXML
	private TextField tf_bonusesNum;

	@FXML
	private TextField tf_date;

	@FXML
	private TextField tf_regParticipants;

	@FXML
	private Button btn_select;

	@FXML
	private TableView<Lottery> tbl_allLotteries;

	@FXML
	private TableColumn<Lottery, String> col_allLotteries_number;

	@FXML
	private TableColumn<Lottery, Date> col_allLotteries_date;

	@FXML
	private TableColumn<Lottery, Integer> col_allLotteries_maxParticipants;

	@FXML
	private TableColumn<Lottery, Integer> col_allLotteries_numberOfWinners;

	@FXML
	private TableColumn<Lottery, Integer> col_allLotteries_numberOfBonuses;

	@FXML
	private Button btn_signUp;

	@FXML
	private Button btn_cancel;

	public void initialize() {

		System.out.println("Initialize " + this.getClass().getName() + " window");

		// setting lotteries table
		col_allLotteries_number.setCellValueFactory(new PropertyValueFactory<>("lotteryNumber"));
		col_allLotteries_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_allLotteries_maxParticipants.setCellValueFactory(new PropertyValueFactory<>("maxParticipants"));
		col_allLotteries_numberOfWinners.setCellValueFactory(new PropertyValueFactory<>("numberOfWinners"));
		col_allLotteries_numberOfBonuses.setCellValueFactory(new PropertyValueFactory<>("numberOfBonuses"));

		setAllLotteriesTable();

	}

	/**
	 * Set lottery table
	 */
	private void setAllLotteriesTable() {
		ObservableList<Lottery> lotteries = FXCollections.observableArrayList();
		lotteries.setAll(LotteryLogic.getInstance().getAllLotterys());
		tbl_allLotteries.setItems(lotteries);
		tbl_allLotteries.refresh();
	}

	/**
	 * cancel lottery
	 */
	@FXML
	void cancelLottery() {
		String lotteryNumber = tbl_allLotteries.getSelectionModel().getSelectedItem().getLotteryNumber();

		boolean regToThisLottery = false;

		// Check if user already registered
		for (Participant p : allParticipants) {
			if (p.getLotteryNumber().equals(lotteryNumber) && p.getUniqueAddress().equals(currentMinerAddress)) {
				regToThisLottery = true;
				break;
			}
		}

		// Check if already registered
		if (regToThisLottery == false) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Not registred!");
			alert.setContentText("You are not registred");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		// cancel registration
		else if (regToThisLottery == true) {

			if (LotteryLogic.getInstance().removeParticipant(lotteryNumber, currentMinerAddress)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Seccessfully signed up!");
				alert.setContentText("Seccessfully signed up");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();

				watchLotteryDetails();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Failed to cancel registration!");
				alert.setContentText("Faild to cancel registration");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();
			}
		}

	}

	/**
	 * Sign up lottery
	 */
	@FXML
	void signUpLottery() {

		String lotteryNumber = tbl_allLotteries.getSelectionModel().getSelectedItem().getLotteryNumber();
		int maxParticipants = allLotteries.get(lotteryNumber).getMaxParticipants();
		int currentParticipants = LotteryLogic.getInstance().getSumOfParticipantsInLottery(lotteryNumber);

		boolean regToThisLottery = false;

		// Check if user already registered
		for (Participant p : allParticipants) {
			if ((p.getLotteryNumber().equals(lotteryNumber)) & (p.getUniqueAddress().equals(currentMinerAddress))) {
				regToThisLottery = true;
				break;
			}
		}

		// Check if lottery is full
		if ((currentParticipants + 1) > maxParticipants) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Lottery is full!");
			alert.setContentText("Sorry, lottery is full! Please try to sign up to another lottery");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		// Lottery is not full --> sign up
		else {
			if (regToThisLottery == false) {
				if (LotteryLogic.getInstance().addParticipant(lotteryNumber, currentMinerAddress, false)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Seccessfully signed up!");
					alert.setContentText("Seccessfully signed up");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.showAndWait();

					watchLotteryDetails();
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Failed to signed up!");
					alert.setContentText("Faild to signed up");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.showAndWait();
				}
			}

			// if registered
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Already registred!");
				alert.setContentText("You are already registred");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();
			}
		}
	}

	/**
	 * Watch lottery details
	 */
	@FXML
	void watchLotteryDetails() {

		String lotteryNumber = tbl_allLotteries.getSelectionModel().getSelectedItem().getLotteryNumber();
		tf_number.setText(allLotteries.get(lotteryNumber).getLotteryNumber());
		tf_maxParticipants.setText(Integer.toString(allLotteries.get(lotteryNumber).getMaxParticipants()));
		tf_winners.setText(Integer.toString(allLotteries.get(lotteryNumber).getNumberOfWinners()));
		tf_bonusesNum.setText(Integer.toString(allLotteries.get(lotteryNumber).getNumberOfBonuses()));
		tf_date.setText(allLotteries.get(lotteryNumber).getDate().toString());

		tf_regParticipants
				.setText(Integer.toString(LotteryLogic.getInstance().getSumOfParticipantsInLottery(lotteryNumber)));

	}

}
