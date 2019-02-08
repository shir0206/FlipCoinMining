package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class LotteriesController {

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
    private Button btn_select;

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
    private ListView<?> lv_bonusesInLottery;

    
    @FXML
    private Button btn_signUp;

    @FXML
    private Button btn_cancel;

    @FXML
    void cancelLottery() {

    }

    @FXML
    void signUpLottery() {

    }

    @FXML
    void watchLotteryDetails() {

    }

}
