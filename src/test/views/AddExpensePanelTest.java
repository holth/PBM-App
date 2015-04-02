package test.views;

import static org.junit.Assert.*;

import org.junit.*;

import views.AddExpensePanel;

public class AddExpensePanelTest {

	AddExpensePanel purchaseExpense;
	AddExpensePanel billExpense;
	
	@Before
	public void setUp() {
		purchaseExpense = new AddExpensePanel("purchase");
		billExpense = new AddExpensePanel("bill");
	}
	
	/**
	 * Test default components of purchase expense form
	 * of the AddExpensePanel class.
	 * The purchase expense form must contain fields for
	 * category, service provider, amount, payment mode,
	 * payment status and due date.
	 * It must also contain save button and cancel button.
	 * @throws Exception
	 */
	@Test
	public void testPurchaseComponents() throws Exception {
		fail("Not yet implemented");
	}
	
	/**
	 * Test default components of bill expense form
	 * of the AddExpensePanel class.
	 * The purchase expense form must contain fields for
	 * category, service provider, location, amount, 
	 * payment mode, payment status, bill interval
	 * and due date.
	 * It must also contain save button and cancel button.
	 * @throws Exception
	 */
	@Test
	public void testBillComponents() throws Exception {
		fail("Not yet implemented");
	}
	
	/**
	 * Test clearAllFields() method of AddExpensePanel class.
	 * The method must reset all select fields to its initial
	 * value and all fields to empty.
	 * @throws Exception
	 */
	@Test
	public void testClearAllFields() throws Exception {
		fail("Not yet implemented");
	}

}
