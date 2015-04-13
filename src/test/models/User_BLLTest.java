package models;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class User_BLLTest {
	private User_BLL user_bll;
	 private void deleteExistedData() throws Exception{
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
			query="INSERT INTO User(username,password) VALUES(\'TestIsExist\',\'"+this.user_bll.HashToSHA256("TestIsExist")+"\');";
			delete.execute(query);
			con.close();
	    }
		/**
		 * @throws java.lang.Exception
		 */
		@Before
		public void setUp() throws Exception {
			this.user_bll=new User_BLL();
			this.deleteExistedData();
		}

	@SuppressWarnings("deprecation")
	@Test
	public void testCreateUser() throws Exception {
		boolean result=this.user_bll.createUser("TestCreate", "TestCreate");
		Assert.assertTrue(result);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testIsUserExist() throws Exception {
		boolean result=this.user_bll.isUserExist("TestIsExist");
		Assert.assertTrue(result);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testValidatePin() throws Exception {
		boolean result=this.user_bll.validatePin("TestIsExist", "TestIsExist");
		Assert.assertTrue(result);
		result=this.user_bll.validatePin("TestIsExist", "TestFalse");
		Assert.assertFalse(result);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testHashToSHA256() throws Exception {
		String result=this.user_bll.HashToSHA256("c");
		Assert.assertEquals("2E7D2C03A9507AE265ECF5B5356885A53393A2029D241394997265A1A25AEFC6", result);
	}

}
