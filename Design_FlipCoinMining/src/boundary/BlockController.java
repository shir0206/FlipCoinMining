package boundary;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import control.BlockLogic;
import control.DataLogic;
import entity.Block;
import entity.Consts;
import entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import net.sf.jasperreports.engine.JRException;

public class BlockController {

	private String currentMinerAddress = Consts.currentMinerAddress; // the current miner that is logged in
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

	/**
	 * Initialize Transactions in block table
	 */
	private void setTransInBlockTable() {

		// Get all transactions from DB
		allTransactions = BlockLogic.getInstance().getAllAvailableTransactions();

		// Get all available transactions that are attached to current block
		for (int i = 0; i < allTransactions.size(); i++) {
			if ((allTransactions.get(i).getblockAddress() != null)
					&& (allTransactions.get(i).getblockAddress().equals(currentBlock)))
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

		allTransactions = BlockLogic.getInstance().getAllTransactions();

		for (int i = 0; i < allTransactions.size(); i++) {
			if (allTransactions.get(i).getblockAddress() == null)
				allAvailableTransactions.add(allTransactions.get(i));
		}

		// Display the available transactions
		ObservableList<Transaction> allAvailableTransactionsList = FXCollections.observableArrayList();
		allAvailableTransactionsList.setAll(BlockLogic.getInstance().getAllAvailableTransactions());

		System.out.println(BlockLogic.getInstance().getAllTransactions());

		tbl_allTrans.setItems(allAvailableTransactionsList);
		tbl_allTrans.refresh();
	}

	/**
	 * This method sets the block details
	 */
	private void initBlockDetails() {

		// Get all blocks from DB
		allBlocks = BlockLogic.getInstance().getAllBlocks();

		// Get all blocks of the current miner
		allCurrentMinerBlocks = new ArrayList<Block>();
		for (int i = 0; i < allBlocks.size(); i++) {
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

		Integer size = allCurrentMinerBlocks.get(currentBlockIndex).getSize();
		String previousBlock = allCurrentMinerBlocks.get(currentBlockIndex).getPreviousBlock();

		tf_address.setText(blockID);
		;
		tf_creationDate.setText(creationDate.toString());

		tf_size.setText(size.toString());
		;

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
		blockCapacity = BlockLogic.getInstance().getSumOfTransInBlock(currentBlock);
		return blockCapacity;
	}

	@FXML
	void addTransToBlock() throws ClassNotFoundException {

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

		else {
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

				else {

					// Check size of the transaction, if it is possible to add it
					if ((t.getSize()) + calculateBlockCapacity() > allCurrentMinerBlocks.get(currentBlockIndex)
							.getSize()) {

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
						// allAvailableTransactions.remove(allAvailableTransactions.indexOf(transaction));
						BlockLogic.getInstance().addTransToBlock(transaction,
								allCurrentMinerBlocks.get(currentBlockIndex));
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

		ArrayList<Transaction> transactions = new ArrayList<>();
		transactions.addAll(tbl_allTrans.getSelectionModel().getSelectedItems());
		System.out.println("Successfully added available transactions");

		tf_address.getText();
		if (allTransactionsInBlock.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Choose transactions");
			alert.setContentText("Please choose transactions from the table");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		else {
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

				else {

					// Add the transaction
					allTransactionsInBlock.remove(transaction);
					allAvailableTransactions.add(transaction);
					BlockLogic.getInstance().addTransToBlock(transaction, allCurrentMinerBlocks.get(currentBlockIndex));
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

	public void importTransactionsFromJSON() {
		try (FileReader reader = new FileReader(new File("json/importedTransactions.json"))) {
			JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
			JsonArray transactions = (JsonArray) doc.get("Transactions");
			Iterator<Object> iterator = transactions.iterator();
			int errors = 0;
			while (iterator.hasNext()) {
				JsonObject obj = (JsonObject) iterator.next();
				Transaction t = new Transaction((String) obj.get("ID"), obj.getInteger("Size"),
						(String) obj.get("Type"), (Double) obj.getDouble("Comission Fee"));
			}
		}

		catch (IOException | DeserializationException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("There was an error importing transactions!");
			alert.showAndWait();
			return;
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText("All transactions were imported successfully");
		alert.showAndWait();

	}

	public void exportTransactionsToXML() {

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn
							.prepareStatement(Consts.SQL_Transaction_getAllTransInBlock(currentBlock));
					ResultSet rs = stmt.executeQuery()) {

				// create document object.
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

				// push root element into document object.
				Element rootElement = doc.createElement("Transactions");
				rootElement.setAttribute("exportDate", LocalDateTime.now().toString());
				doc.appendChild(rootElement);

				while (rs.next()) { // run on all customer records..
									// create customer element.
					Element transaction = doc.createElement("transaction");

					// assign key to customer.
					Attr attr = doc.createAttribute("ID");
					attr.setValue(rs.getString(1));
					transaction.setAttributeNode(attr);

					// push elements to customer.
					for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++) {
						Element element = doc.createElement(rs.getMetaData().getColumnName(i)); // push element to doc.
						rs.getObject(i); // for wasNull() check..
						element.appendChild(doc.createTextNode(rs.wasNull() ? "" : rs.getString(i))); // set element
																										// value.
						transaction.appendChild(element); // push element to customer.
					}

					// push transaction to document's root element.
					rootElement.appendChild(transaction);
				}

				// write the content into xml file

				DOMSource source = new DOMSource(doc);
				File file = new File("xml/transactions.xml");
				file.getParentFile().mkdir(); // create xml folder if doesn't exist.
				StreamResult result = new StreamResult(file);
				TransformerFactory factory = TransformerFactory.newInstance();

				Transformer transformer = factory.newTransformer();
				transformer.transform(source, result);
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			}

			catch (SQLException | NullPointerException | ParserConfigurationException | TransformerException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Status");
		alert.setHeaderText("Export has been done");
		alert.showAndWait();

	}

}
