package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.BusinessCompany;
import entity.Consts;
import entity.Miner;

public class MinerLogic {

	// ----------------------------------------- SINGLETON TO DB -----------------------------------------

	private static MinerLogic instance;

	public static MinerLogic getInstance() {
		if (instance == null)
			instance = new MinerLogic();
		return instance;
	}

	// ----------------------------------------- Business Company Methods -------------------------------------

	/**
	 * Get all BusinessCompanys from DB file.
	 * 
	 * @return ArrayList of BusinessCompanys.
	 */
	public ArrayList<BusinessCompany> getAllBusinessCompanys() {
		ArrayList<BusinessCompany> results = new ArrayList<BusinessCompany>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_BusinessCompany_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new BusinessCompany(rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++)));
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
	 * Add a new BusinessCompany with the parameters received from the form.
	 * 
	 * @return true if the insertion was successful, else - return false.
	 */
	public boolean addBusinessCompany(String uniqueAddress, String contactName, String contactPhone,
			String contactEmail) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_BusinessCompany_insert)) {

				int i = 1;
				stmt.setString(i++, uniqueAddress); // can't be null

				if (contactName != null)
					stmt.setString(i++, contactName);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (contactPhone != null)
					stmt.setString(i++, contactPhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (contactEmail != null)
					stmt.setString(i++, contactEmail);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

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
	 * Delete the selected BusinessCompany in form.
	 * 
	 * @param BusinessCompanyID
	 *            - the BusinessCompany to delete from DB.
	 * @return true if the deletion was successful, else - return false.
	 */
	public boolean removeBusinessCompany(String uniqueAddress) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_BusinessCompany_delete)) {

				stmt.setString(1, uniqueAddress);
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
	 * Edit an existed BusinessCompany with the parameters received from the form.
	 * 
	 * @return true if the update was successful, else - return false.
	 * 
	 */
	public boolean editBusinessCompany(String uniqueAddress, String contactName, String contactPhone,
			String contactEmail) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_BusinessCompany_update)) {
				int i = 1;

				if (contactName != null)
					stmt.setString(i++, contactName);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (contactPhone != null)
					stmt.setString(i++, contactPhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (contactEmail != null)
					stmt.setString(i++, contactEmail);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				stmt.setString(i++, uniqueAddress);

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

	// ----------------------------------------- Miner Methods -------------------------------------

	/**
	 * Get all Miners from DB file.
	 * 
	 * @return ArrayList of Miners.
	 */
	public ArrayList<Miner> getAllMiners() {
		ArrayList<Miner> results = new ArrayList<Miner>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Miner_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Miner(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDouble(i++),
							rs.getString(i++)));
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
	 * Add a new Miner with the parameters received from the form.
	 * 
	 * @return true if the insertion was successful, else - return false.
	 */
	public boolean addMiner(String uniqueAddress, String name, String password, Double digitalProfit, String email) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Miner_insert)) {

				int i = 1;
				stmt.setString(i++, uniqueAddress); // can't be null

				if (name != null)
					stmt.setString(i++, name);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (password != null)
					stmt.setString(i++, password);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (digitalProfit != null)
					stmt.setDouble(i++, digitalProfit);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (email != null)
					stmt.setString(i++, email);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

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
	 * Delete the selected Miner in form.
	 * 
	 * @param MinerID
	 *            - the Miner to delete from DB.
	 * @return true if the deletion was successful, else - return false.
	 */
	public boolean removeMiner(String uniqueAddress) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Miner_delete)) {

				stmt.setString(1, uniqueAddress);
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
	 * Edit an existed Miner with the parameters received from the form.
	 * 
	 * @return true if the update was successful, else - return false.
	 * 
	 */
	public boolean editMiner(String uniqueAddress, String name, String password, Double digitalProfit, String email) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Miner_update)) {
				int i = 1;

				if (name != null)
					stmt.setString(i++, name);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (password != null)
					stmt.setString(i++, password);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (digitalProfit != null)
					stmt.setDouble(i++, digitalProfit);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (email != null)
					stmt.setString(i++, email);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				stmt.setString(i++, uniqueAddress);
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
