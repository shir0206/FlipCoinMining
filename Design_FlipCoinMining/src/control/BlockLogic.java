package control;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JFrame;
import entity.Consts;
import entity.GetBonus;
import entity.Lottery;
import entity.Miner;
import entity.Participant;
import entity.Riddle;
import entity.RiddleLevel;
import entity.Solution;
import entity.SolvedRiddle;
import entity.Block;
import entity.Bonus;
import entity.BusinessCompany;
import entity.Transaction;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class BlockLogic {

	//----------------------------------------- SINGLETON TO DB -----------------------------------------

	private static BlockLogic instance;

	public static BlockLogic getInstance() {
		if (instance == null)
			instance = new BlockLogic();
		return instance;
	}

	//----------------------------------------- ARRAY LISTS FROM DB -----------------------------------------


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
					/*results.add(new Block(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* Add a new Block with the parameters received from the form.
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addBlock(int ID, Date creationDate, Date creationHour, int size, String minerAddress, String previousBlock) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Block_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	public boolean editBlock (int ID, Date creationDate, Date creationHour, int size, String minerAddress, String previousBlock) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Block_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, BlockID);*/
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




	

	//----------------------------------------- Bonus Methods -------------------------------------

	/**
	* Get all Bonuss from DB file.
	* @return ArrayList of Bonuss.
	*/
	public ArrayList<Bonus> getAllBonuss() {
		ArrayList<Bonus> results = new ArrayList<Bonus>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Bonus_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					/*results.add(new Bonus(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* Add a new Bonus with the parameters received from the form.
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addBonus(int bonusNumber, String description) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Bonus_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	* Delete the selected Bonus in form.
	* @param bonusNumber - the Bonus to delete from DB.
	* @return true if the deletion was successful, else - return false.
	*/
	public boolean removeBonus(int bonusNumber) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Bonus_delete)) {
				
				stmt.setLong(1, bonusNumber);
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
	* Edit an existed Bonus with the parameters received from the form.
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editBonus (int bonusNumber, String description) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Bonus_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, BonusID);*/
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

	
	
	//----------------------------------------- Business Company Methods -------------------------------------

	/**
	* Get all BusinessCompanys from DB file.
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
					/*results.add(new BusinessCompany(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addBusinessCompany(String uniqueAddress, String contactName, String contactPhone, String contactEmail) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_BusinessCompany_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	* @param BusinessCompanyID - the BusinessCompany to delete from DB.
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
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editBusinessCompany (String uniqueAddress, String contactName, String contactPhone, String contactEmail) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_BusinessCompany_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, BusinessCompanyID);*/
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


	
	
	//----------------------------------------- Get Bonus Methods -------------------------------------

	/**
	* Get all GetBonuss from DB file.
	* @return ArrayList of GetBonuss.
	*/
	public ArrayList<GetBonus> getAllGetBonuss() {
		ArrayList<GetBonus> results = new ArrayList<GetBonus>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GetBonus_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					/*results.add(new GetBonus(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* Add a new GetBonus with the parameters received from the form.
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addGetBonus(String uniqueAddress, int lotteryNumber, int bonusNumber) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_GetBonus_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	* Delete the selected GetBonus in form.
	* @param uniqueAddress - the GetBonus to delete from DB.
	* @return true if the deletion was successful, else - return false.
	*/
	public boolean removeGetBonus(String uniqueAddress) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_GetBonus_delete)) {
				
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
	* Edit an existed GetBonus with the parameters received from the form.
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editGetBonus (String uniqueAddress, int lotteryNumber, int bonusNumber) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_GetBonus_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, GetBonusID);*/
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


	
		
	//----------------------------------------- Lottery Methods -------------------------------------
	/**
	* Get all Lotterys from DB file.
	* @return ArrayList of Lotterys.
	*/
	public ArrayList<Lottery> getAllLotterys() {
		ArrayList<Lottery> results = new ArrayList<Lottery>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Lottery_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					/*results.add(new Lottery(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* Add a new Lottery with the parameters received from the form.
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addLottery(int lotteryNumber, Date date, int maxParticipants, int numberOfWinners, int numberOfBonuses) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Lottery_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	* Delete the selected Lottery in form.
	* @param LotteryID - the Lottery to delete from DB.
	* @return true if the deletion was successful, else - return false.
	*/
	public boolean removeLottery(int lotteryNumber) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Lottery_delete)) {
				
				stmt.setLong(1, lotteryNumber);
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
	* Edit an existed Lottery with the parameters received from the form.
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editLottery (int lotteryNumber, Date date, int maxParticipants, int numberOfWinners, int numberOfBonuses) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Lottery_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, LotteryID);*/
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



	
		
	//----------------------------------------- Miner Methods -------------------------------------

	
	/**
	* Get all Miners from DB file.
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
					/*results.add(new Miner(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addMiner(String uniqueAddress, String name, String password, long digitalProfit, String email) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Miner_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	* @param MinerID - the Miner to delete from DB.
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
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editMiner (String uniqueAddress, String name, String password, long digitalProfit, String email) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Miner_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, MinerID);*/
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



	
	//----------------------------------------- Participant Methods -------------------------------------

	/**
	* Get all Participants from DB file.
	* @return ArrayList of Participants.
	*/
	public ArrayList<Participant> getAllParticipants() {
		ArrayList<Participant> results = new ArrayList<Participant>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Participant_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					/*results.add(new Participant(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* Add a new Participant with the parameters received from the form.
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addParticipant(int lotteryNumber, String uniqueAddress, boolean isWinner) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Participant_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	* Delete the selected Participant in form.
	* @param ParticipantID - the Participant to delete from DB.
	* @return true if the deletion was successful, else - return false.
	*/
	public boolean removeParticipant(int lotteryNumber, String uniqueAddress) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Participant_delete)) {
				
				stmt.setLong(1, lotteryNumber);
				stmt.setString(2, uniqueAddress);
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
	* Edit an existed Participant with the parameters received from the form.
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editParticipant (int lotteryNumber, String uniqueAddress, boolean isWinner) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Participant_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, ParticipantID);*/
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




		
	//----------------------------------------- Riddle Methods -------------------------------------

	/**
	* Get all Riddles from DB file.
	* @return ArrayList of Riddles.
	*/
	public ArrayList<Riddle> getAllRiddles() {
		ArrayList<Riddle> results = new ArrayList<Riddle>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Riddle_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					/*results.add(new Riddle(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* Add a new Riddle with the parameters received from the form.
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addRiddle(int riddleNumber, Date publishDate, Date publishHour, String description, int solutionTime, String status, int riddleLevel) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Riddle_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	* Delete the selected Riddle in form.
	* @param RiddleID - the Riddle to delete from DB.
	* @return true if the deletion was successful, else - return false.
	*/
	public boolean removeRiddle(int riddleNumber) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Riddle_delete)) {
				
				stmt.setLong(1, riddleNumber);
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
	* Edit an existed Riddle with the parameters received from the form.
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editRiddle (int riddleNumber, Date publishDate, Date publishHour, String description, int solutionTime, String status, int riddleLevel) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Riddle_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, RiddleID);*/
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




		
	//----------------------------------------- RiddleLevel Methods -------------------------------------

	/**
	* Get all RiddleLevels from DB file.
	* @return ArrayList of RiddleLevels.
	*/
	public ArrayList<RiddleLevel> getAllRiddleLevels() {
		ArrayList<RiddleLevel> results = new ArrayList<RiddleLevel>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_RiddleLevel_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					/*results.add(new RiddleLevel(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* Add a new RiddleLevel with the parameters received from the form.
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addRiddleLevel(int levelCode, String levelName, int levelDifficulty, int blockSize) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_RiddleLevel_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	* Delete the selected RiddleLevel in form.
	* @param RiddleLevelID - the RiddleLevel to delete from DB.
	* @return true if the deletion was successful, else - return false.
	*/
	public boolean removeRiddleLevel(int levelCode) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_RiddleLevel_delete)) {
				
				stmt.setLong(1, levelCode);
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
	* Edit an existed RiddleLevel with the parameters received from the form.
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editRiddleLevel (int levelCode, String levelName, int levelDifficulty, int blockSize) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_RiddleLevel_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, RiddleLevelID);*/
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



		
	//----------------------------------------- Solution Methods -------------------------------------

	/**
	* Get all Solutions from DB file.
	* @return ArrayList of Solutions.
	*/
	public ArrayList<Solution> getAllSolutions() {
		ArrayList<Solution> results = new ArrayList<Solution>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Solution_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					/*results.add(new Solution(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* Add a new Solution with the parameters received from the form.
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addSolution(int riddleNumber, int solutionNumber, String result) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Solution_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	* Delete the selected Solution in form.
	* @param SolutionID - the Solution to delete from DB.
	* @return true if the deletion was successful, else - return false.
	*/
	public boolean removeSolution(int riddleNumber, int solutionNumber) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Solution_delete)) {
				
				stmt.setLong(1, riddleNumber);
				stmt.setLong(2, solutionNumber);

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
	* Edit an existed Solution with the parameters received from the form.
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editSolution (int riddleNumber, int solutionNumber, String result) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Solution_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, SolutionID);*/
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



	
	//----------------------------------------- SolvedRiddle Methods -------------------------------------

	/**
	* Get all SolvedRiddles from DB file.
	* @return ArrayList of SolvedRiddles.
	*/
	public ArrayList<SolvedRiddle> getAllSolvedRiddles() {
		ArrayList<SolvedRiddle> results = new ArrayList<SolvedRiddle>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SolvedRiddle_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					/*results.add(new SolvedRiddle(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	* Add a new SolvedRiddle with the parameters received from the form.
	* @return true if the insertion was successful, else - return false.
	*/
	public boolean addSolvedRiddle(String uniqueAddress, int riddleNumber, Date time) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_SolvedRiddle_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	* Delete the selected SolvedRiddle in form.
	* @param SolvedRiddleID - the SolvedRiddle to delete from DB.
	* @return true if the deletion was successful, else - return false.
	*/
	public boolean removeSolvedRiddle(String uniqueAddress, int riddleNumber) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_SolvedRiddle_delete)) {
				
				stmt.setString(1, uniqueAddress);
				stmt.setLong(2, riddleNumber);
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
	* Edit an existed SolvedRiddle with the parameters received from the form.
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editSolvedRiddle (String uniqueAddress, int riddleNumber, Date time) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_SolvedRiddle_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, SolvedRiddleID);*/
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
					/*results.add(new Transaction(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
							rs.getString(i++), rs.getString(i++), rs.getString(i++)));*/
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
	public boolean addTransaction(int ID, int size, String type, double fee, String blockAddress, Date additionTime, Date additionDate ) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_insert)) {
				
				int i = 1;
				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
				if (title != null)
					stmt.setString(i++, title);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (birthDate != null)
					stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (hireDate != null)
					stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				if (address != null)
					stmt.setString(i++, address);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (city != null)
					stmt.setString(i++, city);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (country != null)
					stmt.setString(i++, country);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (homePhone != null)
					stmt.setString(i++, homePhone);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (extension != null)
					stmt.setString(i++, extension);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				if (photo != null)
					stmt.setString(i++, photo);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);*/
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
	public boolean removeTransaction(int ID) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_delete)) {
				
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
	* Edit an existed Transaction with the parameters received from the form.
	* @return true if the update was successful, else - return false.
	*  
	*/
	public boolean editTransaction (int ID, int size, String type, double fee, String blockAddress, Date additionTime, Date additionDate ) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Transaction_update)) {
				int i = 1;

				/*stmt.setString(i++, lastName); // can't be null
				stmt.setString(i++, firstName); // can't be null
					if (title != null)
						stmt.setString(i++, title);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (birthDate != null)
						stmt.setDate(i++, new java.sql.Date(birthDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (hireDate != null)
						stmt.setDate(i++, new java.sql.Date(hireDate.getTime()));
					else
						stmt.setNull(i++, java.sql.Types.DATE);
					if (address != null)
						stmt.setString(i++, address);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (city != null)
						stmt.setString(i++, city);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (country != null)
						stmt.setString(i++, country);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (homePhone != null)
						stmt.setString(i++, homePhone);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (extension != null)
						stmt.setString(i++, extension);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					if (photo != null)
						stmt.setString(i++, photo);
					else
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					stmt.setLong(i++, TransactionID);*/
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


	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//----------------------------------------- BALAGAN: -------------------------------------

	
//	
//	/**
//	 * Get all blocks
//	 * @return ArrayList of all blocks in DB
//	 */
//	public ArrayList<Block> getAllBlocks() {
//
//		ArrayList<Block> results = new ArrayList<Block>();
//
//		try {
//			Class.forName(Consts.JDBC_STR);
//			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//
//					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_BLOCKS);
//					ResultSet rs = stmt.executeQuery()) { {
//
//						while (rs.next()) {
//							int i=1;
//
//							results.add(new Block(rs.getInt(i++),
//									rs.getDate(i++),
//									rs.getInt(i++),
//									rs.getString(i++),
//									rs.getString(i++)));
//
//						}
//					}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		return results;
//	}
//
//	/**
//	 * Edit block
//	 * @return Success / Fail
//	 */
//	public boolean editBlock(String ID, Date creationDate, int size, String owner, String previousBlock) {
//
//		try {
//			Class.forName(Consts.JDBC_STR);
//			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//
//					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_BLOCK)) {
//
//				int i=1;
//
//				stmt.setString(i++, ID); // can't be null
//				stmt.setInt(i++, size); // can't be null
//				stmt.setString(i++, owner); // can't be null
//
//				if (previousBlock != null)
//					stmt.setString(i++, previousBlock);
//				else
//					stmt.setNull(i++, java.sql.Types.VARCHAR);
//
//				stmt.executeUpdate();
//				return true;
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return false;
//
//	}
//
//	/**
//	 * Get all transactions
//	 * @return ArrayList of all transactions in DB
//	 */
//	public ArrayList<Transaction> getAllTransactions() {
//		ArrayList<Transaction> results = new ArrayList<Transaction>();
//		try {
//			Class.forName(Consts.JDBC_STR);
//			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//
//					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_TRANS_NO_BLOCK);
//					ResultSet rs = stmt.executeQuery()) { {
//
//						while (rs.next()) {
//							int i=1;
//
//							results.add(new Transaction(rs.getString(i++),
//									rs.getInt(i++),
//									rs.getString(i++),
//									rs.getDouble(i++),
//									rs.getString(i++),
//									rs.getDate(i++)));
//						}
//					}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		return results;
//	}
//
//	/**
//	 * Edit transaction
//	 * @return Success / Fail
//	 */
//	public boolean editTransaction(String ID, int size, String type, double commission, String blockID) {
//
//		try {
//			Class.forName(Consts.JDBC_STR);
//			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//
//					CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_TRANSACTION)) {
//
//				int i=1;
//
//				stmt.setString(i++, ID); // can't be null
//				stmt.setInt(i++, size); // can't be null
//				stmt.setString(i++, type); // can't be null
//				stmt.setDouble(i++, commission); // can't be null
//
//				if (blockID != null)
//					stmt.setString(i++, blockID);
//				else
//					stmt.setNull(i++, java.sql.Types.VARCHAR);
//
//				stmt.executeUpdate();
//				return true;
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return false;
//
//	}
//
//
//	/**
//	 * Add a transaction to a specific block
//	 * @param block
//	 * @param a
//	 * @return
//	 */
//	public static boolean addToBlock(Block block, Transaction transaction) {
//		try {
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//					CallableStatement stmt = conn.prepareCall(Consts.SQL_UpdateTrans)) {
//
//				stmt.setInt(1, block.getID());
//				stmt.setString(3, transaction.getID());
//
//				stmt.executeUpdate();
//				return true;
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return false;
//
//	}
///**
// * Get last block ID
// * @return last block ID
// */
//	public int getLastBlockID() {
//		ArrayList<Block> results = new ArrayList<Block>();
//		try {
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_BLOCKS);
//					ResultSet rs = stmt.executeQuery())
//			{
//				while (rs.next()) {
//					int i = 1;
//					results.add(new Block(rs.getInt(i++),
//							rs.getDate(i++),
//							rs.getInt(i++),
//							rs.getString(i++),
//							rs.getString(i++)));
//				}
//			}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		return results.size();
//	}
//
//	/*----------------------------------------- REPORT -----------------------------------------*/
//	
//	public void produceReport() throws  ClassNotFoundException, SQLException, JRException {
//		Connection conn = DriverManager.getConnection(Consts.CONN_STR);
//		JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("../boundary/RepTransactionsPairs.jasper"), null, conn);
//		JFrame frame = new JFrame("Transactions Report");
//		frame.getContentPane().add(new JRViewer(print));
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.pack();
//		frame.setVisible(true);
//
//	}

}
