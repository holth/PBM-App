package test.views;

import static org.junit.Assert.*;

import org.junit.*;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;

import views.AddExpensePanel;

public class AddExpensePanelTest extends ViewsTestHelper {

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
		
		assertTrue(((JComboBox) getField(purchaseExpense, "selectCategory")).isVisible());
		
		// Cannot be visible
		assertFalse(((JTextField) getField(purchaseExpense, "textFieldCategory")).isVisible());
		
		assertTrue(((JTextField) getField(purchaseExpense, "textFieldProvider")).isVisible());
		assertTrue(((JTextField) getField(purchaseExpense, "textFieldLocation")).isVisible());
		assertTrue(((JTextField) getField(purchaseExpense, "textFieldAmount")).isVisible());
		assertTrue(((JDateChooser) getField(purchaseExpense, "chooserDate")).isVisible());
		assertTrue(((JComboBox) getField(purchaseExpense, "selectMode")).isVisible());
		assertTrue(((JComboBox) getField(purchaseExpense, "selectStatus")).isVisible());
		
		assertTrue(((JButton) getField(purchaseExpense, "btnSave")).isVisible());
		assertTrue(((JButton) getField(purchaseExpense, "btnCancel")).isVisible());
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
		
		assertTrue(((JComboBox) getField(billExpense, "selectCategory")).isVisible());
		
		// Cannot be visible
		assertFalse(((JTextField) getField(billExpense, "textFieldCategory")).isVisible());
		
		assertTrue(((JTextField) getField(billExpense, "textFieldProvider")).isVisible());
		assertTrue(((JTextField) getField(billExpense, "textFieldAmount")).isVisible());
		assertTrue(((JComboBox) getField(billExpense, "selectInterval")).isVisible());
		assertTrue(((JComboBox) getField(billExpense, "selectMode")).isVisible());
		assertTrue(((JComboBox) getField(purchaseExpense, "selectStatus")).isVisible());
		
		assertTrue(((JButton) getField(billExpense, "btnSave")).isVisible());
		assertTrue(((JButton) getField(billExpense, "btnCancel")).isVisible());
	}
	
	/**
	 * Test clearAllFields() method of AddExpensePanel class.
	 * The method must reset all select fields to its initial
	 * value and all fields to empty.
	 * @throws Exception
	 */
	@Test
	public void testClearAllFields() throws Exception {
		
		((JTextField) getField(billExpense, "textFieldCategory")).setText("FOOD");
		((JTextField) getField(billExpense, "textFieldProvider")).setText("McDonalds");
		((JTextField) getField(billExpense, "textFieldAmount")).setText("9.95");
		
		billExpense.clearAllFields();
		
		assertEquals("", ((JTextField) getField(billExpense, "textFieldCategory")).getText());
		assertEquals("", ((JTextField) getField(billExpense, "textFieldProvider")).getText());
		assertEquals("", ((JTextField) getField(billExpense, "textFieldAmount")).getText());
	}

}
