package test.integration;


import test.helpers.*;
import views.*;
import controllers.*;
//import models.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UpdateStatusTest extends TestHelper{
	
	private ExpensesFrame expensesFrame;
	private ExpensesController expensesController;
	//private Expense_BLL expenseBLL;

	@Before
	public void setUp() throws Exception {
		expensesFrame = new ExpensesFrame("testName");
		expensesController = new ExpensesController(expensesFrame);
		//expenseBLL = new Expense_BLL();
	}

	/**
	 * Test case: Update Expense Status
	 * @throws Exception 
	 */
	@Test
	public void updateStatus() throws Exception {
		
		//before update action
		ViewExpensesPanel view = (ViewExpensesPanel) getField(expensesController, "viewExpensesPanel");
		int rowIndexToUpdate = 2;
		String statusBefore = (String) view.getTable().getValueAt(rowIndexToUpdate, 9);
		
		//update action
		expensesController.updateStatus(view.getTable(), rowIndexToUpdate);

		//after update action
		
		//the model should update the expense status
		
		//the controller should update the view
		view = (ViewExpensesPanel) getField(expensesController, "viewExpensesPanel");
		String statusAfter = (String) view.getTable().getValueAt(rowIndexToUpdate, 9);
		assertNotEquals(statusBefore, statusAfter);
		
	}

}
