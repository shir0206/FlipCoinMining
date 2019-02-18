package boundary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import control.DataLogic;
import entity.Block;
import entity.Consts;
import entity.Riddle;
import entity.RiddleLevel;
import entity.Solution;
import entity.SolvedRiddle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

public class RiddlesController {

	private String currentMinerAddress = Consts.currentMinerAddress; //the current miner that  is logged in
	private boolean isWorker = Consts.isWorker;

	private HashMap<Integer,Riddle> allRiddles = DataLogic.getInstance().getAllRiddlesHM();

	private ArrayList<Solution> allSolutions = DataLogic.getInstance().getAllSolutions();

	private ArrayList<RiddleLevel>  allLevels = DataLogic.getInstance().getAllRiddleLevels();

	private ArrayList<Block> allBlocks = DataLogic.getInstance().getAllBlocks();

	private ArrayList<Block> allCurrentMinerBlocks = new ArrayList<Block>();

	@FXML
	private TextField tf_number;

	@FXML
	private TextField tf_riddleDescription;

	@FXML
	private TextField tf_publishDate;

	@FXML
	private TextField tf_publishHour;

	@FXML
	private TextField tf_solutionTime;

	@FXML
	private TextField tf_level;

	@FXML
	private Button btn_select;

	@FXML
	private TableView<Riddle> tbl_allRiddles;

	@FXML
	private TableColumn<Riddle, Integer> col_allRiddles_number;

	@FXML
	private TableColumn<Riddle, Timestamp> col_allRiddles_publishDate;

	@FXML
	private TableColumn<Riddle, Timestamp> col_allRiddles_solutionTime;

	@FXML
	private TableColumn<Riddle, Integer> col_allRiddles_level;

	@FXML
	private TableColumn<Riddle, String> col_allRiddles_status;

	@FXML
	private Button btn_send;

	@FXML
	private TextField tf_riddleSolution;

	public void initialize() {

		System.out.println("Initialize " + this.getClass().getName() + " window");

		// setting riddles item table

		col_allRiddles_number.setCellValueFactory(new PropertyValueFactory<>("riddleNumber"));
		col_allRiddles_publishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
		col_allRiddles_solutionTime.setCellValueFactory(new PropertyValueFactory<>("solutionTime"));
		col_allRiddles_level.setCellValueFactory(new PropertyValueFactory<>("riddleLevel"));
		col_allRiddles_status.setCellValueFactory(new PropertyValueFactory<>("status"));

		setAllRiddlesTable();

	}


	private void setAllRiddlesTable() {

		ObservableList<Riddle> riddles = FXCollections.observableArrayList();
		riddles.setAll(DataLogic.getInstance().getAllRiddles());
		tbl_allRiddles.setItems(riddles);	
		tbl_allRiddles.refresh();
	}




	@FXML
	void sendRiddleSolution() {


		int riddleID = Integer.valueOf(tf_number.getText());
		int solutionID;
		String solutionUser = tf_riddleSolution.getText();

		// calc the user solving time
		Timestamp currentTime = Consts.getCurrentTimeStamp();
		Timestamp solutionTime = allRiddles.get(riddleID).getSolutionTime();
		long diffTime = solutionTime.getTime() - currentTime.getTime();
		System.out.println(diffTime);
		//long diffTime = (Date)solutionTime.getTime() - (Date)currentTime.getTime();



		// Check if answer exists	
		if (solutionUser.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("No solution");
			alert.setContentText("No Solution! Please enter your solution");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		// check if answer correct
		else {

			boolean correct = false;

			// Iterate over all solutions if check if user right or wrong
			for (Solution s : allSolutions) {
				if (s.getRiddleNumber()==riddleID) {
					if (s.getResult().equals(solutionUser)) {		
						correct = true;
						solutionID = s.getSolutionNumber();
						break;
					}
				}
			}

			// if solution is not correct, alert
			if (!correct) {
				incorrectSolution();
				return;
			}

			// if solution is correct
			// if solved in time
			if (diffTime<0) {

				//alert
				timeOut();

				// change riddle status to closed 
				DataLogic.getInstance().editRiddleStatus(riddleID, "Closed");
				return;
			}

			SolvedRiddle solvedRiddle = new SolvedRiddle(currentMinerAddress, riddleID, currentTime);
			DataLogic.getInstance().addSolvedRiddle(currentMinerAddress, riddleID, currentTime);



			// update riddle solving time in db
			// TODO


			// create new block
			String blockID;
			String previousBlock;

			// get all miner blocks
			for (Block b : allBlocks) {
				if (b.getMinerAddress().equals(currentMinerAddress))
					allCurrentMinerBlocks.add(b);
			}

			if (allCurrentMinerBlocks.isEmpty() || allCurrentMinerBlocks==null) {
				previousBlock = null;
				blockID = "b" + currentMinerAddress.substring(0, currentMinerAddress.length()-1) + "00";
			}
				
			else {
			// get last block address
			previousBlock = allCurrentMinerBlocks.get((allCurrentMinerBlocks.size()-1)).getID();

			blockID = "b" + (Integer.parseInt(previousBlock.substring(1)) + 1);
			}

			// calc block size
			int level = allRiddles.get(riddleID).getRiddleLevel();
			int blockSize = 0;

			for (RiddleLevel l : allLevels) {
				if (l.getLevelCode() == level)
					blockSize = l.getBlockSize();
			}


			System.out.println(blockID + " "+ currentTime+ " "+  blockSize+ " "+ currentMinerAddress+ " "+ previousBlock);
			// add block
			DataLogic.getInstance().addBlock(blockID, currentTime, blockSize, currentMinerAddress, previousBlock);

			// change riddle status to closed 
			DataLogic.getInstance().editRiddleStatus(riddleID, "Closed");

			// alert
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Correct solution");
			alert.setContentText("Correct Solution!");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();

			// refresh riddles table
			setAllRiddlesTable();
		}

	}


	void incorrectSolution() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Wrong solution");
		alert.setContentText("Wrong Solution! Please try again");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.showAndWait();
	}

	void timeOut() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Time Out");
		alert.setContentText("Time out");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.showAndWait();

	}


	@FXML
	void watchRiddleDetails() {

		int riddleNumber = tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleNumber();

		int riddleID = tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleNumber();

		// Check if you can answer this riddle
		// Check status
		if (allRiddles.get(riddleID).getStatus().equals("Closed")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Riddle Closed");
			alert.setContentText("Riddle has alreay been answered");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}


		// Check time
		//else if (allRiddles.get(riddleID).getSolutionTime()<0){
		// TODO
		// publish date & time + solution time > today
		//}

		else {		

			tf_number.setText(Integer.toString(tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleNumber()));

			tf_riddleDescription.setText(allRiddles.get(riddleNumber).getDescription());

			tf_publishDate.setText(tbl_allRiddles.getSelectionModel().getSelectedItem().getPublishDate().toString());

			tf_solutionTime.setText(tbl_allRiddles.getSelectionModel().getSelectedItem().getSolutionTime().toString());

			tf_level.setText(Integer.toString(tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleLevel()));
		}


	}


}
