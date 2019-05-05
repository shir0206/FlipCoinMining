package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmpLotteriesController {

	@FXML
	private TextField tf_number;

	@FXML
	private TextField tf_publishDate;

	@FXML
	private TextField tf_publishHour;

	@FXML
	private TextField tf_solutionTime;

	@FXML
	private TextField tf_level;

	@FXML
	private Button btn_updateLottery;

	@FXML
	private TableView<?> tbl_allLotteries;

	@FXML
	private TableColumn<?, ?> col_allLotteries_number;

	@FXML
	private TableColumn<?, ?> col_allLotteries_date;

	@FXML
	private TableColumn<?, ?> col_allLotteries_maxParticipants;

	@FXML
	private TableColumn<?, ?> col_allLotteries_numberOfWinners;

	@FXML
	private TableColumn<?, ?> col_allLotteries_numberOfBonuses;

	@FXML
	private Button btn_addBonusToLottery;

	@FXML
	private ListView<?> lv_bonusesInLottery;

	@FXML
	private Button btn_saveLottery;

	@FXML
	private Button btn_addLottery;

	@FXML
	private Button btn_deleteLottery;

	@FXML
	private ListView<?> lv_allBonuses;

	@FXML
	void addBonusToLottery() {
	}

	@FXML
	void addLotteryDetails() {
	}

	@FXML
	void deleteLotteryDetails() {
	}

	@FXML
	void saveLotteryDetails() {
	}

	@FXML
	void updateLotteryDetails() {
	}

}
