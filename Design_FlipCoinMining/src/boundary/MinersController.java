package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MinersController {

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_email;

    @FXML
    private RadioButton rb_privateMiner;

    @FXML
    private RadioButton rb_businerrMiner;

    @FXML
    private TextField tf_contactName;

    @FXML
    private TextField tf_contactPhone;

    @FXML
    private TextField tf_contactEmail;

    @FXML
    private TextField tf_digitalProfit;

    @FXML
    private Button btn_select;

    @FXML
    private TableView<?> tbl_allMiners;

    @FXML
    private TableColumn<?, ?> col_allMiners_username;

    @FXML
    private TableColumn<?, ?> col_allMiners_digitalProfit;

    @FXML
    void watchMinerDetails() {

    }

}
