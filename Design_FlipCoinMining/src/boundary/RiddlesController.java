package boundary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import control.DataLogic;
import entity.Riddle;
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

	private String currentMinerAddress = "a111"; //the current miner that  is logged in UPDATE

	private HashMap<Integer,Riddle> allRiddlesList= DataLogic.getInstance().getAllRiddlesHM();

	private ArrayList<Solution> allSolutionsList= DataLogic.getInstance().getAllSolutions();


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
	private TableColumn<Riddle, Date> col_allRiddles_publishDate;

	@FXML
	private TableColumn<Riddle, Date> col_allRiddles_publishHour;

	@FXML
	private TableColumn<Riddle, Integer> col_allRiddles_solutionTime;

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
		col_allRiddles_publishHour.setCellValueFactory(new PropertyValueFactory<>("publishHour"));
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
		String solutionUser = tf_riddleSolution.getText();

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
			for (Solution s : allSolutionsList) {
				if (s.getRiddleNumber()==riddleID) {
					if (s.getResult().equals(solutionUser)) {		
						correct = true;
					}
				}
			}

			// if solution is not correct, alert
			if (!correct) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wrong solution");
				alert.setContentText("Wrong Solution! Please try again");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();
			}

			// if solution is correct
			else {


				Date now = new Date();
				// calc the user solving time
				// TODO



				// update riddle solving time in db
				// TODO


				// create new block
				// TODO

				// change riddle status to closed 
				DataLogic.getInstance().editRiddleStatus(riddleID,
						"Closed");

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
	}


	@FXML
	void watchRiddleDetails() {

		int riddleNumber = tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleNumber();

		int riddleID = tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleNumber();

		// Check if you can answer this riddle
		// Check status
		if (allRiddlesList.get(riddleID).getStatus().equals("Closed")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Riddle Closed");
			alert.setContentText("Riddle has alreay been answered");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}


		// Check time
		else if (allRiddlesList.get(riddleID).getSolutionTime()<0){
			// TODO
			// publish date & time + solution time > today
		}

		else {		

			tf_number.setText(Integer.toString(tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleNumber()));

			tf_riddleDescription.setText(allRiddlesList.get(riddleNumber).getDescription());

			tf_publishDate.setText(tbl_allRiddles.getSelectionModel().getSelectedItem().getPublishDate().toString());

			tf_publishHour.setText(tbl_allRiddles.getSelectionModel().getSelectedItem().getPublishHour().toString());

			tf_solutionTime.setText(Integer.toString(tbl_allRiddles.getSelectionModel().getSelectedItem().getSolutionTime()));

			tf_level.setText(Integer.toString(tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleLevel()));
		}


	}


}
