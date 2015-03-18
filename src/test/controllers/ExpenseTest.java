package test.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controllers.BillExpense;
import controllers.Expense;
import controllers.PurchaseExpense;


public class ExpenseTest {
	
	private Expense expense;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * should add an Expense of Purchase type
	 * @throws Exception 
	 */
	@Test
	public void testAddPurchaseExpense() throws Exception {
		
		expense = new PurchaseExpense("FOOD & DINNING", "Tim Hortons", "Concordia", "2015-03-15", 
				(float) 7.99, "Credit Card", "Unpaid", "2015-04-15");
		
		boolean result = expense.save("AppDemo");
		assertTrue(result);
	}
	
	/**
	 * should add an Expense of Bill type
	 * @throws Exception 
	 */
	@Test
	public void testAddBillExpense() throws Exception {
		
		expense = new BillExpense("FOOD & DINNING", "Tim Hortons", "Concordia", 
				(float) 7.99, "Monthly", "Credit Card", "Unpaid", "2015-04-15");
		
		boolean result = expense.save("AppDemo");
		assertTrue(result);
	}

}
