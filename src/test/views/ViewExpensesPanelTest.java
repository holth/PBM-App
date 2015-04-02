package test.views;

import static org.junit.Assert.*;

import org.jdesktop.swingx.JXTreeTable;
import org.junit.*;

import views.ViewExpensesPanel;

public class ViewExpensesPanelTest {
	
	ViewExpensesPanel expensesPanel;
	
	@Before
	public void setUp() {
		expensesPanel = new ViewExpensesPanel("AppDemo", "all", "all", "all");
	}
	
	/**
	 * Test the default components of ViewExpensePanel class.
	 * ViewExpensePanel must contain a DataTable.
	 */
	@Test
	public void testDefaultComponents() {
		
		assertNotNull(expensesPanel.getTable());
	}

}
