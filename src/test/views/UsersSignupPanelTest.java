package test.views;

import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.*;

import views.*;

public class UsersSignupPanelTest extends ViewsTestHelper {

	UsersSignupPanel signupPanel;
	
	@Before
	public void setUp() throws Exception {
		signupPanel = new UsersSignupPanel();
		
		// Set default value for username, password and password confirm for later use
		((JTextField) getField(signupPanel, "txtFieldName")).setText("John");
		((JPasswordField) getField(signupPanel, "pwdFieldPwd")).setText("myPassword");
		((JPasswordField) getField(signupPanel, "pwdFieldPwdConfirm")).setText("myPassword");
	}
	
	/**
	 * Test the default components of UsersSignupPanel class.
	 * UsersSignupPanel must contain a field for 'username',
	 * a field for 'password', a field for 'password's
	 * confirmation', a submit button and a cancel button.
	 * @throws Exception
	 */
	@Test
	public void testDefaultComponents() throws Exception {
		
		// Existence of name, password and confirm fields
		assertTrue(((JTextField) getField(signupPanel, "txtFieldName")).isVisible());
		assertTrue(((JPasswordField) getField(signupPanel, "pwdFieldPwd")).isVisible());
		assertTrue(((JPasswordField) getField(signupPanel, "pwdFieldPwdConfirm")).isVisible());
		
		// Existence of signup button and cancel button
		assertTrue(((JButton) getField(signupPanel, "btnSignup")).isVisible());
		assertTrue(((JButton) getField(signupPanel, "btnCancelSignup")).isVisible());
	}
	
	/**
	 * Test the clearAllFields() method of UsersSignupPanel class.
	 * After calling the method, the fields 'username', 'password'
	 * and 'password confirmation' must be empty.
	 * @throws Exception
	 */
	@Test
	public void testClearAllFields() throws Exception {
		
		// Field name, password and confirm are not empty
		assertEquals("John", signupPanel.getUsername());
		assertNotEquals(0, signupPanel.getPwd().length);
		assertNotEquals(0, signupPanel.getPwdConfirm().length);
		
		signupPanel.clearAllFields();
		
		// Username must be blank
		assertEquals("", signupPanel.getUsername());
		
		// The length of password and confirmation must be 0.
		assertEquals(0, signupPanel.getPwd().length);
		assertEquals(0, signupPanel.getPwdConfirm().length);
	}
	
	/**
	 * Test the getUsername() method of UsersSignupPanel class.
	 * The method must return the correct 'username'.
	 * @throws Exception
	 */
	@Test
	public void testGetUsername() throws Exception {

		assertEquals("John", signupPanel.getUsername());
	}
	
	/**
	 * Test the getPwd() method of UsersSignupPanel class.
	 * The method must return an array of characters.
	 */
	@Test
	public void testGetPwd() {
		
		// Password must be an array of characters
		char[] charArray = {'a', 'b', 'c'};
		assertSame(charArray.getClass(), signupPanel.getPwd().getClass());
	}
	
	/**
	 * Test the getPwdConfirm() method of UsersSignupPanel class.
	 * The method must return an array of characters.
	 */
	@Test
	public void testGetPwdConfirm() {
		
		// Password confirmation must be an array of characters
		char[] charArray = {'a', 'b', 'c'};
		assertSame(charArray.getClass(), signupPanel.getPwdConfirm().getClass());
	}

}
