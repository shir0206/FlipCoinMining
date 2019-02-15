package boundary;

import java.util.ArrayList;
import java.util.Date;

import control.DataLogic;
import entity.Riddle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RiddlesController {

	private String currentMinerAddress = "a111"; //the current miner that  is logged in UPDATE

	private ArrayList<Riddle> allRiddlesList= DataLogic.getInstance().getAllRiddles();



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

	}

	@FXML
	void watchRiddleDetails() {

		int riddleNumber = tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleNumber();

		tf_number.setText(Integer.toString(tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleNumber()));

		tf_riddleDescription.setText(allRiddlesList.get(riddleNumber).getDescription());

		tf_publishDate.setText(tbl_allRiddles.getSelectionModel().getSelectedItem().getPublishDate().toString());

		tf_publishHour.setText(tbl_allRiddles.getSelectionModel().getSelectedItem().getPublishHour().toString());

		tf_solutionTime.setText(Integer.toString(tbl_allRiddles.getSelectionModel().getSelectedItem().getSolutionTime()));

		tf_level.setText(Integer.toString(tbl_allRiddles.getSelectionModel().getSelectedItem().getRiddleLevel()));


	}

}
