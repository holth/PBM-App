package test.integration;

import java.util.ArrayList;

import test.helpers.*;
import views.*;
import controllers.*;
import models.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ViewExpenseTest extends TestHelper{
	
	private ExpensesFrame expensesFrame;
	private ExpensesController expensesController;
	private Expense_BLL expenseBLL;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		expensesFrame = new ExpensesFrame("testName");
		expensesController = new ExpensesController(expensesFrame);
		expenseBLL = new Expense_BLL();
	}

	/**
	 * Test case: View Expense
	 * @throws Exception 
	 */
	@Test
	public void viewExpense() throws Exception {
		
		//the view should display expensesFrame
		expensesFrame = (ExpensesFrame) getField(expensesController, "expensesFrame");
		assertTrue(expensesFrame.isShowing());
		assertTrue(((ViewExpensesPanel) getField(expensesFrame, "expensesPanel")).isShowing());
		
		
		//the model should provide all the expenses to the user
		ArrayList<ArrayList<String>> resultExpense = expenseBLL.viewExpenseByUsername("testName");
		assertNotNull(resultExpense);
		
		resultExpense = expenseBLL.viewExpenseBy("testName", "All", "All", "All");
		assertNotNull(resultExpense);
	}

}
