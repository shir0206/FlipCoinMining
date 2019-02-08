package boundary;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entity.Block;
import entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BlockController {

	String currentMinerAddress = "a111"; //the current miner that  is logged in UPDATE

	ArrayList<Block> allBlocks;
	ArrayList<Block> allCurrentMinerBlocks;
	ArrayList<Transaction> allAvailableTransactions;
	ArrayList<Transaction> allTransactionsInBlock;

	String currentBlock;

	ArrayList<Transaction> allTransactions;
	@FXML
	private TextField tf_address;

	@FXML
	private TextField tf_size;

	@FXML
	private TextField tf_blockCapacity;

	@FXML
	private Button btn_transPairsRep;

	@FXML
	private Button btn_nextBlock;

	@FXML
	private Button btn_prevBlock;

	@FXML
	private TextField tf_creationDate;

	@FXML
	private TextField tf_creationHour;

	@FXML
	private TableView<Transaction> tbl_transInBlock;

	@FXML
	private TableColumn<Transaction, String> col_transInBlock_ID;

	@FXML
	private TableColumn<Transaction, Integer> col_transInBlock_size;

	@FXML
	private TableColumn<Transaction, String> col_transInBlock_type;

	@FXML
	private TableColumn<Transaction, Double> col_transInBlock_fee;

	@FXML
	private TableView<Transaction> tbl_allTrans;

	@FXML
	private TableColumn<Transaction, String> col_allTrans_ID;

	@FXML
	private TableColumn<Transaction, Integer> col_allTrans_size;

	@FXML
	private TableColumn<Transaction, String> col_allTrans_type;

	@FXML
	private TableColumn<Transaction, Double> col_allTrans_fee;

	@FXML
	private TextField tf_prevBlock;

	@FXML
	private Button btn_add;

	@FXML
	private Button btn_remove;

	@FXML
	private DatePicker dp_creationDate;

	@FXML
	private DatePicker dp_creationHour;

	/**
	 * This method initialize the window
	 */
	public void initialize() {

		System.out.println("Initialize " + this.getClass().getName() + " window");

		setBlockDetails();

		// setting the table of the transactions in the block
		col_transInBlock_ID.setCellValueFactory(new PropertyValueFactory<>("ID")); // According to variable name
		col_transInBlock_size.setCellValueFactory(new PropertyValueFactory<>("size")); // Same here
		col_transInBlock_type.setCellValueFactory(new PropertyValueFactory<>("type")); // And here
		col_transInBlock_fee.setCellValueFactory(new PropertyValueFactory<>("fee")); // And here

		// setting the table of all the available transactions to add to the block
		col_allTrans_ID.setCellValueFactory(new PropertyValueFactory<>("ID")); // According to variable name
		col_allTrans_size.setCellValueFactory(new PropertyValueFactory<>("size")); // Same here
		col_allTrans_type.setCellValueFactory(new PropertyValueFactory<>("type")); // And here

		setAllTransTable();
		setTransInBlockTable();

	}


	private void setTransInBlockTable() {

		// Get all transactions from DB
		allTransactions = ViewLogic.instance.getAllTransactions();

		// Get all available transactions that are attached to current block
		allTransactionsInBlock = new ArrayList<Transaction>();
		for (int i=0; i<allTransactions.size(); i++) {
			if ((allTransactions.get(i).getblockAddress() != null) && (allTransactions.get(i).getblockAddress().equals(currentBlock))) 
				allTransactionsInBlock.add(allTransactions.get(i));
		}


		// Update block capacity
		Integer blockCapacity = calculateBlockCapacity();
		tf_blockCapacity.setText(blockCapacity.toString());


		// Display the transactions in the block
		ObservableList<Transaction> allTransactionsInBlockList = FXCollections.observableArrayList();
		allTransactionsInBlockList.setAll(allTransactionsInBlock);

		tbl_transInBlock.setItems(allTransactionsInBlockList);
		tbl_transInBlock.refresh();


	}

	/**
	 * This method display all available transactions in the table
	 */
	private void setAllTransTable() {

		// Get all transactions from DB
		allTransactions = ViewLogic.instance.getAllTransactions();

		// Get all available transactions that aren't attached to any block
		allAvailableTransactions = new ArrayList<Transaction>();
		for (int i=0; i<allTransactions.size(); i++) {
			if (allTransactions.get(i).getblockAddress() == null) 
				allAvailableTransactions.add(allTransactions.get(i));
		}

		// Display the available transactions
		ObservableList<Transaction> allAvailableTransactionsList = FXCollections.observableArrayList();
		allAvailableTransactionsList.setAll(allAvailableTransactions);
		
		
		tbl_allTrans.setItems(allAvailableTransactionsList);
		tbl_allTrans.refresh();
	}


	/**
	 * This method sets the block details
	 */
	private void setBlockDetails() {

		// Get all blocks from DB
		allBlocks = ViewLogic.instance.getAllBlocks();

		// Get all blocks of the current miner
		allCurrentMinerBlocks = new ArrayList<Block>();
		for (int i=0; i<allBlocks.size();i++) {
			if (allBlocks.get(i).getMinerAddress().equals(currentMinerAddress)) 
				allCurrentMinerBlocks.add(allBlocks.get(i));
		}

		// The last block in the chain
		int lastBlock = (allCurrentMinerBlocks.size() - 1);

		String blockID = allCurrentMinerBlocks.get(lastBlock).getID();
		currentBlock = blockID;


		Date creationDate = allCurrentMinerBlocks.get(lastBlock).getCreationDate();
		//	LocalDate creationDate1 = allCurrentMinerBlocks.get(certainBlock).getCreationDate();

		Date creationHour = allCurrentMinerBlocks.get(lastBlock).getCreationHour();
		Integer size = allCurrentMinerBlocks.get(lastBlock).getSize();
		String minerAddress = allCurrentMinerBlocks.get(lastBlock).getMinerAddress();
		String previousBlock = allCurrentMinerBlocks.get(lastBlock).getPreviousBlock();

		tf_address.setText(blockID);;
		tf_creationDate.setText(creationDate.toString());

		tf_size.setText(size.toString());;

		Integer blockCapacity = calculateBlockCapacity();
		tf_blockCapacity.setText(blockCapacity.toString());

		if (previousBlock != null) 
			tf_prevBlock.setText(previousBlock.toString());
	}



	/**
	 * This method calculates block capacity
	 */
	private int calculateBlockCapacity() {

		int blockCapacity = 0;

		if (!(allTransactionsInBlock == null || allTransactionsInBlock.isEmpty()))
			for (int i=0; i< allTransactionsInBlock.size(); i++)
				blockCapacity += allTransactionsInBlock.get(i).getSize();

		return blockCapacity;

	}






	@FXML
	void addTransToBlock() {

	}

	@FXML
	void removeTransFromBlock() {

	}

	@FXML
	void transPairsReport() {

	}

	@FXML
	void watchNextBlock() {

	}

	@FXML
	void watchPrevBlock() {

	}

}
