package test.views;

import static org.junit.Assert.*;

import org.junit.*;

import views.*;

public class ExpensesFrameTest extends ViewsTestHelper {
	
	ExpensesFrame expenseFrame;
	
	@Before
	public void setUp() {
		// ...
	}
	
	/**
	 * Test the default components of ExpensesFrame class.
	 * ExpensesFrame must contain a title, four sections
	 * (panels), some fields and buttons to filter expenses,
	 * and three action buttons.
	 * @throws Exception
	 */
	@Test
	public void testDefaultComponents() throws Exception {
		
		// TODO: Application title = Personal Budget Manager
		// TODO: Existence of top, middle, right and bottom panels
		// TODO: Existence of expense and add expense panels
		// TODO: Expense panel hidden by default
		// TODO: Existence of filters fields and buttons
		// TODO: Existence of add, updated, delete buttons
	}
	
	/**
	 * Test the getUsername() methods of ExpensesFrame class.
	 * The method must return the current user's username.
	 */
	@Test
	public void testGetUsername() {
		// TODO: Must return the current user's username
	}
	
	/**
	 * Test the hideExpenseForm() methods of ExpensesFrame class.
	 * The method must hide the right section (panel).
	 */
	@Test
	public void testHideExpenseForm() {
		// TODO: Right panel must be invisible
	}
	
	/**
	 * Test the showAddPurchaseForm method of ExpensesFrame class.
	 * The method must show the right section (panel) and, in it,
	 * show the purchase expense form.
	 */
	@Test
	public void testShowAddPurchaseForm() {
		// TODO: Purchase must be visible
	}
	
	/**
	 * Test the showAddBillForm() methods of ExpensesFrame class.
	 * The method must show the right section (panel) and, in it,
	 * show the bill expense form.
	 */
	@Test
	public void testShowAddBillForm() {
		// TODO: Bill must be visible
	}
	
	/**
	 * Test the clearFilter() methods of ExpensesFrame class.
	 * The method must reset the fields for filters to its
	 * default value.
	 */
	@Test
	public void testClearFilter() {
		// TODO: Reset filters fields to index = 0
	}
	
	/*----------
	 * TO DELETE
	 * ---------
	 */
	@Test
	public void testExpensesFrame()
	{
		ExpensesFrame ef = new ExpensesFrame("abc");
		
		String s = ef.getUsername();
		assertSame("abc",s);
		
		ViewExpensesPanel vep = ef.getExpensesPanel();
		assertNotNull(vep);
		
		AddExpensePanel aep = ef.getPurchasePanel();
		assertNotNull(aep);
		
	    aep = ef.getBillPanel();
		assertNotNull(aep);
		
		s = ef.getFilterCategory();
		assertNotNull(s);
		
		s = ef.getFilterType();
		assertNotNull(s);
		
		s = ef.getFilterStatus();
		assertNotNull(s);
		
	}

}
