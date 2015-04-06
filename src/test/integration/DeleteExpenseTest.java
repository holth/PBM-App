package test.integration;


import java.util.ArrayList;

import test.helpers.*;
import views.*;
import controllers.*;
import models.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeleteExpenseTest extends TestHelper{
	
	private ExpensesFrame expensesFrame;
	private ExpensesController expensesController;
	private Expense_BLL expenseBLL;

	@Before
	public void setUp() throws Exception {
		expensesFrame = new ExpensesFrame("testName");
		expensesController = new ExpensesController(expensesFrame);
		expenseBLL = new Expense_BLL();
	}

	/**
	 * Test case: Delete Atomic Expense
	 * @throws Exception 
	 */
	@Test
	public void deleteAtomicExpense() throws Exception {
		
		//before delete action
		ViewExpensesPanel view = (ViewExpensesPanel) getField(expensesController, "viewExpensesPanel");
		int sizeBefore = view.getTable().getRowCount();
		ArrayList<ArrayList<String>> expensesBefore = expenseBLL.viewExpenseBy("testName", "All", "All", "All");
		
		//delete action
		expensesController.deleteExpense(view.getTable(), 1);

		//after delete action
		
		//the model should delete an expense
		ArrayList<ArrayList<String>> expensesAfter = expenseBLL.viewExpenseBy("testName", "All", "All", "All");
		assertEquals(expensesBefore.size() - 1, expensesAfter.size());
		
		//the controller should update the view
		view = (ViewExpensesPanel) getField(expensesController, "viewExpensesPanel");
		int sizeAfter = view.getTable().getRowCount();
		assertEquals(sizeBefore - 1, sizeAfter);
		
	}
	
	/**
	 * Test case: Delete all expenses of a category
	 * @throws Exception 
	 */
	@Test
	public void deleteCategoryExpenses() throws Exception {
		
		//before delete action
		ViewExpensesPanel view = (ViewExpensesPanel) getField(expensesController, "viewExpensesPanel");
		int sizeBefore = view.getTable().getRowCount();

		//delete action
		expensesController.deleteExpense(view.getTable(), 0);

		//after delete action
		
		//the model should delete all expenses of a category
		ArrayList<ArrayList<String>> expensesAfter = expenseBLL.viewExpenseBy("testName", "FOOD & DINNING", "All", "All");
		assertEquals(expensesAfter.size(), 0);
		
		//the controller should update the view
		view = (ViewExpensesPanel) getField(expensesController, "viewExpensesPanel");
		int sizeAfter = view.getTable().getRowCount();
		assertNotEquals(sizeBefore, sizeAfter);
		assertNotEquals(sizeBefore - 1, sizeAfter);
		
	}

}
