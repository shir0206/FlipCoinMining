package boundary;

import java.util.ArrayList;

import control.DataLogic;
import entity.Lottery;
import entity.Miner;
import entity.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MinersController {

	private ArrayList<Miner> allMiners= DataLogic.getInstance().getAllMiners();

	private String currentMinerAddress = "a111"; //the current miner that  is logged in UPDATE

	
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
    private TableView<Miner> tbl_allMiners;

    @FXML
    private TableColumn<Miner, String> col_allMiners_username;

    @FXML
    private TableColumn<Miner, Double> col_allMiners_digitalProfit;

    
    public void initialize() {

		System.out.println("Initialize " + this.getClass().getName() + " window");

		// setting miners table
		col_allMiners_username.setCellValueFactory(new PropertyValueFactory<>("uniqueAddress"));
		col_allMiners_digitalProfit.setCellValueFactory(new PropertyValueFactory<>("name"));

		setAllMinersTable();

	}
    
      private void setAllMinersTable() {
    	ObservableList<Miner> miners = FXCollections.observableArrayList();
    	
    	
		miners.setAll(DataLogic.getInstance().getAllMiners());
		tbl_allMiners.setItems(miners);	
		tbl_allMiners.refresh();			
	}


	@FXML
    void watchMinerDetails() {
		
		String minerID =  tbl_allMiners.getSelectionModel().getSelectedItem().getUniqueAddress();
		
		tf_username.setText(minerID);

    }

}
