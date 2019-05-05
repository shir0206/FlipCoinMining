package entity;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public final class Consts {

	// ----------------------------------------- Constructor -----------------------------------------

	private Consts() {
		throw new AssertionError();
	}

	// ----------------------------------------- User details  -------------------------------------

	public static String currentMinerAddress;
	public static boolean isWorker;

	// ----------------------------------------- Static Variables -------------------------------------

	public static final String JDBC_STR = "net.ucanaccess.jdbc.UcanaccessDriver";

	public static final String DB_FILE_NAME = "FlipCoinMiningDB.accdb";
	public static final String DB_FILE_PATH = getDBPath();

	public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILE_PATH + ";COLUMNORDER=DISPLAY";

	// ----------------------------------------- GET DB PATH -----------------------------------------

	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();

			String pathSrc = path.substring(0, path.length() - 4) + "src";

			System.out.println(pathSrc);

			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));

				return decoded + "/database/" + DB_FILE_NAME;
			} else {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));

				return decoded + "/entity/" + DB_FILE_NAME;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ----------------------------------------- Time Methods -------------------------------------

	// gets current time
	public static java.sql.Timestamp getCurrentTimeStamp() {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		return timestamp;
	}

	// add X hours to current time
	public static java.sql.Timestamp addHoursToTime(int hoursToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getCurrentTimeStamp().getTime());
		cal.add(Calendar.HOUR, 24);
		Timestamp timestamp = new Timestamp(cal.getTime().getTime());
		return timestamp;
	}

	public static java.sql.Timestamp addMinsToTime(int minsToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getCurrentTimeStamp().getTime());
		cal.add(Calendar.MINUTE, minsToAdd);
		Timestamp timestamp = new Timestamp(cal.getTime().getTime());
		return timestamp;
	}

	// ----------------------------------------- Block Queries -------------------------------------

	public static final String SQL_Block_select = "Select * FROM tblBlock";
	public static final String SQL_Block_delete = "{ call qryBlockDelete(?,?,?) }";
	public static final String SQL_Block_update = "{ call qryBlockUpdate(?,?,?,?,?,?) }";

	public static final String SQL_Block_insert = ""
			+ "INSERT INTO tblBlock (blockAddress, creationDate, size, previousBlock, minerAddress) "
			+ "VALUES ((?), (?), (?), (?), (?));";

	// ----------------------------------------- Bonus Queries -------------------------------------

	public static final String SQL_Bonus_select = "Select * FROM tblBonus";
	public static final String SQL_Bonus_delete = "{ call qryBonusDelete(?,?,?) }";
	public static final String SQL_Bonus_insert = "{ call qryBonusInsert(?,?,?) }";
	public static final String SQL_Bonus_update = "{ call qryBonusUpdate(?,?) }";

	// ----------------------------------------- BusinessCompany Queries -------------------------------------

	public static final String SQL_BusinessCompany_select = "Select * FROM tblBusinessCompany";
	public static final String SQL_BusinessCompany_delete = "{ call qryBusinessCompanyDelete(?,?,?) }";

	public static final String SQL_BusinessCompany_insert = ""
			+ "INSERT INTO tblBusinessCompany ( uniqueAddress, contactName, contactPhone, contactEmail ) "
			+ "VALUES (?, ?, ?, ?);";

	public static final String SQL_BusinessCompany_update = ""
			+ "UPDATE tblBusinessCompany SET tblBusinessCompany.contactName = ?, tblBusinessCompany.contactPhone = ?, tblBusinessCompany.contactEmail = ? "
			+ "WHERE (tblBusinessCompany.uniqueAddress = ?);";

	// ----------------------------------------- GetBonus Queries -------------------------------------

	public static final String SQL_GetBonus_select = "Select * FROM tblGetBonus";
	public static final String SQL_GetBonus_delete = "{ call qryGetBonusDelete(?,?,?) }";
	public static final String SQL_GetBonus_insert = "{ call qryGetBonusInsert(?,?,?) }";
	public static final String SQL_GetBonus_update = "{ call qryGetBonusUpdate(?,?,?) }";

	// ----------------------------------------- Lottery Queries -------------------------------------

	public static final String SQL_Lottery_select = "Select * FROM tblLottery";
	public static final String SQL_Lottery_delete = "{ call qryLotteryDelete(?,?,?) }";
	public static final String SQL_Lottery_insert = "{ call qryLotteryInsert(?,?,?) }";
	public static final String SQL_Lottery_update = "{ call qryLotteryUpdate(?,?,?,?,?) }";

	public static final String SQL_Lottery_selectAvailable = "SELECT qryLotteryAvailableSelect.CountOfParticipants, qryLotteryAvailableSelect.lotteryNumber, qryLotteryAvailableSelect.Date, qryLotteryAvailableSelect.maxParticipants, qryLotteryAvailableSelect.numberOfWinners, qryLotteryAvailableSelect.numberOfBonuses\r\n"
			+ "FROM qryLotteryAvailableSelect\r\n"
			+ "WHERE (((qryLotteryAvailableSelect.CountOfParticipants)<[qryLotteryAvailableSelect].[maxParticipants]));\r\n"
			+ "";

	public static final String SQL_Lottery_selectAfterToday = "SELECT tblLottery.lotteryNumber, tblLottery.date, tblLottery.maxParticipants, tblLottery.numberOfWinners, tblLottery.numberOfBonuses\r\n"
			+ "FROM tblLottery\r\n" + "WHERE (((tblLottery.date)>Date()));\r\n" + "";;

	// ----------------------------------------- Miner Queries -------------------------------------

	public static final String SQL_Miner_select = "Select * FROM tblMiner";
	public static final String SQL_Miner_delete = "{ call qryMinerDelete(?,?,?) }";
	public static final String SQL_Miner_insert = "{ call qryMinerInsert(?,?,?) }";

	public static final String SQL_Miner_update = ""
			+ "UPDATE tblMiner SET tblMiner.name = ?, tblMiner.password = ?, tblMiner.digitalProfit = ?, tblMiner.email = ? "
			+ "WHERE (tblMiner.uniqueAddress = ?);";

	// ----------------------------------------- Participant Queries -------------------------------------

	public static final String SQL_Participant_select = "Select * FROM tblParticipant";
	public static final String SQL_Participant_delete = "{ call qryParticipantDelete(?,?) }";

	public static final String SQL_Participant_insert = ""
			+ "INSERT INTO tblParticipant ( lotteryNumber, uniqueAddress, isWinner ) " + "VALUES (?, ?, ?);";

	public static final String SQL_Participant_update = "{ call qryParticipantUpdate(?,?,?) }";

	public static String SQL_Participant_selectSumOfParticipantsInLottery(String lottery) {
		return "" + "SELECT Count(tblParticipant.uniqueAddress) AS CountOfuniqueAddress " + "FROM tblParticipant "
				+ "WHERE (tblParticipant.lotteryNumber = \"" + lottery + "\") "
				+ "GROUP BY tblParticipant.lotteryNumber;";
	}

	public static String SQL_Participant_deleteParticipant = "{ call qryParticipantUpdate(?,?) }";

	// ----------------------------------------- Riddle Queries -------------------------------------

	public static final String SQL_Riddle_select = "Select * FROM tblRiddle";
	public static final String SQL_Riddle_delete = "{ call qryRiddleDelete(?,?,?) }";
	public static final String SQL_Riddle_insert = "{ call qryRiddleInsert(?,?,?) }";
	public static final String SQL_Riddle_update = "{ call qryRiddleUpdate(?,?,?,?,?,?,?) }";

	public static final String SQL_Riddle_updateStatus = "" + "UPDATE tblRiddle " + "SET tblRiddle.status = ? "
			+ "WHERE tblRiddle.riddleNumber = ?;";

	// ----------------------------------------- RiddleLevel Queries -------------------------------------

	public static final String SQL_RiddleLevel_select = "Select * FROM tblRiddleLevel";
	public static final String SQL_RiddleLevel_delete = "{ call qryRiddleLevelDelete(?,?,?) }";
	public static final String SQL_RiddleLevel_insert = "{ call qryRiddleLevelInsert(?,?,?) }";
	public static final String SQL_RiddleLevel_update = "{ call qryRiddleLevelUpdate(?,?,?,?) }";

	// ----------------------------------------- Solution Queries -------------------------------------

	public static final String SQL_Solution_select = "Select * FROM tblSolution";
	public static final String SQL_Solution_delete = "{ call qrySolutionDelete(?,?,?) }";
	public static final String SQL_Solution_insert = "{ call qrySolutionInsert(?,?,?) }";
	public static final String SQL_Solution_update = "{ call qrySolutionUpdate(?,?,?) }";

	// ----------------------------------------- SolvedRiddle Queries -------------------------------------

	public static final String SQL_SolvedRiddle_select = "Select * FROM tblSolvedRiddle";
	public static final String SQL_SolvedRiddle_delete = "{ call qrySolvedRiddleDelete(?,?,?) }";
	public static final String SQL_SolvedRiddle_update = "{ call qrySolvedRiddleUpdate(?,?,?) }";

	public static final String SQL_SolvedRiddle_insert = ""
			+ "INSERT INTO tblSolvedRiddle ( uniqueAddress, riddleNumber, time ) " + "VALUES (?, ?, ?);";

	// ----------------------------------------- Transaction Queries -------------------------------------

	public static final String SQL_Transaction_select = "Select * FROM tblTransaction";
	public static final String SQL_Transaction_delete = "{ call qryTransactionDelete(?,?,?) }";
	public static final String SQL_Transaction_insert = "{ call qryTransactionInsert(?,?,?) }";

	public static final String SQL_Transaction_update = "" + "UPDATE tblTransaction SET tblTransaction.ID = (?) "
			+ "WHERE tblTransaction.blockAddress = (?);";

	public static final String qryTransactionGetAllAvailable = "{ call qryTransactiongetAllAvailable() }";

	public static String SQL_Transaction_getSumOfTransInBlock(String block) {
		return "SELECT Sum(tblTransaction.size) AS SumOfsize\r\n" + "FROM tblTransaction\r\n"
				+ "GROUP BY tblTransaction.blockAddress\r\n" + "HAVING (((tblTransaction.blockAddress)=\"" + block
				+ "\"));\r\n" + "";
	}

	public static String SQL_Transaction_getAllTransInBlock(String block) {
		return "SELECT *\r\n" + "FROM tblTransaction\r\n" + "WHERE ((tblTransaction.blockAddress)=\"" + block
				+ "\");\r\n" + "";
	}

	public static final String SQL_Transaction_getAllAvailable = "SELECT *\r\n" + "FROM tblTransaction\r\n"
			+ "WHERE ((IsNull(tblTransaction.blockAddress)));\r\n" + "";

}
