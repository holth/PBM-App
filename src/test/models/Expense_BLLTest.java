package test.models;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import junit.framework.Assert;
import models.Expense_BLL;

import org.junit.Before;
import org.junit.Test;

public class Expense_BLLTest {
    private Expense_BLL expense_bll;
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
		query="INSERT INTO Provider(name,type,address) VALUES(\'CONCORDIA\' , \'BILL\' , \'CONCORDIA\');";
		delete.execute(query);
		query="INSERT INTO Account (bank_account_id,balance,type,bank,address) VALUES(1,0.0,\'CASH\',\'CONCORDIA\',\'CONCORDIA\');";
		delete.execute(query);
		query="INSERT INTO Owns (user_id,account_id) VALUES(1,1);";
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
		this.expense_bll=new Expense_BLL();
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testViewExpenseByUsername() throws Exception {
		ArrayList<ArrayList<String>> result=this.expense_bll.viewExpenseByUsername("TestIsExist");
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
	public void testViewExpenseByUsernameAndType() throws Exception {
		ArrayList<ArrayList<String>> result=this.expense_bll.viewExpenseByUsernameAndType("TestIsExist", "CASH");
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
	public void testViewExpenseBy() throws Exception {
		ArrayList<ArrayList<String>> result=this.expense_bll.viewExpenseBy("TestIsExist", "GUNS", "BILL", "PAYED");
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
		ArrayList<String> result=this.expense_bll.getCategories();
		Assert.assertEquals("GUNS", result.get(0));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetCategoriesByUsername() throws Exception {
		ArrayList<String> result=this.expense_bll.getCategoriesByUsername("TestIsExist");
		Assert.assertEquals("GUNS", result.get(0));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetCategoriesBy() throws Exception {
		ArrayList<String> result=this.expense_bll.getCategoriesBy("TestIsExist", "BILL", "PAYED");
		Assert.assertEquals("GUNS", result.get(0));
		result=this.expense_bll.getCategoriesBy("TestIsExist", "BILL", " ");
		if(result.isEmpty()){
			Assert.assertTrue(true);
			return;
		}
		Assert.assertTrue(false);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDeleteExpense() throws Exception {
		/*
		 * Delete an exist transaction
		 */
		boolean result=this.expense_bll.deleteExpense(2);
		Assert.assertTrue(result);
		/*
		 * Delete a transaction which already be deleted.
		 */
		result=this.expense_bll.deleteExpense(2);
		Assert.assertFalse(result);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateExpenseIntStringString() throws Exception {
		this.expense_bll.updateExpense(1,"status", "UNPAYED");
		Assert.assertEquals("UNPAYED", this.expense_bll.viewExpenseByUsername("TestIsExist").get(0).get(9));
		this.expense_bll.updateExpense(1,"status", "PAYED");
		Assert.assertEquals("PAYED", this.expense_bll.viewExpenseByUsername("TestIsExist").get(0).get(9));
	}



	@SuppressWarnings("deprecation")
	@Test
	public void testAddExpense() throws Exception {
		boolean result=this.expense_bll.addExpense("TestIsExist","CASH","CONCORDIA","BILL","CONCORDIA",(float)5.0,"GUNS","PAYED","NULL","2014-2-12","NULL");
		Assert.assertTrue(result);
	}

}
