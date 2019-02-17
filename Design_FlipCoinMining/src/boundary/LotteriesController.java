package boundary;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import control.DataLogic;
import entity.Bonus;
import entity.Lottery;
import entity.Participant;
import entity.Solution;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

public class LotteriesController {

	private String currentMinerAddress = "a111"; //the current miner that  is logged in UPDATE
	//private boolean registred = false;

	private HashMap<String,Lottery> allLotteries= DataLogic.getInstance().getAllLotteriesHM();
	private ArrayList<Participant> allParticipants= DataLogic.getInstance().getAllParticipants();

	private ArrayList<Bonus> allBonusesList= DataLogic.getInstance().getAllBonuses();

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

	private void setAllLotteriesTable() {
		ObservableList<Lottery> lotteries = FXCollections.observableArrayList();
		lotteries.setAll(DataLogic.getInstance().getAllLotterys());
		tbl_allLotteries.setItems(lotteries);	
		tbl_allLotteries.refresh();		
	}

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
		if (regToThisLottery==false) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Not registred!");
			alert.setContentText("You are not registred");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}


		// cancel registration
		else if (regToThisLottery==true) {

			if (DataLogic.getInstance().removeParticipant(lotteryNumber, currentMinerAddress)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Seccessfully signed up!");
				alert.setContentText("Seccessfully signed up");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();

				watchLotteryDetails();
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Failed to cancel registration!");
				alert.setContentText("Faild to cancel registration");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();
			}
		}

	}

	@FXML
	void signUpLottery() {

		String lotteryNumber = tbl_allLotteries.getSelectionModel().getSelectedItem().getLotteryNumber();
		int maxParticipants = allLotteries.get(lotteryNumber).getMaxParticipants();
		int currentParticipants = DataLogic.getInstance().getSumOfParticipantsInLottery(lotteryNumber);

		boolean regToThisLottery = false;

		// Check if user already registered
		for (Participant p : allParticipants) {
			if ((p.getLotteryNumber().equals(lotteryNumber)) & (p.getUniqueAddress().equals(currentMinerAddress))) {
				regToThisLottery = true;
//				Alert alert = new Alert(AlertType.ERROR);
//				alert.setTitle("Already registred!");
//				alert.setContentText("You are already registred");
//				alert.initModality(Modality.APPLICATION_MODAL);
//				alert.showAndWait();
				break;
			}
		}

		// Check if lottery is full
		if  ((currentParticipants + 1) > maxParticipants ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Lottery is full!");
			alert.setContentText("Sorry, lottery is full! Please try to sign up to another lottery");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		// Lottery is not full --> sign up
		else {
			if (regToThisLottery==false) {
				if (DataLogic.getInstance().addParticipant(lotteryNumber, currentMinerAddress, false)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Seccessfully signed up!");
					alert.setContentText("Seccessfully signed up");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.showAndWait();

					watchLotteryDetails();
				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Failed to signed up!");
					alert.setContentText("Faild to signed up");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.showAndWait();
				}
			}
			//registered==true
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Already registred!");
				alert.setContentText("You are already registred");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();
			}
		}
	

}

@FXML
void watchLotteryDetails() {

	String lotteryNumber = tbl_allLotteries.getSelectionModel().getSelectedItem().getLotteryNumber();
	tf_number.setText(allLotteries.get(lotteryNumber).getLotteryNumber());
	tf_maxParticipants.setText(Integer.toString(allLotteries.get(lotteryNumber).getMaxParticipants()));
	tf_winners.setText(Integer.toString(allLotteries.get(lotteryNumber).getNumberOfWinners()));
	tf_bonusesNum.setText(Integer.toString(allLotteries.get(lotteryNumber).getNumberOfBonuses()));
	tf_date.setText(allLotteries.get(lotteryNumber).getDate().toString());

	tf_regParticipants.setText(Integer.toString(DataLogic.getInstance().getSumOfParticipantsInLottery(lotteryNumber)));

}

}
