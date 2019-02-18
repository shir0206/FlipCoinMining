package boundary;

import java.util.ArrayList;

import control.DataLogic;
import entity.BusinessCompany;
import entity.Consts;
import entity.Miner;
import entity.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class OtherMinersController {

	private String currentMinerAddress = Consts.currentMinerAddress; //the current miner that  is logged in
	private boolean isWorker = Consts.isWorker;
	
	private ArrayList<Miner> allMiners = DataLogic.getInstance().getAllMiners();
	private ArrayList<BusinessCompany> allBussinessMiners= DataLogic.getInstance().getAllBusinessCompanys();
	
    @FXML
    private TextField tf_address;

    @FXML
    private TextField tf_profit;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_username;

    @FXML
    private Button btn_select;

    @FXML
    private RadioButton rb_private;

    @FXML
    private RadioButton rb_business;
    
    @FXML
    private TableView<Miner> tbl_allMiners;

    @FXML
    private TableColumn<Miner, String> col_allMiners_address;

    @FXML
    private TableColumn<Miner, String> col_allMiners_username;

    @FXML
    private TableColumn<Miner, Double> col_allMiners_profit;

    @FXML
    private TableColumn<Miner, String> col_allMiners_email;

    @FXML
    private TextField tf_contactName;

    @FXML
    private TextField tf_contactEmail;

    @FXML
    private TextField tf_contactPhone;

    
    
    public void initialize() {

		System.out.println("Initialize " + this.getClass().getName() + " window");

		// setting miners table
		col_allMiners_address.setCellValueFactory(new PropertyValueFactory<>("uniqueAddress"));
		col_allMiners_username.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_allMiners_profit.setCellValueFactory(new PropertyValueFactory<>("digitalProfit"));
		col_allMiners_email.setCellValueFactory(new PropertyValueFactory<>("email"));

		setAllMinersTable();
	}
    
      private void setAllMinersTable() {
    	ObservableList<Miner> miners = FXCollections.observableArrayList();
		miners.setAll(allMiners);
		tbl_allMiners.setItems(miners);
		tbl_allMiners.refresh();			    
      }


	@FXML
    void watchMinerDetails(MouseEvent event) {


		String minerID =  tbl_allMiners.getSelectionModel().getSelectedItem().getUniqueAddress();
		Miner miner = null;
		BusinessCompany businessCompany = null;
		boolean isBussiness = false;
		
		// get miner details from DB
		for (Miner m : allMiners) {
			if (m.getUniqueAddress().equals(minerID)) {
				miner = m;
				break;
			}
		}
		
		// check if miner is business or private, if business, get business details from DB
		for (BusinessCompany b : allBussinessMiners) {
			if (miner.getUniqueAddress().equals(b.getUniqueAddress())) {
				businessCompany = b;
				isBussiness = true;
				break;
			}
		}
		
		tf_address.setText(miner.getUniqueAddress());
		tf_profit.setText(Double.toString(miner.getDigitalProfit()));
		tf_email.setText(miner.getEmail());
		tf_username.setText(miner.getName());
		

		
		if (isBussiness == true) {
			rb_business.setSelected(true);
			rb_private.setSelected(false);
			
			rb_business.setDisable(true);
			rb_private.setDisable(true);
			
			
			tf_contactName.setText(businessCompany.getContactName());
			tf_contactEmail.setText(businessCompany.getContactEmail());
			tf_contactPhone.setText(businessCompany.getContactPhone());
			
		}
		else {
			rb_business.setSelected(false);
			rb_private.setSelected(true);
			
			rb_business.setDisable(true);
			rb_private.setDisable(true);
		}
		

    }
    
    

}
