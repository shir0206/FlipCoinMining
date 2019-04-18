package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import entity.Block;
import entity.Consts;
import entity.Transaction;

public class BlockLogic {

	
	//----------------------------------------- SINGLETON TO DB -----------------------------------------

		private static BlockLogic instance;

		public static BlockLogic getInstance() {
			if (instance == null)
				instance = new BlockLogic();
			return instance;
		}
	//----------------------------------------- Block Methods -------------------------------------

	/**
	 * Get all Blocks from DB file.
	 * @return ArrayList of Blocks.
	 */
	public ArrayList<Block> getAllBlocks() {
		ArrayList<Block> results = new ArrayList<Block>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Block_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Block(rs.getString(i++), rs.getTimestamp(i++),
							rs.getInt(i++), rs.getString(i++), rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}


	/*
	 * 	
	private final String ID;
	private Timestamp creationDate;
	private int size;
	private String previousBlock;
	private String minerAddress;
	 */
	/**
	 * Add a new Block with the parameters received from the form.
	 * @return true if the insertion was successful, else - return false.
	 */
	public boolean addBlock(String ID, Timestamp creationDate, Integer size,
			String minerAddress, String previousBlock) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Block_insert)) {

				int i = 1;

				stmt.setString(i++, ID); // can't be null

				if (creationDate != null)
					stmt.setTimestamp(i++, creationDate);
				else
					stmt.setNull(i++, java.sql.Types.DATE);

				if (size != null)
					stmt.setInt(i++, size);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				stmt.setString(i++, minerAddress);

				stmt.setString(i++, previousBlock);

				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * Delete the selected Block in form.
	 * @param BlockID - the Block to delete from DB.
	 * @return true if the deletion was successful, else - return false.
	 */
	public boolean removeBlock(int ID) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Block_delete)) {

				stmt.setLong(1, ID);
				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * Edit an existed Block with the parameters received from the form.
	 * @return true if the update was successful, else - return false.
	 *  
	 */
	public boolean editBlock (int ID, Date creationDate, Date creationHour, Integer size, 
			String minerAddress, String previousBlock) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Block_update)) {
				int i = 1;

				stmt.setInt(i++, ID); // can't be null

				if (creationDate != null)
					stmt.setDate(i++, new java.sql.Date(creationDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);

				if (creationHour != null)
					stmt.setDate(i++, new java.sql.Date(creationHour.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);

				if (size != null)
					stmt.setInt(i++, size);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (minerAddress != null)
					stmt.setString(i++, minerAddress);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (previousBlock != null)
					stmt.setString(i++, previousBlock);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				stmt.setLong(i++, ID);
				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}






	//----------------------------------------- Transaction Methods -------------------------------------

	/**
	 * Get all Transactions from DB file.
	 * @return ArrayList of Transactions.
	 */
	public ArrayList<Transaction> getAllTransactions() {
		ArrayList<Transaction> results = new ArrayList<Transaction>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Transaction_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Transaction(rs.getString(i++), rs.getInt(i++), rs.getString(i++), 
							rs.getDouble(i++), rs.getString(i++), rs.getTimestamp(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Get all available transactions that arent attached to ant block from DB file.
	 * @return ArrayList of Transactions.
	 */
	public ArrayList<Transaction> getAllAvailableTransactions() {
		ArrayList<Transaction> results = new ArrayList<Transaction>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Transaction_getAllAvailable);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Transaction(rs.getString(i++), rs.getInt(i++), rs.getString(i++), 
							rs.getDouble(i++), rs.getString(i++), rs.getTimestamp(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Get all available transactions that are attached to certain block from DB file.
	 * @return ArrayList of Transactions.
	 */
	public ArrayList<Transaction> getAllTransactionsInBlock(String blockID) {
		ArrayList<Transaction> results = new ArrayList<Transaction>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Transaction_getAllTransInBlock(blockID));
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Transaction(rs.getString(i++), rs.getInt(i++), rs.getString(i++), 
							rs.getDouble(i++), rs.getString(i++), rs.getTimestamp(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}



	/**
	 * Get all available transactions that are attached to certain block from DB file.
	 * @return ArrayList of Transactions.
	 */
	public int getSumOfTransInBlock(String blockID) {
		int results = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Transaction_getSumOfTransInBlock(blockID));
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results = new Integer(rs.getInt(i++));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Add a new Transaction with the parameters received from the form.
	 * @return true if the insertion was successful, else - return false.
	 */
	public boolean addTransaction(String ID, Integer size, String type, Double fee, String blockAddress, 
			Date additionTime, Date additionDate ) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_insert)) {

				int i = 1;
				stmt.setString(i++, ID); // can't be null

				if (size != null)
					stmt.setInt(i++, size);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (type != null)
					stmt.setString(i++, type);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (fee != null)
					stmt.setDouble(i++, fee);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (blockAddress != null)
					stmt.setString(i++, blockAddress);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);


				if (additionTime != null)
					stmt.setDate(i++, new java.sql.Date(additionTime.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (additionDate != null)
					stmt.setDate(i++, new java.sql.Date(additionDate.getTime()));

				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * Delete the selected Transaction in form.
	 * @param TransactionID - the Transaction to delete from DB.
	 * @return true if the deletion was successful, else - return false.
	 */
	public boolean removeTransaction(String ID) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_delete)) {

				stmt.setString(1, ID);
				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}


	//	/**
	//	 * Edit an existed Transaction with the parameters received from the form.
	//	 * @return true if the update was successful, else - return false.
	//	 *  
	//	 */
	//	public boolean editTransaction (String ID, Integer size, String type, Double fee, String blockAddress, 
	//			Date additionTime, Date additionDate) {
	//		try {
	//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	//			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	//					CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_update)) {
	//				int i = 1;
	//
	//				stmt.setString(i++, ID); // can't be null
	//
	//				if (size != null)
	//					stmt.setInt(i++, size);
	//				else
	//					stmt.setNull(i++, java.sql.Types.VARCHAR);
	//				
	//				if (type != null)
	//					stmt.setString(i++, type);
	//				else
	//					stmt.setNull(i++, java.sql.Types.VARCHAR);
	//				
	//				if (fee != null)
	//					stmt.setDouble(i++, fee);
	//				else
	//					stmt.setNull(i++, java.sql.Types.VARCHAR);
	//				
	//				if (blockAddress != null)
	//					stmt.setString(i++, blockAddress);
	//				else
	//					stmt.setNull(i++, java.sql.Types.VARCHAR);
	//				
	//				
	//				if (additionTime != null)
	//					stmt.setDate(i++, new java.sql.Date(additionTime.getTime()));
	//				else
	//					stmt.setNull(i++, java.sql.Types.DATE);
	//				if (additionDate != null)
	//					stmt.setDate(i++, new java.sql.Date(additionDate.getTime()));
	//					stmt.setString(i++, ID);
	//				stmt.executeUpdate();
	//				return true;
	//
	//			} catch (SQLException e) {
	//				e.printStackTrace();
	//			}
	//		} catch (ClassNotFoundException e) {
	//			e.printStackTrace();
	//		}
	//		return false;
	//	}

	//	/**
	//	 * Edit an existed Transaction with the parameters received from the form.
	//	 * @return true if the update was successful, else - return false.
	//	 *  
	//	 */
	//	public boolean editTransaction (String ID, Integer size, String type, Double fee, String blockAddress) {
	//		try {
	//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	//			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	//					CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_update)) {
	//				int i = 1;
	//
	//				stmt.setString(i++, ID); // can't be null
	//
	//				if (size != null)
	//					stmt.setInt(i++, size);
	//				else
	//					stmt.setNull(i++, java.sql.Types.VARCHAR);
	//				
	//				if (type != null)
	//					stmt.setString(i++, type);
	//				else
	//					stmt.setNull(i++, java.sql.Types.VARCHAR);
	//				
	//				if (fee != null)
	//					stmt.setDouble(i++, fee);
	//				else
	//					stmt.setNull(i++, java.sql.Types.VARCHAR);
	//				
	//				if (blockAddress != null)
	//					stmt.setString(i++, blockAddress);
	//				else
	//					stmt.setNull(i++, java.sql.Types.VARCHAR);
	//
	//				stmt.executeUpdate();
	//				return true;
	//
	//			} catch (SQLException e) {
	//				e.printStackTrace();
	//			}
	//		} catch (ClassNotFoundException e) {
	//			e.printStackTrace();
	//		}
	//		return false;
	//	}



	/**
	 * Edit an existed Transaction with the parameters received from the form.
	 * @return true if the update was successful, else - return false.
	 * @throws ClassNotFoundException 
	 *  
	 */
	public boolean addTransToBlock (Transaction trans, Block block) throws ClassNotFoundException {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			//		try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			//	CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_update(block.getID(),trans.getID()))) {
			//	CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_update)) {
			Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_update);
			int i = 1;

			stmt.setString(i++, block.getID());
			stmt.setString(i++, trans.getID()); // can't be null

			//				if (block.getID() != null)
			//					stmt.setString(i++, block.getID());
			//				else
			//					stmt.setNull(i++, java.sql.Types.VARCHAR);	

			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//	} catch (ClassNotFoundException e) {
		//		e.printStackTrace();
		//	}
		return false;
	}



	/**
	 * Edit an existed Transaction with the parameters received from the form.
	 * @return true if the update was successful, else - return false.
	 *  
	 */
	public boolean removeTransFromBlock (Transaction trans, Block block) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_update)) {
				int i = 1;

				stmt.setNull(i++, java.sql.Types.VARCHAR);
				stmt.setString(i++, trans.getID()); // can't be null


				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
}
