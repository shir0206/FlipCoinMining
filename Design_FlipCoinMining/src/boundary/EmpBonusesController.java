package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmpBonusesController {

	@FXML
	private TextField tf_number;

	@FXML
	private TextField tf_description;

	@FXML
	private Button btn_updateBonus;

	@FXML
	private TableView<?> tbl_allBonuses;

	@FXML
	private TableColumn<?, ?> col_allBonuses_number;

	@FXML
	private TableColumn<?, ?> col_allBonuses_description;

	@FXML
	private Button btn_saveBonus;

	@FXML
	private Button btn_addBonus;

	@FXML
	private Button btn_deleteBonus;

	@FXML
	void addBonusDetails() {
	}

	@FXML
	void deleteBonusDetails() {
	}

	@FXML
	void saveBonusDetails() {
	}

	@FXML
	void updateBonusDetails() {
	}

}
