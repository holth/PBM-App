package test.integration;


import test.helpers.*;
import views.*;
import controllers.*;
//import models.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddExpenseTest extends TestHelper{
	
	private ExpensesFrame expensesFrame;
	private ExpensesController expensesController;
	
	private Expense purchaseExpense;
	private Expense billExpense;
	
	//private Expense_BLL expenseBLL;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		expensesFrame = new ExpensesFrame("testName");
		expensesController = new ExpensesController(expensesFrame);
		//expenseBLL = new Expense_BLL();
	}

	/**
	 * Test case: Add Purchase Expense
	 * @throws Exception 
	 */
	@Test
	public void addPurchaseExpense() throws Exception {
		
		expensesController.addExpense();
		
		//the expensesFrame should display AddExpensePanel
		expensesFrame = (ExpensesFrame) getField(expensesController, "expensesFrame");
		assertTrue(((AddExpensePanel) getField(expensesFrame, "purchaseExpensePanel")).isShowing());
		
		
		//the model should store a new expense of purchase type
		purchaseExpense = new PurchaseExpense("FOOD & DINNING", "Tim Hortons", "Concordia", "2015-03-15", 
				(float) 7.99, "Credit Card", "Unpaid", "2015-04-15");
		assertTrue(purchaseExpense.save("testName"));
		
		
		//the view should update to display the newly added expense
		ViewExpensesPanel view = (ViewExpensesPanel) getField(expensesController, "viewExpensesPanel");
		int sizeBefore = view.getTable().getRowCount();
		
		expensesController.saveExpense(purchaseExpense);
		
		view = (ViewExpensesPanel) getField(expensesController, "viewExpensesPanel");
		int sizeAfter = view.getTable().getRowCount();
		
		assertNotEquals(sizeBefore, sizeAfter);
		
		//the expensesFrams should hide AddExpensePanel
		expensesFrame = (ExpensesFrame) getField(expensesController, "expensesFrame");
		assertFalse(((AddExpensePanel) getField(expensesFrame, "purchaseExpensePanel")).isShowing());
		
	}
	
	/**
	 * Test case: Add Bill Expense
	 * @throws Exception 
	 */
	@Test
	public void addBillExpense() throws Exception {
		
		expensesController.addExpense();
		
		//the expensesFrame should display AddExpensePanel
		expensesFrame = (ExpensesFrame) getField(expensesController, "expensesFrame");
		assertTrue(((AddExpensePanel) getField(expensesFrame, "billExpensePanel")).isShowing());
		
		
		//the model should store a new expense of bill type
		billExpense = new BillExpense("INTERNET", "Bell", "N/A", (float) 7.99, 
										"Monthly", "Credit Card", "Unpaid", "2015-04-15");
		assertTrue(billExpense.save("testName"));
		
		
		//the view should update to display the newly added expense
		ViewExpensesPanel view = (ViewExpensesPanel) getField(expensesController, "viewExpensesPanel");
		int sizeBefore = view.getTable().getRowCount();
		
		expensesController.saveExpense(billExpense);
		
		view = (ViewExpensesPanel) getField(expensesController, "viewExpensesPanel");
		int sizeAfter = view.getTable().getRowCount();
		
		assertNotEquals(sizeBefore, sizeAfter);
		
		//the expensesFrams should hide AddExpensePanel
		expensesFrame = (ExpensesFrame) getField(expensesController, "expensesFrame");
		assertFalse(((AddExpensePanel) getField(expensesFrame, "billExpensePanel")).isShowing());
		
	}

}
