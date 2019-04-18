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
import java.util.HashMap;

import entity.Consts;
import entity.Riddle;
import entity.RiddleLevel;
import entity.Solution;
import entity.SolvedRiddle;

public class RiddleLogic {


	//----------------------------------------- SINGLETON TO DB -----------------------------------------

	private static RiddleLogic instance;

	public static RiddleLogic getInstance() {
		if (instance == null)
			instance = new RiddleLogic();
		return instance;
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
					results.add(new Riddle(rs.getInt(i++), rs.getTimestamp(i++), 
							rs.getString(i++), rs.getTimestamp(i++), rs.getString(i++), rs.getInt(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	public HashMap<Integer,Riddle> getAllRiddlesHM(){
		HashMap<Integer, Riddle> results = new HashMap<Integer, Riddle>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Riddle_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;

					Riddle value = new Riddle (rs.getInt(i++), rs.getTimestamp(i++), 
							rs.getString(i++), rs.getTimestamp(i++), rs.getString(i++), rs.getInt(i++));

					Integer key = value.getRiddleNumber();

					results.put(key, value);

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
	public boolean addRiddle(Integer riddleNumber, Date publishDate, Date publishHour, 
			String description, Integer solutionTime, String status, Integer riddleLevel) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Riddle_insert)) {

				int i = 1;
				stmt.setInt(i++, riddleNumber); // can't be null

				if (publishDate != null)
					stmt.setDate(i++, new java.sql.Date(publishDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);

				if (publishHour != null)
					stmt.setDate(i++, new java.sql.Date(publishHour.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);

				if (description != null)
					stmt.setString(i++, description);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (solutionTime != null)
					stmt.setInt(i++, solutionTime);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (status != null)
					stmt.setString(i++, status);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (riddleLevel != null)
					stmt.setInt(i++, riddleLevel);
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
	public boolean editRiddle (Integer riddleNumber, Date publishDate, Date publishHour, 
			String description, Integer solutionTime, String status, Integer riddleLevel) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Riddle_update)) {
				int i = 1;

				//stmt.setInt(i++, riddleNumber); // can't be null


				if (publishDate != null)
					stmt.setDate(i++, new java.sql.Date(publishDate.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);


				if (publishHour != null)
					stmt.setDate(i++, new java.sql.Date(publishHour.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);

				if (description != null)
					stmt.setString(i++, description);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (solutionTime != null)
					stmt.setInt(i++, solutionTime);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (status != null)
					stmt.setString(i++, status);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (riddleLevel != null)
					stmt.setInt(i++, riddleLevel);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				stmt.setInt(i++, riddleNumber);
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
	public boolean editRiddleStatus (int riddleNumber, String status) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Riddle_updateStatus)) {
				int i = 1;

				//stmt.setInt(i++, riddleNumber); // can't be null

				if (status != null)
					stmt.setString(i++, status);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				stmt.setInt(i++, riddleNumber);
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
					results.add(new RiddleLevel(rs.getInt(i++), rs.getString(i++), 
							rs.getInt(i++), rs.getInt(i++)));
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
	public boolean addRiddleLevel(Integer levelCode, String levelName, Integer levelDifficulty, Integer blockSize) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_RiddleLevel_insert)) {

				int i = 1;
				stmt.setInt(i++, levelCode); // can't be null

				if (levelName != null)
					stmt.setString(i++, levelName);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (levelDifficulty != null)
					stmt.setInt(i++, levelDifficulty);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);


				if (blockSize != null)
					stmt.setInt(i++, blockSize);
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
	public boolean editRiddleLevel (Integer levelCode, String levelName, Integer levelDifficulty, Integer blockSize) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_RiddleLevel_update)) {
				int i = 1;

				stmt.setInt(i++, levelCode); // can't be null

				if (levelName != null)
					stmt.setString(i++, levelName);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (levelDifficulty != null)
					stmt.setInt(i++, levelDifficulty);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				if (blockSize != null)
					stmt.setInt(i++, blockSize);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				stmt.setLong(i++, levelCode);
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
					results.add(new Solution(rs.getInt(i++), rs.getInt(i++), rs.getString(i++)));
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
	public boolean addSolution(Integer riddleNumber, Integer solutionNumber, String result) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Solution_insert)) {

				int i = 1;
				stmt.setInt(i++, riddleNumber); // can't be null
				stmt.setInt(i++, solutionNumber); // can't be null

				if (result != null)
					stmt.setString(i++, result);
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
	public boolean editSolution (Integer riddleNumber, Integer solutionNumber, String result) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Solution_update)) {
				int i = 1;

				stmt.setInt(i++, riddleNumber); // can't be null
				stmt.setInt(i++, solutionNumber); // can't be null

				if (result != null)
					stmt.setString(i++, result);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				stmt.setLong(i++, riddleNumber);
				stmt.setLong(i++, solutionNumber);

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
					results.add(new SolvedRiddle(rs.getString(i++), rs.getInt(i++), rs.getTimestamp(i++)));
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
	public boolean addSolvedRiddle(String uniqueAddress, Integer riddleNumber, Timestamp time) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_SolvedRiddle_insert)) {

				int i = 1;
				stmt.setString(i++, uniqueAddress); // can't be null
				stmt.setInt(i++, riddleNumber); // can't be null

				if (time != null)
					stmt.setTimestamp(i++, time);
				else
					stmt.setNull(i++, java.sql.Types.DATE);

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

				stmt.setString(i++, uniqueAddress); // can't be null
				stmt.setInt(i++, riddleNumber); // can't be null

				if (time != null)
					stmt.setDate(i++, new java.sql.Date(time.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);
				stmt.setString(i++, uniqueAddress);
				stmt.setLong(i++, riddleNumber);

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
