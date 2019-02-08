package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RiddlesController {

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
    private TableView<?> tbl_allRiddles;

    @FXML
    private TableColumn<?, ?> col_allRiddles_number;

    @FXML
    private TableColumn<?, ?> col_allRiddles_publishDate;

    @FXML
    private TableColumn<?, ?> col_allRiddles_publishHour;

    @FXML
    private TableColumn<?, ?> col_allRiddles_solutionTime;

    @FXML
    private TableColumn<?, ?> col_allRiddles_level;

    @FXML
    private TableColumn<?, ?> col_allRiddles_status;

    @FXML
    private Button btn_send;

    @FXML
    private TextField tf_riddleSolution;

    @FXML
    void sendRiddleSolution() {

    }

    @FXML
    void watchRiddleDetails() {

    }

}
