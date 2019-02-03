//SHIR
package entity;

import java.net.URLDecoder;

public final class Consts {

	//----------------------------------------- Constructor -----------------------------------------

	private Consts() {
		throw new AssertionError();
	}

	//----------------------------------------- Static Variables -------------------------------------

	public static final String JDBC_STR = "net.ucanaccess.jdbc.UcanaccessDriver";

	public static final String DB_FILE_NAME = "FlipCoinMiningDB.accdb";
	public static final String DB_FILE_PATH = getDBPath(); 

	public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILE_PATH + ";COLUMNORDER=DISPLAY";

	//----------------------------------------- Block Queries -------------------------------------

	public static final String SQL_Block_select = "SELECT * FROM tblBlock";
	
	public static final String SQL_Block_delete = "DELETE *\r\n" + 
			"FROM tblBlock\r\n" + 
			"WHERE tblBlock.blockAddress  = [1];\r\n" + 
			"";
	

	public static final String SQL_Block_insert = "INSERT INTO tblBlock ( blockAddress, creationDate, creationHour, size, previousBlock, minerAddress )\r\n" + 
			"VALUES ([1], [2], [3], [4], [5], [6]);\r\n" + 
			"";
	
	public static final String SQL_Block_update = "UPDATE tblBlock SET tblBlock.blockAddress = [1], tblBlock.creationDate = [2], tblBlock.creationHour = [3], tblBlock.size = [4], tblBlock.previousBlock = [5], tblBlock.minerAddress = [6];\r\n" + 
			"";


	//----------------------------------------- Bonus Queries -------------------------------------

	public static final String SQL_Bonus_select = "SELECT * FROM tblBonus";
	
	public static final String SQL_Bonus_delete = "DELETE *\r\n" + 
			"FROM tblBonus\r\n" + 
			"WHERE tblBonus.bonusNumber  = [1];\r\n" + 
			"";

	
	public static final String SQL_Bonus_insert = "INSERT INTO tblBonus ( bonusNumber, description )\r\n" + 
			"VALUES ([1], [2]);\r\n" + 
			"";
	
	public static final String SQL_Bonus_update = "UPDATE tblBonus SET tblBonus.bonusNumber = [1], tblBonus.description = [2];\r\n" + 
			"";
	
	
	//----------------------------------------- Business Company Queries -------------------------------------

	public static final String SQL_BusinessCompany_select = "SELECT * FROM tblBusinessCompany";
	
	public static final String SQL_BusinessCompany_delete = "DELETE *\r\n" + 
			"FROM tblBusinessCompany\r\n" + 
			"WHERE tblBusinessCompany.uniqueAddress  = [1];\r\n" + 
			"";
	
	public static final String SQL_BusinessCompany_insert = "INSERT INTO tblBusinessCompany ( uniqueAddress, contactName, contactPhone, contactEmail )\r\n" + 
			"VALUES ([1], [2], [3], [4]);\r\n" + 
			"";
	
	public static final String SQL_BusinessCompany_update = "UPDATE tblBusinessCompany SET tblBusinessCompany.uniqueAddress = [1], tblBusinessCompany.contactName = [2], tblBusinessCompany.contactPhone = [3], tblBusinessCompany.contactEmail = [4];\r\n" + 
			"";
	
	
	//----------------------------------------- Get Bonus Queries -------------------------------------

	public static final String SQL_GetBonus_select = "SELECT * FROM tblGetBonus";
	
	public static final String SQL_GetBonus_delete = "DELETE *\r\n" + 
			"FROM tblGetBonus\r\n" + 
			"WHERE (((tblGetBonus.uniqueAddress) = [1]) AND ((tblGetBonus.lotteryNumber) = [2]) AND ((tblGetBonus.bonusNumber) = [3]));\r\n" + 
			"";
	
	public static final String SQL_GetBonus_insert = "INSERT INTO tblGetBonus ( uniqueAddress, lotteryNumber, bonusNumber )\r\n" + 
			"VALUES ([1], [2], [3]);\r\n" + 
			"";
	
	public static final String SQL_GetBonus_update = "UPDATE tblGetBonus SET tblGetBonus.uniqueAddress = [1], tblGetBonus.lotteryNumber = [2], tblGetBonus.bonusNumber = [3];\r\n" + 
			"";
	
		
	//----------------------------------------- Lottery Queries -------------------------------------

	public static final String SQL_Lottery_select = "SELECT * FROM tblLottery";
	
	public static final String SQL_Lottery_delete = "DELETE *\r\n" + 
			"FROM tblLottery\r\n" + 
			"WHERE tblLottery.lotteryNumber  = [1];\r\n" + 
			"";
	
	public static final String SQL_Lottery_insert = "INSERT INTO tblLottery ( lotteryNumber, date, maxParticipants, numberOfWinners, numberOfBonuses )\r\n" + 
			"VALUES ([1], [2], [3], [4], [5]);\r\n" + 
			"";
	
	public static final String SQL_Lottery_update = "UPDATE tblLottery SET tblLottery.lotteryNumber = [1], tblLottery.date = [2], tblLottery.maxParticipants = [3],  tblLottery.numberOfWinners = [4], tblLottery.numberOfBonuses = [5];\r\n" + 
			"";
	
		
	//----------------------------------------- Miner Queries -------------------------------------

	
	public static final String SQL_Miner_select = "SELECT * FROM tblMiner";
	
	public static final String SQL_Miner_delete = "DELETE *\r\n" + 
			"FROM tblMiner\r\n" + 
			"WHERE tblMiner.uniqueAddress  = [1];\r\n" + 
			"";
	
	public static final String SQL_Miner_insert = "INSERT INTO tblMiner ( uniqueAddress, name, password, digitalProfit, email )\r\n" + 
			"VALUES ([1], [2], [3], [4], [5]);\r\n" + 
			"";
	
	public static final String SQL_Miner_update = "UPDATE tblMiner SET tblMiner.uniqueAddress = [1], tblMiner.name = [2], tblMiner.password = [3],  tblMiner.digitalProfit = [4], tblMiner.email = [5];\r\n" + 
			"";
		
	
	//----------------------------------------- Participant Queries -------------------------------------


	public static final String SQL_Participant_select = "SELECT * FROM tblParticipant";
	
	public static final String SQL_Participant_delete = "DELETE *\r\n" + 
			"FROM tblParticipant\r\n" + 
			"WHERE (((tblParticipant.lotteryNumber) = [1]) AND ((tblParticipant.uniqueAddress) = [2]));\r\n" + 
			"";
	
	public static final String SQL_Participant_insert = "INSERT INTO tblParticipant ( lotteryNumber, uniqueAddress, isWinner )\r\n" + 
			"VALUES ([1], [2], [3]);\r\n" + 
			"";
	
	public static final String SQL_Participant_update = "UPDATE tblParticipant SET tblParticipant.lotteryNumber = [1], tblParticipant.uniqueAddress = [2], tblParticipant.isWinner = [3];\r\n" + 
			"";
	
		
	//----------------------------------------- Riddle Queries -------------------------------------

	
	public static final String SQL_Riddle_select = "SELECT * FROM tblRiddle";
	
	public static final String SQL_Riddle_delete = "DELETE *\r\n" + 
			"FROM tblRiddle\r\n" + 
			"WHERE tblRiddle.riddleNumber  = [1];\r\n" + 
			"";
	/*
	public static final String SQL_Riddle_delete2 = "DELETE *\r" + 
			"FROM tblRiddle\r" + 
			"WHERE tblRiddle.riddleNumber  = [1];\r";
	
	public static final String SQL_Riddle_delete3 = "DELETE *\r FROM tblRiddle\r WHERE tblRiddle.riddleNumber  = [1];\r";*/
	
	public static final String SQL_Riddle_insert = "INSERT INTO tblRiddle ( riddleNumber, publishDate, publishHour, description, solutionTime, status, riddleLevel )\r\n" + 
			"VALUES ([1], [2], [3], [4], [5], [6], [7]);\r\n" + 
			"";
	
	public static final String SQL_Riddle_update = "UPDATE tblRiddle SET tblRiddle.riddleNumber = [1], tblRiddle.publishDate = [2], tblRiddle.publishHour = [3], tblRiddle.description = [4], tblRiddle.solutionTime = [5], tblRiddle.status = [6], tblRiddle.riddleLevel = [7];\r\n" + 
			"";
	
		
	//----------------------------------------- RiddleLevel Queries -------------------------------------

	
	public static final String SQL_RiddleLevel_select = "SELECT * FROM tblRiddleLevel";
	
	public static final String SQL_RiddleLevel_delete = "DELETE *\r\n" + 
			"FROM tblRiddleLevel\r\n" + 
			"WHERE tblRiddleLevel.levelCode  = [1];\r\n" + 
			"";
	
	public static final String SQL_RiddleLevel_insert = "INSERT INTO tblRiddleLevel ( levelCode, levelName, levelDifficulty, blockSize )\r\n" + 
			"VALUES ([1], [2], [3], [4]);\r\n" + 
			"";
	
	public static final String SQL_RiddleLevel_update = "UPDATE tblRiddleLevel SET tblRiddleLevel.levelCode = [1], tblRiddleLevel.levelName = [2], tblRiddleLevel.levelDifficulty = [3], tblRiddleLevel.blockSize = [4];\r\n" + 
			"";
		
	//----------------------------------------- Solution Queries -------------------------------------

	public static final String SQL_Solution_select = "SELECT * FROM tblSolution";
	
	public static final String SQL_Solution_delete = "DELETE *\r\n" + 
			"FROM tblParticipant\r\n" + 
			"WHERE (((tblSolution.riddleNumber) = [1]) AND ((tblSolution.solutionNumber) = [2]));\r\n" + 
			"";
	
	public static final String SQL_Solution_insert = "INSERT INTO tblSolution ( riddleNumber, solutionNumber, result )\r\n" + 
			"VALUES ([1], [2], [3]);\r\n" + 
			"";
	
	public static final String SQL_Solution_update = "UPDATE tblSolution SET tblSolution.riddleNumber = [1], tblSolution.solutionNumber = [2], tblSolution.result = [3];\r\n" + 
			"";
	
	//----------------------------------------- SolvedRiddle Queries -------------------------------------

	public static final String SQL_SolvedRiddle_select = "SELECT * FROM tblSolvedRiddle";
	
	public static final String SQL_SolvedRiddle_delete = "DELETE *\r\n" + 
			"FROM tblSolvedRiddle\r\n" + 
			"WHERE (((tblSolvedRiddle.uniqueAddress) = [1]) AND ((tblSolvedRiddle.riddleNumber) = [2]));\r\n" + 
			"";
	
	public static final String SQL_SolvedRiddle_insert = "INSERT INTO tblSolvedRiddle ( uniqueAddress, riddleNumber, time )\r\n" + 
			"VALUES ([1], [2], [3]);\r\n" + 
			"";
	
	public static final String SQL_SolvedRiddle_update = "UPDATE tblSolvedRiddle SET tblSolvedRiddle.uniqueAddress = [1], tblSolvedRiddle.riddleNumber = [2], tblSolvedRiddle.time = [3];\r\n" + 
			"";
	
	
	//----------------------------------------- Transaction Queries -------------------------------------

	public static final String SQL_Transaction_select = "SELECT * FROM tblTransaction";
	
	public static final String SQL_Transaction_delete = "DELETE *\r\n" + 
			"FROM tblTransaction\r\n" + 
			"WHERE tblTransaction.ID  = [1];\r\n" + 
			"";
	
	public static final String SQL_Transaction_insert = "INSERT INTO tblTransaction ( ID, size, type, fee, blockAddress, additionTime, additionDate )\r\n" + 
			"VALUES ([1], [2], [3], [4], [5], [6], [7]);\r\n" + 
			"";
	
	public static final String SQL_Transaction_update = "UPDATE tblTransaction SET tblTransaction.ID = [1], tblTransaction.size = [2], tblTransaction.type = [3], tblTransaction.fee = [4], tblTransaction.blockAddress = [5], tblTransaction.additionTime = [6], tblTransaction.additionDate = [7];\r\n" + 
			"";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//----------------------------------------- BALAGAN: -------------------------------------

	
	/*----------------------------------------- BLOCK QUERIES -----------------------------------------*/

	public static final String SQL_SEL_BLOCKS = "SELECT * FROM TblBlock";
	public static final String SQL_DEL_BLOCK = "{ call QryDelBlock(?) }";
	public static final String SQL_INS_BLOCK = "{ call QryInsBlock(?,?,?,?,?) }";
	public static final String SQL_UPD_BLOCK = "{ call QryUpdBlock(?,?,?,?,?) }";

	/*----------------------------------------- MINER QUERIES --------------------------------------------*/

	public static final String SQL_SEL_MINERS = "SELECT * FROM TblMiner";
	public static final String SQL_UPD_MINER = "{ call QryUpdMiner(?,?,?,?,?) }";
	public static final String SQL_INS_MINER = "{ call QryInsMiner(?,?,?,?,?) }";
	public static final String SQL_DEL_MINER = "{ call QryDelMiner(?) }";

	/*----------------------------------------- TRANSACTION QUERIES ------------------------------------*/

	public static final String SQL_DEL_TRANSACTION = "{ call qryDelOrderDetails(?) }";
	public static final String SQL_UPD_TRANSACTION = "{ call qryUpdOrderDetails(?,?,?,?,?) }";
	public static final String SQL_INS_TRANSACTION = "{ call qryInsOrderDetails(?,?,?,?,?) }";
	public static final String SQL_TRANS_NO_BLOCK = "SELECT *\r\n" + 
			"FROM TblTransaction\r\n" + 
			"WHERE (((TblTransaction.blockID) Is Null));\r\n" + 
			"";

	/*----------------------------------------- MORE QUERIES ----------------------------------------------*/

	public static final String SQL_BlockOwnerDetails = "SELECT TblBlock.ID, TblBlock.creationDate, TblBlock.size, TblBlock.previousBlock, TblBlock.owner, TblMiner.username, TblMiner.digitalProfit\r\n" + 
			"FROM (TblMiner INNER JOIN TblBlock ON TblMiner.address = TblBlock.owner) INNER JOIN TblCompany ON TblMiner.address = TblCompany.address;\r\n" + 
			"";

	public static final String SQL_MinerBlockSize = "SELECT TblMiner.address, TblBlock.ID, TblBlock.size\r\n" + 
			"FROM TblMiner INNER JOIN TblBlock ON TblMiner.address = TblBlock.owner;\r\n" + 
			"";

	public static final String SQL_TransactionPairs = "SELECT t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID\r\n" + 
			"FROM TblTransaction AS t1, TblTransaction AS t2\r\n" + 
			"GROUP BY t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID\r\n" + 
			"HAVING (((t1.ID)<>[t2].[ID] And (t1.ID)<[t2].[ID]))\r\n" + 
			"ORDER BY t1.ID, t2.ID;\r\n" + 
			"";

	public static final String SQL_TransactionPairsUnused = "SELECT t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID\r\n" + 
			"FROM TblTransaction AS t1, TblTransaction AS t2\r\n" + 
			"GROUP BY t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID\r\n" + 
			"HAVING (((t1.ID)<>[t2].[ID] And (t1.ID)<[t2].[ID]) AND t1.blockID IS NULL AND t2.blockID IS NULL)\r\n" + 
			"ORDER BY t1.ID, t2.ID;\r\n" + 
			"";

	/*public static final String SQL_TransactionPairsUnusedByBlockSize = "SELECT TblMiner.address, TblBlock.ID, TblBlock.size, t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID\r\n" + 
			"FROM (SELECT t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID\r\n" + 
			"FROM TblTransaction AS t1, TblTransaction AS t2\r\n" + 
			"GROUP BY t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID\r\n" + 
			"HAVING (((t1.ID)<>[t2].[ID] And (t1.ID)<[t2].[ID]) AND t1.blockID IS NULL AND t2.blockID IS NULL)\r\n" + 
			"ORDER BY t1.ID, t2.ID)  AS [%$##@_Alias], TblMiner INNER JOIN TblBlock ON TblMiner.address = TblBlock.owner\r\n" + 
			"WHERE (TblBlock.size>=(t1.size + t2.size));\r\n" + 
			"";*/

	public static final String SQL_TransactionPairsUnusedByBlockSize = "SELECT TblMiner.address, TblBlock.ID, TblBlock.size, t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID, (t1.size + t1.size) AS totalSize,  (t1.commission +  t2.commission) AS totalCommission\r\n" + 
			"FROM (SELECT t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID FROM TblTransaction AS t1, TblTransaction AS t2 GROUP BY t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID HAVING (((t1.ID)<>t2.ID And (t1.ID)<t2.ID) And t1.blockID Is Null And t2.blockID Is Null) ORDER BY t1.ID, t2.ID)  AS [%$##@_Alias], TblMiner INNER JOIN TblBlock ON TblMiner.address = TblBlock.owner\r\n" + 
			"WHERE (TblBlock.size>=(t1.size + t2.size));\r\n" + 
			"";


	public static final String SQL_UpdateTrans = "UPDATE tblTransaction SET TblTransaction.BlockID = [1]\r\n" + 
			"WHERE (((TblTransaction.TranscationID)=[2]));\r\n" + 
			"";



	public static final String SQL_REP = "SELECT TblBlock.size, [%$##@_Alias].t1.ID, [%$##@_Alias].t1.size, [%$##@_Alias].t1.type, [%$##@_Alias].t1.commission, [%$##@_Alias].t2.ID, [%$##@_Alias].t2.size, [%$##@_Alias].t2.type, [%$##@_Alias].t2.commission, (t1.size+t1.size) AS totalSize, (t1.commission+t2.commission) AS totalCommission\r\n" + 
			"FROM (SELECT t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID FROM TblTransaction AS t1, TblTransaction AS t2 GROUP BY t1.ID, t1.size, t1.type, t1.commission, t1.blockID, t2.ID, t2.size, t2.type, t2.commission, t2.blockID HAVING (((t1.ID)<>t2.ID And (t1.ID)<t2.ID) And t1.blockID Is Null And t2.blockID Is Null) ORDER BY t1.ID, t2.ID)  AS [%$##@_Alias], TblMiner INNER JOIN TblBlock ON TblMiner.address = TblBlock.owner\r\n" + 
			"WHERE (((TblBlock.size)>=([t1].[size]+[t2].[size])))\r\n" + 
			"ORDER BY TblBlock.size;\r\n" + 
			"";

	/*----------------------------------------- GET DB PATH -----------------------------------------*/


	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));

				return decoded + "/database/" + DB_FILE_NAME;
			}
			else {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));

				return decoded + "/entity/"+ DB_FILE_NAME;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}