package models;

import java.security.MessageDigest;
import java.sql.*;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
public class User_Service {
	private Account_Service account_service;
	private static Connection con;
	private String createUser = "CREATE TABLE IF NOT EXISTS User("
			+ " id INTEGER  PRIMARY KEY,"
			+ " username VARCHAR(64) NOT NULL UNIQUE,"
			+ "password VARCHAR(64) NOT NULL);";
	private String createAccount = "CREATE TABLE IF NOT EXISTS Account("
			+ " id INTEGER  PRIMARY KEY,"
			+ " bank_account_id INTEGER NOT NULL,"
			+ " balance FLOAT DEFAULT 0.0," + " type VARCHAR(10) NOT NULL,"
			+ " bank VARCHAR(64) NOT NULL,"
			+ " address VARCHAR(128) NOT NULL);";
	private String createOwns = "CREATE TABLE IF NOT EXISTS Owns("
			+ " user_id INT NOT NULL," + " account_id INT NOT NULL,"
			+ " PRIMARY KEY(user_id, account_id),"
			+ " FOREIGN KEY(user_id) REFERENCES User(id)"
			+ " ON UPDATE CASCADE " + " ON DELETE CASCADE,"
			+ " FOREIGN KEY(account_id) REFERENCES Account(id)"
			+ " ON DELETE CASCADE" + " ON UPDATE CASCADE); ";
	private String createProvider = "CREATE TABLE IF NOT EXISTS Provider("
			+ " id INTEGER  PRIMARY KEY," + " name VARCHAR(50) NOT NULL,"
			+ " type VARCHAR(10) NOT NULL," + " address VARCHAR(50) NOT NULL,"
			+ " UNIQUE(name,type,address));";
	private String createTransactions = "CREATE TABLE IF NOT EXISTS Transactions("
			+ " id INTEGER PRIMARY KEY,"
			+ " account_id INT NOT NULL,"
			+ " provider_id INT NOT NULL,"
			+ " type VARCHAR(10) NOT NULL,"
			+ " status VARCHAR(10) NOT NULL,"
			+ " amount float DEFAULT 0.0,"
			+ " time VARCHAR(30) NOT NULL,"
			+ " due_date VARCHAR(20), "
			+ " duration VARCHAR(10),"
			+ " FOREIGN KEY(account_id) REFERENCES Account(id) "
			+ " ON DELETE CASCADE "
			+ " ON UPDATE CASCADE,"
			+ " FOREIGN KEY (provider_id) REFERENCES Provider(id) "
			+ " ON DELETE CASCADE " + " ON UPDATE CASCADE);";
	public User_Service() throws Exception{
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement create = con.createStatement();
		create.executeUpdate(createUser);
		create.executeUpdate(createAccount);
		create.executeUpdate(createProvider);
		create.executeUpdate(createTransactions);
		create.executeUpdate(createOwns);
		con.close();

		this.account_service=new Account_Service();
	}
	
	public boolean insertNewUser(String username, String password) throws Exception {
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
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
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}

	}
	
	public boolean isUserExist(String username) throws SQLException {
		try {
			// Get connection to DB
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:test.db");
			Statement select = con.createStatement();
			String query = "SELECT COUNT(*) AS num FROM User WHERE username= \'"
					+ username + "\';";
			select = con.createStatement();
			ResultSet result = select.executeQuery(query);
			result.next();
			int count = result.getInt("num");
			con.close();
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
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:test.db");
			Statement select = con.createStatement();
			String query = "SELECT COUNT(*) AS num FROM User WHERE username= \'"
					+ username
					+ "\' AND password =\'"
					+ password + "\' ;";
			select = con.createStatement();
			ResultSet result = select.executeQuery(query);
			int count = result.getInt("num");
			con.close();
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
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT id FROM User WHERE username = \'" + username
				+ "\' ;";
		//System.out.println(query);
		ResultSet result = select.executeQuery(query);
		if (result.next()) {
			int userId = result.getInt("id");
			// Connection close
			con.close();
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
	 * @return
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
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:test.db");
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
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			con.close();
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

		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT * FROM Account WHERE bank_account_id = "
				+ bankAccountId + " AND type = \'" + type.toUpperCase().trim()
				+ "\' ;";
		int result = select.executeUpdate(query);
		con.close();
		if (result > 0)
			return true;
		return false;
	}

}
