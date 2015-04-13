/**
 * 
 */
package models;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Jianhui Zhu
 *
 */
public class User_ServiceTest {
    private User_Service user_Service;
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
		con.close();
    }
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.user_Service=new User_Service();
		this.deleteExistedData();
	}

	/**
	 * Test method for {@link models.User_Service#insertNewUser(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testInsertNewUser() {
		try {
			boolean result;
			/*
			 * A Not exist user can be Inserted
			 */
			result=this.user_Service.insertNewUser("TestInsert", "TestInsert");
			Assert.assertTrue(result);
			/*
			 * A existed user cannot be inserted
			 */
			try{
			this.user_Service.insertNewUser("TestInsert","**");
			}catch(SQLException e){
				assert(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link models.User_Service#isUserExist(java.lang.String)}.
	 * @throws SQLException 
	 */
	@Test
	public void testIsUserExist() throws SQLException {
		boolean result;
		result=this.user_Service.isUserExist("TestIsExist");
		Assert.assertTrue(result);
		result=this.user_Service.isUserExist("TestIsNotExist");
		Assert.assertFalse(result);
	}

	/**
	 * Test method for {@link models.User_Service#isUsernameMatchPassword(java.lang.String, java.lang.String)}.
	 * @throws SQLException 
	 */
	@Test
	public void testIsUsernameMatchPassword() throws SQLException {
		boolean result;
		/*
		 * An already match username and password
		 */
		result=this.user_Service.isUsernameMatchPassword("TestIsExist", "TestIsExist");
		Assert.assertTrue(result);
		/*
		 *A real username and fake password 
		 */
		result=this.user_Service.isUsernameMatchPassword("TestInsert", "ThisIsAJoke");
		Assert.assertFalse(result);
	}

	/**
	 * Test method for {@link models.User_Service#getUserIdByUsername(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetUserIdByUsername() throws Exception {
		int result;
		result=this.user_Service.getUserIdByUsername("TestIsExist");
		Assert.assertEquals(result, 1);
	}

	/**
	 * Test method for {@link models.User_Service#addBankAccount(int, int, float, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testAddBankAccount() throws Exception {
		boolean result;
		result=this.user_Service.addBankAccount(1, 1, (float) 0.0, "CASH", "Concordia", "Concordia");
		Assert.assertTrue(result);
	}

}
