package test.models;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import junit.framework.Assert;
import models.Expense_Service;

import org.junit.Before;
import org.junit.Test;

public class Expense_ServiceTest {
    private Expense_Service expense_service;
    private void deleteExistedDataAndAddTestData() throws SQLException{
    	Connection con;
		con = DriverManager.getConnection("jdbc:sqlite:test.db");
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
		query="INSERT INTO Provider(name,type,address) VALUES(\'CONCORDIA\' , \'BILL\' , \'CONCORDIA\');";
		delete.execute(query);
		query="INSERT INTO Transactions(account_id,provider_id,amount,category,status,time,due_Date,duration) VALUES(1,1,2.0,\'GUNS\',\'PAYED\',\'2015-12-31\',\'NULL\' , \'NULL\')";
		delete.execute(query);
		query="INSERT INTO Transactions(account_id,provider_id,amount,category,status,time,due_Date,duration) VALUES(1,1,3.0,\'GUNS\',\'PAYED\',\'2015-12-31\',\'NULL\' , \'NULL\')";
		delete.execute(query);
		query="INSERT INTO Transactions(account_id,provider_id,amount,category,status,time,due_Date,duration) VALUES(1,1,4.0,\'GUNS\',\'PAYED\',\'2015-12-31\',\'NULL\' , \'NULL\')";
		delete.execute(query);
		con.close();
    }
	@Before
	public void setUp() throws Exception {
		this.deleteExistedDataAndAddTestData();
		this.expense_service=new Expense_Service();
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testGetProviderNameByProviderId() {
		try {
			String result=this.expense_service.getProviderNameByProviderId(1);
			Assert.assertEquals("CONCORDIA",result );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetProviderTypeByProviderId() {
		try {
			String result=this.expense_service.getProviderTypeByProviderId(1);
			Assert.assertEquals("BILL", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetTransactionByUsernameAndType() throws Exception {
		ArrayList<ArrayList<String>> result=this.expense_service.getTransactionByUsernameAndType(1, "CASH");
		Assert.assertEquals("GUNS", result.get(0).get(0));
		Assert.assertEquals(1, Integer.parseInt(result.get(0).get(1)));
		Assert.assertEquals("CASH", result.get(0).get(2));
		Assert.assertEquals("Concordia", result.get(0).get(3));
		Assert.assertEquals("Concordia", result.get(0).get(4));
		Assert.assertEquals("2.0", result.get(0).get(5));
		Assert.assertEquals("2015-12-31", result.get(0).get(6));
		Assert.assertEquals("NULL", result.get(0).get(7));
		Assert.assertEquals("NULL", result.get(0).get(9));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetTransactionByUsername() throws Exception {
		//Test able to get info with correct info provided
		ArrayList<ArrayList<String>> result=this.expense_service.getTransactionByUsername(1);
		Assert.assertEquals("GUNS", result.get(0).get(0));
		Assert.assertEquals(1, Integer.parseInt(result.get(0).get(1)));
		Assert.assertEquals("Bill", result.get(0).get(2));
		Assert.assertEquals("Concordia", result.get(0).get(3));
		Assert.assertEquals("Concordia", result.get(0).get(4));
		Assert.assertEquals("2.0", result.get(0).get(5));
		Assert.assertEquals("2015-12-31", result.get(0).get(6));
		Assert.assertEquals("NULL", result.get(0).get(7));
		Assert.assertEquals("CASH", result.get(0).get(8));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetTransactionBy() throws Exception {
		//Test able to get info with correct info provided
		ArrayList<ArrayList<String>> result=this.expense_service.getTransactionBy(1, "GUNS", "BILL", "PAYED");
		Assert.assertEquals("GUNS", result.get(0).get(0));
		Assert.assertEquals(1, Integer.parseInt(result.get(0).get(1)));
		Assert.assertEquals("Bill", result.get(0).get(2));
		Assert.assertEquals("Concordia", result.get(0).get(3));
		Assert.assertEquals("Concordia", result.get(0).get(4));
		Assert.assertEquals("2.0", result.get(0).get(5));
		Assert.assertEquals("2015-12-31", result.get(0).get(6));
		Assert.assertEquals("NULL", result.get(0).get(7));
		Assert.assertEquals("CASH", result.get(0).get(8));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetCategories() throws Exception {
		//Get the all the category
		ArrayList<String> result=this.expense_service.getCategories();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals("GUNS", result.get(0));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetCategoriesByUserId() throws Exception {
		ArrayList<String> result=this.expense_service.getCategoriesByUserId(1);
		Assert.assertEquals("GUNS", result.get(0));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetCategoriesBy() throws Exception {
		/*
		 * Get a categories with correct information 
		 */
		ArrayList<String> result=this.expense_service.getCategoriesBy(1, "BILL", "PAYED");
		//Expect return correct info
		Assert.assertEquals("GUNS", result.get(0));
		/*
		 * Get a categories with fake info
		 */
		result=this.expense_service.getCategoriesBy(1, "BILL", " ");
		//Expect nothing return
		if(result.isEmpty()){
			Assert.assertTrue(true);
			return;
		}
		Assert.assertTrue(false);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDeleteTransaction() throws SQLException {
		/*
		 * Delete an exist transaction
		 */
		boolean result=this.expense_service.deleteTransaction(2);
		Assert.assertTrue(result);
		/*
		 * Delete a transaction which already be deleted.
		 */
		result=this.expense_service.deleteTransaction(2);
		Assert.assertFalse(result);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateTransactionIntStringString() throws Exception {
		ArrayList<ArrayList<String>> result=this.expense_service.getTransactionBy(1, "GUNS", "BILL", "PAYED");
		Assert.assertEquals("4.0", result.get(2).get(5));
		this.expense_service.updateTransaction(3,"amount", "2.0");
		result=this.expense_service.getTransactionBy(1, "GUNS", "BILL", "PAYED");
		Assert.assertEquals("2.0", result.get(2).get(5));
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testMakeTransaction() throws Exception {
		boolean result=this.expense_service.makeTransaction(1, "CASH", 1, "CONCORDIA", (float) 5.0, "GUNS", "PAYED", "NULL", "2015-12-31", "NULL");
		Assert.assertTrue(result);
		
	}

   

	@SuppressWarnings("deprecation")
	@Test
	public void testGetProviderAddressByProviderId() throws Exception {
		String result=this.expense_service.getProviderAddressByProviderId(1);
		Assert.assertEquals("CONCORDIA", result);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetProviderIdByNameTypeAndAddress() throws Exception {
		int result=this.expense_service.getProviderIdByNameTypeAndAddress("CONCORDIA", "BILL", "CONCORDIA");
		Assert.assertEquals(1, result);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testAddProvider() throws Exception {
		int result=this.expense_service.addProvider("CONCORDIA", "PURCHASE", "CONCORDIA");
		Assert.assertEquals(2, result);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testIsProviderExist() throws Exception {
		boolean result=this.expense_service.isProviderExist("CONCORDIA", "BILL", "CONCORDIA");
		Assert.assertTrue(result);
		result=this.expense_service.isProviderExist("timHorton", "BILL", "CONCORDIA");
		Assert.assertFalse(result);
	}

}
