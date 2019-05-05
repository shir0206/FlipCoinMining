package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;

import entity.Consts;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class DataLogic {

	// ----------------------------------------- SINGLETON TO DB -----------------------------------------

	private static DataLogic instance;

	public static DataLogic getInstance() {
		if (instance == null)
			instance = new DataLogic();
		return instance;
	}

	// ----------------------------------------- Report Methods -------------------------------------

	public void TransactionsPairsReport6() throws ClassNotFoundException, SQLException, JRException {
		Connection conn = DriverManager.getConnection(Consts.CONN_STR);
		JasperPrint print = JasperFillManager
				.fillReport(getClass().getResourceAsStream("../boundary/RepTransactionsPairs.jasper"), null, conn);
		JFrame frame = new JFrame("Transactions Report");
		frame.getContentPane().add(new JRViewer(print));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.pack();
		frame.setVisible(true);

	}

	/**
	 * producing trans pairs report
	 * 
	 * @param date
	 *            of the report
	 * @return the report itself
	 */
	public JFrame TransactionsPairsReport() {
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("../boundary/RepTransactionsPairs.jasper"), null, conn);

				JFrame frame = new JFrame("Transaction Status Report");
				frame.getContentPane().add(new JRViewer(print));
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.pack();
				return frame;
			} catch (SQLException | JRException | NullPointerException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

}
