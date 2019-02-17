package boundary;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import control.DataLogic;
import entity.Block;
import entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import net.sf.jasperreports.engine.JRException;

public class BlockController {

	private String currentMinerAddress = "a111"; //the current miner that  is logged in UPDATE

	private ArrayList<Block> allBlocks;
	private ArrayList<Block> allCurrentMinerBlocks = new ArrayList<Block>();

	private ArrayList<Transaction> allAvailableTransactions = new ArrayList<Transaction>();
	private ArrayList<Transaction> allTransactionsInBlock = new ArrayList<Transaction>();

	private String currentBlock;
	private int currentBlockIndex;

	private ArrayList<Transaction> allTransactions;

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
		initBlockDetails();
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
		col_allTrans_fee.setCellValueFactory(new PropertyValueFactory<>("fee")); // And here

		setAllTransTable();
		setTransInBlockTable();

	}


	private void setTransInBlockTable() {

		// Get all transactions from DB
		allTransactions = ViewLogic.instance.getAllTransactions();

		// Get all available transactions that are attached to current block
		for (int i=0; i<allTransactions.size(); i++) {
			if ((allTransactions.get(i).getblockAddress() != null) && (allTransactions.get(i).getblockAddress().equals(currentBlock))) 
				allTransactionsInBlock.add(allTransactions.get(i));
		}


		// Update block capacity
		Integer blockCapacity = calculateBlockCapacity();
		tf_blockCapacity.setText(blockCapacity.toString());


		// Display the transactions in the block
		ObservableList<Transaction> allTransactionsInBlockList = FXCollections.observableArrayList();
		allTransactionsInBlockList.setAll(ViewLogic.instance.getAllTransactionsInBlock(currentBlock));

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
		for (int i=0; i<allTransactions.size(); i++) {
			if (allTransactions.get(i).getblockAddress() == null) 
				allAvailableTransactions.add(allTransactions.get(i));
		}

		// Display the available transactions
		ObservableList<Transaction> allAvailableTransactionsList = FXCollections.observableArrayList();
		allAvailableTransactionsList.setAll(ViewLogic.instance.getAllAvailableTransactions());

		System.out.println(ViewLogic.instance.getAllTransactions());

		tbl_allTrans.setItems(allAvailableTransactionsList);
		tbl_allTrans.refresh();
	}




	/**
	 * This method sets the block details
	 */
	private void initBlockDetails() {

		// Get all blocks from DB
		allBlocks = ViewLogic.instance.getAllBlocks();

		// Get all blocks of the current miner
		allCurrentMinerBlocks = new ArrayList<Block>();
		for (int i=0; i<allBlocks.size();i++) {
			if (allBlocks.get(i).getMinerAddress().equals(currentMinerAddress)) 
				allCurrentMinerBlocks.add(allBlocks.get(i));
		}

		// The last block in the chain
		currentBlockIndex = (allCurrentMinerBlocks.size() - 1);

		currentBlock = allCurrentMinerBlocks.get(currentBlockIndex).getID();


	}


	/**
	 * This method sets the block details
	 */
	private void setBlockDetails() {

		String blockID = currentBlock;

		Date creationDate = allCurrentMinerBlocks.get(currentBlockIndex).getCreationDate();
		//	LocalDate creationDate1 = allCurrentMinerBlocks.get(certainBlock).getCreationDate();

	//	Date creationHour = allCurrentMinerBlocks.get(currentBlockIndex).getCreationHour();
		Integer size = allCurrentMinerBlocks.get(currentBlockIndex).getSize();
	//	String minerAddress = allCurrentMinerBlocks.get(currentBlockIndex).getMinerAddress();
		String previousBlock = allCurrentMinerBlocks.get(currentBlockIndex).getPreviousBlock();

		tf_address.setText(blockID);;
		tf_creationDate.setText(creationDate.toString());

		tf_size.setText(size.toString());;

		Integer blockCapacity = calculateBlockCapacity();
		tf_blockCapacity.setText(blockCapacity.toString());

		if (previousBlock != null) 
			tf_prevBlock.setText(previousBlock.toString());
	}

//
//	/**
//	 * This method sets the block details
//	 */
//	private void setBlockDetails0() {
//
//		// Get all blocks from DB
//		allBlocks = ViewLogic.instance.getAllBlocks();
//
//		// Get all blocks of the current miner
//		allCurrentMinerBlocks = new ArrayList<Block>();
//		for (int i=0; i<allBlocks.size();i++) {
//			if (allBlocks.get(i).getMinerAddress().equals(currentMinerAddress)) 
//				allCurrentMinerBlocks.add(allBlocks.get(i));
//		}
//
//		// The last block in the chain
//		int lastBlock = (allCurrentMinerBlocks.size() - 1);
//
//		String blockID = allCurrentMinerBlocks.get(lastBlock).getID();
//		currentBlock = blockID;
//
//
//		Date creationDate = allCurrentMinerBlocks.get(lastBlock).getCreationDate();
//
//		Date creationHour = allCurrentMinerBlocks.get(lastBlock).getCreationHour();
//		Integer size = allCurrentMinerBlocks.get(lastBlock).getSize();
//		String minerAddress = allCurrentMinerBlocks.get(lastBlock).getMinerAddress();
//		String previousBlock = allCurrentMinerBlocks.get(lastBlock).getPreviousBlock();
//
//		tf_address.setText(blockID);;
//		tf_creationDate.setText(creationDate.toString());
//
//		tf_size.setText(size.toString());;
//
//		Integer blockCapacity = calculateBlockCapacity();
//		tf_blockCapacity.setText(blockCapacity.toString());
//
//		if (previousBlock != null) 
//			tf_prevBlock.setText(previousBlock.toString());
//	}


	/**
	 * This method calculates block capacity
	 */
	private int calculateBlockCapacity() {

		int blockCapacity = 0;
		blockCapacity = ViewLogic.instance.getSumOfTransInBlock(currentBlock);
		return blockCapacity;
	}

	@FXML
	void addTransToBlock() throws ClassNotFoundException {
		//	allTransactionsInBlock

		ArrayList<Transaction> transactions = new ArrayList<>();
		transactions.addAll(tbl_allTrans.getSelectionModel().getSelectedItems());
		System.out.println("Successfully added transactions");

		String blockID = tf_address.getText();
		if (allTransactionsInBlock.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Choose transactions");
			alert.setContentText("Please choose transactions from the table");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		else  {
			for (Transaction t : transactions) {
				Transaction transaction = new Transaction(t.getID(), t.getSize(), t.getType(), t.getFee());

				if (allTransactionsInBlock.contains(transaction)) {
					allTransactionsInBlock.get(allTransactionsInBlock.indexOf(transaction)).setblockAddress(blockID);
			
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Transaction Exists");
					alert.setContentText("Transaction already exists in the block");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.showAndWait();
				
				}
				
				else  {
					
					// Check size of the transaction, if it is possible to add it
					if ( (t.getSize()) + calculateBlockCapacity() > allCurrentMinerBlocks.get(currentBlockIndex).getSize()) {
						
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Transaction Too Big");
						alert.setContentText("Transaction is too big for the current block");
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.showAndWait();
					}
					
					else {
					// Add the transaction
						allAvailableTransactions.remove(transaction);

					allTransactionsInBlock.add(transaction);
				//	allAvailableTransactions.remove(allAvailableTransactions.indexOf(transaction));
					ViewLogic.instance.addTransToBlock(transaction, allCurrentMinerBlocks.get(currentBlockIndex));
					}
				}
			}
		}
		System.out.println(allTransactionsInBlock);
		setTransInBlockTable();

		setAllTransTable();
	}



	@FXML
	void removeTransFromBlock() throws ClassNotFoundException {
		//	allTransactionsInBlock

		ArrayList<Transaction> transactions = new ArrayList<>();
		transactions.addAll(tbl_allTrans.getSelectionModel().getSelectedItems());
		System.out.println("Successfully added available transactions");

		String blockID = tf_address.getText();
		if (allTransactionsInBlock.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Choose transactions");
			alert.setContentText("Please choose transactions from the table");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		else  {
			for (Transaction t : transactions) {
				Transaction transaction = new Transaction(t.getID(), t.getSize(), t.getType(), t.getFee());

				if (allAvailableTransactions.contains(transaction)) {
					allAvailableTransactions.get(allAvailableTransactions.indexOf(transaction)).setblockAddress("");
			
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Transaction Exists");
					alert.setContentText("Transaction already exists in the block");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.showAndWait();
				
				}
				
				else  {
					
					
					// Add the transaction
					allTransactionsInBlock.remove(transaction);
					allAvailableTransactions.add(transaction);
					ViewLogic.instance.addTransToBlock(transaction, allCurrentMinerBlocks.get(currentBlockIndex));
					}
				
			}
		}
		System.out.println(allTransactionsInBlock);
		setTransInBlockTable();

		setAllTransTable();
	}

	@FXML
	void transPairsReport() throws ClassNotFoundException, SQLException, JRException {

		DataLogic.getInstance().TransactionsPairsReport();
	}

	@FXML
	void watchNextBlock() {
		

		// If it is the last block in the chain, alert
		if ((currentBlockIndex + 1) == allCurrentMinerBlocks.size()) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Last Block");
			alert.setContentText("This is the last block in the chain");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		// go to the previous block
		else {
			
			currentBlockIndex++;
			currentBlock = allCurrentMinerBlocks.get(currentBlockIndex).getID();

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

	}

	@FXML
	void watchPrevBlock() {

		// If it is the first block in the chain, alert
		if ((currentBlockIndex - 1) < 0) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("First Block");
			alert.setContentText("This is the first block in the chain");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		// go to the previous block
		else {
			
			currentBlockIndex--;
			currentBlock = allCurrentMinerBlocks.get(currentBlockIndex).getID();

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

	}

}
