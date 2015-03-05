package models;

import java.sql.*;

public class Account_Service {
	private static Connection con;
	/**
	 * ONLY FOR INCREMENT 1 TEMPORARY Get account id (NOT bank_acount_id) from
	 * the account table By username and type Since in first temp increment,
	 * user only has 3 default account "CASH, CREDIT DEBIT" and CANNOT create
	 * any account THIS FUNCTION WORKS
	 * 
	 * @param username
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public int getAccountIdByUsernameAndType(int userId, String type)
			throws Exception {
		String query = "SELECT id FROM Account WHERE type = \'"
				+ type.toUpperCase().trim()
				+ "\' AND id IN("
				+ " SELECT account_id FROM Owns WHERE user_id="+userId+");";
		// Get connection
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		ResultSet result = select.executeQuery(query);
		if (result.next()) {
			int accountId = result.getInt("id");
			// Close connection
			con.close();
			//System.out.println("This is account id" + accountId);
			return accountId;
		}
		throw new Exception("Error account_id READING");
	}

	/**
	 * This for future Get user account id by bank account id and type, here
	 * assume bank account id is REAL, The conclusion is bank_account_id is
	 * UNIQUE EXCEPT CASH
	 * 
	 * @param bankAccountId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public int getAccountIdByBankAccountIdAndType(int bankAccountId, String type)
			throws Exception {
		// Get connection to DB
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT * FROM Account WHERE bank_account_id = "
				+ bankAccountId + " AND type = \'" + type.toUpperCase().trim()
				+ "\';";
		ResultSet result = select.executeQuery(query);
		if (result.next()) {
			int accountId = result.getInt("id");
			// Close connection to DB
			con.close();
			return accountId;
		}
		throw new Exception("Error account_id READING");
	}

	

	/**
	 * For displaying payment type purpose
	 * 
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public String getPaymentTypeByAccountId(int accountId) throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT type FROM Account WHERE id=" + accountId + ";";
		ResultSet result = select.executeQuery(query);
		if (result.next()) {
			String type = result.getString("type");
			con.close();
			return type;
		}
		throw new Exception("Read ACCOUNT TYPE ERROR");
	}
	/**
	 * Check whether given user exist in system
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 */

}
