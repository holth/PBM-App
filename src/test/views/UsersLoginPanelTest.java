package test.views;

import static org.junit.Assert.*;

import javax.swing.*;

import org.junit.*;

import views.*
;

public class UsersLoginPanelTest extends ViewsTestHelper {
	
	UsersLoginPanel loginPanel;
	
	@Before
	public void setUp() throws Exception {
		loginPanel = new UsersLoginPanel();
		
		// Set default value for username and password for later use
		((JTextField) getField(loginPanel, "txtFieldName")).setText("John");
		((JPasswordField) getField(loginPanel, "pwdFieldPwd")).setText("myPassword");
	}
	
	/**
	 * Test the default components of the UsersLoginPanel class.
	 * UsersLoginPanel must contain a field for 'username', a
	 * field for 'password', a submit button and a 'signup now'
	 * button.
	 * @throws Exception
	 */
	@Test
	public void testDefaultComponents() throws Exception {
		
		// Fields name and password exist
		assertTrue(((JTextField) getField(loginPanel, "txtFieldName")).isVisible());
		assertTrue(((JPasswordField) getField(loginPanel, "pwdFieldPwd")).isVisible());
		
		// Login and "Signup now" buttons exist
		assertTrue(((JButton) getField(loginPanel, "btnLogin")).isVisible());
		assertTrue(((JButton) getField(loginPanel, "btnShowSignup")).isVisible());
	}
	
	/**
	 * Test the clearAllFields() method of UsersLoginPanel class.
	 * After calling the method, the fields 'username' and
	 * 'password' must be empty.
	 * @throws Exception
	 */
	@Test
	public void testClearAllFields() throws Exception {
		
		// Field name and password are not empty
		assertEquals("John", loginPanel.getUsername());
		assertNotEquals(0, loginPanel.getPassword().length);
		
		// Call the method
		loginPanel.clearAllFields();
		
		// Username must be blank
		assertEquals("", loginPanel.getUsername());
		
		// Password's lenth = 0
		assertEquals(0, loginPanel.getPassword().length);
	}
	
	/**
	 * Test the getUsername() method of UsersLoginPanel class.
	 * The method must return the correct 'username'.
	 * @throws Exception
	 */
	@Test
	public void textGetUsername() throws Exception {

		assertEquals("John", loginPanel.getUsername());
	}
	
	/**
	 * Test the getPassword() method of UsersLoginPanel class.
	 * The method must return an array of characters.
	 * @throws Exception
	 */
	@Test
	public void textGetPassword() throws Exception {
		
		// Password must be an array of characters
		char[] charArray = {'a', 'b', 'c'};
		assertSame(charArray.getClass(), loginPanel.getPassword().getClass());
	}

} // EOF
