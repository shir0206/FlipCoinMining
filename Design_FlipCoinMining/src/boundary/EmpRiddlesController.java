package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EmpRiddlesController {

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
    private ListView<?> lv_riddleSolution;

    @FXML
    private Button btn_select;

    @FXML
    private Button btn_save;

    @FXML
    void saveRiddleDetails(MouseEvent event) {

    }

    @FXML
    void watchRiddleDetails(MouseEvent event) {

    }

}
