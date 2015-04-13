package models;

import java.security.MessageDigest;
import java.sql.*;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
public class User_Service {
	private Account_Service account_service;
	private static Connection con;
	
	public User_Service() throws Exception{
		this.account_service=new Account_Service();
	}
	
	public boolean insertNewUser(String username, String password) throws Exception {
		con  = DatabaseConnection.getInstance().getConnection();
		/*
		 * Create CASH Account for user
		 */
		String query = "INSERT INTO User (username,password) VALUES(\'"
				+ username + "\',\'" + password
				+ "\');";
		Statement insert = con.createStatement();
		if (insert.executeUpdate(query) != 0) {
			int userId = this.getUserIdByUsername(username);
			if (userId == 0)
				throw new Exception("create user problem");
			// Add CASH Account
			addBankAccount(0, userId, (float) 0.0, "CASH", "NONE", "NONE");
			// Add Debit Account
			addBankAccount(1, userId, (float) 0.0, "DEBIT", "NONE", "NONE");
			// Add Credit Account
			addBankAccount(2, userId, (float) 0.0, "CREDIT CARD", "NONE",
					"NONE");
			con=null;
			DatabaseConnection.getInstance().closeConnection();
			return true;
		} else {
			con=null;
			DatabaseConnection.getInstance().closeConnection();
			return false;
		}

	}
	
	public boolean isUserExist(String username) throws SQLException {
		try {
			// Get connection to DB
			con = DatabaseConnection.getInstance().getConnection();
			Statement select = con.createStatement();
			String query = "SELECT COUNT(*) AS num FROM User WHERE username= \'"
					+ username + "\';";
			select = con.createStatement();
			ResultSet result = select.executeQuery(query);
			result.next();
			int count = result.getInt("num");
			con=null;
			DatabaseConnection.getInstance().closeConnection();
			if (count != 0)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * For login purpose, check whether username and password match
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean isUsernameMatchPassword(String username, String password)
			throws SQLException {
		try {
			con = DatabaseConnection.getInstance().getConnection();
			Statement select = con.createStatement();
			String query = "SELECT COUNT(*) AS num FROM User WHERE username= \'"
					+ username
					+ "\' AND password =\'"
					+ password + "\' ;";
			select = con.createStatement();
			ResultSet result = select.executeQuery(query);
			int count = result.getInt("num");
			con=null;
			DatabaseConnection.getInstance().closeConnection();
			if (count != 0)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public int getUserIdByUsername(String username) throws Exception {
		// Get connection to DB
		con = DatabaseConnection.getInstance().getConnection();
		Statement select = con.createStatement();
		String query = "SELECT id FROM User WHERE username = \'" + username
				+ "\' ;";
		//System.out.println(query);
		ResultSet result = select.executeQuery(query);
		if (result.next()) {
			int userId = result.getInt("id");
			// Connection close

			con=null;
			DatabaseConnection.getInstance().closeConnection();
			return userId;
		}
		throw new Exception("Error user_id READING");
	}
	/**
	 * Add a new bank account into account table with specific information
	 * Specify the owner of this account by specific user_id
	 * 
	 * @param bankAccountId
	 * @param userId
	 * @param accountType
	 * @param bank
	 * @param address
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addBankAccount(int bankAccountId, int userId, float balance,
			String accountType, String bank, String address) throws Exception {
		boolean accountVarification = isBankAccountExist(bankAccountId,
				accountType);
		// If account with the same id and type already exist, no need to create
		if (accountVarification)
			return false;
		try {
			// Get connection to DB
			con = DatabaseConnection.getInstance().getConnection();
			Statement insert = con.createStatement();
			String query = "INSERT INTO Account (bank_account_id,balance,type,bank,address) VALUES("
					+ bankAccountId
					+ ","
					+ balance
					+ ",\'"
					+ accountType
					+ "\',\'" + bank + "\',\'" + address + "\')";
			insert = con.createStatement();
			int result = insert.executeUpdate(query);
			// System.out.println("The result for create account is " + result);
			if (result != 0) {
				//int accountId = this.account_service.getAccountIdByBankAccountIdAndType(
						//bankAccountId, accountType.toUpperCase().trim());
				query="SELECT MAX(id)  AS id FROM Account;";
				ResultSet resultSet=insert.executeQuery(query);
				int accountId;
				if(resultSet.next()){
					accountId=resultSet.getInt("id");
				}
				else
					throw new Exception("Get id error");
				query = "INSERT INTO Owns (user_id,account_id) VALUES("
						+ userId + "," + accountId + ")";
				result = insert.executeUpdate(query);
				// System.out.println("The result for create owns is " +
				// result);
			}

			con=null;
			DatabaseConnection.getInstance().closeConnection();
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			con=null;
			DatabaseConnection.getInstance().closeConnection();
			return false;
		}
	}
	/**
	 * By using bank account id and type of that bank account Check whether
	 * specific account exist
	 * 
	 * @param bankAccountId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private boolean isBankAccountExist(int bankAccountId, String type)
			throws Exception {

		con = DatabaseConnection.getInstance().getConnection();
		Statement select = con.createStatement();
		String query = "SELECT * FROM Account WHERE bank_account_id = "
				+ bankAccountId + " AND type = \'" + type.toUpperCase().trim()
				+ "\' ;";
		int result = select.executeUpdate(query);
		con=null;
		DatabaseConnection.getInstance().closeConnection();
		if (result > 0)
			return true;
		return false;
	}

}
