package test.models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Expense_BLL;

import org.junit.Before;
import org.junit.Test;

import views.ViewExpensesPanel;

/**
 * 
 *
 */
public class Expense_BLLTest {
	
	private Expense_BLL expenseBLL;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		expenseBLL = new Expense_BLL();
	}
	

	/**
	 * should get all expenses from database
	 */
	@Test
	public void testViewExpense() throws Exception {

		ArrayList<ArrayList<String>> result = expenseBLL.viewExpenseByUsername("AppDemo");
		
		assertNotNull(result);
		
	}
	
	
	/**
	 * should add expense to database
	 */
	@Test
	public void testAddExpense() throws Exception {

		boolean result = expenseBLL.addExpense("AppDemo", "Credit Card", "Tim Hortons", "Purchase", "Concordia",
				(float) 7.99, "FOOD & DINNING", "Unpaid", "N/A", "2015-03-15", "2015-04-15");

		assertTrue(result);
		
	}

}
