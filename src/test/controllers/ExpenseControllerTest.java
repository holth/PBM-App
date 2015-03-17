package test.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import views.ExpensesFrame;
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



	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
