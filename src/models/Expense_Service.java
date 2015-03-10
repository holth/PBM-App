package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Expense_Service {
	private static Connection con;
	private Account_Service account_service;

	private String capitalizeWords(String source) {
		source = source.toLowerCase();
		StringBuffer sb = new StringBuffer();
		char[] words = source.toCharArray();
		char prevc = ' ';
		for (char c : words) {
			if (c != ' ' && prevc == ' ') {
				sb.append(Character.toUpperCase(c));
			} else {
				sb.append(c);
			}
			prevc = c;
		}

		return sb.toString();
	}

	public Expense_Service() {
		account_service = new Account_Service();

	}

	/**
	 * For displaying provider name purpose
	 * 
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public String getProviderNameByProviderId(int providerId) throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT name FROM Provider WHERE id=" + providerId + ";";
		ResultSet result = select.executeQuery(query);
		if (result.next()) {
			String name = result.getString("name");
			result.close();
			return name;
		}
		throw new Exception("Reading error");
	}

	/**
	 * For displaying provider type
	 * 
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public String getProviderTypeByProviderId(int providerId) throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT type FROM Provider WHERE id=" + providerId + ";";
		ResultSet result = select.executeQuery(query);
		if (result.next()) {
			String name = result.getString("type");
			result.close();
			return name;
		}
		throw new Exception("Reading error");
	}

	public ArrayList<ArrayList<String>> getTransactionByUsernameAndType(
			int userId, String type) throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT * FROM Transactions WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id ="
				+ userId
				+ " AND account_id IN (SELECT account_id FROM Account WHERE type = \'"
				+ type + "\'));";
		select = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet result = select.executeQuery(query);
		result = select.executeQuery(query);
		ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
		while (result.next()) {
			int providerId = result.getInt("provider_id");
			ArrayList<String> sub = new ArrayList<String>();
			sub.add(result.getString("category"));
			sub.add(result.getString("id"));
			sub.add(type.toUpperCase());
			sub.add(this.capitalizeWords(this.getProviderNameByProviderId(providerId)));
			sub.add(this.capitalizeWords(this.getProviderAddressByProviderId(providerId)));
			sub.add(result.getString("amount"));
			sub.add(result.getString("time"));
			sub.add(result.getString("duration"));
			sub.add(result.getString("type"));
			sub.add(result.getString("status"));
			sub.add(result.getString("due_date"));

			arrayList.add(sub);
		}
		con.close();
		return arrayList;
	}

	/**
	 * Display all this user's Transactions
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ArrayList<String>> getTransactionByUsername(int userId)
			throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT * FROM Transactions WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id ="
				+ userId + ") "
				+ "ORDER BY category;";
		select = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet result = select.executeQuery(query);
		result = select.executeQuery(query);
		ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
		while (result.next()) {
			int providerId = result.getInt("provider_id");
			ArrayList<String> sub = new ArrayList<String>();
			sub.add(result.getString("category"));
			sub.add(result.getString("id"));
			sub.add(this.capitalizeWords(this.getProviderTypeByProviderId(providerId)));
			sub.add(this.capitalizeWords(this.getProviderNameByProviderId(providerId)));
			sub.add(this.capitalizeWords(this.getProviderAddressByProviderId(providerId)));
			sub.add(result.getString("amount"));
			sub.add(result.getString("time"));
			sub.add(result.getString("duration"));
			sub.add(this.account_service.getPaymentTypeByAccountId(result
					.getInt("account_id")));
			sub.add(result.getString("status"));
			sub.add(result.getString("due_date"));

			arrayList.add(sub);
		}
		con.close();
		return arrayList;
	}
	
	/**
	 * Display all this user's Transactions by category, provider type and expense status
	 * @param userId
	 * @param category
	 * @param providerType
	 * @param expenseStatus
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ArrayList<String>> getTransactionBy(
			int userId, String category, String providerType, String expenseStatus)
			throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		
		String query = "SELECT * FROM Transactions "
				+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ");";
		
		if(category.equalsIgnoreCase("all") 
				&& providerType.equalsIgnoreCase("all") 
					&& expenseStatus.equalsIgnoreCase("all")) {
			
			query = "SELECT * FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ");";
			
		} else if(category.equalsIgnoreCase("all") 
					&& !providerType.equalsIgnoreCase("all") 
						&& expenseStatus.equalsIgnoreCase("all")) {
			
			query = "SELECT * FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") "
							+ "AND provider_id IN (SELECT id FROM Provider WHERE type=\'" + providerType + "\');";
			
		} else if(category.equalsIgnoreCase("all") 
					&& providerType.equalsIgnoreCase("all") 
						&& !expenseStatus.equalsIgnoreCase("all")) {
			
			query = "SELECT * FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") "
							+ "AND status =\'" + expenseStatus + "\';";
	
		} else if(category.equalsIgnoreCase("all") 
					&& !providerType.equalsIgnoreCase("all") 
						&& !expenseStatus.equalsIgnoreCase("all")) {
			
			query = "SELECT * FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") "
							+ "AND provider_id IN (SELECT id FROM Provider WHERE type=\'" + providerType + "\') "
							+ "AND status =\'" + expenseStatus + "\';";
	
		} else if(!category.equalsIgnoreCase("all") 
					&& providerType.equalsIgnoreCase("all") 
						&& expenseStatus.equalsIgnoreCase("all")) {
			
			query = "SELECT * FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") "
							+ "AND category =\'" + category + "\';";
	
		} else if(!category.equalsIgnoreCase("all") 
					&& !providerType.equalsIgnoreCase("all") 
						&& expenseStatus.equalsIgnoreCase("all")) {
			
			query = "SELECT * FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") "
							+ "AND category =\'" + category + "\' "
							+ "AND provider_id IN (SELECT id FROM Provider WHERE type=\'" + providerType + "\');";
	
		} else if(!category.equalsIgnoreCase("all") 
					&& providerType.equalsIgnoreCase("all") 
						&& !expenseStatus.equalsIgnoreCase("all")) {
			
			query = "SELECT * FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") "
							+ "AND category =\'" + category + "\' "
							+ "AND status =\'" + expenseStatus + "\';";
	
		} else if(!category.equalsIgnoreCase("all") 
					&& !providerType.equalsIgnoreCase("all") 
						&& !expenseStatus.equalsIgnoreCase("all")) {
			
			query = "SELECT * FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") "
							+ "AND category =\'" + category + "\' "
							+ "AND provider_id IN (SELECT id FROM Provider WHERE type=\'" + providerType + "\') "
							+ "AND status =\'" + expenseStatus + "\';";
	
		}
		
		select = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet result = select.executeQuery(query);
		result = select.executeQuery(query);
		ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
		while (result.next()) {
			int providerId = result.getInt("provider_id");
			ArrayList<String> sub = new ArrayList<String>();
			sub.add(result.getString("category"));
			sub.add(result.getString("id"));
			sub.add(this.capitalizeWords(this.getProviderTypeByProviderId(providerId)));
			sub.add(this.capitalizeWords(this.getProviderNameByProviderId(providerId)));
			sub.add(this.capitalizeWords(this.getProviderAddressByProviderId(providerId)));
			sub.add(result.getString("amount"));
			sub.add(result.getString("time"));
			sub.add(result.getString("duration"));
			sub.add(this.account_service.getPaymentTypeByAccountId(result
					.getInt("account_id")));
			sub.add(result.getString("status"));
			sub.add(result.getString("due_date"));

			arrayList.add(sub);
		}
		con.close();
		return arrayList;
	}
	
	/**
	 * Get all unique categories
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> getCategories()
			throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT DISTINCT category FROM Transactions ORDER BY category;";
		select = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet result = select.executeQuery(query);
		result = select.executeQuery(query);
		ArrayList<String> list = new ArrayList<String>();
		while(result.next()) {
			list.add(result.getString("category"));
		}
		con.close();
		return list;
	}
	
	/**
	 * Get all unique categories by user
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> getCategoriesByUserId(int userId)
			throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT DISTINCT category "
				+ "FROM Transactions "
				+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id ="
				+ userId + ") "
				+ "ORDER BY category;";
		select = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet result = select.executeQuery(query);
		result = select.executeQuery(query);
		ArrayList<String> list = new ArrayList<String>();
		while(result.next()) {
			list.add(result.getString("category"));
		}
		con.close();
		return list;
	}
	
	/**
	 * Get unique categories by user, provider type and expense status
	 * @param userId
	 * @param providerType
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> getCategoriesBy(int userId, String providerType, String status)
			throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		
		String query = "";
		
		if(providerType.equalsIgnoreCase("all") && status.equalsIgnoreCase("all")) {
			
			query = "SELECT DISTINCT category "
					+ "FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") ORDER BY category;";
			
		} else if(!providerType.equalsIgnoreCase("all") && status.equalsIgnoreCase("all")) {
			
			query = "SELECT DISTINCT category "
					+ "FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") "
					+ "AND provider_id IN (SELECT id FROM Provider WHERE type =\'" + providerType + "\') ORDER BY category;";
			
		} else if(providerType.equalsIgnoreCase("all") && !status.equalsIgnoreCase("all")) {
			
			query = "SELECT DISTINCT category "
					+ "FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") "
					+ "AND status =\'" + status + "\' ORDER BY category;";
			
		} else if(!providerType.equalsIgnoreCase("all") && !status.equalsIgnoreCase("all")) {
			
			query = "SELECT DISTINCT category "
					+ "FROM Transactions "
					+ "WHERE account_id IN (SELECT account_id FROM Owns WHERE user_id =" + userId + ") "
					+ "AND provider_id IN (SELECT id FROM Provider WHERE type =\'" + providerType + "\') "
					+ "AND status =\'" + status + "\' ORDER BY category;";
		}
		
		select = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet result = select.executeQuery(query);
		result = select.executeQuery(query);
		ArrayList<String> list = new ArrayList<String>();
		while(result.next()) {
			list.add(result.getString("category"));
		}
		con.close();
		return list;
	}

	/**
	 * Delete expense with specific expenseId
	 * 
	 * @param expenseId
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteTransaction(int transactionId) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:test.db");
			Statement delete = con.createStatement();
			String query = "DELETE FROM Transactions WHERE id = "
					+ transactionId + ";";
			delete = con.createStatement();
			int result = delete.executeUpdate(query);
			con.close();
			if (result > 0)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Payment paid, unpaid...whatever
	 * 
	 * @param expenseId
	 * @param status
	 * @throws SQLException
	 */
	public void updateTransaction(int transactionId, String attribute,
			String value) throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement update = con.createStatement();
		if (!(attribute.equals("provider_id") || attribute.equals("account_id") || attribute
				.equals("amount"))) {

			String query = "UPDATE Transactions SET "
					+ attribute.trim().toLowerCase() + " = \'"
					+ value.toUpperCase().trim() + "\' WHERE id = "
					+ transactionId + ";";
			update = con.createStatement();
			update.executeUpdate(query);

		} else if (attribute.equals("amount")) {
			String query = "UPDATE Transactions SET amount = "
					+ Float.parseFloat(value) + " WHERE id = " + transactionId
					+ ";";
			update = con.createStatement();
			update.executeUpdate(query);
		} else {
			String query = "UPDATE Transactions SET "
					+ attribute.trim().toLowerCase() + " = "
					+ Integer.parseInt(value) + " WHERE id = " + transactionId
					+ ";";
			update = con.createStatement();
			update.executeUpdate(query);
		}
		con.close();
	}

	/**
	 * make a transaction
	 * 
	 * @param accountId
	 * @param providerName
	 * @param providerType
	 * @param address
	 * @param amount
	 * @param type
	 * @param status
	 * @param interval
	 * @return
	 * @throws Exception
	 */
	public boolean makeTransaction(int providerId, String accountType,
			int accountId, String address, float amount, String category,
			String status, String duration, String dateTime, String dueDate)
			throws Exception {
		String query = "INSERT INTO Transactions(account_id,provider_id,amount,category,status,time,due_Date,duration) VALUES("
				+ accountId
				+ ","
				+ providerId
				+ ","
				+ amount
				+ ",\'"
				+ category
				+ "\',\'"
				+ status
				+ "\',\'"
				+ dateTime
				+ "\',\'"
				+ dueDate
				+ "\' , \'" + duration + "\')";
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement insert = con.createStatement();
		int result = insert.executeUpdate(query);
		if (result > 0) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}

	public boolean updateTransaction(int transactionId, int providerId,
			int accountId, String accountType, String providerName,
			String providerType, String address, float amount, String category,
			String status, String duration, String dateTime, String dueDate)
			throws Exception {
		String query = "UPDATE Transactions " + "SET account_id = " + accountId
				+ ", " + "provider_id=" + providerId + ", " + "type= \'"
				+ category.toUpperCase().trim() + "\' , " + "status= \'"
				+ status.toUpperCase().trim() + " \' ," + "amount=" + amount
				+ " ," + "time= \'" + dateTime.toUpperCase().trim() + "\' ,"
				+ "duration=\'" + duration.toUpperCase().trim() + "\' , "
				+ "due_date= \'" + dueDate.toUpperCase().trim()
				+ "\' WHERE id=" + transactionId + ";";
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement update = con.createStatement();
		int result = update.executeUpdate(query);
		if (result == 1)
			return true;
		return false;

	}

	/**
	 * For displaying provider address purpose
	 * 
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public String getProviderAddressByProviderId(int providerId)
			throws Exception {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT address FROM Provider WHERE id=" + providerId
				+ ";";
		ResultSet result = select.executeQuery(query);
		if (result.next()) {
			String address = result.getString("address");
			result.close();
			return address;
		}
		throw new Exception("Reading error");
	}

	/**
	 * Get provider id by using the KEY combination of name type and address
	 * 
	 * @param providerName
	 * @param type
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public int getProviderIdByNameTypeAndAddress(String providerName,
			String type, String address) throws Exception {
		String query = "SELECT id FROM Provider WHERE name= \'"
				+ providerName.toUpperCase().trim() + "\' AND type = \'"
				+ type.toUpperCase().trim() + "\' AND address= \'"
				+ address.toUpperCase().trim() + "\' ;";
		// Get connection to DB
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		ResultSet result = select.executeQuery(query);
		result.next();
		int providerId = new Integer(result.getInt("id"));
		//System.out.println("This is provider id " + providerId);
		con.close();
		return providerId;
	}

	/**
	 * Add a provider by using name, type and address and return the added
	 * provider's ID IF Provider already exist, it will directly return the
	 * existing provider ID
	 * 
	 * @param providerName
	 * @param providerType
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public int addProvider(String providerName, String providerType,
			String address) throws Exception {
		if (isProviderExist(providerName, providerType, address)) {
			// if this provider already exist, directly return current provider
			// id to the caller
			return this.getProviderIdByNameTypeAndAddress(providerName,
					providerType, address);
		}
		// Get connection to DB
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement insert = con.createStatement();
		String query = "INSERT INTO Provider(name,type,address) VALUES(\'"
				+ providerName.toUpperCase().trim() + "\' , \'"
				+ providerType.toUpperCase().trim() + "\' , \'"
				+ address.toUpperCase().trim() + "\');";
		int result = insert.executeUpdate(query);
		if (result == 0)
			throw new Exception("Create provider error");
		int providerId = getProviderIdByNameTypeAndAddress(providerName
				.toUpperCase().trim(), providerType.toUpperCase().trim(),
				address.toUpperCase().trim());
		// Close connection
		con.close();
		return providerId;
	}

	/**
	 * Check whether specific provider is exist
	 * 
	 * @param providerName
	 * @param type
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public boolean isProviderExist(String providerName, String type,
			String address) throws Exception {
		// Get connection to DB
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement select = con.createStatement();
		String query = "SELECT COUNT(*) AS num FROM Provider WHERE name= \'"
				+ providerName.toUpperCase().trim() + "\' AND type = \'"
				+ type.toUpperCase().trim() + "\' AND address= \'"
				+ address.toUpperCase().trim() + "\' ;";
		ResultSet result = select.executeQuery(query);
		result.next();
		int num = result.getInt("num");
		//System.out.println("The num of provider query is " + num);
		// Connection close
		con.close();
		if (num > 0)
			return true;
		return false;
	}

}
