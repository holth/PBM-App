package test.views;

import static org.junit.Assert.*;

import javax.swing.*;

import org.junit.*;

import test.helpers.*;
import views.*;


public class ExpensesFrameTest extends TestHelper {
	
	ExpensesFrame expensesFrame;
	
	@Before
	public void setUp() throws Exception {
		
		expensesFrame = new ExpensesFrame("AppDemo");
		
		((JComboBox) getField(expensesFrame, "selectFilterCategory")).setSelectedIndex(1);
		((JComboBox) getField(expensesFrame, "selectFilterType")).setSelectedIndex(1);
		((JComboBox) getField(expensesFrame, "selectFilterStatus")).setSelectedIndex(1);
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
		
		// Application title equal to "Personal Budget Manager"
		assertSame("Personal Budget Manager", expensesFrame.getTitle());

		// Existence of top, middle, right and bottom panels
		// Right panel hiddent by default
		assertTrue(((JPanel) getField(expensesFrame, "topPanel")).isVisible());
		assertTrue(((JPanel) getField(expensesFrame, "mainPanel")).isVisible());
		assertFalse(((JPanel) getField(expensesFrame, "rightPanel")).isVisible());
		assertTrue(((JPanel) getField(expensesFrame, "bottomPanel")).isVisible());
		
		// Existence of add expense panels
		assertNotNull(((JPanel) getField(expensesFrame, "purchaseExpensePanel")));
		assertNotNull(((JPanel) getField(expensesFrame, "billExpensePanel")));
		
		// Existence of filters fields and buttons
		assertNotNull(((JComboBox) getField(expensesFrame, "selectFilterCategory")));
		assertNotNull(((JComboBox) getField(expensesFrame, "selectFilterType")));
		assertNotNull(((JComboBox) getField(expensesFrame, "selectFilterStatus")));
		assertNotNull(((JButton) getField(expensesFrame, "btnFilter")));
		assertNotNull(((JButton) getField(expensesFrame, "btnClearFilter")));
		
		// Existence of add, updated, delete buttons
		assertNotNull(((JButton) getField(expensesFrame, "btnAddExpense")));
		assertNotNull(((JButton) getField(expensesFrame, "btnUpdateStatus")));
		assertNotNull(((JButton) getField(expensesFrame, "btnDeleteExpense")));
	}
	
	/**
	 * Test the getUsername() methods of ExpensesFrame class.
	 * The method must return the current user's username.
	 */
	@Test
	public void testGetUsername() {

		assertEquals("AppDemo", expensesFrame.getUsername());
	}
	
	/**
	 * Test the hideExpenseForm() methods of ExpensesFrame class.
	 * The method must hide the right section (panel).
	 * @throws Exception
	 */
	@Test
	public void testHideExpenseForm() throws Exception {
		
		expensesFrame.showAddBillForm();
		
		expensesFrame.hideExpenseForm();
		
		assertFalse(((JPanel) getField(expensesFrame, "rightPanel")).isVisible());
	}
	
	/**
	 * Test the showAddPurchaseForm method of ExpensesFrame class.
	 * The method must show the right section (panel) and, in it,
	 * show the purchase expense form.
	 * @throws Exception
	 */
	@Test
	public void testShowAddPurchaseForm() throws Exception {
		
		expensesFrame.showAddPurchaseForm();
		
		assertTrue(((JPanel) getField(expensesFrame, "rightPanel")).isVisible());
		assertTrue(((JPanel) getField(expensesFrame, "purchaseExpensePanel")).isShowing());
	}
	
	/**
	 * Test the showAddBillForm() methods of ExpensesFrame class.
	 * The method must show the right section (panel) and, in it,
	 * show the bill expense form.
	 * @throws Exception
	 */
	@Test
	public void testShowAddBillForm() throws Exception {
		
		expensesFrame.showAddBillForm();
		
		assertTrue(((JPanel) getField(expensesFrame, "rightPanel")).isVisible());
		assertTrue(((JPanel) getField(expensesFrame, "billExpensePanel")).isShowing());
	}
	
	/**
	 * Test the clearFilter() methods of ExpensesFrame class.
	 * The method must reset the fields for filters to its
	 * default value.
	 * @throws Exception
	 */
	@Test
	public void testClearFilter() throws Exception {
		
		expensesFrame.clearFilter();
		
		assertEquals("Category", expensesFrame.getFilterCategory());
		assertEquals("Type", expensesFrame.getFilterType());
		assertEquals("Status", expensesFrame.getFilterStatus());
	}

}
