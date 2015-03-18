package test.controllers;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import views.ExpensesFrame;
import views.ViewExpensesPanel;
import controllers.ExpensesController;



/**
 *
 *
 */
public class ExpenseControllerTest {
	
	private ExpensesController expenses;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ExpensesFrame expensesFrame = new ExpensesFrame("AppDemo");
		expenses = new ExpensesController(expensesFrame);
	}


	/**
	 * should show viewExpensesPanel
	 */
	@Test
	public void testViewExpenses() throws Exception {
		
		expenses.viewExpenses();
		assertTrue(((ViewExpensesPanel) getField(expenses, "viewExpensesPanel")).isShowing());
		
	}
	
	/**
	 * should show AddExpensePanel
	 *//*
	@Test
	public void testShowAddExpensePanel() throws Exception {
		
		expenses.addExpense();
		assertTrue(((AddExpensePanel) getField(expenses, "purchaseExpensePanel")).isShowing() ||
				((AddExpensePanel) getField(expenses, "billExpensesPanel")).isShowing());
		
	}*/
	
	
	/**
	 * should add an expense
	 */
	@Test
	public void testAddExpense() throws Exception {


		ViewExpensesPanel view = (ViewExpensesPanel) getField(expenses, "viewExpensesPanel");
		int sizeBefore = view.getTable().getRowCount();
		
		expenses.saveExpense("Purchase", "FOOD & DINNING", "Tim Hortons", "Concordia", "2015-03-15", 
				(float) 7.99, "N/A", "Credit Card", "Unpaid", "2015-04-15");

		view = (ViewExpensesPanel) getField(expenses, "viewExpensesPanel");
		int sizeAfter = view.getTable().getRowCount();
		
		assertTrue(sizeBefore + 1 == sizeAfter || sizeBefore + 2 == sizeAfter);
		
	}
	
	/**
	 * should update status
	 */
	@Test
	public void testUpdateStatus() throws Exception {

		ViewExpensesPanel view = (ViewExpensesPanel) getField(expenses, "viewExpensesPanel");
		int rowIndexToUpdate = 2;
		String statusBefore = (String) view.getTable().getValueAt(rowIndexToUpdate, 9);
		
		expenses.updateStatus(view.getTable(), rowIndexToUpdate);

		view = (ViewExpensesPanel) getField(expenses, "viewExpensesPanel");
		String statusAfter = (String) view.getTable().getValueAt(rowIndexToUpdate, 9);
		
		//System.out.println(statusBefore + statusAfter);
		assertNotEquals(statusBefore, statusAfter);
		
	}
	
	
	/**
	 * should delete an expense
	 */
	@Test
	public void testDeleteExpenses() throws Exception {


		ViewExpensesPanel view = (ViewExpensesPanel) getField(expenses, "viewExpensesPanel");
		int sizeBefore = view.getTable().getRowCount();
		
		expenses.deleteExpense(view.getTable(), 11);

		view = (ViewExpensesPanel) getField(expenses, "viewExpensesPanel");
		int sizeAfter = view.getTable().getRowCount();
		
		assertNotSame(sizeBefore, sizeAfter);
		
	}
	

	/**
  	 * Gets the field value from an instance.  The field we wish to retrieve is
  	 * specified by passing the name.  The value will be returned, even if the
  	 * field would have private or protected access.
  	 */
  	private Object getField( Object instance, String name ) throws Exception
  	{
  		Class c = instance.getClass();

  		// Retrieve the field with the specified name
  		Field f = c.getDeclaredField( name );

  		// *MAGIC* make sure the field is accessible, even if it
  		// would be private or protected
  		f.setAccessible( true );

  		// Return the value of the field for the instance
  		return f.get( instance );
  	}
}
