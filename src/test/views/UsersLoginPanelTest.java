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

	@Test
	public void testDefaultComponents() throws Exception {
		
		// Fields name and password exist
		assertTrue(((JTextField) getField(loginPanel, "txtFieldName")).isVisible());
		assertTrue(((JPasswordField) getField(loginPanel, "pwdFieldPwd")).isVisible());
		
		// Login and "Signup now" buttons exist
		assertTrue(((JButton) getField(loginPanel, "btnLogin")).isVisible());
		assertTrue(((JButton) getField(loginPanel, "btnShowSignup")).isVisible());
	}
	
	@Test
	public void testClearAllFields() throws Exception {
		
		// Field name and password are not empty
		assertEquals("John", loginPanel.getUsername());
		assertNotEquals(0, loginPanel.getPassword().length);
		
		loginPanel.clearAllFields();
		
		// Fields name and password must be blank
		assertEquals("", loginPanel.getUsername());
		assertEquals(0, loginPanel.getPassword().length);
	}
	
	@Test
	public void textGetUsername() throws Exception {
		
		// Username must be a String
		assertEquals("John".getClass(), loginPanel.getUsername().getClass());
	}
	
	@Test
	public void textGetPassword() throws Exception {
		
		// Password must be an array of characters
		char[] charArray = {'a', 'b', 'c'};
		assertSame(charArray.getClass(), loginPanel.getPassword().getClass());
	}

} // EOF
