package test.views;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.junit.*;

import views.*;

public class ViewsTest extends ViewsTestHelper {

	UsersFrame usersFrame;
	ExpensesFrame expensesFrame;
	boolean success;
	
	@Before
	public void setUp() {
		
		usersFrame = new UsersFrame();
		expensesFrame = new ExpensesFrame("AppDemo");
		
		success = false;
	}

	/**
	 * Test the initialization of the UsersFrame object.
	 * @throws Exception
	 */
	@Test
	public void testUsersFrame() throws Exception {
		
		assertNotNull(usersFrame);
	}
	
	/**
	 * Test listening of the 'login' button.
	 * @throws Exception
	 */
	@Test
	public void testLoginButton() throws Exception {
		
		UsersLoginPanel loginPanel = usersFrame.getLoginPanel();
		
		// Action Listener to change the value of success to TRUE
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				success = true;
			}
		};
		
		// Add the action listener
		loginPanel.login(listener);
		
		// Simulate click
		((JButton) getField(loginPanel, "btnLogin")).doClick();
		
		assertTrue(success);
	}

	/**
	 * Test listening of the 'signup now' button.
	 * @throws Exception
	 */
	@Test
	public void testSignupNowButton() throws Exception {
		
		UsersLoginPanel loginPanel = usersFrame.getLoginPanel();
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				success = true;
			}
		};
		
		loginPanel.showSignup(listener);
		
		((JButton) getField(loginPanel, "btnShowSignup")).doClick();
		
		assertTrue(success);
	}
	
	/**
	 * Test listening of the 'signup' button.
	 * @throws Exception
	 */
	@Test
	public void testSignupButton() throws Exception {
		
		UsersSignupPanel signupPanel = new UsersSignupPanel();
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				success = true;
			}
		};
		
		signupPanel.signup(listener);
		
		((JButton) getField(signupPanel, "btnSignup")).doClick();
		
		assertTrue(success);
	}
	
	/**
	 * Test listening of the 'cancel' button during signup.
	 * @throws Exception
	 */
	@Test
	public void testCancelButton() throws Exception {
		
		UsersSignupPanel signupPanel = new UsersSignupPanel();
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				success = true;
			}
		};
		
		signupPanel.cancelSignup(listener);
		
		((JButton) getField(signupPanel, "btnCancelSignup")).doClick();
		
		assertTrue(success);
	}

	/**
	 * Test the initialization of the ExpensesFrame object.
	 * @throws Exception
	 */
	@Test
	public void testExpensesFrame() throws Exception {
		
		assertNotNull(expensesFrame);
	}
	
	/**
	 * Test listening of the 'add expense' button.
	 * @throws Exception
	 */
	@Test
	public void testAddExpenseButton() throws Exception {
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				success = true;
			}
		};
		
		expensesFrame.addExpense(listener);
		
		((JButton) getField(expensesFrame, "btnAddExpense")).doClick();
		
		assertTrue(success);
	}
	
	/**
	 * Test listening of the 'update expense' button.
	 * @throws Exception
	 */
	@Test
	public void testUpdateStatusButton() throws Exception {
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				success = true;
			}
		};
		
		expensesFrame.updateStatus(listener);
		
		((JButton) getField(expensesFrame, "btnUpdateStatus")).doClick();
		
		assertTrue(success);
	}
	
	/**
	 * Test listening of the 'delete expense' button.
	 * @throws Exception
	 */
	@Test
	public void testDeleteExpenseButton() throws Exception {
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				success = true;
			}
		};
		
		expensesFrame.deleteExpense(listener);
		
		((JButton) getField(expensesFrame, "btnDeleteExpense")).doClick();
		
		assertTrue(success);
	}
	
	/**
	 * Test listening of the 'filter expenses' button.
	 * @throws Exception
	 */
	@Test
	public void testFilterExpensesButton() throws Exception {
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				success = true;
			}
		};
		
		expensesFrame.filterExpenses(listener);
		
		((JButton) getField(expensesFrame, "btnFilter")).doClick();
		
		assertTrue(success);
	}
	
	/**
	 * Test listening of the 'save expense' button.
	 * @throws Exception
	 */
	@Test
	public void testSaveExpenseButton() throws Exception {
		
		AddExpensePanel addExpense = expensesFrame.getPurchasePanel();
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				success = true;
			}
		};
		
		addExpense.save(listener);
		
		((JButton) getField(addExpense, "btnSave")).doClick();
		
		assertTrue(success);
	}
}
