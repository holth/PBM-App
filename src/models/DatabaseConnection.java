package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection{
	private static String createUser = "CREATE TABLE IF NOT EXISTS User("
			+ " id INTEGER  PRIMARY KEY,"
			+ " username VARCHAR(64) NOT NULL UNIQUE,"
			+ "password VARCHAR(64) NOT NULL);";
	private static String createAccount = "CREATE TABLE IF NOT EXISTS Account("
			+ " id INTEGER  PRIMARY KEY,"
			+ " bank_account_id INTEGER NOT NULL,"
			+ " balance FLOAT DEFAULT 0.0," + " type VARCHAR(10) NOT NULL,"
			+ " bank VARCHAR(64) NOT NULL,"
			+ " address VARCHAR(128) NOT NULL);";
	private static String createOwns = "CREATE TABLE IF NOT EXISTS Owns("
			+ " user_id INT NOT NULL," + " account_id INT NOT NULL,"
			+ " PRIMARY KEY(user_id, account_id),"
			+ " FOREIGN KEY(user_id) REFERENCES User(id)"
			+ " ON UPDATE CASCADE " + " ON DELETE CASCADE,"
			+ " FOREIGN KEY(account_id) REFERENCES Account(id)"
			+ " ON DELETE CASCADE" + " ON UPDATE CASCADE); ";
	private static String createProvider = "CREATE TABLE IF NOT EXISTS Provider("
			+ " id INTEGER  PRIMARY KEY," + " name VARCHAR(50) NOT NULL,"
			+ " type VARCHAR(10) NOT NULL," + " address VARCHAR(50) NOT NULL,"
			+ " UNIQUE(name,type,address));";
	private static String createTransactions = "CREATE TABLE IF NOT EXISTS Transactions("
			+ " id INTEGER PRIMARY KEY,"
			+ " account_id INT NOT NULL,"
			+ " provider_id INT NOT NULL,"
			+ " category VARCHAR(50) NOT NULL,"
			+ " status VARCHAR(10) NOT NULL,"
			+ " amount float DEFAULT 0.0,"
			+ " time VARCHAR(30) NOT NULL,"
			+ " due_date VARCHAR(20), "
			+ " duration VARCHAR(10),"
			+ " FOREIGN KEY(account_id) REFERENCES Account(id) "
			+ " ON DELETE CASCADE "
			+ " ON UPDATE CASCADE,"
			+ " FOREIGN KEY (provider_id) REFERENCES Provider(id) "
			+ " ON DELETE CASCADE " + " ON UPDATE CASCADE,"
			+ " FOREIGN KEY(category_id) REFERENCES Category(id) "
			+ " ON UPDATE CASCADE);";
        private static String dbDrive="jdbc:sqlite:test.db";
        private static Connection con=null;
	    private volatile static DatabaseConnection DBcon=new DatabaseConnection();
	    private DatabaseConnection(){
	    	try {
				con = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			Statement create = con.createStatement();
			create.executeUpdate(createUser);
			create.executeUpdate(createAccount);
			create.executeUpdate(createProvider);
			create.executeUpdate(createTransactions);
			create.executeUpdate(createOwns);
			con.close();
	    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    public static DatabaseConnection getInstance(){
	    	synchronized(DatabaseConnection.class){
	    		return DBcon;
	    	}
	    }
	    public Connection getConnection(){
	    	
	    	try {
				con = DriverManager.getConnection("jdbc:sqlite:test.db");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return con;
	    }
	    public void closeConnection(){
	    	try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

