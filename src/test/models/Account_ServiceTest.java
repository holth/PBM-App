package models;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class Account_ServiceTest {
	private Account_Service account_service;
	private void deleteExistedData() throws SQLException{
    	Connection con = DriverManager.getConnection("jdbc:sqlite:test.db");
		/*
		 * delete all exist data
		 */
		String query = "DELETE FROM User;";
		Statement delete = con.createStatement();
		delete.execute(query);
		query="DELETE FROM Account;";
		delete.execute(query);
		query="DELETE FROM Owns;";
		delete.execute(query);
		query="DELETE FROM Provider;";
		delete.execute(query);
		query="DELETE FROM Transactions;";
		delete.execute(query);
		query="INSERT INTO User(username,password) VALUES(\'TestIsExist\',\'TestIsExist\');";
		delete.execute(query);
		query="INSERT INTO Account (bank_account_id,balance,type,bank,address) VALUES(1,0.0,\'CASH\',\'CONCORDIA\',\'CONCORDIA\');";
		delete.execute(query);
		query="INSERT INTO Owns (user_id,account_id) VALUES(1,1);";
		delete.execute(query);
		con.close();
    }
	@Before
	public void setUp() throws Exception {
		this.account_service=new Account_Service();
		this.deleteExistedData();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetAccountIdByUsernameAndType() {
		try {
			int result=this.account_service.getAccountIdByUsernameAndType(1, "CASH");
			Assert.assertEquals(1, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetAccountIdByBankAccountIdAndType() {
		int result;
		try {
			result=this.account_service.getAccountIdByBankAccountIdAndType(1, "CASH");
			Assert.assertEquals(1, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetPaymentTypeByAccountId() {
		String result;
		try {
			result=this.account_service.getPaymentTypeByAccountId(1);
			Assert.assertEquals("CASH",result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
