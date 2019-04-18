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

import entity.Bonus;
import entity.Consts;
import entity.GetBonus;
import entity.Lottery;
import entity.Participant;
import entity.SolvedRiddle;

public class LotteryLogic {




	//----------------------------------------- SINGLETON TO DB -----------------------------------------

	private static LotteryLogic instance;

	public static LotteryLogic getInstance() {
		if (instance == null)
			instance = new LotteryLogic();
		return instance;
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
					results.add(new GetBonus(rs.getString(i++), rs.getInt(i++), rs.getInt(i++)));
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
				stmt.setString(i++, uniqueAddress); // can't be null
				stmt.setInt(i++, lotteryNumber); // can't be null
				stmt.setInt(i++, bonusNumber); // can't be null

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
	public boolean removeGetBonus(String uniqueAddress, int lotteryNumber, int bonusNumber) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_GetBonus_delete)) {

				stmt.setString(1, uniqueAddress);
				stmt.setInt(2, lotteryNumber);
				stmt.setInt(3, bonusNumber);

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

				stmt.setString(i++, uniqueAddress); // can't be null
				stmt.setInt(i++, lotteryNumber); // can't be null
				stmt.setInt(i++, bonusNumber); // can't be null
				//stmt.setLong(i++, GetBonusID);*/
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
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Lottery_selectAfterToday);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Lottery(rs.getString(i++), rs.getDate(i++), rs.getInt(i++),
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
	 * Get all Lotterys from DB file.
	 * @return ArrayList of Lotterys.
	 */
	public HashMap<String,Lottery> getAllLotteriesHM() {
		HashMap<String,Lottery> results = new HashMap<String,Lottery>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Lottery_selectAfterToday);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;

					Lottery value = new Lottery(rs.getString(i++), rs.getDate(i++), rs.getInt(i++),
							rs.getInt(i++), rs.getInt(i++));
					String key = value.getLotteryNumber();

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
	 * Add a new Lottery with the parameters received from the form.
	 * @return true if the insertion was successful, else - return false.
	 */
	public boolean addLottery(Integer lotteryNumber, Date date, Integer maxParticipants,
			Integer numberOfWinners, Integer numberOfBonuses) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Lottery_insert)) {

				int i = 1;
				stmt.setInt(i++, lotteryNumber); // can't be null



				if (date != null)
					stmt.setDate(i++, new java.sql.Date(date.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);



				if (maxParticipants != null)
					stmt.setInt(i++, maxParticipants);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);


				if (numberOfWinners != null)
					stmt.setInt(i++, numberOfWinners);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);


				if (numberOfBonuses != null)
					stmt.setInt(i++, numberOfBonuses);
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
	public boolean editLottery (Integer lotteryNumber, Date date, Integer maxParticipants,
			Integer numberOfWinners, Integer numberOfBonuses) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Lottery_update)) {
				int i = 1;

				stmt.setInt(i++, lotteryNumber); // can't be null

				if (date != null)
					stmt.setDate(i++, new java.sql.Date(date.getTime()));
				else
					stmt.setNull(i++, java.sql.Types.DATE);


				if (maxParticipants != null)
					stmt.setInt(i++, maxParticipants);
				else
					stmt.setNull(i++, java.sql.Types.DATE);


				if (numberOfWinners != null)
					stmt.setInt(i++, numberOfWinners);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);


				if (numberOfBonuses != null)
					stmt.setInt(i++, numberOfBonuses);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				stmt.setLong(i++, lotteryNumber);
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
					results.add(new Participant(rs.getString(i++), rs.getString(i++), rs.getBoolean(i++)));
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
	 * Get sum of participants.
	 * @return ArrayList of Participants.
	 */
	public int getSumOfParticipantsInLottery(String lottery) {
		int results = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Participant_selectSumOfParticipantsInLottery(lottery));
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results = rs.getInt(i++);
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
	public boolean addParticipant(String lotteryNumber, String uniqueAddress, Boolean isWinner) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Participant_insert)) {

				int i = 1;
				stmt.setString(i++, lotteryNumber); // can't be null
				stmt.setString(i++, uniqueAddress); // can't be null
				stmt.setBoolean(i++, isWinner); // can't be null

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
	public boolean removeParticipant(String lotteryNumber, String uniqueAddress) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Participant_deleteParticipant)) {

				stmt.setString(1, lotteryNumber);
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
	public boolean editParticipant (Integer lotteryNumber, String uniqueAddress, Boolean isWinner) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_Participant_update)) {
				int i = 1;

				stmt.setInt(i++, lotteryNumber); // can't be null
				stmt.setString(i++, uniqueAddress); // can't be null
				if (isWinner != null)
					stmt.setBoolean(i++, isWinner);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				stmt.setLong(i++, lotteryNumber);
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




	//----------------------------------------- Bonus Methods -------------------------------------

	/**
	 * Get all Bonuses from DB file.
	 * @return ArrayList of Bonuses.
	 */
	public ArrayList<Bonus> getAllBonuses() {
		ArrayList<Bonus> results = new ArrayList<Bonus>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_Bonus_select);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					results.add(new Bonus(rs.getInt(i++), rs.getString(i++)));
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
				stmt.setInt(i++, bonusNumber); // can't be null

				if (description != null)
					stmt.setString(i++, description);
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

				stmt.setInt(i++, bonusNumber); // can't be null

				if (description != null)
					stmt.setString(i++, description);
				else
					stmt.setNull(i++, java.sql.Types.VARCHAR);

				stmt.setLong(i++, bonusNumber);

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
