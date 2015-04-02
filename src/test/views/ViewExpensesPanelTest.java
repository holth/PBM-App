package test.views;

import static org.junit.Assert.*;

import org.jdesktop.swingx.JXTreeTable;
import org.junit.*;

import views.ViewExpensesPanel;

public class ViewExpensesPanelTest {
	
	ViewExpensesPanel expensesPanel;
	
	@Before
	public void setUp() {
		expensesPanel = new ViewExpensesPanel("John", "all", "all", "all");
	}
	
	/**
	 * Test the default components of ViewExpensePanel class.
	 * ViewExpensePanel must contain a JXTreeTable.
	 */
	@Test
	public void testDefaultComponents() {
		// TODO: Table is present.
	}
	
	/*----------
	 * TO DELETE
	 * ---------
	 */
	@org.junit.Test
	public void TestGetTable() {
		ViewExpensesPanel v =new ViewExpensesPanel("abc", "all", "credit", "unpaid");
		JXTreeTable testTable = v.getTable();
		assertNotNull(testTable);
	}

}
