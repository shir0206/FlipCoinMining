package boundary;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;
import control.BlockLogic;
import entity.Block;
import entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import net.sf.jasperreports.engine.JRException;

public class BlockManagementController {

	private Calendar today = Calendar.getInstance();
	private ArrayList<Transaction> transInBlock = new ArrayList<>();

	@FXML
	private Tab tab_blockMng;

	@FXML
	private TextField tf_blockID;

	@FXML
	private TextField tf_blockSize;

	@FXML
	private TextField tf_owner;

	@FXML
	private DatePicker dp_date;

	@FXML
	private TableView<Transaction> tblChs;

	@FXML
	private TableColumn<Transaction, String> tblChs_id;

	@FXML
	private TableColumn<Transaction, Integer> tblChs_size;

	@FXML
	private TableColumn<Transaction, String> tblChs_type;

	@FXML
	private TableColumn<Transaction, Double> tblChs_coms;

	@FXML
	private TableView<Transaction> tblList;

	@FXML
	private TableColumn<Transaction, String> tblList_id;

	@FXML
	private TableColumn<Transaction, Integer> tblList_size;

	@FXML
	private TableColumn<Transaction, String> tblList_type;

	@FXML
	private TableColumn<Transaction, Double> tblList_coms;

	@FXML
	private Button btn_saveBlock;

	@FXML
	private Button btn_selectTrans;

	@FXML
	private Button btn_rep;

	@FXML
	private Tab tab_transImpExp;

	@FXML
	private RadioButton btn_exp;

	@FXML
	private RadioButton btn_imp;

	@FXML
	private Button btn_run;

	
	
	
	
	


    @FXML
    private TextField tf_minerUsername;

    @FXML
    private Tab tab_profile;

    @FXML
    private TextField profile_tf_username;

    @FXML
    private TextField profile_tf_profit;

    @FXML
    private TextField profile_tf_password;

    @FXML
    private TextField profile_tf_email;

    @FXML
    private TextField profile_tf_contactName;

    @FXML
    private TextField profile_tf_contactPhone;

    @FXML
    private RadioButton profile_rb_private;

    @FXML
    private RadioButton profile_rb_business;

    @FXML
    private TextField profile_tf_contactEmail;

    @FXML
    private Button profile_btn_save;

    @FXML
    private RadioButton profile_rb_market;

    @FXML
    private RadioButton profile_rb_blocks;

    @FXML
    private RadioButton profile_rb_riddles;

    @FXML
    private RadioButton profile_rb_lottery;

    @FXML
    private Button profile_btn_run;

    @FXML
    private Tab tab_blockMng;

    @FXML
    private TextField tf_blockID;

    @FXML
    private TextField tf_blockSize;

    @FXML
    private TextField tf_owner;

    @FXML
    private TableView<?> tblChs;

    @FXML
    private TableColumn<?, ?> tblChs_id;

    @FXML
    private TableColumn<?, ?> tblChs_size;

    @FXML
    private TableColumn<?, ?> tblChs_type;

    @FXML
    private TableColumn<?, ?> tblChs_coms;

    @FXML
    private TableView<?> tblList;

    @FXML
    private TableColumn<?, ?> tblList_id;

    @FXML
    private TableColumn<?, ?> tblList_size;

    @FXML
    private TableColumn<?, ?> tblList_type;

    @FXML
    private TableColumn<?, ?> tblList_coms;

    @FXML
    private Button btn_selectTrans;

    @FXML
    private Button btn_rep;

    @FXML
    private DatePicker dp_date;

    @FXML
    private Tab tab_miners;

    @FXML
    private TableView<?> miners_tbl_allMiners;

    @FXML
    private TableColumn<?, ?> miners_col_username;

    @FXML
    private TableColumn<?, ?> miners_col_profit;

    @FXML
    private TextField miners_tf_username;

    @FXML
    private TextField miners_tf_profit;

    @FXML
    private TextField miners_tf_email;

    @FXML
    private TextField miners_tf_contactName;

    @FXML
    private TextField miners_tf_contactPhone;

    @FXML
    private TextField miners_tf_contactEmail;

    @FXML
    private Button miners_btn_select;

    @FXML
    private RadioButton miners_rb_private;

    @FXML
    private Tab tab_riddles;

    @FXML
    private TableView<?> riddles_tbl_allRiddles;

    @FXML
    private TableColumn<?, ?> riddles_col_riddleNum;

    @FXML
    private TableColumn<?, ?> riddles_col_publishDate;

    @FXML
    private TableColumn<?, ?> riddles_col_publishHour;

    @FXML
    private TableColumn<?, ?> riddles_col_solTime;

    @FXML
    private TableColumn<?, ?> riddles_col_status;

    @FXML
    private TableColumn<?, ?> riddles_col_level;

    @FXML
    private TextField riddles_tf_riddleNum;

    @FXML
    private TextField riddles_tf_publishHour;

    @FXML
    private TextField riddles_tf_solTime;

    @FXML
    private TextField riddles_tf_riddleDes;

    @FXML
    private TextField riddles_tf_solDes;

    @FXML
    private TextField riddles_tf_level;

    @FXML
    private DatePicker riddles_dp_publishDate;

    @FXML
    private Button riddles_btn_select;

    @FXML
    private Button riddles_btn_send;

    @FXML
    private Tab tab_lotteries;

    @FXML
    private TableColumn<?, ?> lotteries_col_lotterytNum;

    @FXML
    private TableColumn<?, ?> lotteries_col_date;

    @FXML
    private TableColumn<?, ?> lotteries_col_maxParticipants;

    @FXML
    private TableColumn<?, ?> lotteries_col_winnersNum;

    @FXML
    private TableColumn<?, ?> lotteries_col_bonusesNum;

    @FXML
    private ListView<?> lotteries_lv_bonuses;

    @FXML
    private Button lotteries_btn_select;

    @FXML
    private TextField lotteries_tf_lotterytNum;

    @FXML
    private TextField lotteries_tf_maxParticipants;

    @FXML
    private TextField lotteries_tf_winnersNum;

    @FXML
    private TextField lotteries_tf_bonusesNum;

    @FXML
    private DatePicker lotteries_dp_date;

    @FXML
    private Button lotteries_btn_signUp;

    @FXML
    private Tab tab_transImpExp;

    @FXML
    private RadioButton btn_exp;

    @FXML
    private RadioButton btn_imp;

    @FXML
    private Button btn_run;

    @FXML
    void lotteries_select(MouseEvent event) {

    }

    @FXML
    void lotteries_signUp(MouseEvent event) {

    }

    @FXML
    void miners_select(MouseEvent event) {

    }

    @FXML
    void produceReport(ActionEvent event) {

    }

    @FXML
    void profile_run(MouseEvent event) {

    }

    @FXML
    void profile_save(MouseEvent event) {

    }

    @FXML
    void riddles_select(MouseEvent event) {

    }

    @FXML
    void riddles_send(MouseEvent event) {

    }

    @FXML
    void saveTransactions(MouseEvent event) {

    }
	
	
	
	
	
	/**
	 * This method initialize window
	 */
	public void initialize() {
		System.out.println("Initialize window");
		setBlockID();

		// setting all transactions list table
		tblList_id.setCellValueFactory(new PropertyValueFactory<>("ID")); // According to variable name
		tblList_size.setCellValueFactory(new PropertyValueFactory<>("size")); // Same here
		tblList_type.setCellValueFactory(new PropertyValueFactory<>("type")); // And here
		tblList_coms.setCellValueFactory(new PropertyValueFactory<>("commission")); // And here
		tblList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		setTransactionsTable();

		// setting chosen transactions in block table
		tblChs_id.setCellValueFactory(new PropertyValueFactory<>("ID")); // According to variable name
		tblChs_size.setCellValueFactory(new PropertyValueFactory<>("size")); // Same here
		tblChs_type.setCellValueFactory(new PropertyValueFactory<>("type")); // And here
		tblChs_coms.setCellValueFactory(new PropertyValueFactory<>("commission")); // And here
		setBlockDate();

	}


	/**
	 * This method sets the block fields
	 */
	private void setBlockID() {

		int certainBlock = 2;

		Integer blockID = new Integer(ViewLogic.blockLogic.getAllBlocks().get(certainBlock).getID());
		tf_blockID.setText(blockID.toString());

		Integer blockSize = new Integer(ViewLogic.blockLogic.getAllBlocks().get(certainBlock).getSize());
		tf_blockSize.setText(blockSize.toString());

		String owner = new String(ViewLogic.blockLogic.getAllBlocks().get(certainBlock).getOwner());
		tf_owner.setText(owner.toString());
	}

	/**
	 * This method displaying all transactions
	 */
	protected void setTransactionsTable() {

		// Display data in table
		ObservableList<Transaction> transactions = FXCollections.observableArrayList();

		transactions.setAll(ViewLogic.blockLogic.getAllTransactions());
		System.out.println(ViewLogic.blockLogic.getAllTransactions());

		tblList.setItems(transactions);
		tblList.refresh();
	}


	/**
	 * This method setting the block date
	 */
	private void setBlockDate(){
		// setting today's date
		today = Calendar.getInstance();
		today.set(Calendar.HOUR, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		LocalDate date = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		dp_date.setValue(date);
	}


	/**
	 * This method saving the transactions in the block when select button is clicked
	 */
	@FXML
	private void saveTransactions() {
		ArrayList<Transaction> transactions = new ArrayList<>();
		transactions.addAll(tblList.getSelectionModel().getSelectedItems());
		System.out.println("Successfully added transactions");

		String blockID = tf_blockID.getText();
		if (transactions.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Choose transactions");
			alert.setContentText("Please choose transactions from the table");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		else  {
			for (Transaction t : transactions) {
				Transaction transaction = new Transaction(t.getID(), t.getSize(), t.getType(), t.getCommission());

				if (transInBlock.contains(transaction)) {
					transInBlock.get(transInBlock.indexOf(transaction)).setBlockID(blockID);
				}
				else  {
					transInBlock.add(transaction);
				}
			}
		}
		System.out.println(transInBlock);
		setChosenTransactionsTable();
	}

	/**
	 * This method displaying all the transactions in the block
	 */
	private void setChosenTransactionsTable() {
		ObservableList<Transaction> blockChsTrans = FXCollections.observableArrayList();
		blockChsTrans.setAll(transInBlock);
		tblChs.setItems(blockChsTrans);
		tblChs.refresh();		
	}

	/**
	 * This method producing transactions pairs report
	 */
	@FXML
	void produceReport(ActionEvent event) throws ClassNotFoundException, SQLException, JRException {
		BlockLogic.getInstance().produceReport();
	}
	
}



